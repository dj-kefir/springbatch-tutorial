package ru.oz.mytutors.springbatch.writers;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;
import ru.oz.mytutors.springbatch.model.upload.Brand;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Igor Ozol
 * on 21.03.2016.
 * Eldorado LLC
 */
@Component("brandWriter")
public class ProductBrandWriter implements ItemWriter<Brand> {

    @Resource(name = "productBrands")
    private List<Brand> brands;

    public void write(List<? extends Brand> items) throws Exception {
        brands.addAll(items);
    }
}
