package com.PineGap.Project;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class DataSetController {
    @Autowired
    private PageService pageService;    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String browseDataSets(
      Model model, 
      @RequestParam("page") Optional<Integer> page, 
      @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);

        Page<DataSet> dataSetPage = pageService.findPaginated(PageRequest.of(currentPage - 1, pageSize));

        model.addAttribute("dataSetPage", dataSetPage);

        model.addAttribute("currentPageAsInt", currentPage);

        int totalPages = dataSetPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                .boxed()
                .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        model.addAttribute("dataSets", DataSet.getDataSets());

        return "/browse_dataSets.html";
    }

    @GetMapping("/viewDataSet/{id}")
    public String viewDataSet(@PathVariable int id, Model model) {

        ArrayList<DataSet> getDataSets = DataSet.getDataSets();
        // we want the id's to start from 1
        if(id > 0 && id - 1 <= getDataSets.size() && getDataSets.get(id - 1) != null){
            model.addAttribute("dataSet", getDataSets.get(id - 1));
        }
        return "/view_dataSet.html";
    }

    @GetMapping("/adminMenu")
    public String adminMenu( Model model) {

        model.addAttribute("dataSets", DataSet.getDataSets());

        return "/adminMenu.html";
    }



    @GetMapping("/addDataSet")
    public String addDataSet( Model model) {
        return "/add_dataSet.html";
    }

    // TODO: when login is done make sure only admin can add new datasets
    @PostMapping("/addDataSet")
    public String addNewDataSet(@ModelAttribute("data") DataSet data,@RequestParam("datapointFile") MultipartFile file) {
        
        //Save the uploaded file to this folder
         String UPLOADED_FOLDER = "UploadedFile/";
         Path path = null;
         
         if (file.isEmpty()) {
             return "redirect:/addDataSet?fileNotOK=yes";
         }
         try {
             byte[] bytes = file.getBytes();
             path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
             Files.write(path, bytes);
            } catch (IOException e) {
                return "redirect:/addDataSet?fileNotOK=yes";
            }
            DataSet.getDataSets().add(new DataSet(data.getName(), data.getDescription(), 
            data.getMinimum(), data.getMaximum(), data.getPricePerDataPoint(), ReadDataPoint.readData(path.toString())));
            return "redirect:/";
    }

    // TODO: when login is done make sure only admin can add new datasets
    @GetMapping("/editDataSet/{id}")
    public String editDataSet(@PathVariable int id, Model model) {
        ArrayList<DataSet> getDataSets = DataSet.getDataSets();
        // we want the id's to start from 1
        if(id > 0 && id - 1 <= getDataSets.size() && getDataSets.get(id - 1) != null){
            model.addAttribute("dataSet", getDataSets.get(id - 1));
        }
        return "/edit_dataSet.html";
    }

    // TODO: when login is done make sure only admin can add new datasets
    @PostMapping("/editDataSet/{id}")
    public String editDataSetProccess(@PathVariable int id, @ModelAttribute("data") DataSet data, Model model) {

        for(DataSet dataSet: DataSet.getDataSets()){
            if(dataSet.getId() - 1 == id - 1){
                dataSet.setDescription(data.getDescription());
                dataSet.setMinimum(data.getMinimum());
                dataSet.setMaximum(data.getMaximum());
                dataSet.setPriceDataPoint(data.getPricePerDataPoint());
            }
        }
        return "redirect:/editDataSet/{id}?updated=yes";
    }

    @GetMapping("/hideDataSet/{id}")
    public String hideDataSet(@PathVariable int id, Model model) {
        for(DataSet dataSet: DataSet.getDataSets()){
            if(dataSet.getId() - 1 == id - 1){
                dataSet.setInVisible();
            }
        }
        return "redirect:/?datasetHidden=yes";
    }

    @GetMapping("/unhideDataSet/{id}")
    public String unHideDataSet(@PathVariable int id, Model model) {
        for(DataSet dataSet: DataSet.getDataSets()){
            if(dataSet.getId() - 1 == id - 1){
                dataSet.setVisible();
            }
        }
        return "redirect:/?datasetHidden=no";
    }
}