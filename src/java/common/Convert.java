package common;

public class Convert {

	public static String codeToString(String str) {
		String s = str;
		try {
			byte tempB[] = s.getBytes("ISO-8859-1");
			s = new String(tempB);
			return s;
		} catch (Exception e) {
			return s;
		}
	}
     

}
