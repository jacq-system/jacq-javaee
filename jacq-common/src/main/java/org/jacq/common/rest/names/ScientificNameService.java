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
package org.jacq.common.rest.names;

import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.jacq.common.model.rest.ScientificNameResult;

/**
 * Service interface providing methods for access to scientific name searching
 *
 * @author wkoller
 */
@Path("/scientificName")
public interface ScientificNameService {

    /**
     * Search for a scientific name, search string will be split and quoted for autocompleting
     *
     * @param search String to search for - no wildcards are allowed
     * @param autocomplete If set to true, search will return matches with wildcards (i.e. autocomplete) as well
     * @return
     */
    @GET
    @Path("/find")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<ScientificNameResult> find(@QueryParam("search") String search, @QueryParam("autocomplete") Boolean autocomplete);

    /**
     * Load a single scientific name id entry
     *
     * @param scientificNameId
     * @return
     */
    @GET
    @Path("/load")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ScientificNameResult load(@QueryParam("scientificNameId") Long scientificNameId);
}
