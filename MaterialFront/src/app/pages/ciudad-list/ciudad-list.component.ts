import { Component,Input, Output ,EventEmitter} from '@angular/core';
import { Ciudad } from 'src/app/model/material.model';

@Component({
  selector: 'app-ciudad-list',
  templateUrl: './ciudad-list.component.html',
  styleUrls: ['./ciudad-list.component.css']
})
export class CiudadListComponent {
  @Input() ciudad: Ciudad[];
  @Output() editMaterial = new EventEmitter<Ciudad>();
  constructor(){
    this.ciudad=[]
    console.log(this.ciudad)
  }

  llenarForm(ciudad: Ciudad) {
    console.log(ciudad);
    this.editMaterial.emit(ciudad);
  }


}
