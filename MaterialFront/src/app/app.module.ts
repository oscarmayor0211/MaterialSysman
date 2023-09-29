import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { ReactiveFormsModule  } from "@angular/forms";
import { HttpClientModule } from "@angular/common/http";
import { AppComponent } from './app.component';
import { MaterialComponent } from './pages/material/material.component';
import { MaterialListComponent } from './pages/material-list/material-list.component';
import { CiudadComponent } from './pages/ciudad/ciudad.component';
import { CiudadListComponent } from './pages/ciudad-list/ciudad-list.component';

@NgModule({
  declarations: [
    AppComponent,
    MaterialComponent,
    MaterialListComponent,
    CiudadComponent,
    CiudadListComponent  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
