import {Food} from "./food.model";

export class Order {
  private food: Food
  private quantity: number;

  constructor(food: Food, quantity: number) {
    this.food = food;
    this.quantity = quantity;
  }

  getFood() {
    return this.food;
  }

  getQuantity() {
    return this.quantity;
  }
}
