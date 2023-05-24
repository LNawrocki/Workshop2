package pl.coderslab;

import pl.coderslab.entity.User;
import pl.coderslab.entity.UserDao;

import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        try(Connection conn =  DbUtil.getConnection();) {
            String CREATE_TABLE_QUERY = "CREATE table users (\n" +
                    "    id int(11) not null auto_increment, primary key(id),\n" +
                    "    email varchar(255) not null UNIQUE ,\n" +
                    "    username varchar(255) not null,\n" +
                    "    password varchar(60) not null\n" +
                    ")";

            Statement stmt1 = conn.createStatement();
            stmt1.executeUpdate(CREATE_TABLE_QUERY);

            UserDao userDao = new UserDao();

            userDao.printAll();
            System.out.println();

            User user1 = new User();
            user1.setEmail("111@111.pl");
            user1.setUserName("user1");
            user1.setPassword("pass1");

            User user2 = new User();
            user2.setEmail("222@222.pl");
            user2.setUserName("user2");
            user2.setPassword("pass2");

            User user3 = new User();
            user3.setEmail("333@333.pl");
            user3.setUserName("user3");
            user3.setPassword("pass3");

            userDao.create(user1);
            userDao.create(user2);
            userDao.create(user3);

            userDao.printAll();
            System.out.println();

            UserDao userDao1 = new UserDao();
            User user5 = userDao1.read(2);

//            User user5 = new User();
            user5.setEmail("123@123.pl");
            user5.setUserName("user123");
            user5.setPassword("pass123");

            userDao.update(user5);

            userDao.printAll();
            System.out.println();

            User userRead = userDao.read(4);
            System.out.println(userRead);

            userDao.delete(3);
            System.out.println();


            User[] tabOfUser = userDao.printAll();
            for (User user : tabOfUser) {
                System.out.println(user.getId() + " "
                        + user.getEmail() + " "
                        + user.getUserName() + " "
                        + user.getPassword());
            }




//            Scanner scan = new Scanner(System.in);
//            System.out.println("naciśnij ENETR  żeby zakończyć");
//            scan.nextLine();

            String DROP_TABLE_QUERY = "DROP TABLE users";

            Statement stmt3 = conn.createStatement();
            stmt3.executeUpdate(DROP_TABLE_QUERY);

        } catch (SQLException e) {
            e.printStackTrace();
        }



    }

}