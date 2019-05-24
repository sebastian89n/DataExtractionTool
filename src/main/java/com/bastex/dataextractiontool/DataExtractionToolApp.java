package com.bastex.dataextractiontool;

import com.bastex.dataextractiontool.configuration.SpringMainConfiguration;
import com.bastex.dataextractiontool.extractor.personalpagedata.IPersonalPageDataExtractor;
import com.bastex.dataextractiontool.extractor.personalpagedata.tos.PersonalPageDataTO;
import com.google.common.collect.ImmutableCollection;
import org.springframework.beans.factory.annotation.BeanFactoryAnnotationUtils;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.stream.Collectors;

public class DataExtractionToolApp {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringMainConfiguration.class);
        IPersonalPageDataExtractor personDataExtractor = BeanFactoryAnnotationUtils.qualifiedBeanOfType(applicationContext.getBeanFactory(), IPersonalPageDataExtractor.class, "wikipedia");

        ImmutableCollection<PersonalPageDataTO> personalPageDataTOs = personDataExtractor.extractDataForName(args[0], args[1]);

        String logForDisplay = personalPageDataTOs.stream().map(PersonalPageDataTO::toString)
                .collect(Collectors.joining("\n"));
        System.out.println(logForDisplay);
    }
}
