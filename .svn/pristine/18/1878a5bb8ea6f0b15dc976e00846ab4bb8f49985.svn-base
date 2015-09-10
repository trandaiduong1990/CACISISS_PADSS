package org.transinfo.cacis.dataacess.daoimpl.oracle.useraccess;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.transinfo.cacis.TPlusCodes;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.HibernetDAO.HibernetFactory;
import org.transinfo.cacis.dataacess.dao.useraccess.PasswordChangeDAO;
import org.transinfo.cacis.dataacess.daoimpl.oracle.BaseDAOImpl;
import org.transinfo.cacis.dto.useraccess.UserMasterDto;


public class PasswordChangeDAOImpl extends BaseDAOImpl implements PasswordChangeDAO {

        	       
        //This method gets the record matching with issuerid and userid
         public UserMasterDto get(String issuerId, String userId) throws TPlusException {
		UserMasterDto userMaster = new UserMasterDto(); 
		Transaction tx = null;
                
                StringBuffer sbf = new StringBuffer();
                                                         
		try
		{      
                                                                       
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();   
                                             
			userMaster = (UserMasterDto) session.get(UserMasterDto.class, new UserMasterDto.Id(issuerId,userId));
			tx.commit();

		}catch (Exception e)
		{
			if (tx != null)
			{
				tx.rollback();
			}

			System.out.println("Error in get method"+ e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
									 "Error: while retrieving the get" + e);
		}
		finally
		{
			HibernetFactory.closeSession();
		}
		return userMaster;

	}
        
                       
        public boolean update(UserMasterDto objDto)throws TPlusException
	{
		boolean bolExecute = false;
		Transaction tx = null;
		try
		{
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			session.update(objDto);
			session.flush();
			tx.commit();
			System.out.println("UserMasterDto object updated to the database.");
			bolExecute = true;
		}

		catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.out.println("Error while updating UserMasterForm data "+ e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
									  "Error: while updating UserMasterForm data" + e);

		} finally
		{
			HibernetFactory.closeSession();
		}

		return bolExecute;
	}

          
}


