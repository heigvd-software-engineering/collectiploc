package org.acme;

import io.vertx.core.http.HttpServerRequest;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;

@Singleton
@Path("/")
public class LocationResource {

    @Inject
    EntityManager em;

    @POST
    @Path("/location")
    @Transactional
    public Response collectiploc(
            @Context HttpServerRequest request,
            @FormParam("longitude") double longitude,
            @FormParam("latitude") double latitude,
            @FormParam("altitude") double altitude,
            @FormParam("accuracy") double accuracy,
            @FormParam("altitudeAccuracy") double altitudeAccuracy,
            @FormParam("heading") double heading,
            @FormParam("speed") double speed,
            @FormParam("email") String email) {
        var headers = request.headers();
        var address = request.connection().remoteAddress().hostAddress();
        var userData = new LocationEntity(longitude, latitude, altitude, accuracy, altitudeAccuracy, heading, speed, email, address, headers.toString(), new java.util.Date(), "macAddress", "Web", "cookie");

        System.out.println(userData);

        em.persist(userData);
        return Response.ok().build();
    }
}
