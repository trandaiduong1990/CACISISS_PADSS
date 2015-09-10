package org.transinfo.cacis.dataacess.daoimpl.oracle.riskmanagement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.transinfo.cacis.TPlusCodes;
import org.transinfo.cacis.TPlusException;
import org.transinfo.cacis.common.CommonDataBean;
import org.transinfo.cacis.dataacess.HibernetDAO.HibernetFactory;
import org.transinfo.cacis.dataacess.dao.riskmanagement.RiskCountryDAO;
import org.transinfo.cacis.dataacess.daoimpl.oracle.BaseDAOImpl;
import org.transinfo.cacis.dto.riskmanagement.RiskCountryDto;


public class RiskCountryDAOImpl extends BaseDAOImpl implements RiskCountryDAO {


	public Collection search(RiskCountryDto objSearchDto, int pageNo)
	throws TPlusException
	{


		Collection objSearchCollection = new ArrayList();
		StringBuffer sbf = new StringBuffer();

		try {

			sbf.append("select to_char(rc.id.cardNo), rc.id.countryCode, to_char(rc.updatedDate,'dd-MM-yyyy'), ");
			sbf.append(" rc.userId FROM  RiskCountryDto rc ");


			if (String.valueOf(objSearchDto.id.getCardNo())!= null &&  objSearchDto.id.getCardNo()!=0)
			{
				sbf.append("where rc.id.cardNo = "+objSearchDto.id.getCardNo()+" ");
			}


			objSearchCollection = getSearchList(sbf.toString(),pageNo);

			System.out.println("Size===> "+objSearchCollection.size());

		} catch (Exception e) {
			System.out.println("Error while retrieving the RiskCountry Search Info" + e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving the RiskCountry Search Info" + e);
		} finally {
			HibernetFactory.closeSession();
		}
		return objSearchCollection;

	}


	public RiskCountryDto getRiskCountryForm(long cardNo, String countryCode) throws TPlusException{
		RiskCountryDto objRiskCntry = null;
		Transaction tx = null;
		try
		{

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			Query qry = session.createQuery("From RiskCountryDto rc where rc.id.cardNo = "+cardNo+" and rc.id.countryCode = '"+countryCode+"' ");
			List listDocs = qry.list();

			for(Iterator it = listDocs.iterator();it.hasNext();)
			{
				objRiskCntry = new RiskCountryDto();
				objRiskCntry = (RiskCountryDto)it.next();
			}

			tx.commit();
			HibernetFactory.closeSession();

			if(objRiskCntry !=null)
			{
				Map selectList = new TreeMap();
				StringBuffer sbf = new StringBuffer();

				sbf.append("select cm.id.cityCode, cm.cityDesc ");
				sbf.append(" FROM CityMasterDto cm ");
				sbf.append(" where cm.id.cityCode ");
				sbf.append(" in( select rct.id.city from RiskCitiesDto rct ");
				if(objRiskCntry.id.getCardNo()!=0){
					sbf.append(" where to_char(rct.id.cardNo) = "+objRiskCntry.id.getCardNo()+" ");                                
				}
				sbf.append( ") ");

				Collection objSearch = getList(sbf.toString());

				for(Iterator it = objSearch.iterator();it.hasNext();)
				{
					CommonDataBean objCommonDB = (CommonDataBean)it.next();
					selectList.put(objCommonDB.getColumn1(),objCommonDB.getColumn2());
				}
				System.out.println(selectList.size());
				//objRiskCntry.setSelectedCities(selectList);
			}

		}catch (Exception e)
		{

			System.out.println("Error in getRiskCountryForm method"+ e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while retrieving the getRiskCountryForm" + e);
		}
		finally
		{
			HibernetFactory.closeSession();
		}
		return objRiskCntry;

	}


	public boolean add(RiskCountryDto objDto)
	throws TPlusException{

		boolean bolExecute=false;
		Transaction tx = null;
		try{

			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();

			System.out.println("Saving........");
			session.save(objDto);
			System.out.println("RiskCountryDto object persisted to the database.");
			tx.commit();

			bolExecute=true;

		}catch (Exception e) {

			if (tx != null)
			{
				tx.rollback();
			}
			System.out.println("Error while adding RiskCountryForm data "+ e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR, "Error: while adding RiskCountryForm data" + e);
		} finally
		{
			HibernetFactory.closeSession();
		}
		return 	bolExecute;
	}


	public boolean update(RiskCountryDto objDto)throws TPlusException
	{
		boolean bolExecute = false;
		Transaction tx = null;
		try
		{
			Session session = HibernetFactory.currentSession();
			tx = session.beginTransaction();
			//session.setFlushMode(FlushMode.AUTO);
			System.out.println("Updating...");
			//session.update(objDto);
			//session.merge(objDto);
			session.saveOrUpdate(objDto);
			tx.commit();
			System.out.println("RiskCountryDto object updated to the database.");
			bolExecute = true;
		}

		catch (Exception e) {

			if (tx != null)
			{
				tx.rollback();
			}
			System.out.println("Error while updating RiskCountryForm data "+ e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,
					"Error: while updating RiskCountryForm data" + e);

		} finally
		{
			HibernetFactory.closeSession();
		}

		return bolExecute;
	}



	public Map getCityList(RiskCountryDto objDto)throws TPlusException{

		Map cityList = new TreeMap();
		try
		{

			StringBuffer sbf = new StringBuffer();

			sbf.append("select cm.id.cityCode, cm.cityDesc ");
			sbf.append(" FROM CityMasterDto cm where cm.id.countryCode = '"+objDto.id.getCountryCode()+"' ");

			if(String.valueOf(objDto.id.getCardNo()) !=null && objDto.id.getCardNo()!=0)
			{
				sbf.append(" and cm.id.cityCode ");
				sbf.append(" not in( select rct.id.city from RiskCitiesDto rct ");
				sbf.append(" where rct.id.cardNo ="+objDto.id.getCardNo()+" and rct.id.countryCode = '"+objDto.id.getCountryCode()+"') ");
			}


			Collection objSearch = getList(sbf.toString());

			for(Iterator it = objSearch.iterator();it.hasNext();)
			{
				CommonDataBean objCommonDB = (CommonDataBean)it.next();
				cityList.put(objCommonDB.getColumn1(),objCommonDB.getColumn2());
			}
			System.out.println("cityList size==>"+cityList.size());

		}catch (Exception e){
			System.out.println("Error in getCityList method"+e);
			throw new TPlusException(TPlusCodes.APPLICATION_ERROR,"Error: while retrieving getCityList Info"+e.getMessage());
		}
		return cityList;
	}


}


