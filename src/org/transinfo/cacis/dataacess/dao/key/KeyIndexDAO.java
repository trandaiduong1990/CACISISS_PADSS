package org.transinfo.cacis.dataacess.dao.key;

import java.util.Collection;
import java.util.Map;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.dao.BaseDAO;
import org.transinfo.cacis.dto.key.KeyIndexDto;
import org.transinfo.cacis.dto.key.KeyIndexSearchDto;

@SuppressWarnings("unchecked")
public interface KeyIndexDAO extends BaseDAO {
	public Collection search(KeyIndexSearchDto objSearchDto, int pageNo)
			throws TPlusException;

	public boolean isRecordExist(KeyIndexDto objDto) throws TPlusException;

	public KeyIndexDto getKeyIndexForm(String issuerId, String cardProductId,
			String keyType, String transactionChannel) throws TPlusException;

	public boolean add(KeyIndexDto objDto) throws TPlusException;

	public boolean update(KeyIndexDto objDto) throws TPlusException;

	public boolean delete(KeyIndexDto objDto) throws TPlusException;

	public Map checkPPKAgainstCardProducts(String issuerID, String keyType,
			String pinPrintSerialNos) throws TPlusException;

	public Map getPPKList(String issuerID, String keyType)
			throws TPlusException;

	public Map getIndexesList(String issuerID) throws TPlusException;
}