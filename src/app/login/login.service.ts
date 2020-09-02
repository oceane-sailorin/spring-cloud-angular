import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {Observable} from "rxjs/index";
import {ApiResponse} from "../api.response";
import { AppConstants } from '../app.constants';

@Injectable()
export class LoginService {

  constructor(private http: HttpClient) { }
  authUrl: string = AppConstants.CONFIG.REST.serverAuthUrl;

  login(loginPayload) : Observable<ApiResponse> {
    return this.http.post<ApiResponse>(this.authUrl + '/token/generate-token', loginPayload);
  }
}