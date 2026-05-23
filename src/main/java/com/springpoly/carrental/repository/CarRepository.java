package com.springpoly.carrental.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.springpoly.carrental.entity.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findByModelContains(String mc);

    Page<Car> findByModelContains(
            String mc,
            Pageable pageable
    );
}