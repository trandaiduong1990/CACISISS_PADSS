//Package name
package org.transinfo.cacis.model.excell.databean;
//Java specific imports
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.Cell;
import jxl.Sheet;

public abstract class TableStructureReader
	{

	String[] getColumns = null;
	public abstract String[] getTables();
	public abstract String getTable(int index);
	public abstract String[][] getAllColumns();
	public abstract String[] getColumns(int index);
	public abstract int getTotalIndex();
	public abstract void createMultibeanData(Sheet sheet, String tname, String[] colArray);


    private static Map dataMap;

    protected TableStructureReader() {
        dataMap = new HashMap();
    }

    public static Map getAllData() {
        return dataMap;
    }


    protected TableDataBean putData(Object key, TableDataBean data) {
		System.out.println("we are in put data method");
        TableDataBean orgData = getData(key);

        if (orgData == null) {
			System.out.println("we are in null method");
            orgData = data;
        } else {
			System.out.println("we are in not null method");
            orgData.addRecords(data.getRecords());
        }

        return (TableDataBean)dataMap.put(key, orgData);
    }

    protected TableDataBean getData(Object key) {
		System.out.println("we are in get data method");
        return (TableDataBean)dataMap.get(key);
    }

  public void createData(Sheet sheet, String tname, String[] colArray) {
  	System.out.println("we are in createData method");
        TableDataBean retData = new TableDataBean(tname, colArray);
        int rows = sheet.getRows();
        int cols = sheet.getColumns();

        System.out.println("Rows in " + tname + ": " + rows);
        System.out.println("Cols in " + tname + ": " + cols);

        for (int rowNum = 1; rowNum < rows; rowNum++) {
            Cell[] cells = sheet.getRow(rowNum);
            //validateRecord(cells, retData);
            List rowList = asList(cells);
            if (!rowList.isEmpty()) {
                retData.addRecord(rowList);
            }
        }
     putData(tname, retData);

    }

   public void createConfigData(Sheet sheet, String tname, String[] colArray) {
        TableDataBean retData = new TableDataBean(tname, colArray);
        int rows = sheet.getRows();
        int cols = sheet.getColumns();

     System.out.println("Rows in " + tname + ": " + rows);
     System.out.println("Cols in " + tname + ": " + cols);

        for (int rowNum = 0; rowNum < rows; rowNum++) {
            Cell[] cells = sheet.getRow(rowNum);
            List multiRecList = asConfigList(cells);
            System.out.println("Multiple records for row " + rowNum + ": " + multiRecList);
            retData.addRecords(multiRecList);
        }
        putData(tname, retData);
    }

  /* public void createMultibeanData(Sheet sheet, String tname, String[] colArray) {
    int pos             = 0;
    int rowNum          = 0;
    int colNum          = 0;
    int startColNum     = 0;
    int endColNum       = 0;
    int rows            = 0;
    int cols            = 0;
    String content      = null;
    String table        = null;
    String[] columns    = null;
    List singleRecList  = null;
    List multiRecList   = null;
    List dataList       = new ArrayList();
    TableDataBean data  = null;
    TableDataBean[] beanArray = null;

    rows = sheet.getRows();
    cols = sheet.getColumns();

    System.out.println("Total Rows: " + rows);
    System.out.println("Total Cols: " + cols);

    // Main Table Data
    table       = tname;
    columns     = colArray;
    startColNum = 0;
    endColNum   = columns.length;
    data        = new TableDataBean(table, columns);

    System.out.println("Cols for " + table + ": " + endColNum);

    for (rowNum = 0; rowNum < rows; rowNum++) {
        singleRecList = new ArrayList();
        for (colNum = startColNum; colNum < endColNum; colNum++) {
            content = sheet.getCell(colNum, rowNum).getContents();
            singleRecList.add(content);
        }
        System.out.println("Record for " + table + ": " + singleRecList);
        if (singleRecList.isEmpty()) {
            singleRecList = null;
        } else {
            data.addRecord(singleRecList);
        }
    }
    dataList.add(data);

    // Sub Table Data
    DataReader dr.
    table       = getTable(ts.ISS_CONFIG_INDEX);
    columns     = ts.getColumns(ts.ISS_CONFIG_INDEX);
    startColNum = endColNum;
    endColNum   = cols;
    data        = new TableDataBean(table, columns);

    System.out.println("Cols for " + table + ": " + endColNum);

    for (rowNum = 0; rowNum < rows; rowNum++) {
        multiRecList = new ArrayList();
        String issuerId = sheet.getCell(0, rowNum).getContents();
        String cardBrand = sheet.getCell(1, rowNum).getContents();

        for (colNum = startColNum, pos = 0;
                colNum < endColNum; colNum++, pos++) {
            singleRecList = new ArrayList();
            content = sheet.getCell(colNum, rowNum).getContents();

           StringBuffer paramNameBuff = new StringBuffer(ts.CARD_BRAND_PARAMS[pos][0]);
           String paramType = ts.CARD_BRAND_PARAMS[pos][1];

         /*   int signKeyLen = OEGeneric.strSignKey.length();

            if (paramNameBuff.toString().startsWith(OEGeneric.strSignKey)) {
                paramNameBuff.insert(signKeyLen, cardBrand + "_");
            }*/

   /*         singleRecList.add(issuerId);
            singleRecList.add(paramNameBuff.toString());
            singleRecList.add(content);
            singleRecList.add(paramType);
            System.out.println("Single Record: " + singleRecList);
            multiRecList.add(singleRecList);
        }

        System.out.println("Multiple records for row " + rowNum + ": " + multiRecList);
        if (multiRecList.isEmpty()) {
            multiRecList = null;
        } else {
            data.addRecords(multiRecList);
        }
    }
    dataList.add(data);

    beanArray = (TableDataBean[])dataList.toArray(new TableDataBean[0]);
    TableDataBean[] multibeans =beanArray;

    if (multibeans != null) {
        data = multibeans[0];// Main Data
        int beanLen = multibeans.length;
        for (int beanPos = 1; beanPos < beanLen; beanPos++) {
            TableDataBean tmpBean = (TableDataBean)multibeans[beanPos];
            putData(tmpBean.getTableName(), tmpBean);
        }
    }
  //  return beanArray;
}*/


    private List asList(Cell[] cells) {

        List retList = new ArrayList();
        int cols = cells.length;
        for (int colNum = 0; colNum < cols; colNum++) {
            String content = cells[colNum].getContents();
            retList.add(content);
        }

        return retList;
    }

    private List asConfigList(Cell[] cells) {
        String issuerId     = null;
        String paramName    = null;
        String paramType    = null;
        String content      = null;
        List rowList        = null;
        List retList        = new ArrayList();

       /* // Issuer Id is the first column
        issuerId = cells[0].getContents();
        int cols = cells.length;
        int configNum = 0;
        // Loop thru from the second column and get all the config values
        for (int colNum = 1; colNum < cols; colNum++) {
            paramName = ISS_CONFIG_PARAMS[configNum][0];
            paramType = ISS_CONFIG_PARAMS[configNum][1];
            configNum++;

            // Check for User Id Field and form the Param Name and Value dynamically
            if (paramName.equals(OEGeneric.strUserIdFields)) {
                String userField = paramName;
                int totalPoints = SCORING_CRITERIA.length;
                int fieldIndex = 0;
                // Loop thru the scoring points (user field values)
                // and form the user field if present
                for (int pointNum = 0; pointNum < totalPoints; pointNum++) {
                    content = cells[colNum].getContents();
                    if (content.trim().equals("Y")) {
                        paramName = userField + fieldIndex++;
                        content = SCORING_CRITERIA[pointNum];

                        rowList = new ArrayList();
                        rowList.add(issuerId);
                        rowList.add(paramName);
                        rowList.add(content);
                        rowList.add(paramType);
                       AdminConfig.writeDebug(
                            "Single user record: " + rowList);
                        retList.add(rowList);
                    }
                    if ((pointNum + 1) < totalPoints) {
                        // Increment the actual column number by 1
                        // only if the next user field value is available
                        colNum++;
                    }
                }
            } else {
                content = cells[colNum].getContents();

                rowList = new ArrayList();
                rowList.add(issuerId);
                rowList.add(paramName);
                rowList.add(content);
                rowList.add(paramType);
               AdminConfig.writeDebug(
                    "Single config record: " + rowList);
                retList.add(rowList);
            }
        }*/
        return retList;
    }

}
