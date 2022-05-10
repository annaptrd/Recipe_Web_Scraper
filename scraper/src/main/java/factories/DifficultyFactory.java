package factories;

import model.Difficulty;

import java.util.ArrayList;
import java.util.List;

public class DifficultyFactory extends BaseFactory {
    public static Difficulty create() {
        return new Difficulty(faker.lorem().sentence());
    }

    public static List<Difficulty> create(int n) {
        List<Difficulty> list = new ArrayList<>();

        for (int i=0;i<n;i++) {
            list.add(create());
        }

        return list;
    }
}
