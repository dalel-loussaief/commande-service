package com.example.tpspringmono;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
@EnableFeignClients
@SpringBootApplication
public class TpspringmonoApplication {

    public static void main(String[] args) {
        SpringApplication.run(TpspringmonoApplication.class, args);
    }

}
