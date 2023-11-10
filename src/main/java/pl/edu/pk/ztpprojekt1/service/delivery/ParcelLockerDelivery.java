package pl.edu.pk.ztpprojekt1.service.delivery;

import com.fasterxml.jackson.annotation.JsonProperty;
import pl.edu.pk.ztpprojekt1.model.DeliveryStatus;

import java.util.Objects;

/**
 * Klasa reprezentująca strategię wysłania zamówienia poprzez paczkomaty.
 */
public class ParcelLockerDelivery implements DeliveryStrategy {
    private String addressee;
    private String address;
    @JsonProperty("parcel_locker_id")
    private String parcelLockerId;
    @JsonProperty("delivery_status")
    private DeliveryStatus deliveryStatus;

    public ParcelLockerDelivery(String addressee, String address, String parcelLockerId) {
        this();
        this.addressee = addressee;
        this.address = address;
        this.parcelLockerId = parcelLockerId;
    }

    public ParcelLockerDelivery() {
        this.deliveryStatus = DeliveryStatus.IN_PACKAGING;
    }

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

    public String getParcelLockerId() {
        return parcelLockerId;
    }

    public void setParcelLockerId(String parcelLockerId) {
        this.parcelLockerId = parcelLockerId;
    }

    @Override
    public DeliveryStatus getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(DeliveryStatus deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    /**
     * Wysyła zamówienie do paczkomatu
     * @return true jeśli zamówienie zostało wysłane pomyślnie
     */
    @Override
    public boolean send() {
        // wysyłanie danych do API systemu obsługi paczkomatu w celu zarejestrowania przesyłki
        deliveryStatus = DeliveryStatus.SENT;
        return true;
    }

    @Override
    public String getDeliveryDetails() {
        return String.format("%s, %s, paczkomat %s", addressee, address, parcelLockerId);
    }

    @Override
    public String toString() {
        return "ParcelLockerDelivery{" +
                "addressee='" + addressee + '\'' +
                ", address='" + address + '\'' +
                ", parcelLockerId='" + parcelLockerId + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParcelLockerDelivery that = (ParcelLockerDelivery) o;
        return Objects.equals(addressee, that.addressee) && Objects.equals(address, that.address) && Objects.equals(parcelLockerId, that.parcelLockerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(addressee, address, parcelLockerId);
    }
}
