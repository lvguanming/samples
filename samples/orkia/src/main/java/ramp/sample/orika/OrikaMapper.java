/**
 * 
 */
package ramp.sample.orika;

import ma.glasnost.orika.CustomConverter;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;

/**
 * @author Rama Palaniappan
 * @since Apr 13, 2014
 */
public class OrikaMapper extends ConfigurableMapper {

	@Override
	protected void configure(MapperFactory factory) {
		// TODO Auto-generated method stub
		super.configure(factory);
		factory.classMap(ramp.sample.orika.dto.PaymentMethod.class,
				ramp.sample.orika.entity.PaymentMethod.class)
				.fieldAToB("id", "id").exclude("createdDate").register();

		factory.classMap(ramp.sample.orika.dto.BankPaymentMethod.class,
				ramp.sample.orika.entity.BankPaymentMethod.class)
				.mapNulls(false).byDefault().register();
		factory.classMap(ramp.sample.orika.dto.CardPaymentMethod.class,
				ramp.sample.orika.entity.CardPaymentMethod.class)
				.mapNulls(false).byDefault()
				.register();

		// register String to Integer converter
		factory.getConverterFactory().registerConverter(
				new CustomConverter<String, Integer>() {
					public Integer convert(
							String source,
							ma.glasnost.orika.metadata.Type<? extends Integer> target) {
						source = source.substring(0, source.indexOf('-'));
						return Integer.valueOf(source);
					}
				});

		// // register Integer to String converter
		// factory.getConverterFactory().registerConverter(
		// new CustomConverter<Integer, String>() {
		// public String convert(
		// Integer source,
		// ma.glasnost.orika.metadata.Type<? extends String> target) {
		// return source.toString();
		// }
		// });
	}

}
