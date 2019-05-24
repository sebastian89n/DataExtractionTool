package com.bastex.dataextractiontool.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.node.TextNode;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Custom deserializer for parsing date from Wiki API response to LocalDateTime.
 *
 * @author Sebastian Nowak
 * @createdAt 24.05.2019
 */
public class WikiJsonDateDeserializer extends JsonDeserializer<LocalDateTime> {
    private static final String WIKI_DATE_PATTERN = "yyyy-MM-dd'T'HH:mm:ss'Z'";

    @Override
    public LocalDateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        String dateAsText = obtainDateAsText(jsonParser);
        LocalDateTime parsedLocalDateTime = parseDate(dateAsText);
        return parsedLocalDateTime;
    }

    private String obtainDateAsText(JsonParser jsonParser) throws IOException {
        ObjectCodec codec = jsonParser.getCodec();
        TextNode node = codec.readTree(jsonParser);
        String dateAsText = node.textValue();
        return dateAsText;
    }

    private LocalDateTime parseDate(String dateAsText) {
        LocalDateTime parsedLocalDateTime = null;

        if (StringUtils.isNotEmpty(dateAsText)) {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(WIKI_DATE_PATTERN);
            parsedLocalDateTime = LocalDateTime.parse(dateAsText, dateTimeFormatter);
        }

        return parsedLocalDateTime;
    }
}
