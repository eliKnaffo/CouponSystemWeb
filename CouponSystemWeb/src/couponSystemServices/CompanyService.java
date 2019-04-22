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

import system.core.facade.CompanyFacade;
import system.core.javabeans.Coupon;
import system.core.javabeans.CouponType;

@Path("company")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CompanyService {
	@Context
	private HttpServletRequest requst;

	@POST
	@Path("crtCoupon")
	public Response createCoupon(Coupon coupon) {
		HttpSession session = requst.getSession(false);
		CompanyFacade companyFacade = (CompanyFacade) session.getAttribute("companyFacade");
		try {
			companyFacade.createCoupon(coupon);
			return Response.status(Status.CREATED).type(MediaType.APPLICATION_JSON).build();
		} catch (Exception e) {
			throw new CouponSystemWebExeption("Error while creating Coupon : " + coupon.getTitle());
		}
	}

	@DELETE
	@Path("rmvCoupon")
	public Response removeCoupon(Coupon coupon) {
		HttpSession session = requst.getSession(false);
		CompanyFacade companyFacade = (CompanyFacade) session.getAttribute("companyFacade");
		try {
			companyFacade.removeCoupon(coupon);
			return Response.status(Status.NO_CONTENT).build();
		} catch (Exception e) {
			throw new CouponSystemWebExeption("Error while removing Coupon : " + coupon.getTitle());
		}
	}

	@PUT
	@Path("updtCoupon")
	public Response updateCoupon(Coupon coupon) {
		HttpSession session = requst.getSession(false);
		CompanyFacade companyFacade = (CompanyFacade) session.getAttribute("companyFacade");
		try {
			companyFacade.updateCoupon(coupon);
			return Response.status(Status.NO_CONTENT).build();
		} catch (Exception e) {
			throw new CouponSystemWebExeption("Error while updating coupon : " + coupon.getTitle());
		}
	}

	@GET
	@Path("getallcoupons")
	public Response getAllCoupons() {
		HttpSession session = requst.getSession(false);
		CompanyFacade companyFacade = (CompanyFacade) session.getAttribute("companyFacade");
		Collection<Coupon> coupons = new ArrayList<>();
		String cuponsInString = "";
		try {
			coupons = companyFacade.getAllCoupons();
			ObjectMapper mapper = new ObjectMapper();
			cuponsInString = mapper.writeValueAsString(coupons);
			return Response.status(Status.OK).entity(cuponsInString).build();
		} catch (Exception e) {
			throw new CouponSystemWebExeption("Error while getting all Coupons");
		}
	}

	@GET
	@Path("typecoupons/{type}")
	public Response getCouponByType(@PathParam("type") CouponType couponType) {
		HttpSession session = requst.getSession(false);
		CompanyFacade companyFacade = (CompanyFacade) session.getAttribute("companyFacade");
		Collection<Coupon> coupons = new ArrayList<>();
		String couponsTypeString = "";
		try {
			coupons = companyFacade.getCouponByType(couponType);
			ObjectMapper mapper = new ObjectMapper();
			couponsTypeString = mapper.writeValueAsString(coupons);
			return Response.status(Status.OK).entity(couponsTypeString).build();
		} catch (Exception e) {
			throw new CouponSystemWebExeption("Error while getting Coupon by Type");
		}
	}
}