import {Component, OnDestroy, OnInit, ViewChild} from "@angular/core"

import {OrderListService} from "../../../services/order-list.service";
import {NgForm} from "@angular/forms";
import {Subscription} from "rxjs";
import {Order} from "../../../shared/order.model";

@Component({
  selector: 'app-order-edit',
  templateUrl: './order-edit.component.html',
  styleUrls: ['./order-edit.component.css']
})
export class OrderEditComponent implements OnInit, OnDestroy {
  @ViewChild('f') slForm: NgForm
  subscription: Subscription;
  editMode = false;
  editedItemIndex: number;
  editedItem: Order;

  constructor(private orderService: OrderListService) {
  }

  ngOnInit(): void {
    this.subscription = this.orderService.startedEditing
      .subscribe((index: number) => {
        this.editedItemIndex = index;
        this.editMode = true;
        this.editedItem = this.orderService.getFoodInOrder(index)
        this.slForm.setValue({
          name: this.editedItem.getFood().name,
          price: this.editedItem.getFood().price,
          quantity: this.editedItem.getQuantity(),
          total: this.editedItem.getFood().price * this.editedItem.getQuantity()
        })
      });
  }

  onSubmit(form: NgForm) {
    const value = form.value;
    const order = this.orderService.getFoodInOrder(this.editedItemIndex)
    const newOrder = new Order(order.getFood(), value.quantity)
    this.orderService.updateQuantityInOrder(this.editedItemIndex, newOrder)
    this.editMode = false;
    form.reset()
  }

  onClear() {
    this.slForm.reset();
    this.editMode = false;
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }

}
