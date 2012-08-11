package at.ac.iiasa.ime.enirma.example.ws.client;

import java.util.List;
import at.ac.iiasa.ime.enrima.ws.example.oxm.Model;


public interface ModelClient {
	//public void login(String username,String password);
    public List<Model> getAll();
    public Model getModelById(int idModel);
    public void save(Model model);
}




