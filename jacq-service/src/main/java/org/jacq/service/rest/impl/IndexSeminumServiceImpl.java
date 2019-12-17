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
package org.jacq.service.rest.impl;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;
import org.jacq.common.model.rest.IndexSeminumDownloadResult;
import org.jacq.common.model.rest.IndexSeminumResult;
import org.jacq.common.model.rest.IndexSeminumTypeResult;
import org.jacq.common.rest.IndexSeminumService;
import org.jacq.service.manager.IndexSeminumManager;

/**
 *
 * @author fhafner
 */
public class IndexSeminumServiceImpl implements IndexSeminumService {

    private static final Logger LOGGER = Logger.getLogger(IndexSeminumServiceImpl.class.getName());

    @Inject
    protected IndexSeminumManager indexSeminumManager;

    @Override
    public IndexSeminumResult save(IndexSeminumResult indexSeminumResult) {
        try {
            return indexSeminumManager.save(indexSeminumResult);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);

            throw new WebApplicationException(e.getMessage());
        }
    }

    /**
     * @see IndexSeminumService#typeFindAll()
     */
    @Override
    public List<IndexSeminumTypeResult> typeFindAll() {
        return indexSeminumManager.typeFindAll();
    }

    @Override
    public List<IndexSeminumResult> search(Integer offset, Integer limit) {
        return indexSeminumManager.search(offset, limit);
    }

    @Override
    public int searchCount() {
        return indexSeminumManager.searchCount();
    }

    @Override
    public List<IndexSeminumDownloadResult> searchContent(Long indexSeminumRevisionId, Integer offset, Integer limit) {
        return indexSeminumManager.searchContent(indexSeminumRevisionId, offset, limit);
    }

    @Override
    public int searchCountContent(Long indexSeminumRevisionId) {
        return indexSeminumManager.searchCountContent(indexSeminumRevisionId);
    }

}
