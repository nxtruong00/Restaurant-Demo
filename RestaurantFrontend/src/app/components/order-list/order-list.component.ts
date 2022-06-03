import {Component, OnDestroy, OnInit} from '@angular/core'
import {OrderListService} from "../../services/order-list.service";
import {Subscription} from "rxjs";
import {Order} from "../../model/order.model";

@Component({
  selector: 'app-order-list',
  templateUrl: './order-list.component.html',
  styleUrls: ['./order-list.component.css'],
})
export class OrderListComponent implements OnInit, OnDestroy {
  orders: Order[]
  private idChangeSub: Subscription;
  editMode = false
  total: number

  constructor(private orderService: OrderListService) {

  }

  ngOnInit(): void {
    this.orders = this.orderService.getOrders()
    this.idChangeSub = this.orderService.orderChanged
      .subscribe((orders: Order[]) => this.orders = orders)
    console.log(this.orders)
    this.loadTotal()
    console.log(this.total)
  }

  onEditItem(index: number) {
    this.editMode = true;
    this.orderService.startedEditing.next(index)
    this.total= this.orderService.calculateTotal()
  }

  onDelete(index: number) {
    this.orderService.deleteFoodInOrder(index)
   this.total= this.orderService.calculateTotal()
  }

  ngOnDestroy() {
    this.idChangeSub.unsubscribe();
  }

  loadTotal() {
    this.idChangeSub = this.orderService.orderChanged.subscribe(() => {
      this.total = this.orderService.calculateTotal();
    });
  }






}
