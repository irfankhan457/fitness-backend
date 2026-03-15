package com.fitness.membership;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MembershipService {

    private final MembershipRepository repo;

    public MembershipService(MembershipRepository repo) {
        this.repo = repo;
    }

    public Membership create(Membership m){
        return repo.save(m);
    }

    public List<Membership> getAll(){
        return repo.findAll();
    }

    public Membership getById(Long id){
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Membership not found"));
    }

    public Membership update(Long id, Membership updated){
        Membership m = getById(id);

        m.setName(updated.getName());
        m.setPrice(updated.getPrice());
        m.setDurationInMonths(updated.getDurationInMonths());
        m.setDescription(updated.getDescription());

        return repo.save(m);
    }

    public void delete(Long id){
        repo.deleteById(id);
    }
}