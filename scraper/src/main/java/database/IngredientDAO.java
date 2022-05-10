package database;


import model.Ingredient;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class IngredientDAO {

    private Database database;

    private static final String SQL_INSERT = "INSERT INTO ingredient(`description`) VALUES (?)";
    private static final String SQL_FIND_BY_ID = "SELECT * from ingredient where ingredient_group_id = ?";
    private static final String SQL_FIND_BY_DESCRIPTION = "SELECT * from ingredient where description = ?";
    private static final String SQL_LIST = "SELECT * from ingredient order by description";

    public IngredientDAO(Database database) {
        this.database = database;
    }

    public boolean insert(Ingredient obj) throws SQLException {
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

    public Ingredient find(String description) throws SQLException {
        try (PreparedStatement preparedStatement = database.getConnection().prepareStatement(SQL_FIND_BY_DESCRIPTION)) {
            preparedStatement.setString(1, description);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next() == false ) {
                return null;
            }

            Ingredient ingredient = new Ingredient();

            ingredient.id = resultSet.getLong("id");
            ingredient.description = resultSet.getString("description");

            return ingredient;
        }
    }

    public Ingredient findOrInsert(String description) throws SQLException {
        Ingredient cat = find(description);

        if (cat == null) {
            cat = new Ingredient(description);
            insert(cat);
        }

        return cat;
    }

    public Ingredient find(Long id) throws SQLException {
        try (PreparedStatement preparedStatement = database.getConnection().prepareStatement(SQL_FIND_BY_ID)) {
            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next() == false ) {
                return null;
            }

            Ingredient ingredient = new Ingredient();

            ingredient.id = resultSet.getLong("id");
            ingredient.description = resultSet.getString("description");
            return ingredient;
        }
    }

    public List<Ingredient> list() throws SQLException {
        try (PreparedStatement preparedStatement = database.getConnection().prepareStatement(SQL_LIST)) {

            ResultSet resultSet = preparedStatement.executeQuery();

            List<Ingredient> list = new ArrayList<>();

            while (resultSet.next()) {
                Ingredient obj = new Ingredient();

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
//    public void update(Ingredient obj) {
//
//    }
}
