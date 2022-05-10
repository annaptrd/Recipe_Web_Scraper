
import database.Database;
import database.DifficultyDAO;
import database.IngredientDAO;

import factories.DifficultyFactory;
import factories.IngredientFactory;
import model.Difficulty;
import model.Ingredient;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestIngredientDAO {

    @Test
    public void testInsert() throws Exception {
        Database db = new Database(false);
        db.connect();

        IngredientDAO dao = new IngredientDAO(db);

        Ingredient obj = IngredientFactory.create();

        boolean actual = dao.insert(obj);
        boolean expected = true;

        assertEquals(actual, expected, "Insert failed");

        db.rollback();
    }

    @Test
    public void testInserts() throws Exception {

        Database db = new Database(false);
        db.connect();

        IngredientDAO dao = new IngredientDAO(db);

        List<Ingredient> list = IngredientFactory.create(100);

        for (Ingredient obj : list) {
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

        IngredientDAO dao = new IngredientDAO(db);

        Ingredient obj = IngredientFactory.create();

        boolean actual = dao.insert(obj);

        assertEquals(actual, true, "Insert failed");

        long id = obj.id;

        Ingredient expected = dao.find(id);

        assertNotNull(expected, "Find failed");

        db.rollback();
    }

    @Test
    public void testList() throws Exception {
        Database db = new Database(false);
        db.connect();

        IngredientDAO dao = new IngredientDAO(db);

        Ingredient obj = IngredientFactory.create();

        boolean actual = dao.insert(obj);

        assertEquals(actual, true, "Insert failed");

        List<Ingredient> list = dao.list();

        for (Ingredient c : list) {
            System.out.println(c);
        }

        actual = list.size() > 0;

        assertEquals(actual, true, "Find failed");

        db.rollback();
    }
}

