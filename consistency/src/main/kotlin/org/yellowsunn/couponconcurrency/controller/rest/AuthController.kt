package org.yellowsunn.couponconcurrency.controller.rest

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class AuthController {
    @PostMapping("/api/login")
    fun login(
        @RequestBody loginRequest: LoginRequest,
    ): ResponseEntity<Unit> {
        return ResponseEntity.ok()
            .header("Set-Cookie", "userId=${loginRequest.userId}; Max-Age=1800; Path=/")
            .build()
    }
}

data class LoginRequest(
    val userId: String,
)
