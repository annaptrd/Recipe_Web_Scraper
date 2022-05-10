
import database.Database;
import database.IngredientGroupHasIngredientDAO;
import model.IngredientGroupHasIngredient;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestIngredientGroupHasIngredientDAO {
//
//    @Test
//    public void testInsert() throws SQLException {
//        Database db = new Database(false);
//        db.connect();
//
//        IngredientGroupHasIngredientDAO dao = new IngredientGroupHasIngredientDAO(db);
//
//        IngredientGroupHasIngredient obj = new IngredientGroupHasIngredient("Test ingr group has ingr");
//
//        boolean actual = dao.insert(obj);
//        boolean expected = true;
//
//        assertEquals(actual, expected, "Insert failed");
//
//        db.rollback();
//    }
//
//
//    @Test
//    public void testFindByID() throws Exception {
//            Database db = new Database(false);
//            db.connect();
//
//            IngredientGroupHasIngredientDAO dao = new IngredientGroupHasIngredientDAO(db);
//
//            IngredientGroupHasIngredient obj = new IngredientGroupHasIngredient("Test ingr group has ingr 2");
//
//            boolean actual = dao.insert(obj);
//
//            assertEquals(actual, true, "Insert failed");
//
//            long id = obj.ingredient_group_id;
//
//            IngredientGroupHasIngredient expected = dao.find(id);
//
//            assertNotNull(expected, "Find failed");
//
//            db.disconnect();
//    }
//
//    @Test
//    public void testList() throws Exception {
//            Database db = new Database(false);
//            db.connect();
//
//            IngredientGroupHasIngredientDAO dao = new IngredientGroupHasIngredientDAO(db);
//
//            IngredientGroupHasIngredient obj = new IngredientGroupHasIngredient("Test ingr group has ingr 2");
//
//            boolean actual = dao.insert(obj);
//
//            assertEquals(actual, true, "Insert failed");
//
//            List<IngredientGroupHasIngredient> list = dao.list();
//
//            for (IngredientGroupHasIngredient c : list) {
//                System.out.println(c);
//            }
//
//            actual = list.size() > 0;
//
//            assertEquals(actual, true, "Find failed");
//
//            db.disconnect();
//    }
}

