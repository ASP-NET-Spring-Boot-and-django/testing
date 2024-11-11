package com.example.demo6.model;

import jakarta.persistence.*;

@Entity
public class Chef {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;
    @Column(nullable = false,unique = true)
    String chefName;
    @Column(nullable = false)
    String chefPassword;
    public String getChefPassword() {
        return chefPassword;
    }

    public void setChefPassword(String chefPassword) {
        this.chefPassword = chefPassword;
    }

    public String getChefName() {
        return chefName;
    }

    public void setChefName(String chefName) {
        this.chefName = chefName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


}
