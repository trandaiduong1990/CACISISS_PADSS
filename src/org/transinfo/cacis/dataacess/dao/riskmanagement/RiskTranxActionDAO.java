
package org.transinfo.cacis.dataacess.dao.riskmanagement;

import java.util.Collection;
import java.util.Map;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.dao.BaseDAO;
import org.transinfo.cacis.dto.riskmanagement.RiskTranxActionDto;

public interface RiskTranxActionDAO extends BaseDAO
{
    public Collection search(RiskTranxActionDto objSearchDto, int pageNo)throws TPlusException;
    public RiskTranxActionDto getRiskActionForm(String riskId) throws TPlusException;
    public boolean add(RiskTranxActionDto objDto)throws TPlusException;
    public boolean update(RiskTranxActionDto objDto)throws TPlusException;
    public Map getRiskId()throws TPlusException;
   
}