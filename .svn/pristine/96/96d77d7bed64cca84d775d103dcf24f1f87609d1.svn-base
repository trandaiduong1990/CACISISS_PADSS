package org.transinfo.cacis.dataacess.daoimpl.oracle.help;

import java.util.Collection;

import org.transinfo.cacis.TPlusCodes;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.HibernetDAO.HibernetFactory;
import org.transinfo.cacis.dataacess.dao.help.HelpDAO;
import org.transinfo.cacis.dataacess.daoimpl.oracle.BaseDAOImpl;
import org.transinfo.cacis.dto.help.HelpDto;

public class HelpDAOImpl extends BaseDAOImpl implements
		HelpDAO {

	public Collection search(HelpDto objSearchDto, int pageNo)
			throws TPlusException {
		Collection objSearchCollection = null;
		StringBuffer sbf = new StringBuffer();
		try {

			sbf
					.append("SELECT af.seqNo, af.id.fieldName, af.fieldDesc, af.screenName ");
			sbf
					.append(" FROM  HelpDto af ");

			if (objSearchDto.id.getScreenId() != null
					&& !objSearchDto.id.getScreenId().equals("")) {
				sbf.append("where af.id.screenId = '"
						+ objSearchDto.id.getScreenId() + "' ");
			}

			sbf.append(" order by af.seqNo");

			objSearchCollection = getSearchList(sbf.toString(), pageNo);

		} catch (Exception e) {
			System.out
					.println("Error while retrieving the Help Search Info"
							+ e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving the Help Search Info"
							+ e);
		} finally {
			HibernetFactory.closeSession();
		}
		return objSearchCollection;
	}
}
