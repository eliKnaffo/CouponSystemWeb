package couponSystemServices;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import system.core.javabeans.ClientType;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class Credentials {

	public String name;
	public String password;
	public ClientType clientType;

	public Credentials() {
		super();
	}

	public Credentials(String name, String password, ClientType clientType) {
		super();
		this.name = name;
		this.password = password;
		this.clientType = clientType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public ClientType getClientType() {
		return clientType;
	}

	public void setClientType(ClientType clientType) {
		this.clientType = clientType;
	}

	@Override
	public String toString() {
		return "Credentials [name=" + name + ", password=" + password + ", clientType=" + clientType + "]";
	}

}
