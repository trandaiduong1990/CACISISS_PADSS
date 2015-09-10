
package org.transinfo.cacis.dataacess.dao.letters;

import java.util.Collection;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.dao.BaseDAO;
import org.transinfo.cacis.dto.letters.ChLetterHistSearchDto;

public interface ChLetterHistDAO extends BaseDAO
{

	public Collection search(ChLetterHistSearchDto objSearchDto,int pageNo)throws TPlusException;
}