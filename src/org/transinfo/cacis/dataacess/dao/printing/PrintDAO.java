package org.transinfo.cacis.dataacess.dao.printing;

import java.util.Collection;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.dao.BaseDAO;

public interface PrintDAO extends BaseDAO
{
	public boolean print()throws TPlusException;
	public Collection retrieve()throws TPlusException;
}