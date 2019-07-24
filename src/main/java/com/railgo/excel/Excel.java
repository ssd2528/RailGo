package com.railgo.excel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellRange;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

public class Excel {
	@Test
	public void excelRead() throws FileNotFoundException, IOException {
		List<String> paths = Arrays.asList("C:\\KSW\\내일고\\자료실\\서비스분류코드.xlsx");
		
		for (String path : paths)  {
			Workbook tempWorkbook;
			
			if (path.endsWith(".xls")) {
				tempWorkbook = new HSSFWorkbook(new FileInputStream(path));
			}
			else if (path.endsWith(".xlsx")) {
				tempWorkbook = new XSSFWorkbook(new FileInputStream(path));
			}
			else {
				throw new IllegalAccessError("xls / xlsx 확장자만 읽을 수 있습니다.");
			}
			
			try (Workbook workbook = tempWorkbook) {
				System.out.println("경로 : " + path);
				Sheet sheet = workbook.getSheetAt(0);
				//System.out.println("첫번째 sheet 읽음");
				
				// 행을 가져옵니다.
				for (Row row : sheet) {
					// cell (행의 각 열) 을 가져옵니다.
					System.out.print("INSERT INTO CATEGORY (CONTENTTYPEID, CONTENTTYPENAME, CAT1, CAT2, CAT3, CAT1NAME, CAT2NAME, CAT3NAME) VALUES(");
					row.forEach(cell -> {
						System.out.print("'"+cell.toString()+"'");
						if(cell.getColumnIndex()==7) {
						}else {
						System.out.print(",");	
					}
					});
					System.out.print(");");
					System.out.println();
				}
			}
		}
	}
}