package org.transinfo.cacis.dataacess.dao.applicationforms;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.dao.BaseDAO;
import org.transinfo.cacis.dto.applicationforms.AddCardProcessSearchDto;
import org.transinfo.cacis.dto.applicationforms.AddCardProcessSetupDto;

public interface AddCardProcessDAO extends BaseDAO {

	public AddCardProcessSearchDto search(
			AddCardProcessSearchDto objAddCardProcessSearchDto, int pageNo)
			throws TPlusException;

	public AddCardProcessSetupDto getAddProductForm(
			AddCardProcessSetupDto objAddCardProcessSetupDto) throws TPlusException;

	public boolean reject(AddCardProcessSetupDto objAddCardProcessSetupDto) throws TPlusException;

	public boolean accept(
			AddCardProcessSetupDto objAddCardProcessSetupDto) throws TPlusException;
}
