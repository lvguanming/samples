/**
 * 
 */
package ramp.sample.jerseyclienthttpclient;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.glassfish.jersey.SslConfigurator;
import org.glassfish.jersey.apache.connector.ApacheClientProperties;
import org.glassfish.jersey.apache.connector.ApacheConnector;
import org.glassfish.jersey.apache.connector.ApacheConnectorProvider;
import org.glassfish.jersey.client.ClientConfig;

/**
 * @author Rama Palaniappan
 * @since Apr 16, 2014
 */
public class CustomerClient {
	// HttpClient apacheClient = HttpClientBuilder.create().build();
	// Client client = new Client(new ApacheHttpClient4Handler(apacheClient,
	// new BasicCookieStore(),
	// true));
	// WebResource webResource =
	// client.resource("http://www.thomas-bayer.com/sqlrest/CUSTOMER/3/");
	// ClientResponse response = webResource.accept("application/json")
	// .get(ClientResponse.class);

	private void restCall() {
		String restURL = "http://www.thomas-bayer.com/sqlrest";
		// Client client = ClientBuilder.newClient();
		Client client = getHttpClient();
		WebTarget webTarget = client.target(restURL).path("/CUSTOMER/3");
		String result = webTarget.request().get(String.class);
		System.out.println(result);
	}

	private Client getUrlClient() {
		return ClientBuilder.newClient();
	}

	private Client getHttpClient() {
		ClientConfig clientConfig = new ClientConfig();
		clientConfig.property(ApacheClientProperties.CONNECTION_MANAGER,
				new PoolingHttpClientConnectionManager());
		// config your ssl for apache connector
		SslConfigurator sslConfig = SslConfigurator.newInstance();
		clientConfig.property(ApacheClientProperties.SSL_CONFIG, sslConfig);
		clientConfig.connectorProvider( new ApacheConnectorProvider());
		
		return ClientBuilder.newBuilder().withConfig(clientConfig).build();
	}

	public static void main(String[] args) {
		new CustomerClient().restCall();
	}
}
