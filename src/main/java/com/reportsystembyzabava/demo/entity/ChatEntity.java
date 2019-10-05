package com.reportsystembyzabava.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.ArrayList;

@Entity
public class ChatEntity {
    @Id
    @GeneratedValue
    @Column(name = "ID", nullable = false, unique = true)
    private Long id;
    @Column(unique = true)
    private String name;

    @Column(name = "Files")
    //@OneToMany
    private ArrayList<FileEntity> fileEntityList;

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

    public ArrayList<FileEntity> getFileEntityList() {
        return fileEntityList;
    }

    public void setFileEntityList(ArrayList<FileEntity> fileEntityList) {
        this.fileEntityList = fileEntityList;
    }
}

