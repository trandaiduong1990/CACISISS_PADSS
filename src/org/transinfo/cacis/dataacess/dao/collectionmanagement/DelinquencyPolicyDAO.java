package org.transinfo.cacis.dataacess.dao.collectionmanagement;

import java.util.Collection;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.dao.BaseDAO;
import org.transinfo.cacis.dto.collectionmanagement.DelinquencyPolicySearchDto;

public interface DelinquencyPolicyDAO extends BaseDAO {

	public Collection search(DelinquencyPolicySearchDto objDto, int pageNo)
			throws TPlusException;

}
