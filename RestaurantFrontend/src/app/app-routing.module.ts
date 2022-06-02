import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {FoodStartComponent} from "./components/foods/food-start/food-start.component";
import {FoodsComponent} from "./components/foods/foods.component";
import {FoodEditComponent} from "./components/foods/food-edit/food-edit.component";
import {FoodDetailComponent} from "./components/foods/food-detail/food-detail.component";
import {OrderListComponent} from "./components/order-list/order-list.component";

import {LoginComponent} from "./components/login/login.component";


const appRoutes: Routes = [
  {path: '', redirectTo: '/login', pathMatch: 'full'},
  {path: 'login', component: LoginComponent},
  {
    path: 'foods', component: FoodsComponent, children: [
      {path: '', component: FoodStartComponent},
      {path: 'new', component: FoodEditComponent},
      {path: ':id', component: FoodDetailComponent},
      {path: ':id/edit', component: FoodEditComponent}
    ]
  },
  {path: 'order-list', component: OrderListComponent},
  {path: 'food-list', component: FoodsComponent,children:[
      {path: '', component: FoodStartComponent},
      {path: ':id', component: FoodDetailComponent},
    ]}
];

@NgModule({
  imports: [RouterModule.forRoot(appRoutes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
