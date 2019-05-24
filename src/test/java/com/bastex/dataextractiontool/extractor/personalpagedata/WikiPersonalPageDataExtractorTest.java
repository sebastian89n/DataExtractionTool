package com.bastex.dataextractiontool.extractor.personalpagedata;

import com.bastex.dataextractiontool.apiclient.wikipedia.IWikipediaApiClient;
import com.bastex.dataextractiontool.apiclient.wikipedia.tos.WikiSearchResultTO;
import com.bastex.dataextractiontool.extractor.personalpagedata.resultpreparator.IPersonalPageDataPreparator;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class WikiPersonalPageDataExtractorTest {
    private static final String FIRST_NAME_SAMPLE = "firstname";
    private static final String LAST_NAME_SAMPLE = "lastname";

    @Mock
    private IPersonalPageDataPreparator<WikiSearchResultTO> wikiPersonalPageDataPreparator;

    @Mock
    private IWikipediaApiClient wikipediaApiClient;

    private WikiPersonalPageDataExtractor wikiPersonalPageDataExtractor;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        wikiPersonalPageDataExtractor = new WikiPersonalPageDataExtractor(wikipediaApiClient, wikiPersonalPageDataPreparator);
    }

    @Test(expected = NullPointerException.class)
    public void extractDataForName_FirstNameIsNull_ShouldThrowNPE() {
        wikiPersonalPageDataExtractor.extractDataForName(null, LAST_NAME_SAMPLE);
    }

    @Test(expected = NullPointerException.class)
    public void extractDataForName_LastNameIsNull_ShouldThrowNPE() {
        wikiPersonalPageDataExtractor.extractDataForName(FIRST_NAME_SAMPLE, null);
    }

    @Test(expected = NullPointerException.class)
    public void extractDataForName_BothFirstAndLastNameAreNull_ShouldThrowNPE() {
        wikiPersonalPageDataExtractor.extractDataForName(null, null);
    }

    @Test
    public void extractDataForName_BothFirstAndLastNameAreSpecified_VerifyMethodInvocations() {
        wikiPersonalPageDataExtractor.extractDataForName(FIRST_NAME_SAMPLE, LAST_NAME_SAMPLE);
        Mockito.verify(wikipediaApiClient, Mockito.times(1)).performSearchQuery(Mockito.anyString());
        Mockito.verify(wikiPersonalPageDataPreparator, Mockito.times(1)).preparePersonData(Mockito.any());
    }
}