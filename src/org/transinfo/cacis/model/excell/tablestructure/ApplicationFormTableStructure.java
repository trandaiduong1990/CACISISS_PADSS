
package org.transinfo.cacis.model.excell.tablestructure;

import jxl.Sheet;

import org.transinfo.cacis.model.excell.databean.TableStructureReader;

public  class ApplicationFormTableStructure extends TableStructureReader {


	public  int S_ISS_APPLICATIONFORM_INDEX      =0;
	public  int M_ISS_APPLICATIONFORM_INDEX      =1;

	public   int ISS_MAX_INDEX           = 1;
	// Total Issuer Tables
	public   int ISS_TOTAL_INDEX         = ISS_MAX_INDEX + 1;

	public   String[] ISS_TABLES = {
			"S_APPLICATION_FORM",
			"M_APPLICATION_FORM"
	};
	public   String[][] ISS_COLUMNS = {
			//applicationforms
			{
				"APPLICATION_ID",
				"ISSUER_ID",
				"OPEN_DATE",
				"CLOSE_DATE", 
				"APPLICATIONTYPE",
				"APPLICATIONSTATUS",
				"TEMP","BRANCH_ID", 
				"CUSTOMER_NAME",
				"SURNAME_NAME",
				"EMBOSSINGNAME", 
				"CUST_TYPE_ID", 
				"DOB", 
				"POB", 
				"GENDER", 
				"MARITAL_STATUS", 
				"DEPENDENTS", 
				"ID_NUMBER", 
				"ID_DATE", 
				"ID_EXPIRE", 
				"ID_PLACE", 
				"NATIONALITY",
				"EDUCATION", 
				"EMAIL", 
				"BILLING_TO",
				"JOB_STATUS", 
				"JOB_STATUS_OTHERS",
				"JOB_TYPE", 
				"JOB_TYPE_OTHERS", 
				"INCOME",
				"REF_NAME", 
				"REF_TEL",
				"REF_FAX",
				"REF_COMPANY", 
				"ATM_LINK",
				"SAVING_ACCT", 
				"CHECKING_ACCT", 
				"SUPPL_CARD_REQUIRED", 
				"SUPPL_CUSTOMER_NAME",
				"SUPPL_SURNAME_NAME", 
				"SUPPL_EMBOSSINGNAME", 
				"SUPPL_DOB, SUPPL_POB", 
				"SUPPL_GENDER",
				"SUPPL_MARITAL_STATUS", 
				"SUPPL_ID_NUMBER",
				"SUPPL_ID_DATE", 
				"SUPPL_ID_EXPIRE",
				"SUPPL_ID_PLACE", 
				"SUPPL_NATIONALITY", 
				"SUPPL_EMAIL", 
				"SUPPL_INCOME",
				"REMARKS", 
				"LAST_UPDATED_DATE", 
				"LAST_UPDATED_BY", 
				"COMPANY_NAME",
				"RELATIONSHIP"
			
			}
	};

	public String[] getTables()
	{
		return ISS_TABLES;
	}

	public String[][] getAllColumns()
	{
		return ISS_COLUMNS;
	}

	public int getTotalIndex()
	{
		return ISS_TOTAL_INDEX;
	}

	public String getTable(int index)
	{

		if(index<getTables().length)
			return ISS_TABLES[index];
		else
			return null;
	}

	public String[] getColumns(int index)
	{
		if(index<getAllColumns().length)
			return ISS_COLUMNS[index];
		else
			return null;
	}

	public void createMultibeanData(Sheet sheet, String tname, String[] colArray){
		//No functionality
		}
}
