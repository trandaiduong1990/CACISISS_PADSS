package org.transinfo.cacis.dataacess.daoimpl.oracle.batchprocess;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.transinfo.cacis.TPlusCodes;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.constants.ICacisiss;
import org.transinfo.cacis.dataacess.HibernetDAO.HibernetFactory;
import org.transinfo.cacis.dataacess.dao.cardproduction.CardBatchDAO;
import org.transinfo.cacis.dataacess.daoimpl.oracle.BaseDAOImpl;
import org.transinfo.cacis.dto.batchprocess.CardBatchDto;
import org.transinfo.cacis.dto.batchprocess.InstantCardDto;
import org.transinfo.cacis.dto.batchprocess.SearchInstantCardDto;
import org.transinfo.cacis.dto.settings.BranchDto;
import org.transinfo.cacis.dto.settings.CardProductDto;
import org.transinfo.cacis.dto.useraccess.CodeMasterDto;
import org.transinfo.cacis.formbean.batchprocess.CardBatchForm;
import org.transinfo.cacis.util.EncryptUtility;

@SuppressWarnings({ "unchecked" })
public class CardBatchDAOImpl extends BaseDAOImpl implements CardBatchDAO {
	private Logger logger = Logger.getLogger(CardBatchDAOImpl.class);

	@Override
	public CardBatchDto getCardBatchDto(String cardBatchDto)
			throws TPlusException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean create(InstantCardDto objCardBatchDto) throws TPlusException {

		boolean boolCreate = false;
		Transaction tx = null;

		objCardBatchDto.setStatus("N");

		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			session.save(objCardBatchDto);

			tx.commit();
			boolCreate = true;
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.error(e);
			System.out.println("Error in CardBatchDAOImpl add method : "
					+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in CardBatchDAOImpl add  method :" + e);
		} finally {
			HibernetFactory.closeSession();
		}

		return boolCreate;
	}

	@Override
	public int checkExitsRecord(InstantCardDto objCardBatch)
			throws TPlusException {
		int resCord = 0;

		try {
			Session session = HibernetFactory.currentSession();
			Query qry = session
					.createQuery("Select count(*) From InstantCardDto dto where dto.batchName=:abc and dto.issuerId='"
							+ objCardBatch.getIssuerId() + "'");
			qry.setString("abc", objCardBatch.getBatchName());
			List list = qry.list();
			resCord = ((Integer) list.get(0)).intValue();
			System.out.println("After EMVProfileDAOImpl checkExistrecord()"
					+ resCord);
		} catch (Exception ex) {
			logger.error(ex);
			System.out
					.println("Error in EMVProfileDAOImpl checkExist record method:"
							+ ex.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error:  Error in EMVProfileDAOImpl checkExistrecord method:"
							+ ex);
		} finally {
			HibernetFactory.closeSession();
		}

		return resCord;
	}

	@Override
	public Collection search(SearchInstantCardDto objDto, int pageNo)
			throws TPlusException {

		Collection objSearchCollection = new ArrayList();
		StringBuffer sbf = new StringBuffer();
		StringBuffer sbf1 = new StringBuffer();
		StringBuffer sbfTotal = new StringBuffer();

		try {

			sbf1.append("Select dto.batchName, cp.cardProductName, dto.status, dto.noOfCardsGenerated, dto.errorMsg, dto.updatedDate, dto.updatedBy, dto.batchId ");
			sbf1.append("From InstantCardDto dto, CardProductDto cp ");
			sbf.append("Where dto.cardProductId = cp.cardProductId and dto.issuerId='"
					+ objDto.getIssuerId()
					+ "' and dto.status "
					+ objDto.getScreenType());
			if (objDto.getBatchName() != "" && objDto.getBatchName() != null)
				sbf.append("and dto.batchName='" + objDto.getBatchName() + "' ");
			if (objDto.getFromDate() != "" && objDto.getFromDate() != null) {
				sbf.append("and dto.updatedDate >= TO_DATE('"
						+ objDto.getFromDate() + " "
						+ ICacisiss.IDateStuff.START_TIME_PART + "', '"
						+ ICacisiss.IDateStuff.DATETIME_FORMAT_VALIDATION
						+ "') ");
			}
			if (objDto.getToDate() != "" && objDto.getToDate() != null) {
				sbf.append("and dto.updatedDate <= TO_DATE('"
						+ objDto.getToDate() + " "
						+ ICacisiss.IDateStuff.END_TIME_PART + "', '"
						+ ICacisiss.IDateStuff.DATETIME_FORMAT_VALIDATION
						+ "') ");
			}
			sbf.append("ORDER BY dto.updatedDate desc, DECODE(dto.status,'N',1, 'A',2, 'P',3)");

			objSearchCollection = getSearchList((sbf1 + "" + sbf).toString(),
					pageNo);

			Session session = HibernetFactory.currentSession();
			sbfTotal.append("Select count(*) From InstantCardDto dto, CardProductDto cp ");
			Query qry = session.createQuery((sbfTotal + "" + sbf).toString());
			List list = qry.list();
			objDto.setTotal((list.get(0)).toString());
		} catch (Exception ex) {
			logger.error(ex);

			System.out.println("Error in CardBatchDAOImpl search method : "
					+ ex.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in EMVProfileDAOImpl search  method :" + ex);
		}

		return objSearchCollection;
	}

	@Override
	public InstantCardDto getCardBatchDetail(String batchId)
			throws TPlusException {

		InstantCardDto objDto = new InstantCardDto();
		Transaction tx = null;
		StringBuffer sbf = new StringBuffer();

		try {

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			sbf.append("From InstantCardDto dto ");
			sbf.append("Where dto.batchId = '" + batchId + "'");

			Query qry = session.createQuery(sbf.toString());
			List list = qry.list();

			if (list.size() > 0) {
				objDto = (InstantCardDto) list.get(0);
			}

			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.error(e);
			System.out
					.println("Error in CardBatchDAOImpl getCardBatchDetail method : "
							+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in CardBatchDAOImpl getCardBatchDetail  method :"
							+ e);
		} finally {
			HibernetFactory.closeSession();
		}

		return objDto;
	}

	@Override
	public CodeMasterDto getStatusDesc(String status) throws TPlusException {

		CodeMasterDto objDto = new CodeMasterDto();
		Transaction tx = null;
		StringBuffer sbf = new StringBuffer();

		try {

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			sbf.append("From CodeMasterDto dto ");
			sbf.append("Where dto.id.groupId = 'BATCH_STATUS' and dto.id.codeId = '"
					+ status + "' ");

			Query qry = session.createQuery(sbf.toString());
			List list = qry.list();

			if (list.size() > 0) {
				objDto = (CodeMasterDto) list.get(0);
			}

			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.error(e);
			System.out
					.println("Error in CardBatchDAOImpl getStatusDesc method : "
							+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in CardBatchDAOImpl getStatusDesc  method :" + e);
		} finally {
			HibernetFactory.closeSession();
		}

		return objDto;
	}

	@Override
	public CardProductDto getCardProductName(String id) throws TPlusException {

		CardProductDto objDto = new CardProductDto();
		Transaction tx = null;
		StringBuffer sbf = new StringBuffer();

		try {

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			sbf.append("From CardProductDto dto ");
			sbf.append("Where dto.cardProductId = '" + id + "'");

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
					.println("Error in CardBatchDAOImpl getCardProductName method : "
							+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in CardBatchDAOImpl getCardProductName  method :"
							+ e);
		} finally {
			HibernetFactory.closeSession();
		}

		return objDto;
	}

	@Override
	public BranchDto getBranchName(String id) throws TPlusException {

		BranchDto objDto = new BranchDto();
		Transaction tx = null;
		StringBuffer sbf = new StringBuffer();

		try {

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			sbf.append("From BranchDto dto ");
			sbf.append("Where dto.branchId = '" + id + "' ");

			Query qry = session.createQuery(sbf.toString());
			List list = qry.list();

			if (list.size() > 0) {
				objDto = (BranchDto) list.get(0);
			}

			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.error(e);
			System.out
					.println("Error in CardBatchDAOImpl getBranchName method : "
							+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in CardBatchDAOImpl getBranchName  method :" + e);
		} finally {
			HibernetFactory.closeSession();
		}

		return objDto;
	}

	@Override
	public boolean update(InstantCardDto objDto) throws TPlusException {

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
			System.out.println("Error in CardBatchDAOImpl update method : "
					+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in CardBatchDAOImpl update  method :" + e);
		} finally {
			HibernetFactory.closeSession();
		}

		return boolUpdate;
	}

	@Override
	public boolean delete(InstantCardDto objDto) throws TPlusException {

		boolean boolDelete = false;
		Transaction tx = null;

		objDto.setStatus("D");

		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			String sql = "UPDATE InstantCardDto SET status='"
					+ objDto.getStatus() + "' WHERE batchId='"
					+ objDto.getBatchId() + "'";

			int count = session.createQuery(sql).executeUpdate();

			tx.commit();
			if (count > 0)
				boolDelete = true;

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.error(e);
			System.out.println("Error in CardBatchDAOImpl delete method : "
					+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in CardBatchDAOImpl delete  method :" + e);
		} finally {
			HibernetFactory.closeSession();
		}

		return boolDelete;
	}

	@Override
	public boolean process(InstantCardDto objDto) throws TPlusException {

		boolean boolProcess = false;
		Transaction tx = null;
		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			objDto.setStatus("P");

			session.update(objDto);

			tx.commit();
			boolProcess = true;
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.error(e);
			System.out.println("Error in CardBatchDAOImpl process method : "
					+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in CardBatchDAOImpl process  method :" + e);
		} finally {
			HibernetFactory.closeSession();
		}

		return boolProcess;
	}

	@Override
	public boolean authorize(InstantCardDto objDto) throws TPlusException {

		boolean boolProcess = false;
		Transaction tx = null;
		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			objDto.setStatus("A");

			session.update(objDto);

			tx.commit();
			boolProcess = true;
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.error(e);
			System.out.println("Error in CardBatchDAOImpl process method : "
					+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in CardBatchDAOImpl process  method :" + e);
		} finally {
			HibernetFactory.closeSession();
		}

		return boolProcess;
	}

	@Override
	public int checkExitsUser(CardBatchForm objForm) throws TPlusException {
		int resCord = 0;
		StringBuffer sbf = new StringBuffer();

		try {
			Session session = HibernetFactory.currentSession();
			sbf.append("Select count(*) From UserMasterDto dto where dto.id.userId='"
					+ objForm.getUserName());
			sbf.append("' and dto.password='"
					+ EncryptUtility.encrPassword(objForm.getPwd()) + "'");
			if (!objForm.getUserName().equals("ASPSUPERADMIN"))
				sbf.append(" and dto.id.issuerId='" + objForm.getIssuerId()
						+ "'");
			Query qry = session.createQuery(sbf.toString());
			List list = qry.list();
			resCord = ((Integer) list.get(0)).intValue();
			System.out.println("After CardBatchDAOImpl checkExitsUser()"
					+ resCord);
		} catch (Exception ex) {
			logger.error(ex);
			System.out
					.println("Error in CardBatchDAOImpl checkExist user in user_role method:"
							+ ex.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error:  Error in CardBatchDAOImpl checkExitsUser method:"
							+ ex);
		} finally {
			HibernetFactory.closeSession();
		}

		return resCord;
	}
}
