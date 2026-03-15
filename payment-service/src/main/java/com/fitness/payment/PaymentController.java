package com.fitness.payment;

import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {
    private final PaymentRepository repo;

    public PaymentController(PaymentRepository repo){
        this.repo=repo;
    }

    @PostMapping
    public Payment create(@RequestBody Payment e){
        return repo.save(e);
    }

    @GetMapping
    public List<Payment> all(){
        return repo.findAll();
    }
}