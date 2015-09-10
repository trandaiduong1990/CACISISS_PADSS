package org.transinfo.cacis.dataacess.daoimpl.oracle.settings;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.transinfo.cacis.TPlusCodes;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.common.constants.CommonCodes;
import org.transinfo.cacis.dataacess.HibernetDAO.HibernetFactory;
import org.transinfo.cacis.dataacess.dao.settings.CardProductDAO;
import org.transinfo.cacis.dataacess.daoimpl.oracle.BaseDAOImpl;
import org.transinfo.cacis.dto.settings.CardProductDto;
import org.transinfo.cacis.dto.settings.CardProductFeeDto;
import org.transinfo.cacis.dto.settings.CardProductSearchDto;
import org.transinfo.cacis.dto.settings.CardPromotionDto;

@SuppressWarnings("unchecked")
public class CardProductDAOImpl extends BaseDAOImpl implements CardProductDAO {

	private Logger logger = Logger.getLogger(CardProductDAOImpl.class);

	/*
	 * This method is used for getting the CardproductTypeList
	 */
	public Collection search(CardProductSearchDto objSearchDto, int pageNo)
			throws TPlusException {

		Collection objSearchCollection = new ArrayList();
		StringBuffer sbf = new StringBuffer();
		try {

			sbf
					.append("select cp.cardProductId,cp.cardProductName,to_char(cp.updatedDate,'dd-MM-yyyy HH24:mi:ss') From CardProductDto cp where cp.status='"
							+ CommonCodes.STATUS_ACTIVE + "'");

			// if(objSearchDto.getIssuerId()!=null &&
			// !objSearchDto.getIssuerId().equals("")){
			sbf.append(" and cp.issuerId = '" + objSearchDto.getIssuerId()
					+ "' ");
			// }

			if (objSearchDto.getCardProductId() != null
					&& !objSearchDto.getCardProductId().equals("")) {
				sbf.append("and cp.cardProductId ='"
						+ objSearchDto.getCardProductId() + "'");
			}

			// sbf.append("and cp.status='"+CommonCodes.STATUS_ACTIVE+"' ");

			objSearchCollection = getSearchList(sbf.toString(), pageNo);

		} catch (Exception e) {
			logger.error(e);

			System.out.println("Error in CardProductDAOImpl search method : "
					+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in CardProductDAOImpl search  method :" + e);

		}
		return objSearchCollection;
	}

	/*
	 * This method is used for getting the particular Record to update
	 */

	public CardProductDto getCardProductDto(String strCardProductId)
			throws TPlusException {

		CardProductDto objDto = null;
		Transaction tx = null;
		try {

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			objDto = (CardProductDto) session.get(CardProductDto.class,
					strCardProductId);
			objDto.getCardProductType().getCardProductType();
			
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.error(e);
			System.out
					.println("Error in CardProductDAOImpl getCardProductDto method : "
							+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in CardProductDAOImpl getCardProductDto  method :"
							+ e);
		} finally {
			HibernetFactory.closeSession();
		}

		return objDto;

	}

	/*
	 * This method is used for insert the particular Record to card_products
	 * table
	 */
	public boolean add(CardProductDto objDto) throws TPlusException {

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
			System.out.println("Error in CardProductDAOImpl add method : "
					+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in CardProductDAOImpl add  method :" + e);
		} finally {
			HibernetFactory.closeSession();
		}
		return boolAdd;

	}

	/*
	 * This method is used for updating the particular Record in card_products
	 * table
	 */

	public boolean update(CardProductDto objDto) throws TPlusException {

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
			System.out.println("Error in CardProductDAOImpl update method : "
					+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in CardProductDAOImpl update  method :" + e);
		} finally {
			HibernetFactory.closeSession();
		}
		return boolUpdate;
	}

	/*
	 * This method is used for deleting the particular Record in card_products
	 * table
	 */
	public boolean delete(CardProductDto objDto) throws TPlusException {

		boolean boolDelete = false;
		Transaction tx = null;
		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			String sql = "UPDATE CardProductDto SET status=:status WHERE cardProductId=:cardproductid";

			int count = session.createQuery(sql).setString("cardproductid",
					objDto.getCardProductId()).setString("status",
					objDto.getStatus()).executeUpdate();

			tx.commit();
			if (count > 0)
				boolDelete = true;

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.error(e);
			System.out.println("Error in CardProductDAOImpl delete method : "
					+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in CardProductDAOImpl delete  method :" + e);
		} finally {
			HibernetFactory.closeSession();
		}
		return boolDelete;

	}

	public ArrayList cardPromotionList() throws TPlusException {

		ArrayList cardPromotionList = new ArrayList();
		Transaction tx = null;
		try {

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			Query qry = session.createQuery("From CardPromotionDto");
			List listcardPromotions = qry.list();
			for (Iterator it = listcardPromotions.iterator(); it.hasNext();) {
				CardPromotionDto objDto = new CardPromotionDto();
				objDto = (CardPromotionDto) it.next();
				cardPromotionList.add(objDto);
			}
			tx.commit();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.error(e);
			System.out
					.println("Error in CardProductDAOImpl cardPromotionList method : "
							+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in CardProductDAOImpl cardPromotionList  method :"
							+ e);
		} finally {
			HibernetFactory.closeSession();
		}
		return cardPromotionList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.transinfo.cacis.dataacess.dao.settings.CardProductDAO
	 * #checkExistrecord(org.transinfo.cacis.dto.settings.CardProductDto) For
	 * checking Record Existed or nor in Card_Products Table
	 */
	public int checkExistrecord(CardProductDto objDto) throws TPlusException {

		int res = 0;
		try {
			Session session = HibernetFactory.currentSession();
			Query qry = session
					.createQuery("select count(*) from CardProductDto prod where prod.cardProductId=:cardproductid ");
			qry.setString("cardproductid", objDto.getCardProductId());
			List list = qry.list();
			res = ((Integer) list.get(0)).intValue();
			System.out.println("After CardProductDAOIMPL checkExistrecord()"
					+ res);
		}

		catch (Exception e) {
			logger.error(e);
			System.out
					.println("Error in CardProductDAOImpl checkExist record method:"
							+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error:  Error in CardProductDAOImpl checkExistrecord method:"
							+ e);
		}

		finally {
			HibernetFactory.closeSession();
		}

		return res;

	}

	public String getCardProductType(String strCardProductId)
			throws TPlusException {

		String cardProductType = "";
		CardProductDto objCardProductDto = null;
		Transaction tx = null;
		try {

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			objCardProductDto = (CardProductDto) session.get(
					CardProductDto.class, strCardProductId);

			cardProductType = objCardProductDto.getCardProductType()
					.getCardProductType();

			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.error(e);
			System.out
					.println("Error in CardProductDAOImpl getCardProductDto method : "
							+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in CardProductDAOImpl getCardProductDto  method :"
							+ e);
		} finally {
			HibernetFactory.closeSession();
		}

		return cardProductType;

	}

	public CardProductFeeDto getCardProductFeeDto(String strCardProductId)
			throws TPlusException {

		CardProductFeeDto objDto = null;
		Transaction tx = null;
		StringBuffer sbf = new StringBuffer();
		try {

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			sbf.append("from CardProductFeeDto cpfdo ");
			sbf.append("where cpfdo.cardProduct.cardProductId = '"
					+ strCardProductId + "'");
			sbf.append("and cpfdo.displayStatus = 'Active'");

			Query qry = session.createQuery(sbf.toString());
			List list = qry.list();

			if (list.size() > 0) {
				objDto = (CardProductFeeDto) list.get(0);
			}

			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.error(e);
			System.out
					.println("Error in CardProductDAOImpl getCardProductDto method : "
							+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in CardProductDAOImpl getCardProductDto  method :"
							+ e);
		} finally {
			HibernetFactory.closeSession();
		}

		return objDto;

	}

	public boolean hasCards(String productId) throws TPlusException {

		boolean boolhas = false;
		Transaction tx = null;
		StringBuffer sbf = new StringBuffer();

		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			sbf.append("select count(*) ");
			sbf.append("from CardsDto cdo ");
			sbf.append("where cdo.status = 'A' ");
			sbf.append("and cdo.cardProductId = '" + productId + "' ");

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
			System.out.println("Error in CardProductDAOImpl delete method : "
					+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in CardProductDAOImpl delete  method :" + e);
		} finally {
			HibernetFactory.closeSession();
		}
		return boolhas;

	}

	public boolean hasWithdrawalLimitRules(String cardProductId)
			throws TPlusException {

		boolean boolhas = false;
		Transaction tx = null;
		StringBuffer sbf = new StringBuffer();

		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			sbf.append("select count(*) ");
			sbf.append("from WithdrawalLimitRulesDto wdlr ");
			sbf.append("where wdlr.cardProductId = '" + cardProductId + "' ");

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
					"Error: in CardProductDAOImpl hasWithdrawalLimitRules  method :"
							+ e);
		} finally {
			HibernetFactory.closeSession();
		}
		return boolhas;

	}

	public static void main(String[] args) throws TPlusException {
		CardProductDAOImpl obj = new CardProductDAOImpl();
		obj.getCardProductFeeDto("290");
	}

}
