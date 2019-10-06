package com.reportsystembyzabava.demo.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "chat")
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false, unique = true)
    private Long id;
    @Column(unique = true)
    private String name;


    @ManyToMany(mappedBy = "chatSet", fetch = FetchType.EAGER)
    private Set<File> fileSet;

    public Chat() {
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

    public Set<File> getFileSet() {
        return fileSet;
    }

    public void setFileSet(Set<File> fileSet) {
        this.fileSet = fileSet;
    }
}

