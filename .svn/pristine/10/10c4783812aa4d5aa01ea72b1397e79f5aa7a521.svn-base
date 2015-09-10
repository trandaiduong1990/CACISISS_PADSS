package org.transinfo.cacis.dataacess.dao.settings;

import java.util.Collection;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.dao.BaseDAO;
import org.transinfo.cacis.dto.settings.CurrencyDto;
import org.transinfo.cacis.dto.settings.CurrencyRateDto;

@SuppressWarnings("unchecked")
public interface CurrencyRateDAO extends BaseDAO {
	
	public Collection search(CurrencyRateDto objSearchDto, int pageNo)throws TPlusException;
	
	public int checkExistrecord(CurrencyRateDto objDto)throws TPlusException;
	
    public boolean add(CurrencyRateDto objDto)throws TPlusException;

    public CurrencyRateDto getCurrencyRateDto(String currId, String issuerID)throws TPlusException;

    public boolean update(CurrencyRateDto objDto)throws TPlusException;

    public boolean delete(CurrencyRateDto objDto)throws TPlusException; 

    public CurrencyDto getCurrencyDto(String currId)throws TPlusException;

}
