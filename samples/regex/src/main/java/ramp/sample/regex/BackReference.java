package ramp.sample.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BackReference {
	
	public String replaceWithBackReference(String raw) {
	    String regex = "(\\d+\\s+[\\w\\.]*)(.*)(\n)";
	    Pattern pattern = Pattern.compile(regex);

	    Matcher matcher = pattern.matcher(raw);

	    String ret = matcher.replaceAll("$1$3");
	    return ret;
	}

	public void printReplace(String raw) {
		System.out.println("RAW String: " + raw);
		System.out.println("REPLACED String: " + replaceWithBackReference(raw));
		System.out.println();
	}
	
	public static void main(String[] args) {
		BackReference br = new BackReference();
	    String raw1 = "0   libsystem_kernel.dylib              0x355ab004 0x355aa000 + 4100\n";
		String raw2 = "6   a.b.c..com..                             (PieGraphController didReceiveMemoryWarning)\n";

		br.printReplace(raw1);
		br.printReplace(raw2);
	}
}
