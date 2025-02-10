package org.tames.productservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tames.productservice.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
