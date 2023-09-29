package com.bank.document.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
public class Post {

    @Id
    Integer Id;

    @Column(name = "userId")
    String userId;

    @Column(name = "title")
    String title;

    @Column(name = "body")
    String body;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Comments> comments;
}
