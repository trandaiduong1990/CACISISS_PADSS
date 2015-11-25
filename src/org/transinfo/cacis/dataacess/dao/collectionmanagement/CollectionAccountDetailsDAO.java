package org.transinfo.cacis.dataacess.dao.collectionmanagement;

import java.util.Collection;
import java.util.Map;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.dao.BaseDAO;
import org.transinfo.cacis.dto.collectionmanagement.CollectionDto;
import org.transinfo.cacis.dto.collectionmanagement.CollectionAgeingDto;
import org.transinfo.cacis.dto.collectionmanagement.CollectionAgentDto;
import org.transinfo.cacis.formbean.collectionmanagement.CollectionAgeingActionSetupForm;
import org.transinfo.cacis.formbean.collectionmanagement.CollectionAgeingSetupForm;
import org.transinfo.cacis.formbean.collectionmanagement.CollectionAgentSetupForm;

public interface CollectionAccountDetailsDAO extends BaseDAO{

	boolean update(CollectionDto objDto)throws TPlusException;

	Collection search(CollectionDto objDto, String customerName, int pageNo)throws TPlusException;

	CollectionDto getCollection(String colectId)	throws TPlusException;

	Map getStatusList() throws TPlusException;

	Map getAgentList(String issuerId) throws TPlusException;

	Map getUserBranchList(String issuerId) throws TPlusException;
}
