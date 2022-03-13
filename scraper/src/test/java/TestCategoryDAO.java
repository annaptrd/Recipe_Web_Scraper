import database.CategoryDAO;
import database.Database;
import model.Category;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestCategoryDAO {

    @Test
    public void testInsert() {
        try {
            Database db = new Database();
            db.connect();

            CategoryDAO dao = new CategoryDAO(db);

            Category obj = new Category("Test category");

            boolean actual = dao.insert(obj);
            boolean expected = true;

            assertEquals(actual, expected, "Insert failed");

            db.disconnect();
        } catch (Exception ex) {
            ex.printStackTrace();

        }
    }

    @Test
    public void testFindByID() {
        try {
            Database db = new Database();
            db.connect();

            CategoryDAO dao = new CategoryDAO(db);

            Category obj = new Category("Test category 2");

            boolean actual = dao.insert(obj);

            assertEquals(actual, true, "Insert failed");

            long id = obj.id;

            Category expected = dao.find(id);

            assertNotNull(expected, "Find failed");

            db.disconnect();
        } catch (Exception ex) {
            ex.printStackTrace();

        }
    }

    @Test
    public void testList() {
        try {
            Database db = new Database();
            db.connect();

            CategoryDAO dao = new CategoryDAO(db);

            Category obj = new Category("Test category 2");

            boolean actual = dao.insert(obj);

            assertEquals(actual, true, "Insert failed");

            List<Category> list = dao.list();

            for (Category c : list) {
                System.out.println(c);
            }

            actual = list.size() > 0;

            assertEquals(actual, true, "Find failed");

            db.disconnect();
        } catch (Exception ex) {
            ex.printStackTrace();

        }
    }
}

