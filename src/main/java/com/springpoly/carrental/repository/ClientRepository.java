package com.springpoly.carrental.repository;
import com.springpoly.carrental.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}