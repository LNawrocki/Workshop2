package pl.coderslab;

import pl.coderslab.entity.User;
import pl.coderslab.entity.UserDao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

            UserDao userDao = new UserDao();
            userDao.create(user1);
            userDao.create(user2);
            userDao.create(user3);

            String PRINT_TABLE_QUERY = "select * from users";
            Statement stmt2 = conn.createStatement();
            ResultSet rs = stmt2.executeQuery(PRINT_TABLE_QUERY);

            while (rs.next()) {
//                String firstName = rs.getString("user_name");
//                int id = 0;
//                String email = "";
//                String username = "";
//                String password = "";
                System.out.print(rs.getInt("id") + " ");
                System.out.print(rs.getString("email") + " ");
                System.out.print(rs.getString("username") + " ");
                System.out.println(rs.getString("password") + " ");
            }

            System.out.println();
            User user5 = userDao.read(2);



            Scanner scan = new Scanner(System.in);
            System.out.println("naciśnij ENETR  żeby zakończyć");
            scan.nextLine();

            String DROP_TABLE_QUERY = "DROP TABLE users";

            Statement stmt3 = conn.createStatement();
            stmt3.executeUpdate(DROP_TABLE_QUERY);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}