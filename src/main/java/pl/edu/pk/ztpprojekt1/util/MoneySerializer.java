package pl.edu.pk.ztpprojekt1.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.math.BigDecimal;

/**
 * Serializer dla pola o typie BigDecimal, który docelowo reprezentuje cenę/walutę.
 */
public class MoneySerializer extends JsonSerializer<BigDecimal> {
    @Override
    public void serialize(BigDecimal value, JsonGenerator jgen, SerializerProvider provider) throws IOException,
            JsonProcessingException {
        jgen.writeString(value.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
    }
}
