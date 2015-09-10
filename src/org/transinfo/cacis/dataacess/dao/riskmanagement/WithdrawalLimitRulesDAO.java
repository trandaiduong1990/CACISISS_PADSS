
package org.transinfo.cacis.dataacess.dao.riskmanagement;

import java.util.Collection;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.dao.BaseDAO;
import org.transinfo.cacis.dto.riskmanagement.WithdrawalLimitRulesDto;
import org.transinfo.cacis.dto.riskmanagement.WithdrawalLimitRulesSearchDto;



public interface WithdrawalLimitRulesDAO extends BaseDAO
{

	public Collection search(WithdrawalLimitRulesSearchDto objSearchDto,int pageNo)throws TPlusException;
	public WithdrawalLimitRulesDto getWithdrawalLimitRules(String ruleid)throws TPlusException;
    public boolean add(WithdrawalLimitRulesDto objWithdraLimRulDto)throws TPlusException;
    public boolean update(WithdrawalLimitRulesDto objWithdraLimRulDto)throws TPlusException;
    public boolean delete(WithdrawalLimitRulesDto objWithdraLimRulDto)throws TPlusException;
    public int checkExistrecord(WithdrawalLimitRulesDto objWithdraLimRulDto)throws TPlusException;
}