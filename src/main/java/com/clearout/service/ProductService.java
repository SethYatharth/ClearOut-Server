package com.clearout.service;

import com.clearout.dto.ProductDto;
import com.clearout.dto.ProductResponseDto;
import com.clearout.dto.ProductStatusDto;
import com.clearout.entity.IndividualUser;
import com.clearout.entity.Product;
import com.clearout.entity.ProductStatus;
import com.clearout.exception.IndividualUserException;
import com.clearout.exception.ProductException;
import com.clearout.mapper.ProductMapper;
import com.clearout.repository.ProductRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final IndividualUserService individualUserService;
    private final ProductMapper productMapper;

    @PersistenceContext
    private EntityManager entityManager;

    public ProductResponseDto sellProduct(ProductDto productDto, String token) throws IndividualUserException {
        IndividualUser individualUser = individualUserService.getIndividualUser(token);
        Product product =productMapper.toProduct(productDto);
        product.setCity(individualUser.getAddress().getCity());
        product.setIndividualSeller(IndividualUser.builder().id(individualUser.getId()).build());
        return productMapper.toProductResponseDto(productRepository.save(product));
    }

    public List<ProductStatusDto> getAllProducts(String token) throws IndividualUserException {
        IndividualUser individualUser = individualUserService.getIndividualUser(token);
        return productRepository.findAllUnsold(individualUser.getId()).stream().map(product -> productMapper.toProductStatusDto(product, individualUser)).toList();
    }


    public List<ProductStatusDto> getAllProductsByCity(String token) throws IndividualUserException {
        IndividualUser individualUser = individualUserService.getIndividualUser(token);
        return productRepository
                .findUnsoldByCity(
                        individualUser
                        .getAddress()
                                .getCity(),individualUser.getId())
                .stream()
                .map(product -> productMapper.toProductStatusDto(product, individualUser))
                .toList();
    }

    public ProductStatusDto buyProduct(Long id, String token) throws IndividualUserException, ProductException {
        IndividualUser individualUser = individualUserService.getIndividualUser(token);
        Product product = productRepository.findById(id).orElseThrow(()->new ProductException(id+" not found"));
        if(product.getProductStatus().equals(ProductStatus.SOLD)){
            throw new ProductException(id+" is already sold");
        }
        if(product.getIndividualSeller().getId().equals(individualUser.getId())){
            throw new ProductException(individualUser.getId()+" can not buy product you are selling");
        }
        product.getIndividualBuyers().add(IndividualUser.builder().id(individualUser.getId()).build());
        return productMapper.toProductStatusDto(productRepository.save(product), individualUser);
    }

    public List<ProductResponseDto> getPotentialBuyer(String token) throws IndividualUserException {
        return individualUserService
                .getIndividualUser(token)
                .getSellingProducts()
                .stream()
                .map(productMapper::toProductResponseDto)
                .toList();
    }

    @Transactional
    public List<ProductResponseDto> soldProduct(Long id, String token) throws IndividualUserException, ProductException {
        IndividualUser individualUser = individualUserService.getIndividualUser(token);
        if(!individualUser.getSellingProducts().contains(productRepository.findById(id).get())){
            throw new ProductException(id+" product doesn't belong to "+individualUser.getEmail());
        }
        productRepository.updateProductStatus(id, ProductStatus.SOLD);
        entityManager.flush();
        entityManager.clear();
        return individualUserService
                .getIndividualUser(token)
                .getSellingProducts()
                .stream()
                .map(productMapper::toProductResponseDto)
                .toList();
    }
}
