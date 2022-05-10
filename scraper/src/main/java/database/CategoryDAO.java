package database;

import model.Category;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {

    private Database database;

    private static final String SQL_INSERT = "INSERT INTO category(`description`) VALUES (?)";
    private static final String SQL_FIND_BY_ID = "SELECT * from category where ingredient_group_id = ?";
    private static final String SQL_FIND_BY_DESCRIPTION = "SELECT * from category where description = ?";
    private static final String SQL_LIST = "SELECT * from category order by description";

    public CategoryDAO(Database database) {
        this.database = database;
    }

    public boolean insert(Category obj) throws SQLException {
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

    public Category find(Long id) throws SQLException {
        try (PreparedStatement preparedStatement = database.getConnection().prepareStatement(SQL_FIND_BY_ID)) {
            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next() == false ) {
                return null;
            }

            Category category = new Category();

            category.id = resultSet.getLong("id");
            category.description = resultSet.getString("description");
            return category;
        }
    }

    public Category find(String description) throws SQLException {
        try (PreparedStatement preparedStatement = database.getConnection().prepareStatement(SQL_FIND_BY_DESCRIPTION)) {
            preparedStatement.setString(1, description);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next() == false ) {
                return null;
            }

            Category category = new Category();

            category.id = resultSet.getLong("id");
            category.description = resultSet.getString("description");
            return category;
        }
    }

    public Category findOrInsert(String description) throws SQLException {
        Category cat = find(description);

        if (cat == null) {
            cat = new Category(description);
            insert(cat);
        }

        return cat;
    }

    public List<Category> list() throws SQLException {
        try (PreparedStatement preparedStatement = database.getConnection().prepareStatement(SQL_LIST)) {

            ResultSet resultSet = preparedStatement.executeQuery();

            List<Category> list = new ArrayList<>();

            while (resultSet.next()) {
                Category obj = new Category();

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
//    public void update(Category obj) {
//
//    }
}
