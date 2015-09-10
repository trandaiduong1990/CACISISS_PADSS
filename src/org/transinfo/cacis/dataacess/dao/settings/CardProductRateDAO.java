package org.transinfo.cacis.dataacess.dao.settings;

import java.util.Collection;
import java.util.List;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.dao.BaseDAO;
import org.transinfo.cacis.dto.settings.CardProductRateDto;
import org.transinfo.cacis.dto.settings.CardProductRateSearchDto;

@SuppressWarnings("unchecked")
public interface CardProductRateDAO extends BaseDAO {

	public Collection search(CardProductRateSearchDto objSearchDto, int pageNo)
			throws TPlusException;

	public boolean add(CardProductRateDto objDto) throws TPlusException;

	public boolean update(CardProductRateDto objDto) throws TPlusException;

	// public boolean delete(CardProductRateDto objDto)throws TPlusException;

	public CardProductRateDto getCardProductRateDto(int rateId)
			throws TPlusException;

	// public int checkExistrecord(CardProductRateDto objDto)throws
	// TPlusException;

	public List getAllRates(String cardProductId) throws TPlusException;

	public List getAllRatesExceptOne(String cardProductId, String serialNo)
			throws TPlusException;

}
