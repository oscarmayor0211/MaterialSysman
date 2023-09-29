import { Component } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from "@angular/forms";
import { CiudadService } from 'src/app/services/ciudad.service';
import { MaterialService } from 'src/app/services/material.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-material',
  templateUrl: './material.component.html',
  styleUrls: ['./material.component.css']
})
export class MaterialComponent {
  form: FormGroup;
  currentDate = new Date();

  material: any;
  ciudad:any;
  mate:any;
    constructor( private fb: FormBuilder, private materialService : MaterialService, private ciudadService :CiudadService){
    this.form = this.fb.group({
      id: [],
      nombre: ["", Validators.required],
      descripcion: ["", Validators.required],
      tipo: ["", Validators.required],
      serial: ["", Validators.required],
      numeroInterno: ["", Validators.required],
      precio: ["", Validators.required],
      fechaCompra: ["", Validators.required],
      fechaVenta: ["", Validators.required],
      estado: [null, Validators.required],
      idciudad: [null, Validators.required],
    });
    this.getAllMaterial();
    this.getAllCiudad();
  }

  //validaciones en el formulario
  get NameInvalid() {
    const nombreControl = this.form.get("nombre");
  
    return nombreControl?.invalid && nombreControl?.touched;
  }

  get DescriptionInvalid() {
    const DescripcionControl = this.form.get("descripcion");
  
    return DescripcionControl?.invalid && DescripcionControl?.touched;
  }
  get TypeInvalid() {
    const control = this.form.get("tipo");
  
    return control?.invalid && control?.touched;
  }
  
  get CiudadInvalid() {
    const control = this.form.get("ciudad");
  
    return control?.invalid && control?.touched;
  }

  get PrecioInvalid() {
    const control = this.form.get("precio");
  
    return control?.invalid && control?.touched;
  }
  get EstadoInvalid() {
    const control = this.form.get("estado");
  
    return control?.invalid && control?.touched;
  }
  get fechaCompraInvalid() {
    const control = this.form.get("fechaCompra");
  
    return control?.invalid && control?.touched;
  }
  get fechaVentaInvalid() {
    const control = this.form.get("fechaVenta");
  
    return control?.invalid && control?.touched;
  }

  get SerialInvalid() {
    const control = this.form.get("serial");
  
    return control?.invalid && control?.touched;
  }
  get NumInternoInvalid() {
    const control = this.form.get("numeroInterno");
  
    return control?.invalid && control?.touched;
  }

  getAllMaterial(){
    this.materialService.getMaterialAll().subscribe((material: any) => {
      console.log(material);
      this.material = material;
    });
  }

  getAllCiudad(){
    this.ciudadService.getCiudadAll().subscribe((ciudad:any)=>{
      console.log(ciudad);
      this.ciudad =ciudad;
    })
  }

  createMaterial() {
    console.log(this.form.value);
    this.mate = {
      id: this.form.value.id,
      descripcion: this.form.value.descripcion,
      nombre: this.form.value.nombre,
      tipo :this.form.value.tipo,
      precio: this.form.value.precio,
      estado: this.form.value.estado,
      serial:this.form.value.serial,
      numeroInterno:this.form.value.numeroInterno,
      fechaVenta:this.form.value.fechaVenta,
      fechaCompra: this.form.value.fechaCompra,
      ciudad: {
        idciudad: this.form.value.idciudad,
      }
    };

    console.log(this.mate);
    if (this.form.invalid) {
      return Object.values(this.form.controls).forEach((control) => {
        if (control instanceof FormGroup) {
          Object.values(control.controls).forEach((control) => {
            control.markAllAsTouched();
          });
        } else {
          control.markAllAsTouched();
        }
      });
    } else {
      if (this.form.value.id === null) {
        this.materialService
          .postMaterial(this.mate)
          .subscribe((material: any) => {
            console.log(material);

            this.material = [...this.material, material];
            Swal.fire("Material Creado");
            this.resetFormulario();
          }, (error) =>{
            Swal.fire({
              icon: 'error',
              title: 'Oops...',
              text: error.error.message,
            })

          });
      } else {
        this.materialService
          .putMaterial(this.mate)
          .subscribe((material: any) => {
            this.getAllMaterial();

            Swal.fire("Material Editado");
          this.resetFormulario();
          }, (error) =>{
            Swal.fire({
              icon: 'error',
              title: 'Oops...',
              text: error.error.message,
            })});
      }
    }
 
  }

  handleEditMaterial(material: any) {
    console.log(material);
    this.form.setValue({
      id: material.id,
      descripcion: material.descripcion      ,
      nombre: material.nombre,
      tipo :material.tipo,
      precio: material.precio,
      estado: material.estado,
      serial:material.serial,
      numeroInterno:material.numeroInterno,
      fechaVenta:material.fechaVenta,
      fechaCompra: material.fechaCompra,
     
        idciudad: material.ciudad.idciudad,
      
    });
  }
  handleDeleteMaterial(id: any) {
    Swal.fire({
      title: "Desear Eliminar el Material?",
      text: "Eliminar el Material",
      icon: "warning",
      showCancelButton: true,
      confirmButtonColor: "#3085d6",
      cancelButtonColor: "#d33",
      confirmButtonText: "Eliminar!",
    }).then((borrar) => {
      if (borrar.value) {
        this.materialService.deleteMaterial(id).subscribe(() => {
          this.getAllMaterial();
        });
      }
    });
  }
  resetFormulario() {
    this.form.reset({
      id:"",
      descripcion: ""     ,
      nombre: "",
      tipo: "",
      precio: "",
      estado: "",
      serial:"",
      numeroInterno:"",
      fechaVenta:null,
      fechaCompra: null,
     
        idciudad: null,
    });
  }
}
