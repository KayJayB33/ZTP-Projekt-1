package pl.edu.pk.ztpprojekt1.util;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Klasa wspomagająca odpowiedzialna za utworzenie pliku JSON dla danych.
 */
public class JsonFileHandler {
    public static void createFileIfNotExists(File jsonFile) throws IOException {
        if(jsonFile.exists()) {
            return;
        }

        if(!jsonFile.getParentFile().exists()) {
            if(!jsonFile.getParentFile().mkdirs()) {
                throw new IOException("Could not create directory: " + jsonFile.getParentFile().getAbsolutePath());
            }
        }

        if(!jsonFile.createNewFile()) {
            throw new IOException("Could not create file: " + jsonFile.getAbsolutePath());
        }

        try(PrintWriter writer = new PrintWriter(jsonFile)) {
            writer.write("[]");
        }
    }
}
