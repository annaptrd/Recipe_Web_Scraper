
import database.CategoryDAO;
import database.Database;
import database.NutritionInfoDAO;
import factories.CategoryFactory;
import factories.NutritionInfoFactory;
import model.Category;
import model.NutritionInfo;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestNutritionInfoDAO {

    @Test
    public void testInsert() throws Exception {
        Database db = new Database(false);
        db.connect();

        NutritionInfoDAO dao = new NutritionInfoDAO(db);

        NutritionInfo obj = NutritionInfoFactory.create();

        boolean actual = dao.insert(obj);
        boolean expected = true;

        assertEquals(actual, expected, "Insert failed");

        db.rollback();
    }

    @Test
    public void testInserts() throws Exception {

        Database db = new Database(false);
        db.connect();

        NutritionInfoDAO dao = new NutritionInfoDAO(db);

        List<NutritionInfo> list = NutritionInfoFactory.create(100);

        for (NutritionInfo obj : list) {
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

        NutritionInfoDAO dao = new NutritionInfoDAO(db);

        NutritionInfo obj = NutritionInfoFactory.create();

        boolean actual = dao.insert(obj);

        assertEquals(actual, true, "Insert failed");

        long id = obj.id;

        NutritionInfo expected = dao.find(id);

        assertNotNull(expected, "Find failed");

        db.rollback();
    }

    @Test
    public void testList() throws Exception {
        Database db = new Database(false);
        db.connect();

        NutritionInfoDAO dao = new NutritionInfoDAO(db);

        NutritionInfo obj = NutritionInfoFactory.create();

        boolean actual = dao.insert(obj);

        assertEquals(actual, true, "Insert failed");

        List<NutritionInfo> list = dao.list();

        for (NutritionInfo c : list) {
            System.out.println(c);
        }

        actual = list.size() > 0;

        assertEquals(actual, true, "Find failed");

        db.rollback();
    }
}

