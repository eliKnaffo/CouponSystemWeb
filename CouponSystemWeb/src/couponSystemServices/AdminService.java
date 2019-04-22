package couponSystemServices;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.fasterxml.jackson.databind.ObjectMapper;

import system.core.facade.AdminFacade;
import system.core.javabeans.Company;
import system.core.javabeans.Customer;

@Path("admin")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AdminService {
	@Context
	private HttpServletRequest requst;

	@POST
	@Path("companyCrt")
	public Response createCompany(Company company) {
		System.out.println(company);
		HttpSession session = requst.getSession(false);
		AdminFacade adminFacade = (AdminFacade) session.getAttribute("adminFacade");
		try {
			adminFacade.createCompany(company);
			return Response.status(Status.CREATED).type(MediaType.APPLICATION_JSON).build();
		} catch (Exception e) {
			throw new CouponSystemWebExeption("Error while creating company : " + company.getCompName());
		}
	}

	@DELETE
	@Path("mycompany/{id}")
	public Response removeCompany(@PathParam("id") long id) {
		System.out.println("removeCompany");
		HttpSession session = requst.getSession(false);
		AdminFacade adminFacade = (AdminFacade) session.getAttribute("adminFacade");
		try {
			adminFacade.removeCompany(adminFacade.getCompany(id));
			return Response.status(Status.NO_CONTENT).build();
		} catch (Exception e) {
			throw new CouponSystemWebExeption("Error while removing company : " + id);
		}
	}

	@PUT
	@Path("UpdateComp/")
	public Response updateCompany(Company company) {
		System.out.println("UpdateCompany " + company);
		HttpSession session = requst.getSession(false);
		AdminFacade adminFacade = (AdminFacade) session.getAttribute("adminFacade");
		try {
			adminFacade.updateCompany(company);
			return Response.status(Status.NO_CONTENT).build();
		} catch (Exception e) {
			throw new CouponSystemWebExeption("Error while updating company : " + company.getCompName());
		}
	}

	@GET
	@Path("companyGet")
	public Response getAllComapnies() {
		HttpSession session = requst.getSession(false);
		AdminFacade adminFacade = (AdminFacade) session.getAttribute("adminFacade");
		Collection<Company> companies = new ArrayList<>();
		String listInString = "";
		try {
			companies = adminFacade.getAllCompanies();
			ObjectMapper mapper = new ObjectMapper();
			listInString = mapper.writeValueAsString(companies);
			return Response.status(Status.OK).entity(listInString).build();
		} catch (Exception e) {
			throw new CouponSystemWebExeption("Error while getting all Companies");
		}
	}

	@POST
	@Path("CreateCustomer")
	public Response createCustomer(Customer customer) {
		System.out.println(customer);
		HttpSession session = requst.getSession(false);
		AdminFacade adminFacade = (AdminFacade) session.getAttribute("adminFacade");
		try {
			adminFacade.createCustomer(customer);
			return Response.status(Status.CREATED).type(MediaType.APPLICATION_JSON).build();
		} catch (Exception e) {
			throw new CouponSystemWebExeption("Error while creating customer : " + customer.getCustName());
		}
	}

	@DELETE
	@Path("RmvCustomer/{id}")
	public Response removeCustomer(@PathParam("id") long id) {
		HttpSession session = requst.getSession(false);
		AdminFacade adminFacade = (AdminFacade) session.getAttribute("adminFacade");
		try {

			adminFacade.removeCustomer(adminFacade.getCustomer(id));
			return Response.status(Response.Status.NO_CONTENT).build();
		} catch (Exception e) {
			throw new CouponSystemWebExeption("Error while removing customer");
		}
	}

	@PUT
	@Path("UpdtCustomer")
	public Response updateCustomer(Customer customer) {
		HttpSession session = requst.getSession(false);
		AdminFacade adminFacade = (AdminFacade) session.getAttribute("adminFacade");
		try {
			adminFacade.updateCustomer(customer);
			return Response.status(Status.NO_CONTENT).build();
		} catch (Exception e) {
			throw new CouponSystemWebExeption("Error while updating customer : " + customer.getCustName());
		}
	}

	@GET
	@Path("customer")
	public Response getAllCustomer() {
		HttpSession session = requst.getSession(false);
		AdminFacade adminFacade = (AdminFacade) session.getAttribute("adminFacade");
		Collection<Customer> customers = new ArrayList<>();
		String listInString = "";
		try {
			customers = adminFacade.getAllCustomers();
			ObjectMapper mapper = new ObjectMapper();
			listInString = mapper.writeValueAsString(customers);
			return Response.status(Status.OK).entity(listInString).build();
		} catch (Exception e) {
			throw new CouponSystemWebExeption("Error while getting All Customers");
		}
	}
}
