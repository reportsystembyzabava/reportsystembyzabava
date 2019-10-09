package com.reportSystemByZabavaApplication.demo.servise.jsonClasses;

public class mailJSON{
        private String code;
        private String userToken;

        public mailJSON(String code, String userToken) {
            this.code = code;
            this.userToken = userToken;
        }

        public mailJSON() {
        }

        public String getCode() {
            return code;
        }

        public mailJSON setCode(String code) {
            this.code = code;
            return this;
        }

        public String getUserToken() {
            return userToken;
        }

        public mailJSON setUserToken(String userToken) {
            this.userToken = userToken;
            return this;
        }
    }