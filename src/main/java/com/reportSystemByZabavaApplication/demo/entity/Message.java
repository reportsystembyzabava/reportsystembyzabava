/*
package com.reportSystemByZabavaApplication.demo.entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "messages")
public class Message {
    @Id
    @GeneratedValue
    private Long id;


    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "users_table_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User author;



    public Message() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return author;
    }

    public void setUser(User user) {
        this.author = user;
    }
}
*/
