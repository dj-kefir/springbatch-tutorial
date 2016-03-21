package ru.oz.mytutors.springbatch.writers;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.xml.StaxWriterCallback;
import org.springframework.stereotype.Component;
import org.springframework.util.xml.StaxUtils;

import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.IOException;
import java.util.Map;

import static ru.oz.mytutors.springbatch.SearchConstants.*;

/**
 * Created by Igor Ozol
 * on 19.03.2016.
 * Eldorado LLC
 */

//https://books.google.ru/books?id=1QGyKvGu1t4C&pg=PA315&lpg=PA315&dq=StaxWriterCallback+example&source=bl&ots=TJ_oxw8wuy&sig=HZzV4FaaskfxMeVMtMZvgOAcSzs&hl=ru&sa=X&ved=0ahUKEwiBrrvqzc3LAhVrMJoKHdwcB50Q6AEIHTAA#v=onepage&q=StaxWriterCallback%20example&f=false

@Component("priceCallback")
@Slf4j
@Data
public class PriceHeaderCallback implements StaxWriterCallback {

    private Map<String, String> attributeNamesToTypes;

    /**
     * use Stax cursor Api
     * @param writer
     * @throws IOException
     */
    public void write(XMLEventWriter eventWriter) throws IOException {

        XMLEventFactory factory = XMLEventFactory.newFactory();
        XMLStreamWriter writer = StaxUtils.createEventStreamWriter(eventWriter, factory);

        try {
            writer.writeCharacters("\n");
            writer.writeCharacters("\t");
            writer.writeStartElement("sphinx:schema");
            writer.writeCharacters("\n");
            UtilsWriter.writeSchemaField(writer, PRODUCT_ID, false);
            UtilsWriter.writeSchemaAttribute(writer, SphinxSearch.BIGINT, PRODUCT_ID);
            UtilsWriter.writeSchemaAttribute(writer, SphinxSearch.FLOAT, PRICE);
            UtilsWriter.writeSchemaAttribute(writer, SphinxSearch.FLOAT, OLD_PRICE);
            UtilsWriter.writeSchemaAttribute(writer, SphinxSearch.BIGINT, BESTSELLER);
            UtilsWriter.writeSchemaAttribute(writer, SphinxSearch.BOOL, PICKUP_TODAY);
            UtilsWriter.writeSchemaAttribute(writer, SphinxSearch.TIMESTAMP, DELIVERY_DATE);
            UtilsWriter.writeSchemaAttribute(writer, SphinxSearch.TIMESTAMP, NEXT_DELIVERY_DATE);
            writer.writeCharacters("\t");
            writer.writeEndElement();
            writer.writeCharacters("\n\n");
        } catch (XMLStreamException e) {
            log.error("Error while generate sphinx schema XML header", e);
        }

    }
}
