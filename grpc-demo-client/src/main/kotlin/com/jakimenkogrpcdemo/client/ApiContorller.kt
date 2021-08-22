package com.jakimenkogrpcdemo.client

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/")
class ApiContorller(
    val gRpcClientService: GRpcClientService
) {

    @GetMapping("call")
    fun testMethod() = gRpcClientService.call()
}
