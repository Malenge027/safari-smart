package com.safarismart.safarismart.controller;

import com.safarismart.safarismart.model.*;
import com.safarismart.safarismart.service.BookingService;
import com.safarismart.safarismart.service.PaymentService;
import com.safarismart.safarismart.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;
    private final BookingService bookingService;
    private final UserService userService;

    @GetMapping
    public String listPayments(Model model) {
        model.addAttribute("payments", paymentService.getAllPayments());
        return "payments/list";
    }

    @GetMapping("/new/{bookingId}")
    public String showPaymentForm(@PathVariable Long bookingId, Model model) {
        Booking booking = bookingService.getBookingById(bookingId);
        model.addAttribute("booking", booking);
        model.addAttribute("payment", new Payment());
        return "payments/form";
    }

    @PostMapping("/save")
    public String savePayment(
            @RequestParam("bookingId") Long bookingId,
            @RequestParam("paymentMethod") String paymentMethod,
            @RequestParam("customerName") String customerName,
            @RequestParam("customerEmail") String customerEmail,
            @RequestParam("customerPhone") String customerPhone) {

        Booking booking = bookingService.getBookingById(bookingId);
        paymentService.createPayment(booking, paymentMethod);
        return "redirect:/payments/receipt/" + bookingId;
    }

    @GetMapping("/verify/{id}")
    public String verifyPayment(@PathVariable Long id, HttpSession session) {
        User currentUser = (User) session.getAttribute("currentUser");
        String verifiedBy = currentUser != null ? currentUser.getName() : "Admin";
        paymentService.verifyPayment(id, verifiedBy);
        return "redirect:/payments";
    }

    @GetMapping("/reject/{id}")
    public String rejectPayment(@PathVariable Long id) {
        paymentService.rejectPayment(id);
        return "redirect:/payments";
    }

    @GetMapping("/receipt/{bookingId}")
    public String viewReceipt(@PathVariable Long bookingId, Model model) {
        Booking booking = bookingService.getBookingById(bookingId);
        model.addAttribute("booking", booking);
        model.addAttribute("payments", paymentService.getPaymentsByBooking(bookingId));
        return "payments/receipt";
    }

    @GetMapping("/dashboard")
    public String paymentDashboard(Model model) {
        model.addAttribute("payments", paymentService.getAllPayments());
        model.addAttribute("totalPayments", paymentService.getAllPayments().size());
        model.addAttribute("pendingPayments", paymentService.countPendingPayments());
        model.addAttribute("verifiedPayments", paymentService.countVerifiedPayments());
        return "payments/dashboard";
    }
}
