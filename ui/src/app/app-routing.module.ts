import { NgModule }                   from '@angular/core';
import { RouterModule, Routes }       from '@angular/router';
import { RegistrationModalComponent } from './modals/registration-modal/registration-modal.component';

const routes: Routes = [
  {
    path: 'registration',
    component: RegistrationModalComponent,
    outlet: 'modal'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
