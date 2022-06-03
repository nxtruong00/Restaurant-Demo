import {Component, Input, OnInit} from '@angular/core';
import {Food} from "../../../../model/food.model";


@Component({
  selector: 'app-food-item',
  templateUrl: './food-item.component.html',
  styleUrls: ['./food-item.component.css']
})
export class FoodItemComponent implements OnInit {
  @Input() food: Food;
  @Input() index: string;

  ngOnInit(): void {
  }

}
