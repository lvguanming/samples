/**
 * 
 */
package ramp.sample.hibernatejpaspring.entity;

import java.io.Serializable;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Rama Palaniappan
 * @since Mar 28, 2014
 */

public class BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Log LOG = LogFactory.getLog(BaseEntity.class);

	@PrePersist
	@PreUpdate
	public void onUpdate() {
		LOG.info("On Update");
	}
}
