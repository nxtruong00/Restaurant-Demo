import { HttpClient} from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import {Token} from "../shared/token.model";

@Injectable({
  providedIn: 'root'
})

export class UsersService {
  private userUrl: string;

  constructor(private http : HttpClient) {
    this.userUrl = 'http://localhost:9000/user/'
  }


}
