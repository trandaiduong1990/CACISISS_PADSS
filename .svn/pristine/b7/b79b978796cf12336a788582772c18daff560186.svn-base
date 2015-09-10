package org.transinfo.cacis.dataacess.daoimpl.oracle.letters;

import java.util.Collection;

import org.transinfo.cacis.TPlusCodes;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.HibernetDAO.HibernetFactory;
import org.transinfo.cacis.dataacess.dao.letters.ChLetterHistDAO;
import org.transinfo.cacis.dataacess.daoimpl.oracle.BaseDAOImpl;
import org.transinfo.cacis.dto.letters.ChLetterHistSearchDto;

public class ChLetterHistDAOImpl extends BaseDAOImpl implements ChLetterHistDAO {

	public Collection search(ChLetterHistSearchDto objSearchDto,int pageNo)
			throws TPlusException
	{
		Collection objSearchCollection = null;
		StringBuffer sbf = new StringBuffer();
		try {

			sbf.append("select af.dispatchId, af.cardNumber, af.applicationId, lt.letterTypesName, to_char(af.dispatchDate,'DD/MM/YYYY') ");
			sbf.append(" FROM  ChLetterHistSearchDto af, LetterTypesListDto lt ");
			sbf.append("where lt.letterTypesId = af.letterId ");

			if (objSearchDto.getLetterId() != null && !objSearchDto.getLetterId().equals(""))
			{
				sbf.append("and af.letterId like '%"+ objSearchDto.getLetterId()+ "%' ");
			}

			if (objSearchDto.getCardNumber() != 0)
			{
				if( objSearchDto.getLetterId() == null && !objSearchDto.getLetterId().equals(""))
				{
					sbf.append(" and af.cardNumber="+ objSearchDto.getCardNumber());
				}
				else
				{
					sbf.append(" and af.cardNumber="+ objSearchDto.getCardNumber());
				}
			}

			objSearchCollection = getSearchList(sbf.toString(),pageNo);


		} catch (Exception e) {
			System.out.println("Error while retrieving the ChLetterHist Search Info" + e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
									 "Error: while retrieving the ChLetterHist Search Info" + e);
		} finally {
			HibernetFactory.closeSession();
		}
		return objSearchCollection;
	}

	public void main(String s[])
	{


		//ChLetterHistDAOImpl daoImpl = new ChLetterHistDAOImpl();
		//daoImpl.search
	}

}
