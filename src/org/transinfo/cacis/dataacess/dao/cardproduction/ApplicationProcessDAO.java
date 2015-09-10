package org.transinfo.cacis.dataacess.dao.cardproduction;

import java.util.Collection;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.dao.BaseDAO;
import org.transinfo.cacis.dto.cardproduction.ApplicationFormDto;
import org.transinfo.cacis.dto.cardproduction.ApplicationProcessDto;
import org.transinfo.cacis.dto.cardproduction.ApplicationProcessSearchDto;
import org.transinfo.cacis.dto.cardproduction.CustomerAddressDto;

@SuppressWarnings("unchecked")
public interface ApplicationProcessDAO extends BaseDAO {

	public Collection search(ApplicationProcessSearchDto objSearchDto,
			int pageNo) throws TPlusException;
	
	public boolean update(ApplicationFormDto objDto) throws TPlusException;

	public boolean accept(ApplicationProcessDto objDto, CustomerAddressDto supplAddressDto) throws TPlusException;

	public boolean reject(ApplicationProcessDto objDto) throws TPlusException;

	public Collection customerHistory(String custIdNumber, String appId)
			throws TPlusException;

	public ApplicationProcessDto getApplicationProcessDto(String applicationId)
			throws TPlusException;

	public boolean pending(ApplicationProcessDto objDto) throws TPlusException;

	public int isDuplicateNRC(String nrc, String suppNrc, String appId) throws TPlusException;

	public boolean isDuplicateNRCNewMethod(String nrc, String suppNrc, String appId) throws TPlusException;
}
