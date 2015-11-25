package org.transinfo.cacis.dataacess.daoimpl.oracle.collectionmanagement;

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
import org.transinfo.cacis.dataacess.dao.collectionmanagement.CollectionConfigDAO;
import org.transinfo.cacis.dataacess.dao.collectionmanagement.DelinquencyPolicyDAO;
import org.transinfo.cacis.dataacess.daoimpl.oracle.BaseDAOImpl;
import org.transinfo.cacis.dto.administration.LicenseMasterDto;
import org.transinfo.cacis.dto.batchprocess.InstantCardDto;
import org.transinfo.cacis.dto.collectionmanagement.CollectionConfigDto;
import org.transinfo.cacis.dto.collectionmanagement.DelinquencyPolicyDto;
import org.transinfo.cacis.dto.collectionmanagement.DelinquencyPolicySearchDto;

public class CollectionConfigDAOImpl extends BaseDAOImpl implements
		CollectionConfigDAO {
	private Logger logger = Logger.getLogger(CollectionConfigDAOImpl.class);

	@Override
	public boolean upload(CollectionConfigDto objDto) throws TPlusException {

		boolean boolCreate = false;
		Transaction tx = null;

		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			session.save(objDto);

			tx.commit();
			boolCreate = true;
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.error(e);
			System.out
					.println("Error in CollectionConfigDAOImpl upload method : "
							+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in CollectionConfigDAOImpl upload  method :" + e);
		} finally {
			HibernetFactory.closeSession();
		}

		return boolCreate;
	}


	@Override
	public boolean update(CollectionConfigDto objDto) throws TPlusException {

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
					.println("Error in CollectionConfigDAOImpl update method : "
							+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in CollectionConfigDAOImpl update  method :" + e);
		} finally {
			HibernetFactory.closeSession();
		}

		return boolUpdate;
	}

	@Override
	public CollectionConfigDto getCollectionConfig() throws TPlusException {
		CollectionConfigDto collectionConfig = new CollectionConfigDto();
		Transaction tx = null;
		StringBuffer sbf = new StringBuffer();
		String issuerId = "";
		try {
			sbf.append("select cc.issuerId FROM CollectionConfigDto cc ");

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			Query qry = session.createQuery(sbf.toString());
			List listCollectionConfig = qry.list();
			for (Iterator it = listCollectionConfig.iterator(); it.hasNext();) {
				issuerId = it.next().toString();
			}
			collectionConfig = (CollectionConfigDto) session.get(
					CollectionConfigDto.class, issuerId);
			tx.commit();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}

			System.out.println("Error in getCollectionConfig method" + e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving the getCollectionConfig" + e);
		} finally {
			HibernetFactory.closeSession();
		}
		return collectionConfig;
	}
}
