import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Params, Router} from "@angular/router";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {FoodService} from "../../../services/food.service";
import {Food} from "../../../model/food.model";

@Component({
  selector: 'app-food-edit',
  templateUrl: './food-edit.component.html',
  styleUrls: ['./food-edit.component.css']
})
export class FoodEditComponent implements OnInit {
  id: string;
  food: Food;
  editMode = false;
  foodForm: FormGroup;

  constructor(private route: ActivatedRoute,
              private foodService: FoodService,
              private router: Router) {
  }

  ngOnInit(): void {

    this.route.params
      .subscribe((params: Params) => {
        this.id = params['id'];
        this.editMode = params['id'] != null;
        console.log(this.editMode)
        this.fillForm();
      })
    if (this.editMode) {
      this.foodService.getFoodById(this.id)
        .subscribe(data => {
          console.log(data);
          this.food = data;
          this.fillForm();
        })
    }

  }

  onSubmit() {
    const newFood = new Food(this.id,
      this.foodForm.value['name'],
      this.foodForm.value['imagePath'],
      this.foodForm.value['description'],
      this.foodForm.value['price']);
    if (this.editMode) {
      this.foodService.updateFood(newFood).subscribe((response) => {
        alert('Sửa thành công');
        this.foodService.goToFoodList()

      })
      console.log(this.foodForm.value)
    } else {
      this.foodService.addFood(newFood).subscribe((response) => {
        alert('Thêm thành công')
        this.foodService.goToFoodList()
      });
    }

    this.onCancel();
  }

  onCancel() {
    this.router.navigate(['../'], {relativeTo: this.route})
  }

  private fillForm() {
    let foodName
    let foodImagePath;
    let foodDescription;
    let foodPrice;
    if (this.editMode) {

      foodName = this.food?.name;
      console.log(foodName)
      foodImagePath = this.food?.imagePath;
      foodDescription = this.food?.description;
      foodPrice = this.food?.price;
    }
    this.foodForm = new FormGroup({
      'name': new FormControl(foodName, Validators.required),
      'imagePath': new FormControl(foodImagePath, Validators.required),
      'description': new FormControl(foodDescription, Validators.required),
      'price': new FormControl(foodPrice, Validators.required)
    });
  }
}
