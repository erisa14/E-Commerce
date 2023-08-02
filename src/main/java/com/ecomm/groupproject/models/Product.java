package com.ecomm.groupproject.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String productName;

    @NotBlank
    private String description;

    @NotNull
    private double price;

    @NotBlank
    private String image;

    @NotBlank
    private String brand;

    @NotBlank
    private  String color;
    private int quantity;

    @Column(updatable=false)
    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<OrderItem> orderItem;

    @Column(updatable=false)
    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<CartItem> cartItem;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="product_id")
    private Wishlist wishlist;

}
