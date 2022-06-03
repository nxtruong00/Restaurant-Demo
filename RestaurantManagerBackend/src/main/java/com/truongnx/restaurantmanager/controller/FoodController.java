package com.truongnx.restaurantmanager.controller;

import com.truongnx.restaurantmanager.dto.FoodListDto;
import com.truongnx.restaurantmanager.exception.ResourceNotFoundException;
import com.truongnx.restaurantmanager.model.Food;
import com.truongnx.restaurantmanager.model.FoodForm;
import com.truongnx.restaurantmanager.service.FoodService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping()
@CrossOrigin(origins = "http://localhost:4200")
public class FoodController {

    @Autowired
    FoodService foodService;


    @GetMapping(value = "/foods")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<FoodListDto> getFoods(@RequestParam("page") int page,@RequestParam(name = "keyWord",defaultValue = "") String keyWord) {
        FoodListDto listFoodDto = new FoodListDto();

        listFoodDto=foodService.getFoods(page,keyWord);

        return new ResponseEntity<>(listFoodDto, HttpStatus.OK);
    }

    @PostMapping("/foods/add")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Food> addFood(@RequestBody Food food) {

        Food newFood = foodService.addFood(food);
        return new ResponseEntity<>(newFood, HttpStatus.CREATED);
    }



    @GetMapping("/foods/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Food> getFoodByID(@PathVariable(value = "id") String foodId)
            throws ResourceNotFoundException {
        Food food = foodService.getFoodById(foodId);
        return new ResponseEntity<Food>(food, HttpStatus.OK);
    }

    @DeleteMapping("/foods/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Map<String, Boolean> deleteFood(@PathVariable(value = "id") String foodId)
            throws ResourceNotFoundException {
        foodService.deleteFood(foodId);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    @PutMapping("/foods/update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Food> updateFood(@PathVariable(value = "id") String foodId, @RequestBody Food food)
            throws ResourceNotFoundException {
        food = foodService.updateFood(food);
        return new ResponseEntity<Food>(food, HttpStatus.OK);
    }

    @GetMapping(value = "foods/report/{format}")
    public ResponseEntity<String> exportReport(@PathVariable("format") String format) throws JRException, IOException {
        String downloadUri = foodService.exportReport(format);
        return new ResponseEntity<>(downloadUri, HttpStatus.OK);
    }

    @GetMapping(value = "foods/download/{fileName}")
    public ResponseEntity<Resource> downloadFileExport(@PathVariable("fileName") String fileName)
            throws JRException, IOException {
        File file = new File("C:\\Users\\Admin\\5weeksTraining\\InternshipProject\\Reports\\" + fileName);

        Path path = Paths.get(file.getAbsolutePath());
        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));

        return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, Files.probeContentType(path))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"")
                .body(resource);
    }
//    @Autowired
//    FoodService foodService;
//
//    @GetMapping(value = "/foods")
//    public ResponseEntity<List<FoodDto>> getAllFoods() {
//        List<FoodDto> list = foodService.getAllFood();
//        return new ResponseEntity<>(list, HttpStatus.OK);
//    }
//
//    @PostMapping("/foods/add")
//    public ResponseEntity<FoodDto> addFood(@RequestBody FoodDto foodDto) {
//        foodDto = foodService.addFood(foodDto);
//        return new ResponseEntity<FoodDto>(foodDto, HttpStatus.CREATED);
//    }
//
//    @GetMapping("/foods/{id}")
//    public ResponseEntity<FoodDto> getFoodByID(@PathVariable(value = "id") String foodId)
//            throws ResourceNotFoundException {
//        FoodDto foodDto = foodService.getFoodById(foodId);
//        return new ResponseEntity<FoodDto>(foodDto, HttpStatus.OK);
//    }
//
//    @DeleteMapping("/foods/delete/{id}")
//    public Map<String, Boolean> deleteFood(@PathVariable(value = "id") String foodId)
//            throws ResourceNotFoundException {
//        foodService.deleteFood(foodId);
//        Map<String, Boolean> response = new HashMap<>();
//        response.put("deleted", Boolean.TRUE);
//        return response;
//    }
//
//    @PutMapping("/foods/update/{id}")
//    public ResponseEntity<FoodDto> updateFood(@PathVariable(value = "id") String foodId, @RequestBody FoodDto foodDto)
//            throws ResourceNotFoundException {
//        foodDto = foodService.updateFood(foodDto);
//        return new ResponseEntity<FoodDto>(foodDto, HttpStatus.OK);
//    }
}
