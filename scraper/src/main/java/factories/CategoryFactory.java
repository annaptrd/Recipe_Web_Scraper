package factories;

import model.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryFactory extends BaseFactory {
    public static Category create() {
        return new Category(faker.lorem().sentence());
    }

    public static List<Category> create(int n) {
        List<Category> list = new ArrayList<>();

        for (int i=0;i<n;i++) {
            list.add(create());
        }

        return list;
    }
}
