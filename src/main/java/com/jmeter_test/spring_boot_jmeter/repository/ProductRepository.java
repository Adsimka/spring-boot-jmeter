package com.jmeter_test.spring_boot_jmeter.repository;

import com.jmeter_test.spring_boot_jmeter.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findByName(String name);
}
