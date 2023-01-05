package si.fri.rso.admin.api.v1.resources;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.headers.Header;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import si.fri.rso.admin.lib.Admin;
import com.kumuluz.ee.cors.annotations.CrossOrigin;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;
import java.util.logging.Logger;

import com.kumuluz.ee.logs.cdi.Log;
import si.fri.rso.admin.services.beans.AdminBean;

@ApplicationScoped
@Path("/admin")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@CrossOrigin(allowOrigin = "*")
@Log
public class AdminResource {

    private Logger log = Logger.getLogger(AdminResource.class.getName());

    @Inject
    private si.fri.rso.admin.services.beans.AdminBean AdminBean;

    @Context
    protected UriInfo uriInfo;

    @Operation(description = "Get all notifications.", summary = "Get all metadata")
    @APIResponses({
            @APIResponse(responseCode = "200",
                    description = "List of notifications",
                    content = @Content(schema = @Schema(implementation = Admin.class, type = SchemaType.ARRAY)),
                    headers = {@Header(name = "X-Total-Count", description = "Number of objects in list")}
            )})
    @GET
    public Response getNotification() {

        List<Admin> notification = AdminBean.getAdminFilter(uriInfo);

        return Response.status(Response.Status.OK).entity(notification).build();
    }


    @Operation(description = "Get metadata for an image.", summary = "Get metadata for an image")
    @APIResponses({
            @APIResponse(responseCode = "200",
                    description = "Image metadata",
                    content = @Content(
                            schema = @Schema(implementation = Admin.class))
            )})
    @GET
    @Path("/{adminId}")
    public Response getAdmin(@Parameter(description = "Notification ID.", required = true)
                                     @PathParam("adminId") Integer adminId) {

        Admin notification = AdminBean.getAdmin(adminId);

        if (notification == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.status(Response.Status.OK).entity(notification).build();
    }

    @Operation(description = "Add notification.", summary = "Add metadata")
    @APIResponses({
            @APIResponse(responseCode = "201",
                    description = "Notification successfully added."
            ),
            @APIResponse(responseCode = "405", description = "Validation error .")
    })
    @POST
    public Response createAdmin(@RequestBody(
            description = "DTO object with image metadata.",
            required = true, content = @Content(
            schema = @Schema(implementation = Admin.class))) Admin notification) {

        if ((notification.getName() == null || notification.getSurname() == null )) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        else {
            notification = AdminBean.createAdmin(notification);
        }

        return Response.status(Response.Status.CONFLICT).entity(notification).build();

    }


    @Operation(description = "Update notification.", summary = "Update metadata")
    @APIResponses({
            @APIResponse(
                    responseCode = "200",
                    description = "Notification successfully updated."
            )
    })
    @PUT
    @Path("{notificationId}")
    public Response putAdmin(@Parameter(description = "Notification ID.", required = true)
                                     @PathParam("notificationId") Integer notificationId,
                                     @RequestBody(
                                             description = "DTO object with image metadata.",
                                             required = true, content = @Content(
                                             schema = @Schema(implementation = Admin.class)))
                                     Admin notification){

        notification = AdminBean.putAdmin(notificationId, notification);

        if (notification == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.status(Response.Status.NOT_MODIFIED).build();

    }

    @Operation(description = "Delete metadata for an image.", summary = "Delete metadata")
    @APIResponses({
            @APIResponse(
                    responseCode = "200",
                    description = "Metadata successfully deleted."
            ),
            @APIResponse(
                    responseCode = "404",
                    description = "Not found."
            )
    })
    @DELETE
    @Path("{adminId}")
    public Response deleteAdmin(@Parameter(description = "Admin ID.", required = true)
                                        @PathParam("adminId") Integer adminId){

        boolean deleted = AdminBean.deleteAdmin(adminId);

        if (deleted) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }





}
