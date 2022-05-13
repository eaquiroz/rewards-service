package com.rewards.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rewards.service.entity.Purchase;

@Repository
public interface PurchaseRepo extends JpaRepository<Purchase, String> {

}
