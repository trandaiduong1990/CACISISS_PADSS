
package org.transinfo.cacis.dataacess.dao.authorization;

import java.util.Collection;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.dao.BaseDAO;
//import org.transinfo.cacis.dto.authorization.BlackListCardDto;
import org.transinfo.cacis.dto.authorization.BlockMerchantDto;

public interface BlockMerchantDAO extends BaseDAO
{
    public Collection search(BlockMerchantDto objBlockMerchantDto, int pageNo)throws TPlusException;
    public boolean add(BlockMerchantDto objBlockMerchantDto)throws TPlusException;
    public boolean delete(BlockMerchantDto objBlockMerchantDto)throws TPlusException;
}