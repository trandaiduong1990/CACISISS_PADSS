package org.transinfo.cacis.dataacess.daoimpl.oracle.settings;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.transinfo.cacis.TPlusCodes;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.HibernetDAO.HibernetFactory;
import org.transinfo.cacis.dataacess.dao.settings.CardProductRulesDAO;
import org.transinfo.cacis.dataacess.daoimpl.oracle.BaseDAOImpl;
import org.transinfo.cacis.dto.settings.CardProductRulesDto;
import org.transinfo.cacis.dto.settings.CardProductRulesSearchDto;
import org.transinfo.cacis.dto.settings.MCCMasterDto;

@SuppressWarnings("unchecked")
public class CardProductRulesDAOImpl extends BaseDAOImpl implements CardProductRulesDAO {

	private Logger logger = Logger.getLogger(CardProductRulesDAOImpl.class);

	/*
	 * This method is used for getting the CardproductTypeList
	 */
	public Collection search(CardProductRulesSearchDto objCardProductRulesSearchDto, int pageNo)
			throws TPlusException {

		Collection objSearchCollection = new ArrayList();
		int totalCount;
		StringBuffer sbSelect = new StringBuffer();
		StringBuffer sbCount = new StringBuffer();
		StringBuffer sb = new StringBuffer();
		try {
			sbSelect.append("select cpr.cardProductId, cp.cardProductName, to_char(cpr.updatedDate,'dd-MM-yyyy HH24:mi:ss') ");
			sbCount.append("select count(*) ");
			sb.append("From CardProductDto cp, CardProductRulesDto cpr ");
			sb.append("where cp.issuerId = '" + objCardProductRulesSearchDto.getIssuerId() + "' ");
			sb.append("and cp.cardProductId = cpr.cardProductId ");
			
			if (objCardProductRulesSearchDto.getCardProductId() != null
					&& !objCardProductRulesSearchDto.getCardProductId().equals("")) {
				sb.append("and cpr.cardProductId = '" + objCardProductRulesSearchDto.getCardProductId() + "' ");
			}

			objSearchCollection = getSearchList((sbSelect.append(sb)).toString(), pageNo);
			objCardProductRulesSearchDto.setSearchList(objSearchCollection);

			totalCount = getSearchTotalCount((sbCount.append(sb)).toString());
			objCardProductRulesSearchDto.setTotalCount(totalCount);
		} catch (Exception e) {
			logger.error(e);

			System.out.println("Error in CardProductRulesDAOImpl search method : "
					+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in CardProductRulesDAOImpl search  method :" + e);

		}
		return objSearchCollection;
	}

	public boolean add(CardProductRulesDto objCardProductRulesDto) throws TPlusException {
		boolean bolExecute = false;
		Transaction tx = null;
		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			session.save(objCardProductRulesDto);
			tx.commit();
			bolExecute = true;
		}catch (Exception e) {
				if (tx != null) {
					tx.rollback();
				}
				throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
						"Error: in CardProductRulesDAOImpl add method" + e);
			} finally {
				HibernetFactory.closeSession();
			}
			return bolExecute;
	}
	
	//check CardProductRulesDto is exist or not
	public boolean checkExistRecord(CardProductRulesDto objCardProductRulesDto) throws TPlusException {

		boolean exist = false;
		CardProductRulesDto objDto = null;
		Transaction tx = null;
		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			objDto = (CardProductRulesDto) session.get(CardProductRulesDto.class, objCardProductRulesDto.getCardProductId());
			tx.commit();
			
			if(objDto != null) {
				exist = true;
			}
		}
		catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.out
					.println("Error in CardProductRulesDAOImpl checkExistRecord method:"
							+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error:  Error in CardProductRulesDAOImpl checkExistRecord method:"	+ e);
		}

		finally {
			HibernetFactory.closeSession();
		}

		return exist;

	}

	public boolean update(CardProductRulesDto objDto) throws TPlusException {
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
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"CardProductRulesDAOImpl update  method :" + e);
		} finally {
			HibernetFactory.closeSession();
		}

		return boolUpdate;
	}

	@Override
	public CardProductRulesDto getCardProductRulesDto(String cardProductId)
			throws TPlusException {
		CardProductRulesDto objDto = null;
		Transaction tx = null;
		try {

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			objDto = (CardProductRulesDto) session.get(CardProductRulesDto.class, cardProductId);
			
			if(objDto != null) {
				Map selectList = new TreeMap();
				String[] mccList = null;
				Query qry;
				if (objDto.getOlMccList() != null) {
					mccList = objDto.getOlMccList().split(", ");
					qry = session.createQuery("from MCCMasterDto m where m.mcc in (:mccList)");
					qry.setParameterList("mccList", mccList);
					List listDocs = qry.list();
					for (Iterator it = listDocs.iterator(); it.hasNext();) {
						MCCMasterDto objMccMasterDto = new MCCMasterDto();
						objMccMasterDto = (MCCMasterDto) it.next();
						selectList.put(objMccMasterDto.getMcc(), objMccMasterDto.getMccName());
					}
				}
				tx.commit();
				objDto.setSelectedListSet(selectList);
			}
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.error(e);
			System.out
					.println("Error in CardProductRulesDAOImpl getCardProductDto method : "
							+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in CardProductRulesDAOImpl getCardProductDto  method :"
							+ e);
		} finally {
			HibernetFactory.closeSession();
		}

		return objDto;
	}
	
	public Map getMccMasterList(String cardProductId) throws TPlusException {
		Map mccMasterList = new TreeMap();
		Transaction tx = null;
		CardProductRulesDto objDto = null;
		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			if(null != cardProductId && !cardProductId.equals("")) {
				objDto = (CardProductRulesDto) session.get(CardProductRulesDto.class, cardProductId);
			}
			String query = "from MCCMasterDto m ";
			Query qry;
			if (objDto != null) {
				String[] mccList = null;
				if (objDto.getOlMccList() != null) {
					mccList = objDto.getOlMccList().split(", ");
					query += "where m.mcc not in (:mccList)";
					qry = session.createQuery(query);
					qry.setParameterList("mccList", mccList);
					List listDocs = qry.list();
					
					for (Iterator it = listDocs.iterator(); it.hasNext();) {
						MCCMasterDto objMccMasterDto = new MCCMasterDto();
						objMccMasterDto = (MCCMasterDto) it.next();
						mccMasterList.put(objMccMasterDto.getMcc(),
								objMccMasterDto.getMccName());
					}
				} else {
					qry = session.createQuery(query);
					List listDocs = qry.list();
					for (Iterator it = listDocs.iterator(); it.hasNext();) {
						MCCMasterDto objMccMasterDto = new MCCMasterDto();
						objMccMasterDto = (MCCMasterDto) it.next();
						mccMasterList.put(objMccMasterDto.getMcc(),
								objMccMasterDto.getMccName());
					}
				}
			} else {
				qry = session.createQuery(query);
				List listDocs = qry.list();
				for (Iterator it = listDocs.iterator(); it.hasNext();) {
					MCCMasterDto objMccMasterDto = new MCCMasterDto();
					objMccMasterDto = (MCCMasterDto) it.next();
					mccMasterList.put(objMccMasterDto.getMcc(),
							objMccMasterDto.getMccName());
				}
			}
			tx.commit();
		} catch (Exception e) {
			logger.error(e);
			System.out.println("while retrieving mccMasterList in CardProductRulesDAOImpl " + e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR, "Error: while retrieving mccMasterList in CardProductRulesDAOImpl " + e);

		} finally {

			HibernetFactory.closeSession();
		}

		return mccMasterList;
	}
}
