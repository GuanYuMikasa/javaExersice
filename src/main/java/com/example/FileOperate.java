package com.example;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Vector;

public class FileOperate {
    public static Vector<Teacher> excelRead(String path)throws Exception{
        FileInputStream inputStream = new FileInputStream(path+"信息表-XLS.xls");
        Workbook workbook = new HSSFWorkbook(inputStream);
        Sheet sheet = workbook.getSheetAt(0);
        Vector<Teacher> teachers=new Vector<>();
        //读取第一行 第一列
        for(int rowNumber=1;rowNumber<sheet.getPhysicalNumberOfRows();rowNumber++){
            Row row=sheet.getRow(rowNumber);
            String name="";
            String position="";
            String school="";
            String rsAreas="";
            for(int cellNumber=0;cellNumber<4;cellNumber++){
                Cell cell= row.getCell(cellNumber);
                switch (cellNumber){
                    case 0:
                        name=cell.getStringCellValue();
                        break;
                    case 1:
                        position=cell.getStringCellValue();
                        break;
                    case 2:
                        school=cell.getStringCellValue();
                        break;
                    case 3:
                        rsAreas=cell.getStringCellValue();
                        break;
                }
            }
            Teacher teacher=new Teacher(name,position,rsAreas,school);
            teachers.add(teacher);
        }

        inputStream.close();
        return teachers;
    }
    public static void excelWrite(String path, Vector<Teacher> teachers)throws Exception{

        excelWrite(path,"信息表",teachers);
    }
    public static void excelWrite(String path,String name, Vector<Teacher> teachers)throws Exception{
        //创建一个工作簿
        Workbook workbook = new HSSFWorkbook();

        //创建表
        Sheet sheet = workbook.createSheet();
        //写入数据
        Row head =sheet.createRow(0);
        head.createCell(0).setCellValue("姓名");
        head.createCell(1).setCellValue("职称");
        head.createCell(2).setCellValue("学校");
        head.createCell(3).setCellValue("研究领域");
        for (int rowNumber = 0; rowNumber < teachers.size(); rowNumber++) {
            //创建行
            Row row = sheet.createRow(rowNumber+1);
            for (int cellNumber = 0; cellNumber < 4; cellNumber++) {
                //创建列
                Cell cell = row.createCell(cellNumber);
                switch (cellNumber){
                    case 0:
                        cell.setCellValue(teachers.get(rowNumber).name);
                        break;
                    case 1:
                        cell.setCellValue(teachers.get(rowNumber).position);
                        break;
                    case 2:
                        cell.setCellValue(teachers.get(rowNumber).school);
                        break;
                    case 3:
                        cell.setCellValue(teachers.get(rowNumber).rsAreas);
                        break;
                }
            }
        }

        FileOutputStream fileOutputStream = new FileOutputStream(path+ name+"-XLS.xls");
        workbook.write(fileOutputStream);
        fileOutputStream.close();
    }
}
