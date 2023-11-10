package pl.edu.pk.ztpprojekt1.service.delivery;

import java.util.Objects;

/**
 * Klasa reprezentująca strategię wysłania zamówienia poprzez Pocztę Polską.
 */
public class PostDelivery implements DeliveryStrategy {
    private String addressee;
    private String address;

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

    /**
     * Wysyła zamówienie Pocztą Polską
     * @return true jeśli zamówienie zostało wysłane pomyślnie
     */
    @Override
    public boolean send() {
        // wysyłanie danych do API systemu poczty polskiej w celu zarejestrowania przesyłki
        return true;
    }

    @Override
    public String getDeliveryDetails() {
        return addressee + ", " + address;
    }

    @Override
    public String toString() {
        return "PostDelivery{" +
                "addressee='" + addressee + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostDelivery that = (PostDelivery) o;
        return Objects.equals(addressee, that.addressee) && Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(addressee, address);
    }
}
