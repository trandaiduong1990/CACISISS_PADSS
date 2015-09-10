package org.transinfo.cacis.dataacess.daoimpl.oracle.cardproduction;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.transinfo.cacis.TPlusCodes;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.common.constants.CommonCodes;
import org.transinfo.cacis.dataacess.HibernetDAO.HibernetFactory;
import org.transinfo.cacis.dataacess.dao.cardproduction.CreditScoringDAO;
import org.transinfo.cacis.dataacess.daoimpl.oracle.BaseDAOImpl;
import org.transinfo.cacis.dto.cardproduction.CreditScoringDto;

public class CreditScoringDAOImpl  extends BaseDAOImpl implements CreditScoringDAO {

private Logger logger = Logger.getLogger(CreditScoringDAOImpl.class);
	
//	@Override
//	public Collection search(CreditScoringSearchDto objCreditScoringDto,
//			int pageNo) throws TPlusException {
//
//		Collection objSearchCollection = new ArrayList();
//		StringBuffer sbf = new StringBuffer();
//		
//		try {
//			
//			sbf.append("Select csd.scoreId, csd.scoreName, csd.lastUpdatedDate  ");
//			sbf.append("From CreditScoringDto csd ");
//			
//			if (!StringUtils.isBlank(objCreditScoringDto.getScoreName())) {
//				sbf.append("Where csd.scoreId='" + objCreditScoringDto.getScoreName() + "' ");
//			}
//			
//			objSearchCollection = getSearchList(sbf.toString(), pageNo);
//
//		} catch (Exception ex) {
//			logger.error(ex);
//
//			System.out.println("Error in CreditScoringDAOImpl search method : "
//					+ ex.getMessage());
//			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
//					"Error: in CreditScoringDAOImpl search  method :" + ex);
//		}
//		
//		return objSearchCollection;
//		
//	}

	@Override
	public List getAllCreditScoring() throws TPlusException {

		List<CreditScoringDto> lstCreditScoring = new ArrayList<CreditScoringDto>();
		Transaction tx = null;
		StringBuffer sbf = new StringBuffer();
		
		try {

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			sbf.append("From CreditScoringDto ");

			
			Query qry = session.createQuery(sbf.toString());
			List list = qry.list();

			for (int i = 0; i < list.size(); i++) {
				CreditScoringDto objDto = new CreditScoringDto();
				objDto = (CreditScoringDto) list.get(i);
				lstCreditScoring.add(objDto);
			}

			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.error(e);
			System.out
					.println("Error in CreditScoringDAOImpl getAllCreditScoring method : "
							+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in CreditScoringDAOImpl getAllCreditScoring method :"
							+ e);
		} finally {
			HibernetFactory.closeSession();
		}

		return lstCreditScoring;
	}

	@Override
	public CreditScoringDto getCreditScoringDetail(String scoreId)
			throws TPlusException {

		CreditScoringDto objDto = new CreditScoringDto();
		Transaction tx = null;
		StringBuffer sbf = new StringBuffer();
		
		try {

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			sbf.append("From CreditScoringDto csd ");
			sbf.append("Where csd.scoreId = '"
					+ scoreId + "' ");

			Query qry = session.createQuery(sbf.toString());
			List list = qry.list();

			if (list.size() > 0) {
				objDto = (CreditScoringDto) list.get(0);
			}

			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.error(e);
			System.out
					.println("Error in CreditScoringDAOImpl getCreditLimitProfileDetail method : "
							+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in CreditScoringDAOImpl getCreditLimitProfileDetail  method :"
							+ e);
		} finally {
			HibernetFactory.closeSession();
		}

		return objDto;
	}

	@Override
	public int checkExitsRecord(CreditScoringDto objCreditScoringDto)
			throws TPlusException {

		int resCord = 0;
		
		try {
			Session session = HibernetFactory.currentSession();
			Query qry = session
					.createQuery("Select count(*) From CreditScoringDto csd where csd.scoreId=:scoreid ");
			qry.setString("scoreid", objCreditScoringDto.getScoreId());
			List list = qry.list();
			resCord = ((Integer) list.get(0)).intValue();
			System.out.println("After CreditScoringDAOImpl checkExistrecord()"
					+ resCord);
		} catch (Exception ex) {
			logger.error(ex);
			System.out
					.println("Error in CreditScoringDAOImpl checkExist record method:"
							+ ex.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error:  Error in CreditScoringDAOImpl checkExistrecord method:"
							+ ex);
		} finally {
			HibernetFactory.closeSession();
		}
		
		return resCord;
	}

	@Override
	public boolean update(CreditScoringDto objDto) throws TPlusException {

		boolean boolUpdate = false;
		Transaction tx = null;
		
		try {
			objDto.setStatus(CommonCodes.STATUS_INACTIVE);
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			session.update(objDto);

			tx.commit();
			boolUpdate = true;

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.error(e);
			System.out.println("Error in CreditScoringDAOImpl update method : "
					+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in CreditScoringDAOImpl update  method :" + e);
		} finally {
			HibernetFactory.closeSession();
		}
		
		return boolUpdate;
	}

	@Override
	public int getTotalCreditScoring(String scoreId) throws TPlusException {

		List<CreditScoringDto> lstCreditScoring = new ArrayList<CreditScoringDto>();
		Transaction tx = null;
		StringBuffer sbf = new StringBuffer();
		int total = 0;
		
		try {

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			sbf.append("From CreditScoringDto Where creditName = '" + scoreId + "'");

			
			Query qry = session.createQuery(sbf.toString());
			List list = qry.list();

			for (int i = 0; i < list.size(); i++) {
				CreditScoringDto objDto = new CreditScoringDto();
				objDto = (CreditScoringDto) list.get(i);
				total = total + objDto.getMaxScore();
			}

			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.error(e);
			System.out
					.println("Error in CreditScoringDAOImpl getTotalCreditScoring method : "
							+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in CreditScoringDAOImpl getTotalCreditScoring method :"
							+ e);
		} finally {
			HibernetFactory.closeSession();
		}
		
		return total;
	}
}
