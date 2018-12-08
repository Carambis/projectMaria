package server;

import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Authorization {
    private static Connection conn = null;

    public static void authUser(String sql, ObjectOutputStream out) throws UnsupportedEncodingException {
        conn = new Server().getConnection();

        try {
            String[] data =  new String[3];
            Statement s = conn.createStatement();
            ResultSet r = s.executeQuery(sql);
            r.next();
            data[0] = r.getString("cnt");
            data[1] = r.getString("id");
            data[2] = r.getString("role");
            r.close();

            out.writeObject(data);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
