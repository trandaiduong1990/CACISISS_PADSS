package org.transinfo.cacis.dataacess.dao.letters;

import java.util.Collection;
import java.util.Map;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.dao.BaseDAO;
import org.transinfo.cacis.dto.letters.LetterCategoryDto;
import org.transinfo.cacis.dto.letters.LetterTemplateDto;
import org.transinfo.cacis.dto.letters.LetterTemplateSearchDto;

public interface LetterTemplateDAO extends BaseDAO {

	LetterTemplateSearchDto search(LetterTemplateSearchDto objDto, int pageNo) throws TPlusException;

	LetterCategoryDto getLetterCategory(String letterCategory) throws TPlusException;

	int checkExitsRecord(LetterTemplateDto objDto) throws TPlusException;

	boolean create(LetterTemplateDto objDto) throws TPlusException;

	LetterTemplateDto getLetterTemplateDetail(String letterId) throws TPlusException;

	boolean update(LetterTemplateDto objDto) throws TPlusException;

	boolean delete(LetterTemplateDto objDto) throws TPlusException;

	Collection getAllLetterTemplate() throws TPlusException;

	Map executeQuery(String sqlQuery) throws TPlusException;

}
