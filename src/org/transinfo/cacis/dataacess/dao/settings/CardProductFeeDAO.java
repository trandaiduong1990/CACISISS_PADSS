package org.transinfo.cacis.dataacess.dao.settings;

import java.util.Collection;
import java.util.List;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.dao.BaseDAO;
import org.transinfo.cacis.dto.settings.CardProductFeeDto;
import org.transinfo.cacis.dto.settings.CardProductFeeSearchDto;

@SuppressWarnings("unchecked")
public interface CardProductFeeDAO extends BaseDAO {

	public Collection search(CardProductFeeSearchDto objSearchDto, int pageNo)
			throws TPlusException;

	// public Collection search(CardProductFeeDto objDto,int pageNo)throws
	// TPlusException;

	public boolean add(CardProductFeeDto objDto) throws TPlusException;

	public boolean update(CardProductFeeDto objDto) throws TPlusException;

	// public boolean delete(CardProductFeeDto objDto)throws TPlusException;

	public CardProductFeeDto getCardProductFeeDto(int feeId)
			throws TPlusException;

	public CardProductFeeDto getCardProductFeeByProductId(String cardProductId)
			throws TPlusException;

	// public int checkExistrecord(CardProductFeeDto objDto)throws
	// TPlusException;

	public List getAllFees(String cardProductId) throws TPlusException;

	public List getAllFeesExceptOne(String cardProductId, String serialNo)
			throws TPlusException;
}
