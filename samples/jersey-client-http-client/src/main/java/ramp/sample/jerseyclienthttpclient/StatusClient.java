/**
 * 
 */
package ramp.sample.jerseyclienthttpclient;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.glassfish.jersey.SslConfigurator;
import org.glassfish.jersey.apache.connector.ApacheClientProperties;
import org.glassfish.jersey.apache.connector.ApacheConnectorProvider;
import org.glassfish.jersey.client.ClientConfig;

/**
 * @author Rama Palaniappan
 * @since Apr 29, 2014
 */
public class StatusClient {
	private void restCall() {
		String restURL = "http://localhost:8080/billComparing/api/v1/payments/29/status";
		// Client client = ClientBuilder.newClient();
		String payloadString = "{ \"id\": \"1001\", \"name\": \"CONNECTING\", \"isTerminal\": false }";
		Client client = getHttpClient();
		WebTarget webTarget = client.target(restURL);
		
		String result = webTarget.request(MediaType.APPLICATION_JSON).header("access_token", "1050273781").put(Entity.json(payloadString), String.class);
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
		new StatusClient().restCall();
	}

}
