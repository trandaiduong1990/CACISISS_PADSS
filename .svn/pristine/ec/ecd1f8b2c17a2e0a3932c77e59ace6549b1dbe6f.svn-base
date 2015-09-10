package org.transinfo.cacis.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.transinfo.cacis.TPlusCodes;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.HibernetDAO.HibernetFactory;
import org.transinfo.cacis.dto.cardproduction.ApplicationProcessDto;
import org.transinfo.cacis.dto.cardproduction.CardsDto;
import org.transinfo.cacis.dto.csr.CustomerInfoDto;
import org.transinfo.cacis.dto.settings.CardProductDto;
import org.transinfo.cacis.formbean.settings.HSMDataBean;
import org.transinfo.cacis.report.db.DBManager;

@SuppressWarnings({"unchecked" , "unused"})
public class DBUtil {

	private static Logger logger = Logger.getLogger(DBUtil.class);

	private DBManager objDBManager = null;

	private String poolName = "";

	/**
	 * Constructor for DBUtil.
	 * 
	 * @param poolName
	 *            Database properties file name.
	 */
	public DBUtil() {

	}

	public String getFieldValue(String sqlStr, String fieldName) {
		String rtnValue = "";
		Session session = HibernetFactory.currentSession();
		try {
			Connection con = session.connection();
			Statement stmt = con.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			ResultSet objrs = stmt.executeQuery(sqlStr.toString());
			if (objrs.next())
				rtnValue = objrs.getString(fieldName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rtnValue;
	}

	public ArrayList getIssuerList(String UserType, String UserIssuerId) {
		String sSQL = new String();
		boolean Val = true;
		Hashtable zoAcqDetails = new Hashtable();
		String[] ResultArr = null;
		ArrayList zoaIssuerArr = new ArrayList();
		Session session = HibernetFactory.currentSession();
		System.out.println(">>>>>>>>>> UserType: " + UserType);

		sSQL = "SELECT ISSUER_ID, ISSUER_NAME FROM ISSUER_MASTER WHERE STATUS!='ASP' ";

		if ((UserType != null) && ((UserType.trim().equals("ISSUSER"))
				|| (UserType.trim().equals("ISSADMIN"))))
			sSQL += "AND ISSUER_ID='" + UserIssuerId + "'  ";
		sSQL += "ORDER BY ISSUER_NAME";
		System.out.println("Issuer Sql: " + sSQL);
		try {
			Connection con = session.connection();
			Statement stmt = con.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = stmt.executeQuery(sSQL.toString());
			while (rs.next()) {
				zoAcqDetails = new Hashtable();
				zoAcqDetails.put("ISSUER_ID", rs.getString("ISSUER_ID"));
				zoAcqDetails.put("ISSUER_NAME", rs.getString("ISSUER_NAME"));
				zoaIssuerArr.add(zoAcqDetails);
			}
		} catch (Exception ex) {
			System.out.println("Database error while retrieving  ISSUER data."
					+ ex.toString());
			ex.printStackTrace();
		}
		return zoaIssuerArr;
	}

	public ArrayList getTranxCodes() throws Exception {
		ArrayList arlList = new ArrayList();
		Object[] objArr = null;
		Session session = HibernetFactory.currentSession();
		Connection con = session.connection();
		System.out.println("con = " + con.toString());
		Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_READ_ONLY);
		System.out.println("stmt = " + stmt.toString());
		ResultSet resultSet = stmt
				.executeQuery("SELECT TRANX_CODE, TRANX_CODE FROM TRANXCODES");
		System.out.println("resultSet = " + resultSet.toString());
		HashMap hmErrorCodes = new HashMap();
		while (resultSet.next()) {
			objArr = new Object[2];
			objArr[0] = resultSet.getString("TRANX_CODE");
			objArr[1] = resultSet.getString("TRANX_CODE");
			arlList.add(objArr);
		}
		return arlList;
	}

	public ArrayList getCodeList(String groupId) throws Exception {
		ArrayList arlList = new ArrayList();
		Object[] objArr = null;
		Session session = HibernetFactory.currentSession();
		Connection con = session.connection();
		System.out.println("con = " + con.toString());
		Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_READ_ONLY);
		System.out.println("stmt = " + stmt.toString());
		ResultSet resultSet = stmt
				.executeQuery("SELECT  CODE_ID, CODE_DESCRIPTION FROM CODE_MASTER WHERE GROUP_ID ='"
						+ groupId + "' ORDER BY CODE_DESCRIPTION");
		System.out.println("resultSet = " + resultSet.toString());

		HashMap hmErrorCodes = new HashMap();
		while (resultSet.next()) {
			objArr = new Object[2];
			objArr[0] = resultSet.getString("CODE_ID");
			objArr[1] = resultSet.getString("CODE_DESCRIPTION");
			arlList.add(objArr);
		}
		return arlList;
	}

	public String getTodayDate() {
		String strtodayDate = getFieldValue(
				"SELECT TO_CHAR(SYSDATE,'DD/MM/YYYY') AS TODAYDATE FROM DUAL",
				"TODAYDATE");
		return strtodayDate;
	}
	
	public String getCardProductType(String cardProductId){
		String res = "";
		
		CardProductDto objDto = null;
		Transaction tx = null;
		
		try {
			Session session = HibernetFactory.currentSession();
            tx = session.beginTransaction();
			objDto = (CardProductDto)session.get(CardProductDto.class,cardProductId);
			tx.commit();

			res = objDto.getCardProductType().getCardProductType();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			HibernetFactory.closeSession();
	 	}
		
		return res;
	}
	
	public String getCardProductEmbossLength(String cardProductId){
		String res = "";
		
		CardProductDto objDto = null;
		Transaction tx = null;
		
		try {
			Session session = HibernetFactory.currentSession();
            tx = session.beginTransaction();
			objDto = (CardProductDto)session.get(CardProductDto.class,cardProductId);
			tx.commit();

			res = String.valueOf(objDto.getEmbossNameLength());
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			HibernetFactory.closeSession();
	 	}
		
		return res;
	}
	
	public int getCardDetails(String cardNo){
		int res = 0;
		
		CardsDto objDto = null;
		Transaction tx = null;
		
		try {
			Session session = HibernetFactory.currentSession();
            tx = session.beginTransaction();
			objDto = (CardsDto)session.get(CardsDto.class,Long.valueOf(cardNo).longValue());
			tx.commit();

			res = objDto.getCardHolderType();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			HibernetFactory.closeSession();
	 	}
		
		return res;
	}
	
	public int getCardProductEmbossNameLength(String cardProductId){
		int res = 0;
		
		CardProductDto objDto = null;
		Transaction tx = null;
		
		try {
			Session session = HibernetFactory.currentSession();
            tx = session.beginTransaction();
			objDto = (CardProductDto)session.get(CardProductDto.class,cardProductId);
			tx.commit();

			res = objDto.getEmbossNameLength();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			HibernetFactory.closeSession();
	 	}
		
		return res;
	}
	
	public boolean isCardExist(String cardNumber, String issuerId) throws TPlusException {

		boolean isRecExist = false;
		StringBuffer sbf = new StringBuffer();
		Transaction tx = null;
		try {

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			sbf.append("from CardsDto cdo ");
			sbf.append("where cdo.cardNumber = " + Long.valueOf(cardNumber));
			sbf.append("and cdo.issuerId = '" + issuerId + "' ");

			Query qry = session.createQuery(sbf.toString());
			List listDocs = qry.list();

			if (listDocs.size() > 0) {
				isRecExist = true;
			}

			tx.commit();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}

			System.out.println("Error in isCardExist method" + e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving the isCardExist" + e);
		} finally {
			HibernetFactory.closeSession();
		}
		return isRecExist;
	}

	public boolean isCardExistEncrypt(String cardNumber, String issuerId) throws TPlusException {

		boolean isRecExist = false;
		StringBuffer sbf = new StringBuffer();
		Transaction tx = null;
		try {

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			sbf.append("from CardsDto cdo ");
			sbf.append("where cdo.encryptedCardNo = '" + cardNumber + "' ");
			sbf.append("and cdo.issuerId = '" + issuerId + "' ");

			Query qry = session.createQuery(sbf.toString());
			List listDocs = qry.list();

			if (listDocs.size() > 0) {
				isRecExist = true;
			}

			tx.commit();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}

			System.out.println("Error in isCardExist method" + e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving the isCardExist" + e);
		} finally {
			HibernetFactory.closeSession();
		}
		return isRecExist;
	}
	
	public void updateSerialNo(String cardProductId, String issuerId, int nextSerialNo, int low3rdRange) throws TPlusException {

		boolean isRecExist = false;
		StringBuffer sbf = new StringBuffer();
		Transaction tx = null;
		int count = 0;
		try {

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			logger.info("nextSerialNo :: " + nextSerialNo);
			logger.info("low3rdRange :: " + low3rdRange);
			
			if(nextSerialNo == 1000){
				logger.info("nextSerialNo == 1000");
				
				nextSerialNo = 0;
				
				logger.info("nextSerialNo :: " + nextSerialNo);
			
				String updateSql = "UPDATE CardProductDto SET cardNoNextValue=:nextVal,low3rdRange=:lowrange WHERE cardProductId=:productId and issuerId=:issId";
				count = session.createQuery(updateSql)
							.setString("issId", issuerId)
							.setString("productId",cardProductId)
							.setInteger("nextVal", nextSerialNo)
							.setString("lowrange",String.valueOf(low3rdRange))
							.executeUpdate();
							
			}else{
				
				String updateSql = "UPDATE CardProductDto SET cardNoNextValue=:nextVal WHERE cardProductId=:productId and issuerId=:issId";
				count = session.createQuery(updateSql)
							.setString("issId", issuerId)
							.setString("productId",cardProductId)
							.setInteger("nextVal", nextSerialNo)
							.executeUpdate();
				
			}

			tx.commit();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}

			System.out.println("Error in updateSerialNo method" + e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving the updateSerialNo" + e);
		} finally {
			HibernetFactory.closeSession();
		}
	}
	
    public HSMDataBean getHSM() throws Exception{
        
        HSMDataBean bean = new HSMDataBean();
        StringBuffer strSql = new StringBuffer();
		Transaction tx = null;
		
        try{
        	
        	DBManager objDBManager = new DBManager();

            strSql = new StringBuffer();
            strSql.append("SELECT HSM_IP, HSM_PORT, MAX_CONN, WEIGHT FROM HSM");

			ResultSet rs = objDBManager.executeSQL(strSql.toString(), true);

            if(rs.next()){
                System.out.println("Getting HSM Info ...... ");
                bean.setIp(rs.getString("HSM_IP"));
                bean.setPort(rs.getString("HSM_PORT"));
                bean.setMaxConn(rs.getString("MAX_CONN"));
                bean.setWeight(rs.getString("WEIGHT"));
            }else{
                throw new Exception("<<< NO VALID HSM >>>");
            }
        }catch(Exception vep) {
            System.out.println("Exception while getting HSM Information : "+vep.toString());
            throw vep;
        }
        
        System.out.println(bean.getIp());
        
        return bean;
    }
    
	public String getCardProductTypeByCardNo(String cardNo){
		String res = "";
		
		CardsDto objDto = null;
		Transaction tx = null;
		
		try {
			Session session = HibernetFactory.currentSession();
            tx = session.beginTransaction();
			objDto = (CardsDto)session.get(CardsDto.class,Long.valueOf(cardNo));

			res = objDto.getCardProductsDto().getCardProductType().getCardProductType();
			tx.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			HibernetFactory.closeSession();
	 	}
		
		return res;
	}
	
	public int getCardProductEmbossNameLengthByCardNo(String cardNo){
		int res = 0;
		
		CardsDto objDto = null;
		Transaction tx = null;
		
		try {
			Session session = HibernetFactory.currentSession();
            tx = session.beginTransaction();
			objDto = (CardsDto)session.get(CardsDto.class,Long.valueOf(cardNo));

			res = objDto.getCardProductsDto().getEmbossNameLength();
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			HibernetFactory.closeSession();
	 	}
		
		return res;
	}
	
	public String validateSuppl(String cardNo){
		String res = "";
		
		CardsDto objDto = null;
		Transaction tx = null;
		
		try {
			Session session = HibernetFactory.currentSession();
            tx = session.beginTransaction();
			objDto = (CardsDto)session.get(CardsDto.class,Long.valueOf(cardNo));
			
			if(objDto != null){
				if(objDto.getCardHolderType() == 1){
					if("CreditCard".equals(objDto.getCardProductsDto().getCardProductType().getCardProductType())){
						String el = String.valueOf(objDto.getCardProductsDto().getEmbossNameLength());
						res = "5#"+el;
					}else{
						res = "4#0";
					}
				}else{
					res = "3#0";
				}
			}else{
				res = "2#0";
			}
			
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			HibernetFactory.closeSession();
	 	}
		
		return res;
	}
	
	public ArrayList getCardProductList(String UserIssuerId) {
		StringBuffer sSQL = new StringBuffer();
		boolean Val = true;
		Hashtable zoAcqDetails = new Hashtable();
		String[] ResultArr = null;
		ArrayList zoaproductArr = new ArrayList();
		Session session = HibernetFactory.currentSession();

		sSQL.append("SELECT CARD_PRODUCT_ID, CARDPRODUCTNAME ");
		sSQL.append("FROM CARD_PRODUCTS ");
		sSQL.append("WHERE ISSUER_ID = '"+ UserIssuerId +"' ");
		sSQL.append("AND STATUS = 'A' ");
		sSQL.append("ORDER BY CARDPRODUCTNAME ");
		
		try {
			Connection con = session.connection();
			Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = stmt.executeQuery(sSQL.toString());
			while (rs.next()) {
				zoAcqDetails = new Hashtable();
				zoAcqDetails.put("CARD_PRODUCT_ID", rs.getString("CARD_PRODUCT_ID"));
				zoAcqDetails.put("CARDPRODUCTNAME", rs.getString("CARDPRODUCTNAME"));
				zoaproductArr.add(zoAcqDetails);
			}
		} catch (Exception ex) {
			System.out.println("Database error while retrieving card product data." + ex.toString());
			ex.printStackTrace();
		}
		return zoaproductArr;
	}
	
	public ArrayList getResponseCodeList() {
		StringBuffer sSQL = new StringBuffer();
		boolean Val = true;
		Hashtable zoAcqDetails = new Hashtable();
		String[] ResultArr = null;
		ArrayList zoaproductArr = new ArrayList();
		Session session = HibernetFactory.currentSession();

		sSQL.append("SELECT RESPONSE_CODE, REASON ");
		sSQL.append("FROM RESPONSE_CODE ");
		sSQL.append("WHERE RESPONSE_CODE <> '0' AND RESPONSE_CODE <> '00' ");
		sSQL.append("ORDER BY REASON ");
		
		try {
			Connection con = session.connection();
			Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = stmt.executeQuery(sSQL.toString());
			while (rs.next()) {
				zoAcqDetails = new Hashtable();
				zoAcqDetails.put("RESPONSE_CODE", rs.getString("RESPONSE_CODE"));
				zoAcqDetails.put("DETAIL", rs.getString("RESPONSE_CODE")+" - "+rs.getString("REASON"));
				zoaproductArr.add(zoAcqDetails);
			}
		} catch (Exception ex) {
			System.out.println("Database error while retrieving card product data." + ex.toString());
			ex.printStackTrace();
		}
		return zoaproductArr;
	}
	
	public ArrayList getCardProductTypeList() {
		StringBuffer sSQL = new StringBuffer();
		boolean Val = true;
		Hashtable zoAcqDetails = new Hashtable();
		String[] ResultArr = null;
		ArrayList zoaproductArr = new ArrayList();
		Session session = HibernetFactory.currentSession();

		sSQL.append("SELECT PRODUCT_TYPE_ID, PRODUCT_TYPE_DESC ");
		sSQL.append("FROM CARD_PRODUCT_TYPE ");
		sSQL.append("ORDER BY PRODUCT_TYPE_DESC ");
		
		try {
			Connection con = session.connection();
			Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = stmt.executeQuery(sSQL.toString());
			while (rs.next()) {
				zoAcqDetails = new Hashtable();
				zoAcqDetails.put("PRODUCT_TYPE_ID", rs.getString("PRODUCT_TYPE_ID"));
				zoAcqDetails.put("PRODUCT_TYPE_DESC", rs.getString("PRODUCT_TYPE_DESC"));
				zoaproductArr.add(zoAcqDetails);
			}
		} catch (Exception ex) {
			System.out.println("Database error while retrieving card product type data." + ex.toString());
			ex.printStackTrace();
		}
		return zoaproductArr;
	}

	public static void main(String s[]) throws Exception {
		String arlList = "";
		DBUtil instance = new DBUtil();
		arlList = instance.getCardProductType("4");
		System.out.println("card product type = " + arlList);
	}
	
	public String getCardProduct(String cardProductId){
		String res = "";
		
		CardProductDto objDto = null;
		//Transaction tx = null;
		
		try {
			Session session = HibernetFactory.currentSession();
           // tx = session.beginTransaction();
			objDto = (CardProductDto)session.get(CardProductDto.class,cardProductId);
			//tx.commit();

			//res = objDto.getAccountCreation();
			String cardPT = objDto.getCardProductType().getCardProductType();
			if("CreditCard".equals(cardPT)){
				res = "Y";
			}else{
				res = "N";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			HibernetFactory.closeSession();
	 	}
		
		return res;
	}
	
	public ApplicationProcessDto getCustomer(String customerId){
		ApplicationProcessDto objApplicationProcessDto = null;
		
		CardsDto objCardsDto = null;
		Transaction tx = null;
		
		try {
			
			Session session = HibernetFactory.currentSession();
            tx = session.beginTransaction();
            objCardsDto = (CardsDto)session.get(CardsDto.class,Long.valueOf(customerId));
            objApplicationProcessDto = (ApplicationProcessDto) session.get(ApplicationProcessDto.class, objCardsDto.getCustomerId());
			tx.commit();

		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			HibernetFactory.closeSession();
	 	}
		
		return objApplicationProcessDto;
	}
	
	public CustomerInfoDto getCustomerInfo(String customerId){
		CustomerInfoDto objCustomerInfoDto = null;
		
		CardsDto objCardsDto = null;
		Transaction tx = null;
		
		try {
			
			Session session = HibernetFactory.currentSession();
            tx = session.beginTransaction();
            objCardsDto = (CardsDto)session.get(CardsDto.class,Long.valueOf(customerId));
            objCustomerInfoDto = (CustomerInfoDto) session.get(CustomerInfoDto.class, objCardsDto.getCustomerId());
			tx.commit();

		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			HibernetFactory.closeSession();
	 	}
		
		return objCustomerInfoDto;
	}
	
	public List getGroupDetails(String groupID){
		
		List reasonList = new ArrayList();
		
		StringBuffer sbf = new StringBuffer();
		sbf.append("from DisputeGroupDetailsDto dgddto ");
		
		if(groupID != null && !"".equals(groupID)){
			sbf.append("where dgddto.disputeGroup.groupId='" + groupID + "' ");
		}
		sbf.append("order by dgddto.chargeBackReason ");
		
		try{
			
			Session session = HibernetFactory.currentSession();
			Query qry = session.createQuery(sbf.toString());
			reasonList = qry.list();
			
		}catch (Exception e) {
			logger.error(new Object(), e);
		} finally {
			HibernetFactory.closeSession();
		}
		
		return reasonList;
		
	}
	
	public List getStateDetails(){
		
		List reasonList = new ArrayList();
		
		StringBuffer sbf = new StringBuffer();
		sbf.append("from StateDto sDto ");
		sbf.append("order by sDto.stateDes ");
		
		try{
			
			Session session = HibernetFactory.currentSession();
			Query qry = session.createQuery(sbf.toString());
			reasonList = qry.list();
			
		}catch (Exception e) {
			logger.error(new Object(), e);
		} finally {
			HibernetFactory.closeSession();
		}
		
		return reasonList;
		
	}
	
	public List getCityDetails(String stateId){
		
		List reasonList = new ArrayList();
		
		StringBuffer sbf = new StringBuffer();
		sbf.append("from CityDto cdto ");
		sbf.append("where cdto.state.stateId = '"+stateId+"' ");
		sbf.append("order by cdto.cityDes ");
		
		try{
			
			Session session = HibernetFactory.currentSession();
			Query qry = session.createQuery(sbf.toString());
			reasonList = qry.list();
			
		}catch (Exception e) {
			logger.error(new Object(), e);
		} finally {
			HibernetFactory.closeSession();
		}
		
		return reasonList;
		
	}
		
	public List getTownshipDetails(String city){
		
		List reasonList = new ArrayList();
		
		StringBuffer sbf = new StringBuffer();
		sbf.append("from TownShipDto tdto ");
		sbf.append("where tdto.city.cityId = '"+city+"' ");
		sbf.append("order by tdto.townshipDes ");
		
		try{
			
			Session session = HibernetFactory.currentSession();
			Query qry = session.createQuery(sbf.toString());
			reasonList = qry.list();
			
		}catch (Exception e) {
			logger.error(new Object(), e);
		} finally {
			HibernetFactory.closeSession();
		}
		
		return reasonList;
		
	}
	
	public static synchronized String getPKSeriesNextValue(){
		String seqId = "";
		
		try{
			
			long res = 0;
			
			Session session = HibernetFactory.currentSession();
			res = (Long) session.createSQLQuery(
					"select SEQ_PKSERIES.NEXTVAL as id from dual")
					.addScalar("id", Hibernate.LONG).uniqueResult();
			
			logger.info("PKSeriesNextValue Long value is :: " + res);
			
			seqId = String.valueOf(res);
			seqId = StringUtil.LPAD(seqId, 7, "0");
			
		}catch (Exception e) {
			logger.error(new Object(), e);
		}finally{
			//HibernetFactory.closeSession();
		}
		
		logger.info("PKSeriesNextValue String value is :: " + seqId + " ...");
		
		return seqId;
	}
	
	public static synchronized String getCardPKSeriesNextValue(){
		String seqId = "";
		
		try{
			
			long res = 0;
			
			Session session = HibernetFactory.currentSession();
			res = (Long) session.createSQLQuery(
					"select SEQ_CARDPK.NEXTVAL as id from dual")
					.addScalar("id", Hibernate.LONG).uniqueResult();
			
			logger.info("PKSeriesNextValue Long value is :: " + res);
			
			seqId = String.valueOf(res);
			seqId = StringUtil.LPAD(seqId, 6, "0");
			
		}catch (Exception e) {
			logger.error(new Object(), e);
		}finally{
			//HibernetFactory.closeSession();
		}
		
		logger.info("PKSeriesNextValue String value is :: " + seqId + " ...");
		
		return seqId;
	}
	
	public static synchronized String getSequenceNextValue(String seqName){
		String seqId = "";
		
		try{
			
			long res = 0;
			
			Session session = HibernetFactory.currentSession();
			res = (Long) session.createSQLQuery("select "+seqName+".NEXTVAL as id from dual").addScalar("id", Hibernate.LONG).uniqueResult();
			
			logger.info("PKSeriesNextValue Long value is :: " + res);
			
			seqId = String.valueOf(res);
			//seqId = StringUtil.LPAD(seqId, 7, "0");
			
		}catch (Exception e) {
			logger.error(new Object(), e);
		}finally{
			//HibernetFactory.closeSession();
		}
		
		logger.info("PKSeriesNextValue String value is :: " + seqId + " ...");
		
		return seqId;
	}
	
	public ResultSet getCardStatDetails(String cardNo) {
		
		StringBuffer sSQL = new StringBuffer();
		Session session = HibernetFactory.currentSession();
		
		ResultSet rs = null;

		sSQL.append("select * from ( ");
		sSQL.append("select ");
		sSQL.append(" STAT_ID,CARD_NUMBER,nvl(PREV_STAT_ID,'') as PREV_STAT_ID,nvl(PREV_STAT_AMT,'') as PREV_STAT_AMT,nvl(PREV_STAT_OUTSTAND_AMT,'') as PREV_STAT_OUTSTAND_AMT,STAT_AMT,STAT_MIN_AMT,TO_CHAR(STAT_DUE_DATE,'YYYY/MM/DD HH:mi:ss am') AS DUEDATE,STAT_FEE_AMT,STAT_INTEREST_AMT,TO_CHAR(STAT_CREATED_DATE,'YYYY/MM/DD HH:mi:ss am') AS CREDATE ");
		sSQL.append(" from customer_statement where card_number='"+cardNo+"' order by generated_date desc ");
		sSQL.append(")WHERE ROWNUM <= 1 ");
		
		try {
			
			Connection con = session.connection();
			Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery(sSQL.toString());
			
		} catch (Exception ex) {
			System.out.println("Database error while retrieving card product data." + ex.toString());
			ex.printStackTrace();
		}
		
		return rs;
	}
}
