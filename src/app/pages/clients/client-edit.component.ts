import { Component, OnInit , Inject} from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ClientsService } from './clients.service';
import Clients from './Clients';
import {first} from "rxjs/operators";

@Component({
  selector: 'app-client-edit',
  templateUrl: './client-edit.component.html',
  styleUrls: ['./client-edit.component.css']
})
export class ClientEditComponent implements OnInit {

  client: Clients;
  editForm: FormGroup;
  editable: boolean = false;
  submitted = false;
  constructor(private formBuilder: FormBuilder,private router: Router, private clientsService: ClientsService) { }

  ngOnInit() {
	if(!window.localStorage.getItem('token')) {
      this.router.navigate(['login']);
      return;
    }
    let clientId = window.localStorage.getItem("editClientId");
    if(!clientId) {
      alert("Invalid action.")
      this.router.navigate(['clients']);
      return;
    }
    this.editForm = this.formBuilder.group({
      id: [''],
      code: ['', Validators.required],
      naming: ['', Validators.required],
      dateCreation: [''],
      dateArchive: ['']
    });
    this.clientsService.getClientById(+clientId)
      .subscribe( data => {
        this.editForm.setValue(data);
      }, error => console.log(error));
  }

 get f() { return this.editForm.controls; }

  onSubmit() {
	this.submitted = true;
	 // stop here if form is invalid
    if (this.editForm.invalid) {
        return;
    }
    this.clientsService.editClient(this.editForm.value)
      .pipe(first())
      .subscribe(
        data => {
          if(this.editForm.status === "VALID") {
            alert('Client updated successfully.');
            this.router.navigate(['pages/clients/get-client']);
          }else {
            alert(this.editForm.getError);
          }
        },
        error => {
          alert(error);
        });
  }

}