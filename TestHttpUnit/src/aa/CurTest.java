package aa;

public class CurTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int result = 2<<3;

		System.out.println(result);
	}

	static String func(String s) {
		return s.length() > 0 ? func(s.substring(1)) + s.charAt(0) : "";
	}
}
