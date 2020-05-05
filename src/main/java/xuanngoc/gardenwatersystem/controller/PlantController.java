package xuanngoc.gardenwatersystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import xuanngoc.gardenwatersystem.model.Plant;
import xuanngoc.gardenwatersystem.service.PlantService;

@Controller
public class PlantController {

    private PlantService plantService;

    @Autowired
    public void setPlantService(PlantService plantService) {
        this.plantService = plantService;
    }

    @RequestMapping("plant/list")
    public String listPlants(Model model) {
        model.addAttribute("plants", plantService.getAllPlants());
        return "plant/list";
    }

    @RequestMapping("plant/new")
    public String newPlant(Model model) {
        model.addAttribute("plant", new Plant());
        return "plant/new-plant";
    }

    @RequestMapping("plant/edit/{id}")
    public String editPlant(@PathVariable Integer id, Model model) {
        model.addAttribute("plant", plantService.getById(id));
        return "plant/edit-plant";
    }

    @RequestMapping(value = "/plant", method = RequestMethod.POST)
    public String saveOrUpdateProduct(Plant product){
        Plant savePlant = plantService.saveOrUpdate(product);
        return "redirect:/plant/list";
    }

    @RequestMapping("plant/delete/{id}")
    public String delete(@PathVariable Integer id) {
        plantService.delete(id);
        return "redirect:/plant/list";
    }


}
