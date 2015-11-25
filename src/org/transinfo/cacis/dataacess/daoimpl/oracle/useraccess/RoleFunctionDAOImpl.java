package org.transinfo.cacis.dataacess.daoimpl.oracle.useraccess;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
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
import org.transinfo.cacis.dataacess.dao.useraccess.RoleFunctionSetupDAO;
import org.transinfo.cacis.dataacess.daoimpl.oracle.BaseDAOImpl;
import org.transinfo.cacis.dto.useraccess.CodeMasterDto;
import org.transinfo.cacis.dto.useraccess.RoleMasterDto;


public class RoleFunctionDAOImpl extends BaseDAOImpl implements RoleFunctionSetupDAO {


    	public Collection search(RoleMasterDto objSearchDto, int pageNo)
		throws TPlusException
	{

		Collection objSearchCollection = null;
		StringBuffer sbf = new StringBuffer();
		try {

			System.out.println("UserTYPE="+objSearchDto.getUserType()+" issuerId "+objSearchDto.id.getIssuerId());

			sbf.append("select rm.id.roleId, rm.roleDesc,  rm.status, to_char(rm.updatedDate,'dd-MM-yyyy'), rm.userType ");
			sbf.append(" FROM  RoleMasterDto rm ");

			if (objSearchDto.id.getIssuerId()!= null && !objSearchDto.id.getIssuerId().equals(""))
			{
				sbf.append(" where rm.id.issuerId = '"+objSearchDto.id.getIssuerId()+"' ");
			}

			if (objSearchDto.getUserType()!= null && !objSearchDto.getUserType().equals(""))
			{
				sbf.append(" and rm.userType = '"+objSearchDto.getUserType()+"' ");
			}


            System.out.println("Query===>"+sbf.toString());
			objSearchCollection = getSearchList(sbf.toString(),pageNo);

            System.out.println("Size===> "+objSearchCollection.size());

		} catch (Exception e) {
			System.out.println("Error while retrieving the RoleMaster Search Info" + e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
									 "Error: while retrieving the RoleMaster Search Info" + e);
		} finally {
			HibernetFactory.closeSession();
		}
		return objSearchCollection;
	}


        public RoleMasterDto getRoleMasterForm(String issuerId, String roleId,String userType) throws TPlusException {
		RoleMasterDto roleMaster = null;
		Transaction tx = null;
		try
		{
                        System.out.println("Issuerid roleid"+issuerId+"   "+roleId);
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

                        Query qry = session.createQuery("From RoleMasterDto rm where rm.id.issuerId = '"+issuerId+"' and rm.id.roleId = '"+roleId+"' ");
                        List listDocs = qry.list();

                        for(Iterator it = listDocs.iterator(); it.hasNext();)
                        {
                            roleMaster = new RoleMasterDto();
                            roleMaster = (RoleMasterDto)it.next();
                        }

						tx.commit();
						HibernetFactory.closeSession();

						if(roleMaster!=null)
						{
							Map selectList = new TreeMap();
							StringBuffer sbf = new StringBuffer();

							sbf.append("select fm.id.functionId, fm.functionDesc From FunctionSetMasterDto fm, PermissionMatrixDto pm ");
							sbf.append(" where pm.id.userType = '"+userType+"' and pm.id.screenAccessible = fm.id.screenId ");
							sbf.append(" and fm.id.functionId ");
							sbf.append(" in( select rfs.id.functionId from RoleFunctionSetDto rfs  ");
							sbf.append(" where rfs.id.issuerId = '"+roleMaster.id.getIssuerId()+"' ");
							sbf.append(" and rfs.id.roleId = '"+roleMaster.id.getRoleId()+"' )");

							Collection objSearch = getList(sbf.toString());

						   for(Iterator it = objSearch.iterator(); it.hasNext();)
						   {
								CommonDataBean objCommonDB = (CommonDataBean)it.next();
								selectList.put(objCommonDB.getColumn1(),objCommonDB.getColumn2());
						   }

							System.out.println(selectList.size());
							roleMaster.setSelectedListSet(selectList);

                        }


		}catch (Exception e)
		{

			System.out.println("Error in getRoleMasterForm method"+ e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
									 "Error: while retrieving the getRoleMasterForm" + e);
		}
		finally
		{
			HibernetFactory.closeSession();
		}
		return roleMaster;

	}

        public boolean add(RoleMasterDto objDto)
            throws TPlusException{

		boolean bolExecute=false;
		org.hibernate.Transaction tx=null;
        try{

                Session session = HibernetFactory.currentSession();

            tx = session.beginTransaction();
            System.out.println("Saving........");
            session.save(objDto);
            System.out.println("RoleMasterDto object persisted to the database.");
            tx.commit();
            session.flush();
            session.close();
            bolExecute=true;

          }catch(Exception ex){
			if (tx != null) {
				tx.rollback();
			}
			System.out.println("Error while updating RoleMasterForm data "+ ex);
            throw new TPlusException(TPlusCodes.APPLICATION_ERROR,"Error: while retrieving Info"+ex.getMessage());
          } finally
		  {
			HibernetFactory.closeSession();
		  }

            return 	bolExecute;

        }

        public boolean update(RoleMasterDto objDto)throws TPlusException
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
			System.out.println("RoleMasterDto object updated to the database.");
			bolExecute = true;
		}

		catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.out.println("Error while updating RoleMasterForm data "+ e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
									  "Error: while updating RoleMasterForm data" + e);

		} finally
		{
			HibernetFactory.closeSession();
		}

		return bolExecute;
	}


        public Map getStatus()throws TPlusException {

	        Map codeList = new HashMap();
	        try{
		    Session session = HibernetFactory.currentSession();
		    Transaction tx =session.beginTransaction();
	            Query qry = session.createQuery("From CodeMasterDto cm where cm.id.groupId = 'CODE_AI' ");
		    List listDocs = qry.list();
                    System.out.println("Class in Status ===>"+listDocs.get(0).getClass().getName());
		    for(Iterator it = listDocs.iterator();it.hasNext();){
                    CodeMasterDto objCodeDto = new CodeMasterDto();
		    objCodeDto = (CodeMasterDto)it.next();
		    codeList.put(objCodeDto.id.getCodeId(), objCodeDto.getCodeDesc());

		    }
		   tx.commit();

		  }catch (Exception e){
		   System.out.println("Error in getStatus method"+e);
		   throw new TPlusException(TPlusCodes.APPLICATION_ERROR,"Error: while retrieving getStatus Info"+e.getMessage());
		  }
	          finally
		  {
		     HibernetFactory.closeSession();
		  }
	     return codeList;
	}


        public Map getFunctionList(String issuerId, String roleId,String userType)throws TPlusException {

	        Map functionList = new TreeMap();
                Collection objSearch = null;
                StringBuffer sbf = new StringBuffer();
                ArrayList arlst  = new ArrayList();
	        try{

                    sbf.append("select fm.id.functionId, fm.functionDesc From FunctionSetMasterDto fm, PermissionMatrixDto pm ");
                    sbf.append(" where pm.id.screenAccessible = fm.id.screenId ");
                    
                    if(userType != null && !userType.equals("")) {
                    	 sbf.append(" and pm.id.userType = '"+userType+"' ");
                    }
                    if(roleId !=null && !roleId.equals(""))
		    		{
						sbf.append(" and fm.id.functionId ");
						sbf.append(" not in( select rfs.id.functionId from RoleFunctionSetDto rfs ");
                                                sbf.append(" where rfs.id.issuerId ='"+issuerId+"' and rfs.id.roleId = '"+roleId+"' )");
		    		}


                    objSearch = getList(sbf.toString());

                    System.out.println("Size of functionlist===> "+objSearch.size());

                    for(Iterator it = objSearch.iterator(); it.hasNext();)
                    {
                    	CommonDataBean objBean = (CommonDataBean)it.next();
                    	functionList.put(objBean.getColumn1(), objBean.getColumn2());
                    }


		  }catch (Exception e){
		   System.out.println("Error in getFunctionList method "+e);
		   throw new TPlusException(TPlusCodes.APPLICATION_ERROR,"Error: while retrieving getFunctionList Info"+e.getMessage());
		  }
	          finally
		  {
		     HibernetFactory.closeSession();
		  }
	     return functionList;
	}



}


