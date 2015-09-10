package org.transinfo.cacis.dataacess.daoimpl.oracle.alert;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.transinfo.cacis.TPlusCodes;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.common.CommonDataBean;
import org.transinfo.cacis.dataacess.HibernetDAO.HibernetFactory;
import org.transinfo.cacis.dataacess.dao.alert.AlertMasterDAO;
import org.transinfo.cacis.dataacess.daoimpl.oracle.BaseDAOImpl;
import org.transinfo.cacis.dto.alert.AlertMasterDto;
import org.transinfo.cacis.dto.alert.AlertMasterSearchDto;


public class AlertMasterDAOImpl extends BaseDAOImpl implements AlertMasterDAO {

    
    	public Collection search(AlertMasterSearchDto objSearchDto, int pageNo)
			throws TPlusException
	{

		Collection objSearchCollection = null;
		StringBuffer sbf = new StringBuffer();
		try {

			sbf.append("select am.alertCode, am.alertDesc, to_char(am.updatedDate,'dd-MM-yyyy'), ");
			sbf.append(" am.userId FROM  AlertMasterDto am ");
                 
			if (objSearchDto.getSearchAlertCode()!= null && !objSearchDto.getSearchAlertCode().equals(""))
			{
				sbf.append("where am.alertCode = '"+ objSearchDto.getSearchAlertCode()+ "' ");
			} 
                                
			objSearchCollection = getSearchList(sbf.toString(),pageNo);

                        System.out.println("Size===> "+objSearchCollection.size());
                        
		} catch (Exception e) {
			System.out.println("Error while retrieving the AlertMaster Search Info" + e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
									 "Error: while retrieving the AlertMaster Search Info" + e);
		} finally {
			HibernetFactory.closeSession();
		}
		return objSearchCollection;
	}

        public AlertMasterDto getAlertMasterForm(String alertCode) throws TPlusException {
		AlertMasterDto alertMaster = null;
		Transaction tx = null;
		try
		{

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
                        
                         Query qry = session.createQuery("From AlertMasterDto am where am.alertCode = '"+alertCode+"' ");
                         List listDocs = qry.list();
                         
                         for(Iterator it = listDocs.iterator(); it.hasNext();){
                           alertMaster = new  AlertMasterDto();
                           alertMaster = (AlertMasterDto)it.next();
                         }

			tx.commit();
                        HibernetFactory.closeSession();
                        
                        if(alertMaster!=null){
                            Map selectList = new TreeMap();
                            StringBuffer sbf = new StringBuffer();
                            
                            sbf.append(" select um.id.userId, um.firstName "); 
                            sbf.append(" From UserMasterDto um where um.id.issuerId = 'ASP' ");                                                
                            sbf.append(" and um.id.userId ");
                            sbf.append(" in (select aau.adminUserId from AlertAdminUserDto aau ");
                            sbf.append(" where aau.alertCode = '"+alertMaster.getAlertCode()+"'  ) ");
                            
                           
                            Collection objSearch = getList(sbf.toString());
                            
                            for(Iterator it = objSearch.iterator();it.hasNext();)
                            {
                                    CommonDataBean objCommonDB = (CommonDataBean)it.next();
                                    selectList.put(objCommonDB.getColumn1(),objCommonDB.getColumn2());
                            }
                            System.out.println(selectList.size());
                            alertMaster.setSelectedList(selectList);
                            
                        }

		}catch (Exception e)
		{
			
			System.out.println("Error in getAlertMasterForm method"+ e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
									 "Error: while retrieving the getAlertMasterForm" + e);
		}
		finally
		{
			HibernetFactory.closeSession();
		}
		return alertMaster;

	}

        public boolean add(AlertMasterDto objDto)
            throws TPlusException{

		boolean bolExecute=false;
                Transaction tx =null;
            try{

                Session session = HibernetFactory.currentSession();

                tx = session.beginTransaction();
                System.out.println("Saving........");                   
                session.save(objDto);
                System.out.println("AlertMasterDto object persisted to the database.");
                tx.commit();            
                bolExecute=true;
                
              }catch(Exception ex)
              {
                if (tx != null) 
                {
                    tx.rollback();
                }
                throw new TPlusException(TPlusCodes.APPLICATION_ERROR,"Error: while retrieving Info"+ex.getMessage());
              }finally
              {
                    HibernetFactory.closeSession();
              }

            return 	bolExecute;

        }
        
        public boolean update(AlertMasterDto objDto)throws TPlusException
	{
		boolean bolExecute = false;
		Transaction tx = null;
		try
		{
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			session.update(objDto);			
			tx.commit();
			System.out.println("AlertMasterDto object updated to the database.");
			bolExecute = true;
		}

		catch (Exception e) 
                {
			if (tx != null) 
                        {
                            tx.rollback();
			}
                    System.out.println("Error while updating AlertMasterForm data "+ e);
                    throw new TPlusException(TPlusCodes.APPLICATION_ERROR,"Error: while updating AlertMasterForm data" + e);

		} finally
		{
			HibernetFactory.closeSession();
		}

		return bolExecute;
	}


        public boolean delete(AlertMasterDto objDto)
            throws TPlusException {
                
              	boolean bolExecute=false;
                Transaction tx = null;
                try{
                    System.out.println("AlertMasterDto object deleted from the database.");
                    Session session = HibernetFactory.currentSession();

                    tx = session.beginTransaction();

                    session.delete(objDto);
                    tx.commit();
                
                    bolExecute=true;
                    System.out.println("AlertMasterDto object deleted from the database.");
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
            
        public Map getUserList(String alertCode)throws TPlusException {

	        Map userList = new TreeMap();
	        try{

                    StringBuffer sbf = new StringBuffer();
                    
                    sbf.append(" select um.id.userId, um.firstName "); 
                    sbf.append(" From UserMasterDto um where um.id.issuerId = 'ASP' ");
                    
                    if(alertCode!=null){
                    sbf.append(" and um.id.userId ");
                    sbf.append(" not in (select aau.adminUserId from AlertAdminUserDto aau ");
                    sbf.append(" where aau.alertCode = '"+alertCode+"'  ) ");
                    }
                    	            
		    
                    Collection objSearch = getList(sbf.toString());
                    
                    for(Iterator it = objSearch.iterator(); it.hasNext();){
                        CommonDataBean objBean = (CommonDataBean)it.next();
                        userList.put(objBean.getColumn1(), objBean.getColumn2());
                    }
                    		    
                    System.out.println("UserListSize from Impl===>"+userList.size());
                    
		   
		  }catch (Exception e){
		   System.out.println("Error in getUserList method"+e);
		   throw new TPlusException(TPlusCodes.APPLICATION_ERROR,"Error: while retrieving UserListData Info"+e.getMessage());
		  }
	          finally
		  {
		     HibernetFactory.closeSession();
		  }
	     return userList;
	}


public static void main(String s[])
{

    try
    {
          AlertMasterDto dto = new AlertMasterDto();
         
         AlertMasterDAOImpl impl = new AlertMasterDAOImpl();
         //impl.get(dto,0);
         //ArrayList arrl = new ArrayList();
         //impl.getUserList();
    }
    catch(Exception e){System.out.println(e);}
}

}


