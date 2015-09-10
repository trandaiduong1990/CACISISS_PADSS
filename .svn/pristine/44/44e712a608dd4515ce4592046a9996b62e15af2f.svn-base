package org.transinfo.cacis.dataacess.dao.settings;

import java.util.Collection;
import java.util.Map;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.dao.BaseDAO;
import org.transinfo.cacis.dto.settings.EMVProfileDto;
import org.transinfo.cacis.dto.settings.EMVProfileSearchDto;

public interface EMVProfileDAO extends BaseDAO{

	public boolean create(EMVProfileDto objEMVProfileDto) throws TPlusException;

	public int checkExitsRecord(EMVProfileDto objDto) throws TPlusException;

	public Collection search(EMVProfileSearchDto objDto, int pageNo) throws TPlusException;

	public EMVProfileDto getEMVProfileDto(String emvProfileName) throws TPlusException;

	public boolean update(EMVProfileDto objDto) throws TPlusException;

	public boolean delete(EMVProfileDto objDto) throws TPlusException;

	public int checkEmvApplTypeCryto(EMVProfileDto objDto) throws TPlusException;

	public Map getCryptogramList(String applType) throws TPlusException;

}
