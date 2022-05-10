import database.CategoryDAO;
import database.Database;
import database.NutritionInfoDAO;
import database.RecipeDAO;
import factories.NutritionInfoFactory;
import factories.RecipeFactory;
import model.Category;
import model.NutritionInfo;
import model.Recipe;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestRecipeDAO {

    @Test
    public void testInsert() throws Exception {
            Database db = new Database(false);
            db.connect();

            RecipeDAO dao = new RecipeDAO(db);
            CategoryDAO cdao = new CategoryDAO(db);

            Recipe obj = RecipeFactory.create();

            cdao.insert(obj.category);

            boolean actual = dao.insert(obj);
            boolean expected = true;

            assertEquals(actual, expected, "Insert failed");

            db.rollback();
    }

    @Test
    public void testInserts() throws Exception {

        Database db = new Database(false);
        db.connect();

        RecipeDAO dao = new RecipeDAO(db);

        List<Recipe> list = RecipeFactory.create(100);

        for (Recipe obj : list) {
            boolean actual = dao.insert(obj);
            boolean expected = true;

            assertEquals(actual, expected, "Insert failed");
        }

        db.rollback();
    }

    @Test
    public void testFindByID() throws Exception {
            Database db = new Database(false);
            db.connect();

            RecipeDAO dao = new RecipeDAO(db);

            Recipe obj =  RecipeFactory.create();

            boolean actual = dao.insert(obj);

            assertEquals(actual, true, "Insert failed");

            long id = obj.id;

            Recipe expected = dao.find(id);

            assertNotNull(expected, "Find failed");

            db.disconnect();
    }

    @Test
    public void testList() throws Exception{
            Database db = new Database(false);
            db.connect();

            RecipeDAO dao = new RecipeDAO(db);

            Recipe obj =  RecipeFactory.create();

            boolean actual = dao.insert(obj);

            assertEquals(actual, true, "Insert failed");

            List<Recipe> list = dao.list();

            for (Recipe c : list) {
                System.out.println(c);
            }

            actual = list.size() > 0;

            assertEquals(actual, true, "Find failed");

            db.disconnect();
    }
}

