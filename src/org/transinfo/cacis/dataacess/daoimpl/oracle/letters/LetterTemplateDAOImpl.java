package org.transinfo.cacis.dataacess.daoimpl.oracle.letters;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.transinfo.cacis.TPlusCodes;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.common.CommonDataBean;
import org.transinfo.cacis.common.constants.CommonCodes;
import org.transinfo.cacis.dataacess.HibernetDAO.HibernetFactory;
import org.transinfo.cacis.dataacess.dao.letters.LetterTemplateDAO;
import org.transinfo.cacis.dataacess.daoimpl.oracle.BaseDAOImpl;
import org.transinfo.cacis.dto.letters.LetterCategoryDto;
import org.transinfo.cacis.dto.letters.LetterTemplateDto;
import org.transinfo.cacis.dto.letters.LetterTemplateSearchDto;

/**
 * LetterTemplateDAOImpl
 * 
 * @author hoang-vu
 * 
 */
public class LetterTemplateDAOImpl extends BaseDAOImpl implements LetterTemplateDAO {

	private Logger logger = Logger.getLogger(LetterTemplateDAOImpl.class);

	@Override
	public LetterTemplateSearchDto search(LetterTemplateSearchDto objDto, int pageNo) throws TPlusException {

		Collection objSearchCollection = new ArrayList();
		StringBuffer sbf = new StringBuffer();
		StringBuffer sbfSelect = new StringBuffer();
		StringBuffer sbfCount = new StringBuffer();
		int totalCount = 0;
		
		try {
			
			sbfSelect.append("Select ltd.letterId, ltd.description, ltd.lastUpdatedBy, ltd.lastUpdatedDate  ");
			
			sbfCount.append("select ");
			sbfCount.append("count(ltd.letterId) ");
			
			sbf.append("From LetterTemplateDto ltd ");
			sbf.append("Where ltd.status='A' ");
			
			if (objDto.getLetterId() != null
					&& !objDto.getLetterId().equals("")) {
				sbf.append("And ltd.letterId = '" + objDto.getLetterId() + "' ");
			}
			
			objSearchCollection = getSearchList(sbfSelect.append(sbf).toString(), pageNo);
			totalCount = getSearchTotalCount((sbfCount.append(sbf)).toString());
			
			objDto.setSearchList(objSearchCollection);
			objDto.setTotalCount(totalCount);

		} catch (Exception ex) {
			logger.error(ex);

			System.out.println("Error in LetterTemplateDAOImpl search method : "
					+ ex.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in LetterTemplateDAOImpl search  method :" + ex);
		}
		
		return objDto;
	}

	@Override
	public LetterCategoryDto getLetterCategory(String letterCategory)
			throws TPlusException {
		LetterCategoryDto objDto = new LetterCategoryDto();
		Transaction tx = null;
		StringBuffer sbf = new StringBuffer();
		
		try {

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			sbf.append("From LetterCategoryDto lcd ");
			sbf.append("Where lcd.id = '"
					+ letterCategory + "' ");

			Query qry = session.createQuery(sbf.toString());
			List list = qry.list();

			if (list.size() > 0) {
				objDto = (LetterCategoryDto) list.get(0);
			}

			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.error(e);
			System.out
					.println("Error in LetterTemplateDAOImpl getLetterCategory method : "
							+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in LetterTemplateDAOImpl getLetterCategory  method :"
							+ e);
		} finally {
			HibernetFactory.closeSession();
		}

		return objDto;
	}
	
	@Override
	public int checkExitsRecord(LetterTemplateDto objDto) throws TPlusException {
		
		int resCord = 0;
		
		try {
			Session session = HibernetFactory.currentSession();
			Query qry = session
					.createQuery("Select count(*) From LetterTemplateDto ltd where ltd.letterId=:letterid ");
			qry.setString("letterid", objDto.getLetterId());
			List list = qry.list();
			resCord = ((Integer) list.get(0)).intValue();
			System.out.println("After LetterTemplateDAOImpl checkExistrecord()"
					+ resCord);
		} catch (Exception ex) {
			logger.error(ex);
			System.out
					.println("Error in LetterTemplateDAOImpl checkExist record method:"
							+ ex.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error:  Error in LetterTemplateDAOImpl checkExistrecord method:"
							+ ex);
		} finally {
			HibernetFactory.closeSession();
		}
		
		return resCord;
	}

	@Override
	public boolean create(LetterTemplateDto objDto) throws TPlusException {
		
		boolean bolExecute = false;
		Transaction tx = null;
		try {

			String filename = objDto.getFileUpload().getFileName();
			System.out.println("filename = " + filename);

			String parent = UPLOAD_DOCUMENTS_PATH + "letters";
			String fullFileName = parent + "\\" + filename;
			File outputFolder = new File(parent);
			if (!outputFolder.exists()) {
				outputFolder.mkdirs();
			}

			File outputFile = new File(fullFileName);
			System.out.println("fullFileName => " + fullFileName);
			InputStream in = objDto.getFileUpload().getInputStream();
			OutputStream out = new FileOutputStream(outputFile);

			// Transfer bytes from in to out
			byte[] buf = new byte[1024];
			int len;
			while ((len = in.read(buf)) > 0) {
				out.write(buf, 0, len);
			}
			in.close();
			out.close();

			objDto.setApplicationSource("/LETTERS/" + filename);
			objDto.setStatus(CommonCodes.STATUS_ACTIVE);

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			
			session.save(objDto);

			tx.commit();
			bolExecute = true;
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.out.println("Exception in LetterTemplateDAOImpl create"
					+ e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while saving data in LetterTemplateDAOImpl" + e);
		} finally {
			HibernetFactory.closeSession();
		}
		return bolExecute;
	}

	@Override
	public LetterTemplateDto getLetterTemplateDetail(String letterId)
			throws TPlusException {

		LetterTemplateDto objDto = new LetterTemplateDto();
		Transaction tx = null;
		StringBuffer sbf = new StringBuffer();
		
		try {

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			sbf.append("From LetterTemplateDto ltd ");
			sbf.append("Where ltd.letterId = '"
					+ letterId + "' ");

			Query qry = session.createQuery(sbf.toString());
			List list = qry.list();

			if (list.size() > 0) {
				objDto = (LetterTemplateDto) list.get(0);
			}

			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.error(e);
			System.out
					.println("Error in LetterTemplateDAOImpl getLetterTemplateDetail method : "
							+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in LetterTemplateDAOImpl getLetterTemplateDetail  method :"
							+ e);
		} finally {
			HibernetFactory.closeSession();
		}

		return objDto;
	}

	@Override
	public boolean update(LetterTemplateDto objDto) throws TPlusException {

		boolean boolUpdate = false;
		Transaction tx = null;
		try {
			if (!StringUtils.isBlank(objDto.getFileUpload().getFileName())) {
				String filename = objDto.getFileUpload().getFileName();
				System.out.println("filename = " + filename);

				String parent = UPLOAD_DOCUMENTS_PATH + "letters";
				String fullFileName = parent + "\\" + filename;
				File outputFolder = new File(parent);
				if (!outputFolder.exists()) {
					outputFolder.mkdirs();
				}

				File outputFile = new File(fullFileName);
				System.out.println("fullFileName => " + fullFileName);
				InputStream in = objDto.getFileUpload().getInputStream();
				OutputStream out = new FileOutputStream(outputFile);

				// Transfer bytes from in to out
				byte[] buf = new byte[1024];
				int len;
				while ((len = in.read(buf)) > 0) {
					out.write(buf, 0, len);
				}
				in.close();
				out.close();

				objDto.setApplicationSource("/LETTERS/" + filename);
				objDto.setStatus(CommonCodes.STATUS_ACTIVE);
			}
			
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
			System.out.println("Error in LetterTemplateDAOImpl update method : "
					+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in LetterTemplateDAOImpl update  method :" + e);
		} finally {
			HibernetFactory.closeSession();
		}
		
		return boolUpdate;
	}

	@Override
	public boolean delete(LetterTemplateDto objDto) throws TPlusException {

		boolean boolDelete = false;
		Transaction tx = null;
		
		objDto.setStatus(CommonCodes.STATUS_DEACTIVE);
		
		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			String sql = "UPDATE LetterTemplateDto SET status=:status WHERE letterId=:letterid";

			int count = session.createQuery(sql).setString("letterid",
					objDto.getLetterId()).setString("status",
					objDto.getStatus()).executeUpdate();

			tx.commit();
			if (count > 0)
				boolDelete = true;

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.error(e);
			System.out.println("Error in LetterTemplateDAOImpl delete method : "
					+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in LetterTemplateDAOImpl delete  method :" + e);
		} finally {
			HibernetFactory.closeSession();
		}
		
		return boolDelete;
	}

	@Override
	public Collection getAllLetterTemplate() throws TPlusException {
		
		Collection objLetterTemplCollection = new ArrayList();
		StringBuffer sbf = new StringBuffer();
		Transaction tx = null;
		
		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			
			sbf.append("From LetterTemplateDto ltd ");
			sbf.append("Where ltd.status='A' ");
			
			Query qry = session.createQuery(sbf.toString());
			List list = qry.list();

			for (int i = 0; i < list.size(); i++) {
				LetterTemplateDto objDto = new LetterTemplateDto();
				objDto = (LetterTemplateDto) list.get(i);
				objLetterTemplCollection.add(objDto);
			}

		} catch (Exception ex) {
			logger.error(ex);

			System.out.println("Error in LetterTemplateDAOImpl getAllLetterTemplate method : "
					+ ex.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in LetterTemplateDAOImpl getAllLetterTemplate  method :" + ex);
		}
		
		return objLetterTemplCollection;
	}

	@Override
	public Map executeQuery(String sqlQuery) throws TPlusException {
		
		Transaction tx = null;
		Map listParams = new HashMap();
		CommonDataBean objLetter = new CommonDataBean();
		Class adminLetterClass = objLetter.getClass();
		
		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			
			sqlQuery.replace("\n", "").replace("\r", "");
			SQLQuery qry = checkScalar(session, sqlQuery, objLetter.lstParam);
			List list = qry.list();
			Object[] temp = (Object[]) list.get(0);
			
			for(int i=0;i<temp.length;i++) {
				if (temp[i] == null) {
					temp[i] = "";
				}
				listParams.put(objLetter.lstParam.get(i), temp[i].toString());
			}
			
			tx.commit();
		} catch (Exception ex) {
			logger.error(ex);

			System.out.println("Error in LetterTemplateDAOImpl executeQuery method : "
					+ ex.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in LetterTemplateDAOImpl executeQuery  method :" + ex);
		}
		return listParams;
	}

	/**
	* this method add param to query
	*/
	private SQLQuery checkScalar(Session session, String sqlQuery, List<String> listParam) {

		SQLQuery qry = session.createSQLQuery(sqlQuery);
		String toLowercase = sqlQuery.toLowerCase();
		for (int i = 0; i < listParam.size(); i++) {
			if (toLowercase.contains(listParam.get(i).toLowerCase())) {
				qry.addScalar(listParam.get(i), Hibernate.STRING);
			}
		}
		
		return qry;
	}
}
