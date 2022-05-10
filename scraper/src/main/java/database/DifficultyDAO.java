package database;


import model.Difficulty;
import model.Scraper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DifficultyDAO {

    private Database database;

    private static final String SQL_INSERT = "INSERT INTO difficulty(`description`) VALUES (?)";
    private static final String SQL_FIND_BY_ID = "SELECT * from difficulty where ingredient_group_id = ?";
    private static final String SQL_FIND_BY_DESCRIPTION = "SELECT * from difficulty where description = ?";
    private static final String SQL_LIST = "SELECT * from difficulty order by description";

    public DifficultyDAO(Database database) {
        this.database = database;
    }

    public boolean insert(Difficulty obj) throws SQLException {
        try (PreparedStatement preparedStatement = database.getConnection().prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, obj.description);

            int i = preparedStatement.executeUpdate();

            if (i <= 0) {
                System.out.println("Insert failed");
                return false;
            } else {
                ResultSet rs = preparedStatement.getGeneratedKeys();

                if (rs.next()) {
                    Long newId = rs.getLong(1);
                    obj.id = newId;
                }

                return true;
            }
        }
    }

    public Difficulty find(Long id) throws SQLException {
        try (PreparedStatement preparedStatement = database.getConnection().prepareStatement(SQL_FIND_BY_ID)) {
            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next() == false ) {
                return null;
            }

            Difficulty difficulty = new Difficulty();

            difficulty.id = resultSet.getLong("id");
            difficulty.description = resultSet.getString("description");
            return difficulty;
        }
    }

    public Difficulty find(String description) throws SQLException {
        try (PreparedStatement preparedStatement = database.getConnection().prepareStatement(SQL_FIND_BY_DESCRIPTION)) {
            preparedStatement.setString(1, description);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next() == false ) {
                return null;
            }

            Difficulty Difficulty = new Difficulty();

            Difficulty.id = resultSet.getLong("id");
            Difficulty.description = resultSet.getString("description");
            return Difficulty;
        }
    }

    public Difficulty findOrInsert(String description) throws SQLException {
        Difficulty cat = find(description);

        if (cat == null) {
            cat = new Difficulty(description);
            insert(cat);
        }

        return cat;
    }
    

    public List<Difficulty> list() throws SQLException {
        try (PreparedStatement preparedStatement = database.getConnection().prepareStatement(SQL_LIST)) {

            ResultSet resultSet = preparedStatement.executeQuery();

            List<Difficulty> list = new ArrayList<>();

            while (resultSet.next()) {
                Difficulty obj = new Difficulty();

                obj.id = resultSet.getLong("id");
                obj.description = resultSet.getString("description");
                list.add(obj);
            }

            return list;
        }
    }
//


//    public void delete(Long ingredient_group_id) {
//
//    }
//
//    public void update(Difficulty obj) {
//
//    }
}
