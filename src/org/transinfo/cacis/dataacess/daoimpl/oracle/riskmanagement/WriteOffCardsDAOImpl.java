package org.transinfo.cacis.dataacess.daoimpl.oracle.riskmanagement;

import java.util.Collection;

import org.transinfo.cacis.TPlusCodes;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.HibernetDAO.HibernetFactory;
import org.transinfo.cacis.dataacess.dao.riskmanagement.WriteOffCardsDAO;
import org.transinfo.cacis.dataacess.daoimpl.oracle.BaseDAOImpl;
import org.transinfo.cacis.dto.riskmanagement.WriteOffCardsDto;

public class WriteOffCardsDAOImpl extends BaseDAOImpl implements
		WriteOffCardsDAO {

	public Collection search(WriteOffCardsDto objSearchDto,
			int pageNo) throws TPlusException {

		Collection objSearchCollection = null;
		StringBuffer sbf = new StringBuffer();
		try {

			sbf.append("select wr.cardNumber, to_char(wr.writeOffDate,'DD/MM/YYYY HH24:MI:SS'), ca.creditLimit, ");
			sbf.append("ca.previousBalance FROM WriteOffCardsDto wr, CustomerAccountDto ca, CardsDto c ");
			sbf.append("where wr.cardNumber = c.cardNumber ");
			sbf.append("and ca.accountId = c.accountId ");
			System.out.println("Long.toString(objSearchDto.getCardNumber()) => "+Long.toString(objSearchDto.getCardNumber()));
			if (Long.toString(objSearchDto.getCardNumber()) != null && !Long.toString(objSearchDto.getCardNumber()).equals("0"))
			{
				sbf.append("and wr.cardNumber = "+ objSearchDto.getCardNumber() + " ");
			}
			System.out.println("objSearchDto.getFromDate() => "+objSearchDto.getStrFromDate());
			System.out.println("objSearchDto.getToDate() => "+objSearchDto.getStrToDate());
			
			if (objSearchDto.getStrFromDate() != null && !objSearchDto.getStrFromDate().equals(""))
			{
				String strFromDate = objSearchDto.getStrFromDate();
				sbf.append("and ( wr.writeOffDate >= TO_DATE('" + strFromDate + "', 'DD/MM/YYYY')) ");
			}
			if (objSearchDto.getStrToDate() != null	&& !objSearchDto.getStrToDate().equals(""))
			{
				String strToDate = objSearchDto.getStrToDate();
				sbf.append("and (wr.writeOffDate <= TO_DATE('" + strToDate + "', 'DD/MM/YYYY')) ");
			}
			objSearchCollection = getSearchList(sbf.toString(), pageNo);

		} catch (Exception e) {
			System.out
					.println("Error while retrieving the WriteOffCards Search Info"
							+ e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving the WriteOffCards Search Info"
							+ e);
		} finally {
			HibernetFactory.closeSession();
		}
		return objSearchCollection;
	}
}
