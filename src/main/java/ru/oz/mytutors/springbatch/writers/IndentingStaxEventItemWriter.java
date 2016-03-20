package ru.oz.mytutors.springbatch.writers;

import javanet.staxutils.IndentingXMLEventWriter;
import org.springframework.batch.item.xml.StaxEventItemWriter;

import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import java.io.Writer;

/**
 * Created by Igor Ozol
 * on 19.03.2016.
 * Eldorado LLC
 */
public class IndentingStaxEventItemWriter<T> extends StaxEventItemWriter<T> {

    @Override
    protected XMLEventWriter createXmlEventWriter(XMLOutputFactory outputFactory, Writer writer) throws XMLStreamException {
        return new IndentingXMLEventWriter(super.createXmlEventWriter(outputFactory, writer));
    }
}
