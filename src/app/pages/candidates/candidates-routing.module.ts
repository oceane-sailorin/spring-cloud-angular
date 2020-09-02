import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { CandidatesComponent } from './candidates.component';
import { CandidateAddComponent } from './candidate-add.component';
import { CandidateEditComponent } from './candidate-edit.component';
import { CandidateGetComponent } from './candidate-get.component';
import { CandidateDashboardComponent } from './candidate-dashboard.component';

const routes: Routes = [
  {
    path: '',
    component: CandidatesComponent,
  },
  { path: 'get-candidate', component: CandidateGetComponent },
  { path: 'dashboard-candidate', component: CandidateDashboardComponent },
  { path: 'add-candidate', component: CandidateAddComponent },
  { path: 'edit-candidate', component: CandidateEditComponent },
];

@NgModule({
  imports: [
    RouterModule.forChild(routes),
  ],
  exports: [
    RouterModule,
  ],
})
export class CandidatesRoutingModule {
}

