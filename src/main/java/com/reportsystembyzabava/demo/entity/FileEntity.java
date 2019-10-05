package com.reportsystembyzabava.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class FileEntity {
    @GeneratedValue
    @Id
    private Long id;
    private String nameForUsers;

    @Column(unique = true)
    private String checkSum;
    private Long size;
    private byte[] file;

    public FileEntity() {
    }

    public FileEntity(String checkSum) {
        this.checkSum = checkSum;
    }


    public Long getId() {
        return id;
    }

    public FileEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getNameForUsers() {
        return nameForUsers;
    }

    public FileEntity setNameForUsers(String nameForUsers) {
        this.nameForUsers = nameForUsers;
        return this;
    }


    public Long getSize() {
        return size;
    }

    public String getCheckSum() {
        return checkSum;
    }

    public FileEntity setCheckSum(String checkSum) {
        this.checkSum = checkSum;
        return this;
    }

    public FileEntity setSize(Long size) {
        this.size = size;
        return this;
    }


    public byte[] getFile() {
        return file;
    }

    public FileEntity setFile(byte[] file) {
        this.file = file;
        return this;
    }
}
