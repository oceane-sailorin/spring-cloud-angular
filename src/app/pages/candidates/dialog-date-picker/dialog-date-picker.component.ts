import { Component } from '@angular/core';
import { NbDialogRef } from '@nebular/theme';

@Component({
  selector: 'ngx-dialog-date-picker',
  templateUrl: 'dialog-date-picker.component.html',
  styleUrls: ['dialog-date-picker.component.scss'],
})
export class DialogDatePickerComponent {

  constructor(protected ref: NbDialogRef<DialogDatePickerComponent>) {}

  cancel() {
    this.ref.close();
  }

  submit(dateUpdate) {
	console.log(dateUpdate);
    this.ref.close(dateUpdate);
  }
}