package org.transinfo.cacis.dataacess.daoimpl.oracle.letters;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.transinfo.cacis.TPlusCodes;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.common.constants.CommonCodes;
import org.transinfo.cacis.controller.settings.CardProductManager;
import org.transinfo.cacis.dataacess.HibernetDAO.HibernetFactory;
import org.transinfo.cacis.dataacess.dao.letters.LetterApplMapDAO;
import org.transinfo.cacis.dataacess.daoimpl.oracle.BaseDAOImpl;
import org.transinfo.cacis.dto.cardproduction.CreditScoringDto;
import org.transinfo.cacis.dto.letters.LetterApplMapDto;
import org.transinfo.cacis.formbean.letters.LetterApplMapSetupForm;

public class LetterApplMapDAOImpl extends BaseDAOImpl implements LetterApplMapDAO {

	private Logger logger = Logger.getLogger(LetterApplMapDAOImpl.class);
	
	@Override
	public List getAllLetterApplMap() throws TPlusException {

		List<LetterApplMapDto> listLetterApplMap = new ArrayList<LetterApplMapDto>();
		Transaction tx = null;
		StringBuffer sbf = new StringBuffer();
		
		try {

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			sbf.append("From LetterApplMapDto Order By applModule ");
			
			Query qry = session.createQuery(sbf.toString());
			List list = qry.list();

			for (int i = 0; i < list.size(); i++) {
				LetterApplMapDto objDto = new LetterApplMapDto();
				objDto = (LetterApplMapDto) list.get(i);
				listLetterApplMap.add(objDto);
			}

			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.error(e);
			System.out
					.println("Error in LetterApplMapDAOImpl getAllLetterApplMap method : "
							+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in LetterApplMapDAOImpl getAllLetterApplMap method :"
							+ e);
		} finally {
			HibernetFactory.closeSession();
		}

		return listLetterApplMap;
	}

	@Override
	public Boolean boolUpdate(List letterApplMapDtoList) throws TPlusException {

		boolean boolUpdate = false;
		Transaction tx = null;
		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			int count;

			for(Iterator<LetterApplMapDto> i = letterApplMapDtoList.iterator(); i.hasNext();) {
				
				LetterApplMapDto objDto = i.next();
				String sql = "UPDATE LetterApplMapDto SET status =:status, letterTemplate =:lettercode WHERE APPL_ACTION =:applaction";
				count = session.createQuery(sql).setString("status", objDto.getStatus())
						.setString("lettercode", objDto.getLetterTemplate())
						.setString("applaction", objDto.id.getApplAction())
						.executeUpdate();
				if (count <= 0) {
					throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
							"Error: in LetterApplMapDAOImpl update  method : Update fail");
				}
				
			}
			tx.commit();
			
			boolUpdate = true;

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.error(e);
			System.out.println("Error in LetterApplMapDAOImpl update method : "
					+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in LetterApplMapDAOImpl update  method :" + e);
		} finally {
			HibernetFactory.closeSession();
		}
		
		return boolUpdate;
	}

}
