package org.transinfo.cacis.dataacess.daoimpl.oracle.authorization;

import java.util.Collection;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.transinfo.cacis.TPlusCodes;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.HibernetDAO.HibernetFactory;
import org.transinfo.cacis.dataacess.dao.authorization.SMSServiceDAO;
import org.transinfo.cacis.dataacess.daoimpl.oracle.BaseDAOImpl;
import org.transinfo.cacis.debug.DebugWriter;
import org.transinfo.cacis.dto.authorization.SMSServiceDto;
import org.transinfo.cacis.dto.authorization.SMSServiceSearchDto;

public class SMSServiceDAOImpl extends BaseDAOImpl implements SMSServiceDAO {

	public Collection search(SMSServiceSearchDto objSearchDto, int pageNo)
			throws TPlusException {

		Collection objSearchCollection = null;
		StringBuffer sbf = new StringBuffer();
		try {

			sbf.append("select af.cardNumber, af.accountEnquiry, af.paymentAlert, af.generalMsg, af.status, to_char(af.lastUpdatedDate,'dd-MM-yyyy') ");
			sbf.append(" FROM  SMSServiceDto af ");
			System.out.println("sbf===> " + sbf.toString());
			if (objSearchDto.getCardNumber() != null
					&& !objSearchDto.getCardNumber().equals("")) {
				sbf.append("where af.cardNumber like '%"
						+ objSearchDto.getCardNumber() + "%' ");
			}
			objSearchCollection = getSearchList(sbf.toString(), pageNo);
			System.out.println("Size===> " + objSearchCollection.size());
		} catch (Exception e) {
			System.out
					.println("Error while retrieving the SMSService Search Info"
							+ e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving the SMSService Search Info" + e);
		} finally {
			HibernetFactory.closeSession();
		}
		return objSearchCollection;
	}

	public boolean add(SMSServiceDto objSMSServiceDto) throws TPlusException {

		boolean bolExecute = false;
		try {

			Session session = HibernetFactory.currentSession();

			org.hibernate.Transaction tx = session.beginTransaction();

			session.save(objSMSServiceDto);
			System.out
					.println("SMSServiceDto object persisted to the database.");
			tx.commit();
			session.flush();
			session.close();
			bolExecute = true;

		} catch (Exception ex) {
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving Info" + ex.getMessage());
		}

		return bolExecute;

	}

	public SMSServiceDto getSMSService(long cardNumber) throws TPlusException {
		SMSServiceDto apd = null;
		Transaction tx = null;
		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			System.out.println(cardNumber);
			Query qry = session
					.createQuery("from SMSServiceDto where cardNumber="
							+ cardNumber + " ");
			List list = qry.list();
			apd = (SMSServiceDto) list.get(0);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}

			System.out.println("Error in getSMSService method" + e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving the getSMSService" + e);
		} finally {
			HibernetFactory.closeSession();
		}
		return apd;
	}

	public boolean update(SMSServiceDto objSMSServiceDto) throws TPlusException {
		boolean bolExecute = false;
		Transaction tx = null;
		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			session.update(objSMSServiceDto);
			session.flush();
			tx.commit();
			System.out.println("SMSServiceDto object updated to the database.");
			bolExecute = true;
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.out.println("Error while updating SMSServiceDto data " + e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while updating SMSServiceDto data" + e);
		} finally {
			HibernetFactory.closeSession();
		}

		return bolExecute;
	}
	
	public int checkExistRecord(SMSServiceDto objDto) throws TPlusException {
		if (DebugWriter.boolDebugEnabled)
			DebugWriter.write("SMSServiceIMPL:checkExistrecord");

		int res = 0;
		try {
			//long a = Long.parseLong("123456789123456789");
			//String b = Long.toString(a);
			Session session = HibernetFactory.currentSession();
			System.out.println(objDto.getCardNumber());
			Query qry = session
					.createQuery("select count(*) from CardsDto prod where prod.cardNumber=:cardnumber");
			qry.setString("cardnumber", Long.toString(objDto.getCardNumber()));
			List list = qry.list();
			res = ((Integer) list.get(0)).intValue();
			System.out.println(res);
			System.out.println("After SMSServiceDAOImpl checkExistrecord()");
		}
		catch (Exception e) {
			System.out.println("6" + e);
		}
		finally {
			HibernetFactory.closeSession();
		}
		return res;
	}
	
	public int checkDuplicateRecord(SMSServiceDto objDto) throws TPlusException {
		if (DebugWriter.boolDebugEnabled)
			DebugWriter.write("SMSServiceIMPL:checkExistrecord");

		int res = 0;
		try {
			Session session = HibernetFactory.currentSession();
			System.out.println(objDto.getCardNumber());
			Query qry = session
					.createQuery("select count(*) from SMSServiceDto prod where prod.cardNumber=:cardnumber");
			qry.setLong("cardnumber", objDto.getCardNumber());
			List list = qry.list();
			res = ((Integer) list.get(0)).intValue();
			System.out.println(res);
			System.out.println("After SMSServiceDAOImpl checkExistrecord()");
		}
		catch (Exception e) {
			System.out.println("6" + e);
		}
		finally {
			HibernetFactory.closeSession();
		}
		return res;
	}
}
