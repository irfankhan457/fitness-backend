package com.fitness.membership;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/memberships")
@CrossOrigin
public class MembershipController {

    private final MembershipService service;

    public MembershipController(MembershipService service){
        this.service = service;
    }

    @PostMapping
    public Membership create(@RequestBody Membership m){
        return service.create(m);
    }

    @GetMapping
    public List<Membership> getAll(){
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Membership getById(@PathVariable Long id){
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public Membership update(@PathVariable Long id,
                             @RequestBody Membership m){
        return service.update(id,m);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        service.delete(id);
    }
}