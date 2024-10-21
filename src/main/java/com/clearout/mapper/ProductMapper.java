package com.clearout.mapper;

import com.clearout.dto.ContactDto;
import com.clearout.dto.ProductDto;
import com.clearout.dto.ProductResponseDto;
import com.clearout.dto.ProductStatusDto;
import com.clearout.entity.IndividualUser;
import com.clearout.entity.Product;
import com.clearout.entity.ProductStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProductMapper {

    public Product toProduct(ProductDto productDto) {
        return Product.builder()
                .productName(productDto.productName())
                .description(productDto.description())
                .price(productDto.price())
                .image(productDto.image())
                .createdAt(LocalDateTime.now())
                .productStatus(ProductStatus.UNSOLD)
                .build();
    }

    public ProductResponseDto toProductResponseDto(Product product) {
        List<ContactDto> contactDtos = new ArrayList<>();
        List<IndividualUser> individualBuyers = product.getIndividualBuyers();
        if(individualBuyers != null) {
            for (IndividualUser user :individualBuyers) {
                contactDtos.add(new ContactDto(user.getFirstname(), user.getLastname(), user.getPhoneNo(), user.getEmail()));
            }
        }
        return ProductResponseDto.builder()
                .id(product.getId())
                .productName(product.getProductName())
                .description(product.getDescription())
                .price(product.getPrice())
                .city(product.getCity())
                .image(product.getImage())
                .createdAt(product.getCreatedAt().toLocalDate())
                .productStatus(product.getProductStatus().name())
                .contactDtos(contactDtos)
                .build();
    }

    public ProductStatusDto toProductStatusDto(Product product, IndividualUser individualUser) {

        return new ProductStatusDto(
                product.getId(),
                product.getProductName(),
                product.getDescription(),
                product.getPrice(),
                product.getCity(),
                product.getImage(),
                product.getIndividualBuyers().contains(individualUser),
                product.getCreatedAt().toLocalDate()
        );
    }
}
