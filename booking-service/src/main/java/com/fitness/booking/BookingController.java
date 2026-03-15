package com.fitness.booking;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/bookings")
@CrossOrigin(origins = "*")
public class BookingController {
    private final BookingRepository repo;

    public BookingController(BookingRepository repo) {
        this.repo = repo;
    }

    @PostMapping
    public ResponseEntity<Booking> create(@RequestBody Booking booking) {
        try {
            // Set default status if not provided
            if (booking.getStatus() == null || booking.getStatus().isEmpty()) {
                booking.setStatus("confirmed");
            }

            Booking saved = repo.save(booking);
            return ResponseEntity.status(HttpStatus.CREATED).body(saved);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Booking>> all() {
        try {
            List<Booking> bookings = repo.findAll();
            return ResponseEntity.ok(bookings);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Booking> getById(@PathVariable Long id) {
        return repo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Booking>> getByUserId(@PathVariable Long userId) {
        List<Booking> bookings = repo.findByUserId(userId);
        return ResponseEntity.ok(bookings);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Booking> update(@PathVariable Long id, @RequestBody Booking booking) {
        return repo.findById(id)
                .map(existing -> {
                    existing.setTrainerId(booking.getTrainerId());
                    existing.setSessionDate(booking.getSessionDate());
                    existing.setSessionTime(booking.getSessionTime());
                    existing.setDuration(booking.getDuration());
                    existing.setStatus(booking.getStatus());
                    Booking updated = repo.save(existing);
                    return ResponseEntity.ok(updated);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}