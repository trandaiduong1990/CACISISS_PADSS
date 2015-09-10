
package org.transinfo.cacis.dataacess.dao.riskmanagement;

import java.util.Collection;
import java.util.Map;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.dao.BaseDAO;
import org.transinfo.cacis.dto.riskmanagement.RiskTranxSaleCashDto;

public interface RiskTranxSaleCashDAO extends BaseDAO
{
    public Collection search(RiskTranxSaleCashDto objSearchDto, int pageNo)throws TPlusException;
    public RiskTranxSaleCashDto getRiskTranxForm(int id) throws TPlusException;
    public boolean add(RiskTranxSaleCashDto objDto)throws TPlusException;
    public boolean update(RiskTranxSaleCashDto objDto)throws TPlusException;
    public Map getMcc(String issuerId,String riskId)throws TPlusException;
    public Map getSelectedMcc(String issuerId,String riskId)throws TPlusException;
}