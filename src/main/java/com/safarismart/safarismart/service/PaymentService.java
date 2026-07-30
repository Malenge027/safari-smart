package com.safarismart.safarismart.service;

import com.safarismart.safarismart.model.Booking;
import com.safarismart.safarismart.model.BookingStatus;
import com.safarismart.safarismart.model.Payment;
import com.safarismart.safarismart.model.PaymentStatus;
import com.safarismart.safarismart.repository.BookingRepository;
import com.safarismart.safarismart.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final BookingRepository bookingRepository;

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    public Payment getPaymentById(Long id) {
        return paymentRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Payment not found with id: " + id));
    }

    public Payment savePayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    public void deletePayment(Long id) {
        paymentRepository.deleteById(id);
    }

    public List<Payment> getPaymentsByUser(Long userId) {
        return paymentRepository.findByUserId(userId);
    }

    public List<Payment> getPaymentsByBooking(Long bookingId) {
        return paymentRepository.findByBookingId(bookingId);
    }

    public boolean isBookingPaid(Long bookingId) {
        return paymentRepository.existsByBookingIdAndStatus(bookingId, PaymentStatus.VERIFIED);
    }

    public Payment createPayment(Booking booking, String paymentMethod) {
        String receiptNumber = "RCP-" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"))
                + "-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();

        Payment payment = Payment.builder()
                .user(booking.getUser())
                .booking(booking)
                .amount(booking.getTotalAmount())
                .paymentMethod(paymentMethod)
                .receiptNumber(receiptNumber)
                .status(PaymentStatus.PENDING)
                .build();

        return paymentRepository.save(payment);
    }

    public Payment verifyPayment(Long paymentId, String verifiedBy) {
        Payment payment = getPaymentById(paymentId);
        payment.setStatus(PaymentStatus.VERIFIED);
        payment.setVerifiedAt(LocalDateTime.now());
        payment.setVerifiedBy(verifiedBy);

        Booking booking = payment.getBooking();
        booking.setStatus(BookingStatus.CONFIRMED);
        bookingRepository.save(booking);

        return paymentRepository.save(payment);
    }

    public Payment rejectPayment(Long paymentId) {
        Payment payment = getPaymentById(paymentId);
        payment.setStatus(PaymentStatus.REJECTED);
        return paymentRepository.save(payment);
    }

    public long countPendingPayments() {
        return paymentRepository.findAll().stream()
                .filter(p -> p.getStatus() == PaymentStatus.PENDING)
                .count();
    }

    public long countVerifiedPayments() {
        return paymentRepository.findAll().stream()
                .filter(p -> p.getStatus() == PaymentStatus.VERIFIED)
                .count();
    }
}
