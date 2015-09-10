package org.transinfo.cacis.model;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.jpos.iso.ISOUtil;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.common.CardEncryption;
import org.transinfo.cacis.common.IdsGenartion;
import org.transinfo.cacis.common.constants.CommonCodes;
import org.transinfo.cacis.constants.ICacisiss;
import org.transinfo.cacis.controller.batchprocess.CardBatchProcessManager;
import org.transinfo.cacis.dataacess.HibernetDAO.HibernetFactory;
import org.transinfo.cacis.dto.cardproduction.ApplicationFormDto;
import org.transinfo.cacis.dto.cardproduction.ApplicationProcessDto;
import org.transinfo.cacis.dto.cardproduction.CardsDto;
import org.transinfo.cacis.dto.cardproduction.CustomerAccountDto;
import org.transinfo.cacis.dto.csr.AddProductDto;
import org.transinfo.cacis.dto.customerservice.CardChangeDto;
import org.transinfo.cacis.dto.settings.CardProductDto;
import org.transinfo.cacis.report.db.DBManager;
import org.transinfo.cacis.util.DBUtil;
import org.transinfo.cacis.util.DateUtil;
import org.transinfo.cacis.util.NumberUtil;
import org.transinfo.cacis.util.StringUtil;

import vn.com.tivn.hsm.phw.EracomPHW;

@SuppressWarnings( { "unchecked", "deprecation","unused" })
public class CardGeneration {

	private Logger logger = Logger.getLogger(CardGeneration.class);

	private static CardGeneration objCardGenerator = null;

	private CardGeneration() {
	}

	public static CardGeneration getInstance() {

		if (objCardGenerator == null) {
			objCardGenerator = new CardGeneration();
		}

		return objCardGenerator;

	}

	public ArrayList cardGeneration(ArrayList recvList) {
		ArrayList genList = new ArrayList();
		try {
			System.out.println("cardNumber" + (String) recvList.get(0));
			System.out.println("cardproductid" + (String) recvList.get(1));
			System.out.println("branchid" + (String) recvList.get(2));
			System.out.println("issuerid" + (String) recvList.get(3));

			Session session = HibernetFactory.currentSession();

			Query namedQuery = session.getNamedQuery("getNewCardInfo");
			namedQuery.setString("cardnumber", (String) recvList.get(0));
			namedQuery.setString("cardproductid", (String) recvList.get(1));
			namedQuery.setString("branchid", (String) recvList.get(2));
			namedQuery.setString("issuerid", (String) recvList.get(3));

			List mainCardlist = namedQuery.list();
			Object generatedPrimcardData[] = (Object[]) mainCardlist.get(0);
			// Setting the data cardsDto to insert into cards table
			System.out.println("Card Number >>>> "
					+ generatedPrimcardData[0].toString());
			genList.add(generatedPrimcardData[0].toString());
			genList.add(generatedPrimcardData[1].toString());
			genList.add((Integer) generatedPrimcardData[2]);
			genList.add((Integer) generatedPrimcardData[3]);
		} catch (Exception e) {
			System.out.println("Exception while cardGeneration" + e);
		}
		return genList;
	}

	/*
	 * This method used for Primary CardGeneration arugment
	 * ApplicationProcessDto returns void
	 */
	public synchronized void PrimaryCardGeneration(
			ApplicationProcessDto objAppProcessDto,
			ApplicationProcessDto objApplicationProcessDtoSuppl,
			ApplicationFormDto objApplicationFormDto, Map insexList, 
			String issuerID, int embossNameLength)
					throws TPlusException {

		// Transaction tx =null;
		CardsDto objCardsDto = null;

		logger.error("Inside the PrimaryCardGeneration Method.");

		try {
			// Session session =HibernetFactory.currentSession();
			// tx= session.beginTransaction();
			// String cardProductIds[] =
			// objAppProcessDto.getSelectedAppCardProducts();
			String cardProductIds = objAppProcessDto.getSelectedAppCardProducts();

			logger.error("Card Product = " + cardProductIds);

			String embossName = objAppProcessDto.getEmbossingName();
			//embossName = StringUtil.RPAD(embossName, embossNameLength, " ");

			String branchId = objApplicationFormDto.getBranchId();

			// for(int i=0; i<cardProductIds.length;i++){
			if (cardProductIds != null) {

				objCardsDto = new CardsDto();


				logger.error("getNewCardInfo before");
				// call the method to generate the card number
				objCardsDto = getNewCardInfo(cardProductIds, objCardsDto, insexList, "", "", "", embossName, issuerID, branchId);
				logger.error("getNewCardInfo after");
				logger.error("Track1 = " + objCardsDto.getTrack1());
				logger.error("Track2 = " + objCardsDto.getTrack2());

				// set cash used & purchase used
				objCardsDto.setCashUsed(0.0);
				objCardsDto.setPurchaseUsed(0.0);

				objCardsDto.setBranchId(branchId);

				objCardsDto.setIssuerId(objAppProcessDto.getIssuerId());
				objCardsDto.setCardProductId(cardProductIds);
				objCardsDto.setCardHolderType(CommonCodes.PRIMARYCARD_HOLDER);
				objCardsDto.setCardStatusId(CommonCodes.CARD_NEW);

				// setting the customer id from ApplicationProcessDto
				objCardsDto.setCustomerId(objAppProcessDto.getCustomerId());

				objCardsDto.setEffectiveDate(new Date());
				objCardsDto.setPinDisabled(ICacisiss.ICardGeneration.PIN_DISABLE_VALUE);
				objCardsDto.setStatus("A");
				objCardsDto.setLastUpdatedBy(objAppProcessDto.getUserId());
				objCardsDto.setUpdatedDate(new Date());

				// Setting remaining card data by generating random numbers
				getCardData(objCardsDto);

				// Mapping Settings(relations like one-to-many )
				setCardAccountMapping(objAppProcessDto, objCardsDto);

				// set cycle no
				objCardsDto.setCycleNo(objApplicationFormDto.getCycleNo());

				System.out.println("main card holder CardNumber is:" + objCardsDto.getCardNumber());
				System.out.println("main card holder Expire Date is:" + objCardsDto.getCardExpDate());

				logger.error("main card holder CardNumber is:" + objCardsDto.getCardNumber());
				logger.error("main card holder Expire Date is:" + objCardsDto.getCardExpDate());
				logger.error("objAppProcessDto.getCheckedSupplCardRequired() :: " + objAppProcessDto.getCheckedSupplCardRequired());

				// Checking for Supplementary
				if (objAppProcessDto.getCheckedSupplCardRequired() == 'Y') {

					embossName = objApplicationProcessDtoSuppl.getEmbossingName();
					embossName = StringUtil.RPAD(embossName, embossNameLength, " ");

					objCardsDto = new CardsDto();

					// call the method to generate the card number
					objCardsDto = getNewCardInfo(cardProductIds, objCardsDto, insexList, "", "", "", embossName, issuerID, branchId);

					// set cash used & purchase used
					objCardsDto.setCashUsed(0.0);
					objCardsDto.setPurchaseUsed(0.0);

					objCardsDto.setBranchId(branchId);

					objCardsDto.setIssuerId(objApplicationProcessDtoSuppl.getIssuerId());
					objCardsDto.setCardProductId(cardProductIds);
					objCardsDto.setCardHolderType(CommonCodes.SUPPLEMENTARYCARD_HOLDER);
					objCardsDto.setCardStatusId(CommonCodes.CARD_NEW);

					// here we have to set the supplementary id
					objCardsDto.setCustomerId(objApplicationProcessDtoSuppl.getCustomerId());

					objCardsDto.setEffectiveDate(new Date());
					objCardsDto.setPinDisabled(ICacisiss.ICardGeneration.PIN_DISABLE_VALUE);
					objCardsDto.setStatus("A");
					objCardsDto.setLastUpdatedBy(objApplicationProcessDtoSuppl.getUserId());
					objCardsDto.setUpdatedDate(new Date());

					// Setting remaining card data by generating random numbers
					getCardData(objCardsDto);

					// Mapping Settings
					setCardAccountMapping(objAppProcessDto, objCardsDto);

					// set cycle no
					objCardsDto.setCycleNo(objApplicationFormDto.getCycleNo());

					System.out
					.println("Supplemenary card holder CardNumber is:"
							+ objCardsDto.getCardNumber());
					System.out
					.println("Supplemenary card holder Expire Date is:"
							+ objCardsDto.getCardExpDate());
				}

			}

			// session.flush();
			// tx.commit();

		} catch (Exception e) {
			// if(tx!=null){
			// tx.rollback();
			// }
			logger.error(new Object(), e);
			System.out.println("Error while generating the Primary card number"
					+ e.getMessage());
			throw new TPlusException(
					"while generating the Primary card number: " + e);

		} finally {

			// HibernetFactory.closeSession();
		}

	}


	public synchronized void NewProductCardGeneration(
			ApplicationProcessDto objAppProcessDt,
			AddProductDto objAddProductDto, Map insexList, 
			String issuerID, int embossNameLength)
					throws TPlusException {

		// Transaction tx =null;
		CardsDto objCardsDto = null;

		logger.error("Inside the PrimaryCardGeneration Method.");

		try {
			// Session session =HibernetFactory.currentSession();
			// tx= session.beginTransaction();
			// String cardProductIds[] =
			// objAppProcessDto.getSelectedAppCardProducts();
			String cardProductIds = objAddProductDto.getNewCardProduct();

			logger.error("Card Product = " + cardProductIds);

			String embossName = objAppProcessDt.getEmbossingName();
			//embossName = StringUtil.RPAD(embossName, embossNameLength, " ");

			String branchId = objAppProcessDt.getBranchId();

			// for(int i=0; i<cardProductIds.length;i++){
			if (cardProductIds != null) {

				objCardsDto = new CardsDto();


				logger.error("getNewCardInfo before");
				// call the method to generate the card number
				objCardsDto = getNewCardInfo(cardProductIds, objCardsDto, insexList, "", "", "", embossName, issuerID, branchId);
				logger.error("getNewCardInfo after");
				logger.error("Track1 = " + objCardsDto.getTrack1());
				logger.error("Track2 = " + objCardsDto.getTrack2());

				// set cash used & purchase used
				objCardsDto.setCashUsed(0.0);
				objCardsDto.setPurchaseUsed(0.0);

				objCardsDto.setBranchId(branchId);

				objCardsDto.setIssuerId(objAppProcessDt.getIssuerId());
				objCardsDto.setCardProductId(cardProductIds);
				objCardsDto.setCardHolderType(CommonCodes.PRIMARYCARD_HOLDER);
				objCardsDto.setCardStatusId(CommonCodes.CARD_NEW);

				// setting the customer id from ApplicationProcessDto
				objCardsDto.setCustomerId(objAddProductDto.getCustomerId());

				objCardsDto.setEffectiveDate(new Date());
				objCardsDto.setPinDisabled(ICacisiss.ICardGeneration.PIN_DISABLE_VALUE);
				objCardsDto.setStatus("A");
				objCardsDto.setLastUpdatedBy(objAppProcessDt.getUserId());
				objCardsDto.setUpdatedDate(new Date());

				// Setting remaining card data by generating random numbers
				getCardData(objCardsDto);

				// Mapping Settings(relations like one-to-many )
				setCardAccountMapping(objAppProcessDt, objCardsDto);

				// set cycle no
				objCardsDto.setCycleNo(objAddProductDto.getCycleNo());

				System.out.println("main card holder CardNumber is:" + objCardsDto.getCardNumber());
				System.out.println("main card holder Expire Date is:" + objCardsDto.getCardExpDate());

				logger.error("main card holder CardNumber is:" + objCardsDto.getCardNumber());
				logger.error("main card holder Expire Date is:" + objCardsDto.getCardExpDate());

			}

		} catch (Exception e) {
			logger.error(new Object(), e);
			System.out.println("Error while generating the Primary card number" + e.getMessage());
			throw new TPlusException("while generating the Primary card number: " + e);
		} finally {
			// HibernetFactory.closeSession();
		}

	}

	/*
	 * This method used for Supplementary Card Generationinput argument
	 * java.util.ArrayListreturns CardsDto
	 */
	public synchronized CardsDto SupplementaryCardGeneration(
			ArrayList suppDataList) throws TPlusException {

		Transaction tx = null;
		CardsDto objCardsDto = null;
		try {

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			objCardsDto = new CardsDto();
			Query namedQuerySupp = session.getNamedQuery("getNewCardInfo");
			namedQuerySupp
			.setString("cardnumber", (String) suppDataList.get(0));
			namedQuerySupp.setString("cardproductid", (String) suppDataList
					.get(1));
			namedQuerySupp.setString("branchid", (String) suppDataList.get(2));
			namedQuerySupp.setString("issuerid", (String) suppDataList.get(3));

			List supplist = namedQuerySupp.list();
			Object suppcards[] = (Object[]) supplist.get(0);
			int checkDigit = checkdigit((String) suppcards[0]);
			// objCardsDto.setCardNumber(((Long)suppcards[0]).longValue());
			objCardsDto.setCardNumber(Long.parseLong((String) suppcards[0]
					+ checkDigit));
			System.out.println("Supp card Number >>>> " + (String) suppcards[0]
					+ checkDigit);
			objCardsDto.setCardExpDate(suppcards[1].toString());
			objCardsDto.setCvki(((Integer) suppcards[2]).intValue());
			objCardsDto.setPvki(((Integer) suppcards[3]).intValue());
			objCardsDto.setIssuerId((String) suppDataList.get(3));
			objCardsDto.setCardProductId((String) suppDataList.get(1));
			objCardsDto.setCardHolderType(CommonCodes.SUPPLEMENTARYCARD_HOLDER);
			objCardsDto.setCardStatusId(CommonCodes.CARD_NEW);
			// objCardsDto.setCustomerId(IdsGenartion.GenerateCustomerId());
			objCardsDto.setCustomerId(IdsGenartion.GenerateSupplementaryId());
			objCardsDto.setEffectiveDate(new Date());
			objCardsDto.setPinDisabled('N');
			objCardsDto.setStatus("A");
			objCardsDto.setLastUpdatedBy("ISSUERADMIN");
			objCardsDto.setUpdatedDate(new Date());

			// Setting card data
			getCardData(objCardsDto);

			System.out.println("Supplemenary card holder CardNumber is:"
					+ ((Long) suppcards[0]).longValue());
			System.out.println("Supplemenary card holder Expire Date is:"
					+ suppcards[1].toString());

			session.flush();
			tx.commit();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}

			System.out
			.println("Error while generating the Supplemenary card number"
					+ e.getMessage());
			throw new TPlusException(
					"while generating the Supplemenary card number: " + e);
		}

		return objCardsDto;
	}

	/*
	 * This method used for for map setting the cards to customeraccount and
	 * customeraccount to cardsinput argument ApplicationProcessDto,CardsDto
	 * returns void
	 */
	private void setCardAccountMapping(ApplicationProcessDto objAppProcessDto,
			CardsDto objCardsDto) {
		if (objAppProcessDto.getCustomerAccount() != null
				&& objAppProcessDto.getCustomerAccount().size() > 0) {
			Set set = objAppProcessDto.getCustomerAccount();
			for (Iterator it = set.iterator(); it.hasNext();) {
				CustomerAccountDto custAcctDto = (CustomerAccountDto) it.next();
				custAcctDto.getCustomerCards().add(objCardsDto);
				objCardsDto.setCustAccountDto(custAcctDto);

			}

		}
	}

	private void setCardAccountMappingReplacement(ApplicationProcessDto objAppProcessDto, CardsDto objCardsDto, CardsDto objCardsDtoOrigi) {
		
		if (objAppProcessDto.getCustomerAccount() != null && objAppProcessDto.getCustomerAccount().size() > 0) {
			
			Set set = objAppProcessDto.getCustomerAccount();
			for (Iterator it = set.iterator(); it.hasNext();) {
				CustomerAccountDto custAcctDto = (CustomerAccountDto) it.next();
				custAcctDto.getCustomerCards().add(objCardsDto);
				objCardsDto.setCustAccountDto(custAcctDto);

			}

		}else{
			CustomerAccountDto custAcctDto = objCardsDtoOrigi.getCustAccountDto();
			custAcctDto.getCustomerCards().add(objCardsDto);
			objCardsDto.setCustAccountDto(custAcctDto);
		}
	}

	/*
	 * This method used for generating and setting the cards data input argument
	 * CardsDtoreturns void
	 */
	public void getCardData(CardsDto objCardsDto) throws Exception {

		// objCardsDto.setCvv(Integer.parseInt(NumberUtil.getRandNo(3)));
		// objCardsDto.setCvv2(Integer.parseInt(NumberUtil.getRandNo(3)));
		objCardsDto.setNip(NumberUtil.getRandNo(4));
		// objCardsDto.setPvv(Integer.parseInt(NumberUtil.getRandNo(3)));
		objCardsDto.setOpvv(Integer.parseInt(NumberUtil.getRandNo(3)));
		// objCardsDto.setPvvOffSet(Integer.parseInt(NumberUtil.getRandNo(3)));
		objCardsDto.setCvki(ICacisiss.ICardGeneration.CVKI);
		objCardsDto.setPvki(ICacisiss.ICardGeneration.PVKI);

	}

	public static void main(String args[]) throws Exception {
		// CardGeneration objCard = new CardGeneration();
		/*ApplicationProcessDto objAppDto = new ApplicationProcessDto();
		// String cardProducts[] ={"1","3","4"};
		String cardProducts = "3";
		objAppDto.setSelectedAppCardProducts(cardProducts);
		objAppDto.setIssuerId("Issuer1");
		objAppDto.setBranchId("01");
		objAppDto.setCheckedSupplCardRequired('Y');*/
		// objCard.PrimaryCardGeneration(objAppDto);

		/*String PVV = "";

		byte[] pvv = new byte[6];
		byte[] ePIN = new byte[8];

		EracomPHW.init("10.0.19.53", 1000, 5, 5000);

		int ret = EracomPHW.generateClearRandomPIN(6, ePIN);

		ret = EracomPHW.generatePVVFromEncryptedPIN(Integer.valueOf("1").intValue(), "4705300000000038", "1",
				EracomPHW.PIN_FORMAT_ANSI, ISOUtil.hexString(ePIN), 6, pvv);
		if (ret == 0) {
			PVV = ISOUtil.hexString(pvv);
		} else {
			throw new Exception("Generating PVV Error");
		}

		System.out.println(PVV);*/

		/*EracomPHW.init("192.168.1.95", 1000, 5, 5000);

		Map indexList = new HashMap();
		indexList.put("01CVK", "1");
		indexList.put("01PVK", "1");
		indexList.put("01CV2K", "1");
		indexList.put("01PPK", "1");

		CardsDto objCardsDto = new CardsDto();

		CardGeneration objCardGeneration = CardGeneration.getInstance();
		objCardsDto = objCardGeneration.getNewCardInfo("01", objCardsDto, indexList, "", "2", "", "Nishan", "Issuer1", "010");*/
		
		CardGeneration objCardGeneration = new CardGeneration();
		System.out.println(objCardGeneration.getCheckDigit("7992739871"));

	}

	public int checkdigit(String idWithoutCheckdigit) throws Exception {

		// allowable characters within identifier
		String validChars = "0123456789ABCDEFGHIJKLMNOPQRSTUVYWXZ_";

		// remove leading or trailing whitespace, convert to uppercase
		idWithoutCheckdigit = idWithoutCheckdigit.toUpperCase();

		// this will be a running total
		int sum = 0;

		// loop through digits from right to left
		for (int i = 0; i < idWithoutCheckdigit.length(); i++) {

			// set ch to "current" character to be processed
			char ch = idWithoutCheckdigit.charAt(idWithoutCheckdigit.length()
					- i - 1);

			// throw exception for invalid characters
			if (validChars.indexOf(ch) == -1)
				throw new Exception("\"" + ch + "\" is an invalid character");

			// our "digit" is calculated using ASCII value - 48
			int digit = (int) ch - 48;

			// weight will be the current digit's contribution to
			// the running total
			int weight;
			if (i % 2 == 0) {
				// for alternating digits starting with the rightmost, we
				// use our formula this is the same as multiplying x 2 and
				// adding digits together for values 0 to 9. Using the
				// following formula allows us to gracefully calculate a
				// weight for non-numeric "digits" as well (from their
				// ASCII value - 48).
				weight = (2 * digit) - (int) (digit / 5) * 9;

			} else {
				// even-positioned digits just contribute their ascii
				// value minus 48
				weight = digit;
			}
			// keep a running total of weights
			sum += weight;
		}
		// avoid sum less than 10 (if characters below "0" allowed,
		// this could happen)
		sum = Math.abs(sum) + 10;

		// check digit is amount needed to reach next number
		// divisible by ten
		return (10 - (sum % 10)) % 10;

	}

	private synchronized CardsDto getNewCardInfo(String cardProduct,
			CardsDto objCardsDto, Map indexList, String changeProduct, 
			String expExtendInterval, String oldExp, 
			String embossName, String appIissuerId, String branchId)
					throws Exception {
		String cardNumber = "";
		//String eComEnable = "";

		String expiryDateDb = "";
		String expiryDateCVV = "";
		String expiryDateCVV2 = "";
		String expiryDateTrack = "";

		String cvkIndex = "";
		String cvk2Index = "";
		String pvkIndex = "";
		String ppkIndex = "";

		String serviceCode = "";

		String CVV = "";
		String CVV2 = "";
		String iCVV = "";
		String PVV = "";

		int pinLength = 0;

		String pinRequired = "";
		int nextSerialNo = 0;
		int low3rdRange = 0;
		boolean flag = false;
		
		String encryptCardNumber = "";

		String issuerId = appIissuerId;
		String productId = cardProduct;

		if (changeProduct != null && !"".equals(changeProduct)) {
			productId = changeProduct;
		}

		logger.error("Card Product inside the CardGeneration = " + productId);

		while (flag == false) {
			StringBuffer strSql = new StringBuffer();
			try {
				/*strSql.append("SELECT CP.SERVICECODE,  CP.FIRSTISSUE_VALIDITY, CP.CARDNO_NEXT_VALUE, CP.PIN_REQUIRED, ");
				strSql.append("CP.BIN || RPAD(CP.CARD_PRODUCT_ID, 3, 0) || LPAD(TO_CHAR(TO_NUMBER(CP.CARDNO_NEXT_VALUE)), CP.CARDNUMBER_LENGTH-(CP.BIN_LENGTH+3+1), 0) AS CARD_NUMBER ");
				strSql.append("FROM CARD_PRODUCTS CP ");
				strSql.append("WHERE CP.CARD_PRODUCT_ID='" + productId + "' AND CP.ISSUER_ID='" + issuerId + "' ");*/

				// commented to test
				/*strSql.append("SELECT CP.SERVICECODE,  CP.FIRSTISSUE_VALIDITY, CP.CARDNO_NEXT_VALUE, CP.PIN_REQUIRED, CP.PIN_LENGTH, ");
				//strSql.append("CP.BIN || LPAD(TO_CHAR(TO_NUMBER(CP.CARDNO_NEXT_VALUE)), CP.CARDNUMBER_LENGTH-(CP.BIN_LENGTH+1), 0) AS CARD_NUMBER ");
				strSql.append("CP.BIN || CASE WHEN CP.BRANCHID_ON_CARDNO = 'Y' THEN '"+branchId+"' END || LPAD(TO_CHAR(TO_NUMBER(CP.CARDNO_NEXT_VALUE)), CP.CARDNUMBER_LENGTH-(CP.BIN_LENGTH+1+(CASE WHEN CP.BRANCHID_ON_CARDNO = 'Y' THEN NVL(LENGTH('"+branchId+"'),0) ELSE 0 END)), 0) AS CARD_NUMBER ");
				strSql.append("FROM CARD_PRODUCTS CP ");
				strSql.append("WHERE CP.CARD_PRODUCT_ID='" + productId + "' AND CP.ISSUER_ID='" + issuerId + "' ");*/
				// end 

				strSql.append("SELECT CP.SERVICECODE,  CP.FIRSTISSUE_VALIDITY, CP.CARDNO_NEXT_VALUE, CP.PIN_REQUIRED, CP.PIN_LENGTH, CP.ECOMM_ENABLE, CP.LOW_3RD_RANGE, ");
				strSql.append("CP.BIN || '00' || CP.LOW_3RD_RANGE || LPAD(TO_CHAR(TO_NUMBER(CP.CARDNO_NEXT_VALUE)), CP.CARDNUMBER_LENGTH-(CP.BIN_LENGTH+2+LENGTH(CP.LOW_3RD_RANGE)+1), 0) AS CARD_NUMBER ");
				strSql.append("FROM CARD_PRODUCTS CP ");
				strSql.append("WHERE CP.CARD_PRODUCT_ID='" + productId + "' AND CP.ISSUER_ID='" + issuerId + "' AND TO_NUMBER(CP.CARDNO_NEXT_VALUE) <= 999 ");
				strSql.append("AND TO_NUMBER(CP.LOW_3RD_RANGE) <= TO_NUMBER(SUBSTR(CP.HIGH_3RD_RANGE,9,4)) ");

				System.out.println("SQL is " + strSql.toString());
				logger.error("SQL is " + strSql.toString());

				DBManager objDBManager = new DBManager();
				ResultSet rs = objDBManager.executeSQL(strSql.toString(), true);

				if (rs.next()) {

					cardNumber = rs.getString("CARD_NUMBER");
					cardNumber = cardNumber + getCheckDigit(cardNumber);

					logger.error("Card no is = " + cardNumber);

					/*eComEnable = rs.getString("ECOMM_ENABLE");
					logger.error("ECOMM_ENABLE = " + eComEnable);
					if("Y".equals(eComEnable)){
						objCardsDto.seteComEnable(eComEnable);
					}*/

					nextSerialNo = rs.getInt("CARDNO_NEXT_VALUE");
					low3rdRange = rs.getInt("LOW_3RD_RANGE");
					
					// encrypt the card number
					encryptCardNumber = CardEncryption.encrypt(cardNumber);

					DBUtil dbutil = new DBUtil();
					if (dbutil.isCardExistEncrypt(encryptCardNumber, issuerId)) {
						flag = false;
						dbutil.updateSerialNo(productId, issuerId, nextSerialNo + 1, low3rdRange+1);
						continue;
					} else {
						flag = true;
						dbutil.updateSerialNo(productId, issuerId, nextSerialNo + 1, low3rdRange+1);
					}

					//objCardsDto.setCardNumber(Long.valueOf(cardNumber));
					Long cardId = Long.valueOf(IdsGenartion.GenerateCardId()+cardNumber.substring(cardNumber.length()-4));
					objCardsDto.setCardNumber(cardId.longValue());
					
					// assign masked card number
					String maskedCardNo = StringUtil.getMaskedCardNo(cardNumber);
					objCardsDto.setMaskedCardNo(maskedCardNo);
					
					// assign encrypted card number
					objCardsDto.setEncryptedCardNo(encryptCardNumber);

					serviceCode = rs.getString("SERVICECODE");
					pinRequired = rs.getString("PIN_REQUIRED");

					logger.error("serviceCode no is = " + serviceCode);
					logger.error("pinRequired no is = " + pinRequired);

					pinLength = rs.getInt("PIN_LENGTH");

					logger.error("pinLength no is = " + pinLength);

					cvkIndex = (String) indexList.get(productId + "CVK");
					pvkIndex = (String) indexList.get(productId + "PVK");
					cvk2Index = (String) indexList.get(productId + "CV2K");
					ppkIndex = (String) indexList.get(productId + "PPK");

					logger.error("cvkIndex no is = " + cvkIndex);
					logger.error("pvkIndex no is = " + pvkIndex);
					logger.error("cvk2Index no is = " + cvk2Index);
					logger.error("ppkIndex no is = " + ppkIndex);

					String cardValidity = rs.getString("FIRSTISSUE_VALIDITY");

					Date now = new Date(System.currentTimeMillis());

					SimpleDateFormat dfcvv = new SimpleDateFormat("yyMM");
					//SimpleDateFormat dfcvv2 = new SimpleDateFormat("MMyy");
					SimpleDateFormat dfcvv2 = new SimpleDateFormat("yyMM");
					SimpleDateFormat dfdb = new SimpleDateFormat("MMyy");
					SimpleDateFormat dftrack = new SimpleDateFormat("yyMM");

					if(expExtendInterval != null && !"".equals(expExtendInterval)){
						now = DateUtil.convertExpiryDateStringToDate(oldExp);
						now.setYear(now.getYear() + Integer.valueOf(expExtendInterval).intValue());
					}else{
						now.setYear(now.getYear() + Integer.valueOf(cardValidity).intValue());
					}

					expiryDateDb = dfdb.format(now);
					objCardsDto.setCardExpDate(expiryDateDb);

					expiryDateCVV = dfcvv.format(now);
					expiryDateCVV2 = dfcvv2.format(now);
					expiryDateTrack = dftrack.format(now);

					int ret;
					byte[] pvv = new byte[6];
					byte[] PINBlock = new byte[8];
					byte[] cvv = new byte[2];
					byte[] cvv2 = new byte[2];
					byte[] ePIN = new byte[8];
					byte[] icvv = new byte[2];

					// check PIN required or NOT
					if (pinRequired != null && pinRequired.equals("Y")) {

						ret = EracomPHW.generateClearRandomPIN(6, ePIN);
						if (ret == 0) {
							ret = EracomPHW.buildPINBlockFromEncryptedPIN(cardNumber, ISOUtil.hexString(ePIN), PINBlock, pinLength, ppkIndex);
							if(ret == 0){
								objCardsDto.setPinBlock(ISOUtil.hexString(PINBlock));
							}else{
								throw new Exception("Generating PIN Block Error on method buildPINBlockFromEncryptedPIN");
							}
						} else {
							throw new Exception("Generating PIN Block Error on method generateClearRandomPIN");
						}

						ret = EracomPHW.generatePVVFromEncryptedPIN(Integer
								.valueOf(pvkIndex).intValue(), cardNumber,
								ppkIndex,
								EracomPHW.PIN_FORMAT_ANSI, ISOUtil
								.hexString(ePIN), pinLength, pvv);
						if (ret == 0) {
							PVV = ISOUtil.hexString(pvv);
							objCardsDto.setPvvOffSet(Long.valueOf(ISOUtil
									.hexString(pvv)));
						} else {
							throw new Exception("Generating PVV Error");
						}

					}

					System.out.println("PVV :: " + PVV);
					logger.error("PVV :: " + PVV);

					// CVV generation
					ret = EracomPHW.GenerateCVV(Integer.valueOf(cvkIndex)
							.intValue(), cardNumber, expiryDateCVV,
							serviceCode, cvv);
					if (ret == 0) {
						CVV = ISOUtil.hexString(cvv);
						CVV = CVV.substring(CVV.length() - 4, CVV.length() - 1);
						objCardsDto.setCvv(Integer.valueOf(CVV));
					} else {
						logger.error("Generating CVV Error");
						throw new Exception("Generating CVV Error");
					}

					// CVV2 generation
					ret = EracomPHW.GenerateCVV(Integer.valueOf(cvk2Index)
							.intValue(), cardNumber, expiryDateCVV2, ICacisiss.IEmboss.CVV2_SERVICE_CODE,
							cvv2);
					if (ret == 0) {
						CVV2 = ISOUtil.hexString(cvv2);
						CVV2 = CVV2.substring(CVV2.length() - 4,
								CVV2.length() - 1);
						objCardsDto.setCvv2(Integer.valueOf(CVV2));
					} else {
						logger.error("Generating CVV2 Error");
						throw new Exception("Generating CVV2 Error");
					}

					// iCVV generation
					ret = EracomPHW.GenerateCVV(Integer.valueOf(cvkIndex)
							.intValue(), cardNumber, expiryDateCVV,
							ICacisiss.IEmboss.ICVV_SERVICE_CODE, icvv);
					if (ret == 0) {
						iCVV = ISOUtil.hexString(icvv);
						iCVV = iCVV.substring(iCVV.length() - 4, iCVV.length() - 1);
						//objCardsDto.setIcvv(Integer.valueOf(iCVV));
						objCardsDto.setIcvv(iCVV);
						System.out.println("iCVV value is  :: " +iCVV);
					} else {
						logger.error("Generating iCVV Error");
						throw new Exception("Generating iCVV Error");
					}

					//CVV = "000";
					//CVV2 = "000";
					objCardsDto.setCvv(Integer.valueOf(CVV));
					objCardsDto.setCvv2(Integer.valueOf(CVV2));

					String track2Data = "";
					String track1Data = "";
					String nameOnCard = embossName.toUpperCase();

					if (pinRequired != null && pinRequired.equals("Y")) {
						track1Data = cardNumber + "^" + nameOnCard + "/^" + expiryDateTrack + serviceCode + pvkIndex + "000000000000" + ICacisiss.IEmboss.BET_SERVICECODE_AND_CVV + CVV + ICacisiss.IEmboss.AFTER_CVV;
						track2Data = cardNumber + "=" + expiryDateTrack + serviceCode + pvkIndex + "000000000" + CVV;
					} else {
						track1Data = cardNumber + "^" + nameOnCard + "/^" + expiryDateTrack + serviceCode + ICacisiss.IEmboss.BET_SERVICECODE_AND_CVV + CVV + ICacisiss.IEmboss.AFTER_CVV;
						track2Data = cardNumber + "=" + expiryDateTrack + serviceCode + CVV;
					}

					logger.error("track1Data no is = " + track1Data);
					logger.error("track2Data no is = " + track2Data);

					objCardsDto.setTrack1(track1Data);
					objCardsDto.setTrack2(track2Data);

				} else {
					logger.error("<<< NO VALID DATA TO GENERATE CARD NUMBER >>> query returned no result set.");
					throw new Exception("<<< NO VALID DATA TO GENERATE CARD NUMBER >>>");
				}
			} catch (Exception vep) {
				throw vep;
			}
		}
		logger.error("Generated Card Number : " + cardNumber);
		System.out.println("Generated Card Number : " + cardNumber);
		return objCardsDto;

	}

	public synchronized CardsDto getRePINData(CardsDto objCardsDto, String ppkIndex, String pvkIndex, int pinLength) throws Exception {

		try{

			int ret;
			byte[] pvv = new byte[6];
			byte[] PINBlock = new byte[8];
			byte[] ePIN = new byte[8];

			String cardNumber = String.valueOf(objCardsDto.getCardNumber());

			ret = EracomPHW.generateClearRandomPIN(6, ePIN);
			if (ret == 0) {
				ret = EracomPHW.buildPINBlockFromEncryptedPIN(cardNumber, ISOUtil.hexString(ePIN), PINBlock, pinLength, ppkIndex);
				if(ret == 0){
					objCardsDto.setPinBlock(ISOUtil.hexString(PINBlock));
				}else{
					throw new Exception("Generating PIN Block Error on method buildPINBlockFromEncryptedPIN");
				}
			} else {
				throw new Exception("Generating PIN Block Error on method generateClearRandomPIN");
			}

			ret = EracomPHW.generatePVVFromEncryptedPIN(Integer.valueOf(pvkIndex).intValue(), cardNumber, ppkIndex, EracomPHW.PIN_FORMAT_ANSI, ISOUtil.hexString(ePIN), pinLength, pvv);
			if (ret == 0) {
				objCardsDto.setPvvOffSet(Long.valueOf(ISOUtil.hexString(pvv)));
			} else {
				throw new Exception("Generating PVV Error");
			}

		}catch (Exception e) {
			throw e;
		}

		return objCardsDto;
	}

	public int getCheckDigit(String number) {
		try {
			if (number.equals(""))
				return -1;
			char[] digits = number.toCharArray();
			int sum = 0;
			int multiplier = 1;
			for (int i = digits.length - 1; i >= 0; i--) {
				if (multiplier == 1)
					multiplier = 2;
				else if (multiplier == 2)
					multiplier = 1;
				int multiple = Integer.parseInt(String.valueOf(digits[i])) * multiplier;
				sum = sum + (multiple % 10) + (multiple / 10);
			}
			return (10 - (sum % 10)) % 10;
		} catch (Exception e) {
			return -1;
		}
	}

	public synchronized void ReplacementCardGeneration(
			ApplicationProcessDto objAppProcessDto, Map insexList,
			int cardHolderType, String expExtendInterval, String oldExp, 
			ApplicationProcessDto objAppProcessDtoOriginal, 
			String cardProduct, String issuerId, CardProductDto objCardProductDto, CardsDto objCardsDtoOrigi) throws TPlusException {

		CardsDto objCardsDto = null;

		try {
			String cardProductIds = cardProduct;
			int embossNameLength = ICacisiss.IEmboss.TRACK1_LENGTH;

			String embossName = objAppProcessDtoOriginal.getEmbossingName();
			//embossName = StringUtil.RPAD(embossName, embossNameLength, " ");

			String branchId = objAppProcessDtoOriginal.getBranchId();

			if (cardProductIds != null) {

				objCardsDto = new CardsDto();

				// call the method to generate the card number
				objCardsDto = getNewCardInfo(cardProductIds, objCardsDto, insexList, "", expExtendInterval, oldExp, embossName, issuerId, branchId);

				objCardsDto.setBranchId(branchId);

				objCardsDto.setIssuerId(objAppProcessDto.getIssuerId());
				objCardsDto.setCardProductId(cardProductIds);
				objCardsDto.setCardHolderType(cardHolderType);
				objCardsDto.setCardStatusId(CommonCodes.CARD_NEW);

				// setting the customer id from ApplicationProcessDto
				objCardsDto.setCustomerId(objAppProcessDtoOriginal.getCustomerId());

				objCardsDto.setEffectiveDate(new Date());
				objCardsDto.setPinDisabled(ICacisiss.ICardGeneration.PIN_DISABLE_VALUE);
				objCardsDto.setStatus("A");
				objCardsDto.setLastUpdatedBy(objAppProcessDto.getUserId());
				objCardsDto.setUpdatedDate(new Date());

				// Setting remaining card data by generating random numbers
				getCardData(objCardsDto);

				// Mapping Settings(relations like one-to-many )
				//setCardAccountMapping(objAppProcessDto, objCardsDto);
				setCardAccountMappingReplacement(objAppProcessDto, objCardsDto, objCardsDtoOrigi);

				System.out.println("main card holder CardNumber is:" + objCardsDto.getCardNumber());
				System.out.println("main card holder Expire Date is:" + objCardsDto.getCardExpDate());

			}

		} catch (Exception e) {
			System.out.println("Error while generating the Primary card number"
					+ e.getMessage());
			throw new TPlusException(
					"while generating the Primary card number: " + e);
		} finally {
		}

	}

	public synchronized void ProductChangeCardGeneration(
			ApplicationProcessDto objAppProcessDto, Map insexList,
			CardChangeDto objCardChangeDto, String expExtendInterval, String oldExp, 
			CardsDto objCardsDtoPrimary, ArrayList supplist,
			ApplicationProcessDto objAppProcessDtoOriginal, 
			String issuerId, int embossNameLength)
					throws TPlusException {

		CardsDto objCardsDto = null;

		try {
			CardBatchProcessManager objBatchProcessManager = new CardBatchProcessManager();
			String cardProductIds = objCardChangeDto.getChangeCardProduct();

			String embossName = objAppProcessDtoOriginal.getEmbossingName();
			//embossName = StringUtil.RPAD(embossName, embossNameLength, " ");

			String branchId = objAppProcessDto.getBranchId();

			if (cardProductIds != null) {

				objCardsDto = new CardsDto();

				// call the method to generate the card number
				objCardsDto = getNewCardInfo(cardProductIds, objCardsDto, insexList, cardProductIds, expExtendInterval, oldExp, embossName, issuerId, branchId);

				objCardsDto.setBranchId(branchId);

				objCardsDto.setIssuerId(issuerId);
				objCardsDto.setCardProductId(cardProductIds);
				objCardsDto.setCardHolderType(CommonCodes.PRIMARYCARD_HOLDER);
				objCardsDto.setCardStatusId(CommonCodes.CARD_NEW);

				// setting the customer id from ApplicationProcessDto
				objCardsDto.setCustomerId(objAppProcessDto.getCustomerId());

				objCardsDto.setEffectiveDate(new Date());
				objCardsDto.setPinDisabled(ICacisiss.ICardGeneration.PIN_DISABLE_VALUE);
				objCardsDto.setStatus("A");
				objCardsDto.setLastUpdatedBy(objAppProcessDto.getUserId());
				objCardsDto.setUpdatedDate(new Date());

				// Setting remaining card data by generating random numbers
				getCardData(objCardsDto);

				// Mapping Settings(relations like one-to-many )
				setCardAccountMapping(objAppProcessDto, objCardsDto);

				objCardsDto.setOldCradNo(String.valueOf(objCardChangeDto.getCardNo()));
				objCardsDto.setCustName(objAppProcessDto.getCustomerName());
				objCardsDto.setNricId(objAppProcessDto.getIdNumber());

				System.out.println("main card holder CardNumber is:"
						+ objCardsDto.getCardNumber());
				System.out.println("main card holder Expire Date is:"
						+ objCardsDto.getCardExpDate());

				// get all supplementary card and regenerate new cards for those
				if(supplist != null && supplist.size() > 0){
					for (Iterator it = supplist.iterator(); it.hasNext();) {
						CardsDto objCardsDto2 = (CardsDto)it.next();
						ApplicationProcessDto objApplicationProcessDto = objBatchProcessManager.getCustomerById(objCardsDto2.getCustomerId());
						embossName = objApplicationProcessDto.getEmbossingName();
						objCardsDto = new CardsDto();

						// call the method to generate the card number
						objCardsDto = getNewCardInfo(cardProductIds, objCardsDto, insexList, cardProductIds, expExtendInterval, oldExp, embossName, issuerId, branchId);

						objCardsDto.setBranchId(branchId);

						objCardsDto.setIssuerId(issuerId);
						objCardsDto.setCardProductId(cardProductIds);
						objCardsDto.setCardHolderType(CommonCodes.SUPPLEMENTARYCARD_HOLDER);
						objCardsDto.setCardStatusId(CommonCodes.CARD_NEW);

						// setting the customer id from ApplicationProcessDto
						objCardsDto.setCustomerId(objApplicationProcessDto.getCustomerId());

						objCardsDto.setEffectiveDate(new Date());
						objCardsDto.setPinDisabled(ICacisiss.ICardGeneration.PIN_DISABLE_VALUE);
						objCardsDto.setStatus("A");
						objCardsDto.setLastUpdatedBy(objAppProcessDto.getUserId());
						objCardsDto.setUpdatedDate(new Date());

						// Setting remaining card data by generating random numbers
						getCardData(objCardsDto);

						// Mapping Settings(relations like one-to-many )
						setCardAccountMapping(objAppProcessDto, objCardsDto);

						objCardsDto.setOldCradNo(String.valueOf(objCardsDto2.getCardNumber()));
						objCardsDto.setCustName(objApplicationProcessDto.getCustomerName());
						objCardsDto.setNricId(objApplicationProcessDto.getIdNumber());
					}
				}

			}

		} catch (Exception e) {
			System.out.println("Error while generating the Primary card number"
					+ e.getMessage());
			throw new TPlusException(
					"while generating the Primary card number: " + e);
		} finally {
		}

	}

	public synchronized void SupplementaryCardGeneration(
			ApplicationProcessDto objAppProcessDto, Map insexList,
			int cardHolderType, ApplicationProcessDto objAppProcessDtoSuppl,
			String cardProduct, String issuerID, int embossNameLength) throws TPlusException {

		CardsDto objCardsDto = null;

		try {
			String cardProductIds = cardProduct;

			String embossName = objAppProcessDtoSuppl.getEmbossingName();
			//embossName = StringUtil.RPAD(embossName, embossNameLength, " ");

			String branchId = objAppProcessDtoSuppl.getBranchId();

			if (cardProductIds != null) {

				objCardsDto = new CardsDto();

				// call the method to generate the card number
				objCardsDto = getNewCardInfo(cardProductIds, objCardsDto, insexList, "", "", "", embossName, issuerID, branchId);

				objCardsDto.setBranchId(branchId);

				objCardsDto.setIssuerId(issuerID);
				objCardsDto.setCardProductId(cardProductIds);
				objCardsDto.setCardHolderType(cardHolderType);
				objCardsDto.setCardStatusId(CommonCodes.CARD_NEW);

				// setting the customer id from ApplicationProcessDto
				objCardsDto.setCustomerId(objAppProcessDtoSuppl.getCustomerId());

				objCardsDto.setEffectiveDate(new Date());
				objCardsDto.setPinDisabled(ICacisiss.ICardGeneration.PIN_DISABLE_VALUE);
				objCardsDto.setStatus("A");
				objCardsDto.setLastUpdatedBy(objAppProcessDto.getUserId());
				objCardsDto.setUpdatedDate(new Date());

				// Setting remaining card data by generating random numbers
				getCardData(objCardsDto);

				// Mapping Settings(relations like one-to-many )
				setCardAccountMapping(objAppProcessDto, objCardsDto);

				System.out.println("main card holder CardNumber is:"
						+ objCardsDto.getCardNumber());
				System.out.println("main card holder Expire Date is:"
						+ objCardsDto.getCardExpDate());

			}

		} catch (Exception e) {
			System.out.println("Error while generating the Primary card number"
					+ e.getMessage());
			throw new TPlusException(
					"while generating the Primary card number: " + e);
		} finally {
		}

	}

	public synchronized void RenewalCardGeneration(
			ApplicationProcessDto objApplicationProcessDto,
			CardsDto objCardsDto, Map indexList,
			CardProductDto objCardProductDto, String cardNo, String expExtendInterval, String oldExp) throws TPlusException {

		try {

			// call the method to generate the card number
			objCardsDto = getCardInfoUpdate(objApplicationProcessDto, objCardsDto, indexList, objCardProductDto, cardNo, expExtendInterval, oldExp);

			objCardsDto.setOc("1");

			System.out.println("main card holder CardNumber is:"
					+ objCardsDto.getCardNumber());
			System.out.println("main card holder Expire Date is:"
					+ objCardsDto.getCardExpDate());

		} catch (Exception e) {
			System.out.println("Error while generating the Primary card number"
					+ e.getMessage());
			throw new TPlusException(
					"while generating the Primary card number: " + e);
		} finally {
		}

	}

	private synchronized CardsDto getCardInfoUpdate(
			ApplicationProcessDto objApplicationProcessDto,
			CardsDto objCardsDto, Map indexList,
			CardProductDto objCardProductDto, String cardNo, String expExtendInterval, String oldExp)
					throws Exception {

		String cardNumber = "";
		String expiryDateDb = "";
		String expiryDateCVV = "";
		String expiryDateCVV2 = "";
		String expiryDateTrack = "";
		String cvkIndex = "";
		String pvkIndex = "";
		String cvk2Index = "";
		String serviceCode = "";

		String CVV = "";
		String CVV2 = "";
		String iCVV = "";

		String productId = "";
		int embossNameLength = 0;

		logger.error("in getCardInfoUpdate method");

		productId = objCardProductDto.getCardProductId();
		serviceCode = objCardProductDto.getServiceCode();
		embossNameLength = ICacisiss.IEmboss.TRACK1_LENGTH;

		String nameOnCard = objApplicationProcessDto.getEmbossingName();
		//nameOnCard = StringUtil.RPAD(nameOnCard, embossNameLength, " ");

		try {

			cardNumber = String.valueOf(cardNo);
			//cardNumber = cardNumber + getCheckDigit(cardNumber);

			logger.error("card no :: " + cardNumber);

			objCardsDto.setCardNumber(Long.valueOf(cardNumber));

			cvkIndex = (String) indexList.get(productId + "CVK");
			cvk2Index = (String) indexList.get(productId + "CV2K");
			pvkIndex = (String) indexList.get(productId + "PVK");

			int cardValidity = objCardProductDto.getRenewalIssueValidity();
			logger.error("cardValidity :: " + cardValidity);

			Date now = new Date(System.currentTimeMillis());

			SimpleDateFormat dfcvv = new SimpleDateFormat("yyMM");
			//SimpleDateFormat dfcvv2 = new SimpleDateFormat("MMyy");
			SimpleDateFormat dfcvv2 = new SimpleDateFormat("yyMM");
			SimpleDateFormat dfdb = new SimpleDateFormat("MMyy");
			SimpleDateFormat dftrack = new SimpleDateFormat("yyMM");

			/*if(expExtendInterval != null && !"".equals(expExtendInterval)){
				now = DateUtil.convertExpiryDateStringToDate(oldExp);
				now.setYear(now.getYear() + Integer.valueOf(expExtendInterval).intValue());
			}else{
				now.setYear(now.getYear() + Integer.valueOf(cardValidity).intValue());
			}*/

			now = DateUtil.convertExpiryDateStringToDate(oldExp);
			// this one month adding since need to renew from one month later
			now.setMonth(now.getMonth() + 1);
			// month adding end
			now.setYear(now.getYear() + Integer.valueOf(cardValidity).intValue());

			expiryDateDb = dfdb.format(now);
			objCardsDto.setCardExpDate(expiryDateDb);

			expiryDateCVV = dfcvv.format(now);
			expiryDateCVV2 = dfcvv2.format(now);
			expiryDateTrack = dftrack.format(now);

			int ret;
			byte[] cvv = new byte[2];
			byte[] cvv2 = new byte[2];
			byte[] icvv = new byte[2];

			ret = EracomPHW.GenerateCVV(Integer.valueOf(cvkIndex).intValue(),
					cardNumber, expiryDateCVV, serviceCode, cvv);
			if (ret == 0) {
				CVV = ISOUtil.hexString(cvv);
				CVV = CVV.substring(CVV.length() - 4, CVV.length() - 1);
				objCardsDto.setCvv(Integer.valueOf(CVV));
			} else {
				throw new Exception("Generating CVV Error");
			}

			ret = EracomPHW.GenerateCVV(Integer.valueOf(cvk2Index).intValue(),
					cardNumber, expiryDateCVV2, ICacisiss.IEmboss.CVV2_SERVICE_CODE, cvv2);
			if (ret == 0) {
				CVV2 = ISOUtil.hexString(cvv2);
				CVV2 = CVV2.substring(CVV2.length() - 4, CVV2.length() - 1);
				objCardsDto.setCvv2(Integer.valueOf(CVV2));
			} else {
				throw new Exception("Generating CVV2 Error");
			}

			// iCVV generation
			ret = EracomPHW.GenerateCVV(Integer.valueOf(cvkIndex).intValue(), cardNumber, expiryDateCVV, ICacisiss.IEmboss.ICVV_SERVICE_CODE, icvv);
			if (ret == 0) {
				iCVV = ISOUtil.hexString(icvv);
				iCVV = iCVV.substring(iCVV.length() - 4, iCVV.length() - 1);
				//objCardsDto.setIcvv(Integer.valueOf(iCVV));
				objCardsDto.setIcvv(iCVV);
				System.out.println("iCVV value is  :: " +iCVV);
			} else {
				logger.error("Generating iCVV Error");
				throw new Exception("Generating iCVV Error");
			}

			objCardsDto.setCvv(Integer.valueOf(CVV));
			objCardsDto.setCvv2(Integer.valueOf(CVV2));

			String track2Data = "";
			String track1Data = "";

			track1Data = cardNumber + "^" + nameOnCard + "/^" + expiryDateTrack + serviceCode + pvkIndex + "000000000000" + ICacisiss.IEmboss.BET_SERVICECODE_AND_CVV + CVV + ICacisiss.IEmboss.AFTER_CVV;
			track2Data = cardNumber + "=" + expiryDateTrack + serviceCode + pvkIndex + "000000000" + CVV;

			//track1Data = cardNumber + "^" + nameOnCard + "/^" + expiryDateTrack + serviceCode + ICacisiss.IEmboss.BET_SERVICECODE_AND_CVV + CVV + ICacisiss.IEmboss.AFTER_CVV;
			//track2Data = cardNumber + "=" + expiryDateTrack + serviceCode + CVV;

			objCardsDto.setTrack1(track1Data);
			objCardsDto.setTrack2(track2Data);
			logger.error("track1Data :: " + track1Data);
			logger.error("track2Data :: " + track2Data);

		} catch (Exception vep) {
			throw vep;
		}
		System.out.println("Generated Card Number : " + cardNumber);
		logger.error("Generated New Card Number : " + cardNumber);
		return objCardsDto;
	}

}
