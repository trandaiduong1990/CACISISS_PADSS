package org.transinfo.cacis.dataacess.dao.settings;

import java.util.Collection;
import java.util.Map;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.dao.BaseDAO;
import org.transinfo.cacis.dto.settings.IssuerDto;
import org.transinfo.cacis.dto.settings.IssuerSearchDto;

@SuppressWarnings("unchecked")
public interface IssuerDAO extends BaseDAO {

	public Map issuerListData() throws TPlusException;

	public Collection search(IssuerSearchDto objSearchDto, int pageNo)
			throws TPlusException;

	public boolean add(IssuerDto objDto) throws TPlusException;

	public boolean update(IssuerDto objDto) throws TPlusException;

	public boolean delete(IssuerDto objDto) throws TPlusException;

	public IssuerDto getIssuerDto(String issuerId) throws TPlusException;

	public int checkExistrecord(IssuerDto objDto) throws TPlusException;
}
