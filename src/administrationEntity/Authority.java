package administrationEntity;

public class Authority {
	private Long id;
	private String staffId;
	private int administrator;
	
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
	public int getAdministrator() {
		return administrator;
	}
	public void setAdministrator(int i) {
		this.administrator = i;
	}
}
