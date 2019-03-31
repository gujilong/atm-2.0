package com.dayuan.util;


import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import java.io.File;
import java.io.IOException;

public class JslText {
    public static void main(String[] args) throws Exception{

    getdate("C:/Users/Administrator/Desktop/test.xls");

    }


    public static void getdate(String path) throws IOException, BiffException {
        Workbook wb = Workbook.getWorkbook(new File(path));
        Sheet[] sheets = wb.getSheets();
        for(Sheet sheet:sheets){
            int columns = sheet.getRows();
            for(int i=0;i<columns;i++){
                Cell[] cells = sheet.getRow(i);
                for(Cell cell:cells){
                    System.out.print(cell.getContents()+"\t");
                }
                System.out.println("");
            }
        }
    }
}
