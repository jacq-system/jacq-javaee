package org.jacq.common.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jacq.common.model.jpa.TblTaxClassification;

@Path("/classification")
public interface ClassificationService {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/test")
    public String test();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/topLevelEntries")
    public List<TblTaxClassification> getTopLevelEntries();
}