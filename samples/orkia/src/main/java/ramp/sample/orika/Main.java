/**
 * 
 */
package ramp.sample.orika;

import java.util.Date;

import ramp.sample.orika.dto.Metadata;

/**
 * @author Rama Palaniappan
 * @since Apr 13, 2014
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Main main = new Main();
		main.map();
	}

	private void map() {
		ramp.sample.orika.dto.BankPaymentMethod bankPaymentMethodDto = getBankPaymentMethodDto();
		OrikaMapper mapper = new OrikaMapper();
		printBankPaymentMethodDto(bankPaymentMethodDto);
		ramp.sample.orika.entity.BankPaymentMethod bankPaymentMethodEntity = mapper
				.map(bankPaymentMethodDto,
						ramp.sample.orika.entity.BankPaymentMethod.class);
		printBankPaymentMethodEntity(bankPaymentMethodEntity);
		printBankPaymentMethodDto(mapper.map(bankPaymentMethodEntity,
				ramp.sample.orika.dto.BankPaymentMethod.class));

		DateOrikaMapper dateMapper = new DateOrikaMapper();
		printBankPaymentMethodDto(bankPaymentMethodDto);
		ramp.sample.orika.entity.BankPaymentMethod bankPaymentMethodEntity1 = dateMapper
				.map(bankPaymentMethodDto,
						ramp.sample.orika.entity.BankPaymentMethod.class);
		printBankPaymentMethodEntity(bankPaymentMethodEntity1);
		printBankPaymentMethodDto(dateMapper.map(bankPaymentMethodEntity,
				ramp.sample.orika.dto.BankPaymentMethod.class));

	}

	private ramp.sample.orika.dto.BankPaymentMethod getBankPaymentMethodDto() {
		ramp.sample.orika.dto.BankPaymentMethod bankPaymentMethodDto = new ramp.sample.orika.dto.BankPaymentMethod();
		bankPaymentMethodDto.setAccountNumber("test-account");
		bankPaymentMethodDto.setId("1-asd");
//		bankPaymentMethodDto.setName("test-name");
		bankPaymentMethodDto.setRoutingNumber("test-routing-number");
		Metadata metadata = new Metadata();
		metadata.setCreatedDate(new Date().toString());
		bankPaymentMethodDto.setMetadata(metadata);
		return bankPaymentMethodDto;
	}
	
	private void printBankPaymentMethodDto(ramp.sample.orika.dto.BankPaymentMethod bankPaymentMethodDto) {
		System.out.println("------------------------------------------");
		System.out.println("ramp.sample.orika.dto.BankPaymentMethod");
		System.out.println("id=" + bankPaymentMethodDto.getId());
		System.out.println("accountNumber=" + bankPaymentMethodDto.getAccountNumber());
		System.out.println("name=" + bankPaymentMethodDto.getName());
		System.out.println("routingNumber=" + bankPaymentMethodDto.getRoutingNumber());
		if (bankPaymentMethodDto != null && bankPaymentMethodDto.getMetadata() != null ) {
		System.out.println("date=" + bankPaymentMethodDto.getMetadata().getCreatedDate());
		}
	}
	
	private void printBankPaymentMethodEntity(ramp.sample.orika.entity.BankPaymentMethod bankPaymentMethodEntity) {
		System.out.println("------------------------------------------");
		System.out.println("ramp.sample.orika.entity.BankPaymentMethod");
		System.out.println("id=" + bankPaymentMethodEntity.getId());
		System.out.println("accountNumber=" + bankPaymentMethodEntity.getAccountNumber());
		System.out.println("name=" + bankPaymentMethodEntity.getName());
		System.out.println("routingNumber=" + bankPaymentMethodEntity.getRoutingNumber());
		System.out.println("date=" + bankPaymentMethodEntity.getCreatedDate1());
	}

}
