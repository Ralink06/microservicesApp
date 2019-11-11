import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {HeaderComponent} from './header/header.component';
import {RegistrationModalComponent} from './modals/registration-modal/registration-modal.component';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {ModalComponent} from './modals/modal/modal.component';
import {ValidationMsgComponent} from './shared/validation/validation-msg/validation-msg.component';
import {ValidationFormDirective} from './shared/validation/validation-form/validation-form.directive';
import {ReactiveFormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    RegistrationModalComponent,
    ModalComponent,
    ValidationMsgComponent,
    ValidationFormDirective
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgbModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [],
  entryComponents: [RegistrationModalComponent],
  bootstrap: [AppComponent]
})
export class AppModule {
}
