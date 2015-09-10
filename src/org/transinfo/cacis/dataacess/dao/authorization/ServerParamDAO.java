package org.transinfo.cacis.dataacess.dao.authorization;

import java.util.Collection;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.dao.BaseDAO;
import org.transinfo.cacis.dto.authorization.ServerParamFormDto;
import org.transinfo.cacis.dto.authorization.ServerParamSearchDto;

public interface  ServerParamDAO extends BaseDAO{
	 	  
            public Collection getServerParam(ServerParamSearchDto objDto, int pageNo)throws TPlusException;
            
	    public boolean update(ServerParamFormDto objDto)throws TPlusException;
	    	          	    
}
