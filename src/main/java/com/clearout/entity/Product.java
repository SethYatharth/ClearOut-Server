package com.clearout.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String productName;
    private String description;
    private double price;
    private String city;
    private String image;
    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    private ProductStatus productStatus;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    @JsonIgnoreProperties("sellingProducts")
    private IndividualUser individualSeller;

    @ManyToMany
    @JoinTable(
            name = "product_buyer",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "buyer_id")
    )
    @JsonIgnoreProperties("purchasedProducts")
    List<IndividualUser> individualBuyers;

}
