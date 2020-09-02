import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {Observable} from "rxjs/index";
import {ApiResponse} from "../../api.response";
import { AppConstants } from '../../app.constants';

@Injectable()
export class CandidateStatusService {

  constructor(private http: HttpClient) { }
  baseUrl: string = AppConstants.CONFIG.REST.serverUrl+'/api/candidates_statuses/';
  authUrl: string = AppConstants.CONFIG.REST.serverAuthUrl;
  candidatesStatusUrl: string = AppConstants.CONFIG.REST.serverUrl+'/api/candidates_statuses/candidates_by_status';

  login(loginPayload) : Observable<ApiResponse> {
    return this.http.post<ApiResponse>(this.authUrl +  'token/generate-token', loginPayload);
  }

  getCandidatesStatuses() : Observable<any> {
    return this.http.get<ApiResponse>(this.baseUrl);
  }

  getCandidateStatusById(id: number): Observable<any> {
    return this.http.get<ApiResponse>(this.baseUrl + id);
  }

  getCandidatesByStatuses() : Observable<any> {
    return this.http.get<ApiResponse>(this.candidatesStatusUrl);
  }

}