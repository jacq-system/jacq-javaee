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
package org.jacq.input.controller;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.jacq.common.model.TreeRecordFileResult;
import org.jacq.common.rest.TreeRecordFileService;
import org.jacq.input.util.ServicesUtil;

/**
 *
 * @author fhafner
 */
@ManagedBean
@ViewScoped
public class TreeRecordFileEditController {

    protected Long treeRecordFileId;

    protected TreeRecordFileResult treeRecordFile;

    protected TreeRecordFileService treeRecordFileService;

    @PostConstruct
    public void init() {

        this.treeRecordFileService = ServicesUtil.getTreeRecordFileService();

        this.treeRecordFile = new TreeRecordFileResult();

    }

    public Long getTreeRecordFileId() {
        return treeRecordFileId;
    }

    public void setTreeRecordFileId(Long treeRecordFileId) {
        this.treeRecordFileId = treeRecordFileId;
    }

    public TreeRecordFileResult getTreeRecordFile() {
        return treeRecordFile;
    }

    public String edit() {
        this.treeRecordFile = this.treeRecordFileService.save(this.treeRecordFile);

        return null;
    }

}
