package com.springpoly.carrental.repository;

import com.springpoly.carrental.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand, Long> {
}