package com.reportSystemByZabavaApplication.demo.entity;

import javax.persistence.*;

@Entity
@Table(name = "mails")
public class Mail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String address;
    private String topic;
    private String body;

    public Mail(String address, String topic, String body) {
        this.address = address;
        this.topic = topic;
        this.body = body;
    }

    public Mail() {
    }

    public Long getId() {
        return id;
    }

    public Mail setId(Long id) {
        this.id = id;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public Mail setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getTopic() {
        return topic;
    }

    public Mail setTopic(String topic) {
        this.topic = topic;
        return this;
    }

    public String getBody() {
        return body;
    }

    public Mail setBody(String body) {
        this.body = body;
        return this;
    }
}
