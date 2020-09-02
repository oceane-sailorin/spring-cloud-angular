import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {Observable} from "rxjs/index";
import {ApiResponse} from "../../api.response";
import { AppConstants } from '../../app.constants';

@Injectable()
export class NeedStatusService {

  constructor(private http: HttpClient) { }
  baseUrl: string = AppConstants.CONFIG.REST.serverUrl+'/api/needs_statuses/';
  authUrl: string = AppConstants.CONFIG.REST.serverAuthUrl;

  login(loginPayload) : Observable<ApiResponse> {
    return this.http.post<ApiResponse>(this.authUrl +  'token/generate-token', loginPayload);
  }

  getNeedsStatuses() : Observable<any> {
    return this.http.get<ApiResponse>(this.baseUrl);
  }

  getNeedStatus(id: number): Observable<any> {
    return this.http.get<ApiResponse>(this.baseUrl + id);
  }

}