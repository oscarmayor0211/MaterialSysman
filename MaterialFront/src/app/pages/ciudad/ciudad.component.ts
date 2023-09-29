import { Component } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from "@angular/forms";
import { CiudadService } from 'src/app/services/ciudad.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-ciudad',
  templateUrl: './ciudad.component.html',
  styleUrls: ['./ciudad.component.css']
})
export class CiudadComponent {
  form: FormGroup;
  ciudad : any;
  constructor(private fb: FormBuilder,private ciudadesService : CiudadService){
    this.form = this.fb.group({
      idciudad: [],
      nombre: ["", Validators.required],
      departamento: ["", Validators.required],
    });
    this.getAllCiudades();
  }

  getAllCiudades(){
    this.ciudadesService.getCiudadAll().subscribe((ciudad =>{
      this.ciudad = ciudad;
      console.log(ciudad);
    }))
  }

  //metodo donde me suscribo para enviar el post
  createCiudad(){
    this.ciudadesService.postCiudad(this.form.value).subscribe((ciudad:any) =>{
      this.ciudad = ciudad;
      console.log(ciudad);
      this.getAllCiudades();
      Swal.fire("Ciudad Creada");
    })
  }

  handleEditCiudad(ciudad: any) {
    console.log(ciudad);
    this.form.setValue({
     idciudad:ciudad.idciudad,
     nombre:ciudad.nombre,
     departamento:ciudad.departamento
    });
  }
}
