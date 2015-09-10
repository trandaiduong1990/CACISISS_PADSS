package org.transinfo.cacis.dataacess.dao.settings;

import java.util.Collection;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.dao.BaseDAO;
import org.transinfo.cacis.dto.settings.CardTypeDto;
import org.transinfo.cacis.dto.settings.CardTypeSearchDto;

@SuppressWarnings("unchecked")
public interface CardTypeDAO extends BaseDAO {

	public Collection search(CardTypeSearchDto objSearchDto, int PageNo)
			throws TPlusException;

	public boolean add(CardTypeDto objDto) throws TPlusException;

	public boolean update(CardTypeDto objDto) throws TPlusException;

	public boolean delete(CardTypeDto objDto) throws TPlusException;

	public CardTypeDto getCardTypeDto(String cardTypeId) throws TPlusException;

	public int checkExistrecord(CardTypeDto objDto) throws TPlusException;

	public boolean hasCardProducts(String cardTypeId) throws TPlusException;

}
