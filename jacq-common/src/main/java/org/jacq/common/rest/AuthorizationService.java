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

import java.util.Date;
import java.util.List;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import org.jacq.common.model.rest.AccessOrganisationResult;

/**
 *
 * @author wkoller
 */
@Path("/authorization")
public interface AuthorizationService {

    /**
     *
     * @param id
     * @param username
     * @param birthdate
     * @param userType
     * @param employmentType
     * @param allowDeny
     * @param userId
     * @param accessOrganisationId
     * @param organisationDescription
     * @param organisationId
     * @param offset
     * @param limit
     * @return
     */
    @GET
    @Path("/search")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<AccessOrganisationResult> search(@QueryParam("id") Long id, @QueryParam("username") String username, @QueryParam("birthdate") Date birthdate, @QueryParam("userType") String userType, @QueryParam("employmentType") String employmentType, @QueryParam("organisationDescription") String organisationDescription, @QueryParam("accessOrganisationId") Long accessOrganisationId, @QueryParam("allowDeny") Boolean allowDeny, @QueryParam("userId") Long userId, @QueryParam("organisationId") Long organisationId, @QueryParam("offset") Integer offset, @QueryParam("limit") Integer limit);

    /**
     *
     *
     * @param id
     * @param username
     * @param birthdate
     * @param allowDeny
     * @param employmentType
     * @param userType
     * @param accessOrganisationId
     * @param userId
     * @param organisationDescription
     * @param organisationId
     * @return
     */
    @GET
    @Path("/searchCount")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public int searchCount(@QueryParam("id") Long id, @QueryParam("username") String username, @QueryParam("birthdate") Date birthdate, @QueryParam("userType") String userType, @QueryParam("employmentType") String employmentType, @QueryParam("organisationDescription") String organisationDescription, @QueryParam("accessOrganisationId") Long accessOrganisationId, @QueryParam("allowDeny") Boolean allowDeny, @QueryParam("userId") Long userId, @QueryParam("organisationId") Long organisationId);

    /**
     *      *
     * @param accessOrganisationResult
     * @return
     */
    @POST
    @Path("/save")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public AccessOrganisationResult save(AccessOrganisationResult accessOrganisationResult);
}
