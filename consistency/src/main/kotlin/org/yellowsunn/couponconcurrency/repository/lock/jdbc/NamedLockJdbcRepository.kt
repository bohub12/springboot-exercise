package org.yellowsunn.couponconcurrency.repository.lock.jdbc

import org.apache.logging.log4j.util.Supplier
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Repository
import org.yellowsunn.couponconcurrency.repository.lock.LockRepository
import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.SQLException
import java.time.Duration
import javax.sql.DataSource

@Repository
class NamedLockJdbcRepository(
    @Qualifier("lockDataSource") private val lockDataSource: DataSource,
) : LockRepository {
    private val logger = LoggerFactory.getLogger(this::class.java)

    companion object {
        const val GET_LOCK = "SELECT GET_LOCK(?, ?)"
        const val RELEASE_LOCK = "SELECT RELEASE_LOCK(?)"
        const val EXCEPTION_MESSAGE = "LOCK 을 수행하는 중에 오류가 발생하였습니다."
    }

    override fun <T> executeWithLock(
        lockName: String,
        timeout: Duration,
        supplier: Supplier<T>,
    ): T {
        try {
            lockDataSource.connection.use { connection ->
                try {
                    getLock(connection, lockName, timeout)
                    return supplier.get()
                } finally {
                    release(connection, lockName)
                }
            }
        } catch (e: SQLException) {
            throw RuntimeException(e.message, e)
        } catch (e: RuntimeException) {
            throw RuntimeException(e.message, e)
        }
    }

    private fun getLock(
        connection: Connection,
        lockName: String,
        timeout: Duration,
    ) {
        connection.prepareStatement(GET_LOCK).use { preparedStatement ->
            preparedStatement.setString(1, lockName)
            preparedStatement.setInt(2, timeout.toSeconds().toInt())

            checkResultSet(lockName, preparedStatement, "GetLock_")
        }
    }

    private fun release(
        connection: Connection,
        lockName: String,
    ) {
        connection.prepareStatement(RELEASE_LOCK).use { preparedStatement ->
            preparedStatement.setString(1, lockName)
            checkResultSet(lockName, preparedStatement, "ReleaseLock_")
        }
    }

    private fun checkResultSet(
        lockName: String,
        preparedStatement: PreparedStatement,
        type: String,
    ) {
        preparedStatement.executeQuery().use { resultSet ->
            if (!resultSet.next()) {
                logger.error(
                    "USER LEVEL LOCK 쿼리 결과 값이 없습니다. type = [], userLockName ], connection=[]",
                    type,
                    lockName,
                    preparedStatement.connection,
                )
                throw RuntimeException(EXCEPTION_MESSAGE)
            }

            val result = resultSet.getInt(1)

            if (result != 1) {
                logger.error(
                    "USER LEVEL LOCK 쿼리 결과 값이 1이 아닙니다. type = [], result ] userLockName ], connection=[]",
                    type,
                    result,
                    lockName,
                    preparedStatement.connection,
                )
                throw RuntimeException(EXCEPTION_MESSAGE)
            }
        }
    }
}
