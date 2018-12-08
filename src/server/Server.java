package server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Server implements ServerInterface {
    private static Connection conn = null;
    private static String dbUrl = "jdbc:mysql://localhost:3306/project_home_budget?useSSL=false";
    private static String tableCosts = "costs";
    private static String tableUser = "user";
    private static String tableOutcome = "outcome";
    private static String tablePrice = "price";

    public static void main(String[] args) throws IOException {
        new Server().openConnection();
    }

    public void openConnection() throws IOException {
        ServerSocket serverSocket = null;
        String action, entity = "", sql = "";
        ObjectInputStream in;
        ObjectOutputStream out;
        int port = 8080;

        try {
            serverSocket = new ServerSocket(port);
            System.out.println("server: socket open on port " + port);
        } catch (IOException e) {
            System.out.println(e);
        }

        //getConnection();

        while (true) {
            try {
                Socket clientSocket = serverSocket.accept();
                
                System.out.println("Connection made to " + clientSocket.getInetAddress());
                
                //userLog(clientSocket.getInetAddress().toString());
                
                in = new ObjectInputStream(clientSocket.getInputStream());
                out = new ObjectOutputStream(clientSocket.getOutputStream());

                action = (String) in.readObject();

                System.out.println("Request from client: " + action);

                String[] exploded = action.split("@");
                action = exploded[0];
                entity = exploded[1];
                sql = exploded[2];

                switch (action) {
                    case "get":
                        if (entity.equalsIgnoreCase("outcome")) {
                            DataHandler.getOutcome(out, sql);
                        } else if (entity.equalsIgnoreCase("price")) {
                            DataHandler.getPrice(out, sql);
                        } else if (entity.equalsIgnoreCase("costs")) {
                            DataHandler.getCosts(out, sql);
                        } else {
                            DataHandler.getUsers(out, sql);
                        }

                        break;

                    case "delete":
                    case "update":
                    case "insert":
                        executeSql(sql, out);

                        break;

                    case "auth":
                        Authorization.authUser(sql, out);
                        
                        break;  
   
                    default:
                        break;
                }
            } catch (IOException | ClassNotFoundException e) {
                System.out.println(e);
            }
        }
    }

    /*public void userLog(String clientIp) {
        Logger logger = Logger.getLogger("logs/user_log");
        FileHandler fh;

        try { 
            fh = new FileHandler("logs/user_log.log");
            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
            logger.info("Connected client with ip: " + clientIp);
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
    
    public Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            String connectionUrl = dbUrl;
            String connectionUser = "root";
            String connectionPassword = "root";
            return DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public void executeSql(String sql, ObjectOutputStream out) throws UnsupportedEncodingException {
        conn = getConnection();

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.executeUpdate();

            out.writeObject("Success");
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

    public String getTableName(String name) {
        if (name.equals("user")) {
            return tableUser;
        } else if (name.equals("outcome")) {
            return tableOutcome;
        } else if (name.equals("price")) {
            return tablePrice;
        } else {
            return tableCosts;
        }
    }
}