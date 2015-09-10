package org.transinfo.cacis.dataacess.daoimpl.oracle.customerservice;

import java.util.Collection;

import org.transinfo.cacis.TPlusCodes;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.HibernetDAO.HibernetFactory;
import org.transinfo.cacis.dataacess.dao.customerservice.CardStatusDAO;
import org.transinfo.cacis.dataacess.daoimpl.oracle.BaseDAOImpl;

@SuppressWarnings("unchecked")
public class CardStatusDAOImpl extends BaseDAOImpl implements CardStatusDAO {

	public Collection list(String cardNo, int pageNo) throws TPlusException {

		Collection objSearchCollection = null;
		StringBuffer sbf = new StringBuffer();

		try {

			sbf.append("select ");
			sbf.append("csrdto.statusNo, csrdto.remarks, csrdto.userId, ");
			sbf.append("to_char(csrdto.updatedDate,'dd-MM-yyyy HH24:mi:ss'), csdto.description ");
			sbf.append("from CardStatusRemarksDto csrdto, CardStatusDto csdto ");
			sbf.append("where csrdto.statusNo = csdto.cardStatusId ");
			sbf.append("and csrdto.cardNo = '" + cardNo + "' ");
			sbf.append("order by csrdto.updatedDate desc ");

			objSearchCollection = getSearchList(sbf.toString(), pageNo);

		} catch (Exception e) {
			System.out.println("Error while get list " + e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving the Card status remarks list " + e);
		} finally {
			HibernetFactory.closeSession();
		}

		return objSearchCollection;
	}

}
