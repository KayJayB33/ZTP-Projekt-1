package pl.edu.pk.ztpprojekt1.service.validator;

public interface Validator<T> {
    T validate(String[] params);
}
