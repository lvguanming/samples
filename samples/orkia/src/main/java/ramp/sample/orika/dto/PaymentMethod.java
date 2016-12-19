/**
 * 
 */
package ramp.sample.orika.dto;

import java.util.Date;

/**
 * @author Rama Palaniappan
 * @since Mar 31, 2014
 */
public abstract class PaymentMethod {
	private String id = null;
	private Metadata metadata;
	
	public Metadata getMetadata() {
		return metadata;
	}

	public void setMetadata(Metadata metadata) {
		this.metadata = metadata;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
