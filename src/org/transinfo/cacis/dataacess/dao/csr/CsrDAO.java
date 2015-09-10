package org.transinfo.cacis.dataacess.dao.csr;

import java.util.Collection;
import java.util.Map;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.common.CommonDataBean;
import org.transinfo.cacis.dataacess.dao.BaseDAO;
import org.transinfo.cacis.dto.csr.CallRecordLogDto;
import org.transinfo.cacis.dto.csr.CsrDto;
import org.transinfo.cacis.dto.csr.CsrSearchDto;
import org.transinfo.cacis.dto.csr.CustomerInfoDto;
import org.transinfo.cacis.dto.customerservice.CreditSplitDto;

@SuppressWarnings("unchecked")
public interface CsrDAO extends BaseDAO{
	
	public Collection callCenterSearch(CsrSearchDto objSearchDto)throws TPlusException;
	
	 public CommonDataBean previousCallsCheck(String  refNo)throws TPlusException;
	
	public Collection previousCallData(CallRecordLogDto objRecLogDto)throws TPlusException;
	
	public void callRecordLog(CallRecordLogDto objRecLogDto)throws TPlusException;

	public boolean  callRecordUpdate(CallRecordLogDto objRecLogDto)throws TPlusException;

	public Collection openCalls()throws TPlusException;
	
	public Collection csrSummary(CsrDto objDto)throws TPlusException;
	
	public Collection cardActivities(CsrDto objDto)throws TPlusException;
	
	public Collection tranxHistory(CsrDto objDto)throws TPlusException;
	
	public  Collection accountInfo(CsrDto objDto) throws TPlusException;
	
	public  CustomerInfoDto customerInfo(CsrDto objDto) throws TPlusException;
	
	public Collection csrResetPinCount(CsrDto objSearchDto)throws TPlusException;
	
	public  Map cardDetails(CsrDto objDto) throws TPlusException;
	
	public  CreditSplitDto  creditSplittData(CreditSplitDto objDto) throws TPlusException;
	
	//for callType List
	 public Map callTypeListData()throws TPlusException;
	
	//for checking Existing records 
    public int checkExistrecord(Object commobj)throws TPlusException;
}
