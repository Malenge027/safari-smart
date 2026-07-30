package com.safarismart.safarismart.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "safari_packages")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SafariPackage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Package name is required")
    private String name;

    private String description;

    @NotBlank(message = "Destination is required")
    private String destination;

    @Min(value = 1, message = "Duration must be at least 1 day")
    private int durationDays;

    @NotNull(message = "Price is required")
    @Min(value = 0)
    private BigDecimal price;

    @Min(value = 1, message = "Capacity must be at least 1")
    private int maxCapacity;

    private String imageUrl;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;
}
