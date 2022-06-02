import {Component, OnDestroy, OnInit} from '@angular/core';
import {Food} from "../../../shared/food.model";
import {FoodService} from "../../../services/food.service";
import {ActivatedRoute, Params, Router} from "@angular/router";
import {AuthService} from "../../../services/auth.service";
import {Subscription} from "rxjs";
import {User} from "../../../shared/user.model";

@Component({
  selector: 'app-recipe-detail',
  templateUrl: './food-detail.component.html',
  styleUrls: ['./food-detail.component.css']
})
export class FoodDetailComponent implements OnInit,OnDestroy {
  food: Food;
  id: string;
  subscription: Subscription;
  user: User

  constructor(private foodService: FoodService, private authService: AuthService
    , private route: ActivatedRoute,
              private router: Router) {
  }



  ngOnInit() {
    this.route.params
      .subscribe(
        (params: Params) => {
          this.id = params['id'];
          this.foodService.getFoodById(this.id)
            .subscribe(data => {
              console.log(data);
              this.food = data;

            })
        }
      )
    this.subscription = this.authService.getUserLogined().subscribe(data=>{
      this.user=data
    })
  }
  isAdmin() {

    console.log(this.user)
    return this.user.roles.includes("ROLE_ADMIN")
  }
  onAddToShoppingList() {
    this.foodService.addToShoppingList(this.food)
  }


  onEditFood() {
    this.router.navigate(['../', this.id, 'edit'], {relativeTo: this.route})
  }


  onDeleteFood() {
    console.log("id", this.id);
    this.foodService.deleteFood(this.id).subscribe((response) => {
      alert('Xoá thành công')
      this.foodService.goToFoodList()
    });

  }
ngOnDestroy() {
    this.subscription.unsubscribe()
}
}
