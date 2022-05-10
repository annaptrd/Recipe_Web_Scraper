
import database.Database;
import database.StepDAO;
import factories.StepFactory;
import model.Step;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestStepDAO {

    @Test
    public void testInsert() throws Exception {
            Database db = new Database(false);
            db.connect();

            StepDAO dao = new StepDAO(db);

            Step obj = new Step("Test step");

            boolean actual = dao.insert(obj);
            boolean expected = true;

            assertEquals(actual, expected, "Insert failed");

            db.rollback();
    }
    
    @Test
    public void testInserts() throws Exception {

        Database db = new Database(false);
        db.connect();

        StepDAO dao = new StepDAO(db);

        List<Step> list = StepFactory.create(100);

        for (Step obj : list) {
            boolean actual = dao.insert(obj);
            boolean expected = true;

            assertEquals(actual, expected, "Insert failed");
        }

        db.rollback();
    }

    @Test
    public void testFindByID() throws Exception{
            Database db = new Database(false);
            db.connect();

            StepDAO dao = new StepDAO(db);

            Step obj = new Step("Test step 2");

            boolean actual = dao.insert(obj);

            assertEquals(actual, true, "Insert failed");

            long id = obj.id;

            Step expected = dao.find(id);

            assertNotNull(expected, "Find failed");

            db.rollback();
    }

    @Test
    public void testList() throws Exception{
            Database db = new Database(false);
            db.connect();

            StepDAO dao = new StepDAO(db);

            Step obj = new Step("Test step 2");

            boolean actual = dao.insert(obj);

            assertEquals(actual, true, "Insert failed");

            List<Step> list = dao.list();

            for (Step c : list) {
                System.out.println(c);
            }

            actual = list.size() > 0;

            assertEquals(actual, true, "Find failed");

            db.rollback();
    }
}

