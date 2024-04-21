package com.example.myapplicationforshit;

import java.util.ArrayList;
import java.util.List;

public class Person {
    public String name;
    public String job;
    public String age;

    public Person() {
        this.name="Dawn";
        this.job="bitch";
        this.age="22";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public Person(String name, String job, String age) {
        this.name = name;
        this.job = job;
        this.age = age;
    }

    public static List<Person> createPeople(Integer n){

        List<Person> ps=new ArrayList<>();
        for(int i=0;i<n;i++){
            ps.add(new Person());
        }
        return ps;
    }
}
