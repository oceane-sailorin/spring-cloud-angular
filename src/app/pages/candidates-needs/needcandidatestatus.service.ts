import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {Observable} from "rxjs/index";
import {ApiResponse} from "../../api.response";
import { AppConstants } from '../../app.constants';

@Injectable()
export class NeedCandidateStatusService {

  constructor(private http: HttpClient) { }
  baseUrl: string = AppConstants.CONFIG.REST.serverUrl+'/api/need_candidate_statuses/';
  authUrl: string = AppConstants.CONFIG.REST.serverAuthUrl;
  needCandidatesStatusUrl: string = AppConstants.CONFIG.REST.serverUrl+'/api/need_candidate_statuses/candidates_needs_by_status';
  needCandidatesNeedStatusUrl: string = AppConstants.CONFIG.REST.serverUrl+'/api/need_candidate_statuses/candidates_needs_by_need_by_status';

  login(loginPayload) : Observable<ApiResponse> {
    return this.http.post<ApiResponse>(this.authUrl +  'token/generate-token', loginPayload);
  }

  getNeedsCandidatesStatuses() : Observable<any> {
    return this.http.get<ApiResponse>(this.baseUrl);
  }

  getNeedCandidateStatusById(id: number): Observable<any> {
    return this.http.get<ApiResponse>(this.baseUrl + id);
  }

  getCandidatesNeedsByStatuses() : Observable<any> {
    return this.http.get<ApiResponse>(this.needCandidatesStatusUrl);
  }

  getCandidatesNeedsByNeedByStatuses() : Observable<any> {
    return this.http.get<ApiResponse>(this.needCandidatesNeedStatusUrl);
  }

}