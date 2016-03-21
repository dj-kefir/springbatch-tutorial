package ru.oz.mytutors.springbatch;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import ru.oz.mytutors.springbatch.model.SearchConstants;
import ru.oz.mytutors.springbatch.model.sem.Category;
import ru.oz.mytutors.springbatch.writers.UtilsWriter;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ozol on 21.03.2016.
 */
@Component("categoryTasklet")
public class CategoryTasklet implements Tasklet {

    private final static int CAT_ID = 0;
    private final static int URI = 1;
    private final static int CAT_NAME = 4;
    private final static int PARENT = 5;
    private final static int CAT_LINK = 7;
    private final static int SMALL_IMG = 15;

    @Value("classpath:data/sem/vr_cat_small.csv")
    private Resource sourceCsvFile;

    @Value("file:data/outs/category.xml")
    private Resource outputSphinxXmlFile;

    private static void writeCategorySchema(XMLStreamWriter writer) throws XMLStreamException, FileNotFoundException {
        writer.writeStartElement("sphinx:schema");
        writer.writeCharacters("\n");
        UtilsWriter.writeSchemaField(writer, SearchConstants.CATEGORY_NAME, true);
        UtilsWriter.writeSchemaField(writer, SearchConstants.PATH, true);
        UtilsWriter.writeSchemaAttribute(writer, SearchConstants.SphinxSearch.BIGINT, SearchConstants.CATEGORY_URI);
        UtilsWriter.writeSchemaAttribute(writer, SearchConstants.SphinxSearch.BIGINT, SearchConstants.CATEGORY_ID);
        UtilsWriter.writeSchemaAttribute(writer, SearchConstants.SphinxSearch.BIGINT, SearchConstants.CATEGORY_LINK);
        UtilsWriter.writeSchemaAttribute(writer, SearchConstants.SphinxSearch.BOOL, SearchConstants.FT_SEARCHABLE);
        UtilsWriter.writeSchemaAttribute(writer, SearchConstants.SphinxSearch.STRING, SearchConstants.PICTURE);
        UtilsWriter.writeSchemaAttribute(writer, SearchConstants.SphinxSearch.STRING, SearchConstants.SMALL_PICTURE);
        UtilsWriter.writeSchemaAttribute(writer, SearchConstants.SphinxSearch.JSON, SearchConstants.UNAVAILABLE_BASESTORES);
        UtilsWriter.writeSchemaAttribute(writer, SearchConstants.SphinxSearch.FLOAT, SearchConstants.CATEGORY_WEIGHT);

        writer.writeCharacters("\t");
        writer.writeEndElement();
        writer.writeCharacters("\n\n");
    }

    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {

        Map<Long, String> parents = new HashMap();
        Map<Long, Category> idToCategoryMap = new HashMap();

        BufferedReader reader = new BufferedReader(new InputStreamReader(sourceCsvFile.getInputStream()));
        String line = null;
        while ((line = reader.readLine()) != null) {
            String[] fields = line.split(";");
            if (!"\uFEFFID".equals(fields[CAT_ID])) {

                Category category = new Category();
                category.setCategoryUri(Long.parseLong(fields[URI]));
                category.setCategoryId(Long.parseLong(fields[CAT_ID]));
                category.setCategoryName(fields[CAT_NAME]);
                category.setPath(fields[PARENT]);
                category.setPicture("");
                category.setCategoryWeight(1);
                category.setUnavailableBaseStores(Collections.EMPTY_LIST);

                if (fields.length > SMALL_IMG) {
                    category.setSmallPicture(fields[SMALL_IMG]);
                } else {
                    category.setSmallPicture("");
                }

                if (fields.length > CAT_LINK && !StringUtils.isEmpty(fields[CAT_LINK])) {
                    category.setCategoryLink(Long.parseLong(fields[CAT_LINK]));
                }

                String path = fields[PARENT];
                parents.put(category.getCategoryId(), path);
                idToCategoryMap.put(category.getCategoryId(), category);
            }
        }

        //CREATE PATH
        for (Long categoryId : parents.keySet()) {
            for (Map.Entry<Long, String> entry : parents.entrySet()) {
                String path = entry.getValue();
                if (path.startsWith(categoryId.toString())) {
                    parents.put(entry.getKey(), parents.get(categoryId) + "/" + path);
                }
            }
        }

        for (Long categoryId : parents.keySet()) {
            if (parents.get(categoryId).startsWith("/")) {
                parents.put(categoryId, parents.get(categoryId).replaceFirst("/", "0/"));
            } else if (StringUtils.isEmpty(parents.get(categoryId))) {
                parents.put(categoryId, "0");
            }
        }

        for (Long categoryId : idToCategoryMap.keySet()) {
            idToCategoryMap.get(categoryId).setPath(parents.get(categoryId));
        }

        saveCategoriesToSphinxXmlFile(idToCategoryMap);

        return RepeatStatus.FINISHED;
    }

    private void saveCategoriesToSphinxXmlFile(Map<Long, Category> idToCategoryMap) throws Exception {
        XMLStreamWriter writer = UtilsWriter.getXmlStreamWriter(outputSphinxXmlFile.getFile().getPath());

        UtilsWriter.writeSphinxDocumentHeader(writer);
        writeCategorySchema(writer);
        for (Category category : idToCategoryMap.values()) {
            writeCategoryDocument(writer, category);
        }

        UtilsWriter.closeWriter(writer);
    }

    private void writeCategoryDocument(XMLStreamWriter writer, Category category) throws XMLStreamException, JsonProcessingException {
        writer.writeCharacters("\t");
        writer.writeStartElement("sphinx:document");
        writer.writeAttribute(SearchConstants.ID, String.valueOf(category.getCategoryId()));
        writer.writeCharacters("\n\t");
        UtilsWriter.writeTagLine(writer, SearchConstants.CATEGORY_URI, String.valueOf(category.getCategoryUri()));
        UtilsWriter.writeTagLine(writer, SearchConstants.CATEGORY_ID, String.valueOf(category.getCategoryId()));
        UtilsWriter.writeTagLine(writer, SearchConstants.CATEGORY_LINK, String.valueOf(category.getCategoryLink()));
        UtilsWriter.writeTagLine(writer, SearchConstants.FT_SEARCHABLE, String.valueOf(category.isFtSearchable()));
        UtilsWriter.writeTagLine(writer, SearchConstants.CATEGORY_NAME, category.getCategoryName());
        UtilsWriter.writeTagLine(writer, SearchConstants.PICTURE, category.getPicture());
        UtilsWriter.writeTagLine(writer, SearchConstants.SMALL_PICTURE, category.getSmallPicture());
        UtilsWriter.writeTagLine(writer, SearchConstants.PATH, category.getPath());
        UtilsWriter.writeTagLine(writer, SearchConstants.UNAVAILABLE_BASESTORES, UtilsWriter.getJsonString(category.getUnavailableBaseStores()));    //MVA
        UtilsWriter.writeTagLine(writer, SearchConstants.CATEGORY_WEIGHT, String.valueOf(category.getCategoryWeight()));
        writer.writeEndElement();
        writer.writeCharacters("\n\n");
    }
}
