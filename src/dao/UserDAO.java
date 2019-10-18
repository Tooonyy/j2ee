package dao;
 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bean.User;
 
public class UserDAO {
 
    public UserDAO() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
 
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/app?characterEncoding=UTF-8", "root",
                "CENTOStony");
    }
 
    public int getTotal() {
        int total = 0;
        try (Connection c = getConnection(); Statement s = c.createStatement();) {
 
            String sql = "select count(*) from user";
 
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                total = rs.getInt(1);
            }
 
            System.out.println("total:" + total);
 
        } catch (SQLException e) {
 
            e.printStackTrace();
        }
        return total;
    }

    public User get(int id) {
        User user = null;
 
        try (Connection c = getConnection(); Statement s = c.createStatement();) {
 
            String sql = "select * from user where id = " + id;
 
            ResultSet rs = s.executeQuery(sql);
 
            if (rs.next()) {
                user = new User();
                String name = rs.getString(2);
                user.name = name;
                user.id = id;
            }
 
        } catch (SQLException e) {
 
            e.printStackTrace();
        }
        return user;
    }
 
    public List<User> list() {
        return list(0, Short.MAX_VALUE);
    }
 
    public List<User> list(int start, int count) {
        List<User> users = new ArrayList<User>();
 
        String sql = "select * from user order by id desc limit ?,? ";
 
        try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
 
            ps.setInt(1, start);
            ps.setInt(2, count);
 
            ResultSet rs = ps.executeQuery();
 
            while (rs.next()) {
                User user = new User();
                int id = rs.getInt(1);
                String name = rs.getString(2);
                user.id = id;
                user.name = name;
                users.add(user);
            }
        } catch (SQLException e) {
 
            e.printStackTrace();
        }
        return users;
    }
 
}