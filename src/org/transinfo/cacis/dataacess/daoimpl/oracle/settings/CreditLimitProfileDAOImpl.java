package org.transinfo.cacis.dataacess.daoimpl.oracle.settings;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.transinfo.cacis.TPlusCodes;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.common.constants.CommonCodes;
import org.transinfo.cacis.dataacess.HibernetDAO.HibernetFactory;
import org.transinfo.cacis.dataacess.dao.settings.CreditLimitProfileDAO;
import org.transinfo.cacis.dataacess.daoimpl.oracle.BaseDAOImpl;
import org.transinfo.cacis.dto.cardproduction.CreditScoringDto;
import org.transinfo.cacis.dto.settings.CardProductDto;
import org.transinfo.cacis.dto.settings.CreditLimitProfileDto;
import org.transinfo.cacis.dto.settings.CreditLimitProfileSearchDto;

public class CreditLimitProfileDAOImpl extends BaseDAOImpl implements CreditLimitProfileDAO {
	
	private Logger logger = Logger.getLogger(CreditLimitProfileDAOImpl.class);
	
//	@Override
//	public String getTotalScoringPoint() throws TPlusException {
//		
//		String result = "";
//		CreditScoringDto objDto = null;
//		Transaction tx = null;
//		StringBuffer sbf = new StringBuffer();
//		try {
//
//			Session session = HibernetFactory.currentSession();
//			tx = session.beginTransaction();
//
//			sbf.append("Select SUM(csd.maxScore)");
//			sbf.append("From CreditScoringDto csd ");
//			sbf.append("");
//
//			Query qry = session.createQuery(sbf.toString());
//			List list = qry.list();
//
//			if (list.size() > 0) {
//				int count = ((Integer) list.get(0)).intValue();
//				result = String.valueOf(count);
//			}
//
//			tx.commit();
//		} catch (Exception e) {
//			if (tx != null) {
//				tx.rollback();
//			}
//			logger.error(e);
//			System.out
//					.println("Error in CreditLimitProfileDAOImpl getTotalScoringPoint method : "
//							+ e.getMessage());
//			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
//					"Error: in CreditLimitProfileDAOImpl getTotalScoringPoint  method :"
//							+ e);
//		} finally {
//			HibernetFactory.closeSession();
//		}
//
//		return result;
//	}

	@Override
	public CardProductDto getLowerUpperLimit(String cardProductId) throws TPlusException {

		CardProductDto objDto = new CardProductDto();
		Transaction tx = null;
		StringBuffer sbf = new StringBuffer();
		
		try {

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			sbf.append("From CardProductDto cpd ");
			sbf.append("Where cpd.cardProductId = '"
					+ cardProductId + "' ");

			Query qry = session.createQuery(sbf.toString());
			List list = qry.list();

			if (list.size() > 0) {
				objDto = (CardProductDto) list.get(0);
			}

			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.error(e);
			System.out
					.println("Error in CreditLimitProfileDAOImpl getLowerUpperLimit method : "
							+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in CreditLimitProfileDAOImpl getLowerUpperLimit  method :"
							+ e);
		} finally {
			HibernetFactory.closeSession();
		}

		return objDto;
	}

	@Override
	public int checkExitsRecord(CreditLimitProfileDto objCreditLimitProfile)
			throws TPlusException {

		int resCord = 0;
		
		try {
			Session session = HibernetFactory.currentSession();
			Query qry = session
					.createQuery("Select count(*) From CreditLimitProfileDto clpd where clpd.description=:sno ");
			qry.setString("sno", objCreditLimitProfile.getDescription());
			List list = qry.list();
			resCord = ((Integer) list.get(0)).intValue();
			System.out.println("After CreditLimitProfileDAOImpl checkExistrecord()"
					+ resCord);
		} catch (Exception ex) {
			logger.error(ex);
			System.out
					.println("Error in CreditLimitProfileDAOImpl checkExist record method:"
							+ ex.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error:  Error in CreditLimitProfileDAOImpl checkExistrecord method:"
							+ ex);
		} finally {
			HibernetFactory.closeSession();
		}
		
		return resCord;
	}

	@Override
	public boolean create(CreditLimitProfileDto objCreditLimitProfileDto) throws TPlusException {

		boolean boolCreate = false;
		Transaction tx = null;
		
		objCreditLimitProfileDto.setStatus("A");
		
		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			session.save(objCreditLimitProfileDto);
			
			tx.commit();
			boolCreate = true;
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.error(e);
			System.out.println("Error in CreditLimitProfileDAOImpl add method : "
					+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in CreditLimitProfileDAOImpl add  method :" + e);
		} finally {
			HibernetFactory.closeSession();
		}
		
		return boolCreate;
	}

	@Override
	public CreditLimitProfileSearchDto search(CreditLimitProfileSearchDto objDto, int pageNo)
			throws TPlusException {

		Collection objSearchCollection = new ArrayList();
		int totalCount = 0;
		
		StringBuffer sbf = new StringBuffer();
		StringBuffer sbfSelect = new StringBuffer();
		StringBuffer sbfCount = new StringBuffer();
		
		try {
			
			sbfSelect.append("Select clpd.sNo, cpd.cardProductName, clpd.scoreId, clpd.lastUpdatedDate  ");
			
			sbfCount.append("Select ");
			sbfCount.append("Count(clpd.sNo) ");
			
			sbf.append("From CreditLimitProfileDto clpd, CardProductDto cpd ");
			sbf.append("Where clpd.cardProduct.cardProductId = cpd.cardProductId ");
			sbf.append("And clpd.status='A' ");
			
			if (!StringUtils.isBlank(objDto.getCardProductId())) {
				sbf.append("And clpd.cardProduct.cardProductId = '" + objDto.getCardProductId() + "' ");
			}
			
			if (!StringUtils.isBlank(objDto.getScoreId())) {
				sbf.append("And clpd.scoreId = '" + objDto.getScoreId() + "' ");
			}
			
			objSearchCollection = getSearchTranxList(sbfSelect.append(sbf).toString(), pageNo);
			objDto.setSearchList(objSearchCollection);
			
			totalCount = getSearchTotalCount(sbfCount.append(sbf).toString());
			objDto.setTotalCount(totalCount);

		} catch (Exception ex) {
			logger.error(ex);

			System.out.println("Error in CreditLimitProfileDAOImpl search method : "
					+ ex.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in CreditLimitProfileDAOImpl search  method :" + ex);
		}
		
		return objDto;
	}

	@Override
	public CreditLimitProfileDto getCardRiskProfileDetail(String sNo)
			throws TPlusException {

		CreditLimitProfileDto objDto = new CreditLimitProfileDto();
		Transaction tx = null;
		StringBuffer sbf = new StringBuffer();
		
		try {

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			sbf.append("From CreditLimitProfileDto clpd ");
			sbf.append("Where clpd.sNo = '" + sNo + "' ");

			Query qry = session.createQuery(sbf.toString());
			List list = qry.list();

			if (list.size() > 0) {
				objDto = (CreditLimitProfileDto) list.get(0);
			}

			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.error(e);
			System.out
					.println("Error in CreditLimitProfileDAOImpl getCardRiskProfileDetail method : "
							+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in CreditLimitProfileDAOImpl getCardRiskProfileDetail  method :"
							+ e);
		} finally {
			HibernetFactory.closeSession();
		}

		return objDto;
	}

	@Override
	public boolean update(CreditLimitProfileDto objDto) throws TPlusException {

		boolean boolUpdate = false;
		Transaction tx = null;
		try {
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
			System.out.println("Error in CreditLimitProfileDAOImpl update method : "
					+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in CreditLimitProfileDAOImpl update  method :" + e);
		} finally {
			HibernetFactory.closeSession();
		}
		
		return boolUpdate;
	}

	@Override
	public boolean delete(CreditLimitProfileDto objDto) throws TPlusException {

		boolean boolDelete = false;
		Transaction tx = null;
		
		objDto.setStatus(CommonCodes.GL_DEBIT);
		
		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			String sql = "UPDATE CreditLimitProfileDto SET status=:status WHERE sNo=:sno";

			int count = session.createQuery(sql).setString("sno",
					objDto.getsNo()).setString("status",
					objDto.getStatus()).executeUpdate();

			tx.commit();
			if (count > 0)
				boolDelete = true;

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.error(e);
			System.out.println("Error in CreditLimitProfileDAOImpl delete method : "
					+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in CreditLimitProfileDAOImpl delete  method :" + e);
		} finally {
			HibernetFactory.closeSession();
		}
		
		return boolDelete;
	}

	@Override
	public int checkUniqueRecord(CreditLimitProfileDto objDto)
			throws TPlusException {
		
		int resCord = 0;
		
		try {
			Session session = HibernetFactory.currentSession();
			
			Query qry = session
					.createQuery("Select count(*) From CreditLimitProfileDto clpd where clpd.scoreId=:scoreid and clpd.cardProduct.cardProductId=:cardproductid");
			qry.setString("scoreid", objDto.getScoreId());
			qry.setString("cardproductid", objDto.getCardProductId());
			List list = qry.list();
			resCord = ((Integer) list.get(0)).intValue();
			System.out.println("After CreditLimitProfileDAOImpl checkUniqueRecord()"
					+ resCord);
		} catch (Exception ex) {
			logger.error(ex);
			System.out
					.println("Error in CreditLimitProfileDAOImpl checkUniqueRecord method:"
							+ ex.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error:  Error in CreditLimitProfileDAOImpl checkUniqueRecord method:"
							+ ex);
		} finally {
			HibernetFactory.closeSession();
		}
		
		return resCord;

	}
	
}
