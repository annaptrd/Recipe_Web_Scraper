package database;



import model.Step;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StepDAO {

    private Database database;

    private static final String SQL_INSERT = "INSERT INTO step(`description`,`order`,`recipe_id`) VALUES (?,?,?)";
    private static final String SQL_FIND_BY_ID = "SELECT * from step where ingredient_group_id = ?";
    private static final String SQL_LIST = "SELECT * from step order by description";

    public StepDAO(Database database) {
        this.database = database;
    }

    public boolean insert(Step obj) throws SQLException {
        try (PreparedStatement preparedStatement = database.getConnection().prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, obj.text);
            preparedStatement.setInt(2, obj.order);
            preparedStatement.setLong(3, obj.recipe_id);

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

    public Step find(Long id) throws SQLException {
        try (PreparedStatement preparedStatement = database.getConnection().prepareStatement(SQL_FIND_BY_ID)) {
            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next() == false ) {
                return null;
            }

            Step step = new Step();

            step.id = resultSet.getLong("id");
            step.text = resultSet.getString("description");
            step.order = resultSet.getInt("order");
            step.recipe_id = resultSet.getInt("recipe_id");
            return step;
        }
    }

    public List<Step> list() throws SQLException {
        try (PreparedStatement preparedStatement = database.getConnection().prepareStatement(SQL_LIST)) {

            ResultSet resultSet = preparedStatement.executeQuery();

            List<Step> list = new ArrayList<>();

            while (resultSet.next()) {
                Step obj = new Step();

                obj.id = resultSet.getLong("id");
                obj.text = resultSet.getString("description");
                obj.order = resultSet.getInt("order");
                obj.recipe_id = resultSet.getInt("recipe_id");
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
