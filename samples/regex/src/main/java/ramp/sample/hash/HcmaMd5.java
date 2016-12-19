/**
 * 
 */
package ramp.sample.hash;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @author Rama Palaniappan
 * @since Jul 5, 2014
 */
public class HcmaMd5 {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		String text = "IMXzaP8B21fgihiPS|1234|5678|100";
		String secretString = "CUMVALLU9";
		System.out.println(hmacDigest(
				text, secretString));
		System.out.println(hmacDigest(
				text, secretString,
				"HmacMD5"));
	}

	public static String hmacDigest(String msg, String keyString) {
		return hmacDigest(msg, keyString, "HmacMD5");
	}
	
	public static String hmacDigest(String msg, String keyString, String algo) {
		String digest = null;
		try {
			SecretKeySpec key = new SecretKeySpec(
					(keyString).getBytes("UTF-8"), algo);
			Mac mac = Mac.getInstance(algo);
			mac.init(key);

			byte[] bytes = mac.doFinal(msg.getBytes("ASCII"));

			StringBuffer hash = new StringBuffer();
			for (int i = 0; i < bytes.length; i++) {
				String hex = Integer.toHexString(0xFF & bytes[i]);
				if (hex.length() == 1) {
					hash.append('0');
				}
				hash.append(hex);
			}
			digest = hash.toString();
		} catch (UnsupportedEncodingException e) {
		} catch (InvalidKeyException e) {
		} catch (NoSuchAlgorithmException e) {
		}
		return digest;
	}
}
