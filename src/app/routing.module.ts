import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginInitComponent } from './login/login-init.component';



const routes: Routes = [
  {
    path: 'login/login-init',
    component: LoginInitComponent
  },
  {
    path: 'pages',
    loadChildren: () => import('./pages/pages.module')
      .then(m => m.PagesModule),
  },
  {
    path: '',
    component: LoginInitComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }