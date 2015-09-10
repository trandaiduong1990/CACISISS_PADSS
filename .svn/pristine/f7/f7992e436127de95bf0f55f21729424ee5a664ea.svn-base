package org.transinfo.cacis.dataacess.dao.settings;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.dao.BaseDAO;
import org.transinfo.cacis.dto.settings.CardProductRulesDto;
import org.transinfo.cacis.dto.settings.CardProductRulesSearchDto;

@SuppressWarnings("unchecked")
public interface CardProductRulesDAO extends BaseDAO {

	public Collection search(CardProductRulesSearchDto objSearchDto, int pageNo)
			throws TPlusException;

	public boolean add(CardProductRulesDto objCardProductRulesDto) throws TPlusException;

	public boolean checkExistRecord(CardProductRulesDto objDto) throws TPlusException;

	public boolean update(CardProductRulesDto objDto) throws TPlusException;

	public CardProductRulesDto getCardProductRulesDto(String cardProductId) throws TPlusException;

	public Map getMccMasterList(String cardProductId) throws TPlusException;

}
