package org.transinfo.cacis.dataacess.dao.applicationforms;

import java.util.Collection;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.dao.BaseDAO;
import org.transinfo.cacis.dto.applicationforms.SupplementaryFormDto;
import org.transinfo.cacis.dto.applicationforms.SupplementaryFormSearchDto;
import org.transinfo.cacis.dto.cardproduction.CardsDto;

@SuppressWarnings("unchecked")
public interface SupplFormDAO extends BaseDAO {

	public Collection search(SupplementaryFormSearchDto objSearchDto, int pageNo)
			throws TPlusException;

	public SupplementaryFormDto getSupplementaryForm(String applicationid)
			throws TPlusException;

	public boolean add(SupplementaryFormDto objSupplFormDto)
			throws TPlusException;

	public boolean update(SupplementaryFormDto objSupplFormDto)
			throws TPlusException;

	public boolean reject(SupplementaryFormDto objSupplFormDto)
			throws TPlusException;

	public boolean accept(SupplementaryFormDto objSupplFormDto)
			throws TPlusException;

	/*
	 * This method is used for getting the Cards object to assign the primary
	 * cardholder account details to newly created supplementary card
	 */
	public CardsDto getCardsDto(long primaryCardNo) throws TPlusException;

	// for checking Existing records
	public int checkExistrecord(Object commobj) throws TPlusException;

}