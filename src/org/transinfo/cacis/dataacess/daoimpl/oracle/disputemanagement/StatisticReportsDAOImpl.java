package org.transinfo.cacis.dataacess.daoimpl.oracle.disputemanagement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.transinfo.cacis.TPlusCodes;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.common.CommonDataBean;
import org.transinfo.cacis.dataacess.HibernetDAO.HibernetFactory;
import org.transinfo.cacis.dataacess.dao.disputemanagement.StatisticReportsDAO;
import org.transinfo.cacis.dataacess.daoimpl.oracle.BaseDAOImpl;
import org.transinfo.cacis.dto.disputemanagement.StatisticReportsSearchDto;

public class StatisticReportsDAOImpl extends BaseDAOImpl implements
		StatisticReportsDAO {

	public Collection daily(StatisticReportsSearchDto objSearchDto, int pageNo)
			throws TPlusException {

		Collection objSearchCollection = null;
		String strTime = "";
		CommonDataBean objReport = new CommonDataBean();
		ArrayList arlResultData = new ArrayList();
		String strN = "";
		String strP = "";
		String strC = "";
		String strDateRange = "";
		try {
			Session session = HibernetFactory.currentSession();
			String strSql = "select to_char(claimDate,'DD/MM/YYYY') from DisputeClaimFormDto ";
			strSql += "where issuerId = '" + objSearchDto.getIssuerId() + "' ";
			strDateRange = setDateRangeHQL(objSearchDto);
			
			strSql += strDateRange + "group by claimDate ";
			System.out.println("==========01===========");
			System.out.println(strSql);
			System.out.println("=======================");
			Query qry = session.createQuery(strSql);
			qry.setFirstResult(pageNo * 10);
			qry.setMaxResults(CommonDataBean.RECORDS_IN_PAGE);
			List list = qry.list();
			for (Iterator it = list.iterator(); it.hasNext();) {
				strTime = (String) it.next();
				System.out.println("String strTime => " + strTime);
				strN = "select status from DisputeClaimFormDto where status='N' and to_char(claimDate,'DD/MM/YYYY') = '"
						+ strTime + "' " + strDateRange;
				strP = "select status from DisputeClaimFormDto where status='P' and to_char(claimDate,'DD/MM/YYYY') = '"
						+ strTime + "' " + strDateRange;
				strC = "select status from DisputeClaimFormDto where status='C' and to_char(claimDate,'DD/MM/YYYY') = '"
						+ strTime + "' " + strDateRange;
				Query qryN = session.createQuery(strN);
				List listN = qryN.list();
				Query qryP = session.createQuery(strP);
				List listP = qryP.list();
				Query qryC = session.createQuery(strC);
				List listC = qryC.list();
				objReport.setColumn1(strTime);
				objReport.setColumn2(Integer.toString(listN.size()));
				objReport.setColumn3(Integer.toString(listP.size()));
				objReport.setColumn4(Integer.toString(listC.size()));
				arlResultData.add(objReport);
				objReport = new CommonDataBean();
				System.out.println("=======================");
			}
			objSearchCollection = arlResultData;
		} catch (Exception e) {
			System.out
					.println("Error while retrieving the StatisticReports Search Info"
							+ e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving the StatisticReports Search Info"
							+ e);
		} finally {
			HibernetFactory.closeSession();
		}
		return objSearchCollection;
	}

	public Collection weekly(StatisticReportsSearchDto objSearchDto, int pageNo)
			throws TPlusException {
		Collection objSearchCollection = null;
		String strTime = "";
		CommonDataBean objReport = new CommonDataBean();
		ArrayList arlResultData = new ArrayList();
		String strN = "";
		String strP = "";
		String strC = "";
		String strDateRange = "";
		try {
			Session session = HibernetFactory.currentSession();
			String strSql = "select distinct to_char(claimDate,'Wth - - MON YYYY') from DisputeClaimFormDto ";
			strSql += "where issuerId = '" + objSearchDto.getIssuerId() + "' ";
			strDateRange = setDateRangeHQL(objSearchDto);
			System.out.println("==========01===========");
			System.out.println(strSql+strDateRange);
			System.out.println("=======================");
			Query qry = session.createQuery(strSql+strDateRange);
			qry.setFirstResult(pageNo * 10);
			qry.setMaxResults(CommonDataBean.RECORDS_IN_PAGE);
			List list = qry.list();
			System.out.println("List strTime => " + list.size());
			for (Iterator it = list.iterator(); it.hasNext();) {
				strTime = (String) it.next();
				System.out.println("String strTime => " + strTime);
				strN = "select status from DisputeClaimFormDto where status='N' and to_char(claimDate,'Wth - - MON YYYY') = '"
						+ strTime + "' " + strDateRange;
				strP = "select status from DisputeClaimFormDto where status='P' and to_char(claimDate,'Wth - - MON YYYY') = '"
						+ strTime + "' " + strDateRange;
				strC = "select status from DisputeClaimFormDto where status='C' and to_char(claimDate,'Wth - - MON YYYY') = '"
						+ strTime + "' " + strDateRange;
				Query qryN = session.createQuery(strN);
				List listN = qryN.list();
				Query qryP = session.createQuery(strP);
				List listP = qryP.list();
				Query qryC = session.createQuery(strC);
				List listC = qryC.list();
				objReport.setColumn1(strTime);
				objReport.setColumn2(Integer.toString(listN.size()));
				objReport.setColumn3(Integer.toString(listP.size()));
				objReport.setColumn4(Integer.toString(listC.size()));
				arlResultData.add(objReport);
				objReport = new CommonDataBean();
				System.out.println("=======================");
			}
			objSearchCollection = arlResultData;
		} catch (Exception e) {
			System.out
					.println("Error while retrieving the StatisticReports Search Info"
							+ e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving the StatisticReports Search Info"
							+ e);
		} finally {
			HibernetFactory.closeSession();
		}
		return objSearchCollection;
	}

	public Collection monthly(StatisticReportsSearchDto objSearchDto, int pageNo)
			throws TPlusException {

		Collection objSearchCollection = null;
		String strTime = "";
		CommonDataBean objReport = new CommonDataBean();
		ArrayList arlResultData = new ArrayList();
		String strN = "";
		String strP = "";
		String strC = "";
		String strDateRange = "";
		try {
			Session session = HibernetFactory.currentSession();
			String strSql = "select distinct to_char(claimDate,'MON YYYY') from DisputeClaimFormDto ";
			strSql += "where issuerId = '" + objSearchDto.getIssuerId() + "' ";
			strDateRange = setDateRangeHQL(objSearchDto);
			System.out.println("==========01===========");
			System.out.println(strSql+strDateRange);
			System.out.println("=======================");
			Query qry = session.createQuery(strSql+strDateRange);
			qry.setFirstResult(pageNo * 10);
			qry.setMaxResults(CommonDataBean.RECORDS_IN_PAGE);
			List list = qry.list();
			for (Iterator it = list.iterator(); it.hasNext();) {
				strTime = (String) it.next();
				System.out.println("String strTime => " + strTime);
				strN = "select status from DisputeClaimFormDto where status='N' and to_char(claimDate,'MON YYYY') = '"
						+ strTime + "' " + strDateRange;
				strP = "select status from DisputeClaimFormDto where status='P' and to_char(claimDate,'MON YYYY') = '"
						+ strTime + "' " + strDateRange;
				strC = "select status from DisputeClaimFormDto where status='C' and to_char(claimDate,'MON YYYY') = '"
						+ strTime + "' " + strDateRange;
				Query qryN = session.createQuery(strN);
				List listN = qryN.list();
				Query qryP = session.createQuery(strP);
				List listP = qryP.list();
				Query qryC = session.createQuery(strC);
				List listC = qryC.list();
				objReport.setColumn1(strTime);
				objReport.setColumn2(Integer.toString(listN.size()));
				objReport.setColumn3(Integer.toString(listP.size()));
				objReport.setColumn4(Integer.toString(listC.size()));
				arlResultData.add(objReport);
				objReport = new CommonDataBean();
				System.out.println("=======================");
			}
			objSearchCollection = arlResultData;
		} catch (Exception e) {
			System.out
					.println("Error while retrieving the StatisticReports Search Info"
							+ e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving the StatisticReports Search Info"
							+ e);
		} finally {
			HibernetFactory.closeSession();
		}
		return objSearchCollection;
	}

	public Collection yearly(StatisticReportsSearchDto objSearchDto, int pageNo)
			throws TPlusException {
		Collection objSearchCollection = null;
		String strTime = "";
		CommonDataBean objReport = new CommonDataBean();
		ArrayList arlResultData = new ArrayList();
		String strN = "";
		String strP = "";
		String strC = "";
		String strDateRange = "";
		try {
			Session session = HibernetFactory.currentSession();
			String strSql = "select distinct to_char(claimDate,'YYYY') from DisputeClaimFormDto ";
			strSql += "where issuerId = '" + objSearchDto.getIssuerId() + "' ";
			strDateRange = setDateRangeHQL(objSearchDto);
			System.out.println("==========01===========");
			System.out.println(strSql+strDateRange);
			System.out.println("=======================");
			Query qry = session.createQuery(strSql+strDateRange);
			qry.setFirstResult(pageNo * 10);
			qry.setMaxResults(CommonDataBean.RECORDS_IN_PAGE);
			List list = qry.list();
			System.out.println("List => " + list.size());
			for (Iterator it = list.iterator(); it.hasNext();) {
				strTime = (String) it.next();
				System.out.println("String strTime => " + strTime);
				strN = "select status from DisputeClaimFormDto where status='N' and to_char(claimDate,'YYYY') = '"
						+ strTime + "' " + strDateRange;
				strP = "select status from DisputeClaimFormDto where status='P' and to_char(claimDate,'YYYY') = '"
						+ strTime + "' " + strDateRange;
				strC = "select status from DisputeClaimFormDto where status='C' and to_char(claimDate,'YYYY') = '"
						+ strTime + "' " + strDateRange;
				Query qryN = session.createQuery(strN);
				List listN = qryN.list();
				Query qryP = session.createQuery(strP);
				List listP = qryP.list();
				Query qryC = session.createQuery(strC);
				List listC = qryC.list();
				objReport.setColumn1(strTime);
				objReport.setColumn2(Integer.toString(listN.size()));
				objReport.setColumn3(Integer.toString(listP.size()));
				objReport.setColumn4(Integer.toString(listC.size()));
				arlResultData.add(objReport);
				objReport = new CommonDataBean();
				System.out.println("=======================");
			}
			objSearchCollection = arlResultData;
		} catch (Exception e) {
			System.out
					.println("Error while retrieving the StatisticReports Search Info"
							+ e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving the StatisticReports Search Info"
							+ e);
		} finally {
			HibernetFactory.closeSession();
		}
		return objSearchCollection;
	}
	
	public String setDateRangeHQL(StatisticReportsSearchDto objSearchDto)
	throws TPlusException {
		String strDateRange = "";
		System.out.println("objSearchDto.getStartDate() => "
				+ objSearchDto.getStrStartDate());
		System.out.println("objSearchDto.getEndDate() => "
				+ objSearchDto.getStrEndDate());
		
		if (objSearchDto.getStrStartDate() != null
				&& !objSearchDto.getStrStartDate().equals("")) {
			String strFromDate = objSearchDto.getStrStartDate();
			strDateRange = "and (claimDate >= TO_DATE('" + strFromDate
					+ "', 'DD/MM/YYYY')) ";
		}
		if (objSearchDto.getStrEndDate() != null
				&& !objSearchDto.getStrEndDate().equals("")) {
			String strToDate = objSearchDto.getStrEndDate();
			strDateRange += "and (claimDate <= TO_DATE('" + strToDate
					+ "', 'DD/MM/YYYY')) ";
		}
		return strDateRange;
	}
	
}
