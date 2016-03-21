package ru.oz.mytutors.springbatch.model.sem;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.collections.CollectionUtils;
import ru.oz.mytutors.springbatch.model.enums.PropertyTypeEnum;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Plonin on 12.11.2015.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PropertyValue {

    private static final String TRUE = "true";

    PropertyTypeEnum type;
    String valueString;
    Long valueLong;
    Boolean valueBoolean;
    Double valueDouble;

    List<PropertyValue> valueMVA = new ArrayList<PropertyValue>();

    public PropertyValue(PropertyTypeEnum type) {
        this.type = type;
    }

    public PropertyValue(PropertyTypeEnum type, String value) {
        this.type = type;
        switch (type){
            case STRING :
                setValueString(value);
                break;
            case LONG :
                setValueLong(Long.parseLong(value));
                break;
            case BOOLEAN:
                setValueBoolean(TRUE.equals(value));
                break;
            case DOUBLE:
                setValueDouble(Double.parseDouble(value));
                break;
        }
    }

    public PropertyValue(List<PropertyValue> valueMVA) {
        this.valueMVA = valueMVA;
        this.type = PropertyTypeEnum.MVA;
    }

    public String getValue() {
        switch (type){
            case STRING :
                return getValueString();
            case LONG :
                return String.valueOf(getValueLong());
            case BOOLEAN:
                return String.valueOf(getValueBoolean());
            case DOUBLE:
                return String.valueOf(getValueDouble());
            case MVA:
            default:
               throw  new IllegalStateException("Inner property must not be MVA (tmf)");
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("PropertyValue{ ").append("Type").append("=").append(type);
        if (valueString != null) {
            builder.append(", ").append("valueString").append("=").append(valueString);
        }
        if (valueLong != null) {
            builder.append(", ").append("valueLong").append("=").append(valueLong);
        }
        if (valueBoolean != null) {
            builder.append(", ").append("valueBoolean").append("=").append(valueBoolean);
        }
        if (valueDouble != null) {
            builder.append(", ").append("valueDouble").append("=").append(valueDouble);
        }
        if (CollectionUtils.isNotEmpty(valueMVA)) {
            builder.append(", ").append("valueMVA").append("{");
            for (PropertyValue propertyValue : valueMVA) {
                builder.append(propertyValue.valueString).append(",");
            }
            builder.deleteCharAt(builder.length()-1);
            builder.append("}");
        }
        builder.append(" }");
        return builder.toString();
    }
}
