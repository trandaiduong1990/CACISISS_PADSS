package org.transinfo.cacis.dataacess.daoimpl.oracle.letters;

import java.util.Collection;

import org.transinfo.cacis.TPlusCodes;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.HibernetDAO.HibernetFactory;
import org.transinfo.cacis.dataacess.dao.letters.ApplicationProcessSearchDAO;
import org.transinfo.cacis.dataacess.daoimpl.oracle.BaseDAOImpl;
import org.transinfo.cacis.dto.letters.ApplicationProcessSearchDto;

@SuppressWarnings("unchecked")
public class ApplicationProcessSearchDAOImpl extends BaseDAOImpl implements
		ApplicationProcessSearchDAO {

	public Collection search(ApplicationProcessSearchDto objSearchDto,
			int pageNo) throws TPlusException {

		Collection objSearchCollection = null;
		StringBuffer sbf = new StringBuffer();
		try {
			sbf.append("SELECT dl.dispatchId, lt.letterTypesName, dl.cardNumber, to_char(dl.lastUpdateDate,'DD/MM/YYYY') ");
			sbf.append("FROM  DispatchLetterSearchDto dl, LetterCategoryDto lc, LetterTypesListDto lt ");
			sbf.append("where lt.category = lc.id ");
			sbf.append("and lt.letterTypesId = dl.letterId ");

			if (objSearchDto.getLetterCategory()!= null && !objSearchDto.getLetterCategory().equals(""))
			{
				sbf.append("and lc.id = '" + objSearchDto.getLetterCategory()+"' ");
			}
			if (objSearchDto.getCardNumber() > 0)
			{
				sbf.append("and dl.cardNumber = " + objSearchDto.getCardNumber()
						+ " ");
			}
			if (objSearchDto.getLetterId() != null && !objSearchDto.getLetterId().equals(""))
			{
				sbf.append("and dl.letterId like '%"+ objSearchDto.getLetterId()+ "%' ");
			}
			System.out.println("objSearchDto.getFromDate() => "
					+ objSearchDto.getFromDate());
			System.out.println("objSearchDto.getToDate() => "
					+ objSearchDto.getToDate());

			if (objSearchDto.getFromDate() != null
					&& !objSearchDto.getFromDate().equals("")) {
				int dayFromDate = objSearchDto.getFromDate().getDate();
				int monthFromDate = objSearchDto.getFromDate().getMonth() + 1;
				int yearFromDate = objSearchDto.getFromDate().getYear() + 1900;
				System.out.println("yearFromDate = " + yearFromDate);
				String strFromDate = dayFromDate + "/" + monthFromDate + "/"
						+ yearFromDate;
				sbf.append("and ( dl.lastUpdateDate >= TO_DATE('" + strFromDate
						+ "', 'DD/MM/YYYY')) ");
			}
			if (objSearchDto.getToDate() != null
					&& !objSearchDto.getToDate().equals("")) {
				int dayToDate = objSearchDto.getToDate().getDate();
				int monthToDate = objSearchDto.getToDate().getMonth() + 1;
				int yearToDate = objSearchDto.getToDate().getYear() + 1900;
				System.out.println("yearToDate = " + yearToDate);
				String strToDate = dayToDate + "/" + monthToDate + "/"
						+ yearToDate;
				sbf.append("and (dl.lastUpdateDate <= TO_DATE('" + strToDate
						+ "', 'DD/MM/YYYY')) ");
			}
			objSearchCollection = getSearchList(sbf.toString(), pageNo);

		} catch (Exception e) {
			System.out
					.println("Error while retrieving the ApplicationProcess Search Info"
							+ e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving the ApplicationProcess Search Info"
							+ e);
		} finally {
			HibernetFactory.closeSession();
		}
		return objSearchCollection;
	}

	public static void main(String s[]) throws Exception {
		ApplicationProcessSearchDAOImpl objImpl = new ApplicationProcessSearchDAOImpl();
		ApplicationProcessSearchDto objDto = new ApplicationProcessSearchDto();
		try {
			Collection objSearchCollection = null;
			objSearchCollection = objImpl.search(objDto, 0);
			System.out.println("objSearchCollection = " + objSearchCollection.size());
		} catch (Exception e) {
			System.out.println("Error OriginalRequestDAOImpl: " + e);
			throw e;
		}
	}

}
