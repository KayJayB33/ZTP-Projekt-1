package pl.edu.pk.ztpprojekt1.service.delivery;

import com.fasterxml.jackson.annotation.JsonProperty;
import pl.edu.pk.ztpprojekt1.model.DeliveryStatus;

import java.util.Objects;

/**
 * Klasa reprezentująca strategię wysłania zamówienia poprzez Pocztę Polską
 */
public class PostDelivery implements DeliveryStrategy {
    private String addressee;
    private String address;
    @JsonProperty("delivery_status")
    private DeliveryStatus deliveryStatus;

    public String getAddressee() {
        return addressee;
    }

    public void setAddressee(String addressee) {
        this.addressee = addressee;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public PostDelivery(String addressee, String address) {
        this();
        this.addressee = addressee;
        this.address = address;
    }

    public PostDelivery() {
        this.deliveryStatus = DeliveryStatus.IN_PACKAGING;
    }

    /**
     * Wysyła zamówienie Pocztą Polską
     * @return true jeśli zamówienie zostało wysłane pomyślnie
     */
    @Override
    public boolean send() {
        // wysyłanie danych do API systemu poczty polskiej w celu zarejestrowania przesyłki
        deliveryStatus = DeliveryStatus.SENT;
        return true;
    }

    @Override
    public String getDeliveryDetails() {
        return addressee + ", " + address;
    }

    @Override
    public DeliveryStatus getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(DeliveryStatus deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostDelivery that = (PostDelivery) o;
        return Objects.equals(addressee, that.addressee) && Objects.equals(address, that.address) && deliveryStatus == that.deliveryStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(addressee, address, deliveryStatus);
    }

    @Override
    public String toString() {
        return "PostDelivery{" +
                "addressee='" + addressee + '\'' +
                ", address='" + address + '\'' +
                ", deliveryStatus=" + deliveryStatus +
                '}';
    }
}
