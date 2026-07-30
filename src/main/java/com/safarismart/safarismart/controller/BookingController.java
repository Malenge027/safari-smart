package com.safarismart.safarismart.controller;

import com.safarismart.safarismart.model.Booking;
import com.safarismart.safarismart.model.BookingStatus;
import com.safarismart.safarismart.model.User;
import com.safarismart.safarismart.service.BookingService;
import com.safarismart.safarismart.service.PaymentService;
import com.safarismart.safarismart.service.SafariPackageService;
import com.safarismart.safarismart.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;
    private final UserService userService;
    private final SafariPackageService packageService;
    private final PaymentService paymentService;

    @GetMapping
    public String listBookings(Model model) {
        List<Booking> bookings = bookingService.getAllBookings();
        bookings.forEach(b -> b.setPaid(paymentService.isBookingPaid(b.getId())));
        model.addAttribute("bookings", bookings);
        return "bookings/list";
    }

    @GetMapping("/{id}")
    public String viewBooking(@PathVariable Long id, Model model) {
        Booking booking = bookingService.getBookingById(id);
        booking.setPaid(paymentService.isBookingPaid(id));
        model.addAttribute("booking", booking);
        return "bookings/view";
    }

    @GetMapping("/new")
    public String showCreateForm(@RequestParam(value = "packageId", required = false) Long packageId, Model model) {
        model.addAttribute("booking", new Booking());
        model.addAttribute("packages", packageService.getAllPackages());
        model.addAttribute("selectedPackageId", packageId);
        return "bookings/form";
    }

    @PostMapping("/save")
    public String saveBooking(
            @RequestParam("customerName") String customerName,
            @RequestParam("customerEmail") String customerEmail,
            @RequestParam("customerPhone") String customerPhone,
            @RequestParam("safariPackageId") Long safariPackageId,
            @RequestParam("travelDate") String travelDate,
            @RequestParam("numberOfPeople") int numberOfPeople) {

        User user = userService.findOrCreateUser(customerName, customerEmail, customerPhone);

        Booking booking = new Booking();
        booking.setUser(user);
        booking.setSafariPackage(packageService.getPackageById(safariPackageId));
        booking.setTravelDate(LocalDate.parse(travelDate));
        booking.setNumberOfPeople(numberOfPeople);

        Booking saved = bookingService.saveBooking(booking);
        return "redirect:/payments/new/" + saved.getId();
    }

    @GetMapping("/{id}/status/{status}")
    public String updateStatus(@PathVariable Long id, @PathVariable BookingStatus status) {
        bookingService.updateBookingStatus(id, status);
        return "redirect:/bookings";
    }

    @GetMapping("/{id}/delete")
    public String deleteBooking(@PathVariable Long id) {
        bookingService.deleteBooking(id);
        return "redirect:/bookings";
    }
}
