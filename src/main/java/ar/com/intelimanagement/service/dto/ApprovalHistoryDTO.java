package ar.com.intelimanagement.service.dto;

import ar.com.intelimanagement.domain.enumeration.ApprovalsStatusType;

public class ApprovalHistoryDTO {

    private UserMinDTO user;
    
    private String role;
    
    private String area;

    private ApprovalsStatusType status;

	public UserMinDTO getUser() {
		return user;
	}

	public void setUser(UserMinDTO user) {
		this.user = user;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public ApprovalsStatusType getStatus() {
		return status;
	}

	public void setStatus(ApprovalsStatusType status) {
		this.status = status;
	}
    
}
