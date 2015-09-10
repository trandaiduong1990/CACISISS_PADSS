package org.transinfo.cacis.controller.disputemanagement;

import java.util.ArrayList;
import java.util.Collection;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.disputemanagement.WorkItemDAO;
import org.transinfo.cacis.dto.disputemanagement.RequestWorkItemFormDto;
import org.transinfo.cacis.dto.disputemanagement.SearchRequestWorkItemDto;
import org.transinfo.cacis.dto.disputemanagement.WorkItemDto;

public class WorkItemManager {

	private  WorkItemDAO objDAO;

	    public WorkItemManager()throws Exception {
	    	objDAO = DAOFactory.getInstance().getWorkItemDAO();

	      }
	  /*
	    * This method is used for getting the CalimFormList
	    */
	   public Collection search(SearchRequestWorkItemDto objSearchDto,int pageNo) throws TPlusException {

	           Collection claimList =null;
	   try {
		     claimList = objDAO.search(objSearchDto, pageNo);

	       } catch (Exception e) {
	    	   throw new TPlusException("Error in WorkItemManager search method" +e);
	    }
	    return claimList;
	}


   public ArrayList workItemInfo(RequestWorkItemFormDto objDto) throws TPlusException {

	      ArrayList workItemData =null;
   try {
	   workItemData = objDAO.workItemInfo(objDto);

       } catch (Exception e) {
    	   throw new TPlusException("Error in WorkItemManager workItemInfo method" +e);
    }
    return workItemData;
}
 //Hee leng
	public boolean add(WorkItemDto objDto) throws TPlusException {
		boolean success = false;
		try {
			success = objDAO.add(objDto);
		} catch (Exception e) {
			throw new TPlusException("Error in WorkItem update method" + e);
		}
		return success;
	}

	public boolean validate(WorkItemDto objDto) throws TPlusException {
		boolean rtnMessage = false;
		WorkItemDto objWIDto = (WorkItemDto) objDto;
		if (objDAO.checkExistRecord(objWIDto) > 0) {
			rtnMessage = true;
		}
		return rtnMessage;
	}
//Satya

	  /**
	   * This method is used for getting the Document List
	   */
	   public Collection getDocumentList(String claimNo) throws TPlusException {

	           Collection claimList =null;
	   try {
		     claimList = objDAO.getDocumentList(claimNo);

	       } catch (Exception e) {
	    	   throw new TPlusException("Error in WorkItemManager search method" +e);
	    }
	    return claimList;
	}


}
