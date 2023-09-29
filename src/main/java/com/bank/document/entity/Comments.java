package com.bank.document.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
public class Comments {

    @Id
    Integer id;
    @Column(name = "name")
    String name;
    @Column(name = "email")
    String email;
    @Column(name = "body")
    String body;

}
