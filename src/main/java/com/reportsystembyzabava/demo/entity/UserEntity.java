package com.reportsystembyzabava.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "UserTable")
public class UserEntity {
    @GeneratedValue
    @Id
    private Long id;
}
