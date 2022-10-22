package com.example;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

//解析页面数据线程类
public class ParseHtmlThread implements Runnable{
    Vector<Teacher> teachers;
    Map<String,Object> pages;
    //构造函数获取数据
    public ParseHtmlThread(Vector<Teacher> teachers,Map<String,Object> pages){
        this.teachers=teachers;
        this.pages=pages;
    }

    @Override
    public void run() {
        Set urlset=pages.keySet();
        Iterator it =urlset.iterator();
        while(it.hasNext()){
            String url=it.next().toString();
            String html="";
            if(pages.get(url) instanceof Page){
                Page page=(Page)pages.get(url);
                if(page.getParseData() instanceof HtmlParseData){
                    HtmlParseData htmlParseData =(HtmlParseData) page.getParseData();
                    html= htmlParseData.getHtml();
                }
            }


            if(url.startsWith("https://www.thss.tsinghua.edu.cn/faculty/")){
                this.teachers.add(addQhuTeacher(html));
            }
            else if(url.startsWith("http://faculty.hust.edu.cn/")){
                this.teachers.add(addHustTeacher(html));
            }else if(url.startsWith("https://www.eecs.mit.edu/people/")){
                this.teachers.add(addMitTeacher(html));
            }
        }
    }
    public static Teacher addHustTeacher(String html){

        Document doc = Jsoup.parse(html, "UTF-8");
        try {
            String name=doc.getElementsByClass("info").first().child(0).text();
            String position=doc.getElementsByClass("cont").first().child(0).text();
            String rsAreas=doc.getElementsByClass("blockwhite Rsh-focus").first().child(1).text();
            Teacher teacher=new Teacher(name,position,rsAreas,"华中科技大学");
            return teacher;
        }catch (Exception e){
            e.printStackTrace();
            return new Teacher();
        }

    }
    public static Teacher addQhuTeacher(String html){
        Document doc = Jsoup.parse(html, "UTF-8");

        try {
            String name=doc.getElementsByClass("basic-col-2").first().child(0).child(1).text();
            String position=doc.getElementsByClass("basic-col-2").first().child(1).child(1).text();
            String rsAreas=doc.getElementsByClass("basic-col-3").first().child(0).child(1).text();
            Teacher teacher=new Teacher(name,position,rsAreas,"清华大学");
            return teacher;
        }catch (Exception e){
            e.printStackTrace();
            return new Teacher();
        }
    }
    public static Teacher addMitTeacher(String html){
        Document doc = Jsoup.parse(html, "UTF-8");

        try {
            String name=doc.getElementsByTag("header").last().child(0).child(1).child(1).text();
            String position=doc.getElementsByTag("section").first().child(0).child(2).child(0).text();
            String rsAreas=doc.getElementsByTag("section").first().child(0).child(3).child(0).child(1).text();
            Teacher teacher=new Teacher(name,position,rsAreas,"MIT");
            return teacher;
        }catch (Exception e){
            e.printStackTrace();
            return new Teacher();
        }
    }
}
