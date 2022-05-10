package database;



import model.IngredientGroupHasIngredient;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class IngredientGroupHasIngredientDAO {

    private Database database;

    private static final String SQL_INSERT = "INSERT INTO ingredient_group_has_ingredient(`ingredient_group_id`, `ingredient_id`) VALUES (?,?)";
    private static final String SQL_FIND_BY_ID = "SELECT * from ingredient_group_has_ingredient where ingredient_group_id = ?";
    private static final String SQL_LIST = "SELECT * from ingredient_group_has_ingredient order by description";

    public IngredientGroupHasIngredientDAO(Database database) {
        this.database = database;
    }

    public boolean insert(IngredientGroupHasIngredient obj) throws SQLException {
        try (PreparedStatement preparedStatement = database.getConnection().prepareStatement(SQL_INSERT)) {
            preparedStatement.setLong(1, obj.ingredient_group_id);
            preparedStatement.setLong(2, obj.ingredient_id);

            int i = preparedStatement.executeUpdate();

            if (i <= 0) {
                System.out.println("Insert failed");
                return false;
            } else {
                return true;
            }
        }
    }

    public IngredientGroupHasIngredient find(Long id) throws SQLException {
        try (PreparedStatement preparedStatement = database.getConnection().prepareStatement(SQL_FIND_BY_ID)) {
            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next() == false ) {
                return null;
            }

            IngredientGroupHasIngredient ingredient_group_has_ingredient = new IngredientGroupHasIngredient();

            ingredient_group_has_ingredient.ingredient_group_id = resultSet.getLong("ingredient_group_id");
            ingredient_group_has_ingredient.ingredient_id = resultSet.getLong("ingredient_id");

            return ingredient_group_has_ingredient;
        }
    }

    public List<IngredientGroupHasIngredient> list() throws SQLException {
        try (PreparedStatement preparedStatement = database.getConnection().prepareStatement(SQL_LIST)) {

            ResultSet resultSet = preparedStatement.executeQuery();

            List<IngredientGroupHasIngredient> list = new ArrayList<>();

            while (resultSet.next()) {
                IngredientGroupHasIngredient obj = new IngredientGroupHasIngredient();

                obj.ingredient_group_id = resultSet.getLong("ingredient_group_id");
                obj.ingredient_id = resultSet.getLong("ingredient_id");
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
//    public void update(Step obj) {
//
//    }
}
