package ru.oz.mytutors.springbatch.mappers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;
import ru.oz.mytutors.springbatch.BatchConstants;
import ru.oz.mytutors.springbatch.model.sem.Price;

/**
 * Created by ozol on 18.03.2016.
 */
@Slf4j
public class PriceMapper implements FieldSetMapper<Price> {

    public Price mapFieldSet(FieldSet fieldSet) throws BindException {
        Price price = new Price();
        price.setProductId(fieldSet.readLong(BatchConstants.PRICES.PRODUCT_ID));
        price.setPriceValue(fieldSet.readDouble(BatchConstants.PRICES.PRICE_VALUE));

        log.info("price {} was read...", price.getProductId());

        return price;
    }
}
