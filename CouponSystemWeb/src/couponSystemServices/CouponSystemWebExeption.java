package couponSystemServices;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class CouponSystemWebExeption extends WebApplicationException {

	public CouponSystemWebExeption(String message, Response.Status stat) {
		super(Response.status(stat).entity(message).type(MediaType.TEXT_PLAIN).build());
	}

	public CouponSystemWebExeption(String message) {
		super(Response.status(Response.Status.CONFLICT).entity(message).type(MediaType.TEXT_PLAIN).build());
	}

	@Override
	public Response getResponse() {
		return super.getResponse();
	}

}
