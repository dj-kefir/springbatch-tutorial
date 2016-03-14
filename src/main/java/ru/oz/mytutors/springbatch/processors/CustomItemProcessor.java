package ru.oz.mytutors.springbatch.processors;

import org.springframework.batch.item.ItemProcessor;
import ru.oz.mytutors.springbatch.model.Student;

/**
 * Created by ozol on 15.03.2016.
 */
public class CustomItemProcessor implements ItemProcessor<Student, Student> {

    public Student process(Student student) throws Exception {
        return student;
    }
}
