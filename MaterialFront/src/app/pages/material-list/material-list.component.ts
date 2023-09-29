import { Component, Input, Output, EventEmitter } from '@angular/core';
import { Material } from 'src/app/model/material.model';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MaterialService } from 'src/app/services/material.service';

@Component({
  selector: 'app-material-list',
  templateUrl: './material-list.component.html',
  styleUrls: ['./material-list.component.css']
})
export class MaterialListComponent {

  @Input() material: Material[];
  @Output() editMaterial = new EventEmitter<Material>();
  @Output() deletMaterial = new EventEmitter<any>();

  materiales: any[] = [];
  ciudadForm: FormGroup;


  constructor(private materialService: MaterialService, private formBuilder: FormBuilder) {
    this.material = []
    console.log(this.material);
    this.ciudadForm = this.formBuilder.group({
      ciudad: ['', Validators.required] // 'ciudad' es el nombre del campo y se inicializa vacío
    });
  }

  llenarForm(material: Material) {
    console.log(material);
    this.editMaterial.emit(material);
  }

  deleteMaterial(id: number) {
    console.log(id);
    this.deletMaterial.emit(id);
  }

  buscarMateriales() {
    if (this.ciudadForm.valid) {
      const ciudad = this.ciudadForm.get("ciudad")?.value;
      // Realiza la búsqueda con la ciudad ingresada
      this.materialService.getMaterialCiudad(ciudad).subscribe((ciudad: any) => {
        this.material = ciudad;
        console.log(ciudad);

      })
    }
  }

  getAllMaterial(){
    this.materialService.getMaterialAll().subscribe((material: any) => {
      console.log(material);
      this.material = material;
    });
  }
}
