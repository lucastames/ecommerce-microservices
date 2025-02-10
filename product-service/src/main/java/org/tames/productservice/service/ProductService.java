package org.tames.productservice.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tames.productservice.dto.CreateProductRequestDto;
import org.tames.productservice.dto.ProductResponseDto;
import org.tames.productservice.entity.Product;
import org.tames.productservice.mapper.ProductMapper;
import org.tames.productservice.repository.ProductRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    public List<ProductResponseDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(productMapper::toDto).toList();
    }

    public ProductResponseDto createProduct(CreateProductRequestDto createProductRequestDto) {
        Product createdProduct = productRepository.save(productMapper.toEntity(createProductRequestDto));
        return productMapper.toDto(createdProduct);
    }

    public ProductResponseDto updateProduct(CreateProductRequestDto createProductRequestDto, Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + productId));
        productMapper.updateEntityWithDto(product, createProductRequestDto);

        return productMapper.toDto(productRepository.save(product));
    }

    public void deleteProduct(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + productId));
        productRepository.delete(product);
    }

    public ProductResponseDto getProductById(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + productId));
        return productMapper.toDto(product);
    }
}
