import database.CategoryDAO;
import database.DifficultyDAO;
import database.Database;
import database.DifficultyDAO;
import factories.CategoryFactory;
import factories.DifficultyFactory;
import model.Category;
import model.Difficulty;
import model.Difficulty;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestDifficultyDAO {

    @Test
    public void testInsert() throws Exception {
        Database db = new Database(false);
        db.connect();

        DifficultyDAO dao = new DifficultyDAO(db);

        Difficulty obj = new Difficulty("Test difficulty");

        boolean actual = dao.insert(obj);
        boolean expected = true;

        assertEquals(actual, expected, "Insert failed");

        db.rollback();
    }

    @Test
    public void testInserts() throws Exception {

        Database db = new Database(false);
        db.connect();

        DifficultyDAO dao = new DifficultyDAO(db);

        List<Difficulty> list = DifficultyFactory.create(100);

        for (Difficulty obj : list) {
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

        DifficultyDAO dao = new DifficultyDAO(db);

        Difficulty obj = new Difficulty("Test difficulty 2");

        boolean actual = dao.insert(obj);

        assertEquals(actual, true, "Insert failed");

        long id = obj.id;

        Difficulty expected = dao.find(id);

        assertNotNull(expected, "Find failed");

        db.rollback();
    }

    @Test
    public void testList() throws Exception {
        Database db = new Database(false);
        db.connect();

        DifficultyDAO dao = new DifficultyDAO(db);

        Difficulty obj = new Difficulty("Test difficulty 2");

        boolean actual = dao.insert(obj);

        assertEquals(actual, true, "Insert failed");

        List<Difficulty> list = dao.list();

        for (Difficulty c : list) {
            System.out.println(c);
        }

        actual = list.size() > 0;

        assertEquals(actual, true, "Find failed");

        db.rollback();
    }
}

