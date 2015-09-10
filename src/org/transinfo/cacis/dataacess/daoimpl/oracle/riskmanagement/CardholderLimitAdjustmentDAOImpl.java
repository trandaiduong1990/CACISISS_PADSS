package org.transinfo.cacis.dataacess.daoimpl.oracle.riskmanagement;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.transinfo.cacis.TPlusCodes;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.HibernetDAO.HibernetFactory;
import org.transinfo.cacis.dataacess.dao.riskmanagement.CardholderLimitAdjustmentDAO;
import org.transinfo.cacis.dataacess.daoimpl.oracle.BaseDAOImpl;
import org.transinfo.cacis.debug.DebugWriter;
import org.transinfo.cacis.dto.riskmanagement.CardLimitsDto;
import org.transinfo.cacis.dto.riskmanagement.CardholderLimitAdjustmentDto;
import org.transinfo.cacis.dto.riskmanagement.CardholderLimitAdjustmentSearchDto;

public class CardholderLimitAdjustmentDAOImpl extends BaseDAOImpl implements
		CardholderLimitAdjustmentDAO {

	public Collection search(CardholderLimitAdjustmentSearchDto objSearchDto,
			int pageNo) throws TPlusException {

		Collection objSearchCollection = null;
		StringBuffer sbf = new StringBuffer();
		try {

			sbf
					.append("select af.temporaryLimitId, af.cardNumber, to_char(af.fromDate,'dd-MM-yyyy'), ");
			sbf
					.append("to_char(af.toDate,'dd-MM-yyyy') FROM  CardholderLimitAdjustmentDto af ");
			sbf.append("where NOT af.status = 'I'");

			if (objSearchDto.getCardNumber() >0) {
				sbf.append("and af.cardNumber like "+ objSearchDto.getCardNumber());
			}
			objSearchCollection = getSearchList(sbf.toString(), pageNo);

		} catch (Exception e) {
			System.out
					.println("Error while retrieving the CardholderLimitAdjustment Search Info"
							+ e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving the CardholderLimitAdjustment Search Info"
							+ e);
		} finally {
			HibernetFactory.closeSession();
		}
		return objSearchCollection;
	}

	public CardLimitsDto getCardLimits(
			String cardNumber) throws TPlusException {
		CardLimitsDto apd = null;
		Transaction tx = null;
		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			System.out.println(cardNumber);
			Query qry = session
					.createQuery("from CardLimitsDto where cardNumber="
							+ cardNumber + " ");
			List list = qry.list();
			apd = (CardLimitsDto) list.get(0);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}

			System.out.println("Error in getCardLimits method"
					+ e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving the getCardLimits"
							+ e);
		} finally {
			HibernetFactory.closeSession();
		}
		return apd;
	}


	public CardholderLimitAdjustmentDto getCardholderLimitAdjustment(
			String temporaryLimitId) throws TPlusException {
		CardholderLimitAdjustmentDto apd = null;
		Transaction tx = null;
		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			System.out.println(temporaryLimitId);
			// session.get give exception of class cast where rule id is integer
			// so List list = qry.list(); is used for this screen.
			// apd = (CardholderLimitAdjustmentDto)
			// session.get(CardholderLimitAdjustmentDto.class, cardNumber);
			Query qry = session
					.createQuery("from CardholderLimitAdjustmentDto where temporaryLimitId="
							+ temporaryLimitId + " ");
			List list = qry.list();
			apd = (CardholderLimitAdjustmentDto) list.get(0);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}

			System.out.println("Error in getCardholderLimitAdjustment method"
					+ e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving the getCardholderLimitAdjustment"
							+ e);
		} finally {
			HibernetFactory.closeSession();
		}
		return apd;
	}

	public boolean add(CardholderLimitAdjustmentDto objChLimAdjDto)
			throws TPlusException {
		boolean bolExecute = false;
		Transaction tx = null;
		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			session.save(objChLimAdjDto);
			session.flush();
			bolExecute = true;
			tx.commit();
			System.out
					.println("CardholderLimitAdjustmentDto object persisted to the database.");

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.out
					.println("Exception in CardholderLimitAdjustmentDAOImpl saveMethod"
							+ e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while saving data in CardholderLimitAdjustmentDAOImpl"
							+ e);
		} finally {
			HibernetFactory.closeSession();
		}
		return bolExecute;

	}

	public boolean update(CardholderLimitAdjustmentDto objChLimAdjDto)
			throws TPlusException {
		boolean bolExecute = false;
		Transaction tx = null;
		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			session.update(objChLimAdjDto);
			session.flush();
			tx.commit();
			System.out
					.println("CardholderLimitAdjustmentDto object updated to the database.");
			bolExecute = true;
		}

		catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.out
					.println("Error while updating CardholderLimitAdjustment data "
							+ e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while updating CardholderLimitAdjustment data" + e);
		} finally {
			HibernetFactory.closeSession();
		}

		return bolExecute;
	}

	public boolean delete(CardholderLimitAdjustmentDto objChLimAdjDto)
			throws TPlusException {
		boolean bolExecute = false;
		Transaction tx = null;
		try{
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			String sql = "UPDATE CardholderLimitAdjustmentDto SET status =:status WHERE temporaryLimitId=:temporarylimitid";
			int count = session.createQuery(sql)
						.setCharacter("status",objChLimAdjDto.getStatus())
						.setLong("temporarylimitid",objChLimAdjDto.getTemporaryLimitId())
						.executeUpdate();

			tx.commit();
			if (count > 0)
			{
				bolExecute = true;
				System.out.println("CardholderLimitAdjustment successfully deleted.");
			}
		}  catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.out
					.println("Error while deleting CardholderLimitAdjustment "
							+ e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while deleting CardholderLimitAdjustment" + e);
		} finally {
			HibernetFactory.closeSession();
		}
		return bolExecute;
	}

	public int checkExistrecord(CardholderLimitAdjustmentDto objDto, int mode)
			throws TPlusException {
		if (DebugWriter.boolDebugEnabled)
			DebugWriter.write("CARDHOLDER_LIMIT_ADJ:checkExistrecord");

		System.out.println("mode = "+mode);
		System.out.println("ADD = "+ADD);
		System.out.println("UPDATE = "+UPDATE);
		System.out.println("CREATE = "+CREATE);
		int res = 0;
		try {
			Session session = HibernetFactory.currentSession();
			System.out.println(objDto.getCardNumber());
			boolean overlap = false;
			String overlapSQL = "";
			Query qry = null;

			if (mode == ADD || mode == UPDATE) {
				if (mode == ADD) {
					overlapSQL = "select to_char(af.fromDate,'MM/DD/YYYY HH24:MI:SS'), to_char(af.toDate,'MM/DD/YYYY HH24:MI:SS') ";
					overlapSQL += "from CardholderLimitAdjustmentDto af ";
					overlapSQL += "where af.cardNumber=:cardnumber";
					qry = session.createQuery(overlapSQL);
					qry.setLong("cardnumber", objDto.getCardNumber());
				} else if (mode == UPDATE) {
					overlapSQL = "select to_char(af.fromDate,'MM/DD/YYYY HH24:MI:SS'), to_char(af.toDate,'MM/DD/YYYY HH24:MI:SS') ";
					overlapSQL += "from CardholderLimitAdjustmentDto af ";
					overlapSQL += "where af.cardNumber=:cardnumber and not af.temporaryLimitId=:temporarylimitid ";
					qry = session.createQuery(overlapSQL);
					qry.setLong("cardnumber", objDto.getCardNumber());
					qry.setLong("temporarylimitid", objDto
							.getTemporaryLimitId());
				}
				List list = qry.list();
				Date fromDate = objDto.getFromDate();
				Date toDate = objDto.getToDate();
				int fromDateCount = 0;
				int toDateCount = 1;
				for (Iterator it = list.iterator(); it.hasNext();) {
					Object[] temp = (Object[]) it.next();
					System.out.println("....");
					Date ExistFromDate = new Date(
							((Object) temp[fromDateCount]).toString());
					Date ExistToDate = new Date(((Object) temp[toDateCount])
							.toString());
					System.out.println("ExistFromDate = " + ExistFromDate);
					System.out.println("fromDate = " + fromDate);
					System.out.println("ExistToDate = " + ExistToDate);
					System.out.println("toDate = " + toDate);
					int startDateFrame = fromDate.compareTo(ExistFromDate);
					int endDateFrame = toDate.compareTo(ExistToDate);
					int startEndFrame = fromDate.compareTo(ExistToDate);
					int endStartFrame = toDate.compareTo(ExistFromDate);

					if (startDateFrame == 0 || endDateFrame == 0
							|| startEndFrame == 0 || endStartFrame == 0)
						overlap = true;
					if (startDateFrame > 0) {
						if (endDateFrame > 0) {
							if (startEndFrame < 0)
								overlap = true;
						} else
							overlap = true;
					} else {
						if (endDateFrame > 0)
							overlap = true;
						else {
							if (endStartFrame > 0)
								overlap = true;
						}
					}
					if (overlap) {
						res = 1;
						break;
					}
				}
			} else if (mode == CREATE) {
				qry = session.createQuery("select count(*) from CardLimitsDto prod where prod.cardNumber=:cardnumber");
				qry.setLong("cardnumber", objDto.getCardNumber());
				List list = qry.list();
				res = ((Integer) list.get(0)).intValue();
			}
			System.out.println(res);
			System.out
					.println("After CardholderLimitAdjustmentDAOImpl checkExistrecord()");
		} catch (Exception e) {
			System.out.println("6" + e);
		} finally {
			HibernetFactory.closeSession();
		}
		return res;

	}

}
