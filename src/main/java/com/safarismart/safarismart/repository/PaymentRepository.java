package com.safarismart.safarismart.repository;

import com.safarismart.safarismart.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findByUserId(Long userId);
    List<Payment> findByBookingId(Long bookingId);
    Optional<Payment> findByReceiptNumber(String receiptNumber);
    Optional<Payment> findByBookingIdAndStatus(Long bookingId, com.safarismart.safarismart.model.PaymentStatus status);
    boolean existsByBookingIdAndStatus(Long bookingId, com.safarismart.safarismart.model.PaymentStatus status);
}
