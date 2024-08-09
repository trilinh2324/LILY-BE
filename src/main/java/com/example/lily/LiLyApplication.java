package com.example.lily;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiLyApplication {

    public static void main(String[] args) {
        SpringApplication.run(LiLyApplication.class, args);
        System.out.println("http://localhost:8090/Products");


    }

}
