package com.reportSystemByZabavaApplication.demo.servise;

import com.reportSystemByZabavaApplication.demo.entity.User;
import com.reportSystemByZabavaApplication.demo.entity.userExtraData.Confirmation;
import com.reportSystemByZabavaApplication.demo.servise.codeGenerator.CodeGenerator;

import java.util.Date;

/**
 * Created by Thealeshka on 19.10.2019 inside the package - com.reportSystemByZabavaApplication.demo.servise
 */


public class SetUserConfirmation {
    public static User setUserConfirmation(User user) {
        return user.setConfirm(new Confirmation().setCode(CodeGenerator.generateCodeStr(4)).setSuccess(false)
                .setSuccess(false).setDataSentEMail(new Date()));


    }
}
