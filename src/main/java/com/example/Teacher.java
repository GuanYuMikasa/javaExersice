package com.example;

public class Teacher {
    public String name;

    public String position;
    public String rsAreas;
    public String school;
    public Teacher(){
        name="";
        rsAreas="";
        school="";
        position="";
    }
    public Teacher(String name,String position,String rsAreas,String school){

        this.name=name;
        this.position=position;
        this.rsAreas=rsAreas;
        this.school=school;
    }
}
