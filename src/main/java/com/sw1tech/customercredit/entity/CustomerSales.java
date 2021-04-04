package com.sw1tech.customercredit.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class CustomerSales {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double price;
    private String description;
    //private int customerid;

    @ManyToOne()
    @JoinColumn(name = "customerId", referencedColumnName = "id")
    private Customer customer;

}
