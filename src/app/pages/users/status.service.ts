import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {Observable} from "rxjs/index";
import {ApiResponse} from "../../api.response";
import { AppConstants } from '../../app.constants';

@Injectable()
export class StatusService {

  constructor(private http: HttpClient) { }
  baseUrl: string = AppConstants.CONFIG.REST.serverUrl+'/api/usersstatuses/';
  authUrl: string = AppConstants.CONFIG.REST.serverAuthUrl;

  login(loginPayload) : Observable<ApiResponse> {
    return this.http.post<ApiResponse>(this.authUrl +  'token/generate-token', loginPayload);
  }

  getStatuses() : Observable<any> {
    return this.http.get<ApiResponse>(this.baseUrl);
  }

  getStatusById(id: number): Observable<any> {
    return this.http.get<ApiResponse>(this.baseUrl + id);
  }

}