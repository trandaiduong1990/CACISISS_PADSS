package org.transinfo.cacis.dataacess.daoimpl.oracle.riskmanagement;

import java.util.Collection;

import org.transinfo.cacis.TPlusCodes;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.HibernetDAO.HibernetFactory;
import org.transinfo.cacis.dataacess.dao.riskmanagement.CardActivityDAO;
import org.transinfo.cacis.dataacess.daoimpl.oracle.BaseDAOImpl;
import org.transinfo.cacis.dto.customerservice.CardActivityDto;

public class CardActivityDAOImpl extends BaseDAOImpl implements
		CardActivityDAO {

	public Collection search(CardActivityDto objSearchDto,
			int pageNo) throws TPlusException {

		Collection objSearchCollection = null;
		StringBuffer sbf = new StringBuffer();
		try {

			sbf.append("select af.cardActivityId, to_char(af.dateTime,'DD/MM/YYYY HH24:MI:SS'), af.activity, ");
			sbf.append("af.stationIp, af.reason, af.userId FROM org.transinfo.cacis.dto.customerservice.CardActivityDto af ");
			sbf.append("where af.cardNumber = "+ objSearchDto.getCardNumber() + " ");

			System.out.println("objSearchDto.getFromDate() => "+objSearchDto.getStrFromDate());
			System.out.println("objSearchDto.getToDate() => "+objSearchDto.getStrToDate());
			
			if (objSearchDto.getStrFromDate() != null && !objSearchDto.getStrFromDate().equals(""))
			{
				String strFromDate = objSearchDto.getStrFromDate();
				sbf.append("and ( af.dateTime >= TO_DATE('" + strFromDate + "', 'DD/MM/YYYY')) ");
			}
			if (objSearchDto.getStrToDate() != null	&& !objSearchDto.getStrToDate().equals(""))
			{
				String strToDate = objSearchDto.getStrToDate();
				sbf.append("and (af.dateTime <= TO_DATE('" + strToDate + "', 'DD/MM/YYYY')) ");
			}
			sbf.append(" ORDER BY af.dateTime DESC ");
			objSearchCollection = getSearchList(sbf.toString(), pageNo);

		} catch (Exception e) {
			System.out
					.println("Error while retrieving the CardActivity Search Info"
							+ e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving the CardActivity Search Info"
							+ e);
		} finally {
			HibernetFactory.closeSession();
		}
		return objSearchCollection;
	}
}
