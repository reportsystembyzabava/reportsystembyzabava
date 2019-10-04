package com.reportsystembyzabava.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ChatEntity {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    public ChatEntity(String name) {
        this.name = name;
    }

    public ChatEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
