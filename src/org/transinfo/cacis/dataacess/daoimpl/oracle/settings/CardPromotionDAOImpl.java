package org.transinfo.cacis.dataacess.daoimpl.oracle.settings;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.transinfo.cacis.TPlusCodes;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.common.constants.CommonCodes;
import org.transinfo.cacis.dataacess.HibernetDAO.HibernetFactory;
import org.transinfo.cacis.dataacess.dao.settings.CardPromotionDAO;
import org.transinfo.cacis.dataacess.daoimpl.oracle.BaseDAOImpl;
import org.transinfo.cacis.dto.settings.CardPromotionDto;
import org.transinfo.cacis.dto.settings.CardPromotionSearchDto;

@SuppressWarnings("unchecked")
public class CardPromotionDAOImpl extends BaseDAOImpl implements
		CardPromotionDAO {

	private Logger logger = Logger.getLogger(CardPromotionDAOImpl.class);

	/*
	 * This method is used for getting the CardPromotionList
	 */
	public Collection search(CardPromotionSearchDto objSearchDto, int pageNo)
			throws TPlusException {

		Collection objSearchCollection = new ArrayList();
		StringBuffer sbf = new StringBuffer();
		try {

			sbf
					.append("select cp.promotionId, cp.promotionType, to_char(cp.updatedDate,'dd-MM-yyyy') From CardPromotionDto cp where cp.status = '"
							+ CommonCodes.STATUS_ACTIVE + "'");

			if (objSearchDto.getPromotionId() != null
					&& !objSearchDto.getPromotionId().equals("")) {
				sbf.append("and cp.promotionId = '"
						+ objSearchDto.getPromotionId() + "' ");
			}

			objSearchCollection = getSearchList(sbf.toString(), pageNo);

		} catch (Exception e) {
			logger.error(e);

			System.out.println("Error in CardPromotionDAOImpl search method : "
					+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in CardPromotionDAOImpl search  method :" + e);

		}
		return objSearchCollection;
	}

	/*
	 * This method is used for getting the particular promotionRecord to update
	 */
	public CardPromotionDto getCardPromotionDto(String pramotionId)
			throws TPlusException {

		CardPromotionDto objDto = null;
		Transaction tx = null;
		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			objDto = (CardPromotionDto) session.get(CardPromotionDto.class,
					pramotionId);
			// session.load(objDto,branchId );
			tx.commit();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.error(e);
			System.out
					.println("Error in CardPromotionDAOImpl getCardPromotionDto method : "
							+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in CardPromotionDAOImpl getCardPromotionDto  method :"
							+ e);
		} finally {
			HibernetFactory.closeSession();
		}
		return objDto;

	}

	/*
	 * This method is used for insert the particular Record to card_promotion
	 * table
	 */
	public boolean add(CardPromotionDto objDto) throws TPlusException {

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
			System.out.println("Error in CardPromotionDAOImpl add method : "
					+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in CardPromotionDAOImpl add  method :" + e);
		} finally {
			HibernetFactory.closeSession();
		}
		return boolAdd;

	}

	/*
	 * This method is used for updating the particular Record in card_promotion
	 * table
	 */
	public boolean update(CardPromotionDto objDto) throws TPlusException {

		boolean boolUpdate = false;
		Transaction tx = null;
		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			session.update(objDto);
			session.flush();
			tx.commit();
			boolUpdate = true;

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.error(e);
			System.out.println("Error in CardPromotionDAOImpl update method : "
					+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in CardPromotionDAOImpl update  method :" + e);
		} finally {
			HibernetFactory.closeSession();
		}
		return boolUpdate;
	}

	/*
	 * This method is used for deleting the particular Record in card_promotion
	 * table
	 */

	public boolean delete(CardPromotionDto objDto) throws TPlusException {

		boolean bolDelete = false;
		Transaction tx = null;
		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			String sql = "UPDATE  CardPromotionDto  SET status='A' WHERE promotionId=:promotionid";
			int count = session.createQuery(sql).setString("promotionid",
					objDto.getPromotionId()).executeUpdate();

			if (count > 0)
				bolDelete = true;
			tx.commit();

		}

		catch (Exception e) {

			if (tx != null) {
				tx.rollback();
			}
			logger.error(e);
			System.out.println("Error in CardPromotionDAOImpl delete method : "
					+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in CardPromotionDAOImpl delete  method :" + e);

		} finally {
			HibernetFactory.closeSession();
		}
		return bolDelete;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.transinfo.cacis.dataacess.dao.settings.CardPromotionDAO
	 * #cardPromotionListData
	 * (org.transinfo.cacis.formbean.settings.CardPromotionSearchForm)
	 */
	public Map cardPromotionListData() throws TPlusException {

		Map cardPromotionList = new TreeMap();
		Transaction tx = null;
		try {

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			Query qry = session.createQuery("From CardPromotionDto");
			List listcardPromotions = qry.list();
			for (Iterator it = listcardPromotions.iterator(); it.hasNext();) {
				CardPromotionDto objDto = new CardPromotionDto();
				objDto = (CardPromotionDto) it.next();
				cardPromotionList.put(objDto.getPromotionId(), objDto
						.getPromotionType());

			}
			tx.commit();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.error(e);
			System.out
					.println("Error in CardPromotionDAOImpl cardPromotionListData method : "
							+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in CardPromotionDAOImpl cardPromotionListData  method :"
							+ e);
		} finally {
			HibernetFactory.closeSession();
		}
		return cardPromotionList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.transinfo.cacis.dataacess.dao.settings.CardPromotionDAO
	 * #checkExistrecord(org.transinfo.cacis.dto.settings.CardPromotionDto) For
	 * checking Record Existed or nor in Card_promotion Table
	 */
	public int checkExistrecord(CardPromotionDto objDto) throws TPlusException {

		int result = 0;
		try {
			Session session = HibernetFactory.currentSession();
			Query qry = session
					.createQuery("select count(*) from CardPromotionDto cp where cp.promotionId=:promotionid ");
			qry.setString("promotionid", objDto.getPromotionId());
			List list = qry.list();
			result = ((Integer) list.get(0)).intValue();
			System.out.println("After CardPromotionDAOIMPL checkExistrecord()"
					+ result);

		} catch (Exception e) {
			logger.error(e);
			System.out
					.println("Error in CardPromotionDAOImpl checkExist record method:"
							+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error:  Error in CardPromotionDAOImpl checkExistrecord method:"
							+ e);
		}

		finally {
			HibernetFactory.closeSession();
		}

		return result;

	}

}
