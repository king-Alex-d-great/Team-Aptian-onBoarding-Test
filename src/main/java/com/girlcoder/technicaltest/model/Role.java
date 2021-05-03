package com.girlcoder.technicaltest.model;


import lombok.Data;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    private String role;

    @OneToMany(fetch = FetchType.EAGER)
    private List<User> user;
}
