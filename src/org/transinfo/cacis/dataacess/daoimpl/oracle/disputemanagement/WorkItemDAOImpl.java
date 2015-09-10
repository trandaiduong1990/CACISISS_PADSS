package org.transinfo.cacis.dataacess.daoimpl.oracle.disputemanagement;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.transinfo.cacis.TPlusCodes;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.common.CommonDataBean;
import org.transinfo.cacis.common.IdsGenartion;
import org.transinfo.cacis.common.constants.CommonCodes;
import org.transinfo.cacis.dataacess.HibernetDAO.HibernetFactory;
import org.transinfo.cacis.dataacess.dao.disputemanagement.WorkItemDAO;
import org.transinfo.cacis.dataacess.daoimpl.oracle.BaseDAOImpl;
import org.transinfo.cacis.dto.disputemanagement.DisputeFormRemarksDto;
import org.transinfo.cacis.dto.disputemanagement.DisputeResponseDocumentsDto;
import org.transinfo.cacis.dto.disputemanagement.RequestWorkItemFormDto;
import org.transinfo.cacis.dto.disputemanagement.SearchRequestWorkItemDto;
import org.transinfo.cacis.dto.disputemanagement.WorkItemDto;

public class WorkItemDAOImpl extends BaseDAOImpl implements WorkItemDAO {

	/*
	 * This method is used for getting the ClaimFomList page Data
	 */
 public Collection search(SearchRequestWorkItemDto objSearchDto,int pageNo)  throws TPlusException {

		   Collection objSearchCollection = new ArrayList();
		   StringBuffer sbf = new StringBuffer();
	try	{

		     sbf.append("select  dcf.cardNumber,dcf.claimNumber,dct.claimType ,drd.description,dcf.customerName,to_char(dcf.claimDate,'DD-MM-YYYY'), dsd.description ");
	 		 sbf.append("from DisputeClaimFormDto dcf,DisputeClaimTypesDto dct,DisputeReasonDto drd,DisputeStatusDto dsd ");
	 		 sbf.append("where ");
	 		 sbf.append("dcf.claimTypeId = dct.claimTypeId and ");
	 	     sbf.append("dcf.claimReasonCode = drd.code and ");
	 		 sbf.append("dcf.status = dsd.id ");
	 	 if(objSearchDto.getClaimNumber()!=null&&!objSearchDto.getClaimNumber().equals("")){
	        sbf.append("and dcf.claimNumber = '"+objSearchDto.getClaimNumber()+"' ");
		  }
		 if(objSearchDto.getCardNumber()>0){
	        sbf.append("and dcf.cardNumber  = "+objSearchDto.getCardNumber()+" ");
		 }
		 if(objSearchDto.getClaimDate()!=null&&!objSearchDto.getClaimDate().equals("")){
	        sbf.append("and to_date(to_char(dcf.claimDate,'DD/MM/YYYY'),'DD/MM/YYYY') = to_date('"+objSearchDto.getClaimDate()+"', 'DD/MM/YYYY')) ");
		  }
		// sbf.append("to_date(to_char(st.tranxDate,'DD/MM/YYYY'),'DD/MM/YYYY') = TO_DATE('"+objSearchDto.getTranxDate()+"', 'DD/MM/YYYY')) ");

	     objSearchCollection = getSearchList(sbf.toString(),pageNo);

		}catch (Exception e){

			System.out.println("Error in WorkItemDAOImpl search method : "+e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,"Error: in WorkItemDAOImpl search  method :"+e);

		}
 return objSearchCollection;

 }



 /*used to show  the claim form Edit Page Data*/
public ArrayList workItemInfo(RequestWorkItemFormDto objDto) throws TPlusException{

	 Transaction tx = null;
	 StringBuffer sbf = null;
	 CommonDataBean objCommBean =null;
	 ArrayList resultList = new ArrayList();
	try
	{

		 Session session = HibernetFactory.currentSession();
		             tx  = session.beginTransaction();

		   //for Claim History Details
		    ArrayList historyArl = new ArrayList();
		      sbf = new StringBuffer();
		      sbf.append("select drd.communicationType, to_char(drd.claimDate,'DD-MM-YYYY'),drd.remarks ,drd.action ");
		      sbf.append("From DisputeFormRemarksDto drd ");
		      sbf.append("where drd.claimNumber = '"+objDto.getClaimNumber()+"' ");

		  Query histQry = session.createQuery(sbf.toString());
		  List claimHistory = histQry.list();

	     for(Iterator it = claimHistory.iterator();it.hasNext();){

	    	  Object obj[]= (Object[])it.next();
			  objCommBean = new CommonDataBean();
			  objCommBean.setColumn1((String)obj[0]);
			  objCommBean.setColumn2((String)obj[1]);
			  objCommBean.setColumn3((String)obj[2]);
			  objCommBean.setColumn4(String.valueOf(obj[3]));
			 //to set the Action as Status
			 if((String.valueOf(obj[3]).trim()).equals(String.valueOf(CommonCodes.CLAIM_NEW))){
				    objCommBean.setColumn4("NEW");
			   }else if((String.valueOf(obj[3]).trim()).equals(String.valueOf(CommonCodes.CLAIM_PROCESS))){
				   objCommBean.setColumn4("PROCESSING");
			   }else  if((String.valueOf(obj[3]).trim()).equals(String.valueOf(CommonCodes.CLAIM_CLOSE))){
				   objCommBean.setColumn4("CLSOED");
			   }else  { objCommBean.setColumn4(""); }

			   historyArl.add(objCommBean);
	     }

	    //for ClaimDetails
	         sbf = new StringBuffer();
	         ArrayList claimArl = new ArrayList();
		     sbf.append("select  dcf.cardNumber,dcf.claimNumber,dct.claimType ,drd.description,dcf.customerName,to_char(dcf.claimDate,'DD-MM-YYYY'), dsd.description ");
	 		 sbf.append("from DisputeClaimFormDto dcf,DisputeClaimTypesDto dct,DisputeReasonDto drd,DisputeStatusDto dsd ");
	 		 sbf.append("where ");
	 		 sbf.append("dcf.claimTypeId = dct.claimTypeId and ");
	 	     sbf.append("dcf.claimReasonCode = drd.code and ");
	 		 sbf.append("dcf.status = dsd.id ");
	 	     sbf.append("and dcf.claimNumber = '"+objDto.getClaimNumber()+"' ");

	 	  Query claimQry = session.createQuery(sbf.toString());
		  List claimList = claimQry.list();

	      for(Iterator it = claimList.iterator();it.hasNext();){
		  	 Object obj[]= (Object[])it.next();
       	     objDto = new RequestWorkItemFormDto();
		     objDto.setCardNumber(String.valueOf(obj[0]));
		     objDto.setClaimNumber((String)obj[1]);
		     objDto.setClaimType((String)obj[2]);
		     objDto.setClaimReason((String)obj[3]);
		     objDto.setCustomerName((String)obj[4]);
		     objDto.setClaimDate((String)obj[5]);
		     objDto.setClaimStatus((String)obj[6]);

		     claimArl.add(objDto);

	      }

	      tx.commit();

	      //adding  result data to resuList
	       resultList.add(claimArl);
	       resultList.add(historyArl);
	}
	catch (Exception e)
	{
		if(tx!=null)
		{
			tx.rollback();
		}
	throw new TPlusException(TPlusCodes.APPLICATION_ERROR,"Error: in workItemInfo method WorkItemDAOImpl"+e);
	}
	finally
	{
		HibernetFactory.closeSession();
	}
return resultList;
}

//Hee leng
	public boolean add(WorkItemDto objDto) throws TPlusException {
		boolean bolExecute = false;
		Transaction tx = null;
		DisputeResponseDocumentsDto objUpdate2Dto = new DisputeResponseDocumentsDto();
		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			objDto.setRemarksId(IdsGenartion.GenDocumentId());
			objDto.setLastUpdatedDate(new Date());
			DisputeFormRemarksDto objUpdateDto = setDisputeFormRemarksDto(objDto);
			session.save(objUpdateDto);
			Map DocumentsNameList = new HashMap();
			DocumentsNameList = objDto.getDocumentNameList();
			Iterator it = DocumentsNameList.keySet().iterator();
			System.out.println(it.getClass());
			while (it.hasNext()) {
				Object Key = it.next();
				System.out.println("DocumentId => " + Key.toString());
				System.out.println("DocumentName => "
						+ DocumentsNameList.get(Key).toString());
				// Response Documents is different from request documents
				// for response documents, the document id is the document name.
				// objDto.setDocumentId(Key.toString());
				// objDto.setDocumentName(DocumentsNameList.get(Key).toString());
				objDto.setDocumentId(DocumentsNameList.get(Key).toString());
				objUpdate2Dto = setDisputeResponseDocumentsDto(objDto);
				session.save(objUpdate2Dto);
			}
			session.flush();
			tx.commit();
			System.out.println("WorkItemDto object add to the database.");
			bolExecute = true;
		}

		catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.out.println("Error while adding WorkItem data " + e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while adding WorkItem data" + e);
		} finally {
			HibernetFactory.closeSession();
		}

		return bolExecute;
	}


	public DisputeResponseDocumentsDto setDisputeResponseDocumentsDto(
			WorkItemDto objDto) throws TPlusException {
		DisputeResponseDocumentsDto objDrdDto = new DisputeResponseDocumentsDto();
		//org.transinfo.cacis.dto.disputemanagement.DisputeResponseDocumentsDto.Id dtoId = new org.transinfo.cacis.dto.disputemanagement.DisputeResponseDocumentsDto.Id();
		org.transinfo.cacis.dto.disputemanagement.DisputeResponseDocumentsDto.Id dtoId = objDrdDto.getId();
		dtoId.setClaimNumber(objDto.getClaimNumber());
		dtoId.setDocumentId(objDto.getDocumentId());
		objDrdDto.setId(dtoId);
		System.out.println(objDrdDto.getId().getClaimNumber());
		System.out.println(objDrdDto.getId().getDocumentId());
		objDrdDto.setDocsUploaded('N');
		objDrdDto.setDocsUploadedDate(null);
		objDrdDto.setRemarksId(objDto.getRemarksId());
		objDrdDto.setUpdatedDate(objDto.getLastUpdatedDate());
		objDrdDto.setUserId(objDto.getUserId());
		return objDrdDto;
	}

	public DisputeFormRemarksDto setDisputeFormRemarksDto(WorkItemDto objDto)
			throws TPlusException {
		DisputeFormRemarksDto objDfrDto = new DisputeFormRemarksDto();
		objDfrDto.setRemarksId(objDto.getRemarksId());
		objDfrDto.setClaimNumber(objDto.getClaimNumber());
		objDfrDto.setCommunicationType("RESPONSE");
		objDfrDto.setRemarks(objDto.getRemarks());
		objDfrDto.setClaimDate(new Date());
		return objDfrDto;
	}

	public int checkExistRecord(WorkItemDto objDto) throws TPlusException {
		int res = 0;
		try {
			Session session = HibernetFactory.currentSession();
			Query qry = session
					.createQuery("select count(*) from DisputeResponseDocumentsDto drd where drd.id.documentId=:documentid and drd.id.claimNumber=:claimnumber");
			qry.setString("documentid", objDto.getDocumentName());
			qry.setString("claimnumber", objDto.getClaimNumber());
			List list = qry.list();
			res = ((Integer) list.get(0)).intValue();
			System.out
					.println("After WorkItemDAOImpl checkExistRecord()");
		} catch (Exception e) {
			System.out.println("6" + e);
		} finally {
			HibernetFactory.closeSession();
		}
		return res;
	}


	/*
	 * This method is used for getting the DocumwentList
	 */
 public Collection getDocumentList(String claimNo)  throws TPlusException {

		   ArrayList arlResultData = new ArrayList();
		   StringBuffer sbf = new StringBuffer();

	try	{


		 //sbf.append(" select dfr.id.remarksId,dd.documentName,dfr.communicationType,drd.docsUploaded,dfr.claimDate");
		/* sbf.append(" select * from(");
		 select * from(

select dfr.remarks_id,Document_name,comm_type as Claimtype, document_uploaded,claim_date
from dispute_request_documents drd,dispute_documents dd,dispute_form_remarks dfr
where
drd.document_id= dd.document_id
and drd.remarks_id = dfr.remarks_id
and drd.claim_no='DC060821462'
union
select dfr.remarks_id,Document_id,comm_type as Claimtype,document_uploaded,claim_date from dispute_response_documents drd ,dispute_form_remarks dfr
where
 drd.remarks_id = dfr.remarks_id
and drd.claim_no='DC060821462')

order by claim_dateselect dfr.id.remarksId,dd.documentName,dfr.communicationType,drd.docsUploaded,dfr.claimDate");
		 sbf.append(" from DisputeRequestDocumentsDto drd, DisputeDocumentsDto dd,DisputeFormRemarksDto dfr ");
		 sbf.append(" where");
		 sbf.append(" drd.id.documentId= dd.id.documentId ");
		 sbf.append(" and drd.remarksId = dfr.id.remarksId");
		 sbf.append(" and drd.id.claimNumber='"+cliamNo+"'");
		 sbf.append(" union");
		 sbf.append(" select dfr.id.remarksId,drd.id.documentId,dfr.communicationType,drd.docsUploaded,dfr.claimDate");
		 sbf.append(" from DisputeResponseDocumentsDto drd ,DisputeFormRemarksDto dfr");
		 sbf.append(" where");
		 sbf.append(" drd.remarksId = dfr.id.remarksId");
		 sbf.append(" and drd.id.claimNumber='"+cliamNo+"'");
		 sbf.append(" order by dfr.claimDate");*/


		sbf.append(" select * from( ");
		sbf.append(" select dfr.remarks_id,comm_type as Claimtype, document_uploaded,claim_date,Document_name ");
		sbf.append(" from dispute_request_documents drd,dispute_documents dd,dispute_form_remarks dfr");
		sbf.append(" where");
		sbf.append(" drd.document_id= dd.document_id ");
		sbf.append(" and drd.remarks_id = dfr.remarks_id");
		sbf.append(" and drd.claim_no='"+claimNo+"'");
		sbf.append(" union");
		sbf.append(" select dfr.remarks_id,comm_type as Claimtype,document_uploaded,claim_date,Document_id ");
		sbf.append(" from dispute_response_documents drd ,dispute_form_remarks dfr");
		sbf.append(" where");
		sbf.append(" drd.remarks_id = dfr.remarks_id");
		sbf.append(" and drd.claim_no='"+claimNo+"')");
		sbf.append(" order by claim_date");

		System.out.println("SQL="+sbf.toString());

		Session session = HibernetFactory.currentSession();
		Connection con = session.connection();
		Statement st = con.createStatement();

		ResultSet rs = st.executeQuery(sbf.toString());


	       CommonDataBean objReport = new CommonDataBean();
	       Class adminReportClass = objReport.getClass();
           Object[] arrObj = new Object[1];
	       Class[] arrClass = new Class[1];
	       arrClass[0] = new String("").getClass();
	       Method method = null;
	       int colCount = 0;

		while(rs.next())
		{
			int intCount = 0;
			while (intCount < 5)
			{
				arrObj[0]= rs.getString(intCount+1);
				method = adminReportClass.getMethod("setColumn"
						+ (intCount + 1), arrClass);
				 method.invoke(objReport, arrObj);
				 intCount++;
			}
			arlResultData.add(objReport);
			objReport = new CommonDataBean();
		}


		}catch (Exception e){

			System.out.println("Error in WorkItemDAOImpl search method : "+e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,"Error: in WorkItemDAOImpl search  method :"+e);

		}
 return arlResultData;

 }


public static void main(String s[])throws Exception
{

		WorkItemDAOImpl widi = new WorkItemDAOImpl();
		ArrayList arrList =(ArrayList)widi.getDocumentList("DC060821462");
		System.out.println("List size="+arrList.size());
		CommonDataBean cdb = (CommonDataBean)arrList.get(0);
		System.out.println(cdb.getColumn1());
}

}


