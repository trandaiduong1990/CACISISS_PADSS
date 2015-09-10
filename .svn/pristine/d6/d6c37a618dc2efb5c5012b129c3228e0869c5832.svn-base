package org.transinfo.cacis.dataacess.dao.settings;

import java.util.Collection;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.dao.BaseDAO;
import org.transinfo.cacis.dto.settings.MCCDto;
import org.transinfo.cacis.dto.settings.MCCMasterDto;
import org.transinfo.cacis.dto.settings.MCCSearchDto;

@SuppressWarnings("unchecked")
public interface MCCDAO extends BaseDAO {

	// public Map issuerListData()throws TPlusException;

	public Collection search(MCCSearchDto objSearchDto, int pageNo)
			throws TPlusException;

	public Collection searchMccMaster(MCCMasterDto objSearchDto, int pageNo)
			throws TPlusException;

	public boolean add(MCCDto objDto) throws TPlusException;

	public boolean update(MCCDto objDto) throws TPlusException;

	public boolean delete(MCCDto objDto) throws TPlusException;

	public MCCDto getMCCDto(String mccId) throws TPlusException;

	public int checkExistrecord(MCCDto objDto) throws TPlusException;

	public boolean hasWithdrawalLimitRules(String mccId) throws TPlusException;

	public boolean hasRiskTranxConfig(String mccId) throws TPlusException;

	public boolean updateByExecute(MCCDto objDto) throws TPlusException;

}
