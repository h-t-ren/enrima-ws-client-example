package at.ac.iiasa.ime.enirma.example.ws.client;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.oxm.XmlMappingException;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.transport.http.CommonsHttpMessageSender;
import org.springframework.xml.transform.StringResult;
import org.apache.ws.security.util.WSSecurityUtil;
import at.ac.iiasa.ime.enrima.ws.example.oxm.ObjectFactory;

public class EnrimaClient {
	
	private WebServiceTemplate webServiceTemplate;
    protected final Log logger = LogFactory.getLog(getClass());

    public WebServiceTemplate getWebServiceTemplate() {
		return this.webServiceTemplate;
	}
	
	public void login(String username,String password)
	{

		logger.debug("u:" + username +", pass: " + password);
		HttpClient client = new HttpClient();
		client.getParams().setAuthenticationPreemptive(true);

		CommonsHttpMessageSender httpMessageSender = new CommonsHttpMessageSender();
		UsernamePasswordCredentials passwordCredentials = new UsernamePasswordCredentials(username, password);
		httpMessageSender.setCredentials(passwordCredentials);
		try {
			httpMessageSender.afterPropertiesSet();
		} catch (Exception e) {

			e.printStackTrace();
		}

		webServiceTemplate.setMessageSender(httpMessageSender);
		webServiceTemplate.afterPropertiesSet();
	}

	
	public void setWebServiceTemplate(WebServiceTemplate webServiceTemplate) {
		this.webServiceTemplate = webServiceTemplate;
	}

	public ObjectFactory objectFactory = new ObjectFactory();
	
	
	public String toXMLString(Object o) {
		StringResult result = new StringResult();
		try {
			webServiceTemplate.getMarshaller().marshal(o, result);
		} catch (XmlMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result.toString();
	}


}
