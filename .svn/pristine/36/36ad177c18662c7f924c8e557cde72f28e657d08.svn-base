package org.transinfo.cacis.util.listutil;



import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.transinfo.cacis.TPlusCodes;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.HibernetDAO.HibernetFactory;
import org.transinfo.cacis.dto.settings.BranchDto;
import org.transinfo.cacis.dto.settings.CardProductDto;
import org.transinfo.cacis.dto.settings.CardProductTypeDto;
import org.transinfo.cacis.dto.settings.CardTypeDto;
import org.transinfo.cacis.dto.settings.CountryMasterDto;
import org.transinfo.cacis.dto.settings.CustomerTypeDto;
import org.transinfo.cacis.dto.settings.IssuerDto;

public class ListDataUtil {
	
   public static HashMap countryListData()  throws TPlusException {
			
		   HashMap countryList = new HashMap();
		try	
		  {
		   
		   Session	session = HibernetFactory.currentSession();
		   Transaction tx =session.beginTransaction();
		   Query qry = session.createQuery("From CountryMasterDto");
		   List listCountries = qry.list();
		   for(Iterator it = listCountries.iterator();it.hasNext();){
		   	 CountryMasterDto objDto = new CountryMasterDto();
			 objDto = (CountryMasterDto) it.next();
			 countryList.put(objDto.getCountryCode(),objDto.getCountryName());
			// System.out.println("CountryCode: " + objDto.getCountryCode());
			// System.out.println("CountryName: " + objDto.getCountryName());
			 }
		    tx.commit();
		    HibernetFactory.closeSession();
		  }catch (Exception e){
				System.out.println("Error"+e);
				throw new TPlusException(TPlusCodes.APPLICATION_ERROR,"Error: while retrieving CountryListData Info"+e.getMessage());
			}
	  return countryList;
	}

	public static HashMap  issuerListData()  throws TPlusException {
			
		     HashMap issuerList = new HashMap();
	 try {
		     Session	session = HibernetFactory.currentSession();
		     Transaction tx =session.beginTransaction();
		     Query qry = session.createQuery("From IssuerDto");
		     List listIssuers = qry.list();
		     for(Iterator it = listIssuers.iterator();it.hasNext();){
		     	 IssuerDto objDto = new IssuerDto();
				 objDto = (IssuerDto) it.next();
				 issuerList.put(objDto.getIssuerId(),objDto.getIssuerId()+ "-" +objDto.getIssuerName());
				// System.out.println("IssuerId: " + objDto.getIssuerId());
				// System.out.println("IssuerName " + objDto.getIssuerName());
			  }
		      tx.commit();
		      HibernetFactory.closeSession();
		    }catch (Exception e){
				System.out.println("Error"+e);
				throw new TPlusException(TPlusCodes.APPLICATION_ERROR,"Error: while retrieving issuerListData Info"+e.getMessage());
			 }
		return issuerList;
    }
	  public static HashMap branchListData()  throws TPlusException {
			
		   //  ArrayList branchList = new ArrayList();
		       HashMap branchList = new HashMap();
			try
			{
			    Session	session = HibernetFactory.currentSession();
			    Transaction tx =session.beginTransaction();
			    Query qry = session.createQuery("From BranchDto");
		        List listBranches = qry.list();
		         for(Iterator it = listBranches.iterator();it.hasNext();){
		        	 BranchDto objDto = new BranchDto();
					 objDto = (BranchDto)it.next();
					 branchList.put(objDto.getBranchId(),objDto.getBranchName());
					// branchList.add(objDto.getBranchName());//for arraylist
					// System.out.println("BranchName " + objDto.getBranchName());
				 }
		          tx.commit();
		          HibernetFactory.closeSession();
			 }catch (Exception e){
				System.out.println("Error"+e);
				throw new TPlusException(TPlusCodes.APPLICATION_ERROR,"Error: while retrieving issuerListData Info"+e.getMessage());
			}
			 return branchList;
	  }
	  public static HashMap customerTypeListData()  throws TPlusException {
			
		   HashMap customerTypeList = new HashMap();
		try	
		  {
		   
		   Session	session = HibernetFactory.currentSession();
		   Transaction tx =session.beginTransaction();
		   Query qry = session.createQuery("From CustomerTypeDto");
		   List listCusomerTypes = qry.list();
		   for(Iterator it = listCusomerTypes.iterator();it.hasNext();){
			   CustomerTypeDto objDto = new CustomerTypeDto();
			 objDto = (CustomerTypeDto) it.next();
			 customerTypeList.put(objDto.getCustomerTypeId(),objDto.getCustomerType());
			 System.out.println("CustomerTypeCode: " + objDto.getCustomerTypeId());
			 System.out.println("CustomerTypeName: " + objDto.getCustomerType());
			 }
		    tx.commit();
		    HibernetFactory.closeSession();
		  }catch (Exception e){
				System.out.println("Error"+e);
				throw new TPlusException(TPlusCodes.APPLICATION_ERROR,"Error: while retrieving CountryListData Info"+e.getMessage());
			}
	  return customerTypeList;
	}	  
	  public static HashMap cardTypeListData()  throws TPlusException {
			
		   HashMap cardTypeList = new HashMap();
		try	
		  {
		   
		   Session	session = HibernetFactory.currentSession();
		   Transaction tx =session.beginTransaction();
		   Query qry = session.createQuery("From CardTypeDto");
		   List listCardTypes = qry.list();
		   for(Iterator it = listCardTypes.iterator();it.hasNext();){
			   CardTypeDto objDto = new CardTypeDto();
			 objDto = (CardTypeDto) it.next();
			 cardTypeList.put(objDto.getCardTypeId(),objDto.getCardType());
			 System.out.println("CardTypeCode: " + objDto.getCardTypeId());
			 System.out.println("CardTypeName: " + objDto.getCardType());
			 }
		    tx.commit();
		    HibernetFactory.closeSession();
		  }catch (Exception e){
				System.out.println("Error"+e);
				throw new TPlusException(TPlusCodes.APPLICATION_ERROR,"Error: while retrieving CountryListData Info"+e.getMessage());
			}
	  return cardTypeList;
	}
	  
	  
	  public static HashMap currencyListData()  throws TPlusException {
			
		   HashMap currencyList = new HashMap();
		try	
		  {
		   
		   Session	session = HibernetFactory.currentSession();
		   Transaction tx =session.beginTransaction();
		/*   Query qry = session.createQuery("From CardTypeDto");
		   List listCurrency = qry.list();
		   for(Iterator it = listCurrency.iterator();it.hasNext();){
			 CardTypeDto objDto = new CardTypeDto();
			 objDto = (CardTypeDto) it.next();
			 currencyList.put(objDto.getCardTypeId(),objDto.getCardType());
			
			 }*/
		    tx.commit();
		    HibernetFactory.closeSession();
		  }catch (Exception e){
				System.out.println("Error"+e);
				throw new TPlusException(TPlusCodes.APPLICATION_ERROR,"Error: while retrieving CountryListData Info"+e.getMessage());
			}
	  return currencyList;
	}
	  
  public static HashMap cardProductTypeListData()  throws TPlusException {
			
		   HashMap cardProductTypeList = new HashMap();
		try	
		  {
		   
		   Session	session = HibernetFactory.currentSession();
		   Transaction tx =session.beginTransaction();
		   Query qry = session.createQuery("From CardProductTypeDto");
		   List listCardProductTypes = qry.list();
		   for(Iterator it = listCardProductTypes.iterator();it.hasNext();){
			   CardProductTypeDto objDto = new CardProductTypeDto();
			 objDto = (CardProductTypeDto) it.next();
			 cardProductTypeList.put(new Long(objDto.getCardProductTypeId()),objDto.getCardProductType());
			// System.out.println("CardProductTypeCode: " +objDto.getCardProductTypeId());
			// System.out.println("CardProductTypeName: " + objDto.getCardProductType());
			 }
		    tx.commit();
		    HibernetFactory.closeSession();
		  }catch (Exception e){
				System.out.println("Error"+e);
				throw new TPlusException(TPlusCodes.APPLICATION_ERROR,"Error: while retrieving CountryListData Info"+e.getMessage());
			}
	  return cardProductTypeList;
	}
  
  public static HashMap cardProductListData()  throws TPlusException {
		
	   HashMap cardProductList = new HashMap();
	try	
	  {
	   
	   Session	session = HibernetFactory.currentSession();
	   Transaction tx =session.beginTransaction();
	   Query qry = session.createQuery("From CardProductDto");
	   List listCardTypes = qry.list();
	   for(Iterator it = listCardTypes.iterator();it.hasNext();){
		   CardProductDto objDto = new CardProductDto();
		 objDto = (CardProductDto) it.next();
		 cardProductList.put(objDto.getCardProductId(),objDto.getCardProductName());
		// System.out.println("CardProductCode: " + objDto.getCardProductId));
		// System.out.println("CardProductName: " + objDto.getCardProductName());
		 }
	    tx.commit();
	    HibernetFactory.closeSession();
	  }catch (Exception e){
			System.out.println("Error"+e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,"Error: while retrieving cardProductListData Info"+e.getMessage());
		}
 return cardProductList;
}
 
}
