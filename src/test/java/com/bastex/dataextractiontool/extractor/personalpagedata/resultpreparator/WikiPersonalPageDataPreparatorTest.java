package com.bastex.dataextractiontool.extractor.personalpagedata.resultpreparator;

import com.bastex.dataextractiontool.apiclient.wikipedia.tos.WikiSearchResultTO;
import com.bastex.dataextractiontool.extractor.personalpagedata.tos.PersonalPageDataTO;
import com.bastex.dataextractiontool.util.DateUtils;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.Lists;
import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

public class WikiPersonalPageDataPreparatorTest {
    private static final DateUtils DATE_UTILS = new DateUtils();
    private static final WikiPersonalPageDataPreparator WIKI_PERSONAL_PAGE_DATA_PREPARATOR = new WikiPersonalPageDataPreparator(DATE_UTILS);

    @Test
    public void preparePersonalPageData_SearchResultsAreNull_ShouldReturnEmptyImmutableCollection() {
        ImmutableCollection<PersonalPageDataTO> personalPageDataTOs = WIKI_PERSONAL_PAGE_DATA_PREPARATOR.preparePersonalPageData(null);
        Assert.assertNotNull(personalPageDataTOs);
    }

    @Test
    public void preparePersonalPageData_SearchResultsAreEmpty_ShouldReturnEmptyImmutableCollection() {
        ImmutableCollection<PersonalPageDataTO> personalPageDataTOs = WIKI_PERSONAL_PAGE_DATA_PREPARATOR.preparePersonalPageData(Collections.emptyList());
        Assert.assertNotNull(personalPageDataTOs);
    }

    @Test
    public void preparePersonalPageData_SearchResultsExistsButWithoutBornAndYear_ShouldReturnEmptyImmutableCollection() {
        List<WikiSearchResultTO> wikiSearchResults = Lists.newArrayList();

        for (long i = 0; i <= 5; i++) {
            WikiSearchResultTO wikiSearchResultTO = WikiSearchResultTO.builder().snippet("This is some random text without born or year.")
                    .pageId(i)
                    .timestamp(LocalDateTime.now())
                    .title("Title " + i)
                    .wordCount(100L + i)
                    .build();

            wikiSearchResults.add(wikiSearchResultTO);
        }

        ImmutableCollection<PersonalPageDataTO> personalPageDataTOs = WIKI_PERSONAL_PAGE_DATA_PREPARATOR.preparePersonalPageData(wikiSearchResults);

        Assert.assertNotNull(personalPageDataTOs);
        Assert.assertThat(personalPageDataTOs.size(), Is.is(0));
    }

    @Test
    public void preparePersonalPageData_SearchResultsExistsWithOneResultsWithBornAndDate_ShouldReturnSinglePersonPageTO() {
        List<WikiSearchResultTO> wikiSearchResults = Lists.newArrayList();

        for (long i = 0; i <= 5; i++) {
            WikiSearchResultTO wikiSearchResultTO = WikiSearchResultTO.builder().snippet("This is some random text without born or year.")
                    .pageId(i)
                    .timestamp(LocalDateTime.now())
                    .title("Title " + i)
                    .wordCount(100L + i)
                    .build();

            wikiSearchResults.add(wikiSearchResultTO);
        }

        WikiSearchResultTO wikiSearchResultWithBornAndDate = WikiSearchResultTO.builder().snippet("This is some random text about FirstName LastName born in 1968")
                .pageId(5L)
                .timestamp(LocalDateTime.now())
                .title("Random Title")
                .wordCount(105L)
                .build();

        wikiSearchResults.add(wikiSearchResultWithBornAndDate);

        ImmutableCollection<PersonalPageDataTO> personalPageDataTOs = WIKI_PERSONAL_PAGE_DATA_PREPARATOR.preparePersonalPageData(wikiSearchResults);

        Assert.assertNotNull(personalPageDataTOs);
        Assert.assertThat(personalPageDataTOs.size(), Is.is(1));
    }

    @Test
    public void preparePersonalPageData_SearchResultsExistsWithOneResultsWithBornAndDate_TestCaseInsensitivityForStringMatching() {
        List<WikiSearchResultTO> wikiSearchResults = Lists.newArrayList();

        for (long i = 0; i <= 5; i++) {
            WikiSearchResultTO wikiSearchResultTO = WikiSearchResultTO.builder().snippet("This is some random text without born or year.")
                    .pageId(i)
                    .timestamp(LocalDateTime.now())
                    .title("Title " + i)
                    .wordCount(100L + i)
                    .build();

            wikiSearchResults.add(wikiSearchResultTO);
        }

        WikiSearchResultTO wikiSearchResultWithBornAndDate = WikiSearchResultTO.builder().snippet("This is some random text about FirstName LastName bOrN iN Los Angeles in 1968")
                .pageId(5L)
                .timestamp(LocalDateTime.now())
                .title("Random Title")
                .wordCount(105L)
                .build();

        wikiSearchResults.add(wikiSearchResultWithBornAndDate);

        ImmutableCollection<PersonalPageDataTO> personalPageDataTOs = WIKI_PERSONAL_PAGE_DATA_PREPARATOR.preparePersonalPageData(wikiSearchResults);

        Assert.assertNotNull(personalPageDataTOs);
        Assert.assertThat(personalPageDataTOs.size(), Is.is(1));
    }
}