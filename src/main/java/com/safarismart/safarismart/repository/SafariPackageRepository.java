package com.safarismart.safarismart.repository;

import com.safarismart.safarismart.model.SafariPackage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SafariPackageRepository extends JpaRepository<SafariPackage, Long> {
    List<SafariPackage> findByCategoryId(Long categoryId);
    List<SafariPackage> findByNameContainingIgnoreCase(String name);
}
