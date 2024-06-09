package org.yellowsunn.couponconcurrency.config

import com.zaxxer.hikari.HikariDataSource
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary

@Configuration
class DataSourceConfig {
    @Primary
    @Bean(name = ["mainDataSource"])
    @ConfigurationProperties(prefix = "spring.main.datasource.hikari")
    fun mainDataSource(): HikariDataSource {
        return DataSourceBuilder.create().type(HikariDataSource::class.java).build()
    }

    /**
     * LockDataSource 를 굳이 나누는 이유
     *
     * <li>lock 을 위한 커넥션풀과 비즈니스 로직을 실행하기 위한 커넥션풀을 따로 관리하기 위함</li>
     * <li>보통 lock은 비즈니스 로직과 다른 커넥션 혹은 트랜잭션에서 실행되기에 다른 커넥션 풀을 사용하는 것이 좋다</li>
     * <li>같은 커넥션풀을 사용하면, lock을 기다리는 커넥션이 많아져서 비즈니스 로직에 영향을 줄 수 있으니, lock 커넥션풀 사이즈와 비즈니스 로직을 위한 커넥션풀 사이즈를 미리 구분하여 설정해두자</li>
     * <li>application.yml 참고</li>
     */
    @Bean(name = ["lockDataSource"])
    @ConfigurationProperties(prefix = "spring.lock.datasource.hikari")
    fun lockDataSource(): HikariDataSource {
        return DataSourceBuilder.create().type(HikariDataSource::class.java).build()
    }
}
