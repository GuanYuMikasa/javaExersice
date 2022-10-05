package ex4;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class ExcelUtil {
    public static void main(String[] args) throws IOException {
        //获取工作簿
        XSSFWorkbook book = new XSSFWorkbook("D:\\Desktop\\1.xls");
        //获取工作表
        XSSFSheet sheet = book.getSheetAt(0);
//        //第一种读取读取所有数据，实际中不需要
//        //获取行
//        for (Row cells : sheet) {
//            //获取单元格
//            for (Cell cell : cells) {
//                //获取单元格中的内容
//                cell.setCellType(CellType.STRING);
//                System.out.println(cell.getStringCellValue());
//            }
//        }
        //普通for循环
        //开始索引0  结束索引
        int lastRowNum = sheet.getLastRowNum();
        System.out.println("最后一行："+lastRowNum);
        for (int i = 1; i <= lastRowNum; i++) {
            //获取单元格
            XSSFRow row = sheet.getRow(i);
            if(row!=null){
                List<String> list =new ArrayList<>();
                for (Cell cell : row) {
                    if(cell!=null && !"".equals(cell)){
                        //此处是把单元格都转换成String类型
                        cell.setCellType(CellType.STRING);
                        String cellValue = cell.getStringCellValue();
                        System.out.println("单元格数据："+cellValue);
                        list.add(cellValue);
                    }
                }

            }


        }
        //释放资源
        book.close();
    }

}