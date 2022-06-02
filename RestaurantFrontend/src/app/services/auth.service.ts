import {HttpClient, HttpHeaders} from '@angular/common/http';
import { Injectable, OnInit } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import {User} from "../shared/user.model";


@Injectable({
  providedIn: 'root'
})
export class AuthService implements OnInit {

  BASE_URL = "http://localhost:9000/user/";

  userLogin = new BehaviorSubject<User>({
    userId: '',
    username: '',
    password: '',
    roles: []
  });

  constructor(private http: HttpClient) {

  }

  ngOnInit(): void {
  }

  getAuthorizationToken() {
    const token = window.localStorage.getItem('token');
    if (token) {
      return token;
    }
    return '';
  }

  getUserByToken () : Observable<User> {
    return this.http.get<User>(this.BASE_URL+'auth', {
      headers: new HttpHeaders({
        'Authorization': 'Bearer ' + localStorage.getItem('token'),
        'Content-Type': 'application/json'
      })
    });
  }

  login(user: User): Observable<any> {
    console.log(user);
    return this.http.post(this.BASE_URL + "login", user, {
      responseType: 'text'
    });
  }

  logout() {
    return this.http.post(this.BASE_URL + "logout/", null);
  }

  getUserLogined(): Observable<User> {
    return this.http.get<User>(this.BASE_URL + 'auth')
  }
}
