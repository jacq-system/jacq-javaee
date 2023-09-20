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
package org.jacq.common.rest;

import java.util.List;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import org.jacq.common.model.rest.IndexSeminumDownloadResult;
import org.jacq.common.model.rest.IndexSeminumResult;
import org.jacq.common.model.rest.IndexSeminumTypeResult;

/**
 *
 * @author fhafner
 */
@Path("/indexSeminum")
public interface IndexSeminumService {

    /**
     *
     * @param indexSeminumResult
     * @return
     */
    @POST
    @Path("/save")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public IndexSeminumResult save(IndexSeminumResult indexSeminumResult);

    /**
     * Returns a list of all available index seminum types
     *
     * @return
     */
    @GET
    @Path("/type/findAll")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<IndexSeminumTypeResult> typeFindAll();

    /**
     * Search the database using the given filter
     *
     *
     * @param offset Return result with an offset
     * @param limit Limit total count of results
     * @return
     */
    @GET
    @Path("/search")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<IndexSeminumResult> search(@QueryParam("offset") Integer offset, @QueryParam("limit") Integer limit);

    /**
     * Search the database using the given filter and return the count
     *
     * @see OrganisationService#search()
     */
    @GET
    @Path("/searchCount")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public int searchCount();

    /**
     * Search the database using the given filter
     *
     *
     * @param indexSeminumRevisionId
     * @param offset Return result with an offset
     * @param limit Limit total count of results
     * @return
     */
    @GET
    @Path("/searchContent")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<IndexSeminumDownloadResult> searchContent(@QueryParam("indexSeminumRevisionId") Long indexSeminumRevisionId, @QueryParam("offset") Integer offset, @QueryParam("limit") Integer limit);

    /**
     *
     * @param indexSeminumRevisionId
     * @return
     */
    @GET
    @Path("/searchCountContent")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public int searchCountContent(@QueryParam("indexSeminumRevisionId") Long indexSeminumRevisionId);

}
