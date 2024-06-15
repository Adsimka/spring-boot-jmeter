package com.spring_boot_hateoas.service;

import com.spring_boot_hateoas.entity.Product;
import com.spring_boot_hateoas.exception.ProductNotFoundException;
import com.spring_boot_hateoas.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductService {

    private final ProductRepository repository;

    @Transactional
    public Product save(Product product) {
        return repository.save(product);
    }

    @Transactional
    public List<Product> saveProducts(List<Product> products) {
        return repository.saveAll(products);
    }

    public Product findById(Long id) {
        return repository.findById(id)
                .orElse(null);
    }

    public List<Product> findAll() {
        return repository.findAll();
    }

    @Transactional
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Transactional
    public Product update(Product product) {
        Product existingProduct = repository.findById(product.getId())
                .orElse(null);

        if (existingProduct != null) {
            existingProduct.setName(product.getName());
            existingProduct.setPrice(product.getPrice());
            existingProduct.setQuantity(product.getQuantity());
        } else {
            throw new ProductNotFoundException("Product not found");
        }

        return repository.save(existingProduct);
    }
}
