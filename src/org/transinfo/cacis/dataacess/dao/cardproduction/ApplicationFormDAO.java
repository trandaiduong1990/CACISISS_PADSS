package org.transinfo.cacis.dataacess.dao.cardproduction;

import java.util.Collection;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.dao.BaseDAO;
import org.transinfo.cacis.dto.cardproduction.ApplicationFormDto;
import org.transinfo.cacis.dto.cardproduction.ApplicationFormSearchDto;

@SuppressWarnings("unchecked")
public interface ApplicationFormDAO extends BaseDAO {

	public Collection search(ApplicationFormSearchDto objSearchDto, int pageNo)
			throws TPlusException;

	public boolean add(ApplicationFormDto objDto) throws TPlusException;

	public boolean update(ApplicationFormDto objDto) throws TPlusException;

	public boolean delete(ApplicationFormDto objDto) throws TPlusException;

	public ApplicationFormDto getApplicationForm(String applicationId)
			throws TPlusException;

	public int checkExistrecord(Object commonObj) throws TPlusException;

	public int checkExistrecordOnUpdate(Object commonObj) throws TPlusException;

	public int isDuplicateNRC(Object commonObj) throws TPlusException;

	public boolean isDuplicateNRCNewMethod(Object commonObj) throws TPlusException;

}
