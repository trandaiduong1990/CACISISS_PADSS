package org.transinfo.cacis.dataacess.daoimpl.oracle.riskmanagement;

import java.util.ArrayList;
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
import org.transinfo.cacis.dataacess.dao.riskmanagement.RiskTranxActionDAO;
import org.transinfo.cacis.dataacess.daoimpl.oracle.BaseDAOImpl;
import org.transinfo.cacis.dto.riskmanagement.RiskMasterDto;
import org.transinfo.cacis.dto.riskmanagement.RiskTranxActionDto;


public class RiskTranxActionDAOImpl extends BaseDAOImpl implements RiskTranxActionDAO {


    	public Collection search(RiskTranxActionDto objSearchDto, int pageNo)
			throws TPlusException
	{


		Collection objSearchCollection = new ArrayList();
		StringBuffer sbf = new StringBuffer();

		try {
                     
                        sbf.append("select rta.riskId, rm.description, cm.codeDesc, rta.smsAlert, ");                        
                        sbf.append(" rta.status, to_char(rta.updatedDate,'dd-MM-yyyy') ");                                                                        
                        sbf.append(" FROM RiskTranxActionDto rta, CodeMasterDto cm, RiskMasterDto rm ");                       
                        sbf.append(" where rta.riskId = rm.riskId");
                        sbf.append(" and rta.action = cm.id.codeId");                        
                        sbf.append(" and cm.id.groupId = 'RISKACTION' ");
                        sbf.append(" and rta.status in('A','I')");
                        
                                               			                                                                     
			objSearchCollection = getSearchList(sbf.toString(),pageNo);

                        System.out.println("Size===> "+objSearchCollection.size());

		} catch (Exception e) {
			System.out.println("Error while retrieving the RiskTranxAction Search Info" + e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
									 "Error: while retrieving the RiskTranxAction Search Info" + e);
		} finally {
			HibernetFactory.closeSession();
		}
		return objSearchCollection;

	}
        

        public RiskTranxActionDto getRiskActionForm(String riskId) throws TPlusException{
		RiskTranxActionDto objRiskAction = null;
		Transaction tx = null;
		try
		{

		    Session session = HibernetFactory.currentSession();
		    tx = session.beginTransaction();
			
                    Query qry = session.createQuery("From RiskTranxActionDto rta where rta.riskId = '"+riskId+"' ");
		    List listDocs = qry.list();

		    for(Iterator it = listDocs.iterator();it.hasNext();)
		    {
                        objRiskAction = new RiskTranxActionDto();
                        objRiskAction = (RiskTranxActionDto)it.next();
                    }

			tx.commit();
			
		}catch (Exception e)
		{
                    if (tx != null)
                    {
                        tx.rollback();
                    }			
                    System.out.println("Error in getRiskActionForm method"+ e);
		    throw new TPlusException(TPlusCodes.APPLICATION_ERROR,"Error: while retrieving the getRiskActionForm" + e);
		}
		finally
		{
			HibernetFactory.closeSession();
		}
		return objRiskAction;

	}
        

        public boolean add(RiskTranxActionDto objDto)
            throws TPlusException{

	   boolean bolExecute=false;
           Transaction tx = null;
        try{

            Session session = HibernetFactory.currentSession();
	    tx = session.beginTransaction();

            System.out.println("Saving........");
            session.save(objDto);
            System.out.println("RiskTranxActionDto object persisted to the database.");
            tx.commit();            
            bolExecute=true;

          }catch (Exception e) {

	       if (tx != null)
	       {
		  tx.rollback();
	       }
		System.out.println("Error while adding RiskTranxActionForm data "+ e);
		throw new TPlusException(TPlusCodes.APPLICATION_ERROR, "Error: while adding RiskTranxActionForm data" + e);
	  } finally
	  {
	      HibernetFactory.closeSession();
	  }
            return 	bolExecute;
        }


        public boolean update(RiskTranxActionDto objDto)throws TPlusException
	{
		boolean bolExecute = false;
		Transaction tx = null;
		try
		{
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();                        
                        System.out.println("Updating...");
			session.update(objDto);			
			tx.commit();
			System.out.println("RiskTranxActionDto object updated to the database.");
			bolExecute = true;
		}

		catch (Exception e) 
                {

			if (tx != null)
			{
				tx.rollback();
			}
                    System.out.println("Error while updating RiskTranxActionForm data "+ e);
                    throw new TPlusException(TPlusCodes.APPLICATION_ERROR,"Error: while updating RiskTranxActionForm data" + e);

		} finally
		{
			HibernetFactory.closeSession();
		}

		return bolExecute;
	}


          
        public Map getRiskId()throws TPlusException{

	        Map riskIds = new HashMap();
	        try{
		    Session session = HibernetFactory.currentSession();
		    Transaction tx =session.beginTransaction();
	            Query qry = session.createQuery("From RiskMasterDto rm ");
		    List listDocs = qry.list();
                    System.out.println("Class in Status ===>"+listDocs.get(0).getClass().getName());
		    for(Iterator it = listDocs.iterator();it.hasNext();){
                        RiskMasterDto objRiskMaster = new RiskMasterDto();
                        objRiskMaster = (RiskMasterDto)it.next();
                        riskIds.put(objRiskMaster.getRiskId(), objRiskMaster.getDescription());
		    }
		   tx.commit();

		  }catch (Exception e){
		   System.out.println("Error in getRiskId method"+e);
		   throw new TPlusException(TPlusCodes.APPLICATION_ERROR,"Error: while retrieving getRiskId Info"+e.getMessage());
		  }
	          finally
		  {
		     HibernetFactory.closeSession();
		  }
	     return riskIds;
	}
        

}


