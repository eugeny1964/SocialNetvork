package dataBase;

import model.Message;
import model.User;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class DataBase {
    private Connection connection;

    public void open() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:db2");
            System.out.println("Connected");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<User>();
        String query = "select * from user";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                User u = new User();
                u.setId(resultSet.getInt("id"));
                u.setFio(resultSet.getString("fio"));
                u.setAge(resultSet.getDouble("age"));
                users.add(u);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return users;
    }

    public void updateUser(Integer id, User a) {
        String query = "update user set fio=?, age =? where id=" + id;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, a.getFio());
            preparedStatement.setDouble(2, a.getAge());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public boolean addMessage(Integer id, Message message) {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        String query = "insert into message(text,date, id_user) values (?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, message.getText());
            preparedStatement.setString(2, format.format(message.getData()));
            preparedStatement.setInt(3, id);
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
        return true;
    }

    public void close() {
        try {
            this.connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<Integer> getGroupOfUser(Integer id) throws SQLException {
        String qyery = "SELECT groupa.id, groupa.name FROM user, user_group, groupa WHERE user.id=" + id +
                " AND user_group.id_user=user.id AND groupa.id=user_group.id_groupa;";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(qyery);
        List<Integer> list = new ArrayList<Integer>();
        while (resultSet.next()) {
            Integer f = resultSet.getInt("id");
            list.add(f);

        }
        return list;
    }

    public List<User> getAllUserInGroup(Integer id) throws SQLException {
        String qyery = "select user.id, user.fio, user.age from user,groupa,user_group where groupa.id=" + id +
                " and user_group.id_groupa=groupa.id and user.id=user_group.id_user;";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(qyery);
        List<User> list = new ArrayList<User>();
        while (resultSet.next()) {
            User user = new User();
            user.setId(resultSet.getInt("id"));
            user.setFio(resultSet.getString("fio"));
            user.setAge(resultSet.getDouble("age"));
            list.add(user);
        }
        return list;
    }

    public void addGroup(String nameTable, String rowName) throws SQLException {
        String qyery="INSERT INTO groupa(name) VALUES('philately');";
        Statement statement=connection.createStatement();
        statement.executeUpdate(qyery);
        System.out.println();
    }

    public void addDateToUser_Group(int i, int i1) throws SQLException {
        String qyery="INSERT INTO user_group(id_user, id_groupa) VALUES("+i+","+i1+");";
        Statement statement=connection.createStatement();
        statement.executeUpdate(qyery);
    }

    public void addUser(String s, int i) throws SQLException {
        String qyery="INSERT into user(fio, age) VALUES('Семенов', 40);";
        Statement statement=connection.createStatement();
        statement.executeUpdate(qyery);
    }
}
