package org.transinfo.cacis.dataacess.dao.accounting;

import java.util.Collection;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.dao.BaseDAO;
import org.transinfo.cacis.dto.accounting.CardHolderPaymentDto;
import org.transinfo.cacis.dto.riskmanagement.OriginalRequestDto;

@SuppressWarnings("unchecked")
public interface CardHolderPaymentDAO extends BaseDAO
{

    //method for  getting CustomerServiceData Bean to show the data
	 public Collection search(CardHolderPaymentDto objDto)throws TPlusException;
	//method for inserting data into settlement table
	 public boolean cardHolderPayment(OriginalRequestDto  objDto, String userId) throws TPlusException;
	 //for checking Existing records 
     public int checkExistrecord(Object commobj)throws TPlusException;   
     
}