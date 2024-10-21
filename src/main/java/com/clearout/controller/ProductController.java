package com.clearout.controller;

import com.clearout.dto.ProductDto;
import com.clearout.dto.ProductResponseDto;
import com.clearout.dto.ProductStatusDto;
import com.clearout.exception.IndividualUserException;
import com.clearout.exception.ProductException;
import com.clearout.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/marketplace")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PreAuthorize("hasAuthority('INDIVIDUAL_USER')")
    @PostMapping("/sell")
    public ResponseEntity<ProductResponseDto> sellProduct(@RequestBody ProductDto productDto,
                                                          @RequestHeader("Authorization") String token) throws IndividualUserException {
        return new ResponseEntity<>(productService.sellProduct(productDto,token), HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('INDIVIDUAL_USER')")
    @GetMapping("/all-products")
    public ResponseEntity<List<ProductStatusDto>> getAllProducts(@RequestHeader("Authorization") String token) throws IndividualUserException{
        return ResponseEntity.ok(productService.getAllProducts(token));
    }

    @PreAuthorize("hasAuthority('INDIVIDUAL_USER')")
    @GetMapping("/all-products/city")
    public ResponseEntity<List<ProductStatusDto>> getAllProductsByCity(@RequestHeader("Authorization") String token) throws IndividualUserException {
        return ResponseEntity.ok(productService.getAllProductsByCity(token));
    }

    @PreAuthorize("hasAuthority('INDIVIDUAL_USER')")
    @PutMapping("/buy/{id}")
    public ResponseEntity<ProductStatusDto> buyProduct(@PathVariable Long id,@RequestHeader("Authorization") String token) throws IndividualUserException, ProductException {
        return ResponseEntity.ok(productService.buyProduct(id,token));
    }


    @PreAuthorize("hasAuthority('INDIVIDUAL_USER')")
    @GetMapping("/potential-buyer")
    public ResponseEntity<List<ProductResponseDto>> getPotentialBuyer(@RequestHeader("Authorization") String token) throws IndividualUserException {
        return ResponseEntity.ok(productService.getPotentialBuyer(token));
    }


    @PreAuthorize("hasAuthority('INDIVIDUAL_USER')")
    @PutMapping("/sold/{id}")
    public ResponseEntity<List<ProductResponseDto>> soldProduct(@PathVariable Long id, @RequestHeader("Authorization") String token) throws IndividualUserException, ProductException {
        return ResponseEntity.ok(productService.soldProduct(id,token));
    }




}
