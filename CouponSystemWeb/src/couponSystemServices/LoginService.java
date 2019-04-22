package couponSystemServices;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import system.core.MainUse.CouponSystem;
import system.core.facade.AdminFacade;
import system.core.facade.CompanyFacade;
import system.core.facade.CouponClientFacade;
import system.core.facade.CustomerFacade;
import system.core.javabeans.ClientType;
import system.core.javabeans.Company;

@Path("/loginpage")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
// @Consumes(MediaType.APPLICATION_JSON)
public class LoginService {
	@Context
	private HttpServletRequest request;
	private CouponClientFacade couponFacade;

	@POST
	@Path("/login")
	public Response login(Credentials credentials) throws Exception {
		HttpSession session = request.getSession();
		System.out.println(credentials);
		CouponSystem cs = CouponSystem.getInstance();

		switch (credentials.getClientType()) {
		case COMPANY:
			couponFacade = (CompanyFacade) cs.login(credentials.getName(), credentials.getPassword(),
					ClientType.COMPANY);
			CompanyFacade companyFacade = new CompanyFacade();
			session.setAttribute("companyFacade", companyFacade);
			break;

		case CUSTOMER:
			couponFacade = (CustomerFacade) cs.login(credentials.getName(), credentials.getPassword(),
					ClientType.CUSTOMER);
			CustomerFacade customerFacade = new CustomerFacade();
			session.setAttribute("customerFacade", customerFacade);
			break;

		case ADMIN:
			couponFacade = (AdminFacade) cs.login("admin", "1234", ClientType.ADMIN);
			AdminFacade adminFacade = new AdminFacade();
			session.setAttribute("adminFacade", adminFacade);
			break;
		}

		session.setAttribute("couponFacade", couponFacade);
		return Response.status(Status.OK).build();
	}
}