package org.transinfo.cacis.util;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.transinfo.cacis.dto.cardproduction.ApplicationProcessDto;
import org.transinfo.cacis.dto.cardproduction.CustomerAddressDto;

@SuppressWarnings("unchecked")
public class CacisissUtils {
	
	public DWRSupplementaryDetails getDWRSuppDetail(ApplicationProcessDto objApplicationProcessDto){

		DWRSupplementaryDetails objDwrSupplementaryDetails = new DWRSupplementaryDetails();
		
		if(objApplicationProcessDto != null){
			
			objDwrSupplementaryDetails.setSupplCustomerName(objApplicationProcessDto.getCustomerName());
			objDwrSupplementaryDetails.setSupplSurName(objApplicationProcessDto.getSurName());
			objDwrSupplementaryDetails.setSupplEmbossingName(objApplicationProcessDto.getEmbossingName());
			
			if(objApplicationProcessDto.getDob() != null)
				objDwrSupplementaryDetails.setStrSupplDob(DateUtil.convertDateToDateString(objApplicationProcessDto.getDob()));
			
			objDwrSupplementaryDetails.setSupplPob(objApplicationProcessDto.getPob());
			objDwrSupplementaryDetails.setSupplGender(objApplicationProcessDto.getGender());
			objDwrSupplementaryDetails.setSupplMaritalStatus(objApplicationProcessDto.getMaritalStatus());
			objDwrSupplementaryDetails.setSupplIdNumber(objApplicationProcessDto.getIdNumber());
			
			if(objApplicationProcessDto.getIdDate() != null)
				objDwrSupplementaryDetails.setStrSupplIdDate(DateUtil.convertDateToDateString(objApplicationProcessDto.getIdDate()));

			if(objApplicationProcessDto.getExpDate() != null)
				objDwrSupplementaryDetails.setStrSupplIdExpire(DateUtil.convertDateToDateString(objApplicationProcessDto.getExpDate()));
			
			objDwrSupplementaryDetails.setSupplIdPlace(objApplicationProcessDto.getIdPlace());
			objDwrSupplementaryDetails.setSupplNationality(objApplicationProcessDto.getNationality());
			objDwrSupplementaryDetails.setSupplEmail(objApplicationProcessDto.getEmail());
			objDwrSupplementaryDetails.setSupplIncome(String.valueOf(objApplicationProcessDto.getIncome()));
			objDwrSupplementaryDetails.setRelationShip(String.valueOf(objApplicationProcessDto.getRelationShip()));
			
			CustomerAddressDto objCustomerAddressDto = null;
			Set custAddress = objApplicationProcessDto.getApplicationAddress();
			for(Iterator it = custAddress.iterator(); it.hasNext();){
				objCustomerAddressDto = (CustomerAddressDto) it.next();
				if(objCustomerAddressDto.getAddressType() != null && "H".equals(objCustomerAddressDto.getAddressType())){
					objDwrSupplementaryDetails.setSupplementaryAddressaddress1(objCustomerAddressDto.getAddress1());
					objDwrSupplementaryDetails.setSupplementaryAddressaddress2(objCustomerAddressDto.getAddress2());
					objDwrSupplementaryDetails.setSupplementaryAddresscity(objCustomerAddressDto.getCity());
					objDwrSupplementaryDetails.setSupplementaryAddressstate(objCustomerAddressDto.getState());
					objDwrSupplementaryDetails.setSupplementaryAddresscountry(objCustomerAddressDto.getCountry());
					objDwrSupplementaryDetails.setSupplementaryAddresspostalCode(String.valueOf(objCustomerAddressDto.getPostalCode()));
					objDwrSupplementaryDetails.setApplicationformphone(objCustomerAddressDto.getPhone());
					objDwrSupplementaryDetails.setSupplementaryAddressfax(objCustomerAddressDto.getFax());
				}
			}
			
		}
		
		return objDwrSupplementaryDetails;
		
	}
	
   public static Map sortByComparator(Map unsortMap) {
	   
        List list = new LinkedList(unsortMap.entrySet());
 
        //sort list based on comparator
        Collections.sort(list, new Comparator() {
			public int compare(Object o1, Object o2) {
	           return ((Comparable) ((Map.Entry) (o1)).getValue())
	           .compareTo(((Map.Entry) (o2)).getValue());
             }
	});
 
        //put sorted list into map again
	Map sortedMap = new LinkedHashMap();
	for (Iterator it = list.iterator(); it.hasNext();) {
	     Map.Entry entry = (Map.Entry)it.next();
	     sortedMap.put(entry.getKey(), entry.getValue());
	}
	
	return sortedMap;
   }

}
