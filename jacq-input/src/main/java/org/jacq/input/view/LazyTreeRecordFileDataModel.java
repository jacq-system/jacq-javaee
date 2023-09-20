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
package org.jacq.input.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.jacq.common.model.rest.TreeRecordFileResult;
import org.jacq.common.rest.TreeRecordFileService;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;

/**
 * Lazy loading data model for botanical object searches
 *
 * @author fhafner
 */
public class LazyTreeRecordFileDataModel extends LazyDataModel<TreeRecordFileResult> {

    /**
     * Reference to organisation service which is used during querying
     */
    protected TreeRecordFileService treeRecordFileService;

    /**
     * Internal storage of result list
     */
    protected List<TreeRecordFileResult> treeRecordFileResult = new ArrayList<>();

    /**
     * Default constructor, needs a reference to the botanical object service
     * for later querying
     *
     * @param organisationService
     */
    public LazyTreeRecordFileDataModel(TreeRecordFileService treeRecordFileService) {
        this.treeRecordFileService = treeRecordFileService;
    }

    @Override
    public TreeRecordFileResult getRowData(String rowKey) {
        Long rowKeyLong = Long.valueOf(rowKey);

        for (TreeRecordFileResult treeRecordFileResult : this.treeRecordFileResult) {
            if (treeRecordFileResult.getTreeRecordFileId().equals(rowKeyLong)) {
                return treeRecordFileResult;
            }
        }

        return null;
    }

    @Override
    public String getRowKey(TreeRecordFileResult treeRecordFileResult) {
        return treeRecordFileResult.getTreeRecordFileId().toString();
    }

    @Override
    public int count(Map<String, FilterMeta> filters) {
        return this.getRowCount();
    }

    @Override
    public List<TreeRecordFileResult> load(int first, int pageSize,
        Map<String, SortMeta> sortFields,
        Map<String, FilterMeta> filters) {

        // get count first
        int rowCount = this.treeRecordFileService.searchCount(
            filters.get("treeRecordFileId") != null ? Long.parseLong(
                filters.get("treeRecordFileId").getFilterValue().toString()) : null,
            filters.get("year") != null ? Long.parseLong(filters.get("year").getFilterValue().toString()) : null,
            filters.get("name") != null ? filters.get("name").getFilterValue().toString() : null,
            filters.get("documentNumber") != null ? filters.get("documentNumber").getFilterValue().toString()
                : null);
        this.setRowCount(rowCount);

        List<TreeRecordFileResult> results = new ArrayList<>();
        if (rowCount > 0) {
            results = this.treeRecordFileService.search(
                filters.get("treeRecordFileId") != null ? Long.parseLong(
                    filters.get("treeRecordFileId").getFilterValue().toString()) : null,
                filters.get("year") != null ? Long.parseLong(filters.get("year").getFilterValue().toString()) : null,
                filters.get("name") != null ? filters.get("name").getFilterValue().toString() : null,
                filters.get("documentNumber") != null ? filters.get("documentNumber").getFilterValue().toString()
                    : null, first, pageSize);
        }

        return results;
    }
}
