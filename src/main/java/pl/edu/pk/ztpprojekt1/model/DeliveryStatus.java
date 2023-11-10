package pl.edu.pk.ztpprojekt1.model;

/**
 * Typ wyliczeniowy reprezentujący status zamówienia:
 * IN_PACKAGING: paczka w przygotowaniu, niewysłana
 * SENT: paczka zarejestrowana w systemie przewoźnika
 */
public enum DeliveryStatus {
    IN_PACKAGING("w trakcie przygotowania"),
    SENT("wysłane");

    private final String status;

    DeliveryStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
