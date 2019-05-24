package com.bastex.dataextractiontool.apiclient.wikipedia;

import com.bastex.dataextractiontool.apiclient.wikipedia.tos.WikiContinueInfoTO;
import com.bastex.dataextractiontool.apiclient.wikipedia.tos.WikiQueryDataTO;
import com.bastex.dataextractiontool.apiclient.wikipedia.tos.WikiQueryResponseTO;
import com.bastex.dataextractiontool.apiclient.wikipedia.tos.WikiSearchResultTO;
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import com.google.common.collect.Lists;
import org.apache.cxf.jaxrs.client.WebClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Primary
@PropertySource("classpath:application.properties")
class WikipediaApiClient implements IWikipediaApiClient{
    private static final List<JacksonJaxbJsonProvider> JACKSON_JAXB_JSON_PROVIDERS = Lists.newArrayList(new JacksonJaxbJsonProvider());

    // Could have been configured in any other way.
    @Value("${wiki.en.api.address}")
    private String enWikiApiAddress;

    @Override
    public Collection<WikiSearchResultTO> performSearchQuery(String searchedText) {
        List<WikiSearchResultTO> searchResultData = Lists.newArrayList();

        Long queryOffset = 0L;
        boolean stillMoreResultsToFetch = true;

        do {
            WikiQueryResponseTO wikiQueryResponseTO = performSearchQueryWithOffset(searchedText, queryOffset);
            WikiQueryDataTO queryResult = wikiQueryResponseTO.getQueryResult();

            List<WikiSearchResultTO> searchResultForCurrentOffset = Optional.ofNullable(queryResult.getSearchResultData()).orElse(Collections.emptyList());
            searchResultData.addAll(searchResultForCurrentOffset);

            WikiContinueInfoTO continueInfo = wikiQueryResponseTO.getContinueInfo();
            if (continueInfo != null) {
                queryOffset = continueInfo.getOffset();
            } else
            {
                stillMoreResultsToFetch = false;
            }

        } while (stillMoreResultsToFetch);

        return searchResultData;
    }

    private WikiQueryResponseTO performSearchQueryWithOffset(String searchedText, Long queryOffset) {
        WebClient webClient = WebClient.create(enWikiApiAddress, JACKSON_JAXB_JSON_PROVIDERS)
                .query("action", "query")
                .query("list", "search")
                .query("format", "json")
                .query("srsearch", searchedText)
                .query("srnamespace", 0)
                .query("srlimit=500")
                .query("sroffset", queryOffset)
                .query("prop", "categories")
                .query("srprop", "categorysnippet%7Cwordcount%7Ctimestamp%7Csnippet")
                .query("redirects", "false");

        WikiQueryResponseTO wikiQueryResponseTO = webClient.get(WikiQueryResponseTO.class);
        return wikiQueryResponseTO;
    }
}
