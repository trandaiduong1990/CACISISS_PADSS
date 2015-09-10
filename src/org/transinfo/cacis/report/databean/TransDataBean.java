//Package structure
package org.transinfo.cacis.report.databean;

//Java specific imports
import java.io.Serializable;
import java.util.ArrayList;


public class TransDataBean extends  org.transinfo.cacis.report.databean.CommonDataBean implements Serializable
{
	private String strCardNoFrom 	= 	"";
	private String strCardNoTo 	= 	"";
	private String strTransDateFrom 	= 	"";
	private String strTransDateTo 	= 	"";
	private String strTransType 	= 	"";
	private String strReferenceNo 	= 	"";
	private String strResponseCode 	= 	"";
	private String strApprovalCode 	= 	"";
	private String strException	=	"";
	private String strScriptReturn 	= 	"";
	private String strChipTrans 	= 	"";
	private String strRowsPerPage 	= 	"";
	private String strTransTimeFrom 	= 	"";
	private String strTransTimeTo 	= 	"";
        private boolean bolChecked 	= 	false;
	private String strChkIssuerResponse 	= 	"";
	private String strChkEmvResponseCode 	= 	"";
	private String strChkFinalResponse 	= 	"";
	private String strChkApprovalCode 	= 	"";
	private String strChkTraceNo	=	"";
	private String strChkChipTrans 	= 	"";
        private String strChkArpcReturned   =   "";
        private String strChkArqcValidation   =   "";
        private String strChkScriptGenerated   =   "";
        private String strChkCvrTvrStatus   =   "";
        private String strChkReasonException   =   "";

  //for Getting the TranxtypeList added by satyam
    private String strReason 	= 	"";
  private ArrayList TranxTypeList = null;
  private ArrayList ReasonList = null;


    public ArrayList getTranxTypeList() {
        return TranxTypeList;
    }

   
    public void setTranxTypeList(ArrayList TranxTypeList) {
        this.TranxTypeList = TranxTypeList;
    }

	 public ArrayList getReasonList() {
        return ReasonList;
    }

   
    public void setReasonList(ArrayList ReasonList) {
        this.ReasonList = ReasonList;
    }

	/**
	 * Gets the value for strCardNoFrom
	 * @param None
	 * @returns String
	 * @throws None
	 */
	public String getCardNoFrom ()
	{
		return strCardNoFrom;
	}

	/**
	 * Sets the value for strCardNoFrom
	 * @param String
	 * @returns None
	 * @throws None
	 */
	public void setCardNoFrom (String strInputValue)
	{
		strCardNoFrom = strInputValue;
	}

	/**
	 * Gets the value for strCardNoTo
	 * @param None
	 * @returns String
	 * @throws None
	 */
	public String getCardNoTo ()
	{
		return strCardNoTo;
	}

	/**
	 * Sets the value for strCardNoTo
	 * @param String
	 * @returns None
	 * @throws None
	 */
	public void setCardNoTo (String strInputValue)
	{
		strCardNoTo = strInputValue;
	}

	/**
	 * Gets the value for strTransDateFrom
	 * @param None
	 * @returns String
	 * @throws None
	 */
	public String getTransDateFrom ()
	{
		return strTransDateFrom;
	}

	/**
	 * Sets the value for strTransDateFrom
	 * @param String
	 * @returns None
	 * @throws None
	 */
	public void setTransDateFrom (String strInputValue)
	{
		strTransDateFrom = strInputValue;
	}

	/**
	 * Gets the value for strTransDateTo
	 * @param None
	 * @returns String
	 * @throws None
	 */
	public String getTransDateTo ()
	{
		return strTransDateTo;
	}

	/**
	 * Sets the value for strTransDateTo
	 * @param String
	 * @returns None
	 * @throws None
	 */
	public void setTransDateTo (String strInputValue)
	{
		strTransDateTo = strInputValue;
	}

	/**
	 * Gets the value for strTransType
	 * @param None
	 * @returns String
	 * @throws None
	 */
	public String getTransType ()
	{
		return strTransType;
	}

	/**
	 * Sets the value for strTransType
	 * @param String
	 * @returns None
	 * @throws None
	 */
	public void setTransType (String strInputValue)
	{
		strTransType = strInputValue;
	}

	/**
	 * Gets the value for strReferenceNo
	 * @param None
	 * @returns String
	 * @throws None
	 */
	public String getReferenceNo ()
	{
		return strReferenceNo;
	}

	/**
	 * Sets the value for strReferenceNo
	 * @param String
	 * @returns None
	 * @throws None
	 */
	public void setReferenceNo (String strInputValue)
	{
		strReferenceNo = strInputValue;
	}

	/**
	 * Gets the value for strResponseCode
	 * @param None
	 * @returns String
	 * @throws None
	 */
	public String getResponseCode  ()
	{
		return strResponseCode;
	}

	/**
	 * Sets the value for strResponseCode
	 * @param String
	 * @returns None
	 * @throws None
	 */
	public void setResponseCode (String strInputValue)
	{
		strResponseCode = strInputValue;
	}

	/**
	 * Gets the value for strApprovalCode
	 * @param None
	 * @returns String
	 * @throws None
	 */
	public String getApprovalCode ()
	{
		return strApprovalCode;
	}

	/**
	 * Sets the value for strApprovalCode
	 * @param String
	 * @returns None
	 * @throws None
	 */
	public void setApprovalCode (String strInputValue)
	{
		strApprovalCode = strInputValue;
	}

	/**
	 * Gets the value for strException
	 * @param None
	 * @returns String
	 * @throws None
	 */
	public String getException ()
	{
		return strException;
	}

	/**
	 * Sets the value for strException
	 * @param String
	 * @returns None
	 * @throws None
	 */
	public void setException (String strInputValue)
	{
		strException = strInputValue;
	}

	/**
	 * Gets the value for strScriptReturn
	 * @param None
	 * @returns String
	 * @throws None
	 */
	public String getScriptReturn ()
	{
		return strScriptReturn;
	}

	/**
	 * Sets the value for strScriptReturn
	 * @param String
	 * @returns None
	 * @throws None
	 */
	public void setScriptReturn (String strInputValue)
	{
		strScriptReturn = strInputValue;
	}

	/**
	 * Gets the value for strChipTrans
	 * @param None
	 * @returns String
	 * @throws None
	 */
	public String getChipTrans ()
	{
		return strChipTrans;
	}

	/**
	 * Sets the value for strChipTrans
	 * @param String
	 * @returns None
	 * @throws None
	 */
	public void setChipTrans (String strInputValue)
	{
		strChipTrans = strInputValue;
	}

	/**
	 * Gets the value for strRowsPerPage
	 * @param None
	 * @returns String
	 * @throws None
	 */
	public String getRowsPerPage ()
	{
		return strRowsPerPage;
	}

	/**
	 * Sets the value for strRowsPerPage
	 * @param String
	 * @returns None
	 * @throws None
	 */
	public void setRowsPerPage (String strInputValue)
	{
		strRowsPerPage = strInputValue;
	}

	/**
	 * Gets the value for strTransTimeFrom
	 * @param None
	 * @returns String
	 * @throws None
	 */
	public String getTransTimeFrom ()
	{
		return strTransTimeFrom;
	}

	/**
	 * Sets the value for strTransTimeFrom
	 * @param String
	 * @returns None
	 * @throws None
	 */
	public void setTransTimeFrom (String strInputValue)
	{
		strTransTimeFrom = strInputValue;
	}

	/**
	 * Gets the value for strTransTimeTo
	 * @param None
	 * @returns String
	 * @throws None
	 */
	public String getTransTimeTo ()
	{
		return strTransTimeTo;
	}

	/**
	 * Sets the value for strTransTimeTo
	 * @param String
	 * @returns None
	 * @throws None
	 */
	public void setTransTimeTo (String strInputValue)
	{
		strTransTimeTo = strInputValue;
	}

	/**
	 * Gets the value for bolChecked
	 * @param None
	 * @returns boolean
	 * @throws None
	 */
	public boolean getChecked ()
	{
		return bolChecked;
	}

	/**
	 * Sets the value for bolChecked
	 * @param boolean
	 * @returns None
	 * @throws None
	 */
	public void setChecked (boolean bolInputValue)
	{
		bolChecked = bolInputValue;
	}

        /**
	 * Gets the value for strChkIssuerResponse
	 * @param None
	 * @returns String
	 * @throws None
	 */
	public String getChkIssuerResponse ()
	{
		return strChkIssuerResponse;
	}

	/**
	 * Sets the value for strChkIssuerResponse
	 * @param String
	 * @returns None
	 * @throws None
	 */
	public void setChkIssuerResponse (String strInputValue)
	{
		strChkIssuerResponse = strInputValue;
	}

	/**
	 * Gets the value for strChkEmvResponseCode
	 * @param None
	 * @returns String
	 * @throws None
	 */
	public String getChkEmvResponseCode ()
	{
		return strChkEmvResponseCode;
	}

	/**
	 * Sets the value for strChkEmvResponseCode
	 * @param String
	 * @returns None
	 * @throws None
	 */
	public void setChkEmvResponseCode (String strInputValue)
	{
		strChkEmvResponseCode = strInputValue;
	}

	/**
	 * Gets the value for strChkFinalResponse
	 * @param None
	 * @returns String
	 * @throws None
	 */
	public String getChkFinalResponse ()
	{
		return strChkFinalResponse;
	}

	/**
	 * Sets the value for strChkFinalResponse
	 * @param String
	 * @returns None
	 * @throws None
	 */
	public void setChkFinalResponse (String strInputValue)
	{
		strChkFinalResponse = strInputValue;
	}

	/**
	 * Gets the value for strChkApprovalCode
	 * @param None
	 * @returns String
	 * @throws None
	 */
	public String getChkApprovalCode ()
	{
		return strChkApprovalCode;
	}

	/**
	 * Sets the value for strChkApprovalCode
	 * @param String
	 * @returns None
	 * @throws None
	 */
	public void setChkApprovalCode (String strInputValue)
	{
		strChkApprovalCode = strInputValue;
	}

	/**
	 * Gets the value for strChkTraceNo
	 * @param None
	 * @returns String
	 * @throws None
	 */
	public String getChkTraceNo ()
	{
		return strChkTraceNo;
	}

	/**
	 * Sets the value for strChkTraceNo
	 * @param String
	 * @returns None
	 * @throws None
	 */
	public void setChkTraceNo (String strInputValue)
	{
		strChkTraceNo = strInputValue;
	}

	/**
	 * Gets the value for strChkChipTrans
	 * @param None
	 * @returns String
	 * @throws None
	 */
	public String getChkChipTrans ()
	{
		return strChkChipTrans;
	}

	/**
	 * Sets the value for strChkChipTrans
	 * @param String
	 * @returns None
	 * @throws None
	 */
	public void setChkChipTrans (String strInputValue)
	{
		strChkChipTrans = strInputValue;
	}

	/**
	 * Gets the value for strChkArpcReturned
	 * @param None
	 * @returns String
	 * @throws None
	 */
	public String getChkArpcReturned ()
	{
		return strChkArpcReturned;
	}

	/**
	 * Sets the value for strChkArpcReturned
	 * @param String
	 * @returns None
	 * @throws None
	 */
	public void setChkArpcReturned (String strInputValue)
	{
		strChkArpcReturned = strInputValue;
	}

	/**
	 * Gets the value for strChkArqcValidation
	 * @param None
	 * @returns String
	 * @throws None
	 */
	public String getChkArqcValidation ()
	{
		return strChkArqcValidation;
	}

	/**
	 * Sets the value for strChkArqcValidation
	 * @param String
	 * @returns None
	 * @throws None
	 */
	public void setChkArqcValidation (String strInputValue)
	{
		strChkArqcValidation = strInputValue;
	}

	/**
	 * Gets the value for strChkScriptGenerated
	 * @param None
	 * @returns String
	 * @throws None
	 */
	public String getChkScriptGenerated ()
	{
		return strChkScriptGenerated;
	}

	/**
	 * Sets the value for strChkScriptGenerated
	 * @param String
	 * @returns None
	 * @throws None
	 */
	public void setChkScriptGenerated (String strInputValue)
	{
		strChkScriptGenerated = strInputValue;
	}


	/**
	 * Gets the value for strChkCvrTvrStatus
	 * @param None
	 * @returns String
	 * @throws None
	 */
	public String getChkCvrTvrStatus ()
	{
		return strChkCvrTvrStatus;
	}

	/**
	 * Sets the value for strChkCvrTvrStatus
	 * @param String
	 * @returns None
	 * @throws None
	 */
	public void setChkCvrTvrStatus (String strInputValue)
	{
		strChkCvrTvrStatus = strInputValue;
	}


	/**
	 * Gets the value for strChkReasonException
	 * @param None
	 * @returns String
	 * @throws None
	 */
	public String getChkReasonException ()
	{
		return strChkReasonException;
	}

	/**
	 * Sets the value for strChkReasonException
	 * @param String
	 * @returns None
	 * @throws None
	 */
	public void setChkReasonException (String strInputValue)
	{
		strChkReasonException = strInputValue;
	}

public String getReason ()
	{
		return strReason;
	}

public void setReason (String strInputValue)
	{
		strReason = strInputValue;
	}



}