package com.safarismart.safarismart.config;

import com.safarismart.safarismart.model.*;
import com.safarismart.safarismart.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.security.crypto.password.PasswordEncoder;

@Component
public class DataInitializer implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final SafariPackageRepository packageRepository;
    private final UserRepository userRepository;
    private final BookingRepository bookingRepository;
    private final PaymentRepository paymentRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(CategoryRepository categoryRepository,
                           SafariPackageRepository packageRepository,
                           UserRepository userRepository,
                           BookingRepository bookingRepository,
                           PaymentRepository paymentRepository,
                           PasswordEncoder passwordEncoder) {
        this.categoryRepository = categoryRepository;
        this.packageRepository = packageRepository;
        this.userRepository = userRepository;
        this.bookingRepository = bookingRepository;
        this.paymentRepository = paymentRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        if (categoryRepository.count() > 0) return;

        Category national = categoryRepository.save(Category.builder()
                .name("National Parks")
                .description("Explore Tanzania's world-renowned national parks")
                .build());

        Category beach = categoryRepository.save(Category.builder()
                .name("Beach & Island")
                .description("Relax on pristine beaches and tropical islands")
                .build());

        Category cultural = categoryRepository.save(Category.builder()
                .name("Cultural Tours")
                .description("Experience rich local traditions and heritage")
                .build());

        Category adventure = categoryRepository.save(Category.builder()
                .name("Adventure")
                .description("Thrilling activities for the adventurous traveler")
                .build());

        Category luxury = categoryRepository.save(Category.builder()
                .name("Luxury")
                .description("Premium safari experiences with top-tier comfort")
                .build());

        SafariPackage serengeti = packageRepository.save(SafariPackage.builder()
                .name("Serengeti Great Migration")
                .description("Witness the breathtaking wildebeest migration across the vast Serengeti plains.")
                .destination("Serengeti National Park, Tanzania")
                .durationDays(5)
                .price(new BigDecimal("3500"))
                .maxCapacity(10)
                .imageUrl("https://images.unsplash.com/photo-1516426122078-c23e76319801?w=800")
                .category(national)
                .build());

        SafariPackage ngorongoro = packageRepository.save(SafariPackage.builder()
                .name("Ngorongoro Crater Expedition")
                .description("Descend into the world's largest intact volcanic caldera for incredible wildlife viewing.")
                .destination("Ngorongoro Conservation Area, Tanzania")
                .durationDays(3)
                .price(new BigDecimal("2800"))
                .maxCapacity(8)
                .imageUrl("https://images.unsplash.com/photo-1546182990-dffeafbe841d?w=800")
                .category(national)
                .build());

        packageRepository.save(SafariPackage.builder()
                .name("Tarangire National Park")
                .description("Famous for its large elephant herds and ancient baobab trees.")
                .destination("Tarangire National Park, Tanzania")
                .durationDays(3)
                .price(new BigDecimal("2200"))
                .maxCapacity(8)
                .imageUrl("https://images.unsplash.com/photo-1506905925346-21bda4d32df4?w=800")
                .category(national)
                .build());

        packageRepository.save(SafariPackage.builder()
                .name("Lake Manyara Safari")
                .description("Explore the scenic lake known for its tree-climbing lions and vast birdlife.")
                .destination("Lake Manyara National Park, Tanzania")
                .durationDays(3)
                .price(new BigDecimal("2000"))
                .maxCapacity(8)
                .imageUrl("https://images.unsplash.com/photo-1464822759023-fed622ff2c3b?w=800")
                .category(national)
                .build());

        packageRepository.save(SafariPackage.builder()
                .name("Gombe Stream Chimpanzee Trek")
                .description("Follow in Jane Goodall's footsteps and trek wild chimpanzees on Lake Tanganyika.")
                .destination("Gombe Stream National Park, Tanzania")
                .durationDays(4)
                .price(new BigDecimal("3200"))
                .maxCapacity(6)
                .imageUrl("https://images.unsplash.com/photo-1497752531616-c3afd9760a11?w=800")
                .category(national)
                .build());

        SafariPackage mahale = packageRepository.save(SafariPackage.builder()
                .name("Mahale Mountains Chimpanzee Safari")
                .description("Remote chimpanzee trekking in one of Africa's most pristine forests.")
                .destination("Mahale Mountains, Tanzania")
                .durationDays(5)
                .price(new BigDecimal("4000"))
                .maxCapacity(8)
                .imageUrl("https://images.unsplash.com/photo-1540573133985-87b6da6d54a9?w=800")
                .category(national)
                .build());

        packageRepository.save(SafariPackage.builder()
                .name("Rubondo Island Safari")
                .description("Tanzania's island national park with hippos, crocodiles, and rare fish eagles.")
                .destination("Lake Victoria, Tanzania")
                .durationDays(4)
                .price(new BigDecimal("2500"))
                .maxCapacity(8)
                .imageUrl("https://images.unsplash.com/photo-1439066615861-d1af74d74000?w=800")
                .category(national)
                .build());

        packageRepository.save(SafariPackage.builder()
                .name("Arusha National Park")
                .description("Discover Mount Meru, crater lakes, and diverse wildlife near Arusha.")
                .destination("Arusha National Park, Tanzania")
                .durationDays(2)
                .price(new BigDecimal("1500"))
                .maxCapacity(10)
                .imageUrl("https://images.unsplash.com/photo-1500382017468-9049fed747ef?w=800")
                .category(national)
                .build());

        SafariPackage zanzibar = packageRepository.save(SafariPackage.builder()
                .name("Zanzibar Beach Holiday")
                .description("Relax on the stunning white-sand beaches of Zanzibar's turquoise coast.")
                .destination("Zanzibar, Tanzania")
                .durationDays(5)
                .price(new BigDecimal("1800"))
                .maxCapacity(15)
                .imageUrl("https://images.unsplash.com/photo-1544551763-46a013bb70d5?w=800")
                .category(beach)
                .build());

        packageRepository.save(SafariPackage.builder()
                .name("Mafia Island Diving")
                .description("World-class diving and snorkeling in the Mafia Island Marine Park.")
                .destination("Mafia Island, Tanzania")
                .durationDays(5)
                .price(new BigDecimal("2500"))
                .maxCapacity(10)
                .imageUrl("https://images.unsplash.com/photo-1486870591958-9b9d0d1dda99?w=800")
                .category(beach)
                .build());

        packageRepository.save(SafariPackage.builder()
                .name("Pemba Island Escape")
                .description("Remote island paradise with pristine coral reefs and lush clove forests.")
                .destination("Pemba Island, Tanzania")
                .durationDays(6)
                .price(new BigDecimal("3000"))
                .maxCapacity(8)
                .imageUrl("https://images.unsplash.com/photo-1519501025264-65ba15a82390?w=800")
                .category(beach)
                .build());

        packageRepository.save(SafariPackage.builder()
                .name("Maasai Village Experience")
                .description("Immerse yourself in the rich traditions of the Maasai people.")
                .destination("Maasai Steppe, Tanzania")
                .durationDays(2)
                .price(new BigDecimal("1200"))
                .maxCapacity(12)
                .imageUrl("https://images.unsplash.com/photo-1564349683136-77e08dba1ef7?w=800")
                .category(cultural)
                .build());

        packageRepository.save(SafariPackage.builder()
                .name("Stone Town Heritage Tour")
                .description("Explore the historic heart of Zanzibar City, a UNESCO World Heritage Site.")
                .destination("Stone Town, Zanzibar")
                .durationDays(2)
                .price(new BigDecimal("800"))
                .maxCapacity(15)
                .imageUrl("https://images.unsplash.com/photo-1512453979798-5ea266f8880c?w=800")
                .category(cultural)
                .build());

        SafariPackage kilimanjaro = packageRepository.save(SafariPackage.builder()
                .name("Kilimanjaro Trekking")
                .description("Conquer Africa's highest peak on the iconic Machame Route.")
                .destination("Mount Kilimanjaro, Tanzania")
                .durationDays(7)
                .price(new BigDecimal("4500"))
                .maxCapacity(12)
                .imageUrl("https://images.unsplash.com/photo-1540979388789-6cee28a1cdc9?w=800")
                .category(adventure)
                .build());

        packageRepository.save(SafariPackage.builder()
                .name("Selous Game Reserve")
                .description("One of Africa's largest game reserves with boat safaris on the Rufiji River.")
                .destination("Selous Game Reserve, Tanzania")
                .durationDays(4)
                .price(new BigDecimal("3000"))
                .maxCapacity(8)
                .imageUrl("https://images.unsplash.com/photo-1448375240586-882707db888b?w=800")
                .category(adventure)
                .build());

        packageRepository.save(SafariPackage.builder()
                .name("Luxury Safari Lodge")
                .description("Ultimate luxury in the heart of the wilderness with premium amenities.")
                .destination("Various locations, Tanzania")
                .durationDays(7)
                .price(new BigDecimal("8000"))
                .maxCapacity(6)
                .imageUrl("https://images.unsplash.com/photo-1541427468627-a89a96e5ca1d?w=800")
                .category(luxury)
                .build());

        User admin = userRepository.save(User.builder()
                .name("Admin").email("admin@safarismart.com").phone("+255700000000")
                .password(passwordEncoder.encode("admin123")).role(UserRole.ADMIN).build());
        User user1 = userRepository.save(User.builder()
                .name("John Doe").email("john@example.com").phone("+255711000000")
                .password(passwordEncoder.encode("user123")).role(UserRole.USER).build());
        User user2 = userRepository.save(User.builder()
                .name("Jane Smith").email("jane@example.com").phone("+255722000000")
                .password(passwordEncoder.encode("user123")).role(UserRole.USER).build());

        Booking booking1 = bookingRepository.save(Booking.builder()
                .user(admin)
                .safariPackage(serengeti)
                .travelDate(LocalDate.of(2025, 6, 15))
                .numberOfPeople(2)
                .status(BookingStatus.CONFIRMED)
                .build());

        Booking booking2 = bookingRepository.save(Booking.builder()
                .user(user1)
                .safariPackage(ngorongoro)
                .travelDate(LocalDate.of(2025, 7, 10))
                .numberOfPeople(1)
                .status(BookingStatus.PENDING)
                .build());

        Booking booking3 = bookingRepository.save(Booking.builder()
                .user(user2)
                .safariPackage(zanzibar)
                .travelDate(LocalDate.of(2025, 8, 5))
                .numberOfPeople(3)
                .status(BookingStatus.CONFIRMED)
                .build());

        Booking booking4 = bookingRepository.save(Booking.builder()
                .user(admin)
                .safariPackage(kilimanjaro)
                .travelDate(LocalDate.of(2025, 9, 1))
                .numberOfPeople(4)
                .status(BookingStatus.CANCELLED)
                .build());

        Booking booking5 = bookingRepository.save(Booking.builder()
                .user(user1)
                .safariPackage(mahale)
                .travelDate(LocalDate.of(2025, 10, 12))
                .numberOfPeople(2)
                .status(BookingStatus.PENDING)
                .build());

        paymentRepository.save(Payment.builder()
                .booking(booking1)
                .user(admin)
                .amount(new BigDecimal("7000"))
                .paymentMethod("Credit Card")
                .status(PaymentStatus.VERIFIED)
                .receiptNumber("RCPT-" + System.currentTimeMillis())
                .build());

        paymentRepository.save(Payment.builder()
                .booking(booking3)
                .user(user2)
                .amount(new BigDecimal("5400"))
                .paymentMethod("Bank Transfer")
                .status(PaymentStatus.PENDING)
                .receiptNumber("RCPT-" + (System.currentTimeMillis() + 1))
                .build());

        System.out.println("Sample data initialized successfully!");
    }
}
