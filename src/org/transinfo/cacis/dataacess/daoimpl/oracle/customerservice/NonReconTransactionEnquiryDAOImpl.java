package org.transinfo.cacis.dataacess.daoimpl.oracle.customerservice;

import java.util.Collection;

import org.transinfo.cacis.TPlusCodes;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.constants.ICacisiss;
import org.transinfo.cacis.dataacess.dao.customerservice.NonReconTransactionEnquiryDAO;
import org.transinfo.cacis.dataacess.daoimpl.oracle.BaseDAOImpl;
import org.transinfo.cacis.dto.customerservice.NonReconTranxEnquirySearchDto;

@SuppressWarnings("unchecked")
public class NonReconTransactionEnquiryDAOImpl extends BaseDAOImpl implements
		NonReconTransactionEnquiryDAO {

	public NonReconTranxEnquirySearchDto search(
			NonReconTranxEnquirySearchDto objNonReconTranxEnquirySearchDto, int pageNo)
			throws TPlusException {

		Collection objSearchCollection = null;
		int totalCount = 0;

		StringBuffer sbf = new StringBuffer();
		StringBuffer sbfSelect = new StringBuffer();
		StringBuffer sbfCount = new StringBuffer();

		try {

			sbfSelect.append("select ");
			sbfSelect.append("nrtlDto.accNo, ");
			sbfSelect.append("nrtlDto.purchaseDate, ");
			sbfSelect.append("nrtlDto.tranxCode, nrtlDto.sourceAmt, nrtlDto.sourceCurrCode, ");
			sbfSelect.append("nrtlDto.destinationAmt, nrtlDto.destinationCurrCode, ");
			sbfSelect.append("nrtlDto.merchantName, nrtlDto.merchantCountryCode, ");
			sbfSelect.append("nrtlDto.authCode ");

			sbfCount.append("select ");
			sbfCount.append("count(nrtlDto.tranxNonReconId) ");

			sbf.append("from NonReconTransactionLogDto nrtlDto, CardsDto cdto ");
			sbf.append("where nrtlDto.accNo = cdto.cardNumber ");

			if (objNonReconTranxEnquirySearchDto.getCardNo() != null
					&& !objNonReconTranxEnquirySearchDto.getCardNo().equals("")) {
				sbf.append("and nrtlDto.accNo = "
						+ objNonReconTranxEnquirySearchDto.getCardNo() + " ");
			}

			if (objNonReconTranxEnquirySearchDto.getAuthCode() != null
					&& !objNonReconTranxEnquirySearchDto.getAuthCode().equals("")) {
				sbf.append("and nrtlDto.authCode = '"
						+ objNonReconTranxEnquirySearchDto.getAuthCode() + "' ");
			}

			if (objNonReconTranxEnquirySearchDto.getStartDate() != null
					&& !objNonReconTranxEnquirySearchDto.getStartDate().equals("")) {
				sbf.append("and nrtlDto.updatedDate >= TO_DATE('"
						+ objNonReconTranxEnquirySearchDto.getStartDate() + " "
						+ ICacisiss.IDateStuff.START_TIME_PART + "', '"
						+ ICacisiss.IDateStuff.DATETIME_FORMAT_VALIDATION
						+ "') ");
			}

			if (objNonReconTranxEnquirySearchDto.getEndDate() != null
					&& !objNonReconTranxEnquirySearchDto.getEndDate().equals("")) {
				sbf.append("and nrtlDto.updatedDate <= TO_DATE('"
						+ objNonReconTranxEnquirySearchDto.getEndDate() + " "
						+ ICacisiss.IDateStuff.END_TIME_PART + "', '"
						+ ICacisiss.IDateStuff.DATETIME_FORMAT_VALIDATION
						+ "') ");
			}

			sbf.append("order by nrtlDto.purchaseDate desc");
			objSearchCollection = getSearchTranxList((sbfSelect.append(sbf))
					.toString(), pageNo);
			objNonReconTranxEnquirySearchDto.setSearchList(objSearchCollection);

			totalCount = getSearchTotalCount((sbfCount.append(sbf)).toString());
			objNonReconTranxEnquirySearchDto.setTotalCount(totalCount);

		} catch (Exception e) {
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error:in TransactionEnquiryDAOImpl search method" + e);
		}

		return objNonReconTranxEnquirySearchDto;
	}
}
