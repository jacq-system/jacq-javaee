package org.jacq.common.rest;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

/**
 * UUID Service interface for handling unique identifiers across the jacq system
 *
 * @author wkoller
 */
@Path("/uuid")
public interface UuidService {

    /**
     * Resolves a given UUID into the corresponding URL
     *
     * @param uuid
     * @return
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/resolve/{uuid}")
    public String resolve(@PathParam("uuid") String uuid);

    /**
     * Mints an UUID for the given type / internal-id, if already minted returns
     * the existing identifier
     *
     * @param type
     * @param internal_id
     * @return UUID of the given type / internal-id
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/mint/{type}/{internal_id}")
    public String mint(@PathParam("type") String type, @PathParam("internal_id") int internal_id);
}
