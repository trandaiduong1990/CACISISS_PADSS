package org.transinfo.cacis.dataacess.daoimpl.oracle.applicationforms;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.transinfo.cacis.TPlusCodes;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.common.IdsGenartion;
import org.transinfo.cacis.common.constants.CommonCodes;
import org.transinfo.cacis.dataacess.HibernetDAO.HibernetFactory;
import org.transinfo.cacis.dataacess.dao.applicationforms.SupplFormDAO;
import org.transinfo.cacis.dataacess.daoimpl.oracle.BaseDAOImpl;
import org.transinfo.cacis.dto.applicationforms.SupplementaryFormDto;
import org.transinfo.cacis.dto.applicationforms.SupplementaryFormSearchDto;
import org.transinfo.cacis.dto.cardproduction.ApplicationProcessDto;
import org.transinfo.cacis.dto.cardproduction.CardEmbossingDto;
import org.transinfo.cacis.dto.cardproduction.CardsDto;
import org.transinfo.cacis.dto.cardproduction.CustomerAddressDto;
import org.transinfo.cacis.dto.common.ApplicationMasterDto;
import org.transinfo.cacis.dto.customerservice.CardActivityDto;

@SuppressWarnings( { "unused", "unchecked" })
public class SupplFormDAOImpl extends BaseDAOImpl implements SupplFormDAO {

	public Collection search(SupplementaryFormSearchDto objSearchDto, int pageNo)
			throws TPlusException {

		Collection objSearchCollection = null;
		StringBuffer sbf = new StringBuffer();
		try {

			sbf.append("select af.applicationId,af.principleChName,af.principleNRIC,");
			sbf.append("to_char(af.lastUpdatedDate,'dd-MM-yyyy') FROM  SupplementaryFormDto af ");
			sbf.append("where (af.applStatus = " + CommonCodes.APPLICATIONSTATUS_NEW + " OR af.applStatus = " + CommonCodes.APPLICATIONSTATUS_PENDING + ") ");

			if (objSearchDto.getPrincipleChName() != null && !objSearchDto.getPrincipleChName().equals("")) {
				sbf.append("and (af.principleChSurname like '%" + objSearchDto.getPrincipleChName() + "%' ");
				sbf.append(" OR af.principleChName like '%" + objSearchDto.getPrincipleChName() + "%' ) ");
			}
			if (objSearchDto.getPrincipleNRIC() != null && !objSearchDto.getPrincipleNRIC().equals("")) {
				sbf.append("and af.principleNRIC ='" + objSearchDto.getPrincipleNRIC() + "'");				
			}

			objSearchCollection = getSearchList(sbf.toString(), pageNo);

		} catch (Exception e) {
			System.out.println("Error while retrieving the Supplementary Search Info" + e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR, "Error: while retrieving the Supplementary Search Info" + e);
		} finally {
			HibernetFactory.closeSession();
		}
		return objSearchCollection;
	}

	public SupplementaryFormDto getSupplementaryForm(String applicationId)
			throws TPlusException {
		SupplementaryFormDto apd = null;
		Transaction tx = null;
		try {

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			apd = (SupplementaryFormDto) session.get(
					SupplementaryFormDto.class, applicationId);
			tx.commit();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}

			System.out.println("Error in getSupplementaryForm method" + e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving the getSupplementaryForm" + e);
		} finally {
			HibernetFactory.closeSession();
		}
		return apd;

	}

	public boolean add(SupplementaryFormDto objSupplFormDto)
			throws TPlusException {
		boolean bolExecute = false;
		Transaction tx = null;
		ApplicationMasterDto objAppMaster = null;
		CardsDto objCardsDto = null;
		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			
			objCardsDto = (CardsDto)session.get(CardsDto.class, objSupplFormDto.getPrincipleChCardNo());

			// inserting into Application_master Table
			objAppMaster = new ApplicationMasterDto();
			objAppMaster.setApplicationId(objSupplFormDto.getApplicationId());
			objAppMaster.setIssuerId(objSupplFormDto.getIssuerId());
			objAppMaster.setApplicationStatus(objSupplFormDto.getApplStatus());
			objAppMaster.setApplicationType(objSupplFormDto.getApplType());
			objAppMaster.setIdNumber(objSupplFormDto.getIdNumber());
			objAppMaster.setOpenDate(objSupplFormDto.getOpenDate());
			objAppMaster.setUserId(objSupplFormDto.getUserId());
			objAppMaster.setUpdatedDate(objSupplFormDto.getLastUpdatedDate());

			session.save(objAppMaster);

			// inserting Supply_forms table
			objSupplFormDto.setCardProductId(objCardsDto.getCardProductId());
			session.save(objSupplFormDto);

			session.flush();
			tx.commit();
			bolExecute = true;
			System.out
					.println("SupplementaryFormDto object persisted to the database.");

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.out
					.println("Exception in ApplicationProcessDAOImpl saveMethod"
							+ e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while saving data in SupplementaryForm" + e);
		} finally {
			HibernetFactory.closeSession();
		}
		return bolExecute;

	}

	public boolean update(SupplementaryFormDto objSupplFormDto)
			throws TPlusException {
		boolean bolExecute = false;
		Transaction tx = null;
		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			session.update(objSupplFormDto);
			session.flush();
			tx.commit();
			System.out
					.println("SupplementaryFormDto object updated to the database.");
			bolExecute = true;
		}

		catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.out.println("Error while updating SupplementaryForm data "
					+ e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while updating SupplementaryForm data" + e);

		} finally {
			HibernetFactory.closeSession();
		}

		return bolExecute;
	}

	public boolean accept(SupplementaryFormDto objSupplFormDto)
			throws TPlusException {

		boolean accept = false;
		Transaction tx = null;
		int count;
		CardsDto objCardsDto = null;
		CardEmbossingDto objCardEb = null;
		CardActivityDto objCardActivity = null;
		//DispatchLetterSearchDto objDispLetters = null;
		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			// updating SupplementaryFormDto
			String sql = "UPDATE SupplementaryFormDto SET applicationStatus =:appacceptstatus  WHERE applicationId=:applicationid";
			count = session.createQuery(sql).setString("applicationid",
					objSupplFormDto.getApplicationId()).setInteger(
					"appacceptstatus", objSupplFormDto.getApplStatus())
					.executeUpdate();

			// updating in Application_master table
			String appMtsql = "UPDATE ApplicationMasterDto  SET applicationStatus =:appacceptstatus,closeDate =:closedate WHERE applicationId=:applicationid";
			count = session.createQuery(appMtsql).setString("applicationid",
					objSupplFormDto.getApplicationId()).setInteger(
					"appacceptstatus", objSupplFormDto.getApplStatus())
					.setDate("closedate", objSupplFormDto.getCloseDate())
					.executeUpdate();

			if (count > 0) {
				
				CardsDto objCardsDto2 = (CardsDto)session.get(CardsDto.class, objSupplFormDto.getPrincipleChCardNo());
				ApplicationProcessDto objApplicationProcessDtoPrimary = (ApplicationProcessDto)session.get(ApplicationProcessDto.class, objCardsDto2.getCustomerId());
				
				ApplicationProcessDto objApplicationProcessDto = new ApplicationProcessDto();
				objApplicationProcessDto.setCustomerId(IdsGenartion.GenerateCustomerId());
				objApplicationProcessDto.setApplicationId(objSupplFormDto.getApplicationId());
				objApplicationProcessDto.setIssuerId(objSupplFormDto.getIssuerId());
				objApplicationProcessDto.setCustomerStatus(1);
				objApplicationProcessDto.setBranchId(objSupplFormDto.getBranchId());
				objApplicationProcessDto.setCustomerName(objSupplFormDto.getCustomerName());
				objApplicationProcessDto.setSurName(objSupplFormDto.getSurnameName());
				objApplicationProcessDto.setEmbossingName(objSupplFormDto.getEmbossingName());
				objApplicationProcessDto.setCustomerTypeId(objApplicationProcessDtoPrimary.getCustomerTypeId());
				objApplicationProcessDto.setDob(objSupplFormDto.getDob());
				objApplicationProcessDto.setPob(objSupplFormDto.getPob());
				objApplicationProcessDto.setGender(objSupplFormDto.getGender());
				objApplicationProcessDto.setMaritalStatus(objSupplFormDto.getMaritalStatus());
				objApplicationProcessDto.setIdNumber(objSupplFormDto.getIdNumber());
				objApplicationProcessDto.setIdDate(objSupplFormDto.getIdDate());
				objApplicationProcessDto.setExpDate(objSupplFormDto.getIdExpire());
				objApplicationProcessDto.setIdPlace(objSupplFormDto.getIdPlace());
				objApplicationProcessDto.setNationality(objSupplFormDto.getNationality());
				objApplicationProcessDto.setEmail(objSupplFormDto.getEmail());
				objApplicationProcessDto.setIncome(objSupplFormDto.getIncome());
				objApplicationProcessDto.setUpdatedDate(new Date());
				objApplicationProcessDto.setUserId(objSupplFormDto.getUserId());
				objApplicationProcessDto.setParenetCustomerId(objApplicationProcessDtoPrimary.getCustomerId());
				objApplicationProcessDto.setSelectedAppCardProducts(objSupplFormDto.getCardProductId());
				objApplicationProcessDto.setBillingTo("S");
				//objApplicationProcessDto.getCustomerAccount().add(objAppProcessDto.getCustomerAccount());
				
				CustomerAddressDto objCustomerAddressDto = new CustomerAddressDto();
				objCustomerAddressDto.setAddressType("S");
				objCustomerAddressDto.setAddress1(objSupplFormDto.getAddress().getAddress1());
				objCustomerAddressDto.setAddress2(objSupplFormDto.getAddress().getAddress2());
				objCustomerAddressDto.setCity(objSupplFormDto.getAddress().getCity());
				objCustomerAddressDto.setState(objSupplFormDto.getAddress().getState());
				objCustomerAddressDto.setCountry(objSupplFormDto.getAddress().getCountry());
				objCustomerAddressDto.setPostalCode(objSupplFormDto.getAddress().getPostalCode());
				objCustomerAddressDto.setPhone(objSupplFormDto.getAddress().getPhone());
				objCustomerAddressDto.setFax(objSupplFormDto.getAddress().getFax());
				
				objApplicationProcessDto.getApplicationAddress().add(objCustomerAddressDto);
				
				session.save(objApplicationProcessDto);

				// Inserting Data into CardActivity Table
				objCardActivity = new CardActivityDto();
				objCardActivity.setDateTime(new Date());
				objCardActivity.setCardNumber(objSupplFormDto
						.getPrincipleChCardNo());
				objCardActivity
						.setActivity("Supplementary Card Application Acccepted");
				objCardActivity.setStationIp(java.net.InetAddress
						.getLocalHost().getHostAddress());
				objCardActivity.setUserId(objSupplFormDto.getUserId());
				objCardActivity.setReason(objSupplFormDto.getRemarks());
				objCardActivity.setLastUpdatedBy(objSupplFormDto.getUserId());
				objCardActivity.setUpdatedDate(objSupplFormDto
						.getLastUpdatedDate());

				session.save(objCardActivity);
			}
			session.flush();
			// tx.rollback();
			tx.commit();
			if (count > 0)
				accept = true;

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.out
					.println("Error while accepting the  data in SupplementaryFormDAOImpl AcceptMethod"
							+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while accepting the SupplementaryForm in SupplementaryFormDAOImpl"
							+ e);
		} finally {

			HibernetFactory.closeSession();
		}
		return accept;
	}

	public boolean reject(SupplementaryFormDto objSupplFormDto)
			throws TPlusException {
		boolean bolExecute = false;
		Transaction tx = null;
		CardActivityDto objCardActivity = null;
		//DispatchLetterSearchDto objDispLetters = null;
		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			String sql = "UPDATE SupplementaryFormDto SET applStatus =:rejectedstatus WHERE applicationId=:applicationid";
			int count = session.createQuery(sql).setInteger("rejectedstatus",
					objSupplFormDto.getApplStatus()).setString("applicationid",
					objSupplFormDto.getApplicationId()).executeUpdate();

			if (count > 0) {

				// inserting into LettersDispatch table
				/*objDispLetters = new DispatchLetterSearchDto();
				objDispLetters.setDispatchId(IdsGenartion.GenLetterDispId());
				objDispLetters
						.setLetterId(CommonCodes.NEWSUPPCARD_REJECT_APPLICATION);
				objDispLetters.setApplicationId(objSupplFormDto
						.getApplicationId());
				objDispLetters.setCardNumber(objSupplFormDto
						.getPrincipleChCardNo());
				objDispLetters.setIssuerId(objSupplFormDto.getIssuerId());
				objDispLetters.setStatus(CommonCodes.CARD_PROCESS_NEW);
				objDispLetters.setLastUpdateDate(objSupplFormDto
						.getLastUpdatedDate());
				objDispLetters.setLastUpdatedBy(objSupplFormDto.getUserId());

				session.save(objDispLetters);*/

				// Inserting Data into CardActivity Table
				objCardActivity = new CardActivityDto();
				objCardActivity.setDateTime(new Date());
				objCardActivity.setCardNumber(objSupplFormDto
						.getPrincipleChCardNo());
				objCardActivity
						.setActivity("Supplementary Card Application Rejected");
				objCardActivity.setStationIp(java.net.InetAddress
						.getLocalHost().getHostAddress());
				objCardActivity.setUserId(objSupplFormDto.getUserId());
				objCardActivity.setReason(objSupplFormDto.getRemarks());
				objCardActivity.setLastUpdatedBy(objSupplFormDto.getUserId());
				objCardActivity.setUpdatedDate(objSupplFormDto
						.getLastUpdatedDate());

				session.save(objCardActivity);
			}
			tx.commit();
			if (count > 0)
				bolExecute = true;

		}

		catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.out.println("Error while rejecting SupplementaryForm " + e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while rejecting SupplementaryForm" + e);

		} finally {
			HibernetFactory.closeSession();
		}

		return bolExecute;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.transinfo.cacis.dataacess.dao.applicationforms.SupplFormDAO
	 * #getCardsDto(long) this method is for getting the Cards object to assign
	 * the primary cardholder account details to newly created supplementary
	 * card
	 */
	public CardsDto getCardsDto(long primaryCardNo) throws TPlusException {

		CardsDto cardsDto = null;
		Transaction tx = null;
		try {

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			cardsDto = (CardsDto) session.get(CardsDto.class, new Long(
					primaryCardNo));
			tx.commit();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}

			System.out
					.println("Error  in SupplformsDAOIMPL in getCardsDto method"
							+ e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: Error  in SupplformsDAOIMPL in getCardsDto method"
							+ e);
		} finally {
			HibernetFactory.closeSession();
		}
		return cardsDto;

	}

	public int checkExistrecord(Object commonObj) throws TPlusException {

		int res = 0;
		try {
			StringBuffer sbf = new StringBuffer();
			Session session = HibernetFactory.currentSession();

			if (commonObj instanceof SupplementaryFormDto) {
				SupplementaryFormDto objDto = (SupplementaryFormDto) commonObj;
				sbf
						.append("select count(*) from SupplementaryFormDto sfd where sfd.applicationId='"
								+ objDto.getApplicationId() + "' ");
			} else if (commonObj instanceof ApplicationMasterDto) {
				ApplicationMasterDto objDto = (ApplicationMasterDto) commonObj;
				sbf
						.append("select count(*) from ApplicationMasterDto mst where mst.idNumber ='"
								+ objDto.getIdNumber() + "' ");
				sbf.append("and mst.applicationType ="
						+ objDto.getApplicationType() + " ");
				sbf.append("and mst.closeDate is  null ");
			}

			Query qry = session.createQuery(sbf.toString());
			List list = qry.list();
			res = ((Integer) list.get(0)).intValue();
			System.out
					.println("After SupplementaryFormDAOImpl checkExistrecord()"
							+ res);
		}

		catch (Exception e) {
			System.out.println("Error Because Record Already Existed:"
					+ e.getMessage());
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error:  Because Record Already Existed:" + e);
		}

		finally {
			HibernetFactory.closeSession();
		}

		return res;
	}

}
