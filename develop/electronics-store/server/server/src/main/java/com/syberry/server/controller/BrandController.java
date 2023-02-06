package com.syberry.server.controller;

import com.syberry.server.entity.Brand;
import com.syberry.server.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/brand")
public class BrandController {
    private final BrandService brandService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> addBrand(@RequestBody Brand brand) {
        brandService.addBrand(brand);
        return ResponseEntity.ok("Brand successfully saved");
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> getBrand(@PathVariable Long id) {
        return ResponseEntity.ok(brandService.getById(id));
    }

    @GetMapping("/")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> getBrands() {
        return ResponseEntity.ok(brandService.getBrands());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteBrand(@PathVariable Long id) {
        brandService.deleteById(id);
        return ResponseEntity.ok("Brand successfully deleted");
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateBrand(@RequestBody Brand newBrand, @PathVariable Long id) {
        return ResponseEntity.ok(brandService.updateBrand(newBrand, id));
    }

}
