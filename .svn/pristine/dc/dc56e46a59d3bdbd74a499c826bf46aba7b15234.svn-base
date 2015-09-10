package org.transinfo.cacis.dataacess.daoimpl.oracle.riskmanagement;

import java.util.Collection;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.transinfo.cacis.TPlusCodes;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.HibernetDAO.HibernetFactory;
import org.transinfo.cacis.dataacess.dao.riskmanagement.ChargeBack1DAO;
import org.transinfo.cacis.dataacess.daoimpl.oracle.BaseDAOImpl;
import org.transinfo.cacis.debug.DebugWriter;
import org.transinfo.cacis.dto.riskmanagement.OriginalRequestDto;
import org.transinfo.cacis.dto.riskmanagement.OriginalRequestSearchDto;

public class ChargeBack1DAOImpl extends BaseDAOImpl implements ChargeBack1DAO {

	public Collection search(OriginalRequestSearchDto objSearchDto, int pageNo)
			throws TPlusException {

		Collection objSearchCollection = null;
		StringBuffer sbf = new StringBuffer();
		try {

			sbf
					.append("select af.settlementId, af.cardNumber, to_char(af.tranxDate,'DD-MM-YYYY HH24:MI'), ");
			sbf
					.append("af.amountCurr, af.refNumber, af.merchantName, af.status FROM  OriginalRequestDto af ");
			sbf.append("where af.cardNumber like '"
					+ objSearchDto.getCardNumber() + "' ");

			if (objSearchDto.getRefNumber() != null
					&& !objSearchDto.getRefNumber().equals("")) {
				sbf.append("and af.refNumber = '" + objSearchDto.getRefNumber()
						+ "' ");
			}

			System.out.println("objSearchDto.getTranxDate() => "
					+ objSearchDto.getStrTranxDate());

			if (objSearchDto.getStrTranxDate() != null
					&& !objSearchDto.getStrTranxDate().equals("")) {
				String strTranxDate = objSearchDto.getStrTranxDate();
				sbf
						.append("and to_date(to_char(af.tranxDate,'DD/MM/YYYY'),'DD/MM/YYYY') = TO_DATE('"
								+ strTranxDate + "', 'DD/MM/YYYY')) ");
			}
			objSearchCollection = getSearchList(sbf.toString(), pageNo);

		} catch (Exception e) {
			System.out
					.println("Error while retrieving the OriginalRequest Search Info"
							+ e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving the OriginalRequest Search Info"
							+ e);
		} finally {
			HibernetFactory.closeSession();
		}
		return objSearchCollection;
	}

	public OriginalRequestDto getOriginalRequest(long settlementId)
			throws TPlusException {
		OriginalRequestDto apd = null;
		Transaction tx = null;
		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			System.out.println(settlementId);
			// session.get give exception of class cast where settlement id is
			// long
			// so List list = qry.list(); is used for this screen.
			// apd = (OriginalRequestDto)
			// session.get(OriginalRequestDto.class, settlementId);
			Query qry = session
					.createQuery("from OriginalRequestDto where settlementId="
							+ settlementId + " ");
			List list = qry.list();
			apd = (OriginalRequestDto) list.get(0);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}

			System.out.println("Error in getOriginalRequest method" + e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving the getOriginalRequest" + e);
		} finally {
			HibernetFactory.closeSession();
		}
		return apd;

	}

	public boolean update(OriginalRequestDto objOriReqDto)
			throws TPlusException {
		boolean bolExecute = false;
		Transaction tx = null;
		int UpdateCount = 0;
		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			if (objOriReqDto.getStatus() == 2) {
				objOriReqDto.setStatus(3);
				objOriReqDto.setSent("N");
				String UpdateSql = "UPDATE OriginalRequestDto SET status =:requeststatus, sent=:sentvalue, remarks=:remark WHERE settlementId=:settlementid";
				UpdateCount = session.createQuery(UpdateSql).setInteger(
						"requeststatus", objOriReqDto.getStatus()).setString(
						"sentvalue", objOriReqDto.getSent()).setString(
						"remark", objOriReqDto.getRemarks()).setLong(
						"settlementid", objOriReqDto.getSettlementId())
						.executeUpdate();
			} else {
				String UpdateSql = "UPDATE OriginalRequestDto SET remarks=:remark WHERE settlementId=:settlementid";
				UpdateCount = session.createQuery(UpdateSql).setString(
						"remark", objOriReqDto.getRemarks()).setLong(
						"settlementid", objOriReqDto.getSettlementId())
						.executeUpdate();
			}
			session.flush();
			tx.commit();
			System.out
					.println("OriginalRequestDto object updated to the database.");
			if (UpdateCount > 0)
				bolExecute = true;
		}

		catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.out
					.println("Error while updating OriginalRequest data " + e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while updating OriginalRequest data" + e);
		} finally {
			HibernetFactory.closeSession();
		}

		return bolExecute;
	}

	public int checkExistrecord(OriginalRequestDto objDto)
			throws TPlusException {
		if (DebugWriter.boolDebugEnabled)
			DebugWriter.write("ORIGINALREQUESTIMPL:checkExistrecord");
		int res = 0;
		try {
			Session session = HibernetFactory.currentSession();
			System.out.println("objDto.getCardNumber() = "
					+ objDto.getCardNumber());
			System.out.println("objDto.getSettlementId() = "
					+ objDto.getSettlementId());
			if (objDto.getSettlementId() != 0) {
				Query qry = session
						.createQuery("select count(*) from OriginalRequestDto prod where prod.settlementId=:settlementid");
				qry.setLong("settlementid", objDto.getSettlementId());
				List list = qry.list();
				res = ((Integer) list.get(0)).intValue();
			} else {
				Query qry = session
						.createQuery("select count(*) from OriginalRequestDto prod where prod.cardNumber=:cardnumber");
				qry.setString("cardnumber", objDto.getCardNumber());
				List list = qry.list();
				res = ((Integer) list.get(0)).intValue();
			}
			System.out
					.println("After OriginalRequestDAOImpl checkExistrecord()");
		} catch (Exception e) {
			System.out.println("6" + e);
		} finally {
			HibernetFactory.closeSession();
		}
		return res;
	}
}
