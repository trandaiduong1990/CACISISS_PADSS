package org.transinfo.cacis.dataacess.daoimpl.oracle.authorization;

import java.util.Collection;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.transinfo.cacis.TPlusCodes;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.HibernetDAO.HibernetFactory;
import org.transinfo.cacis.dataacess.dao.authorization.BlackListCardDAO;
import org.transinfo.cacis.dataacess.daoimpl.oracle.BaseDAOImpl;
import org.transinfo.cacis.dto.authorization.BlackListCardDto;


public class BlackListCardDAOImpl extends BaseDAOImpl implements BlackListCardDAO {

   
    	public Collection search(BlackListCardDto objSearchDto, int pageNo)
			throws TPlusException
	{

		Collection objSearchCollection = null;
		StringBuffer sbf = new StringBuffer();
		try {

			sbf.append("select bc.cardNumber,bc.cardStatusId,to_char(bc.updatedDate,'dd-MM-yyyy') ");
			sbf.append(" FROM  BlackListCardDto bc ");
	                        
                        if (objSearchDto.getIssuerId()!=null && !objSearchDto.getIssuerId().equals(""))
                        {
                            sbf.append(" where bc.issuerId = '"+ objSearchDto.getIssuerId()+"' ");
                        }
                        
                        if (objSearchDto.getCardNumber()>0)
			{
				sbf.append(" and bc.cardNumber = "+ objSearchDto.getCardNumber()+ " ");
			}

			objSearchCollection = getSearchList(sbf.toString(),pageNo);

                        System.out.println("Size===> "+objSearchCollection.size());
                     
		} catch (Exception e) {
			System.out.println("Error while retrieving the BlackListCard Search Info" + e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
									 "Error: while retrieving the BlackListCard Search Info" + e);
		} finally {
			HibernetFactory.closeSession();
		}
		return objSearchCollection;
	}


        public boolean add(BlackListCardDto objBlackListCardDto)
        throws TPlusException{

            boolean bolExecute=false;
            Transaction tx = null;
            try{

                Session session = HibernetFactory.currentSession();

                tx = session.beginTransaction();

                session.save(objBlackListCardDto);
                System.out.println("BlackListCardDto object persisted to the database.");
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

        public boolean delete(BlackListCardDto objBlackListCardDto)
        throws TPlusException {
              	boolean bolExecute=false;
                Transaction tx = null;
                try{
                    System.out.println("BlackListCardDto object deleted from the database.");
                    Session session = HibernetFactory.currentSession();

                    tx = session.beginTransaction();

                    session.delete(objBlackListCardDto);
                    tx.commit();           
                    bolExecute=true;
                    System.out.println("BlackListCardDto object deleted from the database.");
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



}


