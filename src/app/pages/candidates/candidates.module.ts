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
import { CandidatesRoutingModule } from './candidates-routing.module';
import { CandidateAddComponent } from './candidate-add.component';
import { CandidateEditComponent } from './candidate-edit.component';
import { CandidateGetComponent } from './candidate-get.component';
import { CandidateDashboardComponent } from './candidate-dashboard.component';
import { CandidatesComponent } from './candidates.component';
import { DialogDatePickerComponent } from './dialog-date-picker/dialog-date-picker.component';
import { FormsModule } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';


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
    CandidatesRoutingModule,
    NbSelectModule,
    NbIconModule,
    FormsModule,
    ReactiveFormsModule,
    NbTreeGridModule,
    Ng2SmartTableModule,
    DragDropModule,
    NgMultiSelectDropDownModule.forRoot(),
    ...materialModules,
  ],
  declarations: [
    CandidateAddComponent,
    CandidateEditComponent,
    CandidateGetComponent,
    CandidateDashboardComponent,
    CandidatesComponent,
    DialogDatePickerComponent,
  ],
  schemas: [ CUSTOM_ELEMENTS_SCHEMA ],
  exports:[DialogDatePickerComponent]
})
export class CandidatesModule { }
