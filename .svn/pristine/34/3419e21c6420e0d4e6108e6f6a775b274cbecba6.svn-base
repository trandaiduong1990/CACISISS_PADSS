package org.transinfo.cacis.dataacess.daoimpl.oracle.authorization;

import java.util.Collection;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.transinfo.cacis.TPlusCodes;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.HibernetDAO.HibernetFactory;
import org.transinfo.cacis.dataacess.dao.authorization.BlackListCardDAO;
import org.transinfo.cacis.dataacess.dao.authorization.BlockMerchantDAO;
import org.transinfo.cacis.dataacess.daoimpl.oracle.BaseDAOImpl;
import org.transinfo.cacis.dto.authorization.BlackListCardDto;
import org.transinfo.cacis.dto.authorization.BlockMerchantDto;


public class BlockMerchantDAOImpl extends BaseDAOImpl implements BlockMerchantDAO {

   
    	public Collection search(BlockMerchantDto objSearchDto, int pageNo)
			throws TPlusException
	{

		Collection objSearchCollection = null;
		StringBuffer sbf = new StringBuffer();
		try {

			sbf.append("select bm.merchantName,bm.searchMerchantId,bm.blockStatus,bm.reason,to_char(bm.lastUpdatedDate,'dd-MM-yyyy') ");
			sbf.append(" FROM  BlockMerchantDto bm ");
            
			
			//sbf.append("select bm.MERCHANT_NAME,bm.MERCHANTID,DECODE(BLOCKSTATUS,'B','BLOCKED','U','UNBLOCKED'),bm.REASON,to_char(bm.LAST_UPDATED_DATE,'dd-MM-yyyy') FROM BLACKLIST_MERCHANT bm");

	                        
                        if (objSearchDto.getIssuerId()!=null && !objSearchDto.getIssuerId().equals(""))
                        {
                            sbf.append(" where bm.issuerId = '"+ objSearchDto.getIssuerId()+"' ");
                            //sbf.append(" where bm.ISSUER_ID = '"+ objSearchDto.getIssuerId()+"' ");

                        }
                        
                        if (objSearchDto.getSearchMerchantId()>0)
			{
				sbf.append(" and bm.searchMerchantId = "+ objSearchDto.getSearchMerchantId()+ " ");
            				//sbf.append(" and bm.MERCHANTID = "+ objSearchDto.getSearchMerchantId()+ " ");

			}

			objSearchCollection = getSearchList(sbf.toString(),pageNo);
			

                        System.out.println("Size===> "+objSearchCollection.size());
                     
		} catch (Exception e) {
			System.out.println("Error while retrieving the BlackListCard Search Info in DAO impl" + e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
									 "Error: while retrieving the BlackListCard Search Info" + e);
		} finally {
			HibernetFactory.closeSession();
		}
		return objSearchCollection;
	}


        public boolean add(BlockMerchantDto objBlockMerchantDto)
        throws TPlusException{

            boolean bolExecute=false;
            Transaction tx = null;
            try{

                Session session = HibernetFactory.currentSession();
System.out.println("*******************DAO Impl **********************************");
System.out.println("SearchMerchantId : "+ objBlockMerchantDto.getSearchMerchantId());
System.out.println("BlockStatus : "+objBlockMerchantDto.getBlockStatus() );
System.out.println("MerchantName : "+objBlockMerchantDto.getMerchantName());
System.out.println("IssuerId : "+objBlockMerchantDto.getIssuerId());
System.out.println("Reason : "+objBlockMerchantDto.getReason());
System.out.println("LastUpdatedDate : "+objBlockMerchantDto.getLastUpdatedDate());
 Date  lastUpdatedDate = new Date();
 objBlockMerchantDto.setLastUpdatedDate(lastUpdatedDate);
 System.out.println(" After LastUpdatedDate : "+objBlockMerchantDto.getLastUpdatedDate());
System.out.println("LastUpdatedUserId : "+objBlockMerchantDto.getUserId());
System.out.println("*******************DAO Impl *************************************");

                tx = session.beginTransaction();

                session.save(objBlockMerchantDto);
                System.out.println("BlockMerchantDto object persisted to the database.");
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

        public boolean delete(BlockMerchantDto objBlockMerchantDto)
        throws TPlusException {
              	boolean bolExecute=false;
                Transaction tx = null;
                try{
                    System.out.println("BlockMerchantDto object deleted from the database.");
                    Session session = HibernetFactory.currentSession();
                    
                    System.out.println("*******************DAO Impl **********************************");
                    System.out.println("SearchMerchantId : "+ objBlockMerchantDto.getSearchMerchantId());
                    System.out.println("BlockStatus : "+objBlockMerchantDto.getBlockStatus() );
                    System.out.println("MerchantName : "+objBlockMerchantDto.getMerchantName());
                    System.out.println("IssuerId : "+objBlockMerchantDto.getIssuerId());
                    System.out.println("Reason : "+objBlockMerchantDto.getReason());
                    System.out.println("LastUpdatedDate : "+objBlockMerchantDto.getLastUpdatedDate());
                     Date  lastUpdatedDate = new Date();
                     objBlockMerchantDto.setLastUpdatedDate(lastUpdatedDate);
                     System.out.println(" After LastUpdatedDate : "+objBlockMerchantDto.getLastUpdatedDate());
                    System.out.println("LastUpdatedUserId : "+objBlockMerchantDto.getUserId());
                    if(objBlockMerchantDto.getBlockStatus().equals("U")){
                    objBlockMerchantDto.setBlockStatus("B");
                    }
                    else{
                    	objBlockMerchantDto.setBlockStatus("U");
                    }
                    System.out.println("BlockStatus : "+objBlockMerchantDto.getBlockStatus());
                    
                    System.out.println("*******************DAO Impl *************************************");


                    tx = session.beginTransaction();
                    session.update(objBlockMerchantDto);
                    //session.delete(objBlockMerchantDto);
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


