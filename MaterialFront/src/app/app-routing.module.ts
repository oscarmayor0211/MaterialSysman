import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MaterialComponent } from './pages/material/material.component';
import { CiudadComponent } from './pages/ciudad/ciudad.component';

const routes: Routes = [ { path: "materiales", component: MaterialComponent },
{ path: "ciudad", component: CiudadComponent },
{ path: "**", redirectTo: "materiales", pathMatch: "full" },];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
