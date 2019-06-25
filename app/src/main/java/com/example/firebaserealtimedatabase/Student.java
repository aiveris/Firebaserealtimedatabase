package com.example.firebaserealtimedatabase;

public class Student {
    private String id, name;
    private int marks;

    public Student(){}//always needed

    public Student(String id, String name, int marks) {
        this.id = id;
        this.name = name;
        this.marks = marks;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getMarks() {
        return marks;
    }
}
