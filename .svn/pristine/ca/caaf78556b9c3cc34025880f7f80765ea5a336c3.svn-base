package org.transinfo.cacis.dataacess.dao.cardproduction;

import java.util.Collection;
import java.util.Date;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.dao.BaseDAO;
import org.transinfo.cacis.dto.cardproduction.ApplicationProcessDto;
import org.transinfo.cacis.dto.cardproduction.CardEmbossingDto;
import org.transinfo.cacis.dto.cardproduction.CardEmbossingSearchDto;
import org.transinfo.cacis.dto.cardproduction.CardsDto;

@SuppressWarnings("unchecked")
public interface CardEmbossingDAO extends BaseDAO {

	public Collection search(CardEmbossingSearchDto objSearchDto, int pageNo)
			throws TPlusException;

	public boolean save(CardEmbossingSearchDto objSearchDto)
			throws TPlusException;
	
	public CardsDto getCard(String embossSerialNo)throws TPlusException;

	public boolean updateObjects(String embossSerialNo, String userId, Date embossDate)
			throws TPlusException;
	
	public ApplicationProcessDto getCustomerByCusId(String custId)throws TPlusException;
	
	public CardEmbossingDto getCardEmboss(String embossSerialNo)throws TPlusException;

	public Collection searchReplacement(CardEmbossingSearchDto objSearchDto, int pageNo)
			throws TPlusException;

	public boolean updateObjectsReplacement(String embossSerialNo, String userId, Date embossDate)
			throws TPlusException;

	public Collection searchRenewal(CardEmbossingSearchDto objSearchDto, int pageNo)
			throws TPlusException;

	public boolean updateObjectsRenewal(String embossSerialNo, String userId, Date embossDate)
			throws TPlusException;
}
