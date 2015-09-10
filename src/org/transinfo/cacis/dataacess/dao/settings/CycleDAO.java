package org.transinfo.cacis.dataacess.dao.settings;

import java.util.Collection;
import java.util.Map;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.dao.BaseDAO;
import org.transinfo.cacis.dto.settings.CycleDto;
import org.transinfo.cacis.dto.settings.CycleSearchDto;

@SuppressWarnings("unchecked")
public interface CycleDAO extends BaseDAO {

	public Map issuerListData() throws TPlusException;

	public Collection search(CycleSearchDto objsearchDto, int pageNo)
			throws TPlusException;

	public boolean add(CycleDto objDto) throws TPlusException;

	public boolean update(CycleDto objDto) throws TPlusException;

	public boolean delete(CycleDto objDto) throws TPlusException;

	public CycleDto getCycleDto(int cycleNo) throws TPlusException;

	public int checkExistrecord(CycleDto objDto) throws TPlusException;

	public int checkExistrecordCycleNo(String cycleNo) throws TPlusException;

	public int checkExistrecordDayOfMonth(String cycleNo) throws TPlusException;

	public boolean hasCustomerAccounts(String cycleNo) throws TPlusException;

}
