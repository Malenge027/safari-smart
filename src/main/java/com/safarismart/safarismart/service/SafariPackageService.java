package com.safarismart.safarismart.service;

import com.safarismart.safarismart.model.SafariPackage;
import com.safarismart.safarismart.repository.SafariPackageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SafariPackageService {

    private final SafariPackageRepository packageRepository;

    public List<SafariPackage> getAllPackages() {
        return packageRepository.findAll();
    }

    public SafariPackage getPackageById(Long id) {
        return packageRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Safari package not found with id: " + id));
    }

    public SafariPackage savePackage(SafariPackage safariPackage) {
        return packageRepository.save(safariPackage);
    }

    public void deletePackage(Long id) {
        packageRepository.deleteById(id);
    }

    public List<SafariPackage> getPackagesByCategory(Long categoryId) {
        return packageRepository.findByCategoryId(categoryId);
    }

    public List<SafariPackage> searchPackages(String keyword) {
        return packageRepository.findByNameContainingIgnoreCase(keyword);
    }
}
