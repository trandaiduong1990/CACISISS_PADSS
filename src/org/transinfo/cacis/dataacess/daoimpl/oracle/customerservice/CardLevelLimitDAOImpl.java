package org.transinfo.cacis.dataacess.daoimpl.oracle.customerservice;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.transinfo.cacis.TPlusCodes;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.HibernetDAO.HibernetFactory;
import org.transinfo.cacis.dataacess.dao.customerservice.CardLevelLimitDAO;
import org.transinfo.cacis.dataacess.daoimpl.oracle.BaseDAOImpl;
import org.transinfo.cacis.dto.cardproduction.CardsDto;
import org.transinfo.cacis.dto.cardproduction.CustomerLimitsDto;
import org.transinfo.cacis.dto.customerservice.CardLevelLimitSearchDto;
import org.transinfo.cacis.dto.settings.CardProductLimitDto;

@SuppressWarnings("unchecked")
public class CardLevelLimitDAOImpl extends BaseDAOImpl implements
CardLevelLimitDAO {

	private Logger logger = Logger.getLogger(CardLevelLimitDAOImpl.class);

	/*
	 * This method is used for getting the CardproductTypeList
	 */
	public Collection search(CardLevelLimitSearchDto objSearchDto, int pageNo)
			throws TPlusException {

		Collection objSearchCollection = new ArrayList();
		StringBuffer sbf = new StringBuffer();

		try {

			String cardNo = objSearchDto.getCardNo();

			sbf.append("select cl.cardNo,cl.dailyLimitCount,cl.dailyLimitAmt,cl.cashLimit,cl.purchaseLimit,to_char(cl.lastUpdatedDate,'dd-MM-yyyy HH24:mi:ss') ");
			sbf.append(", cl.status ");
			sbf.append("From CustomerLimitsDto cl ");

			if(cardNo != null && !"".equals(cardNo)){
				sbf.append("where cl.cardNo='" + cardNo + "' ");
			}
			
			sbf.append("order by cl.lastUpdatedDate desc ");

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

	public CustomerLimitsDto getCustomerLimitDto(String cardNo)
			throws TPlusException {

		CustomerLimitsDto objDto = null;

		try {

			Session session = HibernetFactory.currentSession();

			objDto = (CustomerLimitsDto) session.get(CustomerLimitsDto.class,
					Long.valueOf(cardNo));

		} catch (Exception e) {
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

	public boolean update(CustomerLimitsDto objDto) throws TPlusException {

		boolean boolUpdate = false;
		Transaction tx = null;

		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			session.saveOrUpdate(objDto);

			/*String appSql = "UPDATE CardsDto SET eComEnable=:ecomenable WHERE cardNumber=:cardno";
			int count = session.createQuery(appSql)
			.setString("ecomenable", objDto.geteComEnable())
			.setLong("cardno", objDto.getCardNo())
			.executeUpdate();*/

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

	public CardProductLimitDto getCardProductLimitDto(String cardNo)
			throws TPlusException {

		CardProductLimitDto objDto = null;

		try {

			Session session = HibernetFactory.currentSession();

			CardsDto objCardsDto = (CardsDto)session.get(CardsDto.class, Long.valueOf(cardNo));

			objDto = (CardProductLimitDto) session.get(CardProductLimitDto.class, objCardsDto.getCardProductId());
			if(objDto != null){
				objDto.getCardProduct();
			}

		} catch (Exception e) {
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

	public static void main(String[] args) throws TPlusException {
		CardLevelLimitDAOImpl obj = new CardLevelLimitDAOImpl();
		CardLevelLimitSearchDto objCardLevelLimitSearchDto = new CardLevelLimitSearchDto();
		objCardLevelLimitSearchDto.setCardNo("6221590000000018");

		obj.search(objCardLevelLimitSearchDto, 0);
	}

}
