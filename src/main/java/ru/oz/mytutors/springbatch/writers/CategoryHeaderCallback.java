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

import static ru.oz.mytutors.springbatch.model.SearchConstants.*;

/**
 * Created by Igor Ozol
 * on 20.03.2016.
 * Eldorado LLC
 */
@Component("categoryHeaderCallback")
@Slf4j
@Data
public class CategoryHeaderCallback implements StaxWriterCallback {

    public void write(XMLEventWriter eventWriter) throws IOException {

        XMLEventFactory factory = XMLEventFactory.newFactory();
        XMLStreamWriter writer = StaxUtils.createEventStreamWriter(eventWriter, factory);

        try {
            writer.writeCharacters("\n");
            writer.writeCharacters("\t");
            writer.writeStartElement("sphinx:schema");
            writer.writeCharacters("\n");
            UtilsWriter.writeSchemaField(writer, CATEGORY_NAME, true);
            UtilsWriter.writeSchemaField(writer, PATH, true);
            UtilsWriter.writeSchemaAttribute(writer, SphinxSearch.BIGINT, CATEGORY_URI);
            UtilsWriter.writeSchemaAttribute(writer, SphinxSearch.BIGINT, CATEGORY_ID);
            UtilsWriter.writeSchemaAttribute(writer, SphinxSearch.BIGINT, CATEGORY_LINK);
            UtilsWriter.writeSchemaAttribute(writer, SphinxSearch.BOOL, FT_SEARCHABLE);
            UtilsWriter.writeSchemaAttribute(writer, SphinxSearch.STRING, PICTURE);
            UtilsWriter.writeSchemaAttribute(writer, SphinxSearch.STRING, SMALL_PICTURE);
            UtilsWriter.writeSchemaAttribute(writer, SphinxSearch.JSON, UNAVAILABLE_BASESTORES);

            writer.writeCharacters("\t");
            writer.writeEndElement();
            writer.writeCharacters("\n\n");
        } catch (XMLStreamException e) {
            log.error("Error while generate sphinx schema XML header", e);
        }
    }
}
