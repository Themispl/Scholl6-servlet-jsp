package gr.aueb.cf.schoolapp.security;

import org.mindrot.jbcrypt.BCrypt;

public class SecUtil {

    private SecUtil() {}

    public static String hashPassword(String inputpassword) {
        int workload = 12;
        String salt = BCrypt.gensalt(workload);
        return BCrypt.hashpw(inputpassword, salt);
    }

    public static boolean checkPassword(String inputpassword, String storedhashpassword) {
        return BCrypt.checkpw(inputpassword, storedhashpassword);
    }
}

