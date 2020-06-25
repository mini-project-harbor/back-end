package com.enigma.pascal.api.models;

import javax.persistence.*;

@Entity
@Table(name = "master_status")
public class StatusModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    public StatusModel(Integer id) {
        this.id = id;
    }

    public StatusModel() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
