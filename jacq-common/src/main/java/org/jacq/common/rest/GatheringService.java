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
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import org.jacq.common.model.rest.LocationResult;

/**
 *
 * @author wkoller
 */
@Path("/gathering")
public interface GatheringService {

    /**
     * Search location entries, taking into account geonames services
     *
     * @param location
     * @param offset
     * @param count
     * @return
     */
    @Path("/location/find")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<LocationResult> locationFind(@QueryParam("location") String location, @QueryParam("offset") Integer offset, @QueryParam("count") Integer count);

    /**
     * Load a single location entry
     *
     * @param locationId
     * @return
     */
    @Path("/location/load")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public LocationResult locationLoad(@QueryParam("locationId") Long locationId);
}
