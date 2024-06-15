package com.spring_boot_hateoas.controller;

import com.spring_boot_hateoas.entity.Product;
import com.spring_boot_hateoas.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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
    public ResponseEntity<List<Product>> findAll() {
        List<Product> products = service.findAll();

        products.forEach(product -> {
            WebMvcLinkBuilder linkBuilder = linkTo(methodOn(ProductController.class)
                    .findById(product.getId()));
            product.add(linkBuilder.withSelfRel());
        });

        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(@PathVariable Long id) {
        Product product = service.findById(id);

        WebMvcLinkBuilder linkBuilder = linkTo(methodOn(ProductController.class).findAll());
        product.add(linkBuilder.withSelfRel());

        return ResponseEntity.ok(product);
    }

    @PutMapping("/update")
    public Product update(@RequestBody Product product) {
        Product newProduct = service.update(product);
        if (newProduct.getId() == null) {
            WebMvcLinkBuilder linkBuilder = linkTo(methodOn(ProductController.class)
                    .findById(newProduct.getId()));
            newProduct.add(linkBuilder.withSelfRel());
        }
        return newProduct;
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }
}