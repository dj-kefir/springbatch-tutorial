package ru.oz.mytutors.springbatch.mappers;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;
import ru.oz.mytutors.springbatch.model.sem.Price;

/**
 * Created by ozol on 18.03.2016.
 */
public class PriceMapper implements FieldSetMapper<Price> {
    public Price mapFieldSet(FieldSet fieldSet) throws BindException {
        Price price = new Price();
        price.setProductId(fieldSet.readLong("PID"));

        if (!(fieldSet.readString("PRICE").isEmpty()))
            price.setPriceValue(fieldSet.readDouble("PRICE"));

        return price;
    }
}
