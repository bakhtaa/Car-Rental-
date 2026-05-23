package com.springpoly.carrental.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public class CarDashboardDTO {

    private int totalCars;

    private double totalValue;

    private long availableCars;

    private Map<String, Long> carsByBrand;
}