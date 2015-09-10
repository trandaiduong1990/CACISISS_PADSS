
package org.transinfo.cacis.dataacess.dao.letters;

import java.util.Collection;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.dao.BaseDAO;
import org.transinfo.cacis.dto.letters.ApplicationProcessSearchDto;

public interface ApplicationProcessSearchDAO extends BaseDAO
{

	public Collection search(ApplicationProcessSearchDto objSearchDto,int pageNo)throws TPlusException;
}