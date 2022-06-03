import {Food} from "../model/food.model";
import {Injectable} from "@angular/core";

import {Observable, Subject} from "rxjs";
import {OrderListService} from "./order-list.service";
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";

@Injectable()
export class FoodService {
  foodsChanged = new Subject<Food[]>();
  totalFood: number;
  private foodsUrl: string;
  private total: number;
  private totalSubject = new Subject();

  // foods: Observable<Food[]> = this.findAll()

  constructor(private http: HttpClient, private router: Router, private orderService: OrderListService) {
    this.foodsUrl = 'http://localhost:9000/foods/'
  }

  public findAll(page: number,keyWord:string): Observable<any> {
    return this.http.get<any>(this.foodsUrl + "?page="+page+"&keyWord="+keyWord )
  }


  getFoodById(id: string): Observable<any> {
    return this.http.get<Food>(this.foodsUrl + id);
  }

  public addFood(food: Food) {
    return this.http.post<Food>(this.foodsUrl + 'add/', food)
  }

  public updateFood(food: Food): Observable<Food> {
    return this.http.put<Food>(this.foodsUrl + 'update/' + food.id, food)
  }

  public deleteFood(id: string): Observable<any> {
    return this.http.delete<any>(this.foodsUrl + 'delete/' + id);
  }

  goToFoodList() {
    this.router.navigate(['/foods']).then(()=>window.location.reload())
  }

  addToShoppingList(food: Food) {
    this.orderService.addFoodToOrder(food)
    this.orderService.calculateTotal()
  }

  get Total() {
    return this.total;
  }

  set Total(value: number) {
    this.total = value;
    this.totalSubject.next();
  }
}
