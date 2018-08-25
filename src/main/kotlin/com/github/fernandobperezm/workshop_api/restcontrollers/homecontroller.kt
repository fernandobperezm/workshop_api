package com.github.fernandobperezm.workshop_api.restcontrollers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/home")
class HomeController(){

    @GetMapping("/")
    fun index() = "Hello world!"
}