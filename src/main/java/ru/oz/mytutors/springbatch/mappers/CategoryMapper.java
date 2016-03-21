package ru.oz.mytutors.springbatch.mappers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;
import ru.oz.mytutors.springbatch.model.sem.Category;

/**
 * Created by ozol on 21.03.2016.
 */
@Slf4j
public class CategoryMapper implements FieldSetMapper<Category> {

    private final static int CAT_ID = 0;
    private final static int URI = 1;
    private final static int CAT_NAME = 4;
    private final static int PARENT = 5;
    private final static int CAT_LINK = 7;
    private final static int SMALL_IMG = 15;

    public Category mapFieldSet(FieldSet fieldSet) throws BindException {
        Category category = new Category();

        category.setCategoryId(fieldSet.readLong(CAT_ID));
        category.setCategoryUri(fieldSet.readLong(URI));
        category.setCategoryName(fieldSet.readString(CAT_NAME));
        category.setSmallPicture(""); //
        category.setCategoryWeight(1); //

        log.info("category {} was read...", category.getCategoryId());
        return category;
    }
}
