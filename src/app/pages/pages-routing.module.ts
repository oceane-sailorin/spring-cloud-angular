import { RouterModule, Routes } from '@angular/router';
import { NgModule } from '@angular/core';

import { PagesComponent } from './pages.component';
import { NotFoundComponent } from './miscellaneous/not-found/not-found.component';

const routes: Routes = [{
  path: '',
  component: PagesComponent,
  children: [
    {
      path: 'candidates-needs',
      loadChildren: () => import('./candidates-needs/candidates-needs.module')
        .then(m => m.CandidatesNeedsModule),
    },	
    {
      path: 'candidates',
      loadChildren: () => import('./candidates/candidates.module')
        .then(m => m.CandidatesModule),
    },
    {
      path: 'users',
      loadChildren: () => import('./users/users.module')
        .then(m => m.UsersModule),
    },
    {
      path: 'profiles',
      loadChildren: () => import('./profiles/profiles.module')
        .then(m => m.ProfilesModule),
    },
     {
      path: 'clients',
      loadChildren: () => import('./clients/clients.module')
        .then(m => m.ClientsModule),
    },
    {
      path: 'needs',
      loadChildren: () => import('./needs/needs.module')
        .then(m => m.NeedsModule),
    },
    {
      path: 'miscellaneous',
      loadChildren: () => import('./miscellaneous/miscellaneous.module')
        .then(m => m.MiscellaneousModule),
    },
    {
      path: '',
      redirectTo: 'dashboard',
      pathMatch: 'full',
    },
    {
      path: '**',
      component: NotFoundComponent,
    },
  ],
}];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class PagesRoutingModule {
}
