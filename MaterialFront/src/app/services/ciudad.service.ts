import { Injectable } from '@angular/core';
import { Ciudad } from '../model/material.model';
import { URL_SERVICE } from '../config/config';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class CiudadService {

  url = URL_SERVICE  +"/ciudad";
  constructor(private http: HttpClient) { }

  getCiudadAll() {
    return this.http.get(this.url);
  }

  postCiudad(ciudad: Ciudad) {
   
    return this.http.post(this.url, ciudad);
  }
}
