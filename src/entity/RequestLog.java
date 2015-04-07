package entity;

import java.util.Date;

public class RequestLog {
	
	private int id;
	private String session_id;
	private String ip_address;
	private String server_path;
	private String staff_id;
	private String parameters;
	private Date eff_date;
	private String eff_dateString;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSession_id() {
		return session_id;
	}
	public void setSession_id(String session_id) {
		this.session_id = session_id;
	}
	public String getIp_address() {
		return ip_address;
	}
	public void setIp_address(String ip_address) {
		this.ip_address = ip_address;
	}
	public String getServer_path() {
		return server_path;
	}
	public void setServer_path(String server_path) {
		this.server_path = server_path;
	}
	public String getStaff_id() {
		return staff_id;
	}
	public void setStaff_id(String staff_id) {
		this.staff_id = staff_id;
	}
	public String getParameters() {
		return parameters;
	}
	public void setParameters(String parameters) {
		this.parameters = parameters;
	}
	public Date getEff_date() {
		return eff_date;
	}
	public void setEff_date(Date eff_date) {
		this.eff_date = eff_date;
	}
	public String getEff_dateString() {
		return eff_dateString;
	}
	public void setEff_dateString(String eff_dateString) {
		this.eff_dateString = eff_dateString;
	}
}
