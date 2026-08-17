package com.bridgelabz.employeepayrolladdressgreeting.controller;

import com.bridgelabz.employeepayrolladdressgreeting.dto.response.GreetingResponseDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/greeting")
public class GreetingController {

    @GetMapping("/{name}")
    public GreetingResponseDTO getGreeting(@PathVariable String name){
        return new GreetingResponseDTO("Hello " + name + "! Welcome.");
    }


}
