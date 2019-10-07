package com.reportSystemByZabavaApplication.demo.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "file")
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nameForUsers;

    @Column(unique = true)
    private String checkSum;
    private Long size;
    @Column(name = "file_bytes")
    private byte[] file;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Chat> chatSet;

    public File() {
    }

    public File(String checkSum) {
        this.checkSum = checkSum;
    }


    public Long getId() {
        return id;
    }

    public File setId(Long id) {
        this.id = id;
        return this;
    }

    public String getNameForUsers() {
        return nameForUsers;
    }

    public File setNameForUsers(String nameForUsers) {
        this.nameForUsers = nameForUsers;
        return this;
    }


    public Long getSize() {
        return size;
    }

    public String getCheckSum() {
        return checkSum;
    }

    public File setCheckSum(String checkSum) {
        this.checkSum = checkSum;
        return this;
    }

    public File setSize(Long size) {
        this.size = size;
        return this;
    }


    public byte[] getFile() {
        return file;
    }

    public File setFile(byte[] file) {
        this.file = file;
        return this;
    }

    public Set<Chat> getChatSet() {
        return chatSet;
    }

    public File setChatSet(Set<Chat> chatSet) {
        this.chatSet = chatSet;
        return this;
    }
}
