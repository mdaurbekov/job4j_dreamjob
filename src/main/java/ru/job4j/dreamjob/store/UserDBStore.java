package ru.job4j.dreamjob.store;

import net.jcip.annotations.ThreadSafe;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import ru.job4j.dreamjob.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@ThreadSafe
@Repository
public class UserDBStore {
    private static final Logger LOG = LogManager.getLogger(UserDBStore.class.getName());
    private static final String ADD = "INSERT INTO users(name, email) VALUES (?, ?)";
    private static final String FIND_BY_EMAIL_AND_PASSWORD = "SELECT * FROM USERS WHERE email = ? AND password = ?";
    private final BasicDataSource pool;

    public UserDBStore(BasicDataSource pool) {
        this.pool = pool;
    }

    public Optional<User> add(User user) {
        Optional<User> optUser = Optional.empty();
        try (Connection cn = pool.getConnection();
             PreparedStatement ps = cn.prepareStatement(ADD,
                     PreparedStatement.RETURN_GENERATED_KEYS)
        ) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.execute();
            try (ResultSet id = ps.getGeneratedKeys()) {
                if (id.next()) {
                    user.setId(id.getInt(1));
                    optUser = Optional.of(user);
                }
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return optUser;
    }

    public Optional<User> findUserByEmailAndPassword(String email, String password) {
        Optional<User> optUser = Optional.empty();
        try (Connection cn = pool.getConnection();
             PreparedStatement ps = cn.prepareStatement(ADD,
                     PreparedStatement.RETURN_GENERATED_KEYS)
        ) {
            ps.setString(1, email);
            ps.setString(2, password);
            ps.execute();
            try (ResultSet id = ps.getGeneratedKeys()) {
                if (id.next()) {
                    Optional.of(getUser(id));
                }
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return optUser;
    }

    private User getUser(ResultSet id) throws SQLException {
        return new User(id.getInt("id"), id.getString("name"),
                id.getString("email"), id.getString("password"));
    }
}
