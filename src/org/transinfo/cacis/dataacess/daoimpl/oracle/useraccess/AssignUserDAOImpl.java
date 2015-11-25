package org.transinfo.cacis.dataacess.daoimpl.oracle.useraccess;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.transinfo.cacis.TPlusCodes;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.HibernetDAO.HibernetFactory;
import org.transinfo.cacis.dataacess.dao.useraccess.AssignUserDAO;
import org.transinfo.cacis.dataacess.dao.useraccess.UserSetupDAO;
import org.transinfo.cacis.dataacess.daoimpl.oracle.BaseDAOImpl;
import org.transinfo.cacis.dto.settings.BranchDto;
import org.transinfo.cacis.dto.useraccess.AssignUserDto;
import org.transinfo.cacis.dto.useraccess.CodeMasterDto;
import org.transinfo.cacis.dto.useraccess.RoleMasterDto;
import org.transinfo.cacis.dto.useraccess.UserMasterDto;
import org.transinfo.cacis.dto.useraccess.UserSetupSearchDto;

public class AssignUserDAOImpl extends BaseDAOImpl implements AssignUserDAO {

	public Collection search(AssignUserDto objSearchDto, int pageNo)
			throws TPlusException {

		Collection objSearchCollection = null;
		StringBuffer sbf = new StringBuffer();
		try {

			sbf.append("select au.assignId, cm.codeDesc, um.firstName, ");
			sbf.append("au.status FROM  AssignUserDto au, CodeMasterDto cm, UserMasterDto um ");
			sbf.append("where au.roleId = cm.id.codeId and cm.id.groupId = 'ASSIGNROLE' and au.userId = um.id.userId  ");
			sbf.append("and au.status = 'A' ");
			
			if (objSearchDto.getRoleId() != null
					&& !objSearchDto.getRoleId().equals("")) {
				sbf.append(" and au.roleId = '" + objSearchDto.getRoleId() + "' ");
			}

			if (objSearchDto.getUserId() != null
					&& !objSearchDto.getUserId().equals("")) {
				sbf.append(" and au.userId = '" + objSearchDto.getUserId()	+ "' ");
			}

			if (objSearchDto.getLower() != 0) {
				sbf.append(" and au.lower = " + objSearchDto.getLower());
			}

			if (objSearchDto.getUpper() != 0) {
				sbf.append(" and au.upper = " + objSearchDto.getUpper());
			}
			
			objSearchCollection = getSearchList(sbf.toString(), pageNo);

			System.out.println("Size===> " + objSearchCollection.size());

		} catch (Exception e) {
			System.out
					.println("Error while retrieving the AssignUserDAOImpl Search Info"
							+ e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving the UserSetup Search Info" + e);
		} finally {
			HibernetFactory.closeSession();
		}
		return objSearchCollection;
	}

	public boolean add(AssignUserDto objDto) throws TPlusException {

		boolean bolExecute = false;
		Transaction tx = null;
		try {

			Session session = HibernetFactory.currentSession();

			tx = session.beginTransaction();
			System.out.println("Saving........");
			session.save(objDto);
			System.out.println("AssignUserDto object persisted to the database.");
			tx.commit();
			bolExecute = true;

		} catch (Exception ex) {
			if (tx != null) {
				tx.rollback();
			}
			System.out.println("In ADD Exception=" + ex);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving Info" + ex);
		} finally {
			HibernetFactory.closeSession();
		}

		return bolExecute;
	}

	public boolean update(AssignUserDto objDto) throws TPlusException {
		boolean bolExecute = false;
		Transaction tx = null;
		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			session.update(objDto);
			tx.commit();
			System.out.println("AssignUserDto object updated to the database.");
			bolExecute = true;
		}

		catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.out.println("Error while updating AssignUserDto data " + e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while updating AssignUserDto data" + e);

		} finally {
			HibernetFactory.closeSession();
		}

		return bolExecute;
	}


	public Map getRoleId(String groupId) throws TPlusException {

		Map roleList = new HashMap();
		Transaction tx = null;
		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			Query qry = session
					.createQuery("From CodeMasterDto cm where cm.id.groupId = '"
							+ groupId + "'");
			List listDocs = qry.list();

			for (Iterator it = listDocs.iterator(); it.hasNext();) {
				CodeMasterDto objCodeMasterDto = new CodeMasterDto();
				objCodeMasterDto = (CodeMasterDto) it.next();
				roleList.put(objCodeMasterDto.getId().getCodeId(),
						objCodeMasterDto.getCodeDesc());
			}
			System.out.println("roleList==>" + roleList.size());
			tx.commit();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.out.println("Error in getRoleId method" + e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving getRoleId Info" + e.getMessage());
		} finally {
			HibernetFactory.closeSession();
		}
		return roleList;
	}

	@Override
	public AssignUserDto getAssignUserForm(String assignId)
			throws TPlusException {
		AssignUserDto objDto = null;
		Transaction tx = null;
		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			objDto = (AssignUserDto) session.get(AssignUserDto.class, assignId);
			// session.load(objDto,branchId );
			tx.commit();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.out.println("Error in AssignUserDAOImpl getAssignUserForm method : "
					+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: in BranchDAOImpl getBranchDto  method :" + e);
		} finally {
			HibernetFactory.closeSession();
		}
		return objDto;
	}

}
