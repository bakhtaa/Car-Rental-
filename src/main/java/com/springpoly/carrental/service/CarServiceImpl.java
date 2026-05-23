package com.springpoly.carrental.service;
import com.springpoly.carrental.dto.CarDashboardDTO;
import com.springpoly.carrental.entity.Car;
import com.springpoly.carrental.repository.CarRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CarServiceImpl implements IgestionCar {

    private final CarRepository carRepository;

    @Override
    public Car saveCar(Car c) {
        return carRepository.save(c);
    }

    @Override
    public Car updateCar(Car c) {
        return carRepository.save(c);
    }

    @Override
    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }

    @Override
    public Car getCarById(Long id) {
        return carRepository.findById(id).orElse(null);
    }

    @Override
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    @Override
    public List<Car> findByModel(String mc) {
        return carRepository.findByModelContains(mc);
    }

    @Override
    public Page<Car> getCarsByPage(String mc,int page,int size) {

        return carRepository.findByModelContains(
                mc,
                PageRequest.of(page,size)
        );
    }

    @Override
    public CarDashboardDTO getDashboardStats() {

        List<Car> cars = carRepository.findAll();

        int total = cars.size();

        double value = cars.stream()
                .mapToDouble(Car::getPricePerDay)
                .sum();

        long available = cars.stream()
                .filter(Car::isAvailable)
                .count();

        Map<String, Long> byBrand = cars.stream()
                .filter(c -> c.getBrand() != null)
                .collect(Collectors.groupingBy(
                        c -> c.getBrand().getName(),
                        Collectors.counting()
                ));

        return new CarDashboardDTO(total, value, available, byBrand);
    }

    /*@Override
    public Page<Car> getCarsPaginated(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        return carRepository.findAll(pageable);
    }*/

}