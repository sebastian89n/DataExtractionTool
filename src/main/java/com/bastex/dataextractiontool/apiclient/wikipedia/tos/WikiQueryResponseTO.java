package com.bastex.dataextractiontool.apiclient.wikipedia.tos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class WikiQueryResponseTO {
    @JsonProperty("query")
    private WikiQueryDataTO queryResult;

    @JsonProperty(value = "continue")
    private WikiContinueInfoTO continueInfo;
}
