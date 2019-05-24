package com.bastex.dataextractiontool.extractor.personalpagedata.tos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonalPageDataTO {
    private Long pageId;
    private String pageTitle;
    private LocalDate yearOfBirth;
}
