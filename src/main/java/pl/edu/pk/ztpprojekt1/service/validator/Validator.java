package pl.edu.pk.ztpprojekt1.service.validator;

/**
 * Interfejs dla klas sprawdzających poprawność danych.
 * @param <T> typ klasy Której poprawność danych jest sprawdzana
 */
public interface Validator<T> {
    T validate(String[] params);
}
