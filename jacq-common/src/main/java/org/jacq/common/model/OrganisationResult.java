/*
 * Copyright 2016 wkoller.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jacq.common.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import org.jacq.common.model.jpa.TblOrganisation;

/**
 * Wrapper model which represents a single result after a search Used to
 * minimize the transfered data and only return the relevant information
 *
 * @author fhafner
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class OrganisationResult {

    protected Long organisationId;
    protected String description;
    protected String department;
    protected boolean greenhouse;
    protected String ipenCode;

    public Long getOrganisationId() {
        return organisationId;
    }

    public void setOrganisationIdLong(Long organisationId) {
        this.organisationId = organisationId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public boolean getGreenhouse() {
        return greenhouse;
    }

    public void setGreenhouse(boolean greenhouse) {
        this.greenhouse = greenhouse;
    }

    public String getIpenCode() {
        return ipenCode;
    }

    public void setIpenCode(String ipenCode) {
        this.ipenCode = ipenCode;
    }

    public OrganisationResult() {
    }

    public OrganisationResult(TblOrganisation organisation) {
        this.organisationId = organisation.getId();
        this.description = organisation.getDescription();
        this.department = organisation.getDepartment();
        this.greenhouse = organisation.getGreenhouse();
        this.ipenCode = organisation.getIpenCode();
    }

    /**
     * Helper function for converting a list of Organisation entries to
     * organisation results
     *
     * @param organisationList
     * @return
     */
    public static List<OrganisationResult> fromList(List<TblOrganisation> organisationList) {
        List<OrganisationResult> organisationResult = new ArrayList<>();

        if (organisationList != null) {
            for (TblOrganisation organisation : organisationList) {
                organisationResult.add(new OrganisationResult(organisation));
            }
        }

        return organisationResult;
    }
}