package org.transinfo.cacis.dataacess.dao.collectionmanagement;

import java.util.Collection;
import java.util.Map;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.dao.BaseDAO;
import org.transinfo.cacis.dto.collectionmanagement.CollectionAgentDto;
import org.transinfo.cacis.formbean.collectionmanagement.CollectionAgentSetupForm;

public interface CollectionAgentDAO extends BaseDAO{

	Map agentList(String issuerId) throws TPlusException;

	boolean add(CollectionAgentDto objDto) throws TPlusException;

	boolean update(CollectionAgentDto objDto)throws TPlusException;

	Collection search(CollectionAgentDto objDto, int pageNo)throws TPlusException;

	CollectionAgentDto getCollectionAgent(String agentId)throws TPlusException;

	boolean checkExitsRecord(CollectionAgentSetupForm objForm)throws TPlusException;

	Map getAgentType() throws TPlusException;

}
