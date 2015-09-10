
package org.transinfo.cacis.dataacess.dao.riskmanagement;

import java.util.Collection;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.dao.BaseDAO;
import org.transinfo.cacis.dto.riskmanagement.RiskTranxPeriodDto;

public interface RiskTranxPeriodDAO extends BaseDAO
{
    public Collection search(RiskTranxPeriodDto objSearchDto, int pageNo)throws TPlusException;
    public RiskTranxPeriodDto getRiskTranxPeriodForm(String issuerId, int period) throws TPlusException; 
    public boolean add(RiskTranxPeriodDto objDto)throws TPlusException;
    public boolean update(RiskTranxPeriodDto objDto)throws TPlusException;   
    
}