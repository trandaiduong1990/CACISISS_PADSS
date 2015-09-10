
package org.transinfo.cacis.dataacess.dao.riskmanagement;

import java.util.Collection;
import java.util.Map;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.dao.BaseDAO;
import org.transinfo.cacis.dto.riskmanagement.RiskCountryDto;

public interface RiskCountryDAO extends BaseDAO
{
    public Collection search(RiskCountryDto objSearchDto, int pageNo)throws TPlusException;
    public RiskCountryDto getRiskCountryForm(long cardNo, String countryCode) throws TPlusException;
    public boolean add(RiskCountryDto objDto)throws TPlusException;
    public boolean update(RiskCountryDto objDto)throws TPlusException;    
    public Map getCityList(RiskCountryDto objDto)throws TPlusException;
    
}