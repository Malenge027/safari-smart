package com.safarismart.safarismart.controller;

import com.safarismart.safarismart.model.User;
import com.safarismart.safarismart.model.UserRole;
import com.safarismart.safarismart.service.BookingService;
import com.safarismart.safarismart.service.CategoryService;
import com.safarismart.safarismart.service.PaymentService;
import com.safarismart.safarismart.service.SafariPackageService;
import com.safarismart.safarismart.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {

    private final SafariPackageService packageService;
    private final CategoryService categoryService;
    private final BookingService bookingService;
    private final PaymentService paymentService;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public HomeController(SafariPackageService packageService,
                          CategoryService categoryService,
                          BookingService bookingService,
                          PaymentService paymentService,
                          UserService userService,
                          PasswordEncoder passwordEncoder) {
        this.packageService = packageService;
        this.categoryService = categoryService;
        this.bookingService = bookingService;
        this.paymentService = paymentService;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/")
    public String home(HttpSession session, Model model) {
        User user = (User) session.getAttribute("currentUser");
        if (user == null) {
            model.addAttribute("packages", packageService.getAllPackages());
            model.addAttribute("categories", categoryService.getAllCategories());
            model.addAttribute("packageCount", packageService.getAllPackages().size());
            model.addAttribute("bookingCount", bookingService.getAllBookings().size());
            return "index";
        }
        if (user.getRole() == UserRole.ADMIN) {
            return "redirect:/admin/dashboard";
        }
        return "redirect:/user/dashboard";
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password, HttpSession session, Model model) {
        User user = userService.findByEmail(email).orElse(null);
        if (user == null || !passwordEncoder.matches(password, user.getPassword())) {
            model.addAttribute("error", "Invalid email or password");
            return "login";
        }
        session.setAttribute("currentUser", user);
        if (user.getRole() == UserRole.ADMIN) {
            return "redirect:/admin/dashboard";
        }
        return "redirect:/user/dashboard";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

    @GetMapping("/signup")
    public String showSignUpPage() {
        return "signup";
    }

    @PostMapping("/signup")
    public String signUp(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String phone,
            @RequestParam String password,
            HttpSession session, Model model) {
        if (userService.existsByEmail(email)) {
            model.addAttribute("error", "An account with this email already exists");
            return "signup";
        }
        User user = User.builder()
                .name(name)
                .email(email)
                .phone(phone)
                .password(passwordEncoder.encode(password))
                .role(UserRole.USER)
                .build();
        User saved = userService.saveUser(user);
        session.setAttribute("currentUser", saved);
        return "redirect:/user/dashboard";
    }

    @GetMapping("/admin/dashboard")
    public String adminDashboard(HttpSession session, Model model) {
        User user = (User) session.getAttribute("currentUser");
        if (user == null || user.getRole() != UserRole.ADMIN) {
            return "redirect:/login";
        }
        model.addAttribute("currentUser", user);
        model.addAttribute("packages", packageService.getAllPackages());
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("bookingCount", bookingService.getAllBookings().size());
        model.addAttribute("packageCount", packageService.getAllPackages().size());
        model.addAttribute("userCount", userService.getAllUsers().size());
        model.addAttribute("bookings", bookingService.getAllBookings());
        model.addAttribute("pendingPayments", paymentService.countPendingPayments());
        model.addAttribute("verifiedPayments", paymentService.countVerifiedPayments());
        model.addAttribute("totalPayments", paymentService.getAllPayments().size());
        return "admin/dashboard";
    }

    @GetMapping("/user/dashboard")
    public String userDashboard(HttpSession session, Model model) {
        User user = (User) session.getAttribute("currentUser");
        if (user == null) {
            return "redirect:/login";
        }
        model.addAttribute("currentUser", user);
        model.addAttribute("packages", packageService.getAllPackages());
        model.addAttribute("categories", categoryService.getAllCategories());
        return "user/dashboard";
    }
}
