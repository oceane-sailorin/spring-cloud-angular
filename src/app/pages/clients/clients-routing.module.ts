import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { ClientsComponent } from './clients.component';
import { ClientAddComponent } from './client-add.component';
import { ClientEditComponent } from './client-edit.component';
import { ClientGetComponent } from './client-get.component';

const routes: Routes = [
  {
    path: '',
    component: ClientsComponent,
  },
  { path: 'get-client', component: ClientGetComponent },
  { path: 'add-client', component: ClientAddComponent },
  { path: 'edit-client', component: ClientEditComponent },
];

@NgModule({
  imports: [
    RouterModule.forChild(routes),
  ],
  exports: [
    RouterModule,
  ],
})
export class ClientsRoutingModule {
}

