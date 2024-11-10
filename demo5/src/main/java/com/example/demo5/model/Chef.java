package com.example.demo5.model;

import jakarta.persistence.*;

@Entity
public class Chef {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;
    @Column(unique = true, nullable = false)
    String chefname;
    @Column(nullable = false)
    String chefpassword;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getChefname() {
        return chefname;
    }

    public void setChefname(String chefname) {
        this.chefname = chefname;
    }

    public String getChefpassword() {
        return chefpassword;
    }

    public void setChefpassword(String chefpassword) {
        this.chefpassword = chefpassword;
    }
}
