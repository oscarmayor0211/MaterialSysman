import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { URL_SERVICE } from '../config/config';
import { Material } from '../model/material.model';

@Injectable({
  providedIn: 'root'
})
export class MaterialService {
   url = URL_SERVICE  +"/material";
  constructor(private http: HttpClient) { }

  getMaterialAll() {
    return this.http.get(this.url);
  }

  getMaterialCiudad(ciudad: string) {
    return this.http.get(this.url+"/buscar/ciudad/"+ ciudad );
  }

  getMaterialByParameters(tipo:string , fechaCompra : Date , serial:string) {
    return this.http.get(this.url+`/buscar?tipo=${tipo}&fechaCompra=${fechaCompra}&seria=${serial}`);
  }

  postMaterial(material: Material) {
   
    return this.http.post(this.url, material);
  }

  putMaterial(material: Material) {
    return this.http.put(this.url + "/" + material.id, material);
  }

  deleteMaterial(id: any) {
    
    return this.http.delete(this.url + "/" + id);
  }
}
