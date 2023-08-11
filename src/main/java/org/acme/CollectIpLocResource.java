/* TODO: en-tête à ajouter
* */

package org.acme;

import com.google.common.net.InetAddresses;
import io.servicetalk.http.api.StreamingHttpRequest;
import io.servicetalk.transport.api.ConnectionContext;
import java.net.InetSocketAddress;
import java.util.Optional;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.*;

import org.locationtech.jts.geom.Coordinate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
@javax.ws.rs.Path("/")
public class CollectIpLocResource {
    private static final Logger logger = LoggerFactory.getLogger(CollectIpLocResource.class);

    private final CollectIpLocRepository collectIpLocRepository;

    @Inject
    public CollectIpLocResource(CollectIpLocRepository collectIpLocRepository) {
        this.collectIpLocRepository = collectIpLocRepository;
    }


    @GET
    @javax.ws.rs.Path("/api/collectiploc")
    public Response collectiploc(
            @Context ConnectionContext context,
            @Context StreamingHttpRequest request,
            @QueryParam("longitude") double longitude,
            @QueryParam("latitude") double latitude,
            @QueryParam("altitude") double altitude,
            @QueryParam("accuracy") double accuracy,
            @QueryParam("altitudeAccuracy") double altitudeAccuracy,
            @QueryParam("heading") double heading,
            @QueryParam("speed") double speed,
            @QueryParam("ip") String ip) {
        var address = InetAddresses.forString(
                Optional.ofNullable((CharSequence) ip)
                        .or(() -> Optional.ofNullable(request.headers().get("X-Forwarded-For")))
                        .or(() -> Optional.ofNullable(request.headers().get("X-Real-IP")))
                        .orElse(((InetSocketAddress) context.remoteAddress()).getAddress().getHostAddress())
                        .toString().split(",")[0].trim());
        collectIpLocRepository.save(new Coordinate(longitude, latitude, altitude), accuracy, altitudeAccuracy, heading, speed, String.valueOf(address));

        // To delete
        System.out.println("Longitude : " + longitude + ", latitude : " + latitude + "\nAltitude : " + altitude + "\nAccuracy : " + accuracy + "\nAltitudeAccuracy : " + altitudeAccuracy + "\nHeading : " + heading + "\nSpeed : " + speed + "\nIP : " + address + "\n");

        return Response.ok().build();
    }
}
