package org.transinfo.cacis.controller.authorization;

import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.authorization.SystemParamDAO;
import org.transinfo.cacis.dto.authorization.SystemParamDto;

/**
 * A Business Delegate that abstracts out the business specific calls and
 * exceptions . The web tier does not need to know about the business-specific
 * details
 **/
public class SystemParamManager {

	private SystemParamDAO objSystemParamDAO;

	public SystemParamManager() throws Exception {
		objSystemParamDAO = DAOFactory.getInstance().getSystemParamDAO();
	}

	public SystemParamDto get(String issuerid) throws TPlusException {
		SystemParamDto sysParamDto = null;
		try {
			sysParamDto = objSystemParamDAO.get(issuerid);

		} catch (Exception e) {
			throw new TPlusException("Error in manager get method" + e);
		}
		return sysParamDto;
	}

	public boolean add(SystemParamDto objDto) throws TPlusException {

		boolean success = false;
		try {
			success = objSystemParamDAO.add(objDto);
		} catch (Exception e) {
			throw new TPlusException("Error in Add method");
		}
		return success;
	}

	public boolean update(SystemParamDto objDto) throws TPlusException {
		boolean success = false;
		try {
			success = objSystemParamDAO.update(objDto);
		} catch (Exception e) {
			throw new TPlusException("Error in SystemParamForm update method"
					+ e);
		}
		return success;
	}

	public static void main(String s[]) throws Exception {

		SystemParamManager sysMgr = new SystemParamManager();
		SystemParamDto objDto = new SystemParamDto();

		objDto.setIssuerId("ASP");
		objDto.setMailServerAddress("MAIL.CC");
		objDto.setMailServerPort(2);
		objDto.setFromName("MAIL1");
		objDto.setFromMail("FMAIL");
		objDto.setVoicePort(2);
		objDto.setServerPort(2);
		objDto.setDataTimeout(2);
		objDto.setNoWorkerThread(2);
		objDto.setDebugLevel(2);
		objDto.setUpdatedDate(objDto.getUpdatedDate());
		objDto.setUserId("RAMESH");

		try {
			sysMgr.add(objDto);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}