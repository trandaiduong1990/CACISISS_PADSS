package org.transinfo.cacis.controller.riskmanagement;

import java.util.Collection;
import java.util.Date;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.riskmanagement.WithdrawalLimitRulesDAO;
import org.transinfo.cacis.dto.riskmanagement.WithdrawalLimitRulesDto;
import org.transinfo.cacis.dto.riskmanagement.WithdrawalLimitRulesSearchDto;

/**
 * A Business Delegate that abstracts out the business specific calls and
 * exceptions . The web tier does not need to know about the business-specific
 * details
 */
public class WithdrawalLimitRulesManager {

	private WithdrawalLimitRulesDAO objWithdraLimRulDAO;

	public WithdrawalLimitRulesManager() throws Exception {
		objWithdraLimRulDAO = DAOFactory.getInstance()
				.getWithdrawalLimitRulesDAO();
	}

	public Collection search(WithdrawalLimitRulesSearchDto objSearchDto,
			int pageNo) throws TPlusException {

		Collection searchLst = null;

		try {
			searchLst = objWithdraLimRulDAO.search(objSearchDto, pageNo);

		} catch (Exception e) {
			System.out.println("Error while search operation" + e);
			throw new TPlusException(
					"Error in WithdrawalLimitRules search method" + e);
		}
		return searchLst;

	}

	public WithdrawalLimitRulesDto get(String ruleId) throws TPlusException {
		WithdrawalLimitRulesDto WithdraLimRulDto = null;
		try {
			WithdraLimRulDto = objWithdraLimRulDAO
					.getWithdrawalLimitRules(ruleId);
		} catch (Exception e) {
			throw new TPlusException("Error in WithdrawalLimitRules get method"
					+ e);
		}
		return WithdraLimRulDto;
	}

	public boolean add(WithdrawalLimitRulesDto objWithdraLimRulDto)
			throws TPlusException {
		boolean success = false;
		Date today = new Date();
		objWithdraLimRulDto.setLastUpdatedDate(today);
		try {
			success = objWithdraLimRulDAO.add(objWithdraLimRulDto);
		} catch (Exception e) {
			throw new TPlusException("Error in WithdrawalLimitRules Add method"
					+ e);
		}
		return success;
	}

	public boolean update(WithdrawalLimitRulesDto objWithdraLimRulDto)
			throws TPlusException {
		boolean success = false;
		Date today = new Date();
		objWithdraLimRulDto.setLastUpdatedDate(today);
		try {
			success = objWithdraLimRulDAO.update(objWithdraLimRulDto);
		} catch (Exception e) {
			throw new TPlusException(
					"Error in WithdrawalLimitRules update method" + e);
		}
		return success;
	}

	public boolean delete(WithdrawalLimitRulesDto objWithdraLimRulDto)
			throws TPlusException {
		boolean success = false;
		try {
			success = objWithdraLimRulDAO.delete(objWithdraLimRulDto);
		} catch (Exception e) {
			throw new TPlusException(
					"Error in WithdrawalLimitRules delete method" + e);
		}
		return success;
	}

	public boolean validate(Object obj, int mode) throws TPlusException {
		boolean rtnMessage = false;
		WithdrawalLimitRulesDto objWithdraLimRulDto = (WithdrawalLimitRulesDto) obj;
		System.out.println(objWithdraLimRulDto.getCardProductId());
		if ((mode == 0 || mode == 1)
				&& objWithdraLimRulDAO.checkExistrecord(objWithdraLimRulDto) > 0) {
			rtnMessage = true;
		}
		return rtnMessage;
	}

}
