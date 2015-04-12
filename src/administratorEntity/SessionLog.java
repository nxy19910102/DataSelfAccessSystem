package administratorEntity;

import java.util.Date;

public class SessionLog {
	
	private Long id;
	private String sessionId;
	private String ipAddress;
	private String staffId;
	private Date effDate;
	private String effDateString;
	private Date expDate;
	private String expDateString;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public String getStaffId() {
		return staffId;
	}
	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}
	public Date getEffDate() {
		return effDate;
	}
	public void setEffDate(Date effDate) {
		this.effDate = effDate;
	}
	public String getEffDateString() {
		return effDateString;
	}
	public void setEffDateString(String effDateString) {
		this.effDateString = effDateString;
	}
	public Date getExpDate() {
		return expDate;
	}
	public void setExpDate(Date expDate) {
		this.expDate = expDate;
	}
	public String getExpDateString() {
		return expDateString;
	}
	public void setExpDateString(String expDateString) {
		this.expDateString = expDateString;
	}
}
