package org.transinfo.cacis.dataacess.daoimpl.oracle.administration;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.transinfo.cacis.TPlusCodes;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.HibernetDAO.HibernetFactory;
import org.transinfo.cacis.dataacess.dao.administration.LicenseMasterDAO;
import org.transinfo.cacis.dataacess.daoimpl.oracle.BaseDAOImpl;
import org.transinfo.cacis.dto.administration.LicenseMasterDto;
import org.transinfo.cacis.dto.administration.LicenseMasterSearchDto;


public class LicenseMasterDAOImpl extends BaseDAOImpl implements LicenseMasterDAO {

        	       
        
         public LicenseMasterDto get() throws TPlusException {
             
		LicenseMasterDto licenseMaster = new LicenseMasterDto();
		Transaction tx = null;                
                StringBuffer sbf = new StringBuffer();
                String serial = "";
		try
		{      
                        sbf.append("select lm.serialNo FROM LicenseMasterDto lm ");
                                                                      
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
                        
                        Query qry = session.createQuery(sbf.toString());                        
                        List listCardTypes = qry.list();                        
                        for(Iterator it = listCardTypes.iterator();it.hasNext();)
			{
                               serial = it.next().toString();
                        }                         
			licenseMaster = (LicenseMasterDto) session.get(LicenseMasterDto.class, serial);
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
		return licenseMaster;

	}
        
        
        public boolean upload(LicenseMasterDto objDto)
            throws TPlusException{

            boolean bolExecute=false;
            Transaction tx = null;
            try{
                Session session = HibernetFactory.currentSession();
                tx = session.beginTransaction();
                System.out.println("Saving........");                   
                session.save(objDto);
                System.out.println("LicenseMasterDto object persisted to the database.");
                tx.commit();
                bolExecute=true;

              }catch(Exception ex){
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
                
        
        public boolean update(LicenseMasterDto objDto)throws TPlusException
	{
		boolean bolExecute = false;
		Transaction tx = null;
		try
		{
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			session.update(objDto);			
			tx.commit();
			System.out.println("LicenseMasterDto object updated to the database.");
			bolExecute = true;
		}

		catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.out.println("Error while updating LicenseMasterForm data "+ e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
									  "Error: while updating LicenseMasterForm data" + e);

		} finally
		{
			HibernetFactory.closeSession();
		}

		return bolExecute;
	}

          

public static void main(String s[])
{

    try
    {
          LicenseMasterDto dto = new LicenseMasterDto();
          LicenseMasterSearchDto searchDto = new LicenseMasterSearchDto();

         LicenseMasterDAOImpl impl = new LicenseMasterDAOImpl();
         
                    
    }
    catch(Exception e){System.out.println(e);}
}

}


