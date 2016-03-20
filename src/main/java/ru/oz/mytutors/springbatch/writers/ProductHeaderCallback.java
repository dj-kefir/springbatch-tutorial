package ru.oz.mytutors.springbatch.writers;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.xml.StaxWriterCallback;
import org.springframework.stereotype.Component;
import org.springframework.util.xml.StaxUtils;
import ru.oz.mytutors.springbatch.model.SearchConstants;

import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.IOException;

import static ru.oz.mytutors.springbatch.model.SearchConstants.*;

/**
 * Created by Igor Ozol
 * on 20.03.2016.
 * Eldorado LLC
 */
@Component("productCallback")
@Slf4j
@Data
public class ProductHeaderCallback implements StaxWriterCallback {

    public void write(XMLEventWriter eventWriter) throws IOException {
        XMLEventFactory factory = XMLEventFactory.newFactory();
        XMLStreamWriter writer = StaxUtils.createEventStreamWriter(eventWriter, factory);

        try {
            writer.writeStartElement("sphinx:schema");
            writer.writeCharacters("\n");
            UtilsWriter.writeSchemaField(writer, FULL_NAME, true);
            UtilsWriter.writeSchemaField(writer, CATEGORY_NAME, true);
            UtilsWriter.writeSchemaField(writer, BRAND_NAME, true);
            UtilsWriter.writeSchemaField(writer, DESCRIPTION, true);
            UtilsWriter.writeSchemaField(writer, PROPERTY_AGGREGATE, true);
            UtilsWriter.writeSchemaAttribute(writer, SphinxSearch.STRING, NAME);
            UtilsWriter.writeSchemaAttribute(writer, SphinxSearch.BIGINT, PRODUCT_ID);
            UtilsWriter.writeSchemaAttribute(writer, SphinxSearch.JSON, REGION_PICKUP_AVAILABLE);
            UtilsWriter.writeSchemaAttribute(writer, SphinxSearch.JSON, REGION_DELIVERY_AVAILABLE);
            UtilsWriter.writeSchemaAttribute(writer, SphinxSearch.BIGINT, CATEGORY_ID);
            UtilsWriter.writeSchemaAttribute(writer, SphinxSearch.JSON, CATEGORIES);
            UtilsWriter.writeSchemaAttribute(writer, SphinxSearch.BIGINT, BRAND_ID);
            UtilsWriter.writeSchemaAttribute(writer, SphinxSearch.JSON, PROPERTIES);
            UtilsWriter.writeSchemaAttribute(writer, SphinxSearch.JSON, UNAVAILABLE_BASESTORES);
            UtilsWriter.writeSchemaAttribute(writer, SphinxSearch.JSON, FILTERS);
            UtilsWriter.writeSchemaAttribute(writer, SphinxSearch.BIGINT, RATING);
            UtilsWriter.writeSchemaAttribute(writer, SphinxSearch.STRING, IMAGE_URL);
            UtilsWriter.writeSchemaAttribute(writer, SphinxSearch.FLOAT, PRODUCT_WEIGHT);
            UtilsWriter.writeSchemaAttribute(writer, SphinxSearch.FLOAT, TOTAL_WEIGHT);

            writer.writeCharacters("\t");
            writer.writeEndElement();
            writer.writeCharacters("\n\n");
        } catch (XMLStreamException e) {
            log.error("Error while generate sphinx schema XML header", e);
        }
    }
}
