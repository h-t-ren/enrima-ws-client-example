package at.ac.iiasa.ime.ggi.ws.client.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import at.ac.iiasa.ime.enirma.example.ws.client.ModelClient;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/enrima-ws-client-applicationContext.xml")
public class ModelTest {

	 @Autowired private  ModelClient modelClient;
	 

	 @Test
	 public void getModels(){
		// modelClient.login("hongtao","hongtao");
		 modelClient.getAll();
		// modelClient.getModelById(60);
	 }
	 /*
	 @Test
	 public void getModel(){
		 modelClient.getModelById(38);
	 }
	
	 @Test
	 public void saveModel(){
		 Model model = modelClient.getModelById(38);
		 model.setName("Test-R");
		 modelClient.save(model);
	 }
	 
	 @Test
	 public void saveXmlBasedModel()
	 {
		ApplicationContext appContext = new ClassPathXmlApplicationContext();
		Resource request= appContext.getResource("classpath:saveModelRequest.xml");
		try {
			Source requestSource = new ResourceSource(request);
	        StringResult result = new StringResult();
			modelClient.getWebServiceTemplate().sendSourceAndReceiveToResult(requestSource, result);
		} catch (IOException e) {
			e.printStackTrace();
		}
		 
	 }
	 */
}
