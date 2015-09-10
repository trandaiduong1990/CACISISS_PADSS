//Package name
package org.transinfo.cacis.model.excell;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.common.constants.CommonCodes;
import org.transinfo.cacis.dto.authorization.BlackListCardDto;
import org.transinfo.cacis.dto.cardproduction.ApplicationFormDto;
import org.transinfo.cacis.dto.cardproduction.CardDeliverSearchDto;
import org.transinfo.cacis.dto.cardproduction.CardEmbossingSearchDto;
import org.transinfo.cacis.dto.cardproduction.PinPrintingSearchDto;
import org.transinfo.cacis.dto.common.AddressDto;
import org.transinfo.cacis.dto.settings.CurrencyDto;
import org.transinfo.cacis.model.excell.databean.TableDataBean;
import org.transinfo.cacis.model.excell.databean.TableStructureReader;
import org.transinfo.cacis.util.DateUtil;

public class UploadData {

 public UploadData() {}

 ExcelDataReader excellDataReader=null;

 public ArrayList execute(InputStream Istream,String uploadType,String uploadFileName) throws TPlusException  {
	
	ArrayList dtoList = new ArrayList();
      try {

        excellDataReader = new ExcelDataReader(Istream,uploadType,uploadFileName);
        excellDataReader.read();
        Map dataMap =  TableStructureReader.getAllData();
		Set dataSet = dataMap.entrySet();
		Iterator it = dataSet.iterator();

		while(it.hasNext()){
		   	Map.Entry   me = (Map.Entry)it.next();
		    TableDataBean data = (TableDataBean)me.getValue();
		    /*commented by satyam(this method to create this insert script like
		    INSERT INTO CURR_RATE(Issuer_Id, Curr_Code, Rate, last_updated_date, last_updated_by ) VALUES('Issuer3', '101', '50', SYSDATE, 'SYSTEM' )*/
		   // sqlList.addAll(createInsertScripts(data));
		      dtoList= setInsertData(data);
		 }
   
      } catch (Exception ex) {
		 if(ex instanceof TPlusException){
		 		    throw (TPlusException) ex;
		     } else {
		 throw new TPlusException("0","UploadData.execute() ==>"+ ex.toString());
	}

        } finally {
            // Finished - close the reader and free up memory
            try {
                if (excellDataReader != null) {
                    excellDataReader.close();
                }
            } catch (IOException ioe) { };
        }
       return  dtoList;
    }

 //by satyam 
 private  ArrayList setInsertData(TableDataBean data) {
	  
	  List recordList = data.getRecords();
	  ArrayList toSetvalues =null;
	  ArrayList dtoList = new ArrayList();
	  //for currencyrate
	  ArrayList currRateList = new ArrayList();
	  ArrayList currCodeList = new ArrayList();
	  CurrencyDto currDto = new CurrencyDto(); 
	  //to CardProduction
	  String cardNumbers[] = new String[recordList.size()];
	  int k=0;//to assign value to cardNumbers[] String array
	  
	
	  //getting rows in sheet
  for (Iterator itRows = recordList.iterator(); itRows.hasNext();) {
          List rowList = (List)itRows.next();
       
            int i=0;
           toSetvalues =new ArrayList();
         //getting filed value from row and adding to arraylist
       for (Iterator itCols = rowList.iterator(); itCols.hasNext();) {
             String value = (String)itCols.next();
             if(value!=null)
             toSetvalues.add(i,value);
              i++;
          }
       //according to the table name getting the data from above arraylist and setting to dto's
       if(data.getTableName().equalsIgnoreCase("S_APPLICATION_FORM")){
    	   System.out.println("in Upload.java setInsertDate() method in ApplicationForm");  
           ApplicationFormDto appDto = new ApplicationFormDto();
          
           //to write to excell after reading insertion sucees or fail
           appDto.setReferenceId((String)toSetvalues.get(0));
           //bank branch and customer type
            appDto.setBranchId((String)toSetvalues.get(1));
            appDto.setCustomerTypeId((String)toSetvalues.get(2));
            //CardProducts
            System.out.println("2"); 
           //String[] selectedAppCardProducts = {(String)toSetvalues.get(3)};
           String selectedAppCardProducts = (String)toSetvalues.get(3);
           // selectedAppCardProducts[0]= (String)toSetvalues.get(3);
            appDto.setSelectedAppCardProducts(selectedAppCardProducts);
           
            //Applicant Details
            System.out.println("3"); 
            appDto.setCustomerName((String)toSetvalues.get(4));
            appDto.setSurName((String)toSetvalues.get(5));
            appDto.setEmbossingName((String)toSetvalues.get(6));
            appDto.setDob(DateUtil.convertDateStringToDate((String)toSetvalues.get(7)));
            appDto.setPob((String)toSetvalues.get(8));
            appDto.setIdNumber((String)toSetvalues.get(9));
            appDto.setIdDate(DateUtil.convertDateStringToDate((String)toSetvalues.get(10)));
            appDto.setExpDate(DateUtil.convertDateStringToDate((String)toSetvalues.get(11)));
            appDto.setIdPlace((String)toSetvalues.get(12));
            appDto.setNationality((String)toSetvalues.get(13));
            appDto.setBillingTo((String)toSetvalues.get(14));
          
            //Empolyment Details
            System.out.println("4"); 
            appDto.setJobStatus((String)toSetvalues.get(15)); 
            appDto.setJobStatusOthers((String)toSetvalues.get(16));
            appDto.setJobType((String)toSetvalues.get(17));
            appDto.setJobTypeOthers((String)toSetvalues.get(18));
            appDto.setIncome(Float.valueOf((String)toSetvalues.get(19)).floatValue());
            appDto.setCompanyName((String)toSetvalues.get(20));
         
            //Personal Details
            System.out.println("5"); 
            appDto.setGender((String)toSetvalues.get(21));
            appDto.setMaritalStatus((String)toSetvalues.get(22));
            appDto.setDependents(Integer.valueOf((String)toSetvalues.get(23)).intValue());
            appDto.setEducation((String)toSetvalues.get(24));
            appDto.setEmail((String)toSetvalues.get(25));
           //Family Details
            System.out.println("6"); 
            appDto.setReferenceName((String)toSetvalues.get(26));
            appDto.setReferenceCompany((String)toSetvalues.get(27));
            appDto.setReferencePhone((String)toSetvalues.get(28));
            appDto.setReferenceFax((String)toSetvalues.get(29));
            
            // AtmLink
            System.out.println("7"); 
             appDto.setCheckedAtmLink(((String)toSetvalues.get(30)).charAt(0));
             appDto.setSavingAccount((String)toSetvalues.get(31));
             appDto.setCheckingAccount((String)toSetvalues.get(32));
           
             //Supply CardHolder Details
             System.out.println("8"); 
             appDto.setCheckedSupplCardRequired(((String)toSetvalues.get(33)).charAt(0));
             appDto.setSupplCustomerName((String)toSetvalues.get(34));
             appDto.setSupplSurName((String)toSetvalues.get(35));
             appDto.setSupplEmbossingName((String)toSetvalues.get(36));
             appDto.setSupplDob(DateUtil.convertDateStringToDate((String)toSetvalues.get(37)));
             appDto.setSupplPob((String)toSetvalues.get(38));
             appDto.setSupplGender((String)toSetvalues.get(39));
             appDto.setSupplMaritalStatus((String)toSetvalues.get(40));
             appDto.setSupplIdNumber((String)toSetvalues.get(41));
             appDto.setSupplIdDate(DateUtil.convertDateStringToDate((String)toSetvalues.get(42)));
             appDto.setSupplIdExpire(DateUtil.convertDateStringToDate((String)toSetvalues.get(43)));
             appDto.setSupplIdPlace((String)toSetvalues.get(44));
             appDto.setSupplNationality((String)toSetvalues.get(45));
             appDto.setSupplEmail((String)toSetvalues.get(46));
             appDto.setSupplIncome(Float.valueOf((String)toSetvalues.get(47)).floatValue());
             appDto.setRelationShip(Integer.valueOf((String)toSetvalues.get(48)).intValue());
           
           
             //Remarks Application documents
             System.out.println("9"); 
             appDto.setRemarks((String)toSetvalues.get(49));
            //String[] selectedAppDocuments = new String[10];
             String[] selectedAppDocuments = {(String)toSetvalues.get(50)};
            // selectedAppDocuments[0] =  (String)toSetvalues.get(50);
             appDto.setSelectedAppDocuments(selectedAppDocuments)  ;
            
            //Setting Addresses Details
             System.out.println("10"); 
        
        AddressDto homeAddress = new AddressDto();
           //  homeAddress.setAddressId();
             homeAddress.setAddress1((String)toSetvalues.get(51));
             homeAddress.setAddress2((String)toSetvalues.get(52));
             homeAddress.setAddressType(CommonCodes.HOME_ADDRESS);
             homeAddress.setCity((String)toSetvalues.get(53));
             homeAddress.setState((String)toSetvalues.get(54));
             homeAddress.setCountry((String)toSetvalues.get(55));
             homeAddress.setPostalCode(Long.valueOf(String.valueOf(toSetvalues.get(56))).longValue());
             homeAddress.setPhone((String)toSetvalues.get(57));
             homeAddress.setFax((String)toSetvalues.get(58));
      
        AddressDto companyAddress = new AddressDto();
            // companyAddress.setAddressId();
             companyAddress.setAddress1((String)toSetvalues.get(59));
             companyAddress.setAddress2((String)toSetvalues.get(60));
             companyAddress.setAddressType(CommonCodes.COMPANY_ADDRESS);
             companyAddress.setCity((String)toSetvalues.get(61));
             companyAddress.setState((String)toSetvalues.get(62));
             companyAddress.setCountry((String)toSetvalues.get(63));
             companyAddress.setPostalCode(Long.valueOf(String.valueOf(toSetvalues.get(64))).longValue());
             companyAddress.setPhone((String)toSetvalues.get(63));
             companyAddress.setFax((String)toSetvalues.get(66));
            
       AddressDto supplementaryAddress = new AddressDto();
            // supplementaryAddress.setAddressId();
             supplementaryAddress.setAddress1((String)toSetvalues.get(67));
             supplementaryAddress.setAddress2((String)toSetvalues.get(68));
             supplementaryAddress.setAddressType(CommonCodes.SUPPLEMENTARY_ADDRESS);
             supplementaryAddress.setCity((String)toSetvalues.get(69));
             supplementaryAddress.setState((String)toSetvalues.get(70));
             supplementaryAddress.setCountry((String)toSetvalues.get(71));
             supplementaryAddress.setPostalCode(Long.valueOf(String.valueOf(toSetvalues.get(72))).longValue());
             supplementaryAddress.setPhone((String)toSetvalues.get(73));
             supplementaryAddress.setFax((String)toSetvalues.get(74));
        //adding all the addressDto's to address Set    
          Set applicationAddress = new HashSet();
             applicationAddress.add(homeAddress);
             applicationAddress.add(companyAddress);
             applicationAddress.add(supplementaryAddress);
          
           appDto.setApplicationAddress(applicationAddress)  ;
          
           dtoList.add(appDto);
       System.out.println("in Upload.java setInsertDate() dtoList size" +dtoList.size() );   
           }
       if(data.getTableName().equalsIgnoreCase("S_CURR_RATE")){
    	       //getting the currencycode and rate and adding to respective lists 
    	       currCodeList.add((String)toSetvalues.get(1));
    	       currRateList.add((String)toSetvalues.get(2));
             
            }
       if(data.getTableName().equalsIgnoreCase("S_BLACKLIST_CARDS")){
        
           	 BlackListCardDto objBlackListDto = new BlackListCardDto();
           	objBlackListDto.setReferenceId((String)toSetvalues.get(0));
        	 objBlackListDto.setCardNumber(Long.parseLong((String)toSetvalues.get(1)));
        	 objBlackListDto.setCardStatusId(Long.parseLong((String)toSetvalues.get(2)));
        	 dtoList.add(objBlackListDto);
        	     
            }
        if(data.getTableName().equalsIgnoreCase("S_CARD_EMBOSSING")){
        	
        	 CardEmbossingSearchDto cardEmboSerchDto =new CardEmbossingSearchDto();
        	 cardEmboSerchDto.setReferenceId((String)toSetvalues.get(0));
        	 cardNumbers[k] = (String)toSetvalues.get(1);
    	      //k++;
             cardEmboSerchDto.setSelectedCards(cardNumbers);
    		 dtoList.add(cardEmboSerchDto);
          }
        if(data.getTableName().equalsIgnoreCase("S_PIN_PRINTING")){
  		  PinPrintingSearchDto pinPrintSearchDto  = new PinPrintingSearchDto();
  	      pinPrintSearchDto.setReferenceId((String)toSetvalues.get(0));
  		  cardNumbers[k]=(String)toSetvalues.get(1);
  		  pinPrintSearchDto.setSelectedCards(cardNumbers);
  		  dtoList.add(pinPrintSearchDto);
        	     
         }
  	  if(data.getTableName().equalsIgnoreCase("S_CARD_DELIVER")){
  		
  		  CardDeliverSearchDto carDeliverSearchDto = new CardDeliverSearchDto();
  		  carDeliverSearchDto.setReferenceId((String)toSetvalues.get(0));
  		  cardNumbers[k]=(String)toSetvalues.get(1);
  		  carDeliverSearchDto.setSelectedCards(cardNumbers);
  		  dtoList.add(carDeliverSearchDto);
  	       	     
  	      }
  }//for loop close
	
  //to set all the Currency Code and Rate List to CurrencyDto and and dto to dtoList
	  if(data.getTableName().equalsIgnoreCase("S_CURR_RATE")){
	      currDto.setCurrCodeList(currCodeList);
		  currDto.setCurrRateList(currRateList);
	      dtoList.add(currDto);
	  }
	
	
	  
	 return dtoList;
 }
 
 
}