package com.reportSystemByZabavaApplication.demo.entity.userExtraData;

import javax.persistence.*;

/**
 * Created by Thealeshka on 09.10.2019 inside the package - com.reportSystemByZabavaApplication.demo.entity.userExtraData
 */

@Entity
@Table(schema = "public")
public class Confirmation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "confirm_id")
    private Long id;
    private boolean success;
    private String code;
    private String dataSentEMail;


    public Confirmation() {
    }

    public Long getId() {
        return id;
    }

    public Confirmation setId(Long id) {
        this.id = id;
        return this;
    }

    public boolean isSuccess() {
        return success;
    }

    public Confirmation setSuccess(boolean success) {
        this.success = success;
        return this;
    }

    public String getCode() {
        return code;
    }

    public Confirmation setCode(String code) {
        this.code = code;
        return this;
    }

    public String getDataSentEMail() {
        return dataSentEMail;
    }

    public Confirmation setDataSentEMail(String dataSentEMail) {
        this.dataSentEMail = dataSentEMail;
        return this;
    }

}
