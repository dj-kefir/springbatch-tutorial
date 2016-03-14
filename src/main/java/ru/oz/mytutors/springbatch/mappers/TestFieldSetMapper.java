package ru.oz.mytutors.springbatch.mappers;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;
import ru.oz.mytutors.springbatch.model.Student;

/**
 * Created by ozol on 15.03.2016.
 */
public class TestFieldSetMapper implements FieldSetMapper<Student> {

    public Student mapFieldSet(FieldSet fieldSet) throws BindException {
        Student student = new Student();
//        NAME;EMAIL_ADDRESS;PACKAGE
        student.setName(fieldSet.readRawString("NAME"));
        student.setEmailAddress(fieldSet.readString("EMAIL_ADDRESS"));
        student.setPurchasedPackage(fieldSet.readString("PACKAGE"));

        return student;
    }
}
