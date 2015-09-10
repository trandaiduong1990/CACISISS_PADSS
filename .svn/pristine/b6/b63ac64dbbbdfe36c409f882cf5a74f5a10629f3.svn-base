package org.transinfo.cacis.dataacess.dao.disputemanagement;

import java.util.Collection;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.dao.BaseDAO;
import org.transinfo.cacis.dto.disputemanagement.DisputeClaimFormDto;
import org.transinfo.cacis.dto.disputemanagement.SearchDisputeClaimFormDto;


public interface DisputeClaimFormDAO extends BaseDAO {
	
	 	  
  public Collection search(SearchDisputeClaimFormDto objSearchDto,int pageNo)throws TPlusException;

  public boolean add(DisputeClaimFormDto objDto)throws TPlusException;
  
  public DisputeClaimFormDto createClaim(DisputeClaimFormDto objDto) throws TPlusException;
   
  public int checkExistrecord(DisputeClaimFormDto objDto)throws TPlusException;


}
