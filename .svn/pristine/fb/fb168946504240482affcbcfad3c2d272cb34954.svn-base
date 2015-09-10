package org.transinfo.cacis.dataacess.daoimpl.oracle.authorization;

import java.util.ArrayList;
import java.util.Collection;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.transinfo.cacis.TPlusCodes;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.HibernetDAO.HibernetFactory;
import org.transinfo.cacis.dataacess.dao.authorization.ServerParamDAO;
import org.transinfo.cacis.dataacess.daoimpl.oracle.BaseDAOImpl;
import org.transinfo.cacis.dto.authorization.ServerParamFormDto;
import org.transinfo.cacis.dto.authorization.ServerParamSearchDto;


public class ServerParamDAOImpl extends BaseDAOImpl implements ServerParamDAO {
	
          
	public Collection getServerParam(ServerParamSearchDto objDto, int pageNo) throws TPlusException {

		   Collection objSearchCollection = null;
                   StringBuffer sbf = new StringBuffer();
	    try{
                   Session	session = HibernetFactory.currentSession();                                 
                   sbf.append("select sp.paramName,sp.paramValue ");                   
		   sbf.append(" FROM ServerParamDto sp where sp.issuerId='ASP' ");
                   sbf.append(" and sp.paramType = 'SERV_PARAM' ");
                   
            /*       if (objDto.getIssuerId() != null && !objDto.getIssuerId().equals(""))
		   {
			sbf.append("where sp.issuerId like '%"+ objDto.getIssuerId()+ "%' ");
			
	           }
                           
                   if (objDto.getParamType() != null	&& !objDto.getParamType().equals(""))
		   {                       
                        sbf.append("and sp.paramType like '%"	+ objDto.getParamType() + "%'");			
		   } */
                   		  
                  System.out.println("Query===> "+sbf.toString());
                  System.out.println("Query process.........");		                  
                  objSearchCollection = getSearchList(sbf.toString(), pageNo);
                
		}catch (Exception e)
		{
			System.out.println("Error"+e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,"Error: while retrieving the TagFieldSearch Info"+e.getMessage());
		}
		finally
		{
			HibernetFactory.closeSession();
	 	}
	 return objSearchCollection;
 }
 

  public boolean update(ServerParamFormDto objDto)throws TPlusException
  {
                       
			Transaction tx = null;
			boolean bolExecute=false;
                        StringBuffer sbf = new StringBuffer();
                        ArrayList paramNameList = new ArrayList();
                     try
			{
                                paramNameList.add("MAIL_SERVER_ADDRESS");
                                paramNameList.add("MAIL_SERVER_PORT");
                                paramNameList.add("FROM_NAME");
                                paramNameList.add("FROM_MAIL");
                                paramNameList.add("VOICE_PORT");
                                paramNameList.add("SERVER_PORT");
                                paramNameList.add("DATA_TIMEOUT");
                                paramNameList.add("NO_WORKER_THREADS");
                                paramNameList.add("DEBUG_LEVEL");
                                
				Session session =HibernetFactory.currentSession();
                                
                                sbf.append("update ServerParamDto sp set ");
                                for(int i=0; i<paramNameList.size(); i++){
                                    if(paramNameList.get(i).equals("MAIL_SERVER_ADDRESS")){
                                    sbf.append("sp.paramName='MAIL_SERVER_ADDRESS' ");
                                    sbf.append("sp.paramValue='"+objDto.getMailServerAddress()+"' ");    
                                    }
                                    if(paramNameList.get(i).equals("MAIL_SERVER_PORT")){
                                    sbf.append("sp.paramName='MAIL_SERVER_PORT' ");
                                    sbf.append("sp.paramValue='"+objDto.getMailServerPort()+"' ");    
                                    }
                                    if(paramNameList.get(i).equals("FROM_NAME")){
                                    sbf.append("sp.paramName='FROM_NAME' ");
                                    sbf.append("sp.paramValue='"+objDto.getFromName()+"' ");    
                                    }
                                    if(paramNameList.get(i).equals("FROM_MAIL")){
                                    sbf.append("sp.paramName='FROM_MAIL' ");
                                    sbf.append("sp.paramValue='"+objDto.getFromMail()+"' ");    
                                    }
                                    if(paramNameList.get(i).equals("VOICE_PORT")){
                                    sbf.append("sp.paramName='VOICE_PORT' ");
                                    sbf.append("sp.paramValue='"+objDto.getVoicePort()+"' ");    
                                    }
                                    if(paramNameList.get(i).equals("SERVER_PORT")){
                                    sbf.append("sp.paramName='SERVER_PORT' ");
                                    sbf.append("sp.paramValue='"+objDto.getServerPort()+"' ");    
                                    }
                                    if(paramNameList.get(i).equals("DATA_TIMEOUT")){
                                    sbf.append("sp.paramName='DATA_TIMEOUT' ");
                                    sbf.append("sp.paramValue='"+objDto.getDataTimeout()+"' ");    
                                    }
                                    if(paramNameList.get(i).equals("NO_WORKER_THREADS")){
                                    sbf.append("sp.paramName='NO_WORKER_THREADS' ");
                                    sbf.append("sp.paramValue='"+objDto.getNoWorkerThread()+"' ");    
                                    }
                                    if(paramNameList.get(i).equals("DEBUG_LEVEL")){
                                    sbf.append("sp.paramName='DEBUG_LEVEL' ");
                                    sbf.append("sp.paramValue='"+objDto.getNoWorkerThread()+"' ");    
                                    }
                                }
                                sbf.append("where sp.issuerId = 'ASP' and sp.paramType = 'SERV_PARAM' ");
                                
                                Query qry = session.createQuery(sbf.toString());
                                qry.executeUpdate();
                                
				//tx =session.beginTransaction();
				//session.update(objDto);
				//session.flush();
				//tx.commit();
				System.out.println("After ServerParamDAOImpl updateMethod()");
				bolExecute=true;

			}
			catch (Exception e)
			{

				System.out.println("6" +e.getMessage());

				if(tx!=null)
				{
					tx.rollback();
				}
				//throw new TPlusException(TPlusCodes.APPLICATION_ERROR,"Error: while adding the TagFiled Info"+e.getMessage());
			}
			finally
			{
				HibernetFactory.closeSession();
	 		}

		return 	bolExecute;

	}

  
             
     public static void main(String s[])
    {

    try
    {
         ServerParamSearchDto objDto = new ServerParamSearchDto();
         
                //objDto.setIssuerId("ASP");
               

         ServerParamDAOImpl impl = new ServerParamDAOImpl();
         //impl.search(objDto,0);
         //impl.add(objDto);

    }
        catch(Exception e){System.out.println(e);}
    }
  
}


