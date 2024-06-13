package com.jmeter_test.spring_boot_jmeter.controller;

import com.jmeter_test.spring_boot_jmeter.entity.Product;
import com.jmeter_test.spring_boot_jmeter.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;

    @PostMapping("/save/products")
    public List<Product> save(@RequestBody List<Product> products) {
        return service.saveProducts(products);
    }

    @PostMapping("/save/product")
    public Product save(@RequestBody Product product) {
        return service.save(product);
    }

    @GetMapping
    public List<Product> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Product findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @GetMapping("/{name}")
    public Product findByName(@PathVariable String name) {
        return service.findByName(name);
    }

    @PutMapping("/update")
    public Product update(@RequestBody Product product) {
        return service.update(product);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }
}