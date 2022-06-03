package com.truongnx.restaurantmanager.service.impl;

import com.truongnx.restaurantmanager.dto.FoodListDto;
import com.truongnx.restaurantmanager.exception.ResourceNotFoundException;
import com.truongnx.restaurantmanager.model.Food;
import com.truongnx.restaurantmanager.model.FoodForm;
import com.truongnx.restaurantmanager.repository.FoodRepository;
import com.truongnx.restaurantmanager.service.FoodService;
import com.truongnx.restaurantmanager.service.StorageService;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import javax.transaction.Transactional;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

@Service
@Transactional
public class FoodServiceImpl implements FoodService {


    @Autowired
    private FoodRepository foodRepository;

    @Autowired
    private StorageService storageService;

    @Override
    public int getTotalFoods() {
        return foodRepository.findAll().stream().toList().size();
    }

    @Override
    public FoodListDto getFoods(int pageNo, String keyWord) {
        FoodListDto foodListDto = new FoodListDto();
        Pageable paging = PageRequest.of(pageNo, 5);
        List<Food> pagedResult = null;
        int pages;
        if (keyWord.length() == 0) {
            pagedResult = foodRepository.findAll(paging).toList();
            pages = (int) Math.ceil((double) getTotalFoods() / 5);
        } else {
            pagedResult = foodRepository.searchFoodByKeyWord(keyWord, paging).toList();
            pages = (int) Math.ceil((double) foodRepository.countFoodByKeyWord(keyWord)/5);
        }
        foodListDto.setListFood(pagedResult);
        foodListDto.setTotalPage(pages);
        return foodListDto;
    }


    @Override
    public Food getFoodById(String foodId) throws ResourceNotFoundException {
        Optional<Food> searchFood = foodRepository.findById(foodId);
        if (searchFood.isPresent()) {
            return searchFood.get();
        } else {
            throw new ResourceNotFoundException(foodId + " not found!");
        }

    }

    @Override
    public Food updateFood(Food searchFood) {
        if (foodRepository.existsById(searchFood.getId())) {
            foodRepository.deleteById(searchFood.getId());
        }

        searchFood = foodRepository.save(searchFood);
        return searchFood;
    }

    @Override
    public void deleteFood(String foodId) {
        foodRepository.deleteById(foodId);
    }

    @Override
    public Food addFood(Food food) {
        String uuid = UUID.randomUUID().toString();

        food.setId(uuid);
        food = foodRepository.save(food);
        return food;
    }

    @Override
    public String exportReport(String reportFormat) throws FileNotFoundException, JRException {
        String path = "C:\\Users\\Admin\\5weeksTraining\\InternshipProject\\Reports\\";
        File file = ResourceUtils.getFile("classpath:jasper/test.jrxml");
        List<Food> foodList = foodRepository.findAll();
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(foodList);
        Map<String, Object> infos = new HashMap<String, Object>();
        infos.put("createBy", "Java Dev");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, infos, dataSource);
        String downloadUri = null;
        if (reportFormat.equalsIgnoreCase("html")) {
            downloadUri = "http://localhost:9000/foods/download/foods.html";
            JasperExportManager.exportReportToHtmlFile(jasperPrint, path + "\\foods.html");
        }
        if (reportFormat.equalsIgnoreCase("pdf")) {
            downloadUri = "http://localhost:9000/foods/download/foods.pdf";
            JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\foods.pdf");
        }
        return downloadUri;
    }


//    @Override
//    public Food addFood(FoodForm foodForm) {
//        String uuid = UUID.randomUUID().toString();
//        storageService.uploadFile(foodForm.getImage());
//
//        String imagePath="/assets/images/"+ foodForm.getImage().getOriginalFilename()+"";
//        foodForm.setId(uuid);
//        Food newFood=new Food(uuid,foodForm.getName(),imagePath,foodForm.getDescription(),foodForm.getPrice());
//        newFood = foodRepository.save(newFood);
//        return newFood;
//    }


//    @Autowired
//    private FoodRepository foodRepository;
//    @Override
//    public List<FoodDto> getAllFoods(int pageNo, int pageSize) {
//        List<Food> listFoods = foodRepository.findAll();
//        List<FoodDto> listFoodsDto = new ArrayList<>();
//        for (Food food : listFoods) {
//
//            FoodDto foodDto = new FoodDto(food.getId(), food.getName(), food.getImagePath(), food.getDescription(), food.getPrice());
//            listFoodsDto.add(foodDto);
//        }
//
////        Pageable paging= PageRequest.of(pageNo,pageSize);
////
////        Page<FoodDto> pagedResult=foodRepository.findAll(paging);
//
//        return listFoodsDto;
//    }
//
//    @Override
//    public FoodDto getFoodById(String foodId) throws ResourceNotFoundException {
//        Optional<Food> searchFood = foodRepository.findById(foodId);
//        if (searchFood.isPresent()) {
//            Food food = searchFood.get();
//            return new FoodDto(foodId, food.getName(), food.getImagePath(), food.getDescription(), food.getPrice());
//        } else {
//            throw new ResourceNotFoundException(foodId + " not found!");
//        }
//    }
//
//    @Override
//    public FoodDto updateFood(FoodDto foodDto) {
//        if (foodRepository.existsById(foodDto.getId())) {
//            foodRepository.deleteById(foodDto.getId());
//        }
//        Food searchFood = new Food(foodDto.getId(), foodDto.getName(), foodDto.getImagePath(), foodDto.getDescription(), foodDto.getPrice());
//        searchFood = foodRepository.save(searchFood);
//        return new FoodDto(searchFood.getId(), searchFood.getName(), searchFood.getImagePath(), searchFood.getDescription(), searchFood.getPrice());
//    }
//
//    @Override
//    public void deleteFood(String foodId) {
//        FoodDto searchFood=getFoodById(foodId);
//        foodRepository.deleteById(foodId);
//    }
//
//    @Override
//    public FoodDto addFood(FoodDto foodDto) {
//        String uuid = UUID.randomUUID().toString();
//        Food newFood = new Food(uuid, foodDto.getName() , foodDto.getImagePath(), foodDto.getDescription(), foodDto.getPrice());
//        newFood=foodRepository.save(newFood);
//        return new FoodDto(uuid, newFood.getName(), newFood.getImagePath(), newFood.getDescription(), newFood.getPrice());
//    }

}
