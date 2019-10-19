package com.reportSystemByZabavaApplication.demo.servise.jsonClasses;

/**
 * Created by Thealeshka on 09.10.2019 inside the package - com.reportSystemByZabavaApplication.demo.servise.jsonClasses
 */


public class JSONBuilder {
    private StringBuilder stringBuilder;

    public static JSONBuilder create() {
        return new JSONBuilder();
    }

    {
        stringBuilder = new StringBuilder("{\n");

    }

    public JSONBuilder() {
    }

    public StringBuilder getStringBuilder() {
        return stringBuilder;
    }

    public JSONBuilder setStringBuilder(StringBuilder stringBuilder) {
        this.stringBuilder = stringBuilder;
        return this;
    }

    public JSONBuilder add(String key, String value) {
        if (stringBuilder.toString().contains("\"")) {
            stringBuilder.append(",\n");
        }
        stringBuilder.append("\"" + key + "\": \"" + value + "\"");
        return this;
    }

    public String get() {
        stringBuilder.append("\n}");
        return this.getStringBuilder().toString();
    }


}
