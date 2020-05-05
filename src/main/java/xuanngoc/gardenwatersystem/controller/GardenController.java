package xuanngoc.gardenwatersystem.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import xuanngoc.gardenwatersystem.model.Garden;
import xuanngoc.gardenwatersystem.service.GardenService;
import xuanngoc.gardenwatersystem.service.PlantService;

@Controller
public class GardenController {

    private GardenService gardenService;
    private PlantService plantService;

    @Autowired
    public void setGardenService(GardenService gardenService) {
        this.gardenService = gardenService;
    }

    @Autowired
    public void setPlantService(PlantService plantService) {
        this.plantService = plantService;
    }

    @RequestMapping("garden/list")
    public String listGardens(Model model) {
        model.addAttribute("gardens", gardenService.findAllGardens());
        return "garden/list";
    }

    @RequestMapping("garden/{plantId}/list")
    public String listGardens(@PathVariable Integer plantId, Model model) {
        model.addAttribute("gardens", gardenService.findAllGardens(plantId));
        return "garden/list";
    }

    @RequestMapping("garden/new")
    public String newGarden(Model model) {
        model.addAttribute("garden", new Garden());
        model.addAttribute("plants", plantService.getAllPlants());
        return "garden/new-garden";
    }

    @RequestMapping("garden/edit/{id}")
    public String editGarden(@PathVariable Integer id, Model model) {
        model.addAttribute("garden", gardenService.getById(id));
        model.addAttribute("plants", plantService.getAllPlants());
        return "garden/edit-garden";
    }

    @RequestMapping(value = "garden", method = RequestMethod.POST)
    public String saveOrUpdate(Garden garden) {
        Garden saveGarden = gardenService.saveOrUpdate(garden);
        return "redirect:/garden/list";
    }

    @RequestMapping(value = "garden/delete/{id}")
    public String delete(@PathVariable Integer id) {
        gardenService.delete(id);
        return "redirect:/garden/list";
    }
}
