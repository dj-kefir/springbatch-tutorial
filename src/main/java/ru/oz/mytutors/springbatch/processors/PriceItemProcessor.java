package ru.oz.mytutors.springbatch.processors;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;
import ru.oz.mytutors.springbatch.model.sem.Price;

/**
 * Created by ozol on 18.03.2016.
 */
@Slf4j
@Component
public class PriceItemProcessor implements ItemProcessor<Price, Price> {
    public Price process(Price price) throws Exception {
        log.info("price {} converted...", price.getPriceId());
        return price;
    }
}
