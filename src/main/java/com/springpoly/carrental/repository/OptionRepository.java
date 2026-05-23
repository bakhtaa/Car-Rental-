package com.springpoly.carrental.repository;



import com.springpoly.carrental.entity.Option;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OptionRepository extends JpaRepository<Option, Long> {
}