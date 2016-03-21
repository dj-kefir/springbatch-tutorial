package ru.oz.mytutors.springbatch.model.sem;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by bychkova on 27.01.2016.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Property {
    String id;
    PropertyValue propertyValue;
}
