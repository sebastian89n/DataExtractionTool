package com.bastex.dataextractiontool.extractor.personalpagedata;

import com.bastex.dataextractiontool.apiclient.wikipedia.IWikipediaApiClient;
import com.bastex.dataextractiontool.apiclient.wikipedia.tos.WikiSearchResultTO;
import com.bastex.dataextractiontool.extractor.personalpagedata.resultpreparator.IPersonalPageDataPreparator;
import com.bastex.dataextractiontool.extractor.personalpagedata.tos.PersonalPageDataTO;
import com.bastex.dataextractiontool.util.AppConstants;
import com.google.common.collect.ImmutableCollection;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@Qualifier("wikipedia")
class WikiPersonalPageDataExtractor implements IPersonalPageDataExtractor {

    private IWikipediaApiClient wikipediaApiClient;
    private IPersonalPageDataPreparator<WikiSearchResultTO> wikiPersonalPageDataPreparator;

    @Autowired
    public WikiPersonalPageDataExtractor(IWikipediaApiClient wikipediaApiClient, @Qualifier("wikiPersonalPageDataPreparator") IPersonalPageDataPreparator<WikiSearchResultTO> wikiPersonalPageDataPreparator) {
        this.wikipediaApiClient = wikipediaApiClient;
        this.wikiPersonalPageDataPreparator = wikiPersonalPageDataPreparator;
    }

    @Override
    public ImmutableCollection<PersonalPageDataTO> extractDataForName(@NonNull String firstName, @NonNull String lastName) {
        Collection<WikiSearchResultTO> searchResultData = executeApiQuery(firstName, lastName);
        ImmutableCollection<PersonalPageDataTO> personalPageDataTOs = wikiPersonalPageDataPreparator.preparePersonalPageData(searchResultData);
        return personalPageDataTOs;
    }

    private Collection<WikiSearchResultTO> executeApiQuery(String firstName, String lastName) {
        String searchedText = AppConstants.ENCODED_DOUBLE_QUOTE + firstName + "+" + lastName + AppConstants.ENCODED_DOUBLE_QUOTE;
        Collection<WikiSearchResultTO> searchResultData = wikipediaApiClient.performSearchQuery(searchedText);
        return searchResultData;
    }
}
