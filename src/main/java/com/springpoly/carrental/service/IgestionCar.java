package com.springpoly.carrental.service;

import com.springpoly.carrental.dto.CarDashboardDTO;
import com.springpoly.carrental.entity.Car;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IgestionCar {

    Car saveCar(Car c);

    Car updateCar(Car c);

    void deleteCar(Long id);

    Car getCarById(Long id);

    List<Car> getAllCars();

    List<Car> findByModel(String mc);

    Page<Car> getCarsByPage(String mc,int page,int size);
    CarDashboardDTO getDashboardStats();

    //Page<Car> getCarsPaginated(int page, int size);

}