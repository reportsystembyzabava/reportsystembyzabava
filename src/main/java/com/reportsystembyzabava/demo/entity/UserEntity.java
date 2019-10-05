package com.reportsystembyzabava.demo.entity;

import javax.persistence.*;
import java.io.File;

@Entity
@Table(name = "UserTable")
public class UserEntity {
    @GeneratedValue
    @Id
    private Long id;

    @Column(length = 130000000)
    private byte[] file;

    public UserEntity() {
    }

    public UserEntity(byte[] file) {
        this.file = file;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }
}
