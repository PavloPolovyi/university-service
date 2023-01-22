package com.example.universityservice.dto;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class DepartmentSummaryStatistics {
    private static final Long MIN_COUNT = 0L;
    private final Long assistantsCount;
    private final Long associateProfessorsCount;
    private final Long professorsCount;

    public DepartmentSummaryStatistics(Long assistantsCount,
                                       Long associateProfessorsCount, Long professorsCount) {
        this.assistantsCount = assistantsCount != null ? assistantsCount : MIN_COUNT;
        this.associateProfessorsCount = associateProfessorsCount != null
                ? associateProfessorsCount : MIN_COUNT;
        this.professorsCount = professorsCount != null ? professorsCount : MIN_COUNT;
    }

    @Override
    public String toString() {
        return "\nassistants - " + assistantsCount + ","
                + System.lineSeparator()
                + "associate professors - " + associateProfessorsCount + ","
                + System.lineSeparator()
                + "professors - " + professorsCount;
    }
}
