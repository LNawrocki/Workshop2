package pl.coderslab.entity;
import org.mindrot.jbcrypt.BCrypt;
import pl.coderslab.DbUtil;
import java.sql.*;
import java.util.Arrays;

public class UserDao {

    private  static final String CREATE_USER_QUERY = "INSERT INTO users (username, email, password) VALUES (?, ?, ?)";
    private  static final String UPDATE_USER_QUERY = "UPDATE users SET email = ?, userName = ?, password = ? WHERE id = ?";
    public static final String READ_USER_QUERY = "SELECT * FROM users WHERE id = ?";
    public static final String SELECT_USER_QUERY = "SELECT * FROM users";
    public static final String DELETE_USER_QUERY = "DELETE FROM users WHERE id = ?";


    public User[] printAll() {

        User[] tempUsers = new  User[0];
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(SELECT_USER_QUERY);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setEmail(resultSet.getString("email"));
                user.setUserName(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));

                tempUsers = Arrays.copyOf(tempUsers, tempUsers.length + 1);
                tempUsers[tempUsers.length-1] = user;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tempUsers;
    }

    public void delete(int userId) {
        try(Connection conn = DbUtil.getConnection() ) {
            PreparedStatement statement = conn.prepareStatement(DELETE_USER_QUERY);
            statement.setInt(1, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(User user) {
        try(Connection conn = DbUtil.getConnection() ) {
            PreparedStatement statement = conn.prepareStatement(UPDATE_USER_QUERY);
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getUserName());
            statement.setString(3, hashPassword(user.getPassword()));
            statement.setInt(4, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User read(int userId) {
        try(Connection conn = DbUtil.getConnection() ) {
            PreparedStatement statement = conn.prepareStatement(READ_USER_QUERY);
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setEmail(resultSet.getString("email"));
                user.setUserName(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                return user;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User create(User user) {
        try (Connection conn = DbUtil.getConnection()) {
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
