package org.transinfo.cacis.dataacess.dao.disputemanagement;

import java.util.Collection;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.dao.BaseDAO;
import org.transinfo.cacis.dto.disputemanagement.StatisticReportsSearchDto;

public interface StatisticReportsDAO extends BaseDAO
{
	public Collection daily(StatisticReportsSearchDto objSearchDto,int pageNo)throws TPlusException;
	public Collection weekly(StatisticReportsSearchDto objSearchDto,int pageNo)throws TPlusException;
	public Collection monthly(StatisticReportsSearchDto objSearchDto,int pageNo)throws TPlusException;
	public Collection yearly(StatisticReportsSearchDto objSearchDto,int pageNo)throws TPlusException;
}