package ru.oz.mytutors.springbatch.model.sem;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import ru.oz.mytutors.springbatch.SearchConstants;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Plonin on 12.11.2015.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(includeFieldNames = true, exclude = {"prices","unavailableBasestores"} )
@XmlRootElement(name = "sphinx:document")
public final class Product {

    private long id;

    @JsonProperty(value = SearchConstants.NAME)
    private String name;

    @JsonProperty(value = SearchConstants.FULL_NAME)
    private String fullName;

    @JsonProperty(value = SearchConstants.PRODUCT_ID)
    private Long productId = -1L;

    @JsonProperty(value = SearchConstants.REGION_PICKUP_AVAILABLE)
    private List<String> regionPickupAvailable = new ArrayList<>(64);

    @JsonProperty(value = SearchConstants.REGION_DELIVERY_AVAILABLE)
    private List<String> regionDeliveryAvailable = new ArrayList<>(64);

    @JsonProperty(value = SearchConstants.CATEGORY_ID)
    private long categoryId = -1L;

    @JsonProperty(value = SearchConstants.CATEGORY_NAME)
    private String categoryName;

    private List<Long> categories = new ArrayList<>(16);

    @JsonProperty(value = SearchConstants.BRAND_ID)
    private long brandId = -1L;

    @JsonProperty(value = SearchConstants.BRAND_NAME)
    private String brandName;

    private String description;

    @JsonProperty(value = SearchConstants.PROPERTY_AGGREGATE)
    private String propertyAggregate;

    @JsonProperty(value = SearchConstants.PROPERTIES)
    private List<Property> properties = new ArrayList<>(64);

    @JsonProperty(value = SearchConstants.UNAVAILABLE_BASESTORES)
    private List<String> unavailableBasestores = new ArrayList<>(64);

    @JsonProperty(value = SearchConstants.FILTERS)
    private List<Integer> filters = new ArrayList<>();

    @JsonProperty(value = SearchConstants.RATING)
    private int rating;

    @JsonProperty(value = SearchConstants.IMAGE_URL)
    private String imageUrl;

    @JsonProperty(value = SearchConstants.BASESTORES_WITH_PRICE)
    private List<String> basestoresWithPrice = new ArrayList<>(64);

    @JsonProperty(value = SearchConstants.PRODUCT_WEIGHT)
    private float productWeight;

    @JsonProperty(value = SearchConstants.TOTAL_WEIGHT)
    private float totalWeight;

    @JsonIgnore
    private List<Price> prices = new ArrayList<>();

    public String toStringExtended() {
        return "\nProduct{" +
                "id=" + id +
                ",\n name='" + name + '\'' +
                ",\n fullName='" + fullName + '\'' +
                ",\n productId=" + productId +
                ",\n regionPickupAvailable=" + regionPickupAvailable +
                ",\n regionDeliveryAvailable=" + regionDeliveryAvailable +
                ",\n unavailableBasestores=" + unavailableBasestores +
                ",\n categoryId=" + categoryId +
                ",\n categoryName='" + categoryName + '\'' +
                ",\n categories=" + categories +
                ",\n brandId=" + brandId +
                ",\n brandName='" + brandName + '\'' +
                ",\n description='" + description + '\'' +
                ",\n propertyAggregate='" + propertyAggregate + '\'' +
                ",\n propertyMap=" + properties +
                ",\n image_url=" + imageUrl +
                ",\n productWeight=" + productWeight +
                ",\n totalWeight=" + totalWeight +
                ",\n prices=" + prices +
                '}';
    }

}
