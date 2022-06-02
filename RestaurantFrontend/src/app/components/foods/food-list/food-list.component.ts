import {Component, OnChanges, OnDestroy, OnInit, SimpleChanges} from '@angular/core';

import {ActivatedRoute, Router} from "@angular/router";
import {Subscription} from "rxjs";
import {Food} from "../../../shared/food.model";
import {FoodService} from "../../../services/food.service";

@Component({
  selector: 'app-food-list',
  templateUrl: './food-list.component.html',
  styleUrls: ['./food-list.component.css']
})
export class FoodListComponent implements OnInit {
  private page: number = 0
  foods: Food[];
  pages: number[];
  totalPages: number;
  subscription: Subscription;

  constructor(private foodService: FoodService
    , private router: Router, private route: ActivatedRoute) {

  }

  setPage(i, event: any) {
    event.preventDefault()
    this.page = i - 1;
    this.getFoods()
  }

  ngOnInit(): void {
    this.getFoods()
  }

  getFoods() {
    this.foodService.findAll(this.page)
      .subscribe(data => {
          this.foods = data.listFood;
          this.totalPages = data.totalPage;
          this.pages = Array.from({length: this.totalPages}, (_, i) => i + 1)
        }
      );

  }


  onNewFood() {
    this.router.navigate(['new'], {relativeTo: this.route});
  }


  // ngOnDestroy() {
  //   this.subscription.unsubscribe()
  // }
}
