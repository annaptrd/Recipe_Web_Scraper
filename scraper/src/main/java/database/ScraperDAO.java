package database;



import model.Scraper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ScraperDAO {

    private Database database;

    private static final String SQL_INSERT = "INSERT INTO scraper(`description`) VALUES (?)";
    private static final String SQL_FIND_BY_ID = "SELECT * from scraper where ingredient_group_id = ?";
    private static final String SQL_FIND_BY_DESCRIPTION = "SELECT * from scraper where description = ?";
    private static final String SQL_LIST = "SELECT * from scraper order by description";

    public ScraperDAO(Database database) {
        this.database = database;
    }

    public boolean insert(Scraper obj) throws SQLException {
        try (PreparedStatement preparedStatement = database.getConnection().prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, obj.description);

            int i = preparedStatement.executeUpdate();

            if (i <= 0) {
                System.out.println("Insert failed");
                return false;
            } else {
                ResultSet rs = preparedStatement.getGeneratedKeys();

                if (rs.next()) {
                    Long newId = rs.getLong(1);
                    obj.id = newId;
                }

                return true;
            }
        }
    }

    public Scraper find(Long id) throws SQLException {
        try (PreparedStatement preparedStatement = database.getConnection().prepareStatement(SQL_FIND_BY_ID)) {
            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next() == false ) {
                return null;
            }

            Scraper scraper = new Scraper();

            scraper.id = resultSet.getLong("id");
            scraper.description = resultSet.getString("description");
            return scraper;
        }
    }

    public List<Scraper> list() throws SQLException {
        try (PreparedStatement preparedStatement = database.getConnection().prepareStatement(SQL_LIST)) {

            ResultSet resultSet = preparedStatement.executeQuery();

            List<Scraper> list = new ArrayList<>();

            while (resultSet.next()) {
                Scraper obj = new Scraper();

                obj.id = resultSet.getLong("id");
                obj.description = resultSet.getString("description");
                list.add(obj);
            }

            return list;
        }
    }

    public Scraper find(String description) throws SQLException {
        try (PreparedStatement preparedStatement = database.getConnection().prepareStatement(SQL_FIND_BY_DESCRIPTION)) {
            preparedStatement.setString(1, description);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next() == false ) {
                return null;
            }

            Scraper Scraper = new Scraper();

            Scraper.id = resultSet.getLong("id");
            Scraper.description = resultSet.getString("description");
            return Scraper;
        }
    }

    public Scraper findOrInsert(String description) throws SQLException {
        Scraper cat = find(description);

        if (cat == null) {
            cat = new Scraper(description);
            insert(cat);
        }

        return cat;
    }
//


//    public void delete(Long ingredient_group_id) {
//
//    }
//
//    public void update(Scraper obj) {
//
//    }
}
