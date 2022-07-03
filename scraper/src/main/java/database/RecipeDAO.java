package database;

import model.Difficulty;
import model.Recipe;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RecipeDAO {

    private Database database;

    private static final String SQL_INSERT = "INSERT INTO recipe(`uri`,`photo_url`,`title`,`description`,`preparation_time`,`cooking_time`,`servings`,`scraped_at`,`writer`,`difficulty_id`,`scraper_id`,`nutrition_info_id`,`number_of_ingredients`,`category_id`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    private static final String SQL_FIND_BY_ID = "SELECT * from recipe where ingredient_group_id = ?";
    private static final String SQL_LIST = "SELECT * from recipe order by title";

    public RecipeDAO(Database database) {
        this.database = database;
    }

    public boolean insert(Recipe obj) throws SQLException {
        try (PreparedStatement preparedStatement = database.getConnection().prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, obj.uri);
            preparedStatement.setString(2, obj.photo_url);
            preparedStatement.setString(3, obj.title);
            preparedStatement.setString(4, obj.description);
            preparedStatement.setInt(5, obj.preparation_time);
            preparedStatement.setInt(6, obj.cooking_time);
            preparedStatement.setString(7, obj.servings);
            preparedStatement.setDate(8, new java.sql.Date(obj.scraped_at.getTime()));
            preparedStatement.setString(9, obj.writer);
            preparedStatement.setLong(10, obj.difficulty.id);
            preparedStatement.setLong(11, obj.scraper.id);
            preparedStatement.setLong(12, obj.nutritionInfo.id);
            preparedStatement.setInt(13, obj.number_of_ingredients);
            preparedStatement.setLong(14, obj.category.id);

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

    public Recipe find(Long id) throws SQLException {
        DifficultyDAO ddao = new DifficultyDAO(database);
        ScraperDAO sdao = new ScraperDAO(database);
        CategoryDAO cdao = new CategoryDAO(database);
        NutritionInfoDAO ndao = new NutritionInfoDAO(database);


        try (PreparedStatement preparedStatement = database.getConnection().prepareStatement(SQL_FIND_BY_ID)) {
            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next() == false ) {
                return null;
            }

            Recipe recipe = new Recipe();

            recipe.id = resultSet.getLong("id");
            recipe.photo_url = resultSet.getString("photo_url");
            recipe.description = resultSet.getString("description");
            recipe.title = resultSet.getString("title");
            recipe.uri = resultSet.getString("uri");
            recipe.preparation_time = resultSet.getInt("preparation_time");
            recipe.cooking_time = resultSet.getInt("cooking_time");
            recipe.servings = resultSet.getString("servings");
            recipe.writer = resultSet.getString("writer");
            recipe.difficulty = ddao.find(resultSet.getLong("difficulty_id"));
            recipe.scraper = sdao.find(resultSet.getLong("scraper_id"));
            recipe.nutritionInfo = ndao.find(resultSet.getLong("nutrition_info_id"));
            recipe.number_of_ingredients = resultSet.getInt("number_of_ingredients");
            recipe.category = cdao.find(resultSet.getLong("category_id"));
            return recipe;
        }
    }

    public List<Recipe> list() throws SQLException {
        ScraperDAO sdao = new ScraperDAO(database);
        CategoryDAO cdao = new CategoryDAO(database);
        NutritionInfoDAO ndao = new NutritionInfoDAO(database);

        try (PreparedStatement preparedStatement = database.getConnection().prepareStatement(SQL_LIST)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            List<Recipe> list = new ArrayList<>();

            while (resultSet.next()) {
                Recipe obj = new Recipe();

                obj.id = resultSet.getLong("id");
                obj.photo_url = resultSet.getString("photo_url");
                obj.description = resultSet.getString("description");
                obj.title = resultSet.getString("title");
                obj.uri = resultSet.getString("uri");
                obj.preparation_time = resultSet.getInt("preparation_time");
                obj.cooking_time = resultSet.getInt("cooking_time");
                obj.servings = resultSet.getString("servings");
                obj.writer = resultSet.getString("writer");
                obj.difficulty = new Difficulty(resultSet.getLong("difficulty_id"));
                obj.scraper = sdao.find(resultSet.getLong("scraper_id"));
                obj.nutritionInfo = ndao.find(resultSet.getLong("nutrition_info_id"));
                obj.number_of_ingredients = resultSet.getInt("number_of_ingredients");
                obj.category = cdao.find(resultSet.getLong("category_id"));
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
//    public void update(Recipe obj) {
//
//    }
}
