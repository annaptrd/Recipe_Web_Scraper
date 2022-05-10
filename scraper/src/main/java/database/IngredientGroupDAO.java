package database;


import model.Ingredient;
import model.IngredientGroup;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class IngredientGroupDAO {

    private Database database;

    private static final String SQL_INSERT = "INSERT INTO ingredient_group(`description`,`recipe_id`) VALUES (?,?)";
    private static final String SQL_FIND_BY_ID = "SELECT * from ingredient_group where ingredient_group_id = ?";
    private static final String SQL_LIST = "SELECT * from ingredient_group order by description";

    public IngredientGroupDAO(Database database) {
        this.database = database;
    }

    public boolean insert(IngredientGroup obj) throws SQLException {
        try (PreparedStatement preparedStatement = database.getConnection().prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, obj.description);
            preparedStatement.setLong(2, obj.recipe_id);

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

    public IngredientGroup find(Long id) throws SQLException {
        try (PreparedStatement preparedStatement = database.getConnection().prepareStatement(SQL_FIND_BY_ID)) {
            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next() == false ) {
                return null;
            }

            IngredientGroup ingredient_group = new IngredientGroup();

            ingredient_group.id = resultSet.getLong("id");
            ingredient_group.description = resultSet.getString("description");
            ingredient_group.recipe_id = resultSet.getLong("recipe_id");
            return ingredient_group;
        }
    }

    public List<IngredientGroup> list() throws SQLException {
        try (PreparedStatement preparedStatement = database.getConnection().prepareStatement(SQL_LIST)) {

            ResultSet resultSet = preparedStatement.executeQuery();

            List<IngredientGroup> list = new ArrayList<>();

            while (resultSet.next()) {
                IngredientGroup obj = new IngredientGroup();

               obj.id = resultSet.getLong("id");
               obj.description = resultSet.getString("description");
               obj.recipe_id = resultSet.getLong("recipe_id");
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
//    public void update(IngredientGroup obj) {
//
//    }
}
