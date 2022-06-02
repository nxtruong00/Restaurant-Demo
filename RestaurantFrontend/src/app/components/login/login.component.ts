import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import {AuthService} from "../../services/auth.service";


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
    public error: string
  public loginForm = this.formBuilder.group({
    username: ['', Validators.required],
    password: ['', Validators.required]
  })

  constructor(private formBuilder: FormBuilder, private authService: AuthService, private router: Router) {
  }

  ngOnInit(): void {
    if(window.localStorage.getItem('token')){
      this.router.navigate(['/foods']);
    }
  }

  logIn() {
    this.authService.login(this.loginForm.value).subscribe(
      (data) => {
        window.localStorage.setItem('token', data);
        console.log(data)
        this.router.navigate(['/foods']);

        // this.authService.getUserLogined().subscribe(
        //
        //   user => {
        //     this.authService.userLogin.next(user)
        //   }
        // )
      },
      (error) => {
        this.error="Password or username not correct";
      }
    );
  }

}

