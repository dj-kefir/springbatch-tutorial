package ru.oz.mytutors.springbatch.writers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by Bychkova on 22.12.2015.
 */
public class UtilsWriter {

    protected static final String UTF_8 = "UTF-8";
    protected static final XMLOutputFactory factory = XMLOutputFactory.newInstance();

    protected static XMLStreamWriter getXmlStreamWriter(String pathToFile) throws IOException, XMLStreamException {
        File file = new File(pathToFile);
        file.getParentFile().mkdirs();
        if(!file.exists()) {
            file.createNewFile();
        }
        final FileOutputStream outputStream = new FileOutputStream(pathToFile, false);
        final BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
        return factory.createXMLStreamWriter(bufferedOutputStream, UTF_8);
    }

    protected static void writeSphinxDocumentHeader(XMLStreamWriter writer) throws XMLStreamException {
        writer.writeStartDocument(UTF_8, "1.1");
        writer.writeCharacters("\n");
        writer.writeStartElement("sphinx:docset");
        writer.writeCharacters("\n\n");
    }

    protected static void writeSchemaField(XMLStreamWriter writer, String name, Boolean isAttributeAlso) throws XMLStreamException {
        writer.writeCharacters("\t\t");
        writer.writeEmptyElement("sphinx:field");
        writer.writeAttribute("name", name);
        if (isAttributeAlso) {
            writer.writeAttribute("attr", "string");
        }
        writer.writeCharacters("\n");
    }

    protected static void writeSchemaAttribute(XMLStreamWriter writer, String type, String name) throws XMLStreamException {
        writer.writeCharacters("\t\t");
        writer.writeEmptyElement("sphinx:attr");
        writer.writeAttribute("name", name);
        writer.writeAttribute("type", type);
        writer.writeCharacters("\n");
    }

    protected static String getJsonString(List<String> stringList) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(stringList);
    }

    protected static void writeTagLine(XMLStreamWriter writer, String tagName, String tagContent) throws XMLStreamException {
        tagContent = tagContent == null ? "" : tagContent;
        if ("true".equalsIgnoreCase(tagContent)) {
            tagContent = "1";
        } else if ("false".equalsIgnoreCase(tagContent)) {
            tagContent = "0";
        }
        writer.writeCharacters("\t");
        writer.writeStartElement(tagName);
        writer.writeCharacters(tagContent);
        writer.writeEndElement();
        writer.writeCharacters("\n\t");
    }

    protected static void closeWriter(XMLStreamWriter writer) throws XMLStreamException {
        writer.writeEndElement();
        writer.writeEndDocument();
        writer.flush();
        writer.close();
    }
}
