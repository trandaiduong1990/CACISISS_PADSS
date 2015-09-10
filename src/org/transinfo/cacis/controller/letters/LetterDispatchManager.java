package org.transinfo.cacis.controller.letters;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.transinfo.cacis.TPlusCodes;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.HibernetDAO.HibernetFactory;
import org.transinfo.cacis.dataacess.dao.letters.LetterApplMapDAO;
import org.transinfo.cacis.dataacess.dao.letters.LetterDispatchDAO;
import org.transinfo.cacis.dto.letters.DispatchLetterSearchDto;

public class LetterDispatchManager {

	private Logger logger = Logger.getLogger(LetterDispatchManager.class);
	private LetterDispatchDAO objDAO;
	
	public LetterDispatchManager() throws Exception {
		objDAO = DAOFactory.getInstance().getLetterDispatchDAO();
	}
	
	public Collection getLetterDispByLetterId(String letterId) throws TPlusException {
		
		Collection objLetterDispCollection = new ArrayList();
		StringBuffer sbf = new StringBuffer();
		Transaction tx = null;
		
		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			
			sbf.append("From DispatchLetterSearchDto dls ");
			sbf.append("Where dls.status=1 And dls.letterId = '" + letterId + "'");
			
			Query qry = session.createQuery(sbf.toString());
			List list = qry.list();

			for (int i = 0; i < list.size(); i++) {
				DispatchLetterSearchDto objDto = new DispatchLetterSearchDto();
				objDto = (DispatchLetterSearchDto) list.get(i);
				objLetterDispCollection.add(objDto);
			}

		} catch (Exception ex) {
			logger.error(ex);

			System.out.println("Error in LetterDispatchDAOImpl getLetterDispByLetterId method : "
					+ ex.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in LetterDispatchDAOImpl getLetterDispByLetterId  method :" + ex);
		}
		
		return objLetterDispCollection;
	}

}
