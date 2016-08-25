package com.restfully.shop.ecommerce.rest;


import org.w3c.dom.Element;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import org.w3c.dom.Document;

import com.restfully.shop.ecommerce.domain.Customer;
import org.w3c.dom.NodeList;


/**
 * This is a singleton file to hold state
 *
 * @author Russell Cardon
 */
@Path("/customers")
public class CustomerResource {

  private Map<Integer, Customer> customerDB = new ConcurrentHashMap<Integer, Customer>();
  private AtomicInteger idCounter = new AtomicInteger();

  @POST
  @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
  public Response createCustomer(InputStream is) {
    Customer customer = readCustomer(is);
    customer.setId(idCounter.incrementAndGet());
    customerDB.put(customer.getId(), customer);
    System.out.println("Created Customer " + customer.getId());
    return Response.created(URI.create("/customers/" + customer.getId())).build();
  }

  @GET
  @Path("{id}")
  @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
  public StreamingOutput getCustomer(@PathParam("id") int id) {
    final Customer customer = customerDB.get(id);
    if (customer == null) {
      throw new WebApplicationException(Response.Status.NOT_FOUND);  // sets the http response code to 404 not_found
    }
    return new StreamingOutput() {
      public void write(OutputStream output) throws IOException, WebApplicationException {
        outputCustomer(output, customer);
      }
    };
  }

  @PUT
  @Path("{id}")
  @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
  public void updateCustomer(@PathParam("id") int id, InputStream inputStream) {
    Customer update = readCustomer(inputStream);
    Customer current = customerDB.get(id);
    if (current == null) {
      throw new WebApplicationException(Response.Status.NOT_FOUND);
    }
    current.setFirstName(update.getFirstName());
    current.setLastName(update.getLastName());
    current.setStreet(update.getStreet());
    current.setState(update.getState());
    current.setZip(update.getZip());
    current.setCountry(update.getCountry());
  }

  private void outputCustomer(OutputStream outputStream, Customer customer) throws IOException {
    PrintStream writer = new PrintStream(outputStream);
    writer.println("<customer id=\"" + customer.getId() + "\">");
    writer.println("   <first-name>" + customer.getFirstName() + "</first-name>");
    writer.println("   <last-name>" + customer.getLastName() + "</last-name>");
    writer.println("   <street>" + customer.getStreet() + "</street>");
    writer.println("   <city>" + customer.getCity() + "</city>");
    writer.println("   <state>" + customer.getState() + "</state>");
    writer.println("   <zip>" + customer.getZip() + "</zip>");
    writer.println("   <country>" + customer.getCountry() + "</country>");
    writer.println("</customer>");
  }

  private Customer readCustomer(InputStream inputStream) {
    try {
      DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
      Document document = builder.parse(inputStream);
      Element root = document.getDocumentElement();
      Customer customer = new Customer();
      if (root.getAttribute("id") != null && !root.getAttribute("id").trim().equals("")) {
        customer.setId(Integer.valueOf(root.getAttribute("id")));
      }
      NodeList nodes = root.getChildNodes();
      for (int i = 0; i < nodes.getLength(); i++) {
        Element element = (Element) nodes.item(i);
        if (element.getTagName().equals("first-name")) {
          customer.setFirstName(element.getTextContent());
        }
        else if (element.getTagName().equals("last-name")) {
          customer.setLastName(element.getTextContent());
        }
        else if (element.getTagName().equals("street")) {
          customer.setStreet(element.getTextContent());
        }
        else if (element.getTagName().equals("city")) {
          customer.setCity(element.getTextContent());
        }
        else if (element.getTagName().equals("state")) {
          customer.setState(element.getTextContent());
        }
        else if (element.getTagName().equals("zip")) {
          customer.setZip(element.getTextContent());
        }
        else if (element.getTagName().equals("country")) {
          customer.setCountry(element.getTextContent());
        }
      }
    }
    catch (Exception e) {
      throw new WebApplicationException(e, Response.Status.BAD_REQUEST);
    }
    return new Customer();
  }



}
