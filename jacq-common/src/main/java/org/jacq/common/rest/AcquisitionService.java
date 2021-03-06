/*
 * Copyright 2018 wkoller.
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
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.jacq.common.model.rest.AcquisitionSourceResult;

/**
 *
 * @author wkoller
 */
@Path("/acquisition")
public interface AcquisitionService {

    /**
     * Search for an aquisition source entry
     *
     * @param name
     * @param offset
     * @param limit
     * @return
     */
    @GET
    @Path("/source/search")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<AcquisitionSourceResult> sourceSearch(@QueryParam("name") String name, @QueryParam("offset") Integer offset, @QueryParam("limit") Integer limit);

    /**
     * Load a single acquisition source entry
     *
     * @param locationId
     * @return
     */
    @Path("/source/load")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public AcquisitionSourceResult sourceLoad(@QueryParam("acquisitionSourceId") Long locationId);
}
