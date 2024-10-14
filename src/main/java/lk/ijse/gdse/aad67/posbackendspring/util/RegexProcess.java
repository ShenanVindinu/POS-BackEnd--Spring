package lk.ijse.gdse.aad67.posbackendspring.util;
import java.util.regex.Pattern;

public class RegexProcess {
    public static boolean cusIdMatcher(String noteId) {
        String regexForUserID = "^CUS-[a-f0-9]{8}-[a-f0-9]{4}-[a-f0-9]{4}-[a-f0-9]{4}-[a-f0-9]{12}$";
        Pattern regexPattern = Pattern.compile(regexForUserID);
        return regexPattern.matcher(noteId).matches();
    }
    public static boolean itemIdMatcher(String userId) {
        String regexForUserID = "^ITEM-[a-f0-9]{8}-[a-f0-9]{4}-[a-f0-9]{4}-[a-f0-9]{4}-[a-f0-9]{12}$";
        Pattern regexPattern = Pattern.compile(regexForUserID);
        return regexPattern.matcher(userId).matches();
    }

}
