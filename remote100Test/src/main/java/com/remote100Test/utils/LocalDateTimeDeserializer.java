package com.remote100Test.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {

    @Override
    public LocalDateTime deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext) throws IOException {
        String strDate = jsonparser.getText();
        String modDate = strDate.substring(0, 4) + strDate.substring(4, 6).toLowerCase() + strDate.substring(6);
        return LocalDateTime.parse(modDate, DateTimeFormatter.ofPattern("dd-MMM-yy HH:mm:ss"));
    }
}
