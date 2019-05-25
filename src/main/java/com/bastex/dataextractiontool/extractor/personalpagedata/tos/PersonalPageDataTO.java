package com.bastex.dataextractiontool.extractor.personalpagedata.tos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Optional;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonalPageDataTO {
    private Long pageId;
    private String pageTitle;
    private LocalDate yearOfBirth;

    @Override
    public String toString() {
        return "PersonalPageDataTO{" +
                "pageId=" + pageId +
                ", pageTitle='" + pageTitle + '\'' +
                ", yearOfBirth=" + Optional.ofNullable(yearOfBirth).map(LocalDate::getYear).orElse(null) +
                '}';
    }
}
