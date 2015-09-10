package org.transinfo.cacis.dataacess.daoimpl.oracle.settings;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.transinfo.cacis.TPlusCodes;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.common.IdsGenartion;
import org.transinfo.cacis.common.constants.CommonCodes;
import org.transinfo.cacis.dataacess.HibernetDAO.HibernetFactory;
import org.transinfo.cacis.dataacess.dao.settings.CardProductDAO;
import org.transinfo.cacis.dataacess.dao.settings.ProductTranxDAO;
import org.transinfo.cacis.dataacess.daoimpl.oracle.BaseDAOImpl;
import org.transinfo.cacis.dto.cardproduction.ApplicationProcessDto;
import org.transinfo.cacis.dto.cardproduction.CustomerAccountDto;
import org.transinfo.cacis.dto.csr.AddProductDto;
import org.transinfo.cacis.dto.csr.TransactionLogDto;
import org.transinfo.cacis.dto.settings.CardProductDto;
import org.transinfo.cacis.dto.settings.CardProductFeeDto;
import org.transinfo.cacis.dto.settings.CardProductSearchDto;
import org.transinfo.cacis.dto.settings.CardProductTranxDto;
import org.transinfo.cacis.dto.settings.CardPromotionDto;
import org.transinfo.cacis.dto.settings.CardTypeDto;
import org.transinfo.cacis.dto.settings.CurrencyRateDto;
import org.transinfo.cacis.dto.settings.ProductTranxSearchDto;
import org.transinfo.cacis.dto.settings.TranxTypeDto;

@SuppressWarnings("unchecked")
public class ProductTranxDAOImpl extends BaseDAOImpl implements ProductTranxDAO {

	private Logger logger = Logger.getLogger(ProductTranxDAOImpl.class);

	/*
	 * This method is used for getting the CardproductTypeList
	 */
	public Collection search(ProductTranxSearchDto objProductTranxSearchDto, int pageNo)
			throws TPlusException {

		Collection objSearchCollection = new ArrayList();
		int totalCount;
		StringBuffer sbfSelect = new StringBuffer();
		StringBuffer sbf = new StringBuffer();
		StringBuffer sbfCount = new StringBuffer();
		try {

			sbfSelect.append("select cpt.id.productId, cp.cardProductName, cm.codeDesc, to_char(cpt.updatedDate,'dd-MM-yyyy HH24:mi:ss'), cpt.id.channel, cpt.tranxId.tranxType ");
			sbfCount.append("select count(*) ");
			sbf.append("From CardProductDto cp, CodeMasterDto cm, CardProductTranxDto cpt ");
			sbf.append("where cp.issuerId = '" + objProductTranxSearchDto.getIssuerId() + "' ");
			sbf.append("and cm.id.groupId = 'CHANNELS' ");
			sbf.append("and cp.cardProductId = cpt.id.productId and cm.id.codeId = cpt.id.channel ");
			
			if (objProductTranxSearchDto.getCardProductId() != null
					&& !objProductTranxSearchDto.getCardProductId().equals("")) {
				sbf.append("and cpt.id.productId ='" + objProductTranxSearchDto.getCardProductId() + "' ");
			}

			if (objProductTranxSearchDto.getChannelId() != null
					&& !objProductTranxSearchDto.getChannelId().equals("")) {
				sbf.append("and cpt.id.channel = '" + objProductTranxSearchDto.getChannelId() + "' ");
			}
			
			objSearchCollection = getSearchList((sbfSelect.append(sbf)).toString(), pageNo);
			objProductTranxSearchDto.setSearchList(objSearchCollection);

			totalCount = getSearchTotalCount((sbfCount.append(sbf)).toString());
			objProductTranxSearchDto.setTotalCount(totalCount);
		} catch (Exception e) {
			logger.error(e);

			System.out.println("Error in ProductTranxDAOImpl search method : "
					+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in ProductTranxDAOImpl search  method :" + e);

		}
		return objSearchCollection;
	}

	public boolean addProductTranx(CardProductTranxDto objCardProductTranxDto) throws TPlusException {
		boolean bolExecute = false;
		Transaction tx = null;
		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			session.save(objCardProductTranxDto);
			tx.commit();
			bolExecute = true;
		}catch (Exception e) {
				if (tx != null) {
					tx.rollback();
				}
				throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
						"Error: in ProductTranxDAOImpl addProductTranx method" + e);
			} finally {
				HibernetFactory.closeSession();
			}
			return bolExecute;
	}
	
	public TranxTypeDto getTranxTypeDto(String tranxType) throws TPlusException {
		TranxTypeDto objTranxTypeDto = null;
		Transaction tx = null;
		try {

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			objTranxTypeDto = (TranxTypeDto) session.get(TranxTypeDto.class, tranxType);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"ProductTranxDAOImpl getTranxTypeDto method :" + e);
		} finally {
			HibernetFactory.closeSession();
		}

		return objTranxTypeDto;
	}
	
	//check CardProductTranxDto is exist or not
	public boolean checkExistRecord(CardProductTranxDto objCardProductTranxDto) throws TPlusException {

		boolean exist = false;
		CardProductTranxDto objDto = null;
		Transaction tx = null;
		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			String productId = objCardProductTranxDto.id.getProductId();
			String channel = objCardProductTranxDto.id.getChannel();
			objDto = (CardProductTranxDto) session.get(CardProductTranxDto.class, new CardProductTranxDto.Id(productId, channel));
			tx.commit();
			
			if(objDto != null) {
				exist = true;
			}
		}
		catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.out
					.println("Error in ProductTranxDAOImpl checkExistRecord method:"
							+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error:  Error in ProductTranxDAOImpl checkExistRecord method:"	+ e);
		}

		finally {
			HibernetFactory.closeSession();
		}

		return exist;

	}

	//update TranxType for CardProductTranxDto
	public boolean update(CardProductTranxDto objDto) throws TPlusException {
		boolean boolUpdate = false;
		Transaction tx = null;
		int count = 0;
		CardProductTranxDto objCardProductTranxDto = null;
		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			String productId = objDto.id.getProductId();
			String channel = objDto.id.getChannel();
			
			objCardProductTranxDto = (CardProductTranxDto) session.get(CardProductTranxDto.class, new CardProductTranxDto.Id(productId, channel));
			objCardProductTranxDto.setTranxId(objDto.getTranxId());
			session.update(objCardProductTranxDto);
			tx.commit();
			boolUpdate = true;

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"ProductTranxDAOImpl update  method :" + e);
		} finally {
			HibernetFactory.closeSession();
		}

		return boolUpdate;
	}
}
