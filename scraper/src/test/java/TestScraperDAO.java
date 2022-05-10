
import database.Database;
import database.RecipeDAO;
import database.ScraperDAO;
import factories.RecipeFactory;
import factories.ScraperFactory;
import model.Recipe;
import model.Scraper;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestScraperDAO {

    @Test
    public void testInsert() throws Exception {
        Database db = new Database(false);
        db.connect();

        ScraperDAO dao = new ScraperDAO(db);

        Scraper obj = new Scraper("Test scraper");

        boolean actual = dao.insert(obj);
        boolean expected = true;

        assertEquals(actual, expected, "Insert failed");

        db.rollback();
    }


    @Test
    public void testInserts() throws Exception {

        Database db = new Database(false);
        db.connect();

        ScraperDAO dao = new ScraperDAO(db);

        List<Scraper> list = ScraperFactory.create(100);

        for (Scraper obj : list) {
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

        ScraperDAO dao = new ScraperDAO(db);

        Scraper obj = new Scraper("Test scraper 2");

        boolean actual = dao.insert(obj);

        assertEquals(actual, true, "Insert failed");

        long id = obj.id;

        Scraper expected = dao.find(id);

        assertNotNull(expected, "Find failed");

        db.disconnect();
    }

    @Test
    public void testList() throws Exception {
        Database db = new Database(false);
        db.connect();

        ScraperDAO dao = new ScraperDAO(db);

        Scraper obj = new Scraper("Test scraper 2");

        boolean actual = dao.insert(obj);

        assertEquals(actual, true, "Insert failed");

        List<Scraper> list = dao.list();

        for (Scraper c : list) {
            System.out.println(c);
        }

        actual = list.size() > 0;

        assertEquals(actual, true, "Find failed");

        db.disconnect();
    }
}

