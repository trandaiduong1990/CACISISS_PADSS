package org.transinfo.cacis.dataacess.daoimpl.oracle.authorization;

import java.util.Collection;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.transinfo.cacis.TPlusCodes;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.HibernetDAO.HibernetFactory;
import org.transinfo.cacis.dataacess.dao.authorization.TagFieldFormatDAO;
import org.transinfo.cacis.dataacess.daoimpl.oracle.BaseDAOImpl;
import org.transinfo.cacis.dto.authorization.TagFieldFormatDto;
import org.transinfo.cacis.dto.authorization.TagFieldFormatSearchDto;


public class TagFieldFormatDAOImpl extends BaseDAOImpl implements TagFieldFormatDAO {
	
          
	public Collection search(TagFieldFormatSearchDto objDto, int pageNo) throws TPlusException {

		   Collection objSearchCollection = null;
                   StringBuffer sbf = new StringBuffer();
	    try{
                   Session	session = HibernetFactory.currentSession();                                 
                   sbf.append("select tf.tagName,tf.tagDesc,tf.tagFormat,tf.tagAttribute, ");
                   sbf.append("tf.tagResponse,to_char(tf.updatedDate,'dd-MM-yyyy') ");
		   sbf.append(" FROM TagFieldFormatDto tf ");
                  
                   if (objDto.getSearchTagName() != null && !objDto.getSearchTagName().equals(""))
		   {
			sbf.append("where tf.tagName like '%"+ objDto.getSearchTagName()+ "%' ");
			
	           }
                           
		  //Query qry = session.createQuery("FROM TagFieldFormatDto tf where tf.tagName ='"+objDto.getTagName()+"'");
                  //objSearchCollection = (ArrayList)qry.list();   
                   
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
 

  public TagFieldFormatDto getTagFieldFormat(String tagName)throws TPlusException
  {

		TagFieldFormatDto tagField = null;
		
		try
		{

	      Session session =HibernetFactory.currentSession();
	      Transaction tx = session.beginTransaction();
	      tagField = (TagFieldFormatDto)session.get(TagFieldFormatDto.class, tagName);
              
		  System.out.println(" We are after TagFieldFormatDAOIMPL getTagFieldFormat()");
		  tx.commit();
		}
		
		catch (Exception e)
		{
			System.out.println("6" +e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,"Error: while adding the TagField Info"+e.getMessage());
		}
		finally
		{
			HibernetFactory.closeSession();
	 	}


		return 	tagField;

	}

        public boolean add(TagFieldFormatDto objDto)throws TPlusException {

		boolean bolExecute=false;
		Transaction tx=null;
		try
		{
                    Session session =HibernetFactory.currentSession();
                    tx =session.beginTransaction();                   
                    session.save(objDto);
 
		    tx.commit();
                    System.out.println(" We are after TagFieldformatDAOImpl addMethod()");
                    bolExecute=true;

		}

		catch (Exception e)
		{
			if(tx!=null)
			{
				tx.rollback();
			}
			System.out.println("Exception Add" +e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,"Error: while adding the TagField Info"+e.getMessage());
		}
		finally
		{
			HibernetFactory.closeSession();
	 	}

            return 	bolExecute;

        }


        public boolean update(TagFieldFormatDto objDto)throws TPlusException
        {

               Transaction tx = null;
               boolean bolExecute=false;
               try
                 {
                    Session session =HibernetFactory.currentSession();
                    tx =session.beginTransaction();
                    session.update(objDto);		
                    tx.commit();
                    System.out.println("After TagFieldFormatDAOImpl updateMethod()");
                    bolExecute=true;

		}catch (Exception e)
		{

                    System.out.println("6" +e.getMessage());

                    if(tx!=null)
                    {
			tx.rollback();
                    }
                    throw new TPlusException(TPlusCodes.APPLICATION_ERROR,"Error: while adding the TagFiled Info"+e.getMessage());
                }
		finally
		{
                    HibernetFactory.closeSession();
	 	}

		return 	bolExecute;

	}

  
        public boolean delete(TagFieldFormatDto objDto)throws TPlusException 
        {
                Transaction tx = null;
              	boolean bolExecute=false;
            try{
                //System.out.println("TagFieldFormatDto object deleted from the database.");
                Session session = HibernetFactory.currentSession();

                tx = session.beginTransaction();
                System.out.println("Deleting the Record......");
                System.out.println("Delete record TagName ===> "+objDto.getTagName());
            
         /*   Query qry = session.createQuery("DELETE FROM TagFieldFormatDto WHERE tagName = "+objDto.getTagName()+" ");
            qry.executeUpdate();
           */
            
                session.delete(objDto);
                tx.commit();            
                bolExecute=true;
                System.out.println("TagFieldFormatDto object deleted from the database.");
            }catch(Exception ex){
            System.out.println("6" +ex.getMessage());

	    if(tx!=null)
	    {
	      tx.rollback();
	    }
            throw new TPlusException(TPlusCodes.APPLICATION_ERROR,"Error: while deleting"+ex.getMessage());
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
         TagFieldFormatSearchDto objDto = new TagFieldFormatSearchDto();
         
                objDto.setSearchTagName("Tag2");
               //objDto.setTagDesc("Tag Desc");
                //objDto.setTagFormat("Tag Format");
                //objDto.setTagAttribute("Attr");
                //objDto.setTagLength(20);
                //objDto.setTagLeftField("Y");
               // objDto.setTagBinary("Y");
               // objDto.setTagResponse("Y");
               // objDto.setUpdatedDate(objDto.getUpdatedDate());
               // objDto.setUserId("Ramesh");

         TagFieldFormatDAOImpl impl = new TagFieldFormatDAOImpl();
         impl.search(objDto,0);
         //impl.add(objDto);

    }
        catch(Exception e){System.out.println(e);}
    }
  
}


