package com.rewards.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rewards.service.entity.Customer;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, String> {

}
