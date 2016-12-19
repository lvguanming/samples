/**
 * 
 */
package ramp.sample.orika.entity;

import java.util.Date;

/**
 * @author Rama Palaniappan
 * @since Apr 13, 2014
 */
public class PaymentMethod {
	private Integer id;
	private Integer userId;
	private Date createdDate1;
	
	public Date getCreatedDate1() {
		return createdDate1;
	}

	public void setCreatedDate1(Date createdDate) {
		this.createdDate1 = createdDate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

}
