package com.bastex.dataextractiontool.apiclient.wikipedia;

import com.bastex.dataextractiontool.apiclient.wikipedia.tos.WikiSearchResultTO;

import java.util.Collection;

public interface IWikipediaApiClient {
    Collection<WikiSearchResultTO> performSearchQuery(String searchedText);
}
