package com.syberry.server.controller;

import com.syberry.server.entity.Type;
import com.syberry.server.service.TypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/type")
public class TypeController {

    private final TypeService typeService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> addType(@RequestBody Type type) {
        typeService.addType(type);
        return ResponseEntity.ok("Type successfully saved");
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> getType(@PathVariable Long id) {
        return ResponseEntity.ok(typeService.getById(id));
    }

    @GetMapping("/")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> getTypes() {
        return ResponseEntity.ok(typeService.getTypes());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteType(@PathVariable Long id) {
        typeService.deleteById(id);
        return ResponseEntity.ok("Type successfully deleted");
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateType(@RequestBody Type newType, @PathVariable Long id) {
        return ResponseEntity.ok(typeService.updateType(newType, id));
    }
}
