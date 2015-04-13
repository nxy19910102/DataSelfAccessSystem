package administrationEntity;

import java.util.Date;

public class Suggest {
	private Long id;
	private String staffId;
	private String url;
	private String serverPath;
	private String detail;
	private int state;
	private String stateString;
	private Date effDate;
	private Date expDate;
	private String effDateString;
	private String expDateString;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getStaffId() {
		return staffId;
	}
	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getServerPath() {
		return serverPath;
	}
	public void setServerPath(String serverPath) {
		this.serverPath = serverPath;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public Date getEffDate() {
		return effDate;
	}
	public void setEffDate(Date effDate) {
		this.effDate = effDate;
	}
	public Date getExpDate() {
		return expDate;
	}
	public void setExpDate(Date expDate) {
		this.expDate = expDate;
	}
	public String getEffDateString() {
		return effDateString;
	}
	public void setEffDateString(String effDateString) {
		this.effDateString = effDateString;
	}
	public String getExpDateString() {
		return expDateString;
	}
	public void setExpDateString(String expDateString) {
		this.expDateString = expDateString;
	}
	public String getStateString() {
		return stateString;
	}
	public void setStateString(int state) {
		switch (state) {
		case 0 : 
			this.stateString = "已审阅";
			break;
		case 1 : 
			this.stateString = "已提交";
			break;
		}
	}
}