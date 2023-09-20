/*
 * Copyright 2016 Naturhistorisches Museum Wien.
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
package org.jacq.common.rest.dataimport;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.jacq.common.model.dataimport.ImportFile;

/**
 * Common Names service interface
 *
 * @author wkoller
 */
@Path("/dataimport")
public interface DataImportService {

    /**
     * Outputs OpenRefine compliant metadata information
     *
     * @return Metadata information
     */
    @Path("/")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void dataImport(ImportFile importFile);

    /**
     * Triggers the import of the bromi mdb file
     */
    @Path("/importBromiMdb")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void importBromiMdb();

    /**
     * Triggers import from DB-View
     */
    @Path("/importDbView")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void importDbView();
}
