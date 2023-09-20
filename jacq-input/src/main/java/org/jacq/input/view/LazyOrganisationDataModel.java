/*
 * Copyright 2017 fhafner.
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
package org.jacq.input.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.jacq.common.model.rest.OrganisationResult;
import org.jacq.common.rest.OrganisationService;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;

/**
 * Lazy loading data model for botanical object searches
 *
 * @author fhafner
 */
public class LazyOrganisationDataModel extends LazyDataModel<OrganisationResult> {

    protected Boolean hierarchic;

    /**
     * Reference to organisation service which is used during querying
     */
    protected OrganisationService organisationService;

    /**
     * Internal storage of result list
     */
    protected List<OrganisationResult> organisationResult = new ArrayList<>();

    /**
     * Default constructor, needs a reference to the organisation object service
     * for later querying
     *
     * @param organisationService
     */
    public LazyOrganisationDataModel(OrganisationService organisationService) {
        this.organisationService = organisationService;
    }

    @Override
    public OrganisationResult getRowData(String rowKey) {
        Long rowKeyLong = Long.valueOf(rowKey);

        for (OrganisationResult organisationResult : this.organisationResult) {
            if (organisationResult.getOrganisationId().equals(rowKeyLong)) {
                return organisationResult;
            }
        }

        return null;
    }

    @Override
    public String getRowKey(OrganisationResult organisationResult) {
        return organisationResult.getOrganisationId().toString();
    }

    @Override
    public int count(Map<String, FilterMeta> filters) {
        return this.getRowCount();
    }

    @Override
    public List<OrganisationResult> load(int first, int pageSize,
        Map<String, SortMeta> sortFields,
        Map<String, FilterMeta> filters) {

        //Check if Greenhouse is true or False, All = null
        Boolean greenhouse = null;
        if (filters.get("greenhouse") != null) {
            if (String.valueOf(filters.get("greenhouse").getFilterValue()).equals("false")) {
                greenhouse = false;
            } else if (String.valueOf(filters.get("greenhouse").getFilterValue()).equals("true")) {
                greenhouse = true;
            } else {
                greenhouse = null;
            }
        }

        // get count first
        int rowCount = this.organisationService.searchCount(
            (filters.get("organisationId") != null) ? Long.parseLong(
                filters.get("organisationId").getFilterValue().toString()) : null,
            filters.get("description") != null ? filters.get("description").getFilterValue().toString() : null,
            filters.get("department") != null ? filters.get("department").getFilterValue().toString() : null,
            greenhouse, filters.get("ipenCode") != null ? filters.get("ipenCode").getFilterValue().toString() : null,
            filters.get("parentOrganisationDescription") != null ? filters.get(
                "parentOrganisationDescription").getFilterValue().toString() : null,
            filters.get("gardener") != null ? filters.get("gardener").getFilterValue().toString() : null);
        this.setRowCount(rowCount);

        List<OrganisationResult> results = new ArrayList<>();
        if (rowCount > 0) {
            results = this.organisationService.search(
                filters.get("organisationId") != null ? Long.parseLong(
                    filters.get("organisationId").toString()) : null,
                filters.get("description") != null ? filters.get("description").getFilterValue().toString() : null,
                filters.get("department") != null ? filters.get("department").getFilterValue().toString() : null,
                greenhouse,
                filters.get("ipenCode") != null ? filters.get("ipenCode").getFilterValue().toString() : null,
                filters.get("parentOrganisationDescription") != null ? filters.get(
                    "parentOrganisationDescription").getFilterValue().toString() : null,
                filters.get("gardener") != null ? filters.get("gardener").getFilterValue().toString() : null, first,
                pageSize
            );
        }

        return results;
    }

}
