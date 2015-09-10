package org.transinfo.cacis.controller.disputemanagement;

import java.util.Collection;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.disputemanagement.DocumentUploadDAO;
import org.transinfo.cacis.dto.disputemanagement.DocumentUploadDto;
import org.transinfo.cacis.dto.disputemanagement.DocumentUploadSearchDto;

public class DocumentUploadManager {

	private DocumentUploadDAO objDAO;

	public DocumentUploadManager() throws Exception {
		objDAO = DAOFactory.getInstance().getDocumentUploadDAO();
	}

	public Collection search(DocumentUploadSearchDto objSearchDto, int pageNo)
			throws TPlusException {
		Collection searchLst = null;
		try {
			searchLst = objDAO.search(objSearchDto, pageNo);

		} catch (Exception e) {
			System.out.println("Error while search operation" + e);
			throw new TPlusException("Error in DocumentUpload search method"
					+ e);
		}
		return searchLst;
	}

	public boolean upload(DocumentUploadDto objDto) throws TPlusException {
		boolean success = false;
		try {
			success = objDAO.upload(objDto);
		} catch (Exception e) {
			throw new TPlusException("Error in DocumentUpload upload method" + e);
		}
		return success;
	}

	public boolean remove(DocumentUploadDto objDto) throws TPlusException {
		boolean success = false;
		try {
			success = objDAO.remove(objDto);
		} catch (Exception e) {
			throw new TPlusException("Error in DocumentUpload delete method"
					+ e);
		}
		return success;
	}

	public Collection get(DocumentUploadDto objDto, int pageNo)
			throws TPlusException {
		Collection searchLst = null;
		try {
			searchLst = objDAO.getDocumentUpload(objDto, pageNo);
		} catch (Exception e) {
			System.out.println("Error while search operation" + e);
			throw new TPlusException("Error in DocumentUpload search method"
					+ e);
		}
		return searchLst;
	}

}
