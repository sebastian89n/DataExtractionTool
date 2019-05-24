package com.bastex.dataextractiontool.extractor.personalpagedata.resultpreparator;

import com.bastex.dataextractiontool.apiclient.wikipedia.tos.WikiSearchResultTO;
import com.bastex.dataextractiontool.extractor.personalpagedata.tos.PersonalPageDataTO;
import com.bastex.dataextractiontool.util.DateUtils;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Qualifier("wikiPersonalPageDataPreparator")
class WikiPersonalPageDataPreparator implements IPersonalPageDataPreparator<WikiSearchResultTO> {
    private static final String BORN_WITH_YEAR_REGEX_AS_TEXT = "born.*([1-9]\\d{3})";
    private static final Pattern BORN_WITH_YEAR_REGEX_PATTERN = Pattern.compile(BORN_WITH_YEAR_REGEX_AS_TEXT, Pattern.CASE_INSENSITIVE);

    private DateUtils dateUtils;

    @Autowired
    public WikiPersonalPageDataPreparator(DateUtils dateUtils) {
        this.dateUtils = dateUtils;
    }

    @Override
    public ImmutableCollection<PersonalPageDataTO> preparePersonalPageData(Collection<WikiSearchResultTO> searchResultFromWiki) {
        ImmutableCollection<PersonalPageDataTO> extractedPersonDataTO = ImmutableList.of();

        if (!CollectionUtils.isEmpty(searchResultFromWiki)) {
            extractedPersonDataTO = searchResultFromWiki.stream().filter(searchResult -> BORN_WITH_YEAR_REGEX_PATTERN.matcher(searchResult.getSnippet()).find())
                    .map(this::convertWikiSeachResultToPersonPageData)
                    .collect(ImmutableList.toImmutableList());
        }

        return extractedPersonDataTO;
    }

    private PersonalPageDataTO convertWikiSeachResultToPersonPageData(WikiSearchResultTO searchResultTO) {
        Matcher matcher = BORN_WITH_YEAR_REGEX_PATTERN.matcher(searchResultTO.getSnippet());
        LocalDate birthYear = obtainYearOfBirth(matcher);

        PersonalPageDataTO personalPageDataTO = PersonalPageDataTO.builder()
                .yearOfBirth(birthYear)
                .pageId(searchResultTO.getPageId())
                .pageTitle(searchResultTO.getTitle())
                .build();

        return personalPageDataTO;
    }

    private LocalDate obtainYearOfBirth(Matcher matcher) {
        LocalDate birthYear = null;

        if (matcher.find()) {
            String birthYearAsText = matcher.group(1);
            birthYear = dateUtils.parseYearToLocalDate(birthYearAsText);
        }

        return birthYear;
    }
}
