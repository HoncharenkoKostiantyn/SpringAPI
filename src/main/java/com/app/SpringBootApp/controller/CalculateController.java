package com.app.SpringBootApp.controller;

import com.app.SpringBootApp.service.CalculateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CalculateController {

    @Autowired
    CalculateService calculateService;

    @PostMapping("sum")
    public Long calculateSum(@RequestParam("firstNumber") Long firstNumber, @RequestParam("secondNumber") Long secondNumber) {
        return calculateService.sum(firstNumber, secondNumber);
    }

    @GetMapping("getP")
    public String getP() {
        return "3.14";
    }

}
