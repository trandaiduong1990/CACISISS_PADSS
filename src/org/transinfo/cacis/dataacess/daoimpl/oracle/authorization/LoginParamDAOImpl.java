package org.transinfo.cacis.dataacess.daoimpl.oracle.authorization;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.transinfo.cacis.TPlusCodes;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.HibernetDAO.HibernetFactory;
import org.transinfo.cacis.dataacess.dao.authorization.LoginParamDAO;
import org.transinfo.cacis.dataacess.daoimpl.oracle.BaseDAOImpl;
import org.transinfo.cacis.dto.authorization.LoginParamDto;


public class LoginParamDAOImpl extends BaseDAOImpl implements LoginParamDAO {

        	       
        
         public LoginParamDto get(String issuerid) throws TPlusException {
             
		LoginParamDto loginParamDto = new LoginParamDto();
		Transaction tx = null;                                            
		try
		{                                                                                                                         
                    Session session = HibernetFactory.currentSession();
                    tx = session.beginTransaction();
                    System.out.println("issuerid from impl===>"+issuerid);                                                             
                    loginParamDto = (LoginParamDto) session.get(LoginParamDto.class, issuerid);
                        
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
		return loginParamDto;

	}
        
        
        public boolean add(LoginParamDto objDto)
            throws TPlusException{

            boolean bolExecute=false;
            Transaction tx = null;
            try{

                Session session = HibernetFactory.currentSession();

                tx = session.beginTransaction();
                System.out.println("Saving........");                   
                session.save(objDto);
                System.out.println("LoginParamDto object persisted to the database.");
                tx.commit();
                bolExecute=true;

          }catch(Exception ex)
          {
            if (tx != null)
            {
		tx.rollback();
            }              
            throw new TPlusException(TPlusCodes.APPLICATION_ERROR,"Error: while retrieving Info"+ex.getMessage());
          }
            finally
            {
                HibernetFactory.closeSession();
            }
            return 	bolExecute;

        }
                
        
        public boolean update(LoginParamDto objDto)throws TPlusException
	{
		boolean bolExecute = false;
		Transaction tx = null;
		try
		{
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			session.update(objDto);			
			tx.commit();
			System.out.println("LoginParamDto object updated to the database.");
			bolExecute = true;
		}

		catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.out.println("Error while updating LoginParamForm data "+ e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
									  "Error: while updating LoginParamForm data" + e);

		} finally
		{
			HibernetFactory.closeSession();
		}

		return bolExecute;
	}
        
             
          
}


