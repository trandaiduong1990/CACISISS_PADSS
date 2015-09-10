package org.transinfo.cacis.dataacess.daoimpl.oracle.settings;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.transinfo.cacis.TPlusCodes;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.HibernetDAO.HibernetFactory;
import org.transinfo.cacis.dataacess.dao.settings.CardProductFeeDAO;
import org.transinfo.cacis.dataacess.daoimpl.oracle.BaseDAOImpl;
import org.transinfo.cacis.dto.settings.CardProductDto;
import org.transinfo.cacis.dto.settings.CardProductFeeDto;
import org.transinfo.cacis.dto.settings.CardProductFeeSearchDto;

@SuppressWarnings("unchecked")
public class CardProductFeeDAOImpl extends BaseDAOImpl implements
		CardProductFeeDAO {

	private Logger logger = Logger.getLogger(CardProductFeeDAOImpl.class);

	/*
	 * This method is used for getting the CardProductRateList
	 */

	public Collection search(CardProductFeeSearchDto objSearchDto, int pageNo)
			throws TPlusException {

		Collection objSearchCollection = new ArrayList();
		StringBuffer sbf = new StringBuffer();
		try {

			if (objSearchDto.getCardProductId() != null
					&& !objSearchDto.getCardProductId().equals("")) {

				sbf
						.append("select cf.id, to_char(cf.startDt,'dd-MM-yyyy'), to_char(cf.endDt,'dd-MM-yyyy'),cf.displayStatus , to_char(cf.updatedDate,'dd-MM-yyyy') From CardProductFeeDto cf where card_product_id='"
								+ objSearchDto.getCardProductId() + "' ");
			}

			objSearchCollection = getSearchList(sbf.toString(), pageNo);

		} catch (Exception e) {
			logger.error(e);
			System.out
					.println("Error in CardProductFeeDAOImpl search method : "
							+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in CardProductFeeDAOImpl search  method :" + e);

		}
		return objSearchCollection;
	}

	/*
	 * This method is used for getting the particular cardproduct fee Record to
	 * update
	 */
	public CardProductFeeDto getCardProductFeeDto(int feeId)
			throws TPlusException {

		CardProductFeeDto objDto = null;
		Transaction tx = null;
		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			objDto = (CardProductFeeDto) session.get(CardProductFeeDto.class,
					new Integer(feeId));

			tx.commit();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.error(e);
			System.out
					.println("Error in CardProductFeeDAOImpl getCardProductFeeDto method : "
							+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in CardProductFeeDAOImpl getCardProductFeeDto  method :"
							+ e);
		} finally {
			HibernetFactory.closeSession();
		}
		return objDto;

	}

	/*
	 * This method is used for insert the particular Record to card _product_fee
	 * table
	 */
	public boolean add(CardProductFeeDto objDto) throws TPlusException {

		boolean boolAdd = false;
		Transaction tx = null;
		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			Query qry = session
					.createQuery("from CardProductDto prod where prod.cardProductId= '"
							+ objDto.getCardProductId() + "'");
			List res = qry.list();
			CardProductDto cpd = (CardProductDto) res.get(0);
			objDto.setCardProduct(cpd);

			session.save(objDto);
			session.flush();
			tx.commit();
			boolAdd = true;
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.error(e);
			System.out.println("Error in CardProductFeeDAOImpl add method : "
					+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in CardProductFeeDAOImpl add  method :" + e);
		} finally {
			HibernetFactory.closeSession();
		}
		return boolAdd;

	}

	/*
	 * This method is used for updating the particular Record in card
	 * _product_fee table
	 */
	public boolean update(CardProductFeeDto objDto) throws TPlusException {

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
			System.out
					.println("Error in CardProductFeeDAOImpl update method : "
							+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in CardProductFeeDAOImpl update  method :" + e);
		} finally {
			HibernetFactory.closeSession();
		}
		return boolUpdate;
	}

	public CardProductFeeDto getCardProductFeeByProductId(String cardProductId)
			throws TPlusException {

		CardProductFeeDto objCardProductFeeDto = null;
		Transaction tx = null;
		StringBuffer sbf = new StringBuffer();

		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			sbf.append("from CardProductFeeDto cpfdo ");
			sbf.append("where cpfdo.cardProduct.cardProductId = '"
					+ cardProductId + "' ");
			sbf.append("and cpfdo.displayStatus = 'Active' ");

			Query qry = session.createQuery(sbf.toString());
			List res = qry.list();
			if (res.size() > 0) {
				objCardProductFeeDto = (CardProductFeeDto) res.get(0);
			}

			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.error(e);
			System.out
					.println("Error in CardProductFeeDAOImpl getCardProductFeeByProductId method : "
							+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in CardProductFeeDAOImpl getCardProductFeeByProductId  method :"
							+ e);
		} finally {
			HibernetFactory.closeSession();
		}
		return objCardProductFeeDto;

	}

	public List getAllFees(String cardProductId) throws TPlusException {

		List res = new ArrayList();;

		try {
			Session session = HibernetFactory.currentSession();

			Query qry = session
					.createQuery("from CardProductFeeDto prodfee where prodfee.cardProduct= '"
							+ cardProductId + "'");

			res = qry.list();

		} catch (Exception e) {
			logger.error(e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in CardProductFeeDAOImpl getAllFees  method :"
							+ e);
		} finally {
			HibernetFactory.closeSession();
		}

		return res;
	}

	public List getAllFeesExceptOne(String cardProductId, String serialNo) throws TPlusException {

		List res = new ArrayList();;

		try {
			Session session = HibernetFactory.currentSession();

			Query qry = session
					.createQuery("from CardProductFeeDto prodfee where prodfee.cardProduct= '"
							+ cardProductId + "' and prodfee.id <> " + serialNo + " ");

			res = qry.list();

		} catch (Exception e) {
			logger.error(e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in CardProductFeeDAOImpl getAllFeesExceptOne  method :"
							+ e);
		} finally {
			HibernetFactory.closeSession();
		}

		return res;
	}

	/*
	 * This method is used for deleting the particular Record in ard
	 * _product_rate table
	 */
	/*
	 * public boolean delete(CardProductFeeDto objDto)throws TPlusException {
	 * 
	 * boolean bolDelete=false; Transaction tx = null; try { Session session
	 * =HibernetFactory.currentSession(); tx =session.beginTransaction();
	 * 
	 * session.delete(objDto); session.flush(); tx.commit(); bolDelete=true; }
	 * 
	 * catch (Exception e) {
	 * 
	 * if(tx!=null) { tx.rollback(); }
	 * System.out.println("Error in CardProductFeeDAOImpl delete method : "
	 * +e.getMessage()); throw newTPlusException(TPlusCodes.APPLICATION_ERROR,
	 * "Error: in CardProductFeeDAOImpl delete  method :"+e);
	 * 
	 * } finally { HibernetFactory.closeSession(); } return bolDelete;
	 * 
	 * }
	 */

	/*
	 * public int checkExistrecord(CardProductRateDto objDto)throws
	 * TPlusException {
	 * 
	 * int result=0; try { Session session =HibernetFactory.currentSession();
	 * Query qry =session.createQuery(
	 * "select count(*) from CardProductRateDto cr where cr.id=:rateid ");
	 * qry.setInteger("rateid",objDto.getId()); List list = qry.list(); result =
	 * ((Integer)list.get(0)).intValue();
	 * System.out.println("After CardPromotionDAOIMPL checkExistrecord()"
	 * +result);
	 * 
	 * } catch (Exception e) {
	 * System.out.println("Error in CardPromotionDAOImpl checkExist record method:"
	 * +e.getMessage()); throw newTPlusException(TPlusCodes.APPLICATION_ERROR,
	 * "Error:  Error in CardPromotionDAOImpl checkExistrecord method:"+e); }
	 * 
	 * finally { HibernetFactory.closeSession(); }
	 * 
	 * 
	 * return result;
	 * 
	 * }
	 */

	public static void main(String[] args) throws TPlusException {
		String cardProductId = "290";

		CardProductFeeDAOImpl tt = new CardProductFeeDAOImpl();
		tt.getCardProductFeeByProductId(cardProductId);
	}

}
