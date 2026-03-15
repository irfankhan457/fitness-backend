
package com.fitness.user;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

private final UserRepository repo;

public UserController(UserRepository repo){this.repo=repo;}

@PostMapping("/register")
public Users register(@RequestBody Users u){
    return repo.save(u);
}

@GetMapping
public java.util.List<Users> all(){
    return repo.findAll();
}
}
