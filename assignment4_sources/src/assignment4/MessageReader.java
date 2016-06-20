package assignment4;

/**
 * @author Annika Baldi
 * @author Milan Proell
 */

public class MessageReader {

    public static void main(String[] args) {
        System.out.println("Message Reader");
        System.out.println(findMessage("XHAYLLYIOWZIEZLT", "QQHALKKLOWJJELNT") + " = HALLOWELT");
        System.out.println(findMessage("FFOOOOBBAARR", "FOOBAR") + " = FOOBAR");
        System.out.println(findMessage("BANANENEIS", "ANANAS") + " = ANANS");
    }

    public static String findMessage(String a, String b) {
        char[] stra = a.toCharArray();
        char[] strb = b.toCharArray();
        String message = "";
        for (int i = 0; i < Math.max(stra.length, strb.length); i++) {
            for (int j = i; j < Math.max(stra.length, strb.length); j++) {
                if (j < stra.length && i < strb.length && stra[j] == strb[i]) {
                    message = message + stra[j];
                    stra[j] = '-';
                    break;
                }
                if (j < strb.length && i < stra.length && stra[i] == strb[j]) {
                    message = message + strb[j];
                    strb[j] = '-';
                    break;

                }
            }
        }

        return message;
    }
}
