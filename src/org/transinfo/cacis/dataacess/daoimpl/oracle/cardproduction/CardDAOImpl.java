package org.transinfo.cacis.dataacess.daoimpl.oracle.cardproduction;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.transinfo.cacis.TPlusCodes;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.HibernetDAO.HibernetFactory;
import org.transinfo.cacis.dataacess.dao.cardproduction.CardDAO;
import org.transinfo.cacis.dataacess.daoimpl.oracle.BaseDAOImpl;
import org.transinfo.cacis.dto.cardproduction.CardsDto;
import org.transinfo.cacis.dto.customerservice.CardStatusRemarksDto;

@SuppressWarnings("unchecked")
public class CardDAOImpl extends BaseDAOImpl implements CardDAO {

	public CardsDto getCard(String cardNo) throws TPlusException {

		CardsDto objCardsDto = null;
		//Transaction tx = null;
		try {
			Session session = HibernetFactory.currentSession();
			//tx = session.beginTransaction();

			objCardsDto = (CardsDto) session.get(CardsDto.class, Long.valueOf(cardNo));

			if (objCardsDto != null) {
				Hibernate.initialize(objCardsDto.getCustAccountDto());
			}

			//tx.commit();

		} catch (Exception e) {
			/*if (tx != null) {
				tx.rollback();
			}*/
			System.out.println("Error in CardDAOImpl getCard method : "
					+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in CardDAOImpl getCard  method :" + e);
		} finally {
			HibernetFactory.closeSession();
		}
		return objCardsDto;

	}
	
	public CardsDto getCardByEncryptedData(String ecardNo) throws TPlusException {

		CardsDto objCardsDto = null;

		try {
			
			Session session = HibernetFactory.currentSession();
			
			Query qry = session.createQuery("from CardsDto where encryptedCardNo='"+ecardNo+"'");
			List listCard = qry.list();
			if(!listCard.isEmpty())
			{
				objCardsDto = (CardsDto) listCard.get(0);
			}

			if (objCardsDto != null) {
				Hibernate.initialize(objCardsDto.getCustAccountDto());
			}

		} catch (Exception e) {
			System.out.println("Error in CardDAOImpl getCard method : " + e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR, "Error: in CardDAOImpl getCard  method :" + e);
		} finally {
			HibernetFactory.closeSession();
		}
		
		return objCardsDto;

	}

	public boolean isCardEmbossed(String cardNo) throws TPlusException {

		boolean isEmbossed = false;
		StringBuffer sbf = new StringBuffer();
		Transaction tx = null;
		try {

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			sbf.append("FROM CardEmbossingDto cedo ");
			sbf.append("where cedo.card.cardNumber = " + cardNo + " ");
			sbf.append("and cedo.status = 6 ");

			Query qry = session.createQuery(sbf.toString());
			List listCards = qry.list();

			if (listCards.size() > 0) {
				isEmbossed = true;
			}

			tx.commit();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}

			System.out.println("Error in isCardEmbossed method" + e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving the isCardEmbossed" + e);
		} finally {
			HibernetFactory.closeSession();
		}
		return isEmbossed;
	}

	public CardStatusRemarksDto getCardStatusRemarks(long cardStatusId,
			String cardNo) throws TPlusException {

		CardStatusRemarksDto objDto = null;
		Transaction tx = null;

		try {

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			Query qry = session
			.createQuery("from CardStatusRemarksDto where cardNo='"
					+ cardNo + "' and statusNo=" + cardStatusId);
			List listCard = qry.list();
			if (listCard.size() > 0) {
				objDto = (CardStatusRemarksDto) listCard.get(0);
			}
			tx.commit();
		}

		catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.out
			.println("Error while getting CardStatusRemarksDto data in getCardStatusRemarks method"
					+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while getting CardStatusRemarksDto data in getCardStatusRemarks method"
					+ e);
		} finally {
			HibernetFactory.closeSession();
		}

		return objDto;

	}

	@Override
	public String getCardType(String cardNo) throws TPlusException {
		String res = "";

		try {
			Session session = HibernetFactory.currentSession();

			CardsDto objCardsDto = (CardsDto) session.get(CardsDto.class, Long.valueOf(cardNo));
			if(objCardsDto != null){
				res = objCardsDto.getCardProductsDto().getCardProductType().getCardProductType();
			}

		} catch (Exception e) {
			System.out.println("Error in CardDAOImpl getCard method : "
					+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in CardDAOImpl getCard  method :" + e);
		} finally {
			HibernetFactory.closeSession();
		}
		return res;
	}

}
