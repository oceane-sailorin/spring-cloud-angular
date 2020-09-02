import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import Candidates from './Candidates';
import StatusCandidate from './StatusCandidate';
import StatusDateCandidate from './StatusDateCandidate';
import {Observable} from "rxjs/index";
import {ApiResponse} from "../../api.response";
import { AppConstants } from '../../app.constants';

@Injectable()
export class CandidatesService {

  constructor(private http: HttpClient) { }
  baseUrl: string = AppConstants.CONFIG.REST.serverUrl+'/api/candidates/';
  baseStatusCandidateUrl: string = AppConstants.CONFIG.REST.serverUrl+'/api/candidates/status/';
  baseStatusDateCandidateUrl: string = AppConstants.CONFIG.REST.serverUrl+'/api/candidates/statusdate/';
  authUrl: string = AppConstants.CONFIG.REST.serverAuthUrl;
  baseStatusUrl: string = AppConstants.CONFIG.REST.serverUrl+'/api/candidates_statuses';
  baseUserUrl: string = AppConstants.CONFIG.REST.serverUrl+'/api/users';
  baseCountryUrl: string = AppConstants.CONFIG.REST.serverUrl+'/api/countries';
  baseSourceUrl: string = AppConstants.CONFIG.REST.serverUrl+'/api/sources';
  baseKeywordUrl: string = AppConstants.CONFIG.REST.serverUrl+'/api/keywords';

  login(loginPayload) : Observable<ApiResponse> {
    return this.http.post<ApiResponse>(this.authUrl +  'token/generate-token', loginPayload);
  }

  getCandidates() : Observable<any> {
    return this.http.get<ApiResponse>(this.baseUrl);
  }

  getCandidateById(id: number): Observable<any> {
    return this.http.get<ApiResponse>(this.baseUrl + id);
  }

  createCandidate(candidate: Candidates): Observable<any> {
    return this.http.post<ApiResponse>(this.baseUrl, candidate);
  }

  updateCandidate(candidate: Candidates): Observable<any> {	
    return this.http.put<ApiResponse>(this.baseUrl + candidate.id, candidate);
  }

  updateStatusCandidate(id: number, statusCandidate: StatusCandidate): Observable<any> {
    return this.http.put<ApiResponse>(this.baseStatusCandidateUrl + id, statusCandidate);
  }

  updateStatusDateCandidate(id: number, statusDateCandidate: StatusDateCandidate): Observable<any> {
	console.log(statusDateCandidate);	
    return this.http.put<ApiResponse>(this.baseStatusDateCandidateUrl + id, statusDateCandidate);
  }

  deleteCandidate(id: number): Observable<any> {
    return this.http.delete<ApiResponse>(this.baseUrl + id);
  }

 getCandidatesStatuses() : Observable<any> {
    return this.http.get<ApiResponse>(this.baseStatusUrl);
  }

 getSources() : Observable<any> {
    return this.http.get<ApiResponse>(this.baseSourceUrl);
  }

 getCountries() : Observable<any> {
    return this.http.get<ApiResponse>(this.baseCountryUrl);
  }

 getRecruiters() : Observable<any> {
    return this.http.get<ApiResponse>(this.baseUserUrl);
  }

 getSourcers() : Observable<any> {
    return this.http.get<ApiResponse>(this.baseUserUrl);
  }

 getKeywords() : Observable<any> {
    return this.http.get<ApiResponse>(this.baseKeywordUrl);
  }
}