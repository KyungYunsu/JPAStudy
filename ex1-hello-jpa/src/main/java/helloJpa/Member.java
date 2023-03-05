package helloJpa;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Member extends BaseEntity{
	@Id
	@GeneratedValue
	@Column(name = "MEMBER_ID")
	private Long id;
	@Column(name = "USERNAME")
	private String userName;
	
	@OneToOne
	@JoinColumn(name = "LOCKER_ID")
	private Locker locker;
	
	@OneToMany(mappedBy = "product")
	private List<MemberProduct> memberProducts = new ArrayList<MemberProduct>();

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
}
