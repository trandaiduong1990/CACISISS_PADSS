package org.transinfo.cacis.dataacess.daoimpl.oracle.settings;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.transinfo.cacis.TPlusCodes;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.common.constants.CommonCodes;
import org.transinfo.cacis.dataacess.HibernetDAO.HibernetFactory;
import org.transinfo.cacis.dataacess.dao.settings.MCCDAO;
import org.transinfo.cacis.dataacess.daoimpl.oracle.BaseDAOImpl;
import org.transinfo.cacis.dto.settings.MCCDto;
import org.transinfo.cacis.dto.settings.MCCMasterDto;
import org.transinfo.cacis.dto.settings.MCCSearchDto;

@SuppressWarnings("unchecked")
public class MCCDAOImpl extends BaseDAOImpl implements MCCDAO {

	private Logger logger = Logger.getLogger(MCCDAOImpl.class);

	/*
	 * This method is used for getting the MCCList
	 */
	public Collection search(MCCSearchDto objSearchDto, int pageNo)
			throws TPlusException {

		Collection objSearchCollection = new ArrayList();
		StringBuffer sbf = new StringBuffer();
		try {

			sbf
					.append("select mcc.merchantId , mcc.issuerId , mcc.currency , mcc.floorLimit, to_char(mcc.updatedDate,'dd-MM-yyyy') FROM MCCDto  mcc where mcc.status='"
							+ CommonCodes.STATUS_ACTIVE + "' ");

			// if(objSearchDto.getIssuerId()!=null &&
			// !objSearchDto.getIssuerId().equals("")){
			sbf.append("and mcc.issuerId='" + objSearchDto.getIssuerId() + "'");
			// }

			if (objSearchDto.getMerchantId() != null
					&& !objSearchDto.getMerchantId().equals("")) {
				sbf.append("and mcc.merchantId = '"
						+ objSearchDto.getMerchantId() + "' ");
			}

			// sbf.append("and mcc.status='"+CommonCodes.STATUS_ACTIVE+"' ");

			objSearchCollection = getSearchList(sbf.toString(), pageNo);

		} catch (Exception e) {
			logger.error(e);

			System.out.println("Error in MCCDAOImpl search method : "
					+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in MCCDAOImpl search  method :" + e);

		}
		return objSearchCollection;
	}

	public Collection searchMccMaster(MCCMasterDto objMccMasterDto, int pageNo)
			throws TPlusException {

		Collection objSearchCollection = null;
		
		StringBuffer sbf = new StringBuffer();
		
		try {

			sbf.append("SELECT ");
			sbf.append("mccMasDto.mcc, mccMasDto.mccName, ");
			sbf.append("to_char(mccMasDto.updatedDate,'dd-MM-yyyy hh24:mi:ss') ");
			sbf.append("FROM MCCMasterDto mccMasDto ");
			sbf.append("WHERE 1= 1 ");

			if (objMccMasterDto.getMcc() != null && !objMccMasterDto.getMcc().equals("")) {
				sbf.append("and mccMasDto.mcc = '" + objMccMasterDto.getMcc() + "' ");
			}

			if (objMccMasterDto.getMccName() != null && !objMccMasterDto.getMccName().equals("")) {
				sbf.append("and upper(mccMasDto.mccName) like '%" + objMccMasterDto.getMccName().toUpperCase() + "%' ");
			}

			objSearchCollection = getSearchList(sbf.toString(), pageNo);

		} catch (Exception e) {
			System.out.println("Error in MCCDAOImpl search method" + e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR, "Error: Error in MCCDAOImpl search method" + e);
		}
		
		return objSearchCollection;
	}

	/*
	 * This method is used for getting the particular Record to update
	 */
	public MCCDto getMCCDto(String merchantId) throws TPlusException {

		MCCDto objDto = null;
		Transaction tx = null;
		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			objDto = (MCCDto) session.get(MCCDto.class, merchantId);
			tx.commit();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.error(e);
			System.out.println("Error in MCCDAOImpl getMCCDto method : "
					+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in MCCDAOImpl getMCCDto  method :" + e);
		} finally {
			HibernetFactory.closeSession();
		}
		return objDto;

	}

	/*
	 * This method is used for insert the particular Record to MCC table
	 */
	public boolean add(MCCDto objDto) throws TPlusException {

		boolean boolAdd = false;
		Transaction tx = null;
		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			session.save(objDto);

			tx.commit();
			boolAdd = true;

		}

		catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.error(e);
			System.out.println("Error in MCCDAOImpl add method : "
					+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in MCCDAOImpl add  method :" + e);
		} finally {
			HibernetFactory.closeSession();
		}
		return boolAdd;

	}

	/*
	 * This method is used for updating the particular Record in MCC table
	 */
	public boolean update(MCCDto objDto) throws TPlusException {

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
			System.out.println("Error in MCCDAOImpl update method : "
					+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in MCCDAOImpl update  method :" + e);
		} finally {
			HibernetFactory.closeSession();
		}
		return boolUpdate;
	}

	/*
	 * This method is used for deleting the particular Record in MCC table
	 */
	public boolean delete(MCCDto objDto) throws TPlusException {

		boolean boolDelete = false;
		Transaction tx = null;
		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			// session.delete(objDto);
			String sql = "UPDATE MCCDto SET status =:status  WHERE merchantId=:mccid";
			int count = session.createQuery(sql).setString("mccid",
					objDto.getMerchantId()).setString("status",
					objDto.getStatus()).executeUpdate();

			tx.commit();
			if (count > 0)
				boolDelete = true;
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.error(e);
			System.out.println("Error in MCCDAOImpl delete method : "
					+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in MCCDAOImpl delete  method :" + e);
		} finally {
			HibernetFactory.closeSession();
		}
		return boolDelete;
	}

	public boolean hasWithdrawalLimitRules(String mccId) throws TPlusException {

		boolean boolhas = false;
		Transaction tx = null;
		StringBuffer sbf = new StringBuffer();

		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			sbf.append("select count(*) ");
			sbf.append("from WithdrawalLimitRulesDto wdlr ");
			sbf.append("where wdlr.mcc = '" + mccId + "' ");

			Query qry = session.createQuery(sbf.toString());
			List list = qry.list();

			int count = ((Integer) list.get(0)).intValue();
			if (count > 0) {
				boolhas = true;
			}

			tx.commit();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.error(e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in MCCDAOImpl hasWithdrawalLimitRules  method :"
							+ e);
		} finally {
			HibernetFactory.closeSession();
		}
		return boolhas;

	}

	public boolean hasRiskTranxConfig(String mccId) throws TPlusException {

		boolean boolhas = false;
		Transaction tx = null;
		StringBuffer sbf = new StringBuffer();

		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			sbf.append("select count(*) ");
			sbf.append("from RiskTranxSaleCashMccsDto rtscdto ");
			sbf.append("where rtscdto.mcc = '" + mccId + "' ");

			Query qry = session.createQuery(sbf.toString());
			List list = qry.list();

			int count = ((Integer) list.get(0)).intValue();
			if (count > 0) {
				boolhas = true;
			}

			tx.commit();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.error(e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in MCCDAOImpl hasRiskTranxConfig  method :" + e);
		} finally {
			HibernetFactory.closeSession();
		}
		return boolhas;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.transinfo.cacis.dataacess.dao.settings.MCCDAO #issuerListData()
	 */

	/*
	 * public Map issuerListData() throws TPlusException {
	 * 
	 * Map issuerList = new TreeMap(); Transaction tx =null; try { Session
	 * session = HibernetFactory.currentSession(); tx =
	 * session.beginTransaction(); Query qry =
	 * session.createQuery("From IssuerDto"); List listIssuers = qry.list();
	 * for(Iterator it = listIssuers.iterator();it.hasNext();){ IssuerDto objDto
	 * = new IssuerDto(); objDto = (IssuerDto) it.next();
	 * issuerList.put(objDto.getIssuerId(),objDto.getIssuerId()+ "-"
	 * +objDto.getIssuerName()); } tx.commit();
	 * 
	 * }catch (Exception e){ if(tx!=null){ tx.rollback(); }
	 * System.out.println("Error in MCCDAOImpl issuerListData method : "
	 * +e.getMessage()); throw newTPlusException(TPlusCodes.APPLICATION_ERROR,
	 * "Error: in MCCDAOImpl issuerListData  method :"+e); } finally {
	 * HibernetFactory.closeSession(); } return issuerList; }
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.transinfo.cacis.dataacess.dao.settings.MCCDAO
	 * #checkExistrecord(org.transinfo.cacis.dto.settings.MCCDto)
	 */
	public int checkExistrecord(MCCDto objDto) throws TPlusException {

		int res = 0;
		try {
			Session session = HibernetFactory.currentSession();
			Query qry = session
					.createQuery("select count(*) from MCCDto mcc where mcc.merchantId=:merchantId ");
			qry.setString("merchantId", objDto.getMerchantId());
			List list = qry.list();
			res = ((Integer) list.get(0)).intValue();
			System.out.println("After MCCDAOImpl checkExistrecord() count"
					+ res);

		}

		catch (Exception e) {
			logger.error(e);
			System.out.println("Error in MCCDAOImpl checkExist record method:"
					+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error:  Error in MCCDAOImpl checkExistrecord method:" + e);
		}

		finally {
			HibernetFactory.closeSession();
		}

		return res;

	}

	public boolean updateByExecute(MCCDto objDto) throws TPlusException {

		boolean boolUpdate = false;
		int count = 0;

		try {
			Session session = HibernetFactory.currentSession();

			String repSql = "UPDATE MCCDto SET merchantType =:mtype WHERE merchantId=:mid";
			count = session.createQuery(repSql).setString("mtype",
					objDto.getMerchantType()).setString("mid",
					objDto.getMerchantId()).executeUpdate();
			if (count > 0) {
				boolUpdate = true;
			}

		} catch (Exception e) {
			logger.error(e);
			System.out.println("Error in MCCDAOImpl update method : "
					+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in MCCDAOImpl update  method :" + e);
		} finally {
			HibernetFactory.closeSession();
		}
		return boolUpdate;
	}

}
