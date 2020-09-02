import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import Needs from './Needs';
import {Observable} from "rxjs/index";
import {ApiResponse} from "../../api.response";
import { AppConstants } from '../../app.constants';


@Injectable()
export class NeedsService {

  constructor(private http: HttpClient) { }
  baseUrl: string = AppConstants.CONFIG.REST.serverUrl+'/api/needs/';
  authUrl: string = AppConstants.CONFIG.REST.serverAuthUrl;
  baseUserUrl: string = AppConstants.CONFIG.REST.serverUrl+'/api/users';
  baseClientUrl: string = AppConstants.CONFIG.REST.serverUrl+'/api/clients';
  baseStatusUrl: string = AppConstants.CONFIG.REST.serverUrl+'/api/needs_statuses';
  basePriorityUrl: string = AppConstants.CONFIG.REST.serverUrl+'/api/priorities';
  baseKeywordUrl: string = AppConstants.CONFIG.REST.serverUrl+'/api/keywords';
  baseUrlByClient: string = AppConstants.CONFIG.REST.serverUrl+'/api/needs/byclient/';

  login(loginPayload) : Observable<ApiResponse> {
    return this.http.post<ApiResponse>(this.authUrl +  'token/generate-token', loginPayload);
  }

  getNeeds() : Observable<any> {
    return this.http.get<ApiResponse>(this.baseUrl);
  }

  getNeedById(id: number): Observable<any> {
    return this.http.get<ApiResponse>(this.baseUrl + id);
  }

  createNeed(need: Needs): Observable<any> {
    return this.http.post<ApiResponse>(this.baseUrl, need);
  }

  updateNeed(need: Needs): Observable<any> {
    return this.http.put<ApiResponse>(this.baseUrl + need.id, need);
  }

  deleteNeed(id: number): Observable<any> {
    return this.http.delete<ApiResponse>(this.baseUrl + id);
  }

 getClients() : Observable<any> {
    return this.http.get<ApiResponse>(this.baseClientUrl);
  }

 getCommercials() : Observable<any> {
    return this.http.get<ApiResponse>(this.baseUserUrl);
  }

  getPriorities() : Observable<any> {
    return this.http.get<ApiResponse>(this.basePriorityUrl);
  }

  getKeywords() : Observable<any> {
    return this.http.get<ApiResponse>(this.baseKeywordUrl);
  }

  getNeedsByClientNaming() : Observable<any> {
    return this.http.get<ApiResponse>(this.baseUrlByClient);
  }
}