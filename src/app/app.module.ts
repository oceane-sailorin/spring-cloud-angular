import { BrowserModule } from '@angular/platform-browser';
import { NgModule} from '@angular/core';
import { CommonModule } from '@angular/common';

import { AppRoutingModule } from './routing.module';
import { AppComponent } from './app.component';
import { LoginInitComponent } from './login/login-init.component';


import { ReactiveFormsModule } from '@angular/forms';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import {TokenInterceptor} from './core/interceptor';
import { UsersService } from './pages/users/users.service';
import {ProfilesService } from './pages/profiles/profiles.service';
import {LoginService } from './login/login.service';
import {CandidatesService } from './pages/candidates/candidates.service';
import {CandidateStatusService } from './pages/candidates/candidatestatus.service';
import {CandidatesNeedsService } from './pages/candidates-needs/candidates-needs.service';
import {NeedCandidateStatusService } from './pages/candidates-needs/needcandidatestatus.service';
import {ClientsService } from './pages/clients/clients.service';
import {PrioritiesService } from './pages/needs/priority.service';
import {NeedStatusService } from './pages/needs/needStatus.service';
import {NeedsService } from './pages/needs/needs.service';
import {UploadFileService } from './pages/upload-files/upload-files.service';
import { MenuListItemComponent } from './menu-list-item/menu-list-item.component';
import {CustomMaterialModule} from "./core/material.module";
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NavService } from "./nav.service";
import { CoreModule } from './@core/core.module';
import { ThemeModule } from './@theme/theme.module';


import {
  NbDatepickerModule,
  NbDialogModule,
  NbMenuModule,
  NbSidebarModule,
  NbToastrModule,
  NbWindowModule,
  NbActionsModule,
  NbButtonModule,
  NbCardModule,
  NbCheckboxModule,
  NbIconModule,
  NbInputModule,
  NbRadioModule,
  NbSelectModule,
  NbUserModule
} from '@nebular/theme';



@NgModule({
  declarations: [
    AppComponent,
    LoginInitComponent,
    MenuListItemComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    CustomMaterialModule,
    CommonModule,
    NbInputModule,
    NbCardModule,
    NbButtonModule,
    NbActionsModule,
    NbUserModule,
    NbCheckboxModule,
    NbRadioModule,
    NbDatepickerModule,
    NbSelectModule,
    NbIconModule,
	NbSidebarModule.forRoot(),
    NbMenuModule.forRoot(),
    NbDatepickerModule.forRoot(),
    NbDialogModule.forRoot(),
    NbWindowModule.forRoot(),
    NbToastrModule.forRoot(),
    CoreModule.forRoot(),
    ThemeModule.forRoot()
  ],
  providers: [LoginService,UsersService,ProfilesService,CandidatesService,ClientsService,NeedsService,NavService,PrioritiesService,NeedStatusService,CandidateStatusService,UploadFileService,CandidatesNeedsService,NeedCandidateStatusService, {provide: HTTP_INTERCEPTORS,
              useClass: TokenInterceptor,
              multi: true}],
  bootstrap: [AppComponent]
})
export class AppModule { }
