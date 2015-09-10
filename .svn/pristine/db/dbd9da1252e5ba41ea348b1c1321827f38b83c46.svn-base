package org.transinfo.cacis.report.db;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.StringTokenizer;

import oracle.jdbc.driver.OracleTypes;
import oracle.sql.ARRAY;
import oracle.sql.STRUCT;

import org.hibernate.Session;
import org.transinfo.cacis.dataacess.HibernetDAO.HibernetFactory;
import org.transinfo.cacis.report.databean.ReportsDataBean;

@SuppressWarnings("unchecked")
public class DBManager {
	int intMaxColumns = 20;

    // Object.
    private Connection objCon 	= null;
    private ResultSet rs 		= null;
    private Statement stmtSQL = null;

	public static final int VARRAYOFSTRUCT = 100;
	private static final String ADMIN_ARRAY_TYPE = "VV_ADMIN_REPORTS";
	public static int intTotalNoOfStructAttributes = 0;
	protected ARRAY simpleArray = null;

	public DBManager() {
		this.objCon = null;
	}

	public DBManager(String poolName) {
	}

	public String getFieldValue(String sqlStr, String fieldName) {
		String rtnValue = "";
		try {
			ResultSet objrs = executeSQL(sqlStr.toString());

			if (objrs.next())
				rtnValue = objrs.getString(fieldName);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return rtnValue;
	}

	public ResultSet executeSQL(String sql) throws Exception {
		return executeSQL(sql, true);
	}

	public ResultSet executeSQL(String sql, boolean xbIsResultSet)
			throws Exception {
		/*Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;*/
		try {

			Session session = HibernetFactory.currentSession();
			//Connection conn = session.connection();
			//PreparedStatement ps = conn.prepareStatement(sql);
			objCon = session.connection();
			stmtSQL = objCon.createStatement();
			stmtSQL.execute(sql);
			rs = stmtSQL.getResultSet();

		} catch (Exception exp) {
			throw exp;
		} finally {
			/*if(rs != null){
				rs.close();
			}*/
			
			/*if(ps != null){
				ps.close();
			}
			
			if(conn != null){
				conn.close();
			}*/
			
			HibernetFactory.closeSession();
		}

		return rs;

	}

	public ArrayList executeStoredProc(StringBuffer sbfSelectClause,
			StringBuffer sbfFromClause, int intStartIndex, int intEndIndex,
			int intNoOfCols, ArrayList arlResult) throws Exception {

		// ArrayList arlResultData = new ArrayList();
		// ReportsDataBean objReport = new ReportsDataBean();
		// Class adminReportClass = objReport.getClass();
		// Object[] arrObj = new Object[1];
		//Class[] arrClass = new Class[1];
		//arrClass[0] = new String("").getClass();
		// Method method = null;
		// int colCount = 0;

		try {
			int intblankCols = intMaxColumns - intNoOfCols;
			StringBuffer sbfQuery = new StringBuffer();

			sbfQuery = sbfSelectClause;
			if (intblankCols > 0) {
				for (int intCounter = 0; intCounter < intblankCols; intCounter++)
					sbfQuery.append(", ' ' ");
			}

			sbfQuery.append(sbfFromClause);

			arlResult = executeStoredProc(sbfQuery, intStartIndex, intEndIndex,
					intNoOfCols, arlResult);

			/*
			 * System.out.println("SQL=" + sbfQuery.toString());
			 * 
			 * Session session = HibernetFactory.currentSession(); Connection
			 * conn = session.connection(); Statement stmt =
			 * conn.createStatement(); ResultSet rs =
			 * stmt.executeQuery(sbfQuery.toString()); //
			 * System.out.println("\n rs.next() = "+rs.next()+"\n");
			 * System.out.println("intStartIndex = " + intStartIndex);
			 * System.out.println("intEndIndex = " + intEndIndex);
			 * System.out.println("intNoOfCols = " + intNoOfCols);
			 * System.out.println("SQL=" + sbfQuery.toString());
			 * 
			 * while (rs.next()) {
			 * 
			 * int intCount = 1; while (intCount <= 20) { arrObj[0] =
			 * rs.getString(intCount); method =
			 * adminReportClass.getMethod("setColumn" + (intCount), arrClass);
			 * method.invoke(objReport, arrObj); intCount++; }
			 * arlResultData.add(objReport); objReport = new ReportsDataBean();
			 * 
			 * } System.out.println("List Size1=" + arlResultData.size());
			 * arlResult.add(arlResultData); arlResult.add(new
			 * Integer(arlResultData.size()));
			 * System.out.println("arlResultList.get(0)" +
			 * arlResult.get(0).toString());
			 */
		} catch (Exception exp) {
			throw exp;
		}
		return arlResult;
	}

	public ArrayList executeStoredProc(StringBuffer sbfReport,
			int intStartIndex, int intEndIndex, int intNoOfCols,
			ArrayList arlResult) throws Exception {
		ArrayList arlOutput = new ArrayList();
		ArrayList arlSPResult = new ArrayList();

		// variables to be used for SP execution
		ArrayList arlInput = new ArrayList();
		String strStartIndex = "" + intStartIndex;
		String strEndIndex = "" + intEndIndex;
		String strNoOfCols = "" + intNoOfCols;

		try {
			// code for executing the Stored Proc:
			arlInput.add(sbfReport.toString());
			arlInput.add(strStartIndex);
			arlInput.add(strEndIndex);
			arlInput.add(strNoOfCols);

			arlOutput.add(String.valueOf(VARRAYOFSTRUCT));
			arlOutput.add(String.valueOf(OracleTypes.VARCHAR));
			arlOutput.add(String.valueOf(OracleTypes.REF));

			arlSPResult = executeSP("Fetch_Report_Proc", arlInput, arlOutput);
			ArrayList arlData = (ArrayList) arlSPResult.get(0);
			String strTotRowsFetched = (String) arlSPResult.get(1);
			ArrayList arlExcelData = (ArrayList) arlSPResult.get(2);

			// contains the total number of records that matched the search
			// criteria
			Integer intTotRows = new Integer(strTotRowsFetched);

			int intNoOfColumns = intNoOfCols;
			int intSize = arlData.size();
			int intCount = 0;
			ArrayList arlResultData = new ArrayList();

			ReportsDataBean objReport = new ReportsDataBean();
			Class adminReportClass = objReport.getClass();
			Object[] arrObj = new Object[1];
			Class[] arrClass = new Class[1];
			arrClass[0] = new String("").getClass();
			Method method = null;

			boolean bolDataAvl = false;
			for (int intIndex = 0; intIndex < intSize; intIndex++) {
				bolDataAvl = true;
				if (intCount < intNoOfColumns) {
					arrObj[0] = (String) arlData.get(intIndex);
					method = adminReportClass.getMethod("setColumn"
							+ (intCount + 1), arrClass);
					method.invoke(objReport, arrObj);
				}
				if (intCount >= intTotalNoOfStructAttributes) {
					arlResultData.add(objReport);
					objReport = new ReportsDataBean();
					intCount = -1;
					intIndex--;
				}
				intCount++;
			}
			if (bolDataAvl)
				arlResultData.add(objReport);
			intSize = arlResultData.size();
			for (int intIndex = 0; intIndex < intSize; intIndex++) {
				objReport = (ReportsDataBean) arlResultData.get(intIndex);
			}
			arlResult.add(arlResultData);
			arlResult.add(intTotRows);

			arlResult.add(arlExcelData);
		} catch (SQLException e) {
			throw e;
		} catch (Exception expex) {
			// throw new DBException(ErrorCodes.SQL_QUERY_ERR,
			// expex.toString());
		}
		return arlResult;
	}

	public ArrayList executeSP(String strSPName, ArrayList arlInput,
			ArrayList arlOutData) throws Exception {
		// Decalre method variables.
		java.sql.CallableStatement csStoredProcedure = null;
		ArrayList arlOutput = new ArrayList();
		//Connection conn = null;

		try {
			// Open a connection to the database.

			Session session = HibernetFactory.currentSession();
			//Connection conn = session.connection();
			objCon = session.connection();
			//getOracleConnection();

			int intOutCount = 0;
			int intInputCount = 0;

			// Assign the input array sizes,
			if (arlOutData != null) {
				intOutCount = arlOutData.size();
			}
			if (arlInput != null) {
				intInputCount = arlInput.size();
			}

			// Create the stored procedure calling SQL statement.
			StringBuffer sbrProcedure = new StringBuffer();
			int index = 0;
			sbrProcedure.append("{call " + strSPName + "(");

			/**
			 * Assign the input variables to the stored procedure.
			 */
			while (index < intInputCount) {
				sbrProcedure.append("?");
				index++;
				if (index <= intInputCount - 1) {
					sbrProcedure.append(",");
				}

			}
			/**
			 * Assign the output variables to the stored procedure.
			 */
			index = 0;
			while (index < intOutCount) {
				if (index == 0 && intInputCount == 0) {
					sbrProcedure.append("?");
				} else {
					sbrProcedure.append(", ?");
				}
				index++;
			}

			sbrProcedure.append(")");
			sbrProcedure.append("}");
			csStoredProcedure = objCon.prepareCall(sbrProcedure.toString());
			/**
			 * Register the stored procedure input variables. This method
			 * supports only strings as the input variables.
			 */
			for (index = 0; index < intInputCount; index++) {
				csStoredProcedure.setString(index + 1, (String) arlInput
						.get(index));

			}

			/**
			 * Register the stored procedure output variables. This method
			 * supports strings, Ref curors and arays as out put parameters.
			 */
			// int intTotVar = intInputCount + intOutCount;

			for (int i = 0; i < intOutCount; i++) {
				int intProcIndex = i + intInputCount + 1;
				int itnDataType = 0;
				try {
					itnDataType = Integer
							.parseInt(arlOutData.get(i).toString());
				} catch (Exception expex) {
					// throw new DBException(UNKNOWN_DATA_TYPE,
					// expex.toString());
				}
				switch (itnDataType) {
				case VARRAYOFSTRUCT:
					csStoredProcedure.registerOutParameter(intProcIndex,
							OracleTypes.ARRAY, ADMIN_ARRAY_TYPE);
					break;
				case java.sql.Types.ARRAY:
					csStoredProcedure.registerOutParameter(intProcIndex,
							OracleTypes.ARRAY, ADMIN_ARRAY_TYPE);
					break;
				case java.sql.Types.REF:
					csStoredProcedure.registerOutParameter(intProcIndex,
							OracleTypes.CURSOR);
					break;
				case OracleTypes.VARCHAR:
					csStoredProcedure.registerOutParameter(intProcIndex,
							java.sql.Types.VARCHAR);
					break;
				default:
					// throw new DBException(UNKNOWN_DATA_TYPE,
					// "Unknown Data Type");
				}// End of switch.

			}// end for loop

			// Execute the stored procedure.
			ResultSetMetaData rsMetaData;
			csStoredProcedure.execute();

			for (int i = 0; i < intOutCount; i++) {
				int itnDataType = Integer
						.parseInt(arlOutData.get(i).toString());
				int intProcIndex = intInputCount + i + 1;

				switch (itnDataType) {

				case VARRAYOFSTRUCT: {
					simpleArray = (ARRAY) csStoredProcedure.getObject(intProcIndex);
					ArrayList arlArrayOut = new ArrayList();
					Object[] objArrStructArray = (Object[]) simpleArray.getArray();
					int intArrayCount = objArrStructArray.length;
					for (int j = 0; j < intArrayCount; j++) {
						STRUCT sctArrayStruct = (STRUCT) objArrStructArray[j];
						if (sctArrayStruct != null) {
							Object[] objAttributes = sctArrayStruct
									.getAttributes();
							int intLength = objAttributes.length;
							intTotalNoOfStructAttributes = intLength;
							for (int k = 0; k < intLength; k++) {
								if (objAttributes[k] != null) {
									arlArrayOut.add("" + objAttributes[k]);
								} else {
									arlArrayOut.add("");
								}
							}
						}
					}
					arlOutput.add(arlArrayOut);
					break;
				}
				case java.sql.Types.ARRAY: {
					simpleArray = (ARRAY) csStoredProcedure
							.getObject(intProcIndex);
					String strCellValue = "";
					ArrayList arlArrayOut = new ArrayList();
					String values[] = (String[]) simpleArray.getArray();
					for (int k = 0; k < values.length; k++) {
						if (values[k] == null) {
							break;
						}
						StringTokenizer stringTokenizer = new StringTokenizer(
								values[k], "^^");
						ArrayList arlArrayRow = new ArrayList();
						while (stringTokenizer.hasMoreTokens()) {
							strCellValue = stringTokenizer.nextToken()
									.toString();
							if ((strCellValue != null)
									&& (!strCellValue.trim().equals(""))) {
								arlArrayRow.add(strCellValue);
							} else {
								arlArrayRow.add("");
							}
						}

						arlArrayOut.add(arlArrayRow);

					}
					arlOutput.add(arlArrayOut);
				}
					break;

				case java.sql.Types.REF: {
					ArrayList arlArrayOut = new ArrayList();

					rs = (ResultSet) csStoredProcedure.getObject(intProcIndex);
					rsMetaData = rs.getMetaData();

					int intColumnCount = rsMetaData.getColumnCount();
					while (rs.next()) {
						ArrayList arlArrayRow = new ArrayList();
						for (int loop = 1; loop <= intColumnCount; loop++) {
							String strCellValue = rs.getString(rsMetaData
									.getColumnName(loop));
							if (strCellValue != null) {
								arlArrayRow.add(strCellValue);
							} else {
								arlArrayRow.add(" ");
							}
						}
						arlArrayOut.add(arlArrayRow);
					}
					arlOutput.add(arlArrayOut);
				}
					break;

				case OracleTypes.VARCHAR: {
					String strVal = (String) csStoredProcedure
							.getObject(intProcIndex);
					arlOutput.add(strVal);
				}
					break;

				default:
				}// end of switch

			}// end of for

		} catch (SQLException e) {
			throw e;
		} catch (Exception expex) {
			System.out.println(expex);
			throw expex;
		} finally {
			/*if(csStoredProcedure != null){
				csStoredProcedure.close();
			}*/
			
            if (rs != null) {
                rs.close();
                rs = null;
            }
			
			HibernetFactory.closeSession();
		}
		return arlOutput;
	}
	
	public Connection getOracleConnection() throws Exception {
		
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String username = "cabcacis";
		String password = "cabcacis";

		Class.forName(driver);
		objCon = DriverManager.getConnection(url, username, password);
		return objCon;
	}

}
