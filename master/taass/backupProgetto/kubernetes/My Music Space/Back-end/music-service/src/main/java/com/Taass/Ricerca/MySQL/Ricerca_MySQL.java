package com.Taass.Ricerca.MySQL;

//https://www.youtube.com/watch?v=f5bdUjEIbrg

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class Ricerca_MySQL {

    public static void main(String[] args){
        SpringApplication.run(Ricerca_MySQL.class, args);
    }
}
