package org.transinfo.cacis.dataacess.daoimpl.oracle.customerservice;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.transinfo.cacis.TPlusCodes;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.common.constants.CommonCodes;
import org.transinfo.cacis.dataacess.HibernetDAO.HibernetFactory;
import org.transinfo.cacis.dataacess.dao.customerservice.CardChangeDAO;
import org.transinfo.cacis.dataacess.daoimpl.oracle.BaseDAOImpl;
import org.transinfo.cacis.dto.cardproduction.CardsDto;
import org.transinfo.cacis.dto.csr.CustomerInfoDto;
import org.transinfo.cacis.dto.customerservice.CardActivityDto;
import org.transinfo.cacis.dto.customerservice.CardChangeDto;
import org.transinfo.cacis.dto.customerservice.CardChangeLogDto;
import org.transinfo.cacis.dto.settings.CardProductDto;

@SuppressWarnings("unchecked")
public class CardChangeDAOImpl extends BaseDAOImpl implements CardChangeDAO {

	public CardsDto getCardDto(String cardNo) throws TPlusException {

		CardsDto objDto = null;
		Transaction tx = null;

		try {

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			objDto = (CardsDto) session.get(CardsDto.class, Long
					.valueOf(cardNo).longValue());
			objDto.getCardProductsDto().getCardProductName();
			objDto.getCardProductsDto().getCardType().getCardType();
			tx.commit();
		}

		catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.out
					.println("Error while getting CardsDto data in getCardDto method"
							+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while getting CardsDto data in getCardDto method"
							+ e);
		} finally {
			HibernetFactory.closeSession();
		}

		return objDto;
	}

	public CustomerInfoDto getCustomerInfoDto(String customerId)
			throws TPlusException {

		CustomerInfoDto objDto = null;
		Transaction tx = null;

		try {

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			objDto = (CustomerInfoDto) session.get(CustomerInfoDto.class,
					customerId);
			tx.commit();
		}

		catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.out
					.println("Error while getting CustomerInfoDto data in getCustomerInfoDto method"
							+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while getting CustomerInfoDto data in getCustomerInfoDto method"
							+ e);
		} finally {
			HibernetFactory.closeSession();
		}

		return objDto;
	}

	public CardChangeDto getCardChangeDto(String cardNo) throws TPlusException {

		CardChangeDto objDto = null;
		Transaction tx = null;

		try {

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			objDto = (CardChangeDto) session.get(CardChangeDto.class, Long
					.valueOf(cardNo).longValue());
			tx.commit();
		}

		catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.out
					.println("Error while getting CardChangeDto data in getCardChangeDto method"
							+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while getting CardChangeDto data in getCardChangeDto method"
							+ e);
		} finally {
			HibernetFactory.closeSession();
		}

		return objDto;
	}

	public boolean add(CardChangeDto objCardChangeDto) throws TPlusException {

		boolean boolAdd = false;
		Transaction tx = null;
		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			session.save(objCardChangeDto);

			tx.commit();
			boolAdd = true;

		}

		catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.out.println("Error in CardChangeDAOImpl add method : "
					+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in CardChangeDAOImpl add  method :" + e);
		} finally {
			HibernetFactory.closeSession();
		}
		return boolAdd;

	}

	public Collection processSearch(CardChangeDto objSearchDto, int pageNo)
			throws TPlusException {

		Collection objSearchCollection = new ArrayList();
		StringBuffer sbf = new StringBuffer();

		try {
			sbf.append("select ccd.cardNo, ccd.customerName, ccd.nric, ");
			sbf.append("to_char(ccd.updatedDate,'dd-MM-yyyy') FROM CardChangeDto ccd where 1=1 and ccd.status = 0 ");

			if (objSearchDto.getCardNo() > 0) {
				sbf.append("and ccd.cardNo like '%" + objSearchDto.getCardNo()
						+ "%' ");
			}
			if (objSearchDto.getCustomerName() != null
					&& !objSearchDto.getCustomerName().equals("")) {
				sbf.append("and lower(ccd.customerName) like '%"
						+ objSearchDto.getCustomerName().toLowerCase() + "%' ");
			}
			if (objSearchDto.getNric() != null
					&& !objSearchDto.getNric().equals("")) {
				sbf.append("and lower(ccd.nric) like '%"
						+ objSearchDto.getNric().toLowerCase() + "%' ");
			}
			
			if(!"ALL".equalsIgnoreCase(objSearchDto.getBranchId())){
				sbf.append("and ccd.customer.branchId = '" + objSearchDto.getBranchId() + "' ");
			}
			
			sbf.append("and ccd.customer.issuerId = '" + objSearchDto.getIssuerId() + "' ");

			objSearchCollection = getSearchList(sbf.toString(), pageNo);

		} catch (Exception e) {
			System.out
					.println("Error in  CardChangeDAOImpl processSearch Method"
							+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in  CardChangeDAOImpl processSearch  Method" + e);
		}

		return objSearchCollection;
	}

	public boolean accept(CardChangeDto objCardChangeDto) throws TPlusException {

		boolean accept = false;
		Transaction tx = null;
		int count;

		CardActivityDto objCardActivity = null;
		CardChangeLogDto objCardChangeLogDto = null;
		CardsDto objCardsDto = null;
		StringBuffer sbf = new StringBuffer();
		ArrayList objSearchCollection = new ArrayList();

		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			String sql = "UPDATE CardChangeDto SET status =:status  WHERE cardNo=:cardNo";
			count = session.createQuery(sql).setInteger("status",
					CommonCodes.CARD_CHANGE_ACCEPTED).setLong("cardNo",
					objCardChangeDto.getCardNo()).executeUpdate();

			if (count > 0) {

				// Inserting Data into CardActivity Table
				objCardActivity = new CardActivityDto();
				objCardActivity.setDateTime(new Date());
				objCardActivity.setCardNumber(objCardChangeDto.getCardNo());
				objCardActivity.setActivity("Card Change Application Acccepted");
				objCardActivity.setStationIp(InetAddress.getLocalHost().getHostAddress());
				objCardActivity.setUserId(objCardChangeDto.getUserId());
				objCardActivity.setLastUpdatedBy(objCardChangeDto.getUserId());
				objCardActivity.setUpdatedDate(new Date());
				session.save(objCardActivity);
				
				// inserting into CardChanegLog table
				objCardChangeLogDto = new CardChangeLogDto();
				objCardChangeLogDto.setOldCarddNo(String.valueOf(objCardChangeDto.getCardNo()));
				objCardChangeLogDto.setOldCardProduct(objCardChangeDto.getExistCardProduct());
				objCardChangeLogDto.setNewCardProduct(objCardChangeDto.getChangeCardProduct());
				objCardChangeLogDto.setFlag(CommonCodes.LOG_OLD_NO_INSERTED);
				objCardChangeLogDto.setLastUpdatedDate(new Date());
				objCardChangeLogDto.setLastUpdateBy(objCardChangeDto.getUserId());
				session.save(objCardChangeLogDto);
				
				objCardsDto = (CardsDto) session.get(CardsDto.class, objCardChangeDto.getCardNo());
				String accId = objCardsDto.getAccountId();
				// get supplementary cards and insert into CardChangeLog table
				sbf.append("from CardsDto cdo ");
				sbf.append("where cdo.cardHolderType = 2 and cdo.accountId = '" + accId + "'");
				
				Query qry = session.createQuery(sbf.toString());
				objSearchCollection = (ArrayList) qry.list();
				
				for (Iterator it = objSearchCollection.iterator(); it.hasNext();) {
					CardsDto objCards = (CardsDto) it.next();
					
					// inserting into CardChanegLog table
					objCardChangeLogDto = new CardChangeLogDto();
					objCardChangeLogDto.setOldCarddNo(String.valueOf(objCards.getCardNumber()));
					objCardChangeLogDto.setOldCardProduct(objCardChangeDto.getExistCardProduct());
					objCardChangeLogDto.setNewCardProduct(objCardChangeDto.getChangeCardProduct());
					objCardChangeLogDto.setFlag(CommonCodes.LOG_OLD_NO_INSERTED);
					objCardChangeLogDto.setLastUpdatedDate(new Date());
					objCardChangeLogDto.setLastUpdateBy(objCardChangeDto.getUserId());
					session.save(objCardChangeLogDto);
					
				}
			}
			session.flush();
			tx.commit();
			if (count > 0)
				accept = true;

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.out
					.println("Error while accepting card change in CardChangeDAOImpl AcceptMethod"
							+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while accepting the card change in CardChangeDAOImpl"
							+ e);
		} finally {

			HibernetFactory.closeSession();
		}
		return accept;
	}

	public boolean reject(CardChangeDto objCardChangeDto) throws TPlusException {

		boolean accept = false;
		Transaction tx = null;
		int count;

		CardActivityDto objCardActivity = null;

		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			String sql = "UPDATE CardChangeDto SET status =:status  WHERE cardNo=:cardNo";
			count = session.createQuery(sql).setInteger("status",
					CommonCodes.CARD_CHANGE_REJECTED).setLong("cardNo",
					objCardChangeDto.getCardNo()).executeUpdate();

			if (count > 0) {

				// Inserting Data into CardActivity Table
				objCardActivity = new CardActivityDto();
				objCardActivity.setDateTime(new Date());
				objCardActivity.setCardNumber(objCardChangeDto.getCardNo());
				objCardActivity.setActivity("Card Change Application Rejected");
				objCardActivity.setStationIp(InetAddress.getLocalHost()
						.getHostAddress());
				objCardActivity.setUserId(objCardChangeDto.getUserId());
				objCardActivity.setLastUpdatedBy(objCardChangeDto.getUserId());
				objCardActivity.setUpdatedDate(new Date());

				session.save(objCardActivity);
			}
			session.flush();
			tx.commit();
			if (count > 0)
				accept = true;

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.out
					.println("Error while accepting card change in CardChangeDAOImpl RejectMethod"
							+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while accepting the card change in CardChangeDAOImpl"
							+ e);
		} finally {

			HibernetFactory.closeSession();
		}
		return accept;
	}

	public Map getProductListForChange(String issuerID, String cardNo) throws TPlusException {

		Map cardProductList = new TreeMap();
		Transaction tx = null;
		StringBuffer sbf =new StringBuffer();
		CardsDto objCardsDto = null;

		try {

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			
			objCardsDto = (CardsDto)session.get(CardsDto.class, Long.valueOf(cardNo));
			String cardProductType = objCardsDto.getCardProductsDto().getCardProductType().getCardProductTypeId();
			
			sbf.append("From CardProductDto where issuerId ='" + issuerID + "' and cardProductType = "+cardProductType+" ");
			
			Query qry = session.createQuery(sbf.toString());
			
			List listCardproducts = qry.list();
			for (Iterator it = listCardproducts.iterator(); it.hasNext();) {
				CardProductDto objDto = new CardProductDto();
				objDto = (CardProductDto) it.next();
				cardProductList.put(objDto.getCardProductId(), objDto.getCardProductName());
			}
			tx.commit();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.out
					.println("while retrieving getProductListForChange in CardChangeDAOImpl"
							+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving getProductListForChange in CardChangeDAOImpl"
							+ e);
		} finally {

			HibernetFactory.closeSession();
		}
		return cardProductList;
	}

}
