package ru.oz.mytutors.springbatch.model.sem;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import ru.oz.mytutors.springbatch.model.SearchConstants;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.time.LocalDate;

/**
 * Created by ozol on 18.03.2016.
 */
@ToString(includeFieldNames = true)
@NoArgsConstructor
@Getter
@Setter
//@XmlRootElement(name = "price")
@XmlRootElement(name = "sphinx:document")
public class Price {
    @JsonProperty(value = SearchConstants.PRICE_ID)
    @XmlAttribute(name = "id")
    private long priceId;
    @JsonProperty(value = SearchConstants.PRODUCT_ID)
    private Long productId;
    @JsonProperty(value = SearchConstants.BASESTORE)
    private String baseStore;
    @JsonProperty(value = SearchConstants.PRICE)
    private double priceValue;
    @JsonProperty(value = SearchConstants.OLD_PRICE)
    private double oldPriceValue;
    @JsonProperty(value = SearchConstants.BESTSELLER)
    private int bestseller;
    @JsonProperty(value = SearchConstants.ACTIVATION_TIME)
    private long activationTime;
    @JsonProperty(value = SearchConstants.PICKUP_TODAY)
    private boolean pickupToday;
    @JsonProperty(value = SearchConstants.DELIVERY_DATE)
    private LocalDate deliveryDate;
    @JsonProperty(value = SearchConstants.NEXT_DELIVERY_DATE)
    private LocalDate nextDeliveryDate;

    @Builder
    public Price(long priceId,
                 long productId,
                 String baseStore,
                 double priceValue,
                 double oldPriceValue,
                 int bestseller,
                 long activationTime,
                 boolean pickupToday,
                 LocalDate deliveryDate,
                 LocalDate nextDeliveryDate) {
        this.priceId = priceId;
        this.productId = productId;
        this.baseStore = baseStore;
        this.priceValue = priceValue;
        this.oldPriceValue = oldPriceValue;
        this.bestseller = bestseller;
        this.activationTime = activationTime;
        this.pickupToday = pickupToday;
        this.deliveryDate = deliveryDate == null ? LocalDate.of(2000, 1, 1) : deliveryDate;                 //default value
        this.nextDeliveryDate = nextDeliveryDate == null ? LocalDate.of(2000, 1, 1) : nextDeliveryDate;     //default value
    }

    @XmlTransient
    public long getPriceId() {
        return priceId;
    }
}