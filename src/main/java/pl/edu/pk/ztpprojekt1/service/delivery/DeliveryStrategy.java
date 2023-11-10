package pl.edu.pk.ztpprojekt1.service.delivery;

public interface DeliveryStrategy {
    boolean send();

    String getDeliveryDetails();
}
