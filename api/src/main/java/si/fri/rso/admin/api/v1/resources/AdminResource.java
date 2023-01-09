package si.fri.rso.admin.api.v1.resources;

import com.kumuluz.ee.configuration.cdi.ConfigBundle;
import com.kumuluz.ee.discovery.annotations.DiscoverService;
import com.kumuluz.ee.logs.LogManager;
import com.kumuluz.ee.logs.Logger;
import com.kumuluz.ee.logs.cdi.Log;
import com.kumuluz.ee.logs.cdi.LogParams;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.json.JSONArray;



import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Optional;
@ConfigBundle("external-api")
@Path("/admin")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class AdminResource {

    //private static final Logger LOG = LogManager.getLogger(NoviceResource.class.getName());

    @Inject
    private si.fri.rso.admin.api.v1.config.AdminProperties adminProperties;
    String DOMAIN_NAME="http://20.105.42.67/";

    @GET
    @Path("/test")
    public void testGet() {
        System.out.println("Calling GET test");

    }



    @Operation(description = "Mail sended..", summary = "Send mail")
    @APIResponses({
            @APIResponse(responseCode = "201",
                    description = "Mail sent."
            ),
            @APIResponse(responseCode = "405", description = "Sending error.")
    })

    @POST
    @Path("/poslji")
    public Response sendSimpleMessage() throws UnirestException {

        Unirest.post("https://api.mailgun.net/v3/" + "sandbox7c83cf3606fb4d18b359896a98b90e01.mailgun.org" + "/messages")
			.basicAuth("api", "67bcadce4fd9ba4d5af8fb92f9b219af-4c2b2223-23bf2ded")
                .queryString("from", "Excited User <enej@polnilnice.com>")
                .queryString("to", "eb0635@student.uni-lj.si")
                .queryString("subject", "hello")
                .queryString("text", "testing")
                .asJson();
        return Response.status(Response.Status.OK).build();
    }




}