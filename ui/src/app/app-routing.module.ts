import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {RegistrationModalComponent} from './modals/registration-modal/registration-modal.component';
import {ModalComponent} from "./modals/modal/modal.component";

const routes: Routes = [
  {
    path: 'registration',
    component: ModalComponent,
    data: {
      component: RegistrationModalComponent
    },
    outlet: 'modal'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
