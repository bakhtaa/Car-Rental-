package com.springpoly.carrental.controller;

import com.springpoly.carrental.entity.Car;
import com.springpoly.carrental.repository.BrandRepository;
import com.springpoly.carrental.repository.OptionRepository;
import com.springpoly.carrental.service.IgestionCar;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@AllArgsConstructor
public class CarController {

    private final IgestionCar carService;

    private final BrandRepository brandRepository;

    private final OptionRepository optionRepository;




    /*@GetMapping("/home")
    public String home(Model model,
                       @RequestParam(defaultValue = "0") int page,
                       @RequestParam(value = "mc", defaultValue = "") String mc) {

        Page<Car> carPage = carService.getCarsByPage(mc, page, 5);

        model.addAttribute("data", carPage.getContent());

        model.addAttribute("currentPage", page);

        int totalPages = carPage.getTotalPages();

        if (totalPages == 0) {
            totalPages = 1;
        }

        model.addAttribute(
                "pages",
                java.util.stream.IntStream.range(0, totalPages).boxed().toList()
        );

        model.addAttribute("mc", mc);

        //  IMPORTANT KPI
        model.addAttribute("stats", carService.getDashboardStats());

        return "home";
    }*/
    @GetMapping("/home")
    public String home(Model model,
                       @RequestParam(defaultValue = "0") int page,
                       @RequestParam(value = "mc", defaultValue = "") String mc) {

        Page<Car> carPage = carService.getCarsByPage(mc, page, 5);

        model.addAttribute("data", carPage.getContent());

        model.addAttribute("currentPage", page);

        int totalPages = carPage.getTotalPages();
        if (totalPages == 0) totalPages = 1;

        model.addAttribute("pages", java.util.stream.IntStream.range(0, totalPages).boxed().toList());

        model.addAttribute("mc", mc);

        model.addAttribute("stats", carService.getDashboardStats());

        return "home";
    }

    @GetMapping("/add")
    public String addForm(Model model){

        model.addAttribute(
                "car",
                new Car()
        );

        model.addAttribute(
                "brands",
                brandRepository.findAll()
        );

        model.addAttribute(
                "options",
                optionRepository.findAll()
        );

        return "addCar";
    }

    @PostMapping("/save")
    public String saveCar(@ModelAttribute Car car,
                          @RequestParam("brand") Long brandId,
                          @RequestParam(value = "optionIds", required = false) List<Long> optionIds,
                          @RequestParam("file") MultipartFile file) throws IOException {

        car.setBrand(
                brandRepository.findById(brandId).orElse(null)
        );

        if(optionIds != null){
            car.setOptions(optionRepository.findAllById(optionIds));
        }

        // 📁 UPLOAD IMAGE
        if(!file.isEmpty()){

            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();

            String uploadDir = "src/main/resources/static/uploads/";

            Files.copy(file.getInputStream(),
                    Paths.get(uploadDir + fileName),
                    StandardCopyOption.REPLACE_EXISTING);

            car.setImage(fileName);
        }

        carService.saveCar(car);

        return "redirect:/home";
    }

    @GetMapping("/delete/{id}")
    public String deleteCar(
            @PathVariable Long id
    ){

        carService.deleteCar(id);

        return "redirect:/home";
    }


    @GetMapping("/edit/{id}")
    public String editForm(
            @PathVariable Long id,
            Model model
    ){

        Car car=
                carService.getCarById(id);

        model.addAttribute(
                "car",
                car
        );

        model.addAttribute(
                "brands",
                brandRepository.findAll()
        );

        model.addAttribute(
                "options",
                optionRepository.findAll()
        );
        System.out.println(carService.getDashboardStats());

        return "addCar";

    }

}