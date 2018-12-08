package client;

public class GlobalVariable {
    public static int userRole = 0;
    public static int userId = 0;

    public static int getUserRole(){
        return GlobalVariable.userRole;
    }

    public static void setUserRole(int val){
        GlobalVariable.userRole = val;
    }

    /**
     * @return the userId
     */
    public static int getUserId() {
        return GlobalVariable.userId;
    }

    /**
     * @param aUserId the userId to set
     */
    public static void setUserId(int aUserId) {
        GlobalVariable.userId = aUserId;
    }
}
