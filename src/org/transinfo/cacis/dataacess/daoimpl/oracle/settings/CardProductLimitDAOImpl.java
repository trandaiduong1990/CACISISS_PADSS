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
import org.transinfo.cacis.dataacess.HibernetDAO.HibernetFactory;
import org.transinfo.cacis.dataacess.dao.settings.CardProductLimitDAO;
import org.transinfo.cacis.dataacess.daoimpl.oracle.BaseDAOImpl;
import org.transinfo.cacis.dto.settings.CardProductLimitDto;
import org.transinfo.cacis.dto.settings.CardProductLimitSearchDto;
import org.transinfo.cacis.dto.settings.CardPromotionDto;

@SuppressWarnings("unchecked")
public class CardProductLimitDAOImpl extends BaseDAOImpl implements
		CardProductLimitDAO {

	private Logger logger = Logger.getLogger(CardProductLimitDAOImpl.class);

	/*
	 * This method is used for getting the CardproductTypeList
	 */
	public Collection search(CardProductLimitSearchDto objSearchDto, int pageNo)
			throws TPlusException {

		Collection objSearchCollection = new ArrayList();
		StringBuffer sbf = new StringBuffer();
		try {

			sbf
					.append("select cpldto.cardProductId, cpldto.minSalary, to_char(cpldto.updatedDate,'dd-MM-yyyy HH24:mi:ss'), cpldto.cardProduct.cardProductName From CardProductLimitDto cpldto ");

			// if(objSearchDto.getIssuerId()!=null &&
			// !objSearchDto.getIssuerId().equals("")){
			// sbf.append(" and cp.issuerId = '"+objSearchDto.getIssuerId()+"' ");
			// }

			if (objSearchDto.getCardProductId() != null
					&& !objSearchDto.getCardProductId().equals("")) {
				sbf.append("where cpldto.cardProductId ='"
						+ objSearchDto.getCardProductId() + "'");
			}

			// sbf.append("and cp.status='"+CommonCodes.STATUS_ACTIVE+"' ");

			objSearchCollection = getSearchList(sbf.toString(), pageNo);

		} catch (Exception e) {
			logger.error(e);

			System.out
					.println("Error in CardProductLimitDAOImpl search method : "
							+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in CardProductLimitDAOImpl search  method :" + e);

		}
		return objSearchCollection;
	}

	/*
	 * This method is used for getting the particular Record to update
	 */

	public CardProductLimitDto getCardProductLimitDto(String strCardProductId)
			throws TPlusException {

		CardProductLimitDto objDto = null;
		//Transaction tx = null;
		try {

			Session session = HibernetFactory.currentSession();
			//tx = session.beginTransaction();
			objDto = (CardProductLimitDto) session.get(
					CardProductLimitDto.class, strCardProductId);
			//tx.commit();
		} catch (Exception e) {
			/*if (tx != null) {
				tx.rollback();
			}*/
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
	public boolean add(CardProductLimitDto objDto) throws TPlusException {

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
			System.out.println("Error in CardProductLimitDAOImpl add method : "
					+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in CardProductLimitDAOImpl add  method :" + e);
		} finally {
			HibernetFactory.closeSession();
		}
		return boolAdd;

	}

	/*
	 * This method is used for updating the particular Record in card_products
	 * table
	 */

	public boolean update(CardProductLimitDto objDto) throws TPlusException {

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
			System.out
					.println("Error in CardProductLimitDAOImpl update method : "
							+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in CardProductLimitDAOImpl update  method :" + e);
		} finally {
			HibernetFactory.closeSession();
		}
		return boolUpdate;
	}

	/*
	 * This method is used for deleting the particular Record in card_products
	 * table
	 */
	public boolean delete(CardProductLimitDto objDto) throws TPlusException {

		boolean boolDelete = false;
		Transaction tx = null;
		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			/*
			 * String sql =
			 * "UPDATE CardProductLimitDto SET status=:status WHERE cardProductId=:cardproductid"
			 * ;
			 * 
			 * int count = session.createQuery(sql).setString("cardproductid",
			 * objDto.getCardProductId()).setString("status",
			 * objDto.getStatus()).executeUpdate();
			 * 
			 * tx.commit(); if (count > 0) boolDelete = true;
			 */

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
					.println("Error in CardProductlimitDAOImpl cardPromotionList method : "
							+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in CardProductlimitDAOImpl cardPromotionList  method :"
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
	public int checkExistrecord(CardProductLimitDto objDto)
			throws TPlusException {

		int res = 0;
		try {
			Session session = HibernetFactory.currentSession();
			Query qry = session
					.createQuery("select count(*) from CardProductLimitDto prod where prod.cardProductId=:cardproductid ");
			qry.setString("cardproductid", objDto.getCardProductId());
			List list = qry.list();
			res = ((Integer) list.get(0)).intValue();
			System.out
					.println("After CardProductLimitDAOIMPL checkExistrecord()"
							+ res);
		}

		catch (Exception e) {
			logger.error(e);
			System.out
					.println("Error in CardProductLimitDAOIMPL checkExist record method:"
							+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error:  Error in CardProductLimitDAOIMPL checkExistrecord method:"
							+ e);
		}

		finally {
			HibernetFactory.closeSession();
		}

		return res;

	}

}
