package com.safarismart.safarismart.controller;

import com.safarismart.safarismart.model.SafariPackage;
import com.safarismart.safarismart.service.CategoryService;
import com.safarismart.safarismart.service.SafariPackageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/packages")
@RequiredArgsConstructor
public class SafariPackageController {

    private final SafariPackageService packageService;
    private final CategoryService categoryService;

    @GetMapping
    public String listPackages(Model model) {
        model.addAttribute("packages", packageService.getAllPackages());
        return "packages/list";
    }

    @GetMapping("/{id}")
    public String viewPackage(@PathVariable Long id, Model model) {
        model.addAttribute("safariPackage", packageService.getPackageById(id));
        return "packages/view";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("safariPackage", new SafariPackage());
        model.addAttribute("categories", categoryService.getAllCategories());
        return "packages/form";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("safariPackage", packageService.getPackageById(id));
        model.addAttribute("categories", categoryService.getAllCategories());
        return "packages/form";
    }

    @PostMapping("/save")
    public String savePackage(@ModelAttribute SafariPackage safariPackage, BindingResult result) {
        if (result.hasErrors()) {
            return "packages/form";
        }
        packageService.savePackage(safariPackage);
        return "redirect:/packages";
    }

    @GetMapping("/{id}/delete")
    public String deletePackage(@PathVariable Long id) {
        packageService.deletePackage(id);
        return "redirect:/packages";
    }
}
