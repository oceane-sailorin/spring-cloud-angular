import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import Users from './Users';
import {Observable} from "rxjs/index";
import {ApiResponse} from "../../api.response";
import { AppConstants } from '../../app.constants';

@Injectable()
export class UsersService {

  constructor(private http: HttpClient) { }
  baseUrl: string = AppConstants.CONFIG.REST.serverUrl+'/api/users/';
  authUrl: string = AppConstants.CONFIG.REST.serverAuthUrl;
  baseStatusUrl: string = AppConstants.CONFIG.REST.serverUrl+'/api/usersstatuses';

  login(loginPayload) : Observable<ApiResponse> {
    return this.http.post<ApiResponse>(this.authUrl +  'token/generate-token', loginPayload);
  }

  getUsers() : Observable<any> {
    return this.http.get<ApiResponse>(this.baseUrl);
  }

  getUserById(id: number): Observable<any> {
    return this.http.get<ApiResponse>(this.baseUrl + id);
  }

  createUser(user: Users): Observable<any> {
    return this.http.post<ApiResponse>(this.baseUrl, user);
  }

  updateUser(user: Users): Observable<any> {
    return this.http.put<ApiResponse>(this.baseUrl + user.id, user);
  }

  deleteUser(id: number): Observable<any> {
    return this.http.delete<ApiResponse>(this.baseUrl + id);
  }

 getStatuses() : Observable<any> {
    return this.http.get<ApiResponse>(this.baseStatusUrl);
  }
}