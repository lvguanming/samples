/**
 * 
 */
package ramp.sample.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Rama Palaniappan
 * @since Jun 26, 2014
 */
public class ExtractMMID {

	public String extractMMID(String message) {
		String ret = extractMmidIcici(message);
		if (ret == null) {
			ret = extractMmidCiti(message);
		}
		return ret;
	}
	
	public String extractMmidIcici(String message) {
		String regex = ".*?MMID is (\\d{1,7})$";
	    Pattern pattern = Pattern.compile(regex);
	    
	    Matcher matcher = pattern.matcher(message);
	    if (matcher.find()) {
	    	return matcher.group(1);
	    } else {
	    	return null;
	    }
	}
	
	public String extractMmidCiti(String message) {
		String regex = ".*?account number .*?\\d is (\\d{1,7}). Please .*$";
	    Pattern pattern = Pattern.compile(regex);
	    
	    Matcher matcher = pattern.matcher(message);
	    if (matcher.find()) {
	    	return matcher.group(1);
	    } else {
	    	return null;
	    }		
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String icici = "We have successfully generated MMID and your MMID is 1234567";
		String citi = "MMID for IMPS transfers to account number *******801 is 1234567. Please share this MMD and your registered mobile number with remitter";
		ExtractMMID extractMMID = new ExtractMMID();
		System.out.println(extractMMID.extractMMID(icici));
		System.out.println(extractMMID.extractMMID(citi));
	}

}
