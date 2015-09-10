package org.transinfo.cacis.controller.key;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.key.KeyIndexDAO;
import org.transinfo.cacis.dto.key.KeyIndexDto;
import org.transinfo.cacis.dto.key.KeyIndexSearchDto;

/**
 * A Business Delegate that abstracts out the business specific calls and
 * exceptions . The web tier does not need to know about the business-specific
 * details
 **/
@SuppressWarnings("unchecked")
public class KeyIndexManager {

	private KeyIndexDAO objKeyIndexDAO;

	public KeyIndexManager() throws Exception {
		objKeyIndexDAO = DAOFactory.getInstance().getKeyIndexDAO();
	}

	public Collection getKeyIndexList(KeyIndexSearchDto objSearchtDto,
			int pageNo) throws TPlusException {

		Collection searchLst = null;

		try {

			searchLst = objKeyIndexDAO.search(objSearchtDto, pageNo);

		} catch (Exception e) {
			System.out.println("Error while search operation" + e);
			throw new TPlusException("Error in controller search method" + e);
		}
		return searchLst;
	}

	public boolean isRecordExist(KeyIndexDto objDto) throws TPlusException {
		boolean isRecExist = false;
		try {

			isRecExist = objKeyIndexDAO.isRecordExist(objDto);

			System.out.println("isRecExist==>" + isRecExist);
		} catch (Exception e) {
			throw new TPlusException("Error in Controller isRecordExist method"
					+ e);
		}
		return isRecExist;
	}

	public KeyIndexDto get(String issuerId, String cardProductId, String keyType, String transactionChannel)
			throws TPlusException {
		KeyIndexDto keyDto = null;
		try {
			keyDto = objKeyIndexDAO.getKeyIndexForm(issuerId, cardProductId,
					keyType, transactionChannel);
		} catch (Exception e) {
			throw new TPlusException("Error in KeyIndexForm get method" + e);
		}
		return keyDto;
	}

	public boolean add(KeyIndexDto objDto) throws TPlusException {

		boolean success = false;
		try {
			success = objKeyIndexDAO.add(objDto);
		} catch (Exception e) {
			throw new TPlusException("Error in Add method");
		}
		return success;
	}

	public boolean update(KeyIndexDto objDto) throws TPlusException {
		boolean success = false;
		try {
			success = objKeyIndexDAO.update(objDto);
		} catch (Exception e) {
			throw new TPlusException("Error in KeyIndexForm update method" + e);
		}
		return success;
	}

	public boolean delete(KeyIndexDto objDto) throws TPlusException {
		boolean success = false;

		try {
			success = objKeyIndexDAO.delete(objDto);
		} catch (Exception e) {
			throw new TPlusException("Error in KeyIndexForm delete method" + e);
		}
		return success;
	}

	public Map checkPPKAgainstCardProducts(String issuerID, String keyType,
			String pinPrintSerialNos) throws TPlusException {
		Map res = new HashMap();
		try {

			res = objKeyIndexDAO.checkPPKAgainstCardProducts(issuerID, keyType,
					pinPrintSerialNos);

		} catch (Exception e) {
			throw new TPlusException("Error in Controller isRecordExist method"
					+ e);
		}
		return res;
	}
	
	public Map getPPKList(String issuerID, String keyType) throws TPlusException {
		Map res = new HashMap();
		try {

			res = objKeyIndexDAO.getPPKList(issuerID, keyType);

		} catch (Exception e) {
			throw new TPlusException("Error in Controller isRecordExist method"
					+ e);
		}
		return res;
	}
	
	public Map getIndexesList(String issuerID) throws TPlusException {
		Map res = new HashMap();
		try {

			res = objKeyIndexDAO.getIndexesList(issuerID);

		} catch (Exception e) {
			throw new TPlusException("Error in Controller isRecordExist method"
					+ e);
		}
		return res;
	}

	public static void main(String s[]) throws Exception {

		//KeyIndexManager keyMgr = new KeyIndexManager();
		KeyIndexDto objDto = new KeyIndexDto();
		//KeyIndexSearchDto searchDto = new KeyIndexSearchDto();

		objDto.id.setIssuerId("issuer1");
		objDto.id.setCardProductId("4");
		objDto.id.setKeyType("aa");
		objDto.setKeyIndex(1);
		objDto.setStatus("A");
		objDto.setUpdatedDate(objDto.getUpdatedDate());
		objDto.setUserId("Ramesh");

		// keyMgr.getKeyIndexList(searchDto, 0);
		// keyMgr.add(objDto);
		// keyMgr.update(objDto);
		// keyMgr.delete(objDto);
	}

}