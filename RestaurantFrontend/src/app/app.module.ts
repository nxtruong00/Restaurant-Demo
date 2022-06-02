import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {HeaderComponent} from "./components/header/header.component";
import {FoodsComponent} from "./components/foods/foods.component";
import {FoodListComponent} from "./components/foods/food-list/food-list.component";
import {FoodDetailComponent} from "./components/foods/food-detail/food-detail.component";
import {FoodItemComponent} from "./components/foods/food-list/food-item/food-item.component";
import {OrderListComponent} from "./components/order-list/order-list.component";
import {OrderEditComponent} from "./components/order-list/order-edit/order-edit.component";
import {DropdownDirective} from "./shared/dropdown.directive";
import {FoodStartComponent} from "./components/foods/food-start/food-start.component";
import {FoodEditComponent} from "./components/foods/food-edit/food-edit.component";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {OrderListService} from "./services/order-list.service";
import {FoodService} from "./services/food.service";
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {LoginComponent} from './components/login/login.component';
import {UserComponent} from './components/user/user.component';
import {AuthService} from "./services/auth.service";
import {UsersService} from "./services/user.service";
import {JwtInterceptor} from "./interceptor/jwt.interceptors";

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FoodsComponent,
    FoodListComponent,
    FoodDetailComponent,
    FoodItemComponent,
    OrderListComponent,
    OrderEditComponent,
    DropdownDirective,
    FoodStartComponent,
    FoodEditComponent,
    LoginComponent,
    UserComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    FormsModule,
    AppRoutingModule,
    HttpClientModule,
  ],
  providers: [OrderListService, FoodService, AuthService, UsersService,
  [{
    provide: HTTP_INTERCEPTORS,
    useClass: JwtInterceptor,
    multi: true
  }]],
  bootstrap: [AppComponent]
})
export class AppModule {
}
