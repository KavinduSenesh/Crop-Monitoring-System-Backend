package lk.ijse.springboot.greenshadow.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/healthcheck")
public class HealthCheckController {
    @GetMapping
    public String healthCheck(){
        return "api is up and running..";
    }
}
