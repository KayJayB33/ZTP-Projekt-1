package pl.edu.pk.ztpprojekt1.service.delivery;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import pl.edu.pk.ztpprojekt1.model.DeliveryStatus;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME)
@JsonSubTypes({
        @JsonSubTypes.Type(value = ParcelLockerDelivery.class, name = "ParcelLockerDelivery"),
        @JsonSubTypes.Type(value = PostDelivery.class, name = "PostDelivery")
})
public interface DeliveryStrategy {
    boolean send();

    @JsonProperty("delivery_details")
    String getDeliveryDetails();
    DeliveryStatus getDeliveryStatus();
}
