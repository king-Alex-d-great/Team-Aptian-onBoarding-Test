package com.girlcoder.technicaltest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String accountName;
    private Double accountBalance = 0.0;
    private String accountNumber;

    @JsonIgnore
    @OneToMany
    private List<Transaction> Transactions;

    @JsonIgnore
    @OneToOne
    private User accountHolder;

}
