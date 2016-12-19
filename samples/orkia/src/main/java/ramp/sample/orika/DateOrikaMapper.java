/**
 * 
 */
package ramp.sample.orika;

import ma.glasnost.orika.MapperFactory;

/**
 * @author Rama Palaniappan
 * @since Apr 14, 2014
 */
public class DateOrikaMapper extends OrikaMapper {
	@Override
	protected void configure(MapperFactory factory) {
		super.configure(factory);
		factory.classMap(ramp.sample.orika.dto.PaymentMethod.class,
				ramp.sample.orika.entity.PaymentMethod.class)
				.fieldAToB("id", "id").field("metadata.createdDate", "createdDate1")
				.byDefault().register();
	}
}
