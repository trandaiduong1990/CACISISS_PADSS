
package org.transinfo.cacis.dataacess.dao.riskmanagement;

import java.util.Collection;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.dao.BaseDAO;
import org.transinfo.cacis.dto.riskmanagement.CardLimitsDto;
import org.transinfo.cacis.dto.riskmanagement.CardholderLimitAdjustmentDto;
import org.transinfo.cacis.dto.riskmanagement.CardholderLimitAdjustmentSearchDto;



public interface CardholderLimitAdjustmentDAO extends BaseDAO
{

	public Collection search(CardholderLimitAdjustmentSearchDto objSearchDto,int pageNo)throws TPlusException;
	public CardholderLimitAdjustmentDto getCardholderLimitAdjustment(String temporaryLimitId)throws TPlusException;
	public CardLimitsDto getCardLimits(String cardNumber)throws TPlusException;
    public boolean add(CardholderLimitAdjustmentDto objWithdraLimRulDto)throws TPlusException;
    public boolean update(CardholderLimitAdjustmentDto objWithdraLimRulDto)throws TPlusException;
    public boolean delete(CardholderLimitAdjustmentDto objWithdraLimRulDto)throws TPlusException;
    public int checkExistrecord(CardholderLimitAdjustmentDto objWithdraLimRulDto, int mode)throws TPlusException;
}