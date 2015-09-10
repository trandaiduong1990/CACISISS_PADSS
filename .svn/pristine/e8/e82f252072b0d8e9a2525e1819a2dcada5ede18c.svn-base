package org.transinfo.cacis.dataacess.dao.settings;

import java.util.Collection;
import java.util.Map;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.dao.BaseDAO;
import org.transinfo.cacis.dto.settings.CardPromotionDto;
import org.transinfo.cacis.dto.settings.CardPromotionSearchDto;

public interface  CardPromotionDAO extends BaseDAO{
	 	  
	    public Collection search(CardPromotionSearchDto objSearchDto, int pageNo)throws TPlusException;
	   
	    public boolean add (CardPromotionDto objDto)throws TPlusException;

	    public boolean update (CardPromotionDto objDto)throws TPlusException;

	    public boolean delete (CardPromotionDto objDto)throws TPlusException;

	    public CardPromotionDto getCardPromotionDto(String promotionId)throws TPlusException;
	    
	    public Map cardPromotionListData()throws TPlusException;

	    public int checkExistrecord(CardPromotionDto objDto)throws TPlusException;



}
