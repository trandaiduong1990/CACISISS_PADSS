package org.transinfo.cacis.dataacess.daoimpl.oracle.key;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.transinfo.cacis.TPlusCodes;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.dataacess.HibernetDAO.HibernetFactory;
import org.transinfo.cacis.dataacess.dao.key.KeyIndexDAO;
import org.transinfo.cacis.dataacess.daoimpl.oracle.BaseDAOImpl;
import org.transinfo.cacis.dto.key.KeyIndexDto;
import org.transinfo.cacis.dto.key.KeyIndexSearchDto;

@SuppressWarnings("unchecked")
public class KeyIndexDAOImpl extends BaseDAOImpl implements KeyIndexDAO {

	public Collection search(KeyIndexSearchDto objSearchDto, int pageNo)
			throws TPlusException {
		// CardProductDto objCardProductDto = null;
		Collection objSearchCollection = null;
		StringBuffer sbf = new StringBuffer();
		try {
			System.out.println("Query............");
			sbf
					.append("select ki.id.cardProductId, cp.cardProductName, ki.id.keyType, ki.keyIndex, ");
			sbf.append(" ki.status,  to_char(ki.updatedDate,'dd-MM-yyyy'), ");
			sbf.append(" ki.userId, ki.id.transactionChannel, ki.status ");
			sbf.append(" FROM  KeyIndexDto ki, CardProductDto cp ");
			sbf.append(" where ki.id.cardProductId = cp.cardProductId ");
			//sbf.append(" and ki.status = 'A' ");

			if (objSearchDto.getIssuerId() != null
					&& !objSearchDto.getIssuerId().equals("")) {
				sbf.append(" and ki.id.issuerId = '"
						+ objSearchDto.getIssuerId() + "' ");
			}
			
			if (objSearchDto.getCardProductId() != null
					&& !objSearchDto.getCardProductId().equals("")) {
				sbf.append(" and ki.id.cardProductId = '"
						+ objSearchDto.getCardProductId() + "' ");
			}
			
			if (objSearchDto.getKeyType() != null
					&& !objSearchDto.getKeyType().equals("")) {
				sbf.append(" and ki.id.keyType = '"
						+ objSearchDto.getKeyType() + "' ");
			}
			
			if (objSearchDto.getTransactionalChannel() != null
					&& !objSearchDto.getTransactionalChannel().equals("")) {
				sbf.append(" and ki.id.transactionChannel = '"
						+ objSearchDto.getTransactionalChannel() + "' ");
			}
			
			if (objSearchDto.getStatus() != null
					&& !objSearchDto.getStatus().equals("")) {
				sbf.append(" and ki.status = '"
						+ objSearchDto.getStatus() + "' ");
			}

			objSearchCollection = getSearchList(sbf.toString(), pageNo);

			System.out.println("Size===> " + objSearchCollection.size());

		} catch (Exception e) {
			System.out.println("Error while retrieving the Search Info" + e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving the Search Info" + e);
		} finally {
			HibernetFactory.closeSession();
		}
		return objSearchCollection;
	}

	public boolean isRecordExist(KeyIndexDto objDto) throws TPlusException {

		boolean isRecExist = false;
		StringBuffer sbf = new StringBuffer();
		Transaction tx = null;
		try {

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			sbf
					.append("select ki.id.cardProductId FROM  KeyIndexDto ki, CardProductDto cp ");
			sbf.append(" where ki.id.issuerId = '" + objDto.id.getIssuerId()
					+ "' ");
			sbf.append(" and ki.id.cardProductId = '"
					+ objDto.id.getCardProductId() + "' ");
			sbf
					.append(" and ki.id.keyType = '" + objDto.id.getKeyType()
							+ "' ");
			sbf
					.append(" and ki.id.transactionChannel = '" + objDto.id.getTransactionChannel()
							+ "' ");
			sbf.append(" and ki.id.cardProductId = cp.cardProductId ");
			sbf.append(" and ki.status = 'A' ");

			Query qry = session.createQuery(sbf.toString());
			List listDocs = qry.list();

			System.out.println("listDocsSize==>" + listDocs.size());
			if (listDocs.size() > 0) {
				isRecExist = true;
			}

			tx.commit();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}

			System.out.println("Error in getKeyIndexForm method" + e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving the getKeyIndexForm" + e);
		} finally {
			HibernetFactory.closeSession();
		}
		return isRecExist;
	}

	public KeyIndexDto getKeyIndexForm(String issuerId, String cardProductId,
			String keyType, String tranxChannel) throws TPlusException {
		KeyIndexDto keyDto = null;
		Transaction tx = null;
		try {

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			keyDto = (KeyIndexDto) session.get(KeyIndexDto.class,
					new KeyIndexDto.Id(issuerId, cardProductId, keyType, tranxChannel));

			tx.commit();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}

			System.out.println("Error in getKeyIndexForm method" + e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving the getKeyIndexForm" + e);
		} finally {
			HibernetFactory.closeSession();
		}
		return keyDto;
	}

	public boolean add(KeyIndexDto objDto) throws TPlusException {

		boolean bolExecute = false;
		Transaction tx = null;
		try {

			Session session = HibernetFactory.currentSession();

			tx = session.beginTransaction();
			System.out.println("Saving........");
			session.save(objDto);
			System.out.println("KeyIndexDto object persisted to the database.");
			tx.commit();
			bolExecute = true;

		} catch (Exception ex) {
			if (tx != null) {
				tx.rollback();
			}
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving Info" + ex.getMessage());
		} finally {
			HibernetFactory.closeSession();
		}

		return bolExecute;
	}

	public boolean update(KeyIndexDto objDto) throws TPlusException {
		boolean bolExecute = false;
		Transaction tx = null;
		try {
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			session.update(objDto);
			tx.commit();
			System.out.println("KeyIndexDto object updated to the database.");
			bolExecute = true;
		}

		catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.out.println("Error while updating KeyIndexForm data " + e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while updating KeyIndexForm data" + e);

		} finally {
			HibernetFactory.closeSession();
		}

		return bolExecute;
	}

	public boolean delete(KeyIndexDto objDto) throws TPlusException {
		boolean bolExecute = false;
		Transaction tx = null;
		try {
			System.out.println("KeyIndexDto object deleted from the database.");
			Session session = HibernetFactory.currentSession();

			tx = session.beginTransaction();

			session.delete(objDto);
			tx.commit();
			bolExecute = true;
			System.out.println("KeyIndexDto object deleted from the database.");
		} catch (Exception ex) {
			if (tx != null) {
				tx.rollback();
			}
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while deleting" + ex.getMessage());
		} finally {
			HibernetFactory.closeSession();
		}

		return bolExecute;
	}

	public Map checkPPKAgainstCardProducts(String issuerID, String keyType,
			String pinPrintSerialNos) throws TPlusException {

		Map ppkList = new HashMap();
		int recCount = 0;
		int productCount = 0;
		StringBuffer sbf = new StringBuffer();
		StringBuffer sbfsidtinctProducts = new StringBuffer();
		StringBuffer sbfdistinctcards = new StringBuffer();
		StringBuffer sbfppk = new StringBuffer();
		Transaction tx = null;
		try {

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			sbfdistinctcards.append("select distinct ppdo.cardNumber ");
			sbfdistinctcards.append("from PinPrintingDto ppdo ");
			sbfdistinctcards.append("where ppdo.pinPringId in (" + pinPrintSerialNos + ") ");

			sbfsidtinctProducts.append("select distinct cpdo.cardProductId ");
			sbfsidtinctProducts.append("from CardsDto cdo, CardProductDto cpdo ");
			sbfsidtinctProducts.append("where cdo.cardProductId = cpdo.cardProductId ");
			sbfsidtinctProducts.append("and cdo.cardNumber in (" + sbfdistinctcards.toString() + ") ");

			Query productqry = session.createQuery(sbfsidtinctProducts.toString());
			List productList = productqry.list();
			productCount = productList.size();

			sbf.append("select kid.id.cardProductId ");
			sbf.append("from KeyIndexDto kid ");
			sbf.append("where kid.id.issuerId = '" + issuerID + "' ");
			sbf.append("and kid.id.cardProductId in (" + sbfsidtinctProducts.toString() + ") ");
			sbf.append("and kid.id.keyType = '" + keyType + "' ");
			sbf.append("and kid.status = 'A' ");
			sbf.append("and kid.id.transactionChannel = 'ONUS' ");

			Query qry = session.createQuery(sbf.toString());
			List queryList = qry.list();
			recCount = queryList.size();

			if ((recCount != 0) && (recCount == productCount)) {

				sbfppk.append("from KeyIndexDto kid ");
				sbfppk.append("where kid.id.issuerId = '" + issuerID + "' ");
				sbfppk.append("and kid.id.keyType = '" + keyType + "' ");
				sbfppk.append("and kid.status = 'A' ");
				sbfppk.append("and kid.id.transactionChannel = 'ONUS' ");

				Query ppkqry = session.createQuery(sbfppk.toString());
				List listDocs = ppkqry.list();
				for (Iterator it = listDocs.iterator(); it.hasNext();) {
					KeyIndexDto objKeyIndexDto = new KeyIndexDto();
					objKeyIndexDto = (KeyIndexDto) it.next();
					ppkList.put(objKeyIndexDto.id.getCardProductId(),
							objKeyIndexDto.getKeyIndex());
				}
			}

			tx.commit();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}

			System.out.println("Error in getKeyIndexForm method" + e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving the getKeyIndexForm" + e);
		} finally {
			HibernetFactory.closeSession();
		}
		return ppkList;
	}

	public Map getPPKList(String issuerID, String keyType)
			throws TPlusException {

		Map ppkList = new HashMap();
		StringBuffer sbf = new StringBuffer();
		try {

			Session session = HibernetFactory.currentSession();
			Transaction tx = session.beginTransaction();

			sbf.append("from KeyIndexDto kid ");
			sbf.append("where kid.id.issuerId = '" + issuerID + "' ");
			sbf.append("and kid.id.keyType = '" + keyType + "' ");
			sbf.append("and kid.status = 'A' ");

			Query qry = session.createQuery(sbf.toString());
			List listDocs = qry.list();
			for (Iterator it = listDocs.iterator(); it.hasNext();) {
				KeyIndexDto objKeyIndexDto = new KeyIndexDto();
				objKeyIndexDto = (KeyIndexDto) it.next();
				ppkList.put(new Integer(objKeyIndexDto.id.getCardProductId()),
						objKeyIndexDto.getKeyIndex());
			}

			tx.commit();

		} catch (Exception e) {
			System.out.println("Error" + e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving getPPKList Info" + e.getMessage());
		} finally {
			HibernetFactory.closeSession();
		}
		return ppkList;
	}

	public Map getIndexesList(String issuerID) throws TPlusException {

		Map indexesList = new HashMap();
		int recCount = 0;
		int requiredNoOfIndexesPerProduct = 4; // CVK, CVK2, PVK
		String keyTypeSet = "'CVK','CV2K','PVK','PPK'";
		int productCount = 0;
		StringBuffer sbfdistinctProducts = new StringBuffer();
		StringBuffer sbfindexes = new StringBuffer();
		StringBuffer sbfnoofkeysperProducts = new StringBuffer();
		Transaction tx = null;
		try {

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			sbfdistinctProducts.append("select distinct cpdo.cardProductId ");
			sbfdistinctProducts.append("from CardProductDto cpdo ");
			sbfdistinctProducts.append("where cpdo.issuerId = '" + issuerID + "' ");

			Query productqry = session.createQuery(sbfdistinctProducts.toString());
			List productList = productqry.list();
			productCount = productList.size();
			
			sbfnoofkeysperProducts.append("select count(kid.id.keyType) as noofkeys ");
			sbfnoofkeysperProducts.append("from KeyIndexDto kid ");
			sbfnoofkeysperProducts.append("where kid.id.issuerId = '" + issuerID + "' ");
			sbfnoofkeysperProducts.append("and kid.status = 'A' ");
			sbfnoofkeysperProducts.append("and kid.id.transactionChannel = 'ONUS' ");
			sbfnoofkeysperProducts.append("and kid.id.keyType in (" + keyTypeSet + ") ");
			sbfnoofkeysperProducts.append("group by kid.id.cardProductId ");
			
			Query noofkeysperProductqry = session.createQuery(sbfnoofkeysperProducts.toString());
			List noofkeysperProductList = noofkeysperProductqry.list();
			
			if(productCount == noofkeysperProductList.size()){
				
				boolean isValid = true;
				
				for(int i = 0; i < noofkeysperProductList.size(); i++){
					int value = ((Integer) noofkeysperProductList.get(i)).intValue();
					if(value < requiredNoOfIndexesPerProduct){
						isValid = false;
						break;
					}
				}
	
				if(isValid){
					sbfindexes.append("from KeyIndexDto kid ");
					sbfindexes.append("where kid.id.issuerId = '" + issuerID + "' ");
					sbfindexes.append("and kid.status = 'A' ");
					sbfindexes.append("and kid.id.transactionChannel = 'ONUS' ");
					sbfindexes.append("and kid.id.keyType in (" + keyTypeSet + ") ");
		
					Query qry = session.createQuery(sbfindexes.toString());
					List queryList = qry.list();
					recCount = queryList.size();
		
					String keyType = "";
					String productId = "";
					String mapKey = "";
		
					//if ((recCount != 0) && (recCount == (productCount * requiredNoOfIndexesPerProduct))) {
					if ((recCount != 0)) {
		
						for (Iterator it = queryList.iterator(); it.hasNext();) {
							KeyIndexDto objKeyIndexDto = new KeyIndexDto();
							objKeyIndexDto = (KeyIndexDto) it.next();
		
							keyType = objKeyIndexDto.id.getKeyType();
							productId = objKeyIndexDto.id.getCardProductId();
		
							mapKey = productId + keyType;
		
							indexesList.put(mapKey, String.valueOf(objKeyIndexDto.getKeyIndex()));
						}
					}
				}
			
			}

			tx.commit();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}

			System.out.println("Error in getKeyIndexForm method" + e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving the getKeyIndexForm" + e);
		} finally {
			HibernetFactory.closeSession();
		}
		return indexesList;
	}

	public static void main(String s[]) {

		try {
			// KeyIndexDto dto = new KeyIndexDto();

			// KeyIndexDAOImpl impl = new KeyIndexDAOImpl();
			// impl.get(dto,0);
			// ArrayList arrl = new ArrayList();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
