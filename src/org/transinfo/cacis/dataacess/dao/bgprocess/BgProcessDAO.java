package org.transinfo.cacis.dataacess.dao.bgprocess;

import java.util.Date;
import java.util.List;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.dao.BaseDAO;
import org.transinfo.cacis.dto.applicationforms.CardsRenewalDto;
import org.transinfo.cacis.dto.customerservice.CardActivityDto;

@SuppressWarnings("unchecked")
public interface BgProcessDAO extends BaseDAO {

	public List getClosingCardsList(Date cutOffDate) throws TPlusException;

	public boolean updateCardClosing(String cardCloseSNo) throws TPlusException;

	public List getRenewalCardsList(Date startDate, Date endDate)
			throws TPlusException;

	public int renewalProcessStatus(String cardNo) throws TPlusException;

	public boolean insertRenewalAndActivity(CardsRenewalDto objCardsRenewalDto,
			CardActivityDto objActivityDto) throws TPlusException;

	public List getChangeClosingCardsList(Date cutOffDate)
			throws TPlusException;

	public boolean updateChangeCardClosing(String cardChangeCloseSNo,
			String cardNo) throws TPlusException;

}
