package server;

import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataHandler {
    private static Connection conn = null;
    private static Statement stmt = null;
    private static ResultSet rs = null;

    public static void getOutcome(ObjectOutputStream out, String where) {
        Server server = new Server();

        conn = server.getConnection();

        int i = 0, rows = 0;

        try {
            stmt = conn.createStatement();

            String sql = "SELECT o.id, DATE_FORMAT(o.date_create, '%d/%m/%Y') date_create, u.login user_login, u.id user_id, p.name price_name, p.price price FROM " + server.getTableName("outcome") + " o " +
                    "INNER JOIN " + server.getTableName("user") + " u ON o.user_id = u.id " +
                    "INNER JOIN " + server.getTableName("price") + " p ON o.price_id = p.id";

            if (!where.equals("no_sql")) {
                sql += where;
            }

            sql += " ORDER BY o.id ASC";

            rs = stmt.executeQuery(sql);

            if (rs.last()) {
                rows = rs.getRow();
                rs.beforeFirst();
            }

            String[][] data = new String[rows][6];

            while (rs.next()) {
                data[i][0] = rs.getString("id");
                data[i][1] = rs.getString("price_name");
                data[i][2] = rs.getString("price");
                data[i][3] = rs.getString("user_login");
                data[i][4] = rs.getString("date_create");
                data[i][5] = rs.getString("user_id");

                i++;
            }

            out.writeObject(data);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }

                if (stmt != null) {
                    stmt.close();
                }

                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void getCosts(ObjectOutputStream out, String where) {
        Server server = new Server();

        conn = server.getConnection();

        int i = 0, rows = 0;

        try {
            stmt = conn.createStatement();

            String sql = "SELECT c.*, u.login user_login FROM " + server.getTableName("costs") + " c INNER JOIN " + server.getTableName("user") + " u ON c.user_id = u.id";

            if (!where.equals("no_sql")) {
                sql += where;
            }

            sql += " ORDER BY c.id ASC";

            rs = stmt.executeQuery(sql);

            if (rs.last()) {
                rows = rs.getRow();
                rs.beforeFirst();
            }

            String[][] data = new String[rows][9];

            while (rs.next()) {
                data[i][0] = rs.getString("id");
                data[i][1] = rs.getString("income");
                data[i][2] = rs.getString("percent");
                data[i][3] = rs.getString("result");
                data[i][4] = rs.getString("user_login");

                i++;
            }

            out.writeObject(data);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }

                if (stmt != null) {
                    stmt.close();
                }

                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void getPrice(ObjectOutputStream out, String where) {
        conn = new Server().getConnection();

        int i = 0, rows = 0;

        try {
            stmt = conn.createStatement();

            String sql = "SELECT * FROM " + new Server().getTableName("price") + " p ORDER BY p.name";

            if (!where.equals("no_sql")) {
                sql += where;
            }

            rs = stmt.executeQuery(sql);

            if (rs.last()) {
                rows = rs.getRow();
                rs.beforeFirst();
            }

            String[][] data = new String[rows][3];

            while (rs.next()) {
                data[i][0] = rs.getString("id");
                data[i][1] = rs.getString("name");
                data[i][2] = rs.getString("price");

                i++;
            }

            out.writeObject(data);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }

                if (stmt != null) {
                    stmt.close();
                }

                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void getUsers(ObjectOutputStream out, String where) {
        conn = new Server().getConnection();

        int i = 0, rows = 0;

        try {
            stmt = conn.createStatement();

            String sql = "SELECT * FROM " + new Server().getTableName("user") + " u";

            if (!where.equals("no_sql")) {
                sql += where;
            }

            sql += " ORDER BY u.id";

            rs = stmt.executeQuery(sql);

            if (rs.last()) {
                rows = rs.getRow();
                rs.beforeFirst();
            }

            String[][] data = new String[rows][3];

            while (rs.next()) {
                data[i][0] = rs.getString("id");
                data[i][1] = rs.getString("login");
                data[i][2] = rs.getString("pass");

                i++;
            }

            out.writeObject(data);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }

                if (stmt != null) {
                    stmt.close();
                }

                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}