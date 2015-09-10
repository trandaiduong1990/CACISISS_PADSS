package org.transinfo.cacis.dataacess.dao.settings;

import java.util.ArrayList;
import java.util.Collection;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.dao.BaseDAO;
import org.transinfo.cacis.dto.settings.CardProductDto;
import org.transinfo.cacis.dto.settings.CardProductFeeDto;
import org.transinfo.cacis.dto.settings.CardProductSearchDto;
import org.transinfo.cacis.dto.settings.CardProductTranxDto;
import org.transinfo.cacis.dto.settings.ProductTranxSearchDto;
import org.transinfo.cacis.dto.settings.TranxTypeDto;

@SuppressWarnings("unchecked")
public interface ProductTranxDAO extends BaseDAO {

	public Collection search(ProductTranxSearchDto objSearchDto, int pageNo)
			throws TPlusException;

	public boolean addProductTranx(CardProductTranxDto objCardProductTranxDto) throws TPlusException;

	public TranxTypeDto getTranxTypeDto(String tranxType) throws TPlusException;
	
	public boolean checkExistRecord(CardProductTranxDto objDto) throws TPlusException;

	public boolean update(CardProductTranxDto objDto) throws TPlusException;

}
