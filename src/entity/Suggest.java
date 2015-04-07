package entity;

import java.util.Date;

public class Suggest {
	private int id;
	private String staff_id;
	private String url;
	private String server_path;
	private String detail;
	private String state;
	private Date eff_date;
	private Date exp_date;
	private String eff_dateString;
	private String exp_dateString;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStaff_id() {
		return staff_id;
	}
	public void setStaff_id(String staff_id) {
		this.staff_id = staff_id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getServer_path() {
		return server_path;
	}
	public void setServer_path(String server_path) {
		this.server_path = server_path;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Date getEff_date() {
		return eff_date;
	}
	public void setEff_date(Date eff_date) {
		this.eff_date = eff_date;
	}
	public Date getExp_date() {
		return exp_date;
	}
	public void setExp_date(Date exp_date) {
		this.exp_date = exp_date;
	}
	public String getEff_dateString() {
		return eff_dateString;
	}
	public void setEff_dateString(String eff_dateString) {
		this.eff_dateString = eff_dateString;
	}
	public String getExp_dateString() {
		return exp_dateString;
	}
	public void setExp_dateString(String exp_dateString) {
		this.exp_dateString = exp_dateString;
	}
}