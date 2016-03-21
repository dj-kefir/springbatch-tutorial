package ru.oz.mytutors.springbatch.mappers.product;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;
import ru.oz.mytutors.springbatch.model.upload.Brand;

/**
 * Created by Igor Ozol
 * on 21.03.2016.
 * Eldorado LLC
 */
public class BrandMapper implements FieldSetMapper<Brand> {

    public Brand mapFieldSet(FieldSet fieldSet) throws BindException {

        Brand brand = new Brand();
        brand.setId(fieldSet.readLong("brand_id"));
        brand.setName(fieldSet.readString("brand_name"));

        return brand;
    }
}
