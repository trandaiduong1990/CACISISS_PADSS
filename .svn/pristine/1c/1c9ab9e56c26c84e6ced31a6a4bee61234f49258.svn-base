package org.transinfo.cacis.dataacess.dao.settings;

import java.util.Collection;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.dao.BaseDAO;
import org.transinfo.cacis.dto.settings.BranchDto;
import org.transinfo.cacis.dto.settings.BranchSearchDto;

@SuppressWarnings("unchecked")
public interface BranchDAO extends BaseDAO {

	public Collection search(BranchSearchDto objSearchDto, int pageNo)
			throws TPlusException;

	public boolean add(BranchDto objDto) throws TPlusException;

	public boolean update(BranchDto objDto) throws TPlusException;

	public boolean delete(BranchDto objDto) throws TPlusException;

	public BranchDto getBranchDto(String branchId) throws TPlusException;

	public int checkExistrecord(BranchDto objDto) throws TPlusException;

	public boolean hasActiveCustomers(String branchId) throws TPlusException;

}
