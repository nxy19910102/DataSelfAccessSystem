package applicationEntity;

public class DocumentBackup {
	private Long id;
	private int target;
	private String targetString;
	private int targetYear;
	private int targetSeq;
	private String docTitle;
	private String docStaff;
	private String docDepartment;
	private int startYear;
	private int startMonth;
	private int startDay;
	private int dealYear;
	private int dealMonth;
	private int dealDay;
	private String docDetail;
	private String attachmentAddress;
	private String dealStaff;
	private int docState;
	private String docStateString;
	
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
	public String getDocTitle() {
		return docTitle;
	}
	public void setDocTitle(String docTitle) {
		this.docTitle = docTitle;
	}
	public String getDocStaff() {
		return docStaff;
	}
	public void setDocStaff(String docStaff) {
		this.docStaff = docStaff;
	}
	public String getDocDepartment() {
		return docDepartment;
	}
	public void setDocDepartment(String docDepartment) {
		this.docDepartment = docDepartment;
	}
	public int getStartYear() {
		return startYear;
	}
	public void setStartYear(int startYear) {
		this.startYear = startYear;
	}
	public int getStartMonth() {
		return startMonth;
	}
	public void setStartMonth(int startMonth) {
		this.startMonth = startMonth;
	}
	public int getStartDay() {
		return startDay;
	}
	public void setStartDay(int startDay) {
		this.startDay = startDay;
	}
	public int getDealYear() {
		return dealYear;
	}
	public void setDealYear(int dealYear) {
		this.dealYear = dealYear;
	}
	public int getDealMonth() {
		return dealMonth;
	}
	public void setDealMonth(int dealMonth) {
		this.dealMonth = dealMonth;
	}
	public int getDealDay() {
		return dealDay;
	}
	public void setDealDay(int dealDay) {
		this.dealDay = dealDay;
	}
	public String getDocDetail() {
		return docDetail;
	}
	public void setDocDetail(String docDetail) {
		this.docDetail = docDetail;
	}
	public String getAttachmentAddress() {
		return attachmentAddress;
	}
	public void setAttachmentAddress(String attachmentAddress) {
		this.attachmentAddress = attachmentAddress;
	}
	public String getDealStaff() {
		return dealStaff;
	}
	public void setDealStaff(String dealStaff) {
		this.dealStaff = dealStaff;
	}
	public int getDocState() {
		return docState;
	}
	public void setDocState(int docState) {
		this.docState = docState;
	}
	public String getTargetString() {
		return targetString;
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
	public String getDocStateString() {
		return docStateString;
	}
	public void setDocStateString(int docState) {
		switch(docState){
		case 0: 
			this.docStateString = "正常";
			break;
		case 1:
			this.docStateString = "失效";
			break;
		}
	}
}
