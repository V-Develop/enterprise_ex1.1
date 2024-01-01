public class InputUtility {

    public static boolean isNullOrEmptyOrBlank(String str) {
        return str == null || str.trim().isEmpty();
    }

    public static boolean isNumeric(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
