
package com.fitness.payment;

import jakarta.persistence.*;

@Entity
public class Payment {
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
private Long id;

private String name;

public Long getId(){return id;}
public void setId(Long id){this.id=id;}
public String getName(){return name;}
public void setName(String name){this.name=name;}
}
