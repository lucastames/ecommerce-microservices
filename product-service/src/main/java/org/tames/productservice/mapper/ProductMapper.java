package org.tames.productservice.mapper;

import org.springframework.stereotype.Component;
import org.tames.productservice.dto.CreateProductRequestDto;
import org.tames.productservice.dto.ProductResponseDto;
import org.tames.productservice.entity.Product;

@Component
public class ProductMapper {
    public Product toEntity(CreateProductRequestDto createProductRequestDto) {
        return new Product(createProductRequestDto.name(), createProductRequestDto.description(), createProductRequestDto.price(), createProductRequestDto.quantity(), createProductRequestDto.category());
    }

    public ProductResponseDto toDto(Product product) {
        return new ProductResponseDto(product.getId(), product.getName(), product.getDescription(), product.getPrice(), product.getQuantity(), product.getCategory());
    }

    public void updateEntityWithDto(Product productToUpdate, CreateProductRequestDto createProductRequestDto) {
        productToUpdate.setCategory(createProductRequestDto.category());
        productToUpdate.setDescription(createProductRequestDto.description());
        productToUpdate.setName(createProductRequestDto.name());
        productToUpdate.setPrice(createProductRequestDto.price());
        productToUpdate.setQuantity(createProductRequestDto.quantity());
    }
}
