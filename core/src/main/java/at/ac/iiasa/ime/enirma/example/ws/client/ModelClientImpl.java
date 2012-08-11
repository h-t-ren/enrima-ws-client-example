package at.ac.iiasa.ime.enirma.example.ws.client;

import java.io.IOException;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.dom.DOMResult;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ws.security.WSConstants;
import org.apache.ws.security.message.WSSecHeader;
import org.apache.ws.security.message.WSSecUsernameToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.XmlMappingException;
import org.springframework.ws.WebServiceMessage;
import org.springframework.ws.client.core.WebServiceMessageCallback;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.soap.SoapHeader;
import org.springframework.ws.soap.SoapMessage;
import org.springframework.xml.transform.StringResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import at.ac.iiasa.ime.enrima.ws.example.oxm.GetModelDescriptionRequest;
import at.ac.iiasa.ime.enrima.ws.example.oxm.GetModelDescriptionResponse;
import at.ac.iiasa.ime.enrima.ws.example.oxm.GetModelListResponse;
import at.ac.iiasa.ime.enrima.ws.example.oxm.Model;
import at.ac.iiasa.ime.enrima.ws.example.oxm.ObjectFactory;
import at.ac.iiasa.ime.enrima.ws.example.oxm.SaveModelDescriptionRequest;
import at.ac.iiasa.ime.enrima.ws.example.oxm.SaveModelDescriptionResponse;

public class ModelClientImpl implements ModelClient  {
	
	
    @Autowired private WebServiceTemplate webServiceTemplate;
    protected final Log logger = LogFactory.getLog(getClass());
	
    public List<Model> getAll()
    {

    	    GetModelListResponse response =(GetModelListResponse)webServiceTemplate.marshalSendAndReceive(objectFactory.createGetModelListRequest(null), new WebServiceMessageCallback() {

    	     public void doWithMessage(WebServiceMessage webServiceMessage) {
    		try {
    		  SoapMessage soapMessage = (SoapMessage) webServiceMessage;
    		  Document doc = soapMessage.getDocument();
    			WSSecUsernameToken usernametoken = new WSSecUsernameToken();
    			usernametoken.setPasswordType(WSConstants.PASSWORD_DIGEST);
    			usernametoken.setUserInfo("hongtao", "05ac3b503f3f028e470599f3f9907609");
    			WSSecHeader secHeader = new WSSecHeader("", true);
    			secHeader.insertSecurityHeader(doc);
    			usernametoken.build(doc, secHeader);
    			soapMessage.setDocument(doc);
		
    		} 
    	             catch (Exception e) {
    	               new RuntimeException(e);
    		}
    	     }

    	    });


    	logger.debug(toXMLString(response));
    	return response.getModel();
    }

	public Model getModelById(int idModel) {
		GetModelDescriptionRequest request = objectFactory.createGetModelDescriptionRequest();
		request.setIdModel(idModel);
		GetModelDescriptionResponse response = (GetModelDescriptionResponse)webServiceTemplate.marshalSendAndReceive(request);
		logger.debug(toXMLString(response));
		return response.getModel();
	}


	public void save(Model model) {
		SaveModelDescriptionRequest request = objectFactory.createSaveModelDescriptionRequest();
		request.setModel(model);
		SaveModelDescriptionResponse response = (SaveModelDescriptionResponse)webServiceTemplate.marshalSendAndReceive(request);
		logger.debug(toXMLString(response));
		
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




