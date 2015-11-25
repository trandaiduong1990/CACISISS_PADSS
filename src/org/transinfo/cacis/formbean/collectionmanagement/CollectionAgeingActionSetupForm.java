package org.transinfo.cacis.formbean.collectionmanagement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;
import org.transinfo.cacis.dataacess.DAOFactory;
import org.transinfo.cacis.dataacess.dao.collectionmanagement.CollectionAgeingActionDAO;

@SuppressWarnings("serial")
public class CollectionAgeingActionSetupForm extends ValidatorForm {
	private String issuerId;
	private String ageingPolicy;
	private String startDay="";
	private String endDay="";
	private String days;
	private String originalDay="";
	private String phone;
	private String remainder;
	private String remainderType;
	private String tempCardOff;
	private String writeOff;
	private String days2;
	private String phone2;
	private String remainder2;
	private String remainderType2;
	private String tempCardOff2;
	private String writeOff2;
	private String days3;
	private String phone3;
	private String remainder3;
	private String remainderType3;
	private String tempCardOff3;
	private String writeOff3;
	private String days4;
	private String phone4;
	private String remainder4;
	private String remainderType4;
	private String tempCardOff4;
	private String writeOff4;
	private String days5;
	private String phone5;
	private String remainder5;
	private String remainderType5;
	private String tempCardOff5;
	private String writeOff5;
	private String days6;
	private String phone6;
	private String remainder6;
	private String remainderType6;
	private String tempCardOff6;
	private String writeOff6;
	private String days7;
	private String phone7;
	private String remainder7;
	private String remainderType7;
	private String tempCardOff7;
	private String writeOff7;
	private String days8;
	private String phone8;
	private String remainder8;
	private String remainderType8;
	private String tempCardOff8;
	private String writeOff8;
	private String days9;
	private String phone9;
	private String remainder9;
	private String remainderType9;
	private String tempCardOff9;
	private String writeOff9;
	private String days10;
	private String phone10;
	private String remainder10;
	private String remainderType10;
	private String tempCardOff10;
	private String writeOff10;
	private String sno="";
	Map ageingPolicyList;
	Map remainderTypeList;
	List<AgeingAction> ageingActionList = new ArrayList<AgeingAction>();

	public String getIssuerId() {
		return issuerId;
	}

	public void setIssuerId(String issuerId) {
		this.issuerId = issuerId;
	}

	public String getAgeingPolicy() {
		return ageingPolicy;
	}

	public void setAgeingPolicy(String ageingPolicy) {
		this.ageingPolicy = ageingPolicy;
	}

	public String getStartDay() {
		return startDay;
	}

	public void setStartDay(String startDay) {
		this.startDay = startDay;
	}

	public String getEndDay() {
		return endDay;
	}

	public void setEndDay(String endDay) {
		this.endDay = endDay;
	}

	public String getOriginalDay() {
		return originalDay;
	}

	public void setOriginalDay(String originalDay) {
		this.originalDay = originalDay;
	}


	public String getDays() {
		return days;
	}

	public void setDays(String days) {
		this.days = days;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRemainder() {
		return remainder;
	}

	public void setRemainder(String remainder) {
		this.remainder = remainder;
	}

	public String getRemainderType() {
		return remainderType;
	}

	public void setRemainderType(String remainderType) {
		this.remainderType = remainderType;
	}

	public String getTempCardOff() {
		return tempCardOff;
	}

	public void setTempCardOff(String tempCardOff) {
		this.tempCardOff = tempCardOff;
	}

	public String getWriteOff() {
		return writeOff;
	}

	public void setWriteOff(String writeOff) {
		this.writeOff = writeOff;
	}

	public String getDays2() {
		return days2;
	}

	public void setDays2(String days2) {
		this.days2 = days2;
	}

	public String getPhone2() {
		return phone2;
	}

	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	public String getRemainder2() {
		return remainder2;
	}

	public void setRemainder2(String remainder2) {
		this.remainder2 = remainder2;
	}

	public String getRemainderType2() {
		return remainderType2;
	}

	public void setRemainderType2(String remainderType2) {
		this.remainderType2 = remainderType2;
	}

	public String getTempCardOff2() {
		return tempCardOff2;
	}

	public void setTempCardOff2(String tempCardOff2) {
		this.tempCardOff2 = tempCardOff2;
	}

	public String getWriteOff2() {
		return writeOff2;
	}

	public void setWriteOff2(String writeOff2) {
		this.writeOff2 = writeOff2;
	}

	public String getDays3() {
		return days3;
	}

	public void setDays3(String days3) {
		this.days3 = days3;
	}

	public String getPhone3() {
		return phone3;
	}

	public void setPhone3(String phone3) {
		this.phone3 = phone3;
	}

	public String getRemainder3() {
		return remainder3;
	}

	public void setRemainder3(String remainder3) {
		this.remainder3 = remainder3;
	}

	public String getRemainderType3() {
		return remainderType3;
	}

	public void setRemainderType3(String remainderType3) {
		this.remainderType3 = remainderType3;
	}

	public String getTempCardOff3() {
		return tempCardOff3;
	}

	public void setTempCardOff3(String tempCardOff3) {
		this.tempCardOff3 = tempCardOff3;
	}

	public String getWriteOff3() {
		return writeOff3;
	}

	public void setWriteOff3(String writeOff3) {
		this.writeOff3 = writeOff3;
	}

	public String getDays4() {
		return days4;
	}

	public void setDays4(String days4) {
		this.days4 = days4;
	}

	public String getPhone4() {
		return phone4;
	}

	public void setPhone4(String phone4) {
		this.phone4 = phone4;
	}

	public String getRemainder4() {
		return remainder4;
	}

	public void setRemainder4(String remainder4) {
		this.remainder4 = remainder4;
	}

	public String getRemainderType4() {
		return remainderType4;
	}

	public void setRemainderType4(String remainderType4) {
		this.remainderType4 = remainderType4;
	}

	public String getTempCardOff4() {
		return tempCardOff4;
	}

	public void setTempCardOff4(String tempCardOff4) {
		this.tempCardOff4 = tempCardOff4;
	}

	public String getWriteOff4() {
		return writeOff4;
	}

	public void setWriteOff4(String writeOff4) {
		this.writeOff4 = writeOff4;
	}

	public String getDays5() {
		return days5;
	}

	public void setDays5(String days5) {
		this.days5 = days5;
	}

	public String getPhone5() {
		return phone5;
	}

	public void setPhone5(String phone5) {
		this.phone5 = phone5;
	}

	public String getRemainder5() {
		return remainder5;
	}

	public void setRemainder5(String remainder5) {
		this.remainder5 = remainder5;
	}

	public String getRemainderType5() {
		return remainderType5;
	}

	public void setRemainderType5(String remainderType5) {
		this.remainderType5 = remainderType5;
	}

	public String getTempCardOff5() {
		return tempCardOff5;
	}

	public void setTempCardOff5(String tempCardOff5) {
		this.tempCardOff5 = tempCardOff5;
	}

	public String getWriteOff5() {
		return writeOff5;
	}

	public void setWriteOff5(String writeOff5) {
		this.writeOff5 = writeOff5;
	}

	public String getDays6() {
		return days6;
	}

	public void setDays6(String days6) {
		this.days6 = days6;
	}

	public String getPhone6() {
		return phone6;
	}

	public void setPhone6(String phone6) {
		this.phone6 = phone6;
	}

	public String getRemainder6() {
		return remainder6;
	}

	public void setRemainder6(String remainder6) {
		this.remainder6 = remainder6;
	}

	public String getRemainderType6() {
		return remainderType6;
	}

	public void setRemainderType6(String remainderType6) {
		this.remainderType6 = remainderType6;
	}

	public String getTempCardOff6() {
		return tempCardOff6;
	}

	public void setTempCardOff6(String tempCardOff6) {
		this.tempCardOff6 = tempCardOff6;
	}

	public String getWriteOff6() {
		return writeOff6;
	}

	public void setWriteOff6(String writeOff6) {
		this.writeOff6 = writeOff6;
	}

	public String getDays7() {
		return days7;
	}

	public void setDays7(String days7) {
		this.days7 = days7;
	}

	public String getPhone7() {
		return phone7;
	}

	public void setPhone7(String phone7) {
		this.phone7 = phone7;
	}

	public String getRemainder7() {
		return remainder7;
	}

	public void setRemainder7(String remainder7) {
		this.remainder7 = remainder7;
	}

	public String getRemainderType7() {
		return remainderType7;
	}

	public void setRemainderType7(String remainderType7) {
		this.remainderType7 = remainderType7;
	}

	public String getTempCardOff7() {
		return tempCardOff7;
	}

	public void setTempCardOff7(String tempCardOff7) {
		this.tempCardOff7 = tempCardOff7;
	}

	public String getWriteOff7() {
		return writeOff7;
	}

	public void setWriteOff7(String writeOff7) {
		this.writeOff7 = writeOff7;
	}

	public String getDays8() {
		return days8;
	}

	public void setDays8(String days8) {
		this.days8 = days8;
	}

	public String getPhone8() {
		return phone8;
	}

	public void setPhone8(String phone8) {
		this.phone8 = phone8;
	}

	public String getRemainder8() {
		return remainder8;
	}

	public void setRemainder8(String remainder8) {
		this.remainder8 = remainder8;
	}

	public String getRemainderType8() {
		return remainderType8;
	}

	public void setRemainderType8(String remainderType8) {
		this.remainderType8 = remainderType8;
	}

	public String getTempCardOff8() {
		return tempCardOff8;
	}

	public void setTempCardOff8(String tempCardOff8) {
		this.tempCardOff8 = tempCardOff8;
	}

	public String getWriteOff8() {
		return writeOff8;
	}

	public void setWriteOff8(String writeOff8) {
		this.writeOff8 = writeOff8;
	}

	public String getDays9() {
		return days9;
	}

	public void setDays9(String days9) {
		this.days9 = days9;
	}

	public String getPhone9() {
		return phone9;
	}

	public void setPhone9(String phone9) {
		this.phone9 = phone9;
	}

	public String getRemainder9() {
		return remainder9;
	}

	public void setRemainder9(String remainder9) {
		this.remainder9 = remainder9;
	}

	public String getRemainderType9() {
		return remainderType9;
	}

	public void setRemainderType9(String remainderType9) {
		this.remainderType9 = remainderType9;
	}

	public String getTempCardOff9() {
		return tempCardOff9;
	}

	public void setTempCardOff9(String tempCardOff9) {
		this.tempCardOff9 = tempCardOff9;
	}

	public String getWriteOff9() {
		return writeOff9;
	}

	public void setWriteOff9(String writeOff9) {
		this.writeOff9 = writeOff9;
	}

	public String getDays10() {
		return days10;
	}

	public void setDays10(String days10) {
		this.days10 = days10;
	}

	public String getPhone10() {
		return phone10;
	}

	public void setPhone10(String phone10) {
		this.phone10 = phone10;
	}

	public String getRemainder10() {
		return remainder10;
	}

	public void setRemainder10(String remainder10) {
		this.remainder10 = remainder10;
	}

	public String getRemainderType10() {
		return remainderType10;
	}

	public void setRemainderType10(String remainderType10) {
		this.remainderType10 = remainderType10;
	}

	public String getTempCardOff10() {
		return tempCardOff10;
	}

	public void setTempCardOff10(String tempCardOff10) {
		this.tempCardOff10 = tempCardOff10;
	}

	public String getWriteOff10() {
		return writeOff10;
	}

	public void setWriteOff10(String writeOff10) {
		this.writeOff10 = writeOff10;
	}

	public String getSno() {
		return sno;
	}

	public void setSno(String sno) {
		this.sno = sno;
	}

	public Map getAgeingPolicyList() {
		return ageingPolicyList;
	}

	public void setAgeingPolicyList(Map ageingPolicyList) {
		this.ageingPolicyList = ageingPolicyList;
	}

	public Map getRemainderTypeList() {
		return remainderTypeList;
	}

	public void setRemainderTypeList(Map remainderTypeList) {
		this.remainderTypeList = remainderTypeList;
	}

	public List<AgeingAction> getAgeingActionList() {
		return ageingActionList;
	}

	public void setAgeingActionList(List<AgeingAction> ageingActionList) {
		this.ageingActionList = ageingActionList;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request) {
		phone = null;
		remainder = null;
		tempCardOff = null;
		writeOff = null;
	}
	
	public void getPreListData() {

		try {
			CollectionAgeingActionDAO objDAO = DAOFactory.getInstance().getCollectionAgeingActionDAO();

			if (ageingPolicyList == null) {
				ageingPolicyList = objDAO.getAgeingList(issuerId);
			}
			if (remainderTypeList == null) {
				remainderTypeList = objDAO.getRemainderTypeList("COL_ACTIVITY");
			}
			
			AgeingAction action = new AgeingAction(getDays(), getPhone(), getRemainder(), getRemainderType(), getTempCardOff(), getWriteOff());
			AgeingAction action2 = new AgeingAction(getDays2(), getPhone2(), getRemainder2(), getRemainderType2(), getTempCardOff2(), getWriteOff2());
			AgeingAction action3 = new AgeingAction(getDays3(), getPhone3(), getRemainder3(), getRemainderType3(), getTempCardOff3(), getWriteOff3());
			AgeingAction action4 = new AgeingAction(getDays4(), getPhone4(), getRemainder4(), getRemainderType4(), getTempCardOff4(), getWriteOff4());
			AgeingAction action5 = new AgeingAction(getDays5(), getPhone5(), getRemainder5(), getRemainderType5(), getTempCardOff5(), getWriteOff5());
			AgeingAction action6 = new AgeingAction(getDays6(), getPhone6(), getRemainder6(), getRemainderType6(), getTempCardOff6(), getWriteOff6());
			AgeingAction action7 = new AgeingAction(getDays7(), getPhone7(), getRemainder7(), getRemainderType7(), getTempCardOff7(), getWriteOff7());
			AgeingAction action8 = new AgeingAction(getDays8(), getPhone8(), getRemainder8(), getRemainderType8(), getTempCardOff8(), getWriteOff8());
			AgeingAction action9 = new AgeingAction(getDays9(), getPhone9(), getRemainder9(), getRemainderType9(), getTempCardOff9(), getWriteOff9());
			AgeingAction action10 = new AgeingAction(getDays10(), getPhone10(), getRemainder10(), getRemainderType10(), getTempCardOff10(), getWriteOff10());
			
			if(ageingActionList.size()!=0) {
				ageingActionList.clear();
			}
			ageingActionList.add(action);
			ageingActionList.add(action2);
			ageingActionList.add(action3);
			ageingActionList.add(action4);
			ageingActionList.add(action5);
			ageingActionList.add(action6);
			ageingActionList.add(action7);
			ageingActionList.add(action8);
			ageingActionList.add(action9);
			ageingActionList.add(action10);
		} catch (Exception e) {
			System.out
					.println("Error while getting PreList in CustomerTypeSearchForm:"
							+ e.getMessage());
		}
	}
}
