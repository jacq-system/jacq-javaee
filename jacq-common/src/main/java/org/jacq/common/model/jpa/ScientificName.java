package org.jacq.common.model.jpa;

public class ScientificName {
    private final Long nameId;
    private final String scientificName;

    public ScientificName(Long nameId, String scientificName) {
        this.nameId = nameId;
        this.scientificName = scientificName;
    }

    public Long getNameId() {
        return nameId;
    }

    public String getScientificName() {
        return scientificName;
    }
}
