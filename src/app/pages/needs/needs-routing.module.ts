import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { NeedsComponent } from './needs.component';
import { NeedAddComponent } from './need-add.component';
import { NeedEditComponent } from './need-edit.component';
import { NeedGetComponent } from './need-get.component';

const routes: Routes = [
  {
    path: '',
    component: NeedsComponent,
  },
  { path: 'get-need', component: NeedGetComponent },
  { path: 'add-need', component: NeedAddComponent },
  { path: 'edit-need', component: NeedEditComponent },
];

@NgModule({
  imports: [
    RouterModule.forChild(routes),
  ],
  exports: [
    RouterModule,
  ],
})
export class NeedsRoutingModule {
}

