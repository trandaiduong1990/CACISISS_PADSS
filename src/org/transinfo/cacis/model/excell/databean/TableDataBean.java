//Package name
package org.transinfo.cacis.model.excell.databean;

//Java specific imports
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TableDataBean implements Serializable {
    private String tname;
    private String[] colArray;
    // List of records which is a list of lists.
    private List recordList;

    public TableDataBean(String table, String[] columns) {
        this.tname = table;
        this.colArray = columns;
        recordList = new ArrayList();
    }

    public int size() {
        return recordList.size();
    }

    public boolean isEmpty() {
        return recordList.isEmpty();
    }

    public boolean addRecord(List rowList) {
        return recordList.add(rowList);
    }

    public boolean removeRecord(List rowList) {
        return recordList.remove(rowList);
    }

    public List getRecord(int pos) {
        return (List)recordList.get(pos);
    }

    public boolean addRecords(List rowsList) {
        return recordList.addAll(rowsList);
    }

    public void clearRecords() {
        recordList.clear();
    }

    public List getRecords() {
        return recordList;
    }

    public String getTableName() {
        return tname;
    }

    public String[] getColumnNames() {
        return colArray;
    }

    public String toString() {
        return recordList.toString();
    }
}
