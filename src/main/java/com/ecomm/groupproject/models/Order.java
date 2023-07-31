package com.ecomm.groupproject.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate orderDate;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate deliveryDate;

    private String notes;

    private double shippingFee=3.50;

    public Order() {
        this.orderDate = LocalDate.now();
        this.deliveryDate=LocalDate.now().plusDays(3);
    }
    @Column(updatable=false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createdAt;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date updatedAt;
    @PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="order_id")
    private User user;

    @Column(updatable=false)
    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
    private List<OrderItem> orderItem;
}
