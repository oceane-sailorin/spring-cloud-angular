import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { CandidatesNeedsComponent } from './candidates-needs.component';
import { CandidateNeedDashboardComponent } from './candidate-need-dashboard.component';

const routes: Routes = [
  {
    path: '',
    component: CandidatesNeedsComponent,
  },
  { path: 'dashboard', component: CandidateNeedDashboardComponent }
];

@NgModule({
  imports: [
    RouterModule.forChild(routes),
  ],
  exports: [
    RouterModule,
  ],
})
export class CandidatesNeedsRoutingModule {
}

