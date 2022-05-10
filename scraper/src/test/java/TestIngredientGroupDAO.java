import database.Database;
import database.IngredientDAO;
import database.IngredientGroupDAO;
import factories.IngredientFactory;
import model.Ingredient;
import model.IngredientGroup;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestIngredientGroupDAO {

    @Test
    public void testInsert() throws Exception {
        Database db = new Database(false);
        db.connect();

        IngredientGroupDAO dao = new IngredientGroupDAO(db);

        IngredientGroup obj = new IngredientGroup("Test ingredient group");

        boolean actual = dao.insert(obj);
        boolean expected = true;

        assertEquals(actual, expected, "Insert failed");

        db.rollback();
    }

    @Test
    public void testFindByID() throws Exception {
        Database db = new Database(false);
        db.connect();

        IngredientGroupDAO dao = new IngredientGroupDAO(db);

        IngredientGroup obj = new IngredientGroup("Test ingredient group 2");

        boolean actual = dao.insert(obj);

        assertEquals(actual, true, "Insert failed");

//            long ingredient_group_id = obj.ingredient_group_id;

        //           IngredientGroup expected = dao.find(ingredient_group_id);

//            assertNotNull(expected, "Find failed");

        db.disconnect();
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
    public void testList() throws Exception {
        Database db = new Database(false);
        db.connect();

        IngredientGroupDAO dao = new IngredientGroupDAO(db);

        IngredientGroup obj = new IngredientGroup("Test ingredient group 2");

        boolean actual = dao.insert(obj);

        assertEquals(actual, true, "Insert failed");

        List<IngredientGroup> list = dao.list();

        for (IngredientGroup c : list) {
            System.out.println(c);
        }

        actual = list.size() > 0;

        assertEquals(actual, true, "Find failed");

        db.disconnect();
    }
}

