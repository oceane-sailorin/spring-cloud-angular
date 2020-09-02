import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import Clients from './Clients';
import {Observable} from "rxjs/index";
import {ApiResponse} from "../../api.response";
import { AppConstants } from '../../app.constants';

@Injectable()
export class ClientsService {

  constructor(private http: HttpClient) { }
  baseUrl: string = AppConstants.CONFIG.REST.serverUrl+'/api/clients/';
  authUrl: string = AppConstants.CONFIG.REST.serverAuthUrl;

  login(loginPayload) : Observable<ApiResponse> {
    return this.http.post<ApiResponse>(this.authUrl +  'token/generate-token', loginPayload);
  }

  getClients() : Observable<any> {
    return this.http.get<ApiResponse>(this.baseUrl);
  }

  getClientById(id: number): Observable<any> {
    return this.http.get<ApiResponse>(this.baseUrl + id);
  }

  createClient(client: Clients): Observable<any> {
    return this.http.post<ApiResponse>(this.baseUrl, client);
  }

  editClient(client: Clients): Observable<any> {
    return this.http.put<ApiResponse>(this.baseUrl + client.id, client);
  }

  deleteClient(id: number): Observable<any> {
    return this.http.delete<ApiResponse>(this.baseUrl + id);
  }
}