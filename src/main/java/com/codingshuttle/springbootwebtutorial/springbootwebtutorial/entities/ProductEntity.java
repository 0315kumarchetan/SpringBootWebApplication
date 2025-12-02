package com.codingshuttle.springbootwebtutorial.springbootwebtutorial.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@ToString
@Table(
        name = "product",
        uniqueConstraints = {
                @UniqueConstraint(name = "title_price_unique",columnNames = {"title_x","price"})
        },
        indexes = {
                @Index(name = "sku_index",columnList = "sku")
        }

)
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,length = 26)
    private String sku;

    @Column(name = "title_x",nullable = false)
    private String title;

    private BigDecimal price;

    private Integer quantity;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
