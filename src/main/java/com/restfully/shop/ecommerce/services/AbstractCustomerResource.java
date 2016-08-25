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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;

import com.restfully.shop.ecommerce.domain.Customer;

/**
 * This is an abstract class to allow for different implementations of create and get customer
 *
 * @author Russell L Cardon
 */
public abstract class AbstractCustomerResource {

  @POST
  @Consumes({MediaType.APPLICATION_XML})
  public Response createCustomer(InputStream inputStream) {
    return null;
  }

  @GET
  @Path("{id}")
  public StreamingOutput getCustomer(@PathParam("id") int id) {
    return null;
  }

  @PUT
  @Path("{id}")
  @Consumes({MediaType.APPLICATION_XML})
  public void updateCustomer(@PathParam("id") int id, InputStream inputStream) {

  }

  abstract protected void outputCustomer(OutputStream outputStream, Customer customer) throws IOException;
  abstract protected Customer readCustomer(InputStream inputStream);

}
