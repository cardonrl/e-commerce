package com.restfully.shop.ecommerce.services;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;

import com.restfully.shop.ecommerce.domain.Customer;


/**
 * This is a singleton file to hold state
 *
 * @author Russell Cardon
 */
@Path("/customers")
public class CustomerResource extends AbstractCustomerResource {

  @POST
  @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
  public Response createCustomer(InputStream is){return null;}

  @GET
  @Path("{id}")
  @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
  public StreamingOutput getCustomer(@PathParam("id") int id){return null;}

  @PUT
  @Path("{id}")
  @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
  public void updateCustomer(@PathParam("id") int id, InputStream inputStream){}

  protected void outputCustomer(OutputStream outputStream, Customer customer) throws IOException{}

  protected Customer readCustomer(InputStream inputStream){return null;}
}

