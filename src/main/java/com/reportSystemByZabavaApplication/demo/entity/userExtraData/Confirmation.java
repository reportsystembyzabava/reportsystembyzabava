package com.reportSystemByZabavaApplication.demo.entity.userExtraData;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

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
    private Date dataSentEMail;


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

    public Date getDataSentEMail() {
        return dataSentEMail;
    }

    public Confirmation setDataSentEMail(Date dataSentEMail) {
        this.dataSentEMail = dataSentEMail;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Confirmation that = (Confirmation) o;
        return success == that.success &&
                Objects.equals(id, that.id) &&
                Objects.equals(code, that.code) &&
                Objects.equals(dataSentEMail, that.dataSentEMail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, success, code, dataSentEMail);
    }

    @Override
    public String toString() {
        if(dataSentEMail==null){
            dataSentEMail=new Date();
        }
        return "Confirmation{" +
                "id=" + id +
                ", success=" + success +
                ", code='" + code + '\'' +
                ", dataSentEMail=" + dataSentEMail.toString() +
                '}';
    }
}
