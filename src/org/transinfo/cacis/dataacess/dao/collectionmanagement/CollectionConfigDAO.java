package org.transinfo.cacis.dataacess.dao.collectionmanagement;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.dao.BaseDAO;
import org.transinfo.cacis.dto.collectionmanagement.CollectionConfigDto;

public interface CollectionConfigDAO extends BaseDAO{
	CollectionConfigDto getCollectionConfig() throws TPlusException;
	boolean upload(CollectionConfigDto objDto) throws TPlusException;
	boolean update(CollectionConfigDto objDto) throws TPlusException; 
}
