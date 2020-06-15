package com.ritier.crud_tutoring.Models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Teammate extends RealmObject {

    @PrimaryKey
    private int id;
    private String name;
    private String backNumber;
    private String leftRight;
    private String age;

    public Teammate() {

    }

    public Teammate(int id, String name, String backNumber, String leftRight, String age) {
        this.id = id;
        this.name = name;
        this.backNumber = backNumber;
        this.leftRight = leftRight;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBackNumber() {
        return backNumber;
    }

    public void setBackNumber(String backNumber) {
        this.backNumber = backNumber;
    }

    public String getLeftRight() {
        return leftRight;
    }

    public void setLeftRight(String leftRight) {
        this.leftRight = leftRight;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
