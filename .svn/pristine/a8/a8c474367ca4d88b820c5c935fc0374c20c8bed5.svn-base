package org.transinfo.cacis.dataacess.dao.cardproduction;

import java.util.Collection;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.dao.BaseDAO;
import org.transinfo.cacis.dto.cardproduction.CardDeliverSearchDto;
import org.transinfo.cacis.dto.cardproduction.CardsDto;

@SuppressWarnings("unchecked")
public interface CardDeliverDAO extends BaseDAO {

	public Collection search(CardDeliverSearchDto objSearchDto, int pageNo)
			throws TPlusException;

	public boolean save(CardDeliverSearchDto objSearchDto)
			throws TPlusException;

	public boolean received(CardDeliverSearchDto objSearchDto)
			throws TPlusException;

	public CardsDto getCard(String cardDeliverSerialNo) throws TPlusException;

	public boolean updateObjects(String cardDeliverSerialNo, String userId)
			throws TPlusException;

}
