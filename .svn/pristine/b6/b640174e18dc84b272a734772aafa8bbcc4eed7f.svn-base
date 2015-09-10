package org.transinfo.cacis.dataacess.dao.disputemanagement;

import java.util.ArrayList;
import java.util.Collection;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.dao.BaseDAO;
import org.transinfo.cacis.dto.disputemanagement.RequestWorkItemFormDto;
import org.transinfo.cacis.dto.disputemanagement.SearchRequestWorkItemDto;
import org.transinfo.cacis.dto.disputemanagement.WorkItemDto;


public interface WorkItemDAO extends BaseDAO
{


  	public Collection search(SearchRequestWorkItemDto objSearchDto,int pageNo)throws TPlusException;
	public ArrayList workItemInfo(RequestWorkItemFormDto objDto) throws TPlusException;

  	//Hee Leng
	public boolean add(WorkItemDto objDto)throws TPlusException;
	public int checkExistRecord(WorkItemDto objDto)throws TPlusException;
	public Collection getDocumentList(String claimNo)throws TPlusException;

}
