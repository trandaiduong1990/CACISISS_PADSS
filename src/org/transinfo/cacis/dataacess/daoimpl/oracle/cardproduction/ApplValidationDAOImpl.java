package org.transinfo.cacis.dataacess.daoimpl.oracle.cardproduction;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.transinfo.cacis.TPlusCodes;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.HibernetDAO.HibernetFactory;
import org.transinfo.cacis.dataacess.dao.cardproduction.ApplValidationDAO;
import org.transinfo.cacis.dataacess.daoimpl.oracle.BaseDAOImpl;
import org.transinfo.cacis.dto.cardproduction.ApplValidationDto;
import org.transinfo.cacis.dto.cardproduction.ApplValidationSearchDto;

public class ApplValidationDAOImpl extends BaseDAOImpl implements ApplValidationDAO  {

	private Logger logger = Logger.getLogger(ApplValidationDAOImpl.class);
	
	@Override
	public boolean create(ApplValidationDto objApplValidionDto) throws TPlusException {

		boolean boolCreate = false;
		Transaction tx = null;
		
		objApplValidionDto.setStatus("A");
		
		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			session.save(objApplValidionDto);
			
			tx.commit();
			boolCreate = true;
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.error(e);
			System.out.println("Error in ApplValidationDAOImpl add method : "
					+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in ApplValidationDAOImpl add  method :" + e);
		} finally {
			HibernetFactory.closeSession();
		}
		
		return boolCreate;
	}

	@Override
	public int checkExitsRecord(ApplValidationDto objApplValidationDto)
			throws TPlusException {

		int resCord = 0;
		
		try {
			Session session = HibernetFactory.currentSession();
			Query qry = session
					.createQuery("Select count(*) From ApplValidationDto apv where apv.applValName=:applval ");
			qry.setString("applval", objApplValidationDto.getApplValName());
			List list = qry.list();
			resCord = ((Integer) list.get(0)).intValue();
			System.out.println("After ApplValidationDAOImpl checkExistrecord()"
					+ resCord);
		} catch (Exception ex) {
			logger.error(ex);
			System.out
					.println("Error in ApplValidationDAOImpl checkExist record method:"
							+ ex.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error:  Error in ApplValidationDAOImpl checkExistrecord method:"
							+ ex);
		} finally {
			HibernetFactory.closeSession();
		}
		
		return resCord;
	}

	@Override
	public Collection search(ApplValidationSearchDto objDto, int pageNo)
			throws TPlusException {

		Collection objSearchCollection = new ArrayList();
		StringBuffer sbf = new StringBuffer();
		
		try {
			
			sbf.append("Select apv.applValId, apv.applValName, apv.lastUpdatedDate  ");
			sbf.append("From ApplValidationDto apv ");
			sbf.append("Where apv.status = 'A' ");
			
			if (!StringUtils.isBlank(objDto.getApplValName())) {
				sbf.append("And apv.applValName='" + objDto.getApplValName() + "' ");
			}
			
			objSearchCollection = getSearchList(sbf.toString(), pageNo);

		} catch (Exception ex) {
			logger.error(ex);

			System.out.println("Error in ApplValidationDAOImpl search method : "
					+ ex.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in ApplValidationDAOImpl search  method :" + ex);
		}
		
		return objSearchCollection;
	}

	@Override
	public ApplValidationDto getApplValidationDetail(String applValId)
			throws TPlusException {

		ApplValidationDto objDto = new ApplValidationDto();
		Transaction tx = null;
		StringBuffer sbf = new StringBuffer();
		
		try {

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			sbf.append("From ApplValidationDto apv ");
			sbf.append("Where apv.applValId = '"
					+ applValId + "' ");

			Query qry = session.createQuery(sbf.toString());
			List list = qry.list();

			if (list.size() > 0) {
				objDto = (ApplValidationDto) list.get(0);
			}

			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.error(e);
			System.out
					.println("Error in ApplValidationDAOImpl getApplValidationDetail method : "
							+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in ApplValidationDAOImpl getApplValidationDetail method :"
							+ e);
		} finally {
			HibernetFactory.closeSession();
		}

		return objDto;
	}

	@Override
	public boolean update(ApplValidationDto objDto) throws TPlusException {

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
			System.out.println("Error in ApplValidationDAOImpl update method : "
					+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in ApplValidationDAOImpl update  method :" + e);
		} finally {
			HibernetFactory.closeSession();
		}
		
		return boolUpdate;
	}

	@Override
	public boolean delete(ApplValidationDto objApplValidionDto)
			throws TPlusException {

		boolean boolDelete = false;
		Transaction tx = null;
		
		objApplValidionDto.setStatus("D");
		
		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			String sql = "UPDATE ApplValidationDto SET status=:status WHERE applValName=:applname";

			int count = session.createQuery(sql).setString("applname",
					objApplValidionDto.getApplValName()).setString("status",
					objApplValidionDto.getStatus()).executeUpdate();

			tx.commit();
			if (count > 0)
				boolDelete = true;

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.error(e);
			System.out.println("Error in ApplValidationDAOImpl delete method : "
					+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in ApplValidationDAOImpl delete  method :" + e);
		} finally {
			HibernetFactory.closeSession();
		}
		
		return boolDelete;
	}
}
