package com.infosys.licensecreation.model;

public class Constraint {
    private String name;
    private String value;
    private String operator;
    private String errorMessage;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return "Constraint{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                ", operator='" + operator + '\'' +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }
}
