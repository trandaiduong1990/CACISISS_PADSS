package org.transinfo.cacis.dataacess.daoimpl.oracle.disputemanagement;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.transinfo.cacis.TPlusCodes;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.HibernetDAO.HibernetFactory;
import org.transinfo.cacis.dataacess.dao.disputemanagement.DocumentUploadDAO;
import org.transinfo.cacis.dataacess.daoimpl.oracle.BaseDAOImpl;
import org.transinfo.cacis.dto.disputemanagement.DisputeDocumentsDto;
import org.transinfo.cacis.dto.disputemanagement.DisputeRequestDocumentsDto;
import org.transinfo.cacis.dto.disputemanagement.DocumentUploadDto;
import org.transinfo.cacis.dto.disputemanagement.DocumentUploadSearchDto;

public class DocumentUploadDAOImpl extends BaseDAOImpl implements
		DocumentUploadDAO {

	public Collection search(DocumentUploadSearchDto objSearchDto, int pageNo)
			throws TPlusException {

		Collection objSearchCollection = null;
		StringBuffer sbf = new StringBuffer();
		try {
			sbf
					.append("select af.claimNumber, to_char(af.claimDate,'dd-MM-yyyy'), af.cardNumber, af.customerName, af.claimReasonCode FROM DisputeClaimFormDto af ");
			sbf.append("where af.issuerId = '" + objSearchDto.getIssuerId()
					+ "' ");

			if (objSearchDto.getCardNumber() != 0) {
				sbf.append("and af.cardNumber = "
						+ objSearchDto.getCardNumber() + " ");
			}
			if (!objSearchDto.getClaimNumber().trim().equals("")) {
				sbf.append("and af.claimNumber = '"
						+ objSearchDto.getClaimNumber() + "' ");
			}
			System.out.println("objSearchDto.getClaimDate() => "
					+ objSearchDto.getClaimDate());
			if (objSearchDto.getClaimDate() != null
					&& !objSearchDto.getClaimDate().equals("")) {
				int dayOfDate = objSearchDto.getClaimDate().getDate();
				int monthOfDate = objSearchDto.getClaimDate().getMonth() + 1;
				int yearOfDate = objSearchDto.getClaimDate().getYear() + 1900;
				String strDate = dayOfDate + "/" + monthOfDate + "/"
						+ yearOfDate;
				sbf
						.append("and to_date(to_char(af.claimDate,'DD/MM/YYYY'),'DD/MM/YYYY') = to_date('"
								+ strDate + "', 'DD/MM/YYYY')) ");
			}
			objSearchCollection = getSearchList(sbf.toString(), pageNo);
		} catch (Exception e) {
			System.out
					.println("Error while retrieving the DocumentUpload Search Info"
							+ e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving the DocumentUpload Search Info"
							+ e);
		} finally {
			HibernetFactory.closeSession();
		}
		return objSearchCollection;
	}

	public boolean upload(DocumentUploadDto objDto) throws TPlusException {
		boolean bolExecute = false;
		Transaction tx = null;
		try {
			Date uploadDate = objDto.getDocsUploadedDate();
			String strUploadDate = new SimpleDateFormat("ddMMyy")
					.format(uploadDate);
			String filename = objDto.getUploadFile().getFileName();
			System.out.println("filename = " + filename);

			String parent = UPLOAD_DOCUMENTS_PATH + objDto.getClaimNumber()
					+ "\\" + strUploadDate + "\\request";
			String fullFileName = parent + "\\" + filename;
			File outputFolder = new File(parent);
			if (!outputFolder.exists()) {
				outputFolder.mkdirs();
			}

			File outputFile = new File(fullFileName);
			System.out.println("fullFileName => " + fullFileName);
			InputStream in = objDto.getUploadFile().getInputStream();
			OutputStream out = new FileOutputStream(outputFile);

			// Transfer bytes from in to out
			byte[] buf = new byte[1024];
			int len;
			while ((len = in.read(buf)) > 0) {
				out.write(buf, 0, len);
			}
			in.close();
			out.close();

			if (outputFile.exists())
				objDto.setDocsUploaded('Y');

			DisputeRequestDocumentsDto objUpdateDto = setDisputeRequestDocuments(objDto);
			DisputeDocumentsDto objUpdate2Dto = setDisputeDocuments(objDto);

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			session.update(objUpdateDto);
			session.update(objUpdate2Dto);

			/*
			 * Has errors using HQL to update with compasite key... String
			 * UpdateDRDSql = "UPDATE DisputeRequestDocumentsDto SET
			 * docsUploaded=:uploadedstatus, docsUploadedDate=:uploadeddate
			 * WHERE id.claimNumber=:claimnumber and id.documentId=:documentid";
			 * UpdateCount = session.createQuery(UpdateDRDSql).setCharacter(
			 * "uploadedstatus", 'Y').setDate("uploadeddate",
			 * uploadDate).setString("claimnumber",
			 * objDto.getClaimNumber()).setString("documentid",
			 * objDto.getDocumentId()).executeUpdate(); String UpdateDDSql =
			 * "UPDATE DisputeDocumentsDto SET documentName =:documentname WHERE
			 * id.issuerId=:issuerid and id.reasonCode=:reasoncode and
			 * id.documentId=:documentid"; UpdateCount =
			 * session.createQuery(UpdateDDSql).setString( "documentname",
			 * documentname).setString("issuerid",
			 * objDto.getIssuerId()).setString("reasoncode",
			 * objDto.getReasonCode()).setString("documentid",
			 * objDto.getDocumentId()).executeUpdate();
			 */
			session.flush();
			bolExecute = true;
			tx.commit();
			System.out
					.println("DocumentUploadDto object persisted to the database.");
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.out.println("Exception in DocumentUploadDAOImpl saveMethod"
					+ e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while saving data in DocumentUploadDAOImpl" + e);
		} finally {
			HibernetFactory.closeSession();
		}
		return bolExecute;
	}

	public boolean remove(DocumentUploadDto objDto) throws TPlusException {
		boolean bolExecute = false;
		boolean bolDelete = false;
		Transaction tx = null;
		try {

			System.out.println(objDto.getDocsUploadedDate());
			System.out.println(objDto.getClaimNumber());
			System.out.println(objDto.getDocumentId());
			System.out.println(objDto.getIssuerId());
			System.out.println(objDto.getReasonCode());

			String filename = objDto.getDocumentName();
			Date uploadDate = objDto.getDocsUploadedDate();
			String strUploadDate = new SimpleDateFormat("ddMMyy")
					.format(uploadDate);
			String parent = UPLOAD_DOCUMENTS_PATH + objDto.getClaimNumber()
					+ "\\" + strUploadDate + "\\request";
			String fullFileName = parent + "\\" + filename;
			File file = new File(fullFileName);

			if (file.exists())
				bolDelete = file.delete();
			else
				bolDelete = true;

			if (bolDelete) {
				objDto.setDocsUploadedDate(null);
				objDto.setDocsUploaded('N');
				objDto.setUploadFile(null);
			}
			DisputeRequestDocumentsDto objUpdateDto = setDisputeRequestDocuments(objDto);
			DisputeDocumentsDto objUpdate2Dto = setDisputeDocuments(objDto);

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			session.update(objUpdateDto);
			session.update(objUpdate2Dto);

			session.flush();
			bolExecute = true;
			tx.commit();
			System.out
					.println("DocumentUploadDto object persisted to the database.");

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.out.println("Exception in DocumentUploadDAOImpl saveMethod"
					+ e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while saving data in DocumentUploadDAOImpl" + e);
		} finally {
			HibernetFactory.closeSession();
		}
		return bolExecute;
	}

	public Collection getDocumentUpload(DocumentUploadDto objDto, int pageNo)
			throws TPlusException {
		Collection objSearchCollection = null;
		StringBuffer sbf = new StringBuffer();
		try {
			sbf
					.append("select drd.id.documentId, dd.documentName, drd.docsUploaded, to_char(drd.docsUploadedDate,'dd/MM/yyyy'), ");
			sbf
					.append("dcf.claimNumber, dcf.cardNumber, dcf.customerName, to_char(dcf.claimDate,'dd-MM-yyyy'), dcf.claimReasonCode ");
			sbf
					.append("FROM DisputeRequestDocumentsDto drd, DisputeDocumentsDto dd, DisputeClaimFormDto dcf ");
			sbf
					.append("where dd.id.issuerId = '" + objDto.getIssuerId()
							+ "' ");
			sbf.append("and dcf.claimReasonCode = dd.id.reasonCode ");
			sbf.append("and drd.id.documentId = dd.id.documentId ");
			sbf.append("and drd.id.claimNumber = '" + objDto.getClaimNumber()
					+ "' ");
			sbf.append("and drd.id.claimNumber = dcf.claimNumber ");
			objSearchCollection = getSearchList(sbf.toString(), pageNo);
		} catch (Exception e) {
			System.out
					.println("Error while retrieving the DocumentUpload get Info"
							+ e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving the DocumentUpload get Info" + e);
		} finally {
			HibernetFactory.closeSession();
		}
		return objSearchCollection;
	}

	public DisputeRequestDocumentsDto setDisputeRequestDocuments(
			DocumentUploadDto objDto) throws TPlusException {
		DisputeRequestDocumentsDto objDrdDto = new DisputeRequestDocumentsDto();
		org.transinfo.cacis.dto.disputemanagement.DisputeRequestDocumentsDto.Id dtoId = new org.transinfo.cacis.dto.disputemanagement.DisputeRequestDocumentsDto.Id();
		dtoId.setClaimNumber(objDto.getClaimNumber());
		dtoId.setDocumentId(objDto.getDocumentId());
		objDrdDto.setId(dtoId);
		objDrdDto.setDocsUploaded(objDto.getDocsUploaded());
		objDrdDto.setDocsUploadedDate(objDto.getDocsUploadedDate());
		objDrdDto.setUpdatedDate(objDto.getLastUpdatedDate());
		objDrdDto.setUserId(objDto.getUserId());
		return objDrdDto;
	}

	public DisputeDocumentsDto setDisputeDocuments(DocumentUploadDto objDto)
			throws TPlusException {
		DisputeDocumentsDto objDdDto = new DisputeDocumentsDto();
		org.transinfo.cacis.dto.disputemanagement.DisputeDocumentsDto.Id dtoId2 = new org.transinfo.cacis.dto.disputemanagement.DisputeDocumentsDto.Id();
		dtoId2.setDocumentId(objDto.getDocumentId());
		dtoId2.setIssuerId(objDto.getIssuerId());
		dtoId2.setReasonCode(Integer.parseInt(objDto.getReasonCode()));
		objDdDto.setId(dtoId2);
		if (objDto.getUploadFile() == null)
			objDdDto.setDocumentName(objDto.getDocumentName());
		else
			objDdDto.setDocumentName(objDto.getUploadFile().getFileName());
		objDdDto.setDocumentType(objDto.getDocumentType());
		return objDdDto;
	}
}
