package com.bastex.dataextractiontool.apiclient.wikipedia.tos;

import com.bastex.dataextractiontool.util.WikiJsonDateDeserializer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class WikiSearchResultTO {
    private String title;

    @JsonProperty("pageid")
    private Long pageId;

    @JsonProperty("wordcount")
    private Long wordCount;

    private String snippet;

    @JsonDeserialize(using = WikiJsonDateDeserializer.class)
    private LocalDateTime timestamp;

    @JsonProperty("categorysnippet")
    private String categorySnippet;
}
