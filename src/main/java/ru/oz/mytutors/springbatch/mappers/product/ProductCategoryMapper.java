package ru.oz.mytutors.springbatch.mappers.product;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;
import ru.oz.mytutors.springbatch.model.CategoryFileType;
import ru.oz.mytutors.springbatch.model.sem.Category;

/**
 * Created by Igor Ozol
 * on 21.03.2016.
 * Eldorado LLC
 */
@Slf4j
@Data
public class ProductCategoryMapper implements FieldSetMapper<Category> {

    private CategoryFileType fileType = CategoryFileType.UNKNOWN; // see product-data.xml

    @Override
    public Category mapFieldSet(FieldSet fieldSet) throws BindException {

        Category category = new Category();
        switch (fileType) {
            case CATEGORY_LINK:
                category.setId(fieldSet.readLong("category_id"));
                category.setCategoryUri(fieldSet.readLong("category_uri"));
                category.setCategoryLink(fieldSet.readLong("category_link"));
                break;
            case CATEGORY_NAME:
                category.setId(fieldSet.readLong(0));
                category.setCategoryName(fieldSet.readString(1));
                break;
            default:
                throw new RuntimeException("Category upload file not specified");
        }
        return category;
    }

}
