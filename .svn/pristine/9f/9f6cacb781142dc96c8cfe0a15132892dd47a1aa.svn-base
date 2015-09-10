package org.transinfo.cacis.dataacess.daoimpl.oracle.disputemanagement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.transinfo.cacis.TPlusCodes;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.common.IdsGenartion;
import org.transinfo.cacis.constants.ICacisiss;
import org.transinfo.cacis.dataacess.HibernetDAO.HibernetFactory;
import org.transinfo.cacis.dataacess.dao.disputemanagement.DisputeManagementDAO;
import org.transinfo.cacis.dataacess.daoimpl.oracle.BaseDAOImpl;
import org.transinfo.cacis.dto.csr.TransactionLogDto;
import org.transinfo.cacis.dto.disputemanagement.ChargeBackResendSearchDto;
import org.transinfo.cacis.dto.disputemanagement.ChargeBackSearchDto;
import org.transinfo.cacis.dto.disputemanagement.DisputeCaseEventDto;
import org.transinfo.cacis.dto.disputemanagement.DisputeCleaningMasterDto;
import org.transinfo.cacis.dto.disputemanagement.DisputeGroupDetailsDto;
import org.transinfo.cacis.dto.disputemanagement.DisputeMasterDto;
import org.transinfo.cacis.dto.disputemanagement.DisputeNotReconsiledTranxSearchDto;
import org.transinfo.cacis.dto.disputemanagement.DisputeTranxNRSearchDto;
import org.transinfo.cacis.dto.disputemanagement.DisputeTranxSearchDto;

@SuppressWarnings("unchecked")
public class DisputeManagementDAOImpl extends BaseDAOImpl implements
		DisputeManagementDAO {

	public DisputeTranxSearchDto search(
			DisputeTranxSearchDto objDisputeTranxSearchDto, int pageNo)
			throws TPlusException {

		Collection objSearchCollection = null;
		int totalCount = 0;

		StringBuffer sbf = new StringBuffer();
		StringBuffer sbfSelect = new StringBuffer();
		StringBuffer sbfCount = new StringBuffer();

		try {

			sbfSelect.append("select ");
			sbfSelect.append("tldto.tranxLogId, ");
			sbfSelect.append("to_char(tldto.dateTime, '"
					+ ICacisiss.IDateStuff.DATETIME_FORMAT_DISPLAY + "'), ");
			sbfSelect
					.append("tldto.cardNumber, dcmdto.tranxCode.description, tldto.responseCode, ");
			sbfSelect.append("tldto.amount, tldto.merchantId, ");
			sbfSelect.append("tldto.approvalCode, tldto.referenceNo, ");
			sbfSelect.append("dcmdto.arn, ");
			sbfSelect.append("dcmdto.settlementID ");

			sbfCount.append("select ");
			sbfCount.append("count(tldto.tranxLogId) ");

			sbf
					.append("from TransactionLogDto tldto, DisputeCleaningMasterDto dcmdto ");
			sbf.append("where tldto.tranxLogId=dcmdto.tranxLog.tranxLogId ");
			sbf.append("and 1=1 ");
			sbf.append("and tldto.responseCode = '00' ");
			sbf.append("and dcmdto.usageCode = '1' ");
			sbf.append("and dcmdto.isDispute <> 'Y' ");
			sbf.append("and (dcmdto.status <> '1' or dcmdto.status <> '2') ");

			if (objDisputeTranxSearchDto.getCardNo() != null
					&& !objDisputeTranxSearchDto.getCardNo().equals("")) {
				sbf.append("and tldto.cardNumber = "
						+ objDisputeTranxSearchDto.getCardNo() + " ");
			}

			if (objDisputeTranxSearchDto.getTerminalId() != null
					&& !objDisputeTranxSearchDto.getTerminalId().equals("")) {
				sbf.append("and tldto.terminalId = '"
						+ objDisputeTranxSearchDto.getTerminalId() + "' ");
			}

			if (objDisputeTranxSearchDto.getAuthCode() != null
					&& !objDisputeTranxSearchDto.getAuthCode().equals("")) {
				sbf.append("and tldto.approvalCode = '"
						+ objDisputeTranxSearchDto.getAuthCode() + "' ");
			}

			if (objDisputeTranxSearchDto.getRefNo() != null
					&& !objDisputeTranxSearchDto.getRefNo().equals("")) {
				sbf.append("and tldto.referenceNo = '"
						+ objDisputeTranxSearchDto.getRefNo() + "' ");
			}

			if (objDisputeTranxSearchDto.getMcc() != null
					&& !objDisputeTranxSearchDto.getMcc().equals("")) {
				sbf.append("and tldto.mcc = '"
						+ objDisputeTranxSearchDto.getMcc() + "' ");
			}

			if (objDisputeTranxSearchDto.getArn() != null
					&& !objDisputeTranxSearchDto.getArn().equals("")) {
				sbf.append("and dcmdto.arn = '"
						+ objDisputeTranxSearchDto.getArn() + "' ");
			}

			if (objDisputeTranxSearchDto.getAmtFrom() != null
					&& !objDisputeTranxSearchDto.getAmtFrom().equals("")) {
				sbf.append("and tldto.amount >= "
						+ objDisputeTranxSearchDto.getAmtFrom() + " ");
			}

			if (objDisputeTranxSearchDto.getAmtTo() != null
					&& !objDisputeTranxSearchDto.getAmtTo().equals("")) {
				sbf.append("and tldto.amount <= "
						+ objDisputeTranxSearchDto.getAmtTo() + " ");
			}

			if (objDisputeTranxSearchDto.getStartDate() != null
					&& !objDisputeTranxSearchDto.getStartDate().equals("")) {
				sbf.append("and tldto.dateTime >= TO_DATE('"
						+ objDisputeTranxSearchDto.getStartDate() + " "
						+ ICacisiss.IDateStuff.START_TIME_PART + "', '"
						+ ICacisiss.IDateStuff.DATETIME_FORMAT_VALIDATION
						+ "') ");
			}

			if (objDisputeTranxSearchDto.getEndDate() != null
					&& !objDisputeTranxSearchDto.getEndDate().equals("")) {
				sbf.append("and tldto.dateTime <= TO_DATE('"
						+ objDisputeTranxSearchDto.getEndDate() + " "
						+ ICacisiss.IDateStuff.END_TIME_PART + "', '"
						+ ICacisiss.IDateStuff.DATETIME_FORMAT_VALIDATION
						+ "') ");
			}

			sbf.append("order by tldto.tranxLogId desc");

			objSearchCollection = getSearchList((sbfSelect.append(sbf))
					.toString(), pageNo);
			objDisputeTranxSearchDto.setSearchList(objSearchCollection);

			totalCount = getSearchTotalCount((sbfCount.append(sbf)).toString());
			objDisputeTranxSearchDto.setTotalCount(totalCount);

		} catch (Exception e) {
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error:in DisputeManagementDAOImpl search method" + e);
		}

		return objDisputeTranxSearchDto;
	}
	
	public DisputeTranxNRSearchDto searchNR(
			DisputeTranxNRSearchDto objDisputeTranxSearchDto, int pageNo)
			throws TPlusException {

		Collection objSearchCollection = null;
		int totalCount = 0;

		StringBuffer sbf = new StringBuffer();
		StringBuffer sbfSelect = new StringBuffer();
		StringBuffer sbfCount = new StringBuffer();

		try {

			sbfSelect.append("select ");
			sbfSelect.append("tldto.tranxLogId, ");
			sbfSelect.append("to_char(tldto.dateTime, '" + ICacisiss.IDateStuff.DATETIME_FORMAT_DISPLAY + "'), ");
			sbfSelect.append("tldto.cardNumber, dcmdto.tranxCode.description, tldto.responseCode, ");
			sbfSelect.append("tldto.amount, tldto.merchantId, ");
			sbfSelect.append("tldto.approvalCode, tldto.referenceNo, ");
			sbfSelect.append("dcmdto.arn, ");
			sbfSelect.append("dcmdto.settlementID ");

			sbfCount.append("select ");
			sbfCount.append("count(tldto.tranxLogId) ");

			sbf.append("from NonReconTransactionLogDto tldto, DisputeCleaningMasterDto dcmdto ");
			sbf.append("where tldto.tranxLogId=dcmdto.tranxLog.tranxLogId ");
			sbf.append("and 1=1 ");
			sbf.append("and tldto.responseCode = '00' ");
			sbf.append("and dcmdto.usageCode = '1' ");
			sbf.append("and dcmdto.isDispute <> 'Y' ");
			sbf.append("and (dcmdto.status <> '1' or dcmdto.status <> '2') ");

			if (objDisputeTranxSearchDto.getCardNo() != null
					&& !objDisputeTranxSearchDto.getCardNo().equals("")) {
				sbf.append("and tldto.cardNumber = "
						+ objDisputeTranxSearchDto.getCardNo() + " ");
			}

			if (objDisputeTranxSearchDto.getTerminalId() != null
					&& !objDisputeTranxSearchDto.getTerminalId().equals("")) {
				sbf.append("and tldto.terminalId = '"
						+ objDisputeTranxSearchDto.getTerminalId() + "' ");
			}

			if (objDisputeTranxSearchDto.getAuthCode() != null
					&& !objDisputeTranxSearchDto.getAuthCode().equals("")) {
				sbf.append("and tldto.approvalCode = '"
						+ objDisputeTranxSearchDto.getAuthCode() + "' ");
			}

			if (objDisputeTranxSearchDto.getRefNo() != null
					&& !objDisputeTranxSearchDto.getRefNo().equals("")) {
				sbf.append("and tldto.referenceNo = '"
						+ objDisputeTranxSearchDto.getRefNo() + "' ");
			}

			if (objDisputeTranxSearchDto.getMcc() != null
					&& !objDisputeTranxSearchDto.getMcc().equals("")) {
				sbf.append("and tldto.mcc = '"
						+ objDisputeTranxSearchDto.getMcc() + "' ");
			}

			if (objDisputeTranxSearchDto.getArn() != null
					&& !objDisputeTranxSearchDto.getArn().equals("")) {
				sbf.append("and dcmdto.arn = '"
						+ objDisputeTranxSearchDto.getArn() + "' ");
			}

			if (objDisputeTranxSearchDto.getAmtFrom() != null
					&& !objDisputeTranxSearchDto.getAmtFrom().equals("")) {
				sbf.append("and tldto.amount >= "
						+ objDisputeTranxSearchDto.getAmtFrom() + " ");
			}

			if (objDisputeTranxSearchDto.getAmtTo() != null
					&& !objDisputeTranxSearchDto.getAmtTo().equals("")) {
				sbf.append("and tldto.amount <= "
						+ objDisputeTranxSearchDto.getAmtTo() + " ");
			}

			if (objDisputeTranxSearchDto.getStartDate() != null
					&& !objDisputeTranxSearchDto.getStartDate().equals("")) {
				sbf.append("and tldto.dateTime >= TO_DATE('"
						+ objDisputeTranxSearchDto.getStartDate() + " "
						+ ICacisiss.IDateStuff.START_TIME_PART + "', '"
						+ ICacisiss.IDateStuff.DATETIME_FORMAT_VALIDATION
						+ "') ");
			}

			if (objDisputeTranxSearchDto.getEndDate() != null
					&& !objDisputeTranxSearchDto.getEndDate().equals("")) {
				sbf.append("and tldto.dateTime <= TO_DATE('"
						+ objDisputeTranxSearchDto.getEndDate() + " "
						+ ICacisiss.IDateStuff.END_TIME_PART + "', '"
						+ ICacisiss.IDateStuff.DATETIME_FORMAT_VALIDATION
						+ "') ");
			}

			sbf.append("order by tldto.tranxLogId desc");

			objSearchCollection = getSearchList((sbfSelect.append(sbf))
					.toString(), pageNo);
			objDisputeTranxSearchDto.setSearchList(objSearchCollection);

			totalCount = getSearchTotalCount((sbfCount.append(sbf)).toString());
			objDisputeTranxSearchDto.setTotalCount(totalCount);

		} catch (Exception e) {
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error:in DisputeManagementDAOImpl search method" + e);
		}

		return objDisputeTranxSearchDto;
	}

	public DisputeGroupDetailsDto getDisputeGroupDetailsDto(String reasonId)
			throws TPlusException {

		DisputeGroupDetailsDto objDisputeGroupDetailsDto = null;

		try {

			Session session = HibernetFactory.currentSession();

			objDisputeGroupDetailsDto = (DisputeGroupDetailsDto) session.get(
					DisputeGroupDetailsDto.class, reasonId);

		} catch (Exception e) {
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"DisputeManagementDAOImpl getDisputeGroupDetailsDto method :"
							+ e);
		} finally {
			HibernetFactory.closeSession();
		}

		return objDisputeGroupDetailsDto;
	}

	public DisputeGroupDetailsDto getDisputeGroupDetailsDto(String reasonId, String groupId)
			throws TPlusException {

		DisputeGroupDetailsDto objDisputeGroupDetailsDto = null;
		
		List disList = new ArrayList();

		try {

			Session session = HibernetFactory.currentSession();
			
			Query qry = session.createQuery("from DisputeGroupDetailsDto dgd where dgd.reasonCode=:reasoncode and dgd.disputeGroup.groupId=:group ");
			qry.setString("reasoncode", reasonId);
			qry.setString("group", groupId);
			disList = qry.list();

			if (disList.size() > 0) {
				objDisputeGroupDetailsDto = (DisputeGroupDetailsDto) disList.get(0);
			}

		} catch (Exception e) {
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"DisputeManagementDAOImpl getDisputeGroupDetailsDto method :"
							+ e);
		} finally {
			HibernetFactory.closeSession();
		}

		return objDisputeGroupDetailsDto;
	}

	public TransactionLogDto getTransactionDto(String tranxId)
			throws TPlusException {

		TransactionLogDto objTransactionLogDto = null;

		try {

			Session session = HibernetFactory.currentSession();

			objTransactionLogDto = (TransactionLogDto) session.get(
					TransactionLogDto.class, Long.valueOf(tranxId));

			/*
			 * if (objTransactionLogDto.getDisputeMasters().size() > 0) {
			 * DisputeMasterDto objDisputeMasterDto = null; Iterator itr =
			 * objTransactionLogDto.getDisputeMasters() .iterator(); while
			 * (itr.hasNext()) { objDisputeMasterDto = (DisputeMasterDto)
			 * itr.next();
			 * Hibernate.initialize(objDisputeMasterDto.getStatus()); Hibernate
			 * .initialize(objDisputeMasterDto.getDisputeReason()); } }
			 */

		} catch (Exception e) {
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"DisputeManagementDAOImpl getTransactionDto method :" + e);
		} finally {
			HibernetFactory.closeSession();
		}

		return objTransactionLogDto;
	}

	public boolean insertChargeBack(DisputeMasterDto objDisputeMasterDto,
			DisputeCaseEventDto objDisputeCaseEventDto) throws TPlusException {

		boolean boolInsert = false;
		Transaction tx = null;

		try {

			Session session = HibernetFactory.currentSession();

			Long seqId = (Long) session.createSQLQuery(
					"select SEQ_DISPUTEMASTER.NEXTVAL as id from dual")
					.addScalar("id", Hibernate.LONG).uniqueResult();
			String disputeCaseNo = IdsGenartion.GenerateDisputeMasterId()
					+ seqId;
			objDisputeMasterDto.setDisputeCaseNo(disputeCaseNo);

			// assign the dispute master object
			objDisputeCaseEventDto.setDisputeMaster(objDisputeMasterDto);

			tx = session.beginTransaction();

			session.save(objDisputeMasterDto);
			session.save(objDisputeCaseEventDto);

			tx.commit();
			boolInsert = true;

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"DisputeManagementDAOImpl insertChargeBack  method :" + e);
		} finally {
			HibernetFactory.closeSession();
		}

		return boolInsert;
	}

	public boolean insertNotReconciledChargeBack(
			DisputeMasterDto objDisputeMasterDto,
			DisputeCaseEventDto objDisputeCaseEventDto, String settlementId)
			throws TPlusException {

		boolean boolInsert = false;
		Transaction tx = null;

		try {

			Session session = HibernetFactory.currentSession();

			Long seqId = (Long) session.createSQLQuery(
					"select SEQ_DISPUTEMASTER.NEXTVAL as id from dual")
					.addScalar("id", Hibernate.LONG).uniqueResult();
			String disputeCaseNo = IdsGenartion.GenerateDisputeMasterId()
					+ seqId;
			objDisputeMasterDto.setDisputeCaseNo(disputeCaseNo);

			// assign the dispute master object
			objDisputeCaseEventDto.setDisputeMaster(objDisputeMasterDto);

			tx = session.beginTransaction();

			session.save(objDisputeMasterDto);
			session.save(objDisputeCaseEventDto);

			if (settlementId != null && !"".equals(settlementId)) {
				String sql = "UPDATE DisputeCleaningMasterDto SET status =:status  WHERE settlementID=:settlementID";
				int count = session.createQuery(sql).setString("settlementID",
						settlementId).setString("status", "2").executeUpdate();

				if (count > 0) {
					tx.commit();
				}
			} else {
				tx.commit();
			}

			boolInsert = true;

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"DisputeManagementDAOImpl insertChargeBack  method :" + e);
		} finally {
			HibernetFactory.closeSession();
		}

		return boolInsert;
	}

	public ChargeBackSearchDto CBsearch(
			ChargeBackSearchDto objChargeBackSearchDto, int pageNo)
			throws TPlusException {

		Collection objSearchCollection = null;
		int totalCount = 0;

		StringBuffer sbf = new StringBuffer();
		StringBuffer sbfSelect = new StringBuffer();
		StringBuffer sbfCount = new StringBuffer();

		try {

			sbfSelect.append("select ");
			sbfSelect.append("dmdto.disputeCaseNo, ");
			sbfSelect.append("dmdto.disputeType.description, ");
			sbfSelect.append("dmdto.disputeReason.chargeBackReason, ");
			sbfSelect.append("dmdto.disputeAmt, ");
			sbfSelect.append("dmdto.disputeCurrency.currencyName, ");
			sbfSelect.append("dmdto.arn, ");
			sbfSelect.append("to_char(dmdto.disputeCreatedDate, '"
					+ ICacisiss.IDateStuff.DATETIME_FORMAT_DISPLAY + "'), ");
			sbfSelect.append("dmdto.createdDays, ");
			sbfSelect.append("dmdto.disputeReason.timeLimit ");

			sbfCount.append("select ");
			sbfCount.append("count(dmdto.disputeCaseNo) ");

			sbf
					.append("from DisputeMasterDto dmdto, DisputeCleaningMasterDto dcmdto ");

			sbf.append("where dmdto.settlementId=dcmdto.settlementID ");
			sbf.append("and dmdto.status.id <> 'C' ");

			if (objChargeBackSearchDto.getAuthCode() != null
					&& !objChargeBackSearchDto.getAuthCode().equals("")) {
				sbf.append("and dcmdto.athorizationCode = '"
						+ objChargeBackSearchDto.getAuthCode() + "' ");
			}

			if (objChargeBackSearchDto.getArn() != null
					&& !objChargeBackSearchDto.getArn().equals("")) {
				sbf.append("and dmdto.arn = '"
						+ objChargeBackSearchDto.getArn() + "' ");
			}

			if (objChargeBackSearchDto.getAmtFrom() != null
					&& !objChargeBackSearchDto.getAmtFrom().equals("")) {
				sbf.append("and dmdto.disputeAmt >= "
						+ objChargeBackSearchDto.getAmtFrom() + " ");
			}

			if (objChargeBackSearchDto.getAmtTo() != null
					&& !objChargeBackSearchDto.getAmtTo().equals("")) {
				sbf.append("and dmdto.disputeAmt <= "
						+ objChargeBackSearchDto.getAmtTo() + " ");
			}

			if (objChargeBackSearchDto.getStartDate() != null
					&& !objChargeBackSearchDto.getStartDate().equals("")) {
				sbf.append("and dmdto.disputeCreatedDate >= TO_DATE('"
						+ objChargeBackSearchDto.getStartDate() + " "
						+ ICacisiss.IDateStuff.START_TIME_PART + "', '"
						+ ICacisiss.IDateStuff.DATETIME_FORMAT_VALIDATION
						+ "') ");
			}

			if (objChargeBackSearchDto.getEndDate() != null
					&& !objChargeBackSearchDto.getEndDate().equals("")) {
				sbf.append("and dmdto.disputeCreatedDate <= TO_DATE('"
						+ objChargeBackSearchDto.getEndDate() + " "
						+ ICacisiss.IDateStuff.END_TIME_PART + "', '"
						+ ICacisiss.IDateStuff.DATETIME_FORMAT_VALIDATION
						+ "') ");
			}

			sbf.append("order by dmdto.disputeCaseNo desc");

			objSearchCollection = getSearchList((sbfSelect.append(sbf))
					.toString(), pageNo);
			objChargeBackSearchDto.setSearchList(objSearchCollection);

			totalCount = getSearchTotalCount((sbfCount.append(sbf)).toString());
			objChargeBackSearchDto.setTotalCount(totalCount);

		} catch (Exception e) {
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error:in DisputeManagementDAOImpl CBsearch method" + e);
		}

		return objChargeBackSearchDto;
	}

	public DisputeMasterDto getDisputeMasterDto(String disCaseNo)
			throws TPlusException {

		DisputeMasterDto objDisputeMasterDto = null;

		try {

			Session session = HibernetFactory.currentSession();

			objDisputeMasterDto = (DisputeMasterDto) session.get(
					DisputeMasterDto.class, disCaseNo);

			if (objDisputeMasterDto != null) {
				Hibernate.initialize(objDisputeMasterDto.getDisputeCurrency());
				Hibernate.initialize(objDisputeMasterDto.getDisputeReason());
				Hibernate.initialize(objDisputeMasterDto.getStatus());
			}

		} catch (Exception e) {
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"DisputeManagementDAOImpl getDisputeMasterDto method :" + e);
		} finally {
			HibernetFactory.closeSession();
		}

		return objDisputeMasterDto;
	}

	public DisputeCleaningMasterDto getDisputeCleaningMasterDto(
			String settlementId) throws TPlusException {

		DisputeCleaningMasterDto objDisputeCleaningMasterDto = null;

		try {

			Session session = HibernetFactory.currentSession();

			objDisputeCleaningMasterDto = (DisputeCleaningMasterDto) session
					.get(DisputeCleaningMasterDto.class, settlementId);

			if (objDisputeCleaningMasterDto != null) {
				Hibernate.initialize(objDisputeCleaningMasterDto.getTranxLog());
				Hibernate.initialize(objDisputeCleaningMasterDto.getTranxCode());
			}

		} catch (Exception e) {
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"DisputeManagementDAOImpl getDisputeCleaningMasterDto method :"
							+ e);
		} finally {
			HibernetFactory.closeSession();
		}

		return objDisputeCleaningMasterDto;
	}

	public List getDisputeMasterList(String settlementId) throws TPlusException {

		List disList = new ArrayList();

		try {

			Session session = HibernetFactory.currentSession();

			Query qry = session.createQuery("from DisputeMasterDto dmdto where dmdto.settlementId=:settlementId ");
			qry.setLong("settlementId", Long.valueOf(settlementId));
			disList = qry.list();

			if (disList.size() > 0) {
				DisputeMasterDto objDisputeMasterDto = null;
				Iterator itr = disList.iterator();

				while (itr.hasNext()) {
					objDisputeMasterDto = (DisputeMasterDto) itr.next();
					Hibernate.initialize(objDisputeMasterDto.getStatus());
					Hibernate.initialize(objDisputeMasterDto.getDisputeReason());
				}
			}

		} catch (Exception e) {
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"DisputeManagementDAOImpl getDisputeMasterList method :"
							+ e);
		} finally {
			HibernetFactory.closeSession();
		}

		return disList;
	}

	public List getDisputeMasterListByARN(String arn) throws TPlusException {

		List disList = new ArrayList();

		try {

			Session session = HibernetFactory.currentSession();

			Query qry = session.createQuery("from DisputeMasterDto dmdto where dmdto.arn=:arn ");
			qry.setString("arn", arn);
			disList = qry.list();

			if (disList.size() > 0) {
				DisputeMasterDto objDisputeMasterDto = null;
				Iterator itr = disList.iterator();

				while (itr.hasNext()) {
					objDisputeMasterDto = (DisputeMasterDto) itr.next();
					Hibernate.initialize(objDisputeMasterDto.getStatus());
					Hibernate.initialize(objDisputeMasterDto.getDisputeReason());
					Hibernate.initialize(objDisputeMasterDto.getDisputeType());
				}
			}

		} catch (Exception e) {
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"DisputeManagementDAOImpl getDisputeMasterListByARN method :"
							+ e);
		} finally {
			HibernetFactory.closeSession();
		}

		return disList;
	}

	public int getDisputeEventsCountByEvent(String eventId, String arn) throws TPlusException {

		int res = 0;

		try {

			Session session = HibernetFactory.currentSession();

			Query qry = session.createQuery("select count(*) from DisputeCaseEventDto dce where dce.disputEevent.eventId=:eventId and dce.arn=:arn ");
			qry.setString("eventId", eventId);
			qry.setString("arn", arn);
			List disList = qry.list();
			
			res = ((Integer) disList.get(0)).intValue();

		} catch (Exception e) {
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"DisputeManagementDAOImpl getDisputeEventsCountByARN method :"
							+ e);
		} finally {
			HibernetFactory.closeSession();
		}

		return res;
	}

	public List getDisputeCaseEventsList(String disCaseNo)
			throws TPlusException {

		List disList = new ArrayList();

		try {

			Session session = HibernetFactory.currentSession();

			Query qry = session
					.createQuery("from DisputeCaseEventDto dcedto where dcedto.disputeMaster.disputeCaseNo=:disputeCaseNo order by dcedto.dateTime desc ");
			qry.setString("disputeCaseNo", disCaseNo);
			disList = qry.list();

			if (disList.size() > 0) {
				DisputeCaseEventDto objDisputeCaseEventDto = null;
				Iterator itr = disList.iterator();

				while (itr.hasNext()) {
					objDisputeCaseEventDto = (DisputeCaseEventDto) itr.next();
					Hibernate.initialize(objDisputeCaseEventDto
							.getDisputEevent());
					Hibernate.initialize(objDisputeCaseEventDto
							.getDocumentIncluded());
				}
			}

		} catch (Exception e) {
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"DisputeManagementDAOImpl getDisputeCaseEventsList method :"
							+ e);
		} finally {
			HibernetFactory.closeSession();
		}

		return disList;
	}

	public List getDisputeCaseEventsListByARD(String ard)
			throws TPlusException {

		List disList = new ArrayList();

		try {

			Session session = HibernetFactory.currentSession();

			Query qry = session.createQuery("from DisputeCaseEventDto dcedto where dcedto.arn=:ard order by dcedto.dateTime desc ");
			qry.setString("ard", ard);
			disList = qry.list();

			if (disList.size() > 0) {
				DisputeCaseEventDto objDisputeCaseEventDto = null;
				Iterator itr = disList.iterator();

				while (itr.hasNext()) {
					objDisputeCaseEventDto = (DisputeCaseEventDto) itr.next();
					Hibernate.initialize(objDisputeCaseEventDto.getDisputEevent());
				}
			}

		} catch (Exception e) {
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"DisputeManagementDAOImpl getDisputeCaseEventsList method :"
							+ e);
		} finally {
			HibernetFactory.closeSession();
		}

		return disList;
	}

	public DisputeNotReconsiledTranxSearchDto searchNotReconsiledTranx(
			DisputeNotReconsiledTranxSearchDto objDisputeNotReconsiledTranxSearchDto,
			int pageNo) throws TPlusException {

		Collection objSearchCollection = null;
		int totalCount = 0;

		StringBuffer sbf = new StringBuffer();
		StringBuffer sbfSelect = new StringBuffer();
		StringBuffer sbfCount = new StringBuffer();

		try {

			sbfSelect.append("select ");
			sbfSelect.append("dcmdto.settlementID, ");
			sbfSelect.append("dcmdto.cardNo, dcmdto.tranxCode.description, ");
			sbfSelect.append("dcmdto.formatAmount, dcmdto.arn, dcmdto.tranxCode.tranxCode ");

			sbfCount.append("select ");
			sbfCount.append("count(dcmdto.settlementID) ");

			sbf.append("from DisputeCleaningMasterDto dcmdto ");
			sbf.append("where (dcmdto.usageCode = '1' or dcmdto.usageCode is null) ");
			sbf.append("and dcmdto.isDispute = 'Y' ");
			sbf.append("and (dcmdto.status = '1' or dcmdto.status = '2') ");

			if (objDisputeNotReconsiledTranxSearchDto.getCardNo() != null
					&& !objDisputeNotReconsiledTranxSearchDto.getCardNo()
							.equals("")) {
				sbf.append("and dcmdto.cardNo = '"
						+ objDisputeNotReconsiledTranxSearchDto.getCardNo()
						+ "' ");
			}

			if (objDisputeNotReconsiledTranxSearchDto.getAuthCode() != null
					&& !objDisputeNotReconsiledTranxSearchDto.getAuthCode()
							.equals("")) {
				sbf.append("and dcmdto.athorizationCode = '"
						+ objDisputeNotReconsiledTranxSearchDto.getAuthCode()
						+ "' ");
			}

			if (objDisputeNotReconsiledTranxSearchDto.getArn() != null
					&& !objDisputeNotReconsiledTranxSearchDto.getArn().equals(
							"")) {
				sbf
						.append("and dcmdto.arn = '"
								+ objDisputeNotReconsiledTranxSearchDto
										.getArn() + "' ");
			}

			if (objDisputeNotReconsiledTranxSearchDto.getAmtFrom() != null
					&& !objDisputeNotReconsiledTranxSearchDto.getAmtFrom()
							.equals("")) {
				sbf.append("and dcmdto.formatAmount >= "
						+ objDisputeNotReconsiledTranxSearchDto.getAmtFrom()
						+ " ");
			}

			if (objDisputeNotReconsiledTranxSearchDto.getAmtTo() != null
					&& !objDisputeNotReconsiledTranxSearchDto.getAmtTo()
							.equals("")) {
				sbf.append("and dcmdto.formatAmount <= "
						+ objDisputeNotReconsiledTranxSearchDto.getAmtTo()
						+ " ");
			}

			sbf.append("order by dcmdto.settlementID desc");

			objSearchCollection = getSearchList((sbfSelect.append(sbf))
					.toString(), pageNo);
			objDisputeNotReconsiledTranxSearchDto
					.setSearchList(objSearchCollection);

			totalCount = getSearchTotalCount((sbfCount.append(sbf)).toString());
			objDisputeNotReconsiledTranxSearchDto.setTotalCount(totalCount);

		} catch (Exception e) {
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error:in DisputeManagementDAOImpl search method" + e);
		}

		return objDisputeNotReconsiledTranxSearchDto;
	}

	public ChargeBackResendSearchDto CBResendsearch(
			ChargeBackResendSearchDto objChargeBackResendSearchDto, int pageNo)
			throws TPlusException {

		Collection objSearchCollection = null;
		int totalCount = 0;

		StringBuffer sbf = new StringBuffer();
		StringBuffer sbfSelect = new StringBuffer();
		StringBuffer sbfCount = new StringBuffer();

		try {

			sbfSelect.append("select ");
			sbfSelect.append("dmdto.disputeCaseNo, ");
			sbfSelect.append("dmdto.disputeType.description, ");
			sbfSelect.append("dmdto.disputeReason.chargeBackReason, ");
			sbfSelect.append("dmdto.disputeAmt, ");
			sbfSelect.append("dmdto.disputeCurrency.currencyName, ");
			sbfSelect.append("dmdto.arn, ");
			sbfSelect.append("to_char(dmdto.disputeCreatedDate, '"
					+ ICacisiss.IDateStuff.DATETIME_FORMAT_DISPLAY + "'), ");
			sbfSelect.append("dmdto.createdDays, ");
			sbfSelect.append("dmdto.disputeReason.timeLimit ");

			sbfCount.append("select ");
			sbfCount.append("count(dmdto.disputeCaseNo) ");

			sbf
					.append("from DisputeMasterDto dmdto, DisputeCleaningMasterDto dcmdto ");

			sbf.append("where dmdto.settlementId=dcmdto.settlementID ");
			sbf.append("and dmdto.resend = 'Y' ");
			sbf.append("and dmdto.status.id <> 'C' ");

			if (objChargeBackResendSearchDto.getAuthCode() != null
					&& !objChargeBackResendSearchDto.getAuthCode().equals("")) {
				sbf.append("and dcmdto.athorizationCode = '"
						+ objChargeBackResendSearchDto.getAuthCode() + "' ");
			}

			if (objChargeBackResendSearchDto.getArn() != null
					&& !objChargeBackResendSearchDto.getArn().equals("")) {
				sbf.append("and dmdto.arn = '"
						+ objChargeBackResendSearchDto.getArn() + "' ");
			}

			if (objChargeBackResendSearchDto.getAmtFrom() != null
					&& !objChargeBackResendSearchDto.getAmtFrom().equals("")) {
				sbf.append("and dmdto.disputeAmt >= "
						+ objChargeBackResendSearchDto.getAmtFrom() + " ");
			}

			if (objChargeBackResendSearchDto.getAmtTo() != null
					&& !objChargeBackResendSearchDto.getAmtTo().equals("")) {
				sbf.append("and dmdto.disputeAmt <= "
						+ objChargeBackResendSearchDto.getAmtTo() + " ");
			}

			if (objChargeBackResendSearchDto.getStartDate() != null
					&& !objChargeBackResendSearchDto.getStartDate().equals("")) {
				sbf.append("and dmdto.disputeCreatedDate >= TO_DATE('"
						+ objChargeBackResendSearchDto.getStartDate() + " "
						+ ICacisiss.IDateStuff.START_TIME_PART + "', '"
						+ ICacisiss.IDateStuff.DATETIME_FORMAT_VALIDATION
						+ "') ");
			}

			if (objChargeBackResendSearchDto.getEndDate() != null
					&& !objChargeBackResendSearchDto.getEndDate().equals("")) {
				sbf.append("and dmdto.disputeCreatedDate <= TO_DATE('"
						+ objChargeBackResendSearchDto.getEndDate() + " "
						+ ICacisiss.IDateStuff.END_TIME_PART + "', '"
						+ ICacisiss.IDateStuff.DATETIME_FORMAT_VALIDATION
						+ "') ");
			}

			sbf.append("order by dmdto.disputeCaseNo desc");

			objSearchCollection = getSearchList((sbfSelect.append(sbf))
					.toString(), pageNo);
			objChargeBackResendSearchDto.setSearchList(objSearchCollection);

			totalCount = getSearchTotalCount((sbfCount.append(sbf)).toString());
			objChargeBackResendSearchDto.setTotalCount(totalCount);

		} catch (Exception e) {
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error:in DisputeManagementDAOImpl CBsearch method" + e);
		}

		return objChargeBackResendSearchDto;
	}

	public boolean updateChargeBack(String disCaseNo) throws TPlusException {

		boolean boolUpdate = false;
		Transaction tx = null;

		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			String sql = "UPDATE DisputeMasterDto SET status=:status WHERE disputeCaseNo=:disputeCaseNo";

			int count = session.createQuery(sql).setString("disputeCaseNo", disCaseNo)
								.setString("status", "C").executeUpdate();

			tx.commit();
			if (count > 0)
				boolUpdate = true;

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in DisputeManagementDAOImpl updateChargeBack  method :"
							+ e);
		} finally {
			HibernetFactory.closeSession();
		}

		return boolUpdate;

	}

	public boolean updateResendChargeBack(DisputeMasterDto objDisputeMasterDto,
			DisputeCaseEventDto objDisputeCaseEventDto) throws TPlusException {

		boolean boolInsert = false;
		Transaction tx = null;

		try {

			Session session = HibernetFactory.currentSession();

			tx = session.beginTransaction();

			session.saveOrUpdate(objDisputeMasterDto);
			session.save(objDisputeCaseEventDto);

			tx.commit();
			boolInsert = true;

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"DisputeManagementDAOImpl updateResendChargeBack  method :" + e);
		} finally {
			HibernetFactory.closeSession();
		}

		return boolInsert;
	}

}
