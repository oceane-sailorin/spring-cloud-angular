import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import CandidatesNeeds from './CandidatesNeeds';
import StatusCandidateNeed from './StatusCandidateNeed';
import StatusDateCandidateNeed from './StatusDateCandidateNeed';
import {Observable} from "rxjs/index";
import {ApiResponse} from "../../api.response";
import { AppConstants } from '../../app.constants';

@Injectable()
export class CandidatesNeedsService {

  constructor(private http: HttpClient) { }
  baseUrl: string = AppConstants.CONFIG.REST.serverUrl+'/api/candidatesneeds/';
  baseStatusCandidateNeedUrl: string = AppConstants.CONFIG.REST.serverUrl+'/api/candidatesneeds/status/';
  baseStatusDateCandidateNeedUrl: string = AppConstants.CONFIG.REST.serverUrl+'/api/candidatesneeds/statusdate/';
  authUrl: string = AppConstants.CONFIG.REST.serverAuthUrl;
  baseStatusUrl: string = AppConstants.CONFIG.REST.serverUrl+'/api/need_candidate_statuses';
  baseUserUrl: string = AppConstants.CONFIG.REST.serverUrl+'/api/users';
  baseCountryUrl: string = AppConstants.CONFIG.REST.serverUrl+'/api/countries';
  baseSourceUrl: string = AppConstants.CONFIG.REST.serverUrl+'/api/sources';
  baseKeywordUrl: string = AppConstants.CONFIG.REST.serverUrl+'/api/keywords';

  login(loginPayload) : Observable<ApiResponse> {
    return this.http.post<ApiResponse>(this.authUrl +  'token/generate-token', loginPayload);
  }

  getCandidatesNeeds() : Observable<any> {
    return this.http.get<ApiResponse>(this.baseUrl);
  }

  getCandidateNeedById(id: number): Observable<any> {
    return this.http.get<ApiResponse>(this.baseUrl + id);
  }

  createCandidateNeed(candidateNeed: CandidatesNeeds): Observable<any> {
    return this.http.post<ApiResponse>(this.baseUrl, candidateNeed);
  }

  updateCandidateNeed(candidateNeed: CandidatesNeeds): Observable<any> {	
    return this.http.put<ApiResponse>(this.baseUrl + candidateNeed.id, candidateNeed);
  }

  updateStatusCandidateNeed(id: number, statusNeedCandidate: StatusCandidateNeed): Observable<any> {
    return this.http.put<ApiResponse>(this.baseStatusCandidateNeedUrl + id, statusNeedCandidate);
  }

  updateStatusDateCandidateNeed(id: number, statusDateCandidateNeed: StatusDateCandidateNeed): Observable<any> {
	console.log(statusDateCandidateNeed);	
    return this.http.put<ApiResponse>(this.baseStatusDateCandidateNeedUrl + id, statusDateCandidateNeed);
  }

  deleteCandidateNeed(id: number): Observable<any> {
    return this.http.delete<ApiResponse>(this.baseUrl + id);
  }

 getNeedsCandidatesStatuses() : Observable<any> {
    return this.http.get<ApiResponse>(this.baseStatusUrl);
  }

}