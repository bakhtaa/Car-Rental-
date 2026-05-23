package com.springpoly.carrental;

import com.springpoly.carrental.entity.*;
import com.springpoly.carrental.repository.BrandRepository;
import com.springpoly.carrental.repository.OptionRepository;
import com.springpoly.carrental.service.IgestionCar;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CarrentalApplication {

    public static void main(String[] args) {
        SpringApplication.run(CarrentalApplication.class, args);
    }

    @Bean
    CommandLineRunner initData(

    ) {

        return args -> {



            System.out.println("Donnees initialisees avec succes !");
        };

    }

}