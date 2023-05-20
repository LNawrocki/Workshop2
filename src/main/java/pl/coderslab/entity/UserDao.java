package pl.coderslab.entity;

import org.mindrot.jbcrypt.BCrypt;
import pl.coderslab.DbUtil;

import java.sql.*;

public class UserDao {

    private  static final String CREATE_USER_QUERY = "INSERT INTO users (username, email, password) VALUES (?, ?, ?)";
    private  static final String UPDATE_USER_QUERY = "UPDATE users SET email = ?, usrname = ?, password = ? WHERE id = ?";
    public static final String SELECT_USER_QUERY = "SELECT * FROM users WHERE id = ?";


    public UserDao(){

    }

    public User read(int userId) {
        try(Connection conn = DbUtil.connectWorkshop2() ) {

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User create(User user) {
        try (Connection conn = DbUtil.connectWorkshop2()) {
            PreparedStatement statement = conn.prepareStatement(CREATE_USER_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getUserName());
            statement.setString(2, user.getEmail());
//            statement.setString(3, user.getPassword());
            statement.setString(3, hashPassword(user.getPassword()));
            statement.executeUpdate();
            //Pobieramy wstawiony do bazy identyfikator, a następnie ustawiamy id obiektu user.
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                user.setId(resultSet.getInt(1));
            }
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

}
