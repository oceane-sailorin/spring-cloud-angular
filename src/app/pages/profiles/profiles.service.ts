import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import Profiles from './Profiles';
import {Observable} from "rxjs/index";
import {ApiResponse} from "../../api.response";
import { AppConstants } from '../../app.constants';

@Injectable()
export class ProfilesService {

  constructor(private http: HttpClient) { }
  baseUrl: string = AppConstants.CONFIG.REST.serverUrl+'/api/profiles/';
  authUrl: string = AppConstants.CONFIG.REST.serverAuthUrl;

  login(loginPayload) : Observable<ApiResponse> {
    return this.http.post<ApiResponse>(this.authUrl +  'token/generate-token', loginPayload);
  }

  getProfiles() : Observable<any> {
    return this.http.get<ApiResponse>(this.baseUrl);
  }

  getProfileById(id: number): Observable<any> {
    return this.http.get<ApiResponse>(this.baseUrl + id);
  }

  createProfile(profile: Profiles): Observable<any> {
    return this.http.post<ApiResponse>(this.baseUrl, profile);
  }

  editProfile(profile: Profiles): Observable<any> {
    return this.http.put<ApiResponse>(this.baseUrl + profile.id, profile);
  }

  deleteProfile(id: number): Observable<any> {
    return this.http.delete<ApiResponse>(this.baseUrl + id);
  }
}