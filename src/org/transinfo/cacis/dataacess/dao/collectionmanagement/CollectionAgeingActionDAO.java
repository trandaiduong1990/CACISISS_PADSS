package org.transinfo.cacis.dataacess.dao.collectionmanagement;

import java.util.Collection;
import java.util.Map;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.dao.BaseDAO;
import org.transinfo.cacis.dto.collectionmanagement.CollectionAgeingActionDto;
import org.transinfo.cacis.dto.collectionmanagement.CollectionAgeingDto;
import org.transinfo.cacis.dto.collectionmanagement.CollectionAgentDto;
import org.transinfo.cacis.formbean.collectionmanagement.CollectionAgeingActionSetupForm;
import org.transinfo.cacis.formbean.collectionmanagement.CollectionAgeingSetupForm;
import org.transinfo.cacis.formbean.collectionmanagement.CollectionAgentSetupForm;

public interface CollectionAgeingActionDAO extends BaseDAO{

	boolean add(CollectionAgeingActionDto objDto) throws TPlusException;

	boolean update(CollectionAgeingActionDto objDto)throws TPlusException;

	Collection search(CollectionAgeingActionDto objDto, int pageNo)throws TPlusException;

	CollectionAgeingActionDto getCollectionAgeingAction(String sno)
			throws TPlusException;

	boolean checkExist(CollectionAgeingSetupForm objForm)throws TPlusException;

	boolean checkOverlap(int day, String issuerId)throws TPlusException;

	boolean delete(CollectionAgeingActionDto objDto) throws TPlusException;

	Map getAgeingList(String issuerId) throws TPlusException;

	Map getRemainderTypeList(String string) throws TPlusException;

}
