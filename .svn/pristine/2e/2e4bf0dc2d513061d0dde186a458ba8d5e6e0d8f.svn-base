package org.transinfo.cacis.log;

import java.io.File;
import java.io.FileOutputStream;
/**
 *  DebugToFile is the common class to all modules which is used to log the data into the log files.
 */

public class DebugToFile {
    private FileOutputStream foStream;
    private String strLogDir;
    private String strLogFile;
    private boolean blnLogFlag = false;
    private String strToday = "";
    private String orgLogFile = "";

    /**
     * Default Constructor.
     */
    private DebugToFile() {}

    /**
     * This method inits the Debug File.
     * @param logDir Direcotry path of the log file.
     * @param logFile File name of the log file.
     * @param logFlag Flag indicated whether to log the data or not.
     */
    public DebugToFile(String logDir, String logFile, boolean logFlag) {
        strLogDir = logDir;
        strLogFile = logFile;
        blnLogFlag = logFlag;
    }

    /**
     * This method logs the data into the File.
     * @param str String data to be logged
     */

   public void log(String str) {

        try {

            if(blnLogFlag){
             String strThisDay =""; //DateUtil.getDateValue("YYYYMMDD");
                if(!strThisDay.equals(strToday)) {
                    if(foStream != null)
                        foStream.close();

                    if (strLogFile.indexOf(strToday) != -1 && !strToday.trim().equals(""))
                        orgLogFile = strLogFile.substring(0,strLogFile.indexOf("_"+strToday));
                    else
                        orgLogFile = strLogFile.substring(0,strLogFile.lastIndexOf("."));

                    strLogFile = orgLogFile + "_" + strThisDay + strLogFile.substring(strLogFile.lastIndexOf("."));
                    foStream = new FileOutputStream(strLogDir + File.separator + strLogFile, true);
                    strToday = strThisDay;
                }
              //  str = DateUtil.getDateValue("DD/MM/YYYY HH:MI:SS") + " " + str + "\n";
                foStream.write(str.getBytes());
            }
        }
        catch(Exception exception) {
            exception.printStackTrace();
        }
    }

    /**
     * This method logs the data into the File.
     * @param moduleId Module id.
     * @param actionCode Action Code.
     * @param port Port number.
     * @param status Status.
     * @param data Data to be logged.
     */

   public void log(String moduleId, String actionCode, String status, String data){

        String formMsg = "";
        String moduleDesc = ""; //get the module description of module id from database
        String actionDesc = ""; //get the action description of action code from database
        formMsg = moduleId+" "+moduleDesc+" "+actionCode+" "+actionDesc+" "+status+" "+data;
        log(formMsg);

    }
}
