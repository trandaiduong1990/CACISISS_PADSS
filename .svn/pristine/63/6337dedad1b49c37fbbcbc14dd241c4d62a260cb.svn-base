package org.transinfo.cacis.dataacess.daoimpl.oracle.riskmanagement;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.transinfo.cacis.TPlusCodes;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.HibernetDAO.HibernetFactory;
import org.transinfo.cacis.dataacess.dao.riskmanagement.RiskTranxPeriodDAO;
import org.transinfo.cacis.dataacess.daoimpl.oracle.BaseDAOImpl;
import org.transinfo.cacis.dto.riskmanagement.RiskTranxPeriodDto;


public class RiskTranxPeriodDAOImpl extends BaseDAOImpl implements RiskTranxPeriodDAO {


    	public Collection search(RiskTranxPeriodDto objSearchDto, int pageNo)
			throws TPlusException
	{


		Collection objSearchCollection = null;
		StringBuffer sbf = new StringBuffer();
		try {

			sbf.append("select rtp.id.period, rtp.tranxNo, to_char(rtp.updatedDate,'dd-MM-yyyy') ");
			sbf.append(" FROM  RiskTranxPeriodDto rtp ");

                        if (objSearchDto.id.getIssuerId()!= null && !objSearchDto.id.getIssuerId().equals(""))
			{
                            sbf.append("where rtp.id.issuerId = '"+ objSearchDto.id.getIssuerId()+"' ");
			}
                        
                        sbf.append(" order by rtp.tranxNo ");
                        
			objSearchCollection = getSearchList(sbf.toString(),pageNo);

                        System.out.println("Size===> "+objSearchCollection.size());

		} catch (Exception e) {
			System.out.println("Error while retrieving the RiskTranxPeriod Search Info" + e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
									 "Error: while retrieving the RiskTranxPeriod Search Info" + e);
		} finally {
			HibernetFactory.closeSession();
		}
		return objSearchCollection;
	}

        public RiskTranxPeriodDto getRiskTranxPeriodForm(String issuerId, int period) throws TPlusException {
		RiskTranxPeriodDto objRiskPeriod = null;
		Transaction tx = null;
		try
		{

		    Session session = HibernetFactory.currentSession();
		    tx = session.beginTransaction();
	                                                        
                    Query qry = session.createQuery("From RiskTranxPeriodDto rtp where rtp.id.period = "+period+" and rtp.id.issuerId = '"+issuerId+"' ");
		    List listDocs = qry.list();

		   for(Iterator it = listDocs.iterator();it.hasNext();){
                    objRiskPeriod = new RiskTranxPeriodDto();
		    objRiskPeriod = (RiskTranxPeriodDto)it.next();
                    }
                        
		tx.commit();

		}catch (Exception e)
		{
                    if (tx != null)
                    {
                        tx.rollback();
                    }
                    System.out.println("Error in getRiskTranxPeriodForm method"+ e);
                    throw new TPlusException(TPlusCodes.APPLICATION_ERROR,"Error: while retrieving the getRiskTranxPeriodForm" + e);
		}
		finally
		{
			HibernetFactory.closeSession();
		}
		return objRiskPeriod;
	}
        

        public boolean add(RiskTranxPeriodDto objDto)
            throws TPlusException{

	   boolean bolExecute=false;
           Transaction tx = null;
        try{

            Session session = HibernetFactory.currentSession();
	    tx = session.beginTransaction();
            
            System.out.println("Saving........");            
            session.save(objDto);
            System.out.println("RiskTranxPeriodDto object persisted to the database.");
            tx.commit();            
            bolExecute=true;

          }catch (Exception e) {

	       if (tx != null)
	       {
		  tx.rollback();
	       }
		System.out.println("Error while adding RiskTranxPeriodForm data "+ e);
		throw new TPlusException(TPlusCodes.APPLICATION_ERROR, "Error: while adding RiskTranxPeriodForm data" + e);
	  } finally
	  {
	      HibernetFactory.closeSession();
	  }
            return 	bolExecute;
        }

        
        public boolean update(RiskTranxPeriodDto objDto)throws TPlusException
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
			System.out.println("RiskTranxPeriodDto object updated to the database.");
			bolExecute = true;
		}

		catch (Exception e) {

			if (tx != null)
			{
				tx.rollback();
			}
			System.out.println("Error while updating RiskTranxPeriodForm data "+ e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
									  "Error: while updating RiskTranxPeriodForm data" + e);

		} finally
		{
			HibernetFactory.closeSession();
		}

		return bolExecute;
	}

      
         
}


