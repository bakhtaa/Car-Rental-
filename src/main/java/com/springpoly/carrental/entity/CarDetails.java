package com.springpoly.carrental.entity;



import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CarDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fuelType;
    private String gearbox;
    private int seats;
    private String description;

    @OneToOne
    @JoinColumn(name = "car_id")
    private Car car;
}