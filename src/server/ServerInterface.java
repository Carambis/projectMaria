package server;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;

public interface ServerInterface {
    Connection getConnection();
    void openConnection() throws IOException;
    void executeSql(String sql, ObjectOutputStream out) throws UnsupportedEncodingException;
    String getTableName(String name);
}
