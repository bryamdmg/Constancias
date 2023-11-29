package uv.mx.fei.logic.domain;

public class SessionDetails {
    private static SessionDetails instance;
    private final String username;
    private final String userType;
    private final int id;

    public static SessionDetails getInstance(String username, String userType, int id) {
        if (instance == null) {
            instance = new SessionDetails(username, userType, id);
        }
        return instance;
    }

    public static SessionDetails getInstance() {
        return instance;
    }

    private SessionDetails(String username, String userType, int id) {
        this.username = username;
        this.userType = userType;
        this.id = id;
    }

    public static void cleanSessionDetails() {
        instance = null;
    }

    public String getUsername() {
        return username;
    }

    public String getUserType() {
        return userType;
    }

    public int getId() {
        return id;
    }
}
