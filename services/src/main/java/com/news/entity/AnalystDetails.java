package com.news.entity;
import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
@Entity
@Table(name="AnalystDetails")
public class AnalystDetails implements Serializable{
	
 private static final long serialVersionUID = 1L;
		@Id
		@GeneratedValue(strategy=GenerationType.AUTO)
		@Column(name="Id")
	    private long Id;
		
		@NotBlank(message = "Name cannot be empty!")
		@Size(min=3, max = 30, message = "Name cannot be less than 3 characters and more than 30 characters!")
		@Column(name="username")
	    private String username;
		
		@NotBlank(message = "Email cannot be empty!")
		@Size(max = 255, message = "Email cannot exceed 255 characters!")
		@Pattern(regexp = ".+@.+\\..+", message = "Invalid email address!")
		@Column(name="email")	
		private String email;
        
		@NotBlank(message = "Password cannot be empty!")
		@Column(name="password")	
		private String password;
		
		boolean activeStatus;
		
		@NotNull(message="please specify role")
		int roles;
		
		
		
		public AnalystDetails() {	
		}
		
		public AnalystDetails(String username, String email, String password, boolean activeStatus, int roles) {
			this.username = username; this.activeStatus=activeStatus;
			this.email = email; this.roles=roles;
			this.password = password;
		}
		
		public AnalystDetails(String email, String password) {
			this.email = email;
			this.password = password;
		}
		
		
		public long getId() {
			return Id;
		}
		public void setId(long Id) {
			this.Id = Id;
		}
		
		public int getRoles() {
			return roles;
		}
		public void setRoles(int roles) {
			this.roles = roles;
		}
		
		public String getusername() {
			return username;
		}
		public void setusername(String username) {
			this.username = username;
		}
		public String getemail() {
			return email;
		}
		public void setemail(String email) {
			this.email = email;
		}
	
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		
		public boolean isActiveStatus() {
			return activeStatus;
		}
		public void setActiveStatus(boolean activeStatus) {
			this.activeStatus = activeStatus;
		}
		public boolean getActiveStatus() {
			return activeStatus;
		}
		
		@Override
		public String toString() {
			return "AnalystDetails [analystId=" + Id + ", username=" + username + ", email=" + email
					+ ", password=" + password + "]";
		}
}
		
	

