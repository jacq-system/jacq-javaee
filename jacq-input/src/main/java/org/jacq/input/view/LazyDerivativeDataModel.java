/*
 * Copyright 2017 wkoller.
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
import org.apache.commons.lang3.StringUtils;
import org.jacq.common.model.jpa.custom.BotanicalObjectDerivative;
import org.jacq.common.model.rest.OrderDirection;
import org.jacq.common.rest.DerivativeService;
import org.jacq.input.controller.LivingPlantController;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 * Lazy loading data model for derivative searches
 *
 * @author wkoller
 */
public class LazyDerivativeDataModel extends LazyDataModel<BotanicalObjectDerivative> {

    public static final String FILTER_ID = "id";
    public static final String FILTER_TYPE = "type";
    public static final String FILTER_TYPE_EMPTY = "null";

    /**
     * Reference to derivative service which is used during querying
     */
    protected DerivativeService derivativeService;

    /**
     * Internal storage of result list
     */
    protected List<BotanicalObjectDerivative> derivativeResults = new ArrayList<>();

    /**
     * Search criteria
     */
    protected String placeNumber;
    protected String accessionNumber;
    protected int separated = 0;
    protected Long scientificNameId = null;
    protected Long organisationId = null;

    /**
     * Default constructor, needs a reference to the derivative service for later querying
     *
     * @param derivativeService
     */
    public LazyDerivativeDataModel(DerivativeService derivativeService) {
        this.derivativeService = derivativeService;
    }

    @Override
    public BotanicalObjectDerivative getRowData(String rowKey) {
        Long rowKeyLong = Long.valueOf(rowKey);

        for (BotanicalObjectDerivative derivativeResult : this.derivativeResults) {
            if (rowKeyLong.equals(derivativeResult.getDerivativeId())) {
                return derivativeResult;
            }
        }

        return null;
    }

    @Override
    public Object getRowKey(BotanicalObjectDerivative derivativeResult) {
        return derivativeResult.getDerivativeId();
    }

    @Override
    public List<BotanicalObjectDerivative> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        // try to parse the id filter
        Long id = (filters.get(FILTER_ID) == null) ? null : Long.valueOf(String.valueOf(filters.get(FILTER_ID)));
        // convert the separated filter
        Boolean separatedFilter = null;
        if (this.separated == 1) {
            separatedFilter = true;
        }
        else if (this.separated == 2) {
            separatedFilter = false;
        }

        // quote type filter and set to null if empty
        String type = String.valueOf(filters.get(FILTER_TYPE));
        if (StringUtils.isEmpty(type) || FILTER_TYPE_EMPTY.equalsIgnoreCase(type) || LivingPlantController.TYPE_ALL.equalsIgnoreCase(type)) {
            type = null;
        }

        // get count first
        int rowCount = this.derivativeService.count(type, id, placeNumber, accessionNumber, separatedFilter, scientificNameId, organisationId);
        this.setRowCount(rowCount);

        List<BotanicalObjectDerivative> results = new ArrayList<>();
        if (rowCount > 0) {
            results = this.derivativeService.find(type, id, placeNumber, accessionNumber, separatedFilter, scientificNameId, organisationId, sortField, (sortOrder.equals(SortOrder.DESCENDING)) ? OrderDirection.DESC : OrderDirection.ASC, first, pageSize);
        }

        return results;
    }

    public String getPlaceNumber() {
        return placeNumber;
    }

    public void setPlaceNumber(String placeNumber) {
        this.placeNumber = placeNumber;
    }

    public String getAccessionNumber() {
        return accessionNumber;
    }

    public void setAccessionNumber(String accessionNumber) {
        this.accessionNumber = accessionNumber;
    }

    public int getSeparated() {
        return separated;
    }

    public void setSeparated(int separated) {
        this.separated = separated;
    }

    public Long getScientificNameId() {
        return scientificNameId;
    }

    public void setScientificNameId(Long scientificNameId) {
        this.scientificNameId = scientificNameId;
    }

    public Long getOrganisationId() {
        return organisationId;
    }

    public void setOrganisationId(Long organisationId) {
        this.organisationId = organisationId;
    }
}