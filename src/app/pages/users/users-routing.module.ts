import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UsersComponent } from './users.component';
import { UserAddComponent } from './user-add.component';
import { UserEditComponent } from './user-edit.component';
import { UserGetComponent } from './user-get.component';

const routes: Routes = [
  {
    path: '',
    component: UsersComponent,
  },
  { path: 'get-user', component: UserGetComponent },
  { path: 'add-user', component: UserAddComponent },
  { path: 'edit-user', component: UserEditComponent },
];

@NgModule({
  imports: [
    RouterModule.forChild(routes),
  ],
  exports: [
    RouterModule,
  ],
})
export class UsersRoutingModule {
}

