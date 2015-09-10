package org.transinfo.cacis.dataacess.dao.settings;

import java.util.ArrayList;
import java.util.Collection;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.dao.BaseDAO;
import org.transinfo.cacis.dto.settings.CardProductLimitDto;
import org.transinfo.cacis.dto.settings.CardProductLimitSearchDto;

@SuppressWarnings("unchecked")
public interface CardProductLimitDAO extends BaseDAO {

	public Collection search(CardProductLimitSearchDto objSearchDto, int pageNo)
			throws TPlusException;

	// remove this
	// public HashMap searchList(CardProductSearchDto objSearchDto)throws
	// TPlusException;

	public boolean add(CardProductLimitDto objCardProduct)
			throws TPlusException;

	public boolean update(CardProductLimitDto objCardProduct)
			throws TPlusException;

	public boolean delete(CardProductLimitDto objCardProduct)
			throws TPlusException;

	public CardProductLimitDto getCardProductLimitDto(String CardProductId)
			throws TPlusException;

	public ArrayList cardPromotionList() throws TPlusException;

	public int checkExistrecord(CardProductLimitDto objCardProduct)
			throws TPlusException;

}
