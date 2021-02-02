package lk.ijse.dep.web.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MontisoriPool {

    private List<Connection> pool = new ArrayList<>();
    private List<Connection> consumerPool = new ArrayList<>();
    private int initialSize =  5;

    static{
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public MontisoriPool(){
        try {
            obtainConnections();
        } catch (SQLException t) {
            throw new RuntimeException(t);
        }
    }

    public MontisoriPool(int initialSize) {
        this.initialSize = initialSize;
        try {
            obtainConnections();
        } catch (SQLException t) {
            throw new RuntimeException(t);
        }
    }

    private void obtainConnections() throws SQLException {
        for (int i = 0; i < initialSize; i++) {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dep6_pos", "root", "mysql");
            pool.add(connection);
        }
    }

    public synchronized Connection getConnection(){
        while (pool.isEmpty()){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Connection connection = pool.get(0);
        pool.remove(connection);
        consumerPool.add(connection);
        return connection;
    }

    public synchronized void releaseConnection(Connection connection){
        consumerPool.remove(connection);
        pool.add(connection);
        notify();
    }

    public synchronized void releaseAllConnections(){
        for (Connection connection : consumerPool) {
            pool.add(connection);
        }
        consumerPool.clear();
        notifyAll();
    }

}
