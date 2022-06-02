import {Component} from "@angular/core";
import {AuthService} from "../../services/auth.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
})
export class HeaderComponent {

  constructor(private authService:AuthService,private router:Router) {
  }

  logOut(){
    this.authService.logout()
    window.localStorage.clear()
    this.router.navigate(['/login'])
  }

  // isNotAdmin(){
  //   return !this.authService.userLogin.getValue().roles.includes("ROLE_ADMIN")
  //
  // }
}
