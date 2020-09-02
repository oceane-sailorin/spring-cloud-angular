import { Injectable } from '@angular/core';
import { HttpClient, HttpRequest, HttpEvent } from '@angular/common/http';
import {Observable} from "rxjs/index";
import {ApiResponse} from "../../api.response";
import { AppConstants } from '../../app.constants';

@Injectable()
export class UploadFileService {

  constructor(private http: HttpClient) { }
  baseUrl: string = AppConstants.CONFIG.REST.serverUrl+'/api/uploads/';
  baseUrlNeed: string = AppConstants.CONFIG.REST.serverUrl+'/api/uploads/need/';
  baseUrlCandidate: string = AppConstants.CONFIG.REST.serverUrl+'/api/uploads/candidate/';
  authUrl: string = AppConstants.CONFIG.REST.serverAuthUrl;

  login(loginPayload) : Observable<ApiResponse> {
    return this.http.post<ApiResponse>(this.authUrl +  'token/generate-token', loginPayload);
  }

  getFiles() : Observable<any> {
    return this.http.get<ApiResponse>(this.baseUrl);
  }

  getFileByName(filename: string): Observable<any> {
    return this.http.get<ApiResponse>(this.baseUrl + filename);
  }

 getUrl(filename: string): String {
    return this.baseUrl + filename;
  }

  uploadNeed(file: File, name: any, id: any): Observable<HttpEvent<any>> {
    const formData: FormData = new FormData();
  
    formData.append('file', file);
    formData.append('name', name);
    formData.append('id', id);
    const req = new HttpRequest('POST', `${this.baseUrlNeed}`, formData, {
      reportProgress: true,
      responseType: 'json'
    });

    return this.http.request(req);
  }

 uploadCandidate(file: File, name: any, id: any): Observable<HttpEvent<any>> {
    const formData: FormData = new FormData();
console.log("uploadservice");
  console.log(name);
    formData.append('file', file);
    formData.append('name', name);
    formData.append('id', id);
    const req = new HttpRequest('POST', `${this.baseUrlCandidate}`, formData, {
      reportProgress: true,
      responseType: 'json'
    });

    return this.http.request(req);
  }




  
}