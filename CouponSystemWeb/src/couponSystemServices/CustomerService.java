package couponSystemServices;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import system.core.exceptions.CouponSystemException;
import system.core.facade.CustomerFacade;
import system.core.javabeans.Coupon;
import system.core.javabeans.CouponType;

@Path("customer")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CustomerService {
	@Context
	HttpServletRequest request;

	@POST
	@Path("customer")
	public void purchaseCoupon(Coupon coupon) throws CouponSystemException {
		HttpSession session = request.getSession(false);
		CustomerFacade customerFacade = (CustomerFacade) session.getAttribute("customerFacade");
		try {
			customerFacade.purchaseCoupon(coupon);
		} catch (Exception e) {
			throw new CouponSystemWebExeption("Error while purchasing coupon : " + coupon.getTitle());
		}
	}

	@GET
	@Path("/coupons")
	public Collection<Coupon> getAllPurchasedCoupons() throws CouponSystemException {
		HttpSession session = request.getSession(false);
		CustomerFacade customerFacade = (CustomerFacade) session.getAttribute("customerFacade");
		Collection<Coupon> coupons = new ArrayList<>();
		try {
			coupons = customerFacade.getAllCouponsPurchased(null);
		} catch (Exception e) {
			throw new CouponSystemWebExeption("Error while getting all Purchased coupons");
		}
		return coupons;
	}

	@GET
	@Path("/couponsType/{type}")
	public Collection<Coupon> getallPurchasedCouponsByType(@PathParam("type") CouponType type)
			throws CouponSystemException {
		HttpSession session = request.getSession(false);
		CustomerFacade customerFacade = (CustomerFacade) session.getAttribute("customerFacade");
		Collection<Coupon> coupons = new ArrayList<>();
		try {
			coupons = customerFacade.getAllPurchasedCouponsByType(type);
		} catch (Exception e) {
			throw new CouponSystemWebExeption("Error while getting Coupons by Type");
		}
		return coupons;
	}

	@GET
	@Path("/coupons/{price}")
	public Collection<Coupon> getAllPurchasedCouponsByPrice(@PathParam("price") double price)
			throws CouponSystemException {
		HttpSession session = request.getSession(false);
		CustomerFacade customerFacade = (CustomerFacade) session.getAttribute("customerFacade");
		Collection<Coupon> coupons = new ArrayList<>();
		try {
			coupons = customerFacade.getAllPurchasedCouponsByPrice(price);
		} catch (Exception e) {
			throw new CouponSystemWebExeption("Error while getting Coupons by Price");
		}
		return coupons;
	}
}
