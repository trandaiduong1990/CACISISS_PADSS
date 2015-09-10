package org.transinfo.cacis.model.excell;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jxl.Workbook;
import jxl.format.Colour;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

public class ExcellDataWriter {
	
	public ExcellDataWriter(){}

	//to write the data to excel	
public void writeToExcel(String fileName,String sheetName,List resultRow){
		
		WritableWorkbook wb1;
		WritableSheet ws1;
		WritableCellFormat headingcell;
		WritableCellFormat cellformat;
		Colour clrHead = Colour.GRAY_25;
		WritableFont headingfont = new WritableFont(WritableFont.ARIAL, 8,WritableFont.BOLD);
		WritableFont cellfont = new WritableFont(WritableFont.ARIAL, 8);
		
		ArrayList colList =null;
		String tempVal;
		String excelname = fileName+".xls";
		System.out.println("Excel sheet name is "+excelname);
		File fle = new File(excelname);
		
	try{
		
		if(!fle.exists()){
		   wb1 = Workbook.createWorkbook(fle);
		}else{
		   wb1 = Workbook.createWorkbook(fle,Workbook.getWorkbook(fle));
		}
	
		int maxSheets = wb1.getNumberOfSheets();
	    if(maxSheets>=1){
	        ws1 = wb1.createSheet(sheetName+maxSheets,maxSheets+1);
	       }else{
	    	  ws1 = wb1.createSheet(sheetName,maxSheets+1);
		 }   
		headingcell = new WritableCellFormat(headingfont);
		headingcell.setWrap(false);
		headingcell.setBackground(clrHead);
	
		colList = (ArrayList)resultRow.get(0);
		for(int i = 0; i < colList.size(); i++){
		 Label colLabel = new Label(i,0,(colList.get(i).toString()),headingcell);
		//to set the column width(setColumnView(ronum,width in characters)
		if(i==0 || i==1){
	 	    ws1.setColumnView(i,12);
	 	 }else{
		   ws1.setColumnView(i,15);
		}
		  ws1.addCell(colLabel);
		
		}
		System.out.println("Going to write to Excel");
//		Format for multiline cells
		for(int k = 1; k < resultRow.size();k++){
		  colList = (ArrayList)resultRow.get(k);
		  cellformat = new WritableCellFormat(cellfont);
		  cellformat.setWrap(true);
	  for(int j = 0; j < colList.size();j++){
		if(colList.get(j) == null){
		   tempVal = " ";
		 }else{
		   tempVal = colList.get(j).toString();
		 }
	     
		Label colValue = new Label(j,k,tempVal,cellformat);
		ws1.addCell(colValue);
	
		}
	}
		System.out.println("Written to Excel");
//		THESE TWO STEPS ARE NECESSARY TO WRITE AND CLOSE EXCEL
		wb1.write();
		wb1.close();
		}catch(WriteException e){
		        e.printStackTrace();
		}catch(IOException e){
		        e.printStackTrace();
		}catch(BiffException e){
	          	e.printStackTrace();
		}
	}

	

public static void main(String args[]){
	ExcellDataWriter objWriter = new ExcellDataWriter();
	List resultRow = new ArrayList();
ArrayList colnameArl = new ArrayList();
	colnameArl.add("REFERENCE ID");
	colnameArl.add("PROCESS CODE");
	colnameArl.add("DESCRIPTION");
	colnameArl.add("PROCESSDATE");
	 resultRow.add(colnameArl);
//	colnameArl.add("FILENAME");
//	colnameArl.add("STATUS");
ArrayList colDataArl = new ArrayList();
    colDataArl.add(0,"1");
    colDataArl.add(1,"Fail");
    colDataArl.add(2,"All the records processed and apllication created");
    colDataArl.add(3,new Date());
  //  colDataArl.add("Application form");
  //  colDataArl.add("done");
      resultRow.add(colDataArl);
    ArrayList colDataArl2 = new ArrayList(); 
    colDataArl2.add(0,"2");
    colDataArl2.add(1,"Success");
    colDataArl2.add(2,"All the records processed and apllication created");
    colDataArl2.add(3,new Date());
    //  colDataArl.add("Application form");
    //  colDataArl.add("done");
        resultRow.add(colDataArl2);
        
	objWriter.writeToExcel("D:/eclipse/workspace/StrutsCacis/JavaSource/org/transinfo/cacis/model/excell/xls/response/APPLICATIONFORM_RESPONSE","APPRESPONSE",resultRow);
	//objWriter.writeToExcel("D://eclipse//workspace//StrutsCacis//JavaSource//org//transinfo//cacis//model//excell//S_APPLICATION_FORM","BLACKLIST",resultRow);
	try{
	//   objWriter.writeToExcellTest();
	}catch(Exception e){
		System.out.println("the Error"+e.getMessage());
	}
	}
}
