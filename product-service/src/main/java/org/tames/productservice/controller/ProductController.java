package org.tames.productservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import org.tames.productservice.dto.CreateProductRequestDto;
import org.tames.productservice.dto.ProductResponseDto;
import org.tames.productservice.service.ProductService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @PostMapping
    public ResponseEntity<ProductResponseDto> createProduct(@RequestBody CreateProductRequestDto createProductRequestDto, UriComponentsBuilder ucb) {
        ProductResponseDto createdProduct = productService.createProduct(createProductRequestDto);
        URI location = ucb.path("products/{productId}").buildAndExpand(createdProduct.id()).toUri();
        return ResponseEntity.created(location).body(createdProduct);
    }

    @PutMapping("{productId}")
    public ResponseEntity<ProductResponseDto> updateProduct(@RequestBody CreateProductRequestDto createProductRequestDto, @PathVariable Long productId) {
        return ResponseEntity.ok(productService.updateProduct(createProductRequestDto, productId));
    }

    @DeleteMapping("{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("{productId}")
    public ResponseEntity<ProductResponseDto> getProductById(@PathVariable Long productId) {
        return ResponseEntity.ok(productService.getProductById(productId));
    }
}
