
package com.fitness.notification;

import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {
private final NotificationRepository repo;

public NotificationController(NotificationRepository repo){this.repo=repo;}

@PostMapping
public Notification create(@RequestBody Notification e){return repo.save(e);}

@GetMapping
public List<Notification> all(){return repo.findAll();}
}
