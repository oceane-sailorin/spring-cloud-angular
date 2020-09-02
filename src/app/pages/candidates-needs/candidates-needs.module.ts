import { NgModule } from '@angular/core';
import {
  NbActionsModule,
  NbButtonModule,
  NbCardModule,
  NbCheckboxModule,
  NbDatepickerModule, NbIconModule,
  NbInputModule,
  NbRadioModule,
  NbSelectModule,
  NbUserModule,
} from '@nebular/theme';

import { ThemeModule } from '../../@theme/theme.module';
import { CandidatesNeedsRoutingModule } from './candidates-needs-routing.module';
import { CandidateNeedDashboardComponent } from './candidate-need-dashboard.component';
import { CandidatesNeedsComponent } from './candidates-needs.component';
//import { DialogDatePickerComponent } from '../candidates/dialog-date-picker/dialog-date-picker.component';
import { FormsModule } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
import { CandidatesModule } from '../candidates/candidates.module';
import { AddCandidateNeedComponent } from './add/add-candidate-need.component';

import { MatNativeDateModule } from '@angular/material/core';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatSlideToggleModule } from '@angular/material/slide-toggle';
import { MatRadioModule } from '@angular/material/radio';
import { MatButtonModule } from '@angular/material/button';
import { MatButtonToggleModule } from '@angular/material/button-toggle';


import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { NbTreeGridModule } from '@nebular/theme';
import { Ng2SmartTableModule } from 'ng2-smart-table';

import {DragDropModule} from '@angular/cdk/drag-drop';
import {MatCardModule} from '@angular/material/card';
import { NgMultiSelectDropDownModule } from 'ng-multiselect-dropdown';


const materialModules = [
  MatFormFieldModule,
  MatInputModule,
  MatSelectModule,
  MatNativeDateModule,
  MatDatepickerModule,
  MatCheckboxModule,
  MatSlideToggleModule,
  MatRadioModule,
  MatButtonModule,
  MatButtonToggleModule,
  MatCardModule
];

@NgModule({
  imports: [
    ThemeModule,
    NbInputModule,
    NbCardModule,
    NbButtonModule,
    NbActionsModule,
    NbUserModule,
    NbCheckboxModule,
    NbRadioModule,
    NbDatepickerModule,
    CandidatesNeedsRoutingModule,
    NbSelectModule,
    NbIconModule,
    FormsModule,
    ReactiveFormsModule,
    NbTreeGridModule,
    Ng2SmartTableModule,
    DragDropModule,
    CandidatesModule,
    NgMultiSelectDropDownModule.forRoot(),
    ...materialModules,
  ],
  declarations: [
    CandidateNeedDashboardComponent,
    CandidatesNeedsComponent,
    AddCandidateNeedComponent
  ],
  schemas: [ CUSTOM_ELEMENTS_SCHEMA ]
})
export class CandidatesNeedsModule { }
