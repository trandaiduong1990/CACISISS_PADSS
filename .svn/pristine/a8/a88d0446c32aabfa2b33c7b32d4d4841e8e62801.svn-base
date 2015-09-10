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
import org.transinfo.cacis.common.constants.CommonCodes;
import org.transinfo.cacis.dataacess.HibernetDAO.HibernetFactory;
import org.transinfo.cacis.dataacess.dao.settings.CardTypeDAO;
import org.transinfo.cacis.dataacess.daoimpl.oracle.BaseDAOImpl;
import org.transinfo.cacis.dto.settings.CardTypeDto;
import org.transinfo.cacis.dto.settings.CardTypeSearchDto;

@SuppressWarnings("unchecked")
public class CardTypeDAOImpl extends BaseDAOImpl implements CardTypeDAO {

	private Logger logger = Logger.getLogger(CardTypeDAOImpl.class);

	/*
	 * This method is used for getting the CardTypeList
	 */
	public Collection search(CardTypeSearchDto objSearchDto, int PageNo)
			throws TPlusException {

		Collection objSearchCollection = new ArrayList();
		StringBuffer sbf = new StringBuffer();
		try {

			sbf
					.append("select ctd.cardTypeId,ctd.cardType,to_char(ctd.updatedDate,'dd-MM-yyyy') From CardTypeDto ctd where ctd.status='"
							+ CommonCodes.STATUS_ACTIVE + "'");

			// if(objSearchDto.getIssuerId()!=null &&
			// !objSearchDto.getIssuerId().equals("")){
			sbf.append("and ctd.issuerId = '" + objSearchDto.getIssuerId()
					+ "' ");
			// }
			if (objSearchDto.getCardTypeId() != null
					&& !objSearchDto.getCardTypeId().equals("")) {
				sbf.append("and ctd.cardTypeId ='"
						+ objSearchDto.getCardTypeId() + "'");
			}

			// sbf.append("and ctd.status='"+CommonCodes.STATUS_ACTIVE+"' ");

			objSearchCollection = getSearchList(sbf.toString(), PageNo);

		} catch (Exception e) {
			logger.error(e);

			System.out.println("Error in CardTypeDAOImpl search method : "
					+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in CardTypeDAOImpl search  method :" + e);

		}
		return objSearchCollection;
	}

	/*
	 * This method is used for getting the particular CardTypeRecord to update
	 */
	public CardTypeDto getCardTypeDto(String cardTypeId) throws TPlusException {

		CardTypeDto objDto = null;
		Transaction tx = null;
		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			// session.load(objDto,cardTypeId );
			objDto = (CardTypeDto) session.get(CardTypeDto.class, cardTypeId);
			tx.commit();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.error(e);
			System.out
					.println("Error in CardTypeDAOImpl getCardTypeDto method : "
							+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in CardTypeDAOImpl getCardTypeDto  method :" + e);
		} finally {
			HibernetFactory.closeSession();
		}
		return objDto;

	}

	/*
	 * This method is used for insert the particular Record to Card_Type table
	 */
	public boolean add(CardTypeDto objDto) throws TPlusException {

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
			System.out.println("Error in CardTypeDAOImpl add method : "
					+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in CardTypeDAOImpl add  method :" + e);
		} finally {
			HibernetFactory.closeSession();
		}
		return boolAdd;

	}

	/*
	 * This method is used for updating the particular Record in card_type table
	 */
	public boolean update(CardTypeDto objDto) throws TPlusException {

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
			System.out.println("Error in CardTypeDAOImpl update method : "
					+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in CardTypeDAOImpl update  method :" + e);
		} finally {
			HibernetFactory.closeSession();
		}
		return boolUpdate;
	}

	/*
	 * This method is used for deleting the particular Record in card_type table
	 */
	public boolean delete(CardTypeDto objDto) throws TPlusException {

		boolean boolDelete = false;
		Transaction tx = null;
		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			// session.delete(objDto);

			String sql = "UPDATE CardTypeDto SET status =:status  WHERE cardTypeId=:cardtypeid";
			int count = session.createQuery(sql).setString("cardtypeid",
					objDto.getCardTypeId()).setString("status",
					objDto.getStatus()).executeUpdate();

			tx.commit();
			if (count > 0)
				boolDelete = true;
		}

		catch (Exception e) {

			if (tx != null) {
				tx.rollback();
			}
			logger.error(e);
			System.out.println("Error inCardTypeDAOImpl delete method : "
					+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in CardTypeDAOImpl delete  method :" + e);
		} finally {
			HibernetFactory.closeSession();
		}
		return boolDelete;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.transinfo.cacis.dataacess.dao.settings.CardTypeDAO
	 * #checkExistrecord(org.transinfo.cacis.dto.settings.CardTypeDto) For
	 * checking Record Existed or nor in Card_type Table
	 */
	public int checkExistrecord(CardTypeDto objDto) throws TPlusException {

		int res = 0;
		try {
			Session session = HibernetFactory.currentSession();
			Query qry = session
					.createQuery("select count(*) from CardTypeDto ctd where ctd.cardTypeId=:cardtypeid ");
			qry.setString("cardtypeid", objDto.getCardTypeId());
			List list = qry.list();
			res = ((Integer) list.get(0)).intValue();
			System.out.println("After CardTypeDAOIMPL checkExistrecord() count"
					+ res);

		}

		catch (Exception e) {
			logger.error(e);
			System.out
					.println("Error in CardTypeDAOImpl checkExist record method:"
							+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error:  Error in CardTypeDAOImpl checkExistrecord method:"
							+ e);
		}

		finally {
			HibernetFactory.closeSession();
		}

		return res;

	}

	public boolean hasCardProducts(String cardTypeId) throws TPlusException {

		boolean boolhas = false;
		Transaction tx = null;
		StringBuffer sbf = new StringBuffer();

		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			sbf.append("select count(*) ");
			sbf.append("from CardProductDto cpdto ");
			sbf.append("where cpdto.status = 'A' ");
			sbf.append("and  cpdto.cardType = '" + cardTypeId + "' ");

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
					"Error: in CardTypeDAOImpl hasCardProducts  method :" + e);
		} finally {
			HibernetFactory.closeSession();
		}
		return boolhas;

	}

}
