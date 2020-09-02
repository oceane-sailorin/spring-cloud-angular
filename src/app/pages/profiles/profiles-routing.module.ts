import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { ProfilesComponent } from './profiles.component';
import { ProfileAddComponent } from './profile-add.component';
import { ProfileEditComponent } from './profile-edit.component';
import { ProfileGetComponent } from './profile-get.component';

const routes: Routes = [
  {
    path: '',
    component: ProfilesComponent,
  },
  { path: 'get-profile', component: ProfileGetComponent },
  { path: 'add-profile', component: ProfileAddComponent },
  { path: 'edit-profile', component: ProfileEditComponent },
];

@NgModule({
  imports: [
    RouterModule.forChild(routes),
  ],
  exports: [
    RouterModule,
  ],
})
export class ProfilesRoutingModule {
}

