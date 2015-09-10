package org.transinfo.cacis.action.cardproduction;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.action.BaseDispatchAction;
import org.transinfo.cacis.common.CardEncryption;
import org.transinfo.cacis.constants.ICacisiss;
import org.transinfo.cacis.controller.cardproduction.CardEmbossingManager;
import org.transinfo.cacis.controller.settings.CardProductManager;
import org.transinfo.cacis.dto.cardproduction.ApplicationProcessDto;
import org.transinfo.cacis.dto.cardproduction.CardEmbossingDto;
import org.transinfo.cacis.dto.cardproduction.CardEmbossingSearchDto;
import org.transinfo.cacis.dto.cardproduction.CardsDto;
import org.transinfo.cacis.dto.cardproduction.CustomerAddressDto;
import org.transinfo.cacis.dto.settings.CardProductDto;
import org.transinfo.cacis.formbean.cardproduction.EmbossSuccess;
import org.transinfo.cacis.formbean.cardproduction.RenewalCardEmbossingSearch;
import org.transinfo.cacis.util.AdminParamsLoad;
import org.transinfo.cacis.util.CardEmboss;
import org.transinfo.cacis.util.CardEmbossFile;
import org.transinfo.cacis.util.DateUtil;
import org.transinfo.cacis.util.EmbossFileContent;
import org.transinfo.cacis.util.EmbossFileHeaderContent;
import org.transinfo.cacis.util.StringUtil;

@SuppressWarnings({ "unchecked", "deprecation" })
public class RenewalCardEmbossingDispatchAction extends BaseDispatchAction {
	
	private Logger logger = Logger.getLogger(RenewalCardEmbossingDispatchAction.class);

	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		ActionErrors errors = null;
		
		String userId = (String)request.getSession().getAttribute("USERID");

		// Token Validation
		if (!isTokenValid(request)) {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.duplicate"));
			saveErrors(request, errors);
			return mapping.findForward("token");
		}

		// Form Validations
		RenewalCardEmbossingSearch objForm = (RenewalCardEmbossingSearch) form;

		// Dto Creation
		CardEmbossingSearchDto objDto = new CardEmbossingSearchDto();

		try {
			BeanUtils.copyProperties(objDto, objForm);
		}catch (Exception e) {
			System.out
					.println("Error converting to form bean in CardEmbossingDispatchAction save method:  "
							+ e);
			throw new TPlusException(
					"Could not populate the form bean in CardEmbossingDispatchAction save method: "
							+ e);
		}
		String filePath = AdminParamsLoad.embossFilePath;
		//String fileNameFormat = AdminParamsLoad.embossFileNameDateFormat;
		//Date now = new Date();
		//SimpleDateFormat df;
		//df = new SimpleDateFormat(fileNameFormat);
		//String dateTime = df.format(now);
		//String fileName = "CARD_EMB_RENEWAL" + dateTime + ".xls";
		// Action Execution
		CardEmbossingManager objManager = new CardEmbossingManager();
		CardProductManager objCardProductManager = new CardProductManager();
		EmbossSuccess objEmbossSuccess = null;
		ArrayList succesCards = new ArrayList();
		StringBuilder genCardNos = new StringBuilder();
		
		/*CardEmbossFile objCardEmbossFile = new CardEmbossFile();
		// file path set
		objCardEmbossFile.setOutputFile(filePath+fileName);
		// create the file and set settings
		objCardEmbossFile.fileCreate();
		// create the header row
		objCardEmbossFile.headerCreate();*/
		
		// row number. if create header row number should start from 1. otherwise 0.
		//int rowNumber = 1;

		String selectCardEmbossSerialNos[] = objDto.getSelectedEmbossSerialNos();
		// date for emboss table. to group by later for multiple download
		Date embossDate = new Date();
		
		CardEmboss objCardEmboss = new CardEmboss(filePath);
		boolean headerExecute = true;

		int cardholderType = 0;
		
		for (int i = 0; i < selectCardEmbossSerialNos.length; i++) {
			String embossSerialNo = selectCardEmbossSerialNos[i];
			String cardNo = "";
			String cutomerId = "";
			try{
				// get CardEmboss object
				CardEmbossingDto objCardEmbossingDto = objManager.getCardEmboss(embossSerialNo);
				// get Card object
				CardsDto objCardsDto = objManager.getCard(embossSerialNo);
				// card number
				cardNo = String.valueOf(objCardsDto.getCardNumber());
				// card holder type
				cardholderType = objCardsDto.getCardHolderType();
				// customer ID
				cutomerId = objCardsDto.getCustomerId();
				// get Customer object
				ApplicationProcessDto objApplicationProcessDto = objManager.getCustomerByCusId(cutomerId);
				// generate image path
				//String imagePath = StringUtil.imagePathGet(objApplicationProcessDto.getEmbossingName(), objApplicationProcessDto.getDob());
				// get card emboss length
				CardProductDto objCardProductDto = objCardProductManager.getCardProductDto(objCardsDto.getCardProductId());
				//int embossNameLength = objCardProductDto.getEmbossNameLength();
				String embossName = objApplicationProcessDto.getEmbossingName();
				//embossName = StringUtil.RPAD(embossName, embossNameLength, " ");
				//embossName = embossName+"/";
				String imagePath = embossName+".JPG";
				imagePath = StringUtil.RPAD(imagePath, 30, " ");
				// format expire date
				String expDate = objCardsDto.getCardExpDate();
				expDate = StringUtil.formatExpDate(expDate);
				// format issuing date
				String issuDate = DateUtil.convertIssueDateToDateString(objCardsDto.getEffectiveDate());
				issuDate = StringUtil.formatExpDate(issuDate);
				// format card number
				String embossFileCardNo = CardEncryption.decrypt(objCardsDto.getEncryptedCardNo());
				embossFileCardNo = StringUtil.getFormattedCardNo(embossFileCardNo);
				// format track1_data
				String track1data = objCardEmbossingDto.getTrack1();
				//track1data = ICacisiss.IEmboss.TRACK1_DATA_APPENDER + track1data;
				track1data = StringUtil.RPAD(track1data, 69, "0");
				
				// format track2_data
				String track2data = objCardEmbossingDto.getTrack2();
				//track1data = ICacisiss.IEmboss.TRACK1_DATA_APPENDER + track1data;
				track2data = StringUtil.RPAD(track2data, 37, "0");
				
				EmbossFileContent objEmbossFileContent = new EmbossFileContent();
				
				logger.error("befor  objEmbossFileContent");
				// get the residential address
				for (Iterator it = objApplicationProcessDto.getApplicationAddress().iterator(); it.hasNext();) {
					CustomerAddressDto addressDto = (CustomerAddressDto) it.next();
					if(cardholderType == 1){
						if (addressDto.getAddressType() != null && addressDto.getAddressType().equals("H")) {
							objEmbossFileContent.setAddress1(StringUtil.getRequireString(StringUtil.RPAD(addressDto.getAddress1(), 40, " "),40));
							objEmbossFileContent.setAddress2(StringUtil.getRequireString(StringUtil.RPAD(addressDto.getAddress2(), 40, " "),40));
							objEmbossFileContent.setCity(StringUtil.RPAD(addressDto.getCity(), 40, " "));
							String stateCountry = addressDto.getState()+","+addressDto.getCountry();
							objEmbossFileContent.setStateCountry(StringUtil.RPAD(stateCountry, 40, " "));
							break;
						}
					}else if(cardholderType == 2){
						if (addressDto.getAddressType() != null && addressDto.getAddressType().equals("S")) {
							objEmbossFileContent.setAddress1(StringUtil.getRequireString(StringUtil.RPAD(addressDto.getAddress1(), 40, " "),40));
							objEmbossFileContent.setAddress2(StringUtil.getRequireString(StringUtil.RPAD(addressDto.getAddress2(), 40, " "),40));
							objEmbossFileContent.setCity(StringUtil.RPAD(addressDto.getCity(), 40, " "));
							String stateCountry = addressDto.getState()+","+addressDto.getCountry();
							objEmbossFileContent.setStateCountry(StringUtil.RPAD(stateCountry, 40, " "));
							break;
						}
					}
				}

				logger.error("befor  headerExecute");
				if(headerExecute){
					
					String pPrefix = "";
					String pName = objCardProductDto.getCardProductName();
					String[] pArr = pName.split(" ");
					for (int j = 0; j < pArr.length; j++) {
						pPrefix += pArr[j].substring(0, 1);
					}

					String strFileName = "001_EM03"+pPrefix+"_REN_"+DateUtil.getStrDate("yyMMdd", embossDate)+"_"+DateUtil.getStrDate("HHmmss", embossDate)+".txt";
					objCardEmboss.setFileName(strFileName);

					EmbossFileHeaderContent objEmbossFileHeaderContent = new EmbossFileHeaderContent();
					objEmbossFileHeaderContent.setHeader1("901726");
					objEmbossFileHeaderContent.setHeader2(DateUtil.getStrDate("ddMMMyy", embossDate));
					objEmbossFileHeaderContent.setHeader3(DateUtil.getStrDate("HHmmss", embossDate));
					objEmbossFileHeaderContent.setHeader4("01");
					objEmbossFileHeaderContent.setHeader5(StringUtil.RPAD(StringUtil.getRequireString(pName,20), 20, " "));
					
					objCardEmboss.embossHeader(objEmbossFileHeaderContent);
					
					headerExecute = false;
				}

				logger.error("befor  objEmbossFileContent");				
				// write data into EmbossFileContent class
				objEmbossFileContent.setCardNo(embossFileCardNo);
				objEmbossFileContent.setCardHolderName(objApplicationProcessDto.getCustomerName());
				objEmbossFileContent.setEmbossName(embossName);
				objEmbossFileContent.setExpiryDate(expDate);
				objEmbossFileContent.setIssuingDate(issuDate);
				objEmbossFileContent.setCvv2(StringUtil.LPAD(String.valueOf(objCardsDto.getCvv2()), 3, "0"));
				objEmbossFileContent.setTrack1data(track1data);
				objEmbossFileContent.setTrack2data(objCardEmbossingDto.getTrack2());
				objEmbossFileContent.setImagePath(imagePath);

				String icvvDate = DateUtil.convertIssueDateToiCvvFormat(objCardsDto.getEffectiveDate());
				String icvvData = objCardsDto.getIcvv()+"001"+icvvDate+"01";
				objEmbossFileContent.setIcvvData(icvvData);
				//objEmbossFileContent.setRowNumber(rowNumber);

				logger.error("befor  objCardEmboss.cardEmboss");
				// send data to create emboss file and get back result
				boolean embossFile = objCardEmboss.cardEmboss(objEmbossFileContent);

				logger.error("after  objCardEmboss.cardEmboss");
				// check the status of the file creation and continue for further process
				if(embossFile){
					//rowNumber++;
					boolean updateRes = objManager.updateObjectsRenewal(embossSerialNo, userId, embossDate);
					if(updateRes){
						objEmbossSuccess = new EmbossSuccess();
						objEmbossSuccess.setMaskedCardNo(objCardsDto.getMaskedCardNo());
						objEmbossSuccess.setCustName(objApplicationProcessDto.getCustomerName());
						objEmbossSuccess.setEmbossName(objApplicationProcessDto.getEmbossingName());
						objEmbossSuccess.setCardExpDate(objCardsDto.getCardExpDate());
						objEmbossSuccess.setCardProduct(objCardProductDto.getCardProductName());
						
						genCardNos.append("'");
						genCardNos.append(cardNo);
						genCardNos.append("',");
						
						succesCards.add(objEmbossSuccess);
					}
				}
			}catch (Exception e) {
				System.out.println(e);
				logger.error(new Object(), e);
			}
			
		}
		
		// create the emboss file
		String fileDownloadPath = objCardEmboss.creatBatchOutFile();
		
		// make file as read only
		//objCardEmbossFile.makeReadOnly();
		
		// finally write into file and close
		//objCardEmbossFile.writeAndClose();
		
		if(succesCards.size() > 0){
			errors = new ActionErrors();
			if(succesCards.size() == selectCardEmbossSerialNos.length){
				errors.add("Error", new ActionError("error.cardembosssuccess"));
			}else{
				errors.add("Error", new ActionError("error.cardembosssuccesspartial"));
			}
			saveErrors(request, errors);
			
			objForm.setSuccessCardsList(succesCards);
			objForm.setCardNos(genCardNos.toString().substring(0, (genCardNos.toString().length() - 1)));
			//objForm.setEmossFile(filePath+fileName);
			objForm.setEmossFile(fileDownloadPath);
			
			return mapping.findForward("reportpage");
			
		}else{
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.cardembossfailed"));
			saveErrors(request, errors);
			return mapping.findForward("fail");
		}

	}

    @SuppressWarnings("unused")
    private boolean cardEmboss(EmbossFileContent objEmbossFileContent, CardEmbossFile objCardEmbossFile) throws Exception{
    	boolean result = false;
        
        try{	        
	        result = objCardEmbossFile.createRow(objEmbossFileContent);
	        //result = true;
        }catch(Exception ex){
        	System.out.println(ex);
        }
    	
    	return result;
    }
    
	public ActionForward embossFileDownload(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws TPlusException, Exception {

		ActionErrors errors = null;

		// Token Validation
		if (!isTokenValid(request)) {
			errors = new ActionErrors();
			errors.add("Error", new ActionError("error.duplicate"));
			saveErrors(request, errors);
			return mapping.findForward("token");
		}

		// Form Validations
		RenewalCardEmbossingSearch objForm = (RenewalCardEmbossingSearch) form;
		FileInputStream stream = null;
        File file = new File(objForm.getEmossFile());
        
        response.setContentType(ICacisiss.IEmboss.EMBOSS_FILE_CONTENT_TYPE_TXT);
        //response.setHeader("Content-Disposition", "attachment"+ "filename=" + file.getName());
        response.setHeader("content-disposition", "attachment;filename=" + file.getName());
        stream = new FileInputStream(file);
        
        byte fileContent[] = new byte[(int)file.length()];
        stream.read(fileContent);
        
        response.setContentLength(fileContent.length);
        OutputStream os = response.getOutputStream();
        os.write(fileContent, 0 , fileContent.length);
        os.close();
        response.flushBuffer();
        stream.close();
        
        // delete the file from server
        /*boolean sucess = file.delete();
        
        if(sucess){
        	logger.error(file.getName() + " deleted successfully from server -- NOT an error. But has set via error stage");
        }else{
        	logger.error(file.getName() + " coulden't delete from server -- NOT an error. But has set via error stage");
        }*/
        
		return mapping.findForward("fail");

	}

}
