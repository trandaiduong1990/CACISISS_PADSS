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
import org.transinfo.cacis.dataacess.dao.useraccess.UserSetupDAO;
import org.transinfo.cacis.dataacess.daoimpl.oracle.BaseDAOImpl;
import org.transinfo.cacis.dto.useraccess.RoleMasterDto;
import org.transinfo.cacis.dto.useraccess.UserMasterDto;
import org.transinfo.cacis.dto.useraccess.UserSetupSearchDto;


public class UserSetupDAOImpl extends BaseDAOImpl implements UserSetupDAO {


    	public Collection search(UserSetupSearchDto objSearchDto, int pageNo)
			throws TPlusException
	{


		Collection objSearchCollection = null;
		StringBuffer sbf = new StringBuffer();
		try {

			sbf.append("select um.id.userId, um.firstName, um.roleId, um.status,  ");
			sbf.append(" to_char(um.lastUpdatedDate,'dd-MM-yyyy'), um.userType FROM  UserMasterDto um ");


			if (objSearchDto.getIssuerId()!= null && !objSearchDto.getIssuerId().equals(""))
			{
				sbf.append("where um.id.issuerId = '"+ objSearchDto.getIssuerId()+ "' ");
			}

			if (objSearchDto.getUserType()!= null && !objSearchDto.getUserType().equals(""))
			{
                                sbf.append(" and um.userType = '"+ objSearchDto.getUserType()+ "' ");
			}
                        
			if (objSearchDto.getSearchUserId()!= null && !objSearchDto.getSearchUserId().equals(""))
			{
                                sbf.append(" and um.id.userId like '%"+ objSearchDto.getSearchUserId()+ "%' ");
			}


                        if (objSearchDto.getSearchRoleId()!= null && !objSearchDto.getSearchRoleId().equals(""))
			{

				sbf.append(" and um.roleId = '"+ objSearchDto.getSearchRoleId()+"' ");

			}

                        if (objSearchDto.getSearchStatus()!= null && !objSearchDto.getSearchStatus().equals(""))
			{

				sbf.append(" and um.status = '"+ objSearchDto.getSearchStatus()+"' ");

			}


			objSearchCollection = getSearchList(sbf.toString(),pageNo);

                        System.out.println("Size===> "+objSearchCollection.size());

		} catch (Exception e) {
			System.out.println("Error while retrieving the UserSetup Search Info" + e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
									 "Error: while retrieving the UserSetup Search Info" + e);
		} finally {
			HibernetFactory.closeSession();
		}
		return objSearchCollection;
	}
        
        public String getIssuerName(String issuerId)throws TPlusException
        {		
                String issuerName;               
		Transaction tx = null;
		try
		{

		      Session session = HibernetFactory.currentSession();
		      tx = session.beginTransaction();
                        
                      issuerName = (String)(session.createQuery("select issuerName From IssuerDto  where issuerId = '"+issuerId+"' ").iterate()).next();		      	
                      System.out.println("issuerName==>"+issuerName);
                      tx.commit();
                      
		}catch (Exception e)
		{
			if (tx != null)
			{
				tx.rollback();
			}

			System.out.println("Error in getIssuerName method"+ e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
									 "Error: while retrieving the getIssuerName" + e);
		}
		finally
		{
			HibernetFactory.closeSession();
		}
		return issuerName;
        }
        
        public String getUserName(String issuerId, String userId)throws TPlusException
        {		
               // UserMasterDto userMaster = null;
                String userName;               
		Transaction tx = null;
		try
		{

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
                        userName = (String)(session.createQuery("select um.firstName From UserMasterDto um where um.id.issuerId = '"+issuerId+"' and um.id.userId = '"+userId+"' ").iterate()).next();
                      //Query userQry = session.createQuery("select um.firstName From UserMasterDto um where um.id.issuerId = '"+issuerId+"' and um.id.userId = '"+userId+"' ").iterate();
                     //userName = session.createQuery("select um.firstName From UserMasterDto um where um.id.issuerId = '"+issuerId+"' and um.id.userId = '"+userId+"' ").list.get(0);
		   /*  List userList = userQry.list();
		      for(Iterator it = userList.iterator();it.hasNext();){                                                
                          userName = (String) it.next();
                      System.out.println("UserName==>"+userName);                          
                         //userName = it.next().toString();  	                         
		      }*/
		      tx.commit();                      
                        
		}catch (Exception e)
		{
			if (tx != null)
			{
				tx.rollback();
			}

			System.out.println("Error in getUserName method"+ e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
									 "Error: while retrieving the getUserName" + e);
		}
		finally
		{
			HibernetFactory.closeSession();
		}
		return userName;
        }        

        
        public UserMasterDto getUserMasterForm(String issuerId, String userId) throws TPlusException {
		UserMasterDto userMaster = null;
		Transaction tx = null;
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

			System.out.println("Error in getUserMasterForm method"+ e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
									 "Error: while retrieving the getUserMasterForm" + e);
		}
		finally
		{
			HibernetFactory.closeSession();
		}
		return userMaster;

	}

        public boolean add(UserMasterDto objDto)
            throws TPlusException{

		boolean bolExecute=false;
                Transaction tx = null;
            try{

                Session session = HibernetFactory.currentSession();

                tx = session.beginTransaction();
                System.out.println("Saving........");
                System.out.println(objDto.getId().getIssuerId()+"  "+objDto.getId().getUserId());
                session.save(objDto);
                System.out.println("UserMasterDto object persisted to the database.");
                tx.commit();            
                bolExecute=true;

            }catch(Exception ex)
            {
                if (tx != null)
                {
                    tx.rollback();
                }
                System.out.println("In ADD Exception="+ex);
                throw new TPlusException(TPlusCodes.APPLICATION_ERROR,"Error: while retrieving Info"+ex);
            }finally
            {
                HibernetFactory.closeSession();
            }

            return  bolExecute;
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
			tx.commit();
			System.out.println("UserMasterDto object updated to the database.");
			bolExecute = true;
		}

		catch (Exception e) 
                {
			if (tx != null)
			{
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


        public boolean delete(UserMasterDto objDto)
            throws TPlusException {
              	boolean bolExecute=false;
                Transaction tx = null;
            try{
                System.out.println("UserMasterDto object deleted from the database.");
                Session session = HibernetFactory.currentSession();

                tx = session.beginTransaction();

                session.delete(objDto);
                tx.commit();               
		bolExecute=true;
		System.out.println("UserMasterDto object deleted from the database.");
                }catch(Exception ex)
                {
                    if (tx != null)
		    {
			tx.rollback();
		    }
                    throw new TPlusException(TPlusCodes.APPLICATION_ERROR,"Error: while deleting"+ex);
                }finally
		{
                    HibernetFactory.closeSession();
		}

                return 	bolExecute;
            }

            
        public Map getRoleId(String issuerId,String userType)throws TPlusException {

	        Map roleList = new HashMap();
                Transaction tx = null;
	        try{
		    Session session = HibernetFactory.currentSession();
		    tx =session.beginTransaction();
		    StringBuffer sbf = new StringBuffer();
                   // System.out.println("userType in impl===>"+userType);
            sbf.append("From RoleMasterDto rm where rm.id.issuerId='"+issuerId+"' and rm.status='A' ");
            if(userType!=null && !userType.equals("")) {
            	sbf.append("and rm.userType = '"+userType+"' ");
            }
            Query qry = session.createQuery(sbf.toString());
		    List listDocs = qry.list();

		    for(Iterator it = listDocs.iterator();it.hasNext();){
                       RoleMasterDto objRoleDto = new RoleMasterDto();
		       objRoleDto = (RoleMasterDto)it.next();
		       roleList.put(objRoleDto.getId().getRoleId(), objRoleDto.getRoleDesc());
		    }
                    System.out.println("roleList==>"+roleList.size());
		   tx.commit();

		  }catch (Exception e)
                  {
                    if (tx != null)
		    {
			tx.rollback();
		    }		   
                    System.out.println("Error in getRoleId method"+e);
                    throw new TPlusException(TPlusCodes.APPLICATION_ERROR,"Error: while retrieving getRoleId Info"+e.getMessage());
		  }
	          finally
		  {
		     HibernetFactory.closeSession();
		  }
	     return roleList;
	}
        
}


