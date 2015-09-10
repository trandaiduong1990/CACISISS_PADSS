package org.transinfo.cacis.dataacess.daoimpl.oracle.disputemanagement;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.transinfo.cacis.TPlusCodes;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.constants.ICacisiss;
import org.transinfo.cacis.dataacess.HibernetDAO.HibernetFactory;
import org.transinfo.cacis.dataacess.dao.disputemanagement.CTFProcessDAO;
import org.transinfo.cacis.dataacess.daoimpl.oracle.BaseDAOImpl;
import org.transinfo.cacis.dto.csr.TransactionLogDto;
import org.transinfo.cacis.dto.disputemanagement.DisputeCaseEventDto;
import org.transinfo.cacis.dto.disputemanagement.DisputeCleaningMasterDto;
import org.transinfo.cacis.dto.disputemanagement.DisputeMasterDto;

@SuppressWarnings("unchecked")
public class CTFProcessDAOImpl extends BaseDAOImpl implements CTFProcessDAO {

	public TransactionLogDto getTransactionDto(String cardNo, String appCode,
			String[] tranxCode) throws TPlusException {

		TransactionLogDto objTransactionLogDto = null;
		StringBuffer stf = new StringBuffer("");

		try {

			Session session = HibernetFactory.currentSession();

			stf.append("from TransactionLogDto ");
			stf.append("where ");
			stf.append("cardNumber=:accNo ");
			stf.append("and approvalCode=:appCode ");
			stf.append("and tranxCode NOT IN (:tCodes) ");

			Query qry = session.createQuery(stf.toString());

			qry.setLong("accNo", Long.valueOf(cardNo)).setString("appCode",
					appCode).setParameter("tCodes", tranxCode);

			List list = qry.list();

			if (list.size() > 0) {
				objTransactionLogDto = (TransactionLogDto) list.get(0);
			}

		} catch (Exception e) {
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"CTFProcessDAOImpl getTransactionDto method :" + e);
		} finally {
			HibernetFactory.closeSession();
		}

		return objTransactionLogDto;
	}

	public boolean insertCTFIncomingMaster(
			DisputeCleaningMasterDto objDisputeCleaningMasterDto,
			boolean isTranxLogUpdate) throws TPlusException {

		boolean boolInsert = false;
		Transaction tx = null;
		int count = 0;

		try {

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			session.save(objDisputeCleaningMasterDto);

			if (isTranxLogUpdate) {
				count = session
						.createQuery(
								ICacisiss.IDisputeManagement.DIS_INCOME_MASTER_TRANX_UPDATE_QUERY)
						.setLong(
								"transactionId",
								objDisputeCleaningMasterDto.getTranxLog()
										.getTranxLogId()).setTimestamp("rDate",
								new Date()).setString("reconStatus", "Y")
						.executeUpdate();
			}

			if (!isTranxLogUpdate || (isTranxLogUpdate && count > 0)) {
				tx.commit();
				boolInsert = true;
			}

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"CTFProcessDAOImpl insertCTFIncomingMaster method :" + e);
		} finally {
			HibernetFactory.closeSession();
		}

		return boolInsert;
	}

	public DisputeMasterDto getDisputeMasterDto(String arn)
			throws TPlusException {

		DisputeMasterDto objDisputeMasterDto = null;
		StringBuffer stf = new StringBuffer("");

		try {

			Session session = HibernetFactory.currentSession();

			stf.append("from DisputeMasterDto ");
			stf.append("where ");
			stf.append("arn=:disArn ");

			Query qry = session.createQuery(stf.toString());
			qry.setString("disArn", arn);

			List list = qry.list();

			if (list.size() > 0) {
				objDisputeMasterDto = (DisputeMasterDto) list.get(0);
			}

		} catch (Exception e) {
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"CTFProcessDAOImpl getDisputeMasterDto method :" + e);
		} finally {
			HibernetFactory.closeSession();
		}

		return objDisputeMasterDto;
	}

	public boolean updateDisputeResponse(
			DisputeCaseEventDto objDisputeCaseEventDto, String userId)
			throws TPlusException {

		boolean boolRes = false;
		Transaction tx = null;
		int count = 0;

		try {

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			session.save(objDisputeCaseEventDto);

			count = session
					.createQuery(
							ICacisiss.IDisputeManagement.DIS_INCOME_MASTER_DISPUTE_MASTER_UPDATE)
					.setString(
							"disCaseNo",
							objDisputeCaseEventDto.getDisputeMaster()
									.getDisputeCaseNo()).setString("uBy",
							userId).setTimestamp("uDate", new Date()).setString(
							"disStatus", "R").executeUpdate();

			if (count > 0) {
				tx.commit();
				boolRes = true;
			}

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"CTFProcessDAOImpl updateDisputeResponse method :" + e);
		} finally {
			HibernetFactory.closeSession();
		}

		return boolRes;
	}

}
