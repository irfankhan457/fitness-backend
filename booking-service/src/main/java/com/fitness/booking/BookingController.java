
package com.fitness.booking;

import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {
private final BookingRepository repo;

public BookingController(BookingRepository repo){this.repo=repo;}

@PostMapping
public Booking create(@RequestBody Booking e){return repo.save(e);}

@GetMapping
public List<Booking> all(){return repo.findAll();}
}
