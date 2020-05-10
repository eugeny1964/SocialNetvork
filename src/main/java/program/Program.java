package program;

import dataBase.DataBase;
import model.Message;
import model.User;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class Program {
    public static void main(String[] args) throws SQLException {
        DataBase db = new DataBase();
        db.open();
        List<User> allUsers = db.getAllUsers();
        System.out.println(allUsers);
        //  db.updateUser(2, new User("Fedor", 100D));
      //  db.addMessage(3, new Message("some text new", new Date()));
        List<Integer> list = db.getGroupOfUser(3);
        System.out.println(list);
        List<User> list1 = db.getAllUserInGroup(2);
        System.out.println(list1);
        //     db.addGroup("groupa","philately");
        //   db.addDateToUser_Group(1,3);
     //   db.addUser("Семенов",37);
        db.close();
    }
}
