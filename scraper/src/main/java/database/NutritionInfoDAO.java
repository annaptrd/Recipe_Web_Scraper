package database;

import model.Category;
import model.NutritionInfo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class NutritionInfoDAO {

    private Database database;

    private static final String SQL_INSERT = "INSERT INTO nutrition_info(`calories`,`carbohydrates`,`protein`,`fat`,`saturated_fat`,`sugars`) VALUES (?,?,?,?,?,?)";
    private static final String SQL_FIND_BY_ID = "SELECT * from nutrition_info where ingredient_group_id = ?";
    private static final String SQL_LIST = "SELECT * from nutrition_info order by ingredient_group_id";

    public NutritionInfoDAO(Database database) {
        this.database = database;
    }

    public boolean insert(NutritionInfo obj) throws SQLException {
        try (PreparedStatement preparedStatement = database.getConnection().prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS)) {
//            preparedStatement.setString(1, obj.kcal,obj.carbohydrates,obj.protein,obj.fat,obj.saturated_fat,obj.sugars);
            preparedStatement.setFloat(1, obj.kcal);
            preparedStatement.setFloat(2, obj.carbohydrates);
            preparedStatement.setFloat(3, obj.protein);
            preparedStatement.setFloat(4, obj.fat);
            preparedStatement.setFloat(5, obj.saturated_fat);
            preparedStatement.setFloat(6, obj.sugars);

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

    public NutritionInfo find(Long id) throws SQLException {
        try (PreparedStatement preparedStatement = database.getConnection().prepareStatement(SQL_FIND_BY_ID)) {
            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next() == false ) {
                return null;
            }

            NutritionInfo nutrition_info = new NutritionInfo();

            nutrition_info.id = resultSet.getLong("id");
            nutrition_info.protein = resultSet.getFloat("protein");
            nutrition_info.fat = resultSet.getFloat("fats");
            nutrition_info.carbohydrates = resultSet.getFloat("carbohydrates");
            nutrition_info.sugars = resultSet.getFloat("sugars");
            nutrition_info.kcal = resultSet.getFloat("kcal");
            nutrition_info.saturated_fat = resultSet.getFloat("saturated_fat");
            return nutrition_info;
        }
    }

    public List<NutritionInfo> list() throws SQLException {
        try (PreparedStatement preparedStatement = database.getConnection().prepareStatement(SQL_LIST)) {

            ResultSet resultSet = preparedStatement.executeQuery();

            List<NutritionInfo> list = new ArrayList<>();

            while (resultSet.next()) {
                NutritionInfo obj = new NutritionInfo();

                obj.id = resultSet.getLong("id");
                obj.protein = resultSet.getFloat("protein");
                obj.fat = resultSet.getFloat("fats");
                obj.carbohydrates = resultSet.getFloat("carbohydrates");
                obj.sugars = resultSet.getFloat("sugars");
                obj.kcal = resultSet.getFloat("kcal");
                obj.saturated_fat = resultSet.getFloat("saturated_fat");
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
//    public void update(NutritionInfo obj) {
//
//    }
}
