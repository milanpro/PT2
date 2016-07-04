package assignment6;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Simple string hashing function based on registered Java {@link MessageDigest} implementations
 * 
 */
public class JavaMDHashFunction implements HashFunction<String> {
	/**
	 * Simple hash function based on md5
	 */
	public static final JavaMDHashFunction MD5 = new JavaMDHashFunction("MD5");
	/**
	 * Simple hash function based on sha-1
	 */
	public static final JavaMDHashFunction SHA1 = new JavaMDHashFunction("SHA-1");
	/**
	 * Simple hash function based on sha-1
	 */
	public static final JavaMDHashFunction SHA256 = new JavaMDHashFunction("SHA-256");
	
	private final String algorithm;
	public JavaMDHashFunction(String algorithm) {
		this.algorithm = algorithm;
	}

	/**
	 * gets the byte representation of the input string
	 * 
	 * @param input
	 *            the String
	 * @return byte representation
	 */
	protected byte[] digest(String input) {
		try {
			return MessageDigest.getInstance(algorithm)
					.digest(input.getBytes());
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalStateException(e);
		}
	}

	/**
	 * transform byte array to integer
	 * 
	 * @param digest
	 *            the digest byte array
	 * @return the integer representation (not lossless)
	 */
	//*
	// consider first 4 bytes only bytes
	protected int bytes2Int(byte[] digest) {
		int res = 0;
		for (int i = 0; i < 4; i++) {
			res = res << 8;
			res |= (int) (digest[i] & 0xff);
		}
		return res;
	}

	/*/
	// consider all digest bytes
	private int bytes2Int(byte[] digest) {
		int[] resArray = new int[(int) Math.ceil(digest.length / 4.0) + 1];
		for (int i = 0; i < digest.length; i++) {
			int resIndex = (int) Math.floor(digest.length / 4.0);
			resArray[resIndex] = resArray[resIndex] << 8;
			resArray[resIndex] |= (int) (digest[i] & 0xff);
		}
		int res = 0;
		for (int i = 0; i < resArray.length; i++) {
			res ^= resArray[i];
		}
		return res;
	}
	//*/

	/*
	 * (non-Javadoc)
	 * 
	 * @see assignment6.HashFunction#hash(java.lang.Object)
	 */
	@Override
	public int hash(String input) {
		return bytes2Int(digest(input));
	}

	/**
	 * gets the message digest string representation of the given input
	 * 
	 * @param input
	 *            the input
	 * @return the message digest String of the given input
	 */
	public String hashString(String input) {
		StringBuilder sb = new StringBuilder(
				new BigInteger(1, digest(input)).toString(16));
		// in case of missing leading zeros
		if (sb.length() < 32) {
			sb.reverse();
			while (sb.length() < 32) {
				sb.append("0");
			}
			sb.reverse();
		}
		return sb.toString();
	}
}