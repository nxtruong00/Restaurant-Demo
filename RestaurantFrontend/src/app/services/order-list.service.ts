// import {food} from "../shared/food.model";

import {Subject} from "rxjs";
import {Food} from "../model/food.model";
import {Order} from "../model/order.model";

export class OrderListService {
  orderChanged = new Subject<Order[]>()
  startedEditing = new Subject<number>();
  private orders: Order[] = [
  ];

  getFoodInOrder(index: number) {
    return this.orders[index];
  }

  getOrders() {
    return this.orders.slice();
  }

  addFoodToOrder(food: Food) {
    this.orders.push(new Order(food, 1));
    this.orderChanged.next(this.orders.slice())
  }

  updateQuantityInOrder(index: number, newOrder:Order) {
    this.orders[index] = newOrder;
    this.orderChanged.next(this.orders.slice())
  }

  deleteFoodInOrder(index: number) {
    this.orders.splice(index, 1);
    this.orderChanged.next(this.orders.slice());
  }
  calculateTotal() {
    let total = 0
    this.orders.forEach(value => {
      total += value.getFood().price * value.getQuantity()
    })
    return total
  }
}

