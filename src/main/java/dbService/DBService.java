package dbService;

import dbService.dao.UsersDAO;
import dbService.dataSets.UsersDataSet;
import org.h2.jdbcx.JdbcDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBService {
    private final Connection connection;

    public DBService() {
        this.connection = getMysqlConnection();
    }

    public UsersDataSet getUser(long id) throws DBException{
        try {
            return (new UsersDAO(connection).get(id));
        } catch (SQLException e){
            throw new DBException(e);
        }
    }

    public long addUser(String name) throws DBException {
        try {
            connection.setAutoCommit(false);
            UsersDAO usersDAO = new UsersDAO(connection);
            usersDAO.createTable();
            usersDAO.insertUser(name);
            connection.commit();
            return usersDAO.getId(name);
        }catch (SQLException e){
            try {
                connection.rollback();
            }catch (SQLException ignore){
            }
            throw new DBException(e);
        } finally {
            try {
                connection.setAutoCommit(true);
            }catch (SQLException ignore){

            }
        }
    }

    public void cleanUp()throws DBException{
        UsersDAO usersDAO = new UsersDAO(connection);
        try {
            usersDAO.dropTable();
        } catch (SQLException e){
            throw new DBException(e);
        }
    }

    public void printConnectInfo(){
        try {
            System.out.println("DB name: " +connection.getMetaData().getDatabaseProductName() );
            System.out.println("DB version: " +connection.getMetaData().getDatabaseProductVersion() );
            System.out.println("Driver: " +connection.getMetaData().getDriverName());
            System.out.println("Autocommit: " +connection.getMetaData().getDriverName());
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static Connection getMysqlConnection() {
        try {
//            DriverManager.registerDriver((Driver) Class.forName("com.mysql.jdbc.Driver").newInstance());

            StringBuilder url = new StringBuilder();

            url.
                    append("jdbc:mysql://").        //db type
                    append("localhost:").           //host name
                    append("3306/").                //port
                    append("db_example?").          //db name
                    append("user=root&").          //login
                    append("password=root").       //password
                    append("&serverTimezone=UTC"); // Timezone


            System.out.println("URL: " + url + "\n");

            Connection connection = DriverManager.getConnection(url.toString());
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static Connection getH2Connection() {
        try {
            String url = "jdbc:h2:./h2db";
            String name = "tully";
            String pass = "tully";

            JdbcDataSource ds = new JdbcDataSource();
            ds.setURL(url);
            ds.setUser(name);
            ds.setPassword(pass);

            Connection connection = DriverManager.getConnection(url, name, pass);
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}

