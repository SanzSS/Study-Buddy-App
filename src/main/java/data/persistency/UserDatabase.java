package data.persistency;

import account_creation.Account;

import java.io.Serializable;
import java.util.HashMap;

public class UserDatabase implements Serializable {
    private static UserDatabase USERDATABASE;
    public HashMap<String, Account> accounts;
    public Account currentUser;

    private UserDatabase() {
    }
    public static UserDatabase getUserDatabase() {
        if (USERDATABASE == null) {
            USERDATABASE = new UserDatabase();
        }
        return USERDATABASE;
    }
    public static HashMap<String, Account> getAccounts() {
        return accounts;
    }

    public Account getCurrentUser() {
        return USERDATABASE.currentUser;
    }
    public static void setCurrentUser(Account currentUser) {
        UserDatabase.currentUser = currentUser;
    }

    public void setAccounts(HashMap<String, Account> accounts) {
        USERDATABASE.accounts = accounts;
    }
}
