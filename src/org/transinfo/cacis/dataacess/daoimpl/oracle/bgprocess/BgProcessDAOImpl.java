package org.transinfo.cacis.dataacess.daoimpl.oracle.bgprocess;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.transinfo.cacis.TPlusCodes;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.common.constants.CommonCodes;
import org.transinfo.cacis.dataacess.HibernetDAO.HibernetFactory;
import org.transinfo.cacis.dataacess.dao.bgprocess.BgProcessDAO;
import org.transinfo.cacis.dataacess.daoimpl.oracle.BaseDAOImpl;
import org.transinfo.cacis.dto.applicationforms.CardsRenewalDto;
import org.transinfo.cacis.dto.customerservice.CardActivityDto;

@SuppressWarnings("unchecked")
public class BgProcessDAOImpl extends BaseDAOImpl implements BgProcessDAO {

	public List getClosingCardsList(Date cutOffDate) throws TPlusException {
		List closingCards = new ArrayList();

		StringBuffer sbf = new StringBuffer();
		Transaction tx = null;

		try {

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			sbf.append("from CardDataDto cddto ");
			sbf.append("where cddto.status = '0' ");
			sbf.append("and cddto.closingDate is not null ");
			sbf.append("and cddto.closingDate < :cutoffdate ");

			Query qry = session.createQuery(sbf.toString()).setDate("cutoffdate", cutOffDate);
			closingCards = qry.list();

			tx.commit();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in BgProcessDAOImpl getClosingCardsList method :"
							+ e);

		} finally {
			HibernetFactory.closeSession();
		}

		return closingCards;
	}

	public boolean updateCardClosing(String cardCloseSNo) throws TPlusException {

		StringBuffer sbf = new StringBuffer();
		Transaction tx = null;

		boolean res = false;
		int count = 0;

		try {

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			// updating in CARD_DATA table
			sbf.append("UPDATE CardDataDto ");
			sbf.append("SET ");
			sbf.append("status = '1' , ");
			sbf.append("updatedDate =:modifyDate , ");
			sbf.append("updatedBy = 'BatchProcess' ");
			sbf.append("WHERE ");
			sbf.append("cardDataSerialNo = '" + cardCloseSNo + "' ");

			count = session.createQuery(sbf.toString())
							.setDate("modifyDate", new Date())
							.executeUpdate();

			tx.commit();

			if (count > 0) {
				res = true;
			}

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			throw new TPlusException(
					"Error on BgProcessDAOImpl updateCardClosing method " + e);

		} finally {
			HibernetFactory.closeSession();
		}

		return res;
	}
	
	public List getRenewalCardsList(Date startDate, Date endDate) throws TPlusException {
		List renewalCards = new ArrayList();

		StringBuffer sbf = new StringBuffer();
		Transaction tx = null;

		try {

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			sbf.append("from CardsDto cdto ");
			sbf.append("where to_date(cdto.cardExpDate, 'MM/yy') >=:startDate ");
			sbf.append("and to_date(cdto.cardExpDate, 'MM/yy') <:endDate ");

			Query qry = session.createQuery(sbf.toString())
								.setDate("startDate", startDate)
								.setDate("endDate", endDate);
			renewalCards = qry.list();

			tx.commit();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in BgProcessDAOImpl getRenewalCardsList method :"
							+ e);

		} finally {
			HibernetFactory.closeSession();
		}

		return renewalCards;
	}
	
	public int renewalProcessStatus(String cardNo) throws TPlusException{
		int renewalStatus = -1;
		
		StringBuffer sbf = new StringBuffer();
		
		try{
			Session session = HibernetFactory.currentSession();
			
			sbf.append("from CardsRenewalDto crdto ");
			sbf.append("where crdto.cardNumber =:cardNo ");
			sbf.append("and crdto.status IN (0,1) ");
			
			Query qry = session.createQuery(sbf.toString()).setLong("cardNo", Long.valueOf(cardNo));
			List res = qry.list();
			
			if(res.size() == 0){
				renewalStatus = 0;
			}else{
				renewalStatus = 1;
			}
			
		}catch (Exception e) {
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in BgProcessDAOImpl renewalProcessStatus  method :" + e);
		}finally {
			HibernetFactory.closeSession();
		}
		
		return renewalStatus;
	}

	public boolean insertRenewalAndActivity(CardsRenewalDto objCardsRenewalDto,
			CardActivityDto objActivityDto) throws TPlusException {

		boolean boolAdd = false;
		Transaction tx = null;
		
		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			
			session.save(objCardsRenewalDto);
			session.save(objActivityDto);

			tx.commit();
			boolAdd = true;

		}catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in BgProcessDAOImpl insertRenewal method :" + e);
		} finally {
			HibernetFactory.closeSession();
		}
		
		return boolAdd;

	}
	
	public List getChangeClosingCardsList(Date cutOffDate) throws TPlusException {
		List changeClosingCards = new ArrayList();

		StringBuffer sbf = new StringBuffer();
		Transaction tx = null;

		try {

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			sbf.append("from CardChangeCloseDto cccdto ");
			sbf.append("where cccdto.status = 0 ");
			sbf.append("and cccdto.closingDate is not null ");
			sbf.append("and cccdto.closingDate < :cutoffdate ");

			Query qry = session.createQuery(sbf.toString()).setDate("cutoffdate", cutOffDate);
			changeClosingCards = qry.list();

			tx.commit();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in BgProcessDAOImpl getChangeClosingCardsList method :"
							+ e);

		} finally {
			HibernetFactory.closeSession();
		}

		return changeClosingCards;
	}

	public boolean updateChangeCardClosing(String cardChangeCloseSNo, String cardNo) throws TPlusException {

		StringBuffer sbf = new StringBuffer();
		Transaction tx = null;

		boolean res = false;
		int count = 0;

		try {

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			
			// updating in CARDS table
			sbf.append("UPDATE CardsDto ");
			sbf.append("SET ");
			sbf.append("status = 'C' , ");
			sbf.append("cardStatusId =:sId , ");
			sbf.append("updatedDate =:modifyDate , ");
			sbf.append("lastUpdatedBy = 'BatchProcess' ");
			sbf.append("WHERE ");
			sbf.append("cardNumber =:cardNo ");

			count = session.createQuery(sbf.toString())
							.setDate("modifyDate", new Date())
							.setLong("cardNo", Long.valueOf(cardNo))
							.setInteger("sId", CommonCodes.CARD_CLOSE)
							.executeUpdate();
			
			if(count > 0){

				// updating in CARDCHANGE_CLOSE table
				sbf = new StringBuffer();
				sbf.append("UPDATE CardChangeCloseDto ");
				sbf.append("SET ");
				sbf.append("status = '1' , ");
				sbf.append("updatedDate =:modifyDate , ");
				sbf.append("userId = 'BatchProcess' ");
				sbf.append("WHERE ");
				sbf.append("serialNo = '" + cardChangeCloseSNo + "' ");
	
				count = session.createQuery(sbf.toString())
								.setDate("modifyDate", new Date())
								.executeUpdate();
				
				if (count > 0) {
					tx.commit();
					res = true;
				}
			
			}

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			throw new TPlusException(
					"Error on BgProcessDAOImpl updateChangeCardClosing method " + e);

		} finally {
			HibernetFactory.closeSession();
		}

		return res;
	}

}
