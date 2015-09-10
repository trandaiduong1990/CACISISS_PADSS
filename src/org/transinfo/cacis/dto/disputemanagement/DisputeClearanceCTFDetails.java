package org.transinfo.cacis.dto.disputemanagement;

import java.io.Serializable;

@SuppressWarnings("serial")
public class DisputeClearanceCTFDetails implements Serializable {

	private String settlementId;
	private DisputeCleaningMasterDto incomingMaster;
	private String tcr0;
	private String tcr1;
	private String tcr5;
	private String tcr7;

	public String getSettlementId() {
		return settlementId;
	}

	public void setSettlementId(String settlementId) {
		this.settlementId = settlementId;
	}

	public String getTcr0() {
		return tcr0;
	}

	public void setTcr0(String tcr0) {
		this.tcr0 = tcr0;
	}

	public String getTcr1() {
		return tcr1;
	}

	public void setTcr1(String tcr1) {
		this.tcr1 = tcr1;
	}

	public String getTcr5() {
		return tcr5;
	}

	public void setTcr5(String tcr5) {
		this.tcr5 = tcr5;
	}

	public String getTcr7() {
		return tcr7;
	}

	public void setTcr7(String tcr7) {
		this.tcr7 = tcr7;
	}

	public DisputeCleaningMasterDto getIncomingMaster() {
		return incomingMaster;
	}

	public void setIncomingMaster(DisputeCleaningMasterDto incomingMaster) {
		this.incomingMaster = incomingMaster;
	}

}
