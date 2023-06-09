import {HttpClientModule} from '@angular/common/http';
import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppComponent} from './app.component';
import {IMapManagement} from "../domain/ports/imap-management";
import {RemoteMapService} from "../adapters/remote-map.service";
import {MapDisplayComponent} from './map-display.component';
import {AppRoutingModule} from "./app-routing.module";

@NgModule({
  declarations: [
    AppComponent,
    MapDisplayComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [
    {provide: IMapManagement, useClass: RemoteMapService}
  ],
  bootstrap: [AppComponent],
})
export class AppModule {
}
