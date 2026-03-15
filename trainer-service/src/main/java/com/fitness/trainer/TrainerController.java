
package com.fitness.trainer;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/trainers")
public class TrainerController {
    private final TrainerRepository repo;

    public TrainerController(TrainerRepository repo){
        this.repo = repo;
    }

    // CREATE TRAINER
    @PostMapping
    public Trainer create(@Valid @RequestBody Trainer trainer){
        return repo.save(trainer);
    }

    // GET ALL TRAINERS
    @GetMapping
    public List<Trainer> all(){
        return repo.findAll();
    }

    // GET TRAINER BY ID
    @GetMapping("/{id}")
    public Trainer getById(@PathVariable Long id){
        return repo.findById(id).orElseThrow();
    }

    // UPDATE TRAINER
    @PutMapping("/{id}")
    public Trainer update(@PathVariable Long id, @RequestBody Trainer trainer){

        Trainer existing = repo.findById(id).orElseThrow();

        existing.setName(trainer.getName());

        return repo.save(existing);
    }

    // DELETE TRAINER
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        repo.deleteById(id);
    }
}
