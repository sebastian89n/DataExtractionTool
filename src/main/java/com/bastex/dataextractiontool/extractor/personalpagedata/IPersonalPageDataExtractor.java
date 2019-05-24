package com.bastex.dataextractiontool.extractor.personalpagedata;

import com.bastex.dataextractiontool.extractor.personalpagedata.tos.PersonalPageDataTO;
import com.google.common.collect.ImmutableCollection;

public interface IPersonalPageDataExtractor {
    ImmutableCollection<PersonalPageDataTO> extractDataForName(String firstName, String lastName);
}
