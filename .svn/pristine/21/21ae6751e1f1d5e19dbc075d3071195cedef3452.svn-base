package org.transinfo.cacis.dataacess.dao.authorization;

import java.util.Collection;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.dao.BaseDAO;
import org.transinfo.cacis.dto.authorization.TagFieldFormatDto;
import org.transinfo.cacis.dto.authorization.TagFieldFormatSearchDto;

public interface  TagFieldFormatDAO extends BaseDAO{
	 	  
            public Collection search(TagFieldFormatSearchDto objDto, int pageNo)throws TPlusException;
	   
	    public boolean add (TagFieldFormatDto objTagFieldFormat)throws TPlusException;

	    public boolean update (TagFieldFormatDto objTagFieldFormat)throws TPlusException;

	    public boolean delete (TagFieldFormatDto objTagFieldFormat)throws TPlusException;

	    public TagFieldFormatDto getTagFieldFormat(String tagName)throws TPlusException;
      	    
}
