package at.ac.iiasa.ime.enrima.example.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;
import at.ac.iiasa.ime.enirma.example.ws.client.ModelClient;


@Controller
public class ModelController {
	
	@Autowired private ModelClient modelClient;
	
	@RequestMapping(value = "/",method=RequestMethod.GET)
	public String populateModelList(Model model) {
		model.addAttribute("modelList", modelClient.getAll());
        return "modelList";
	}

	@RequestMapping(value = "/model/{modelId}",method=RequestMethod.GET)

	public String populateModel(@PathVariable("modelId") int modelId, Model model) {
		model.addAttribute("model", modelClient.getModelById(modelId));
		return "model";
	}
	
	@RequestMapping(value = "/model/{modelId}",method=RequestMethod.POST)
	public String saveModel(@RequestParam(value = "_cancel", required = false) String cancel,@ModelAttribute("model")at.ac.iiasa.ime.enrima.ws.example.oxm.Model newModel, @PathVariable("modelId") int modelId) {
		
		if(cancel!=null)
			return "redirect:/";
		
		at.ac.iiasa.ime.enrima.ws.example.oxm.Model model = modelClient.getModelById(modelId);
		model.setName(newModel.getName());
		model.setDescription(newModel.getDescription());
		model.setVersion(newModel.getVersion());
		modelClient.save(model); 
		return "redirect:/";
	}

}
