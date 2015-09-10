package org.transinfo.cacis.dataacess.daoimpl.oracle.switching;

import java.util.Collection;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.transinfo.cacis.TPlusCodes;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.HibernetDAO.HibernetFactory;
import org.transinfo.cacis.dataacess.dao.switching.SwitchDAO;
import org.transinfo.cacis.dataacess.daoimpl.oracle.BaseDAOImpl;
import org.transinfo.cacis.dto.switching.SwitchDto;
import org.transinfo.cacis.dto.switching.SwitchSearchDto;


public class SwitchDAOImpl extends BaseDAOImpl implements SwitchDAO {

    
    	public Collection get(SwitchSearchDto objSearchDto, int pageNo)
			throws TPlusException
	{

		Collection objSearchCollection = null;
		StringBuffer sbf = new StringBuffer();
		try {

			sbf.append("select sw.swName, sw.swType,  sw.swIpAddress, ");
                        sbf.append(" sw.swPort, sw.swTimeOut1, sw.swTimeOut2, to_char(sw.updatedDate,'dd-MM-yyyy'), ");
			sbf.append(" sw.userId FROM  SwitchDto sw ");
                 
		   /*	if (objSearchDto.getSearchAlertCode()!= null && !objSearchDto.getSearchAlertCode().equals(""))
			{
				sbf.append("where am.alertCode like '%"+ objSearchDto.getSearchAlertCode()+ "%' ");
			} */ 
                                
			objSearchCollection = getSearchList(sbf.toString(),pageNo);

                        System.out.println("Size===> "+objSearchCollection.size());
                        
		} catch (Exception e) {
			System.out.println("Error while retrieving the Switch Search Info" + e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
									 "Error: while retrieving the Switch Search Info" + e);
		} finally {
			HibernetFactory.closeSession();
		}
		return objSearchCollection;
	}

        
        public SwitchDto getSwitchForm(String swType) throws TPlusException {
		SwitchDto swDto = null;
		Transaction tx = null;
		try
		{

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			swDto = (SwitchDto) session.get(SwitchDto.class, swType);
			tx.commit();

		}catch (Exception e)
		{
                    if (tx != null)
                    {
                        tx.rollback();
                    }

                    System.out.println("Error in getSwitchForm method"+ e);
                    throw new TPlusException(TPlusCodes.APPLICATION_ERROR,"Error: while retrieving the getSwitchForm" + e);
		}
		finally
		{
			HibernetFactory.closeSession();
		}
		return swDto;
	}

        
        public boolean add(SwitchDto objDto)
            throws TPlusException{
            
	    boolean bolExecute=false;
            Transaction tx = null;
        try{

            Session session = HibernetFactory.currentSession();

            tx = session.beginTransaction();
            System.out.println("Saving........");                   
            session.save(objDto);
            System.out.println("SwitchDto object persisted to the database.");
            tx.commit();            
            bolExecute=true;

          }catch(Exception ex){
            if (tx != null)
            {
                tx.rollback();
            }
            throw new TPlusException(TPlusCodes.APPLICATION_ERROR,"Error: while retrieving Info"+ex.getMessage());
          }finally
	   {
	     HibernetFactory.closeSession();
	   }

            return  bolExecute;

        }
        
        
        public boolean update(SwitchDto objDto)throws TPlusException
	{
		boolean bolExecute = false;
		Transaction tx = null;
		try
		{
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			session.update(objDto);			
			tx.commit();
			System.out.println("SwitchDto object updated to the database.");
			bolExecute = true;
		}

		catch (Exception e) 
                {
                    if (tx != null) 
                    {
			tx.rollback();
                    }
		    System.out.println("Error while updating SwitchForm data "+ e);
		    throw new TPlusException(TPlusCodes.APPLICATION_ERROR,"Error: while updating SwitchForm data" + e);
		}finally
		{
			HibernetFactory.closeSession();
		}

		return bolExecute;
	}


        public boolean delete(SwitchDto objDto)
            throws TPlusException {
              	boolean bolExecute=false;
                Transaction tx = null;
            try{
                System.out.println("SwitchDto object deleted from the database.");
                Session session = HibernetFactory.currentSession();

                tx = session.beginTransaction();

                session.delete(objDto);
                tx.commit();
                
		bolExecute=true;
		System.out.println("SwitchDto object deleted from the database.");
                }catch(Exception ex){
                    if (tx != null) 
                    {
			tx.rollback();
                    }                    
                    throw new TPlusException(TPlusCodes.APPLICATION_ERROR,"Error: while deleting"+ex.getMessage());
                }finally
		{
                    HibernetFactory.closeSession();
		}

                return 	bolExecute;
            }
                    

public static void main(String s[])
{

    try
    {
          SwitchDto dto = new SwitchDto();
         
         SwitchDAOImpl impl = new SwitchDAOImpl();
         //impl.get(dto,0);
         //ArrayList arrl = new ArrayList();
         
    }
    catch(Exception e){System.out.println(e);}
}

}


