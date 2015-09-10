package org.transinfo.cacis.dataacess.dao.settings;

import java.util.Collection;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.dao.BaseDAO;
import org.transinfo.cacis.dto.settings.CustomerTypeDto;
import org.transinfo.cacis.dto.settings.CustomerTypeSearchDto;

@SuppressWarnings("unchecked")
public interface CustomerTypeDAO extends BaseDAO {

	public Collection search(CustomerTypeSearchDto objSearchDto, int pageNo)
			throws TPlusException;

	public boolean add(CustomerTypeDto objDto) throws TPlusException;

	public boolean update(CustomerTypeDto objDto) throws TPlusException;

	public boolean delete(CustomerTypeDto objDto) throws TPlusException;

	public CustomerTypeDto getCustomerTypeDto(String custTypeId)
			throws TPlusException;

	public int checkExistrecord(CustomerTypeDto objDto) throws TPlusException;

	public boolean hasActiveCustomers(String customerTypeId)
			throws TPlusException;

	public boolean hasWithdrawalLimitRules(String customerTypeId)
			throws TPlusException;
}
