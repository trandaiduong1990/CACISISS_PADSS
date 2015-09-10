package org.transinfo.cacis.dataacess.dao.letters;

import java.util.List;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.dao.BaseDAO;

public interface LetterApplMapDAO extends BaseDAO {

	List getAllLetterApplMap() throws TPlusException;

	Boolean boolUpdate(List letterApplMapDtoList) throws TPlusException;

}
