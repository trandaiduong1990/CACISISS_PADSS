package org.transinfo.cacis.dataacess.dao.applicationforms;

import java.util.Collection;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.dao.BaseDAO;
import org.transinfo.cacis.dto.applicationforms.CardsRenewalDto;

@SuppressWarnings("unchecked")
public interface CardsRenewalDAO extends BaseDAO {

	public Collection renewalList(int pageNo) throws TPlusException;

	public boolean renewalProcess(CardsRenewalDto objDto) throws TPlusException;

	public CardsRenewalDto getOpenRenewalSubmission(String cardNo)
			throws TPlusException;

	public Collection search(CardsRenewalDto objDto) throws TPlusException;

	public boolean add(CardsRenewalDto objDto) throws TPlusException;

	public Collection processSearch(CardsRenewalDto objDto, int pageNo)
			throws TPlusException;

	public boolean accept(CardsRenewalDto objDto) throws TPlusException;

	public boolean reject(CardsRenewalDto objDto) throws TPlusException;
}