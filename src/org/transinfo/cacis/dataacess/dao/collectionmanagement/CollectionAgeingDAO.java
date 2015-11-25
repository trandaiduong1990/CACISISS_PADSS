package org.transinfo.cacis.dataacess.dao.collectionmanagement;

import java.util.Collection;
import java.util.Map;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.dao.BaseDAO;
import org.transinfo.cacis.dto.collectionmanagement.CollectionAgeingDto;
import org.transinfo.cacis.dto.collectionmanagement.CollectionAgentDto;
import org.transinfo.cacis.formbean.collectionmanagement.CollectionAgeingSetupForm;
import org.transinfo.cacis.formbean.collectionmanagement.CollectionAgentSetupForm;

public interface CollectionAgeingDAO extends BaseDAO{

	boolean add(CollectionAgeingDto objDto) throws TPlusException;

	boolean update(CollectionAgeingDto objDto)throws TPlusException;

	Collection search(CollectionAgeingDto objDto, int pageNo)throws TPlusException;

	CollectionAgeingDto getCollectionAgeing(String ageingPolicy)
			throws TPlusException;

	boolean checkExist(CollectionAgeingSetupForm objForm)throws TPlusException;

	boolean checkOverlap(CollectionAgeingSetupForm objForm)throws TPlusException;

	boolean delete(CollectionAgeingDto objDto) throws TPlusException;

}
