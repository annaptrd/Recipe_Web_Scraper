import com.github.javafaker.Faker;
import database.CategoryDAO;
import database.Database;
import factories.CategoryFactory;
import model.Category;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestCategoryDAO {
    @Test
    public void testInsert() throws Exception {

        Database db = new Database(false);
        db.connect();

        CategoryDAO dao = new CategoryDAO(db);

        Category obj = CategoryFactory.create();

        boolean actual = dao.insert(obj);
        boolean expected = true;

        assertEquals(actual, expected, "Insert failed");

//        db.commit();

        db.rollback();
    }


    @Test
    public void testInserts() throws Exception {

        Database db = new Database(false);
        db.connect();

        CategoryDAO dao = new CategoryDAO(db);

        List<Category> list = CategoryFactory.create(100);

        for (Category obj : list) {
            boolean actual = dao.insert(obj);
            boolean expected = true;

            assertEquals(actual, expected, "Insert failed");
        }

        db.rollback();
    }

    @Test
    public void testDuplicateException() throws Exception {
        Assertions.assertThrows(SQLIntegrityConstraintViolationException.class, () -> {
            Database db = new Database(false);
            db.connect();

            CategoryDAO dao = new CategoryDAO(db);

            Category obj1 = new Category("blabla");
            Category obj2 = new Category("blabla");

            dao.insert(obj1);
            dao.insert(obj2);

            db.rollback();
        });
    }



    @Test
    public void testFindByID() throws Exception {
        Database db = new Database(false);
        db.connect();

        CategoryDAO dao = new CategoryDAO(db);

        Category obj = CategoryFactory.create();

        boolean actual = dao.insert(obj);

        assertEquals(actual, true, "Insert failed");

        long id = obj.id;

        Category expected = dao.find(id);

        assertNotNull(expected, "Find failed");

        db.rollback();
    }

    @Test
    public void testList() throws Exception {
        Database db = new Database(false);
        db.connect();

        CategoryDAO dao = new CategoryDAO(db);

        Category obj = CategoryFactory.create();

        boolean actual = dao.insert(obj);

        assertEquals(actual, true, "Insert failed");

        List<Category> list = dao.list();

        for (Category c : list) {
            System.out.println(c);
        }

        actual = list.size() > 0;

        assertEquals(actual, true, "Find failed");

        db.disconnect();
    }
}

