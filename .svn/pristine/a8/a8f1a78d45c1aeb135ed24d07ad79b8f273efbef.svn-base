package org.transinfo.cacis.model.excell.tablestructure;

import jxl.Sheet;

import org.transinfo.cacis.model.excell.databean.TableStructureReader;

public  class CurrencyRateTableStructure extends TableStructureReader {


	public  int S_ISS_CURRRATE_INDEX      =0;
	public  int M_ISS_CURRRATE_INDEX      =1;

	public   int ISS_MAX_INDEX           = 1;
	// Total Issuer Tables
	public   int ISS_TOTAL_INDEX         = ISS_MAX_INDEX + 1;

	public   String[] ISS_TABLES = {
			"S_CURR_RATE",
			"M_CURR_RATE"
	};
	public   String[][] ISS_COLUMNS = {
			//Currency_Rate
			{
				"Issuer_Id",
				"Curr_Code",
				"Rate"
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
