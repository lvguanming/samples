/**
 * 
 */
package ramp.sample.orika.entity;

import java.util.Date;

/**
 * @author Rama Palaniappan
 * @since Mar 31, 2014
 */
public class CardPaymentMethod extends PaymentMethod {
	private String cardNumberString = null;
	private String nameOnCard = null;
	private Date expiryDate = null;

	public String getCardNumberString() {
		return cardNumberString;
	}

	public void setCardNumberString(String cardNumberString) {
		this.cardNumberString = cardNumberString;
	}

	public String getNameOnCard() {
		return nameOnCard;
	}

	public void setNameOnCard(String nameOnCard) {
		this.nameOnCard = nameOnCard;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}
}
