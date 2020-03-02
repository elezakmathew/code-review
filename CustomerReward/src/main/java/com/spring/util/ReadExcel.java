package com.spring.util;


import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

import model.Customer;
import model.Transaction;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


/**
 * @author Elezabeth
 * Reads data from excel file 
 *
 */
public class ReadExcel {

	/**
	 * 
	 * @param file location
	 * @return list of java object having file data 
	 */
	public  HashMap<Integer,Customer> readExcelfile(String arg) {

        HashMap<Integer, Customer> customerList = new HashMap<Integer,Customer>();
		try
        {
			
			Boolean exsistingCustomer = false;
            ClassLoader cl = this.getClass().getClassLoader();
            java.io.InputStream file = cl.getResourceAsStream(arg);
 
            //Create Workbook instance holding reference to .xlsx file
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            //Get first/desired sheet from the workbook
            XSSFSheet sheet = workbook.getSheetAt(0);
 
            //Iterate through each rows one by one
            Iterator<Row> rowIterator = sheet.iterator();
            
            //Customer list
            //TODO -Change to enhanced for loop
    		
            
            while (rowIterator.hasNext()) 
            {
                Row row = rowIterator.next();

                //For each row, iterate through all the columns
                Iterator<Cell> cellIterator = row.cellIterator();
                
                Transaction transactionObj = new Transaction();
                Customer customerObj = new Customer();
                exsistingCustomer = false; 
                while (cellIterator.hasNext() && row.getRowNum() != 0)
                {
                    Cell cell = cellIterator.next();
                    int columIndex = cell.getColumnIndex();
                                               
                    switch(columIndex) {
                    
                    case 0:                     	
                		if(customerList.containsKey(((Double) getCellValue(cell)).intValue())) {
                    	     exsistingCustomer = true;
                    }else {
                    	exsistingCustomer = false;
                    }
                    	customerObj.setId(((Double) getCellValue(cell)).intValue());

                    	break;
                    case 1:
                    	if (!exsistingCustomer)
                    	customerObj.setName((String) getCellValue(cell));                    
                    	
                    	break;
                    case 2:
                    	transactionObj.setDate((Date) getCellValue(cell));
                    	break;
                    case 3:
                    	transactionObj.setAmount((((Double) getCellValue(cell)).doubleValue()));
                    	break;
                    }
                    
                }
                if(row.getRowNum() != 0 && exsistingCustomer) {
                	

                	if(customerList.get(customerObj.getId()) != null) {
                		customerList.get(customerObj.getId()).addTransactionList(transactionObj);
                	}

                	
                }else if(row.getRowNum() != 0) {
                	customerObj.addTransactionList(transactionObj);
                	customerList.put(customerObj.getId(), customerObj);
                }
               
            }
            file.close();
            workbook.close();
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
		
		return customerList;
		
	}
	
	/**
	 * 
	 * @param cell
	 * @return cell value
	 */
	private Object getCellValue(Cell cell) {
		switch (cell.getCellType()) {
		case NUMERIC:
			if(HSSFDateUtil.isCellDateFormatted(cell)) {
				return cell.getDateCellValue() ;
			}else 
				return cell.getNumericCellValue();
		case STRING:
			return cell.getStringCellValue();

		default:
			break;
		}
		
		return null;
	}
}
