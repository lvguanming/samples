/**
 * 
 */
package ramp.sample.regex;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Rama Palaniappan
 * @since Nov 21, 2014
 */
public class ExtractCrashedTimestamp {

	
	private String getTimeStamp(String raw) {
		String regex = ".*Date/Time:\\s*(.*)\\n.*";
	    Pattern pattern = Pattern.compile(regex);
	    Matcher matcher = pattern.matcher(raw);
	    if (matcher.find()) {
	    	return matcher.group(1);
	    }
	    return null;
	}
	
	private Date getDate(String dateString) {
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z");
		Date date = null;
		try {
			date = formatter.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ExtractCrashedTimestamp eCrashedTimestamp = new ExtractCrashedTimestamp();
		String crashReportString = 
						"Parent Process:  launchd_sim [82931]\n" +
						"\n" +
						"Date/Time:       2014-09-05 04:35:08 +0000\n" +
						"OS Version:      Mac OS X 8.0 (13E28)\n" +
						"Report Version:  104";
		String dateString = eCrashedTimestamp.getTimeStamp(crashReportString);
		System.out.println(dateString);
		System.out.println(eCrashedTimestamp.getDate(dateString));
	}

}
