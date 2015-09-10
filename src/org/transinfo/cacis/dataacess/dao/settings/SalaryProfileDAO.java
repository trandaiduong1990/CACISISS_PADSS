package org.transinfo.cacis.dataacess.dao.settings;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.dao.BaseDAO;
import org.transinfo.cacis.dto.settings.SalaryProfileDto;

@SuppressWarnings("unchecked")
public interface SalaryProfileDAO extends BaseDAO {
	
	public Collection search(String cardProduct)throws TPlusException;
	public Map getAddproductList(String issuerId)throws TPlusException;
	public Map getSearchproductList(String issuerId)throws TPlusException;
    public boolean add(SalaryProfileDto objSalaryProfileDto)throws TPlusException;
	public List getSalaryProfileListUpdate(String cardProduct)throws TPlusException;
    public boolean delete(String cardProduct)throws TPlusException;
	public List getSalaryProfileListAppProcess(String cardProduct, String income)throws TPlusException;

}
