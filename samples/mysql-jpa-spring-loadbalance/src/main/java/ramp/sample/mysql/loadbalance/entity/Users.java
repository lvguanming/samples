package ramp.sample.mysql.loadbalance.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 
 * @author Rama Palaniappan
 * @since Aug 1, 2013
 */
@Entity
@Table(name = "users")
@NamedQueries({
		@NamedQuery(name = "findUserFirstName", query = "select u from Users u "
				+ " where u.firstName = :firstName "),
		@NamedQuery(name = "findUserLastName", query = "select u from Users u "
				+ "  where u.lastName = :lastName "),
		@NamedQuery(name = "findUserFirstAndLastName", query = "select u from Users u "
				+ "  where u.firstName = :firstName and u.lastName = :lastName ") })
public class Users implements Serializable {

	public static final String FIND_USER_FIRST_NAME = "findUserFirstName";
	public static final String FIND_USER_LAST_NAME = "findUserLastName";
	public static final String FIND_USER_FIRST_AND_LAST_NAME = "findUserFirstAndLastName";
	public static final String USER_PARAM_FIRST_NAME = "firstName";
	public static final String USER_PARAM_LAST_NAME = "lastName";

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "email_id", length = 50, unique = true, nullable = false)
	private String emailId = null;
	@Column(name = "first_name", length = 25)
	private String firstName = null;
	@Column(name = "last_name", length = 25)
	private String lastName = null;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_timestamp", nullable = false, length = 19)
	private Date createdTimestamp;
	@Column(name = "last_updated_timestamp", nullable = false, length = 19)
	private Date lastUpdatedTimestamp;

	public Users() {
	}

	public Users(String emailId, String fName, String lName,
			Date createdTimestamp, Date lastUpdatedTimestamp) {
		setEmailId(emailId);
		setFirstName(fName);
		setLastName(lName);
		setCreatedTimestamp(createdTimestamp);
		setLastUpdatedTimestamp(lastUpdatedTimestamp);
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getCreatedTimestamp() {
		return createdTimestamp;
	}

	public void setCreatedTimestamp(Date createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}

	public Date getLastUpdatedTimestamp() {
		return lastUpdatedTimestamp;
	}

	public void setLastUpdatedTimestamp(Date lastUpdatedTimestamp) {
		this.lastUpdatedTimestamp = lastUpdatedTimestamp;
	}

	@PrePersist
	@PreUpdate
	public void onUpdate() {
		setLastUpdatedTimestamp(new Date());
	}

	// Convenience method
	public String toString() {
		return "email-id=" + getEmailId() + ", firstName=" + getFirstName()
				+ ", lastName=" + getLastName() + ", createdTimestamp="
				+ getCreatedTimestamp() + ", lastUpdatedTimestamp="
				+ getLastUpdatedTimestamp() + "\n";
	}
}
