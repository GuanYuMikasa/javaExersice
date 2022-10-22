package com.example;

import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MatchRsArea {
    private Vector<Teacher> teachers;
    public MatchRsArea(Vector<Teacher> teachers){
        this.teachers=teachers;
    }
    public void match(String str){
        String path="D:/Desktop/data/";
        String pattern = str;
        Pattern r = Pattern.compile(pattern);
        Matcher m;
        Vector<Teacher> matchTeachers=new Vector<>();
        for(Teacher teacher: teachers) {
            m=r.matcher(teacher.rsAreas);
            if(m.find()){
                System.out.println(teacher.name);
                System.out.println(teacher.rsAreas);
                matchTeachers.add(teacher);
            }
        }
        try {
            FileOperate.excelWrite(path,str,matchTeachers);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
