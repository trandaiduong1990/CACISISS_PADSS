package org.transinfo.cacis.dataacess.daoimpl.oracle.riskmanagement;

import java.util.ArrayList;
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
import org.transinfo.cacis.dataacess.dao.riskmanagement.RiskTranxSaleCashDAO;
import org.transinfo.cacis.dataacess.daoimpl.oracle.BaseDAOImpl;
import org.transinfo.cacis.dto.riskmanagement.RiskTranxSaleCashDto;


public class RiskTranxCashSalesDAOImpl extends BaseDAOImpl implements RiskTranxSaleCashDAO {


    	public Collection search(RiskTranxSaleCashDto objSearchDto, int pageNo)
			throws TPlusException
	{

		Collection objSearchCollection = new ArrayList();
		StringBuffer sbf = new StringBuffer();

		try {

			sbf.append("select rts.id, rts.tranxNo, to_char(rts.updatedDate,'dd-MM-yyyy') ");
			sbf.append(" FROM  RiskTranxSaleCashDto rts ");


                        if (objSearchDto.getIssuerId()!= null && !objSearchDto.getIssuerId().equals(""))
			{
				sbf.append("where rts.issuerId = '"+ objSearchDto.getIssuerId()+"' ");
			}
                        
                        if (objSearchDto.getTranxCode()!= null && !objSearchDto.getTranxCode().equals(""))
			{
				sbf.append("and rts.tranxCode = '"+ objSearchDto.getTranxCode()+"' order by rts.tranxNo ");
			}


			objSearchCollection = getSearchList(sbf.toString(),pageNo);

                        System.out.println("Size===> "+objSearchCollection.size());

		} catch (Exception e) {
			System.out.println("Error while retrieving the RiskTranxSaleCash Search Info" + e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
									 "Error: while retrieving the RiskTranxSaleCash Search Info" + e);
		} finally {
			HibernetFactory.closeSession();
		}
		return objSearchCollection;

	}
        

        public RiskTranxSaleCashDto getRiskTranxForm(int id) throws TPlusException {
		RiskTranxSaleCashDto objRiskTranx = null;
		Transaction tx = null;
		try
		{

		    Session session = HibernetFactory.currentSession();
		    tx = session.beginTransaction();
			//objRiskTranx = (RiskTranxSaleCashDto) session.get(RiskTranxSaleCashDto.class, "111");

                    Query qry = session.createQuery("From RiskTranxSaleCashDto rts where rts.id = "+id+" ");
		    List listDocs = qry.list();

		    for(Iterator it = listDocs.iterator();it.hasNext();)
		    {
                        objRiskTranx = new RiskTranxSaleCashDto();
		       objRiskTranx = (RiskTranxSaleCashDto)it.next();
                    }

			tx.commit();
			HibernetFactory.closeSession();

			if(objRiskTranx !=null)
			{
				Map selectList = new TreeMap();
				StringBuffer sbf = new StringBuffer();

				sbf.append("select m.merchantId,m.merchantType ");
				sbf.append(" FROM MCCDto m ");
				sbf.append(" where m.merchantId ");
				sbf.append(" in( select rts.mcc from RiskTranxSaleCashMccsDto rts where rts.id="+objRiskTranx.getId()+")");

				
                                Collection objSearch = getList(sbf.toString());
                                
				for(Iterator it = objSearch.iterator();it.hasNext();)
				{
					CommonDataBean objCommonDB = (CommonDataBean)it.next();
					selectList.put(objCommonDB.getColumn1(),objCommonDB.getColumn2());
				}
				System.out.println(selectList.size());
				objRiskTranx.setSelectedMcc(selectList);

			}


		}catch (Exception e)
		{
			/*if (tx != null)
			{
				tx.rollback();
			}*/

			System.out.println("Error in getRiskTranxForm method"+ e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
									 "Error: while retrieving the getRiskTranxForm" + e);
		}
		finally
		{
			HibernetFactory.closeSession();
		}
		return objRiskTranx;

	}

        public boolean add(RiskTranxSaleCashDto objDto)
            throws TPlusException{

	   boolean bolExecute=false;
           Transaction tx = null;
        try{

            Session session = HibernetFactory.currentSession();
	    tx = session.beginTransaction();

            System.out.println("Saving........");
            session.save(objDto);
            System.out.println("RiskTranxSaleCashDto object persisted to the database.");
            tx.commit();           
            bolExecute=true;

          }catch (Exception e) {

	       if (tx != null)
	       {
		  tx.rollback();
	       }
		System.out.println("Error while adding RiskTranxSaleCashForm data "+ e);
		throw new TPlusException(TPlusCodes.APPLICATION_ERROR, "Error: while adding RiskTranxSaleCashForm data" + e);
	  } finally
	  {
	      HibernetFactory.closeSession();
	  }
            return 	bolExecute;
        }


        public boolean update(RiskTranxSaleCashDto objDto)throws TPlusException
	{
		boolean bolExecute = false;
		Transaction tx = null;
		try
		{
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
                        System.out.println("Id in Impl Update==>"+objDto.getId());
                        System.out.println("Updating...");
			session.update(objDto);			
			tx.commit();
			System.out.println("RiskTranxSaleCashDto object updated to the database.");
			bolExecute = true;
		}

		catch (Exception e) {

			if (tx != null)
			{
				tx.rollback();
			}
			System.out.println("Error while updating RiskTranxSaleCashForm data "+ e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
									  "Error: while updating RiskTranxSaleCashForm data" + e);

		} finally
		{
			HibernetFactory.closeSession();
		}

		return bolExecute;
	}


        public Map getMcc(String issuerId,String riskId)throws TPlusException {

	        Map mccList = new TreeMap();
	        try
	        {

					StringBuffer sbf = new StringBuffer();

					sbf.append("select m.merchantId,m.merchantType ");
					sbf.append(" FROM MCCDto m where m.issuerId = '"+issuerId+"' ");

				if(riskId !=null && !riskId.equals(""))
				{
					sbf.append(" and m.merchantId ");
					sbf.append(" not in( select rts.mcc from RiskTranxSaleCashMccsDto rts where rts.id="+riskId+")");
				}
				
                                Collection objSearch = getList(sbf.toString());        

				for(Iterator it = objSearch.iterator();it.hasNext();)
				{

					CommonDataBean objCommonDB = (CommonDataBean)it.next();
					mccList.put(objCommonDB.getColumn1(),objCommonDB.getColumn2());
				}
					System.out.println("mccList size==>"+mccList.size());

		  }catch (Exception e){
		   System.out.println("Error in getMcc method"+e);
		   throw new TPlusException(TPlusCodes.APPLICATION_ERROR,"Error: while retrieving getMcc Info"+e.getMessage());
		  }
	     return mccList;
	}
        
        public Map getSelectedMcc(String issuerId,String riskId)throws TPlusException {

	        Map mccList = new TreeMap();
	        try
	        {

					StringBuffer sbf = new StringBuffer();

					sbf.append("select m.merchantId,m.merchantType ");
					sbf.append(" FROM MCCDto m where m.issuerId = '"+issuerId+"' ");

				if(riskId !=null && !riskId.equals(""))
				{
					sbf.append(" and m.merchantId ");
					sbf.append(" in( select rts.mcc from RiskTranxSaleCashMccsDto rts where rts.id="+riskId+")");
				}
				
				Collection objSearch = getList(sbf.toString());
                
				for(Iterator it = objSearch.iterator();it.hasNext();)
				{
					CommonDataBean objCommonDB = (CommonDataBean)it.next();
					mccList.put(objCommonDB.getColumn1(),objCommonDB.getColumn2());
				}
				System.out.println(mccList.size());

		  }catch (Exception e){
		   System.out.println("Error in getMcc method"+e);
		   throw new TPlusException(TPlusCodes.APPLICATION_ERROR,"Error: while retrieving getMcc Info"+e.getMessage());
		  }
	     return mccList;
	}


public static void main(String s[]) throws Exception
{

		RiskTranxCashSalesDAOImpl csd = new RiskTranxCashSalesDAOImpl();
		//csd.getMcc("Issuer1",null);
		csd.getRiskTranxForm(5);
		/*RiskTranxSaleCashDto rdto = new RiskTranxSaleCashDto();
		rdto.setIssuerId("Issuer1");
		Collection col = csd.search(rdto,0);
		ArrayList list =(ArrayList)col;
		System.out.println("Size="+col.size());
		RiskTranxSaleCashDto riskTranxDto = (RiskTranxSaleCashDto)list.get(0);
		System.out.println(riskTranxDto.getListTranxMcc().size());*/


}



}


