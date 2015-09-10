/**
 * 
 */
package org.transinfo.cacis.util;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.transinfo.cacis.dto.cardproduction.ApplicationProcessDto;
import org.transinfo.cacis.dto.cardproduction.CityDto;
import org.transinfo.cacis.dto.cardproduction.StateDto;
import org.transinfo.cacis.dto.cardproduction.TownShipDto;
import org.transinfo.cacis.dto.csr.CustomerInfoDto;
import org.transinfo.cacis.dto.disputemanagement.DisputeGroupDetailsDto;
import org.transinfo.cacis.test.DWRSubTest;
import org.transinfo.cacis.test.DWRTestBean;

/**
 * @author CTTI-IT-96
 *
 */
@SuppressWarnings("unchecked")
public class DWRUtils {

	private Logger logger = Logger.getLogger(DWRUtils.class);
	
	public String sayHello(String name) {
        return "Hello, " + name;
    }
	
	public String decideCardProductType(String productId){
		String res = "";
		String cardProductType = "";
		String cardProductEmbossLength = "";
		
		DBUtil dbUtils = new DBUtil();
		cardProductType = dbUtils.getCardProductType(productId);
		
		cardProductEmbossLength = dbUtils.getCardProductEmbossLength(productId);
		
		if((!"".equals(cardProductType)) && "DebitCard".equals(cardProductType)){
			res = "Dhide#"+cardProductEmbossLength;
		}else if((!"".equals(cardProductType)) && "ATMCard".equals(cardProductType)){
			res = "Ahide#"+cardProductEmbossLength;
		}else{
			res = "Nohide#"+cardProductEmbossLength;
		}
		
		return res;
	}
	
	public String accountCreationOnSystem(String productId){
		String res = "";
		
		DBUtil dbUtils = new DBUtil();
		String accCreation = dbUtils.getCardProduct(productId);
		
		if((accCreation != null && ("Y".equals(accCreation)))){
			res = "YES";
		}else{
			res = "NO";
		}

		return res;
	}
	
	public String validateSuppl(String cardNo){
		String res = "";
		
		if(cardNo != null && !"".equals(cardNo.trim())){
			try{
				Long.valueOf(cardNo);

				DBUtil dbUtils = new DBUtil();
				res = dbUtils.validateSuppl(cardNo);
				
			}catch (Exception e) {
				res = "1#0";
			}
		}
		
		return res;
	}
	
	public DWRSupplementaryDetails getCustomer(String customerId){
		
		DWRSupplementaryDetails objDwrSupplementaryDetails = null;
		ApplicationProcessDto objApplicationProcessDto = null;
		
		if(customerId != null && !"".equals(customerId.trim())){
			try{

				DBUtil dbUtils = new DBUtil();
				objApplicationProcessDto = dbUtils.getCustomer(customerId);

				CacisissUtils objCacisissUtils = new CacisissUtils();
				objDwrSupplementaryDetails = objCacisissUtils.getDWRSuppDetail(objApplicationProcessDto);
				
			}catch (Exception e) {
				logger.error(new Object(), e);
			}
		}
		
		return objDwrSupplementaryDetails;
	}
	
	public CustomerInfoDto getCustomerInfo(String customerId){
		CustomerInfoDto objCustomerInfoDto = null;
		
		if(customerId != null && !"".equals(customerId.trim())){
			try{

				DBUtil dbUtils = new DBUtil();
				objCustomerInfoDto = dbUtils.getCustomerInfo(customerId);
				
			}catch (Exception e) {
				logger.error(new Object(), e);
			}
		}
		
		return objCustomerInfoDto;
	}
	
	public DWRTestBean getCustomerTest(String customerId){
		DWRTestBean objDwrTestBean = null;
		
		if(customerId != null && !"".equals(customerId.trim())){
			try{

				objDwrTestBean = new DWRTestBean();
				objDwrTestBean.setSupplCustomerName("Nishandan");
				objDwrTestBean.setSupplSureName("Gobal");
				
				DWRSubTest objDwrSubTest = new DWRSubTest();
				objDwrSubTest.setStrOne("1");
				objDwrSubTest.setStrTwo("2");
				Set set = new HashSet();
				set.add(objDwrSubTest);
				
				objDwrTestBean.setApplicationAddress(set);
				
			}catch (Exception e) {
				logger.error(new Object(), e);
			}
		}
		
		return objDwrTestBean;
	}
	
	public List changeDisputeGroup(String groupId){
		
		List<DWRGroupDetail> reasonList = new ArrayList<DWRGroupDetail>();
		List filteredReasonList = new ArrayList();
		
		try{
			
			DBUtil dbUtils = new DBUtil();
			filteredReasonList = dbUtils.getGroupDetails(groupId);
			
			if(filteredReasonList.size() > 0){
				Iterator itr = filteredReasonList.iterator();
				
				while (itr.hasNext()) {
					DisputeGroupDetailsDto objDisputeGroupDetailsDto = (DisputeGroupDetailsDto) itr.next();
					
					DWRGroupDetail objDwrGroupDetail = new DWRGroupDetail();
					objDwrGroupDetail.setReasonCode(objDisputeGroupDetailsDto.getReasonCode());
					objDwrGroupDetail.setReason(objDisputeGroupDetailsDto.getChargeBackReason() + " - " + objDisputeGroupDetailsDto.getReasonCode() + " - " + objDisputeGroupDetailsDto.getDisputeGroup().getGroupId());
					
					reasonList.add(objDwrGroupDetail);
				}
				
			}
			
		}catch (Exception e) {
			logger.error(new Object(), e);
		}
		
		return reasonList;
		
	}
	
	public List getStates(){
		
		List<StateDetail> reasonList = new ArrayList<StateDetail>();
		List stateList = new ArrayList();
		
		try{
			
			DBUtil dbUtils = new DBUtil();
			stateList = dbUtils.getStateDetails();
			
			if(stateList.size() > 0){
				Iterator itr = stateList.iterator();
				
				while (itr.hasNext()) {
					StateDto objDisputeGroupDetailsDto = (StateDto) itr.next();
					
					StateDetail objStateDetail = new StateDetail();
					objStateDetail.setStateId(objDisputeGroupDetailsDto.getStateId());
					objStateDetail.setStateDes(objDisputeGroupDetailsDto.getStateDes());
					
					reasonList.add(objStateDetail);
				}
				
			}
			
		}catch (Exception e) {
			logger.error(new Object(), e);
		}
		
		return reasonList;
		
	}
	
	public List getCities(String stateId){
		
		List<CityDetail> reasonList = new ArrayList<CityDetail>();
		List cityList = new ArrayList();
		
		try{
			
			DBUtil dbUtils = new DBUtil();
			cityList = dbUtils.getCityDetails(stateId);
			
			if(cityList.size() > 0){
				Iterator itr = cityList.iterator();
				
				while (itr.hasNext()) {
					CityDto objCityDto = (CityDto) itr.next();
					
					CityDetail objCityDetail = new CityDetail();
					objCityDetail.setCityId(objCityDto.getCityId());
					objCityDetail.setCityDes(objCityDto.getCityDes());
					
					reasonList.add(objCityDetail);
				}
				
			}
			
		}catch (Exception e) {
			logger.error(new Object(), e);
		}
		
		return reasonList;
		
	}
	
	public List getTownShips(String city){
		
		List<TownshipDetail> reasonList = new ArrayList<TownshipDetail>();
		List townshipList = new ArrayList();
		
		try{
			
			DBUtil dbUtils = new DBUtil();
			townshipList = dbUtils.getTownshipDetails(city);
			
			if(townshipList.size() > 0){
				Iterator itr = townshipList.iterator();
				
				while (itr.hasNext()) {
					TownShipDto objTownShipDto = (TownShipDto) itr.next();
					
					TownshipDetail objTownshipDetail = new TownshipDetail();
					objTownshipDetail.setTownshipId(objTownShipDto.getTownshipId());
					objTownshipDetail.setTownshipDes(objTownShipDto.getTownshipDes());
					
					reasonList.add(objTownshipDetail);
				}
				
			}
			
		}catch (Exception e) {
			logger.error(new Object(), e);
		}
		
		return reasonList;
		
	}
	
	public DWRCardDetails getCardStat(String cardNo){
		
		DWRCardDetails objDwrCardDetails = new DWRCardDetails();;
		ResultSet  rs = null;
		
		if(cardNo != null && !"".equals(cardNo.trim())){
			
			try{

				DBUtil dbUtils = new DBUtil();
				rs = dbUtils.getCardStatDetails(cardNo);

				objDwrCardDetails.setIsCardExists("FALSE");

				while (rs.next()) {
					
					objDwrCardDetails.setIsCardExists("TRUE");
					
					objDwrCardDetails.setStatementId(rs.getString("STAT_ID"));
					objDwrCardDetails.setCardNo(rs.getString("CARD_NUMBER"));
					objDwrCardDetails.setPrevStatementId(rs.getString("PREV_STAT_ID"));
					objDwrCardDetails.setPrevStatementAmt(rs.getString("PREV_STAT_AMT"));
					objDwrCardDetails.setPrevStatementOutStandAmt(rs.getString("PREV_STAT_OUTSTAND_AMT"));
					objDwrCardDetails.setStatementAmt(rs.getString("STAT_AMT"));
					objDwrCardDetails.setStatementMinAmt(rs.getString("STAT_MIN_AMT"));
					objDwrCardDetails.setStatementDueDate(rs.getString("DUEDATE"));
					objDwrCardDetails.setStatementFeeAmt(rs.getString("STAT_FEE_AMT"));
					objDwrCardDetails.setStatementInterestAmt(rs.getString("STAT_INTEREST_AMT"));
					objDwrCardDetails.setStatementCreatedDate(rs.getString("CREDATE"));
				}
				
			}catch (Exception e) {
				logger.error(new Object(), e);
			}
			
		}
		
		return objDwrCardDetails;
	}

}
