//Package name
package org.transinfo.cacis.model.excell;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.model.excell.databean.TableStructureReader;
import org.transinfo.cacis.model.excell.tablestructure.ApplicationFormTableStructure;
import org.transinfo.cacis.model.excell.tablestructure.BlackListCardsTableStructure;
import org.transinfo.cacis.model.excell.tablestructure.CardDeliverTableStructure;
import org.transinfo.cacis.model.excell.tablestructure.CardEmbossingTableStructure;
import org.transinfo.cacis.model.excell.tablestructure.CurrencyRateTableStructure;
import org.transinfo.cacis.model.excell.tablestructure.PinPrintingTableStructure;

public class ExcelDataReader {

   private String uploadType="";
   private String uploadFileName="";
   private Workbook workbook;

  TableStructureReader ts;


  protected ExcelDataReader() {
     }

  public ExcelDataReader(String fileName) throws IOException {
        try {
            File excelFile = new File(fileName);
            workbook = Workbook.getWorkbook(excelFile);
        } catch (BiffException be) {
            throw new IOException(be.getMessage());
        }
    }

  public ExcelDataReader(InputStream fstream,String uploadType,String uploadFileName) throws IOException {
        try {
			this.uploadType=uploadType;
			this.uploadFileName=uploadFileName;
            workbook = Workbook.getWorkbook(fstream);
        } catch (BiffException be) {
            throw new IOException(be.getMessage());
        }
    }

   public void read() throws TPlusException {

    //changed by satyam
    try{
           /*  DBManager objDBManager = new DBManager(AdminConfig.poolName);
             StringBuffer strSql = new StringBuffer();
             strSql.append("select upload_type,upload_filename from uploadlog where upload_id ="+uploadType+" ");
             strSql.append(" and  upload_filename='"+uploadFileName+"' and  status='S' ");
             System.out.println("Process check query"+strSql.toString());
             objDBManager.executeSQL(strSql.toString());
			 DBResultSet rs = objDBManager.getResultSet();
			 if(rs.next()){
				 throw new TPlusException("0","ExcellReader.read()==>Aleardy process the Data ");
				 }*/

		     switch(Integer.parseInt(uploadType)){

			       case 1:
			          {
				System.out.println("\n\nwe are in uploadType ApplicationForm");
				      ts = new ApplicationFormTableStructure();
				      break;
	                  }
			       case 2:
		             {
			System.out.println("\n\nwe are in uploadType CurrencyRate");
			          ts = new CurrencyRateTableStructure();
			          break;
		             } 
			       case 3:
		             {
			System.out.println("\n\nwe are in uploadType BlackListCards");
			          ts =new BlackListCardsTableStructure();
			          break;
   		             }        
			       case 4:
		             {
			System.out.println("\n\nwe are in uploadType CardEmbossing");
			         ts = new CardEmbossingTableStructure();
			         break;
			         }        
			       case 5:
		             {
			System.out.println("\n\nwe are in uploadType PinPrinting");
			         ts = new PinPrintingTableStructure();
			         break;
		             }
  	              case 6:
		             {
			 System.out.println("\n\nwe are in uploadType CardDeliver");
			        ts = new CardDeliverTableStructure();
			        break;
			        }        
		                
		         
              	}
        	
		    int totalSheets = workbook.getNumberOfSheets();
            System.out.println("No of sheets"+totalSheets);

           for (int tabNum = 0; tabNum < ts.getTotalIndex(); tabNum++) {
			String tname = ts.getTable(tabNum);
            System.out.println("tableName:"+tname);
            String[] colArray = ts.getColumns(tabNum);
           // System.out.println("tableColumns:"+colArray.length);

            Sheet sheet = workbook.getSheet(tname);

            if (sheet == null) {
               continue;
              }

            if(tname.startsWith("S")){
				 ts.createData(sheet, tname, colArray);
             }else if(tname.startsWith("M")){
			   ts.createMultibeanData(sheet, tname, colArray);
               }
       }
   }catch(Exception ex){
	   if(ex instanceof TPlusException){
	   		  throw (TPlusException) ex;
		} else {
	       // throw new TPlusException("ExcellReader.read():"+ex.getMessage());
	        System.out.println("ExcellReader.read():"+ex.getMessage());
	          }
       }
 }
     public void close() throws IOException {
	        workbook.close();
    }

}