package com.safarismart.safarismart.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "bookings")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "safari_package_id", nullable = false)
    private SafariPackage safariPackage;

    @NotNull(message = "Travel date is required")
    private LocalDate travelDate;

    @Min(value = 1, message = "At least 1 person required")
    private int numberOfPeople;

    private BigDecimal totalAmount;

    @Enumerated(EnumType.STRING)
    private BookingStatus status;

    private LocalDateTime createdAt;

    @Transient
    private boolean paid;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        status = BookingStatus.PENDING;
        if (safariPackage != null && numberOfPeople > 0) {
            totalAmount = safariPackage.getPrice().multiply(BigDecimal.valueOf(numberOfPeople));
        }
    }
}
