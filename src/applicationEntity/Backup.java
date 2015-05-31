package applicationEntity;

public class Backup {
	private Long id;
	private int target;
	private String targetString;
	private int targetYear;
	private int targetSeq;
	private String applyStaff;
	private String approveStaff;
	private String accNbr;
	private String offerOld;
	private String offerNew;
	private String promotion;
	private String effDate;
	private String expDate;
	private String note;
	private String attachmentAddress;
	private int state;
	private String stateString;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getTarget() {
		return target;
	}
	public void setTarget(int target) {
		this.target = target;
	}
	public String getTargetString() {
		return targetString;
	}
	public int getTargetYear() {
		return targetYear;
	}
	public void setTargetYear(int targetYear) {
		this.targetYear = targetYear;
	}
	public int getTargetSeq() {
		return targetSeq;
	}
	public void setTargetSeq(int targetSeq) {
		this.targetSeq = targetSeq;
	}
	public String getApplyStaff() {
		return applyStaff;
	}
	public void setApplyStaff(String applyStaff) {
		this.applyStaff = applyStaff;
	}
	public String getApproveStaff() {
		return approveStaff;
	}
	public void setApproveStaff(String approveStaff) {
		this.approveStaff = approveStaff;
	}
	public String getAccNbr() {
		return accNbr;
	}
	public void setAccNbr(String accNbr) {
		this.accNbr = accNbr;
	}
	public String getOfferOld() {
		return offerOld;
	}
	public void setOfferOld(String offerOld) {
		this.offerOld = offerOld;
	}
	public String getOfferNew() {
		return offerNew;
	}
	public void setOfferNew(String offerNew) {
		this.offerNew = offerNew;
	}
	public String getPromotion() {
		return promotion;
	}
	public void setPromotion(String promotion) {
		this.promotion = promotion;
	}
	public String getEffDate() {
		return effDate;
	}
	public void setEffDate(String effDate) {
		this.effDate = effDate;
	}
	public String getExpDate() {
		return expDate;
	}
	public void setExpDate(String expDate) {
		this.expDate = expDate;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getAttachmentAddress() {
		return attachmentAddress;
	}
	public void setAttachmentAddress(String attachmentAddress) {
		this.attachmentAddress = attachmentAddress;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getStateString() {
		return stateString;
	}
	
	public void setTargetString(int target) {
		switch(target){
		case 1:
			this.targetString = "签报";
			break;
		case 2:
			this.targetString = "内部联系单";
			break;
		default :
			this.targetString = "其他";
		}
	}
	public void setStateString(int state) {
		switch(state){
		case 0: 
			this.stateString = "正常";
			break;
		case 1:
			this.stateString = "失效";
			break;
		}
	}
}
