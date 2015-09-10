package org.transinfo.cacis.controller.authorization;
import java.util.Collection;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.authorization.TagFieldFormatDAO;
import org.transinfo.cacis.dto.authorization.TagFieldFormatDto;
import org.transinfo.cacis.dto.authorization.TagFieldFormatSearchDto;

/**
  * A Business Delegate that abstracts out the business specific calls
  * and exceptions . The web tier does not need to know about the
  * business-specific details
  **/
public class TagFieldFormatManager
{

	 private TagFieldFormatDAO objDAO;

	    public TagFieldFormatManager()throws Exception {
	    	objDAO = DAOFactory.getInstance().getTagFieldDAO();

	    }

	    public Collection search(TagFieldFormatSearchDto objDto, int pageNo)
	                    throws TPlusException {

			Collection searchLst= null;

	        try {
	            searchLst = objDAO.search(objDto, pageNo);

	        } catch (Exception e) {
				System.out.println("Controller"+e);
	            throw new TPlusException("Error in GetAll method");
	        }
	        return searchLst;

	    }

	   public TagFieldFormatDto get(String tagName)
	                    throws TPlusException {
			TagFieldFormatDto objTagFieldDto= null;

	        try {
	            objTagFieldDto = objDAO.getTagFieldFormat(tagName);

	        } catch (Exception e) {
				System.out.println("Controller"+e);
	            throw new TPlusException("Error in GetAll method");
	        }
	        return objTagFieldDto;
	    }


	    public boolean add(TagFieldFormatDto objTagField)
	                    throws TPlusException {

	        boolean success;
	        try {


			 /*	if(!validate(objTagField, objDAO.ADD))
				{

				  System.out.println("Record Already Exists");
				}   */

	            success = objDAO.add(objTagField);
	        } catch (Exception e) {
	            throw new TPlusException("Error in Add method");
	        }
	        return success;
	    }

	    public boolean update(TagFieldFormatDto objTagField)
	                    throws TPlusException {

	        boolean success;
	        try {

		     /*	if(!validate(objTagField, objDAO.UPDATE))
				{

				 System.out.println("Record Not Exists");
			} */

	            success = objDAO.update(objTagField);
	        } catch (Exception e) {
	            throw new TPlusException("Error in GetAll method");
	        }
	        return success;
	    }

	    public boolean delete(TagFieldFormatDto objTagField)
	                    throws TPlusException {

	        boolean success = false;
	        try {

			     /*	if(!validate(objTagField, objDAO.UPDATE))
				{

				  System.out.println("Record Not Exists");
				}  */
                    System.out.println("Tag Name "+objTagField.getTagName());
		    System.out.println("Before Delete Call");
	            success = objDAO.delete(objTagField);
                    System.out.println("Delete===>"+success);
	        } catch (Exception e) {
	            throw new TPlusException("Error in Delete method");
	        }
	        return success;
	    }

/*
   public boolean validate(Object obj,int mode )throws TPlusException
	    {
	        boolean rtnMessage = true;

	         TagFieldFormatDto objTagField = (TagFieldFormatDto)obj;

	        System.out.println(objTagField.getTagName());

	        if(mode==0 && objDAO.checkExistrecord(objTagField)>0)
	        {
	            rtnMessage = false;
	        }
	        if(mode==1 && objDAO.checkExistrecord(objTagField)==0)
	        {
				rtnMessage = false;
	        }

	        return rtnMessage;
	    }

 */

	  public static void main(String s[])throws Exception
	    {
		  TagFieldFormatManager objManager = new  TagFieldFormatManager();
		  TagFieldFormatDto objTagFieldFormatDto = new TagFieldFormatDto();
		   //adding

                objTagFieldFormatDto.setTagName("Tag4");
                //objTagFieldFormatDto.setTagDesc("Tag Desc");
                objTagFieldFormatDto.setTagFormat("tag form");
                objTagFieldFormatDto.setTagAttribute("attr");
                //objTagFieldFormatDto.setTagLength(20);
                //objTagFieldFormatDto.setTagLeftField("Y");
                //objTagFieldFormatDto.setTagBinary("Y");
                //objTagFieldFormatDto.setTagResponse("Y");
                //objTagFieldFormatDto.setUpdatedDate(objTagFieldFormatDto.getUpdatedDate());
                //objTagFieldFormatDto.setUserId("Ramesh");

                // objManager.search(objTagFieldFormatDto,0);
		//objManager.add(objTagFieldFormatDto);
		//objManager.update(objTagFieldFormatDto);
		objManager.delete(objTagFieldFormatDto);
	 	}

}