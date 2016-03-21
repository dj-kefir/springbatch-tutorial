package ru.oz.mytutors.springbatch.writers;

import lombok.Data;
import org.springframework.batch.item.ItemWriter;
import ru.oz.mytutors.springbatch.model.CategoryFileType;
import ru.oz.mytutors.springbatch.model.sem.Category;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Igor Ozol
 * on 21.03.2016.
 * Eldorado LLC
 */
@Data
public class ProductCategoryWriter implements ItemWriter<Category> {

    private CategoryFileType fileType = CategoryFileType.UNKNOWN; //see product-data.xml

    @Resource(name = "idToCategoryMap")
    private Map<Long, Category> idToCategoryMap;

    @Override
    public void write(List<? extends Category> items) throws Exception {

        Map<Long, Category> newIdToCategoryMap = new HashMap<>();
        switch (fileType) {
            case CATEGORY_LINK:

                for (Category newCategory : items) {
                    if (idToCategoryMap.containsKey(newCategory.getCategoryId())) {
                        Category existingCategory = idToCategoryMap.get(newCategory.getCategoryId());
                        existingCategory.setCategoryUri(newCategory.getCategoryUri());
                        existingCategory.setCategoryLink(newCategory.getCategoryLink());
                    } else {
                        newIdToCategoryMap.put(newCategory.getCategoryId(), newCategory);
                    }
                }

                idToCategoryMap.putAll(newIdToCategoryMap);
                break;
            case CATEGORY_NAME:

                for (Category newCategory : items) {
                    if (idToCategoryMap.containsKey(newCategory.getCategoryId())) {
                        Category existingCategory = idToCategoryMap.get(newCategory.getCategoryId());
                        existingCategory.setCategoryName(newCategory.getCategoryName());
                    } else {
                        newIdToCategoryMap.put(newCategory.getCategoryId(), newCategory);
                    }
                }

                idToCategoryMap.putAll(newIdToCategoryMap);
                break;
            default:
                throw new RuntimeException("Category upload file not specified");
        }

    }
}
