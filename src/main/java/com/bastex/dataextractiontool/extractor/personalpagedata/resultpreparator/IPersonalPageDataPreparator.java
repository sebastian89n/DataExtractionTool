package com.bastex.dataextractiontool.extractor.personalpagedata.resultpreparator;

import com.bastex.dataextractiontool.extractor.personalpagedata.tos.PersonalPageDataTO;
import com.google.common.collect.ImmutableCollection;

import java.util.Collection;
/**
 * Prepares ExtractedPersonDataTOs based on data gather from the external source.
 *
 * @author Sebastian Nowak
 * @createdAt 24.05.2019
 */
public interface IPersonalPageDataPreparator<T> {
    ImmutableCollection<PersonalPageDataTO> preparePersonalPageData(Collection<T> data);
}
