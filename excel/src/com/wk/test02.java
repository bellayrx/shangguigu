package com.wk;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;


/**
 * 写入Excel文件的方法（写表头，写数据）
 * @author lmb
 * @date 2017-3-16
 *
 */


public class test02 {
    public static void main(String[] args) {
        HSSFWorkbook xls = new HSSFWorkbook();//創建文件
        HSSFSheet sheet =xls.createSheet();//創建sheet

        HSSFRow row0 = sheet.createRow(0);
        HSSFCell cell=row0.createCell(0);
        cell.setCellValue(new HSSFRichTextString("成績"));


    }
}
class E{
    /**
     * 将List集合数据写入excel（单个sheet）
     * @param filePath 文件路径
     * @param excelTitle 文件表头
     * @param employeeList 要写入的数据集合
     * @param sheetName sheet名称
     */
    public static void writeEmployeeListToExcel(String filePath,String[] excelTitle,List<Employee> employeeList,String sheetName) {
        System.out.println("开始写入文件>>>>>>>>>>>>");
        Workbook workbook = null;
        if (filePath.toLowerCase().endsWith("xls")) {//2003
            workbook = new XSSFWorkbook();
        } else if (filePath.toLowerCase().endsWith("xlsx")) {//2007
            workbook = new HSSFWorkbook();
        } else {
//			logger.debug("invalid file name,should be xls or xlsx");
        }
        //create sheet
        Sheet sheet = workbook.createSheet(sheetName);
        int rowIndex = 0;//标识位，用于标识sheet的行号
        //遍历数据集，将其写入excel中
        try {
            //写表头数据
            Row titleRow = sheet.createRow(rowIndex);
            for (int i = 0; i < excelTitle.length; i++) {
                //创建表头单元格,填值
                titleRow.createCell(i).setCellValue(excelTitle[i]);
            }
            System.out.println("表头写入完成>>>>>>>>");
            rowIndex++;
            //循环写入主表数据
            for (Iterator<Employee> employeeIter = employeeList.iterator(); employeeIter.hasNext(); ) {
                Employee employee = employeeIter.next();
                //create sheet row
                Row row = sheet.createRow(rowIndex);
                //create sheet coluum(单元格)
                Cell cell0 = row.createCell(0);
                cell0.setCellValue(employee.getName());
                Cell cell1 = row.createCell(1);
                cell1.setCellValue(employee.getGender());
                Cell cell2 = row.createCell(2);
                cell2.setCellValue(employee.getAge());
                Cell cell3 = row.createCell(3);
                cell3.setCellValue(employee.getDepartment());
                Cell cell4 = row.createCell(4);
                cell4.setCellValue(employee.getSalary());
                Cell cell5 = row.createCell(5);
                cell5.setCellValue(employee.getDate());
                rowIndex++;
            }
            System.out.println("主表数据写入完成>>>>>>>>");
            FileOutputStream fos = new FileOutputStream(filePath);
            workbook.write(fos);
            fos.close();
            System.out.println(filePath + "写入文件成功>>>>>>>>>>>");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }