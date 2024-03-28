package helper;

import java.sql.*;

public class DBHelper {
    private static DBHelper dbHelper;
    public static DBHelper getInstance(){
        if (dbHelper == null){
            dbHelper = new DBHelper();
        }
        return dbHelper;
    }
    private DBHelper(){

    }
    private Connection cnx;
    private PreparedStatement pstmt;
    private void openConnection() throws SQLException {
        try {
            if (cnx == null || cnx.isClosed()) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                String url="jdbc:mysql://localhost:3306/cours_java";
                cnx= DriverManager.getConnection(url,"root","");
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new SQLException(e);
        }
    }

    private void myPreparedQuery(String sql) throws SQLException {
        try {
            if (sql.toUpperCase().trim().startsWith("INSERT") ) {
                pstmt = cnx.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            }else {
                pstmt = cnx.prepareStatement(sql);
            }
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    public int getGeneratedKeys() throws SQLException {
        ResultSet rs = pstmt.getGeneratedKeys();

        if (rs.next()){
            int id = rs.getInt(1);
            rs.close();
            return id;
        }
        return -1;
    }

    public ResultSet executeSelect(String sql, Object[] parameters) throws SQLException {

        try {
            openConnection();
            myPreparedQuery(sql);
            if (parameters != null) {
                for(int i = 0; i < parameters.length; i++) {
                    pstmt.setObject((i+1), parameters[i]);
                }
            }
            return pstmt.executeQuery();
        } catch (SQLException e) {
            throw new SQLException(e);
        }


    }

    public int executeMaj(String sql, Object[] parameters) throws SQLException {

        try {
            openConnection();
            myPreparedQuery(sql);
            if (parameters != null) {
                for(int i = 0; i < parameters.length; i++) {
                    pstmt.setObject((i+1), parameters[i]);
                }
            }
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    public void closeConnection() throws SQLException {
        if (pstmt != null && !pstmt.isClosed()){
            pstmt.close();
            pstmt = null;
        }
        if (cnx != null && !cnx.isClosed()){
            cnx.close();
            cnx = null;
        }
    }

    public void beginTransaction() throws SQLException {
        cnx.setAutoCommit(false);
    }
    public void endTransaction() throws SQLException {
        cnx.setAutoCommit(true);
    }
    public void validateTransaction() throws SQLException {
        cnx.commit();
    }
    public void rollbackTransaction() throws SQLException {
        cnx.rollback();
    }
}
