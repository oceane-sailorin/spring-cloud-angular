import {Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import { ClientsService } from './clients.service';

@Component({
  selector: 'app-client-add',
  templateUrl: './client-add.component.html',
  styleUrls: ['./client-add.component.scss']
})
export class ClientAddComponent implements OnInit {

  submitted = false;

  constructor(private formBuilder: FormBuilder,private router: Router, private clientsService: ClientsService) { }

  addForm: FormGroup;

  ngOnInit() {
	if(!window.localStorage.getItem('token')) {
      this.router.navigate(['login']);
      return;
    }
    this.addForm = this.formBuilder.group({
      code: ['', Validators.required],
      naming: ['', Validators.required],
      dateCreation: [''],
      dateArchive: ['']
    });

  }

get f() { return this.addForm.controls; }

  onSubmit() {
	this.submitted = true;
	
	// stop here if form is invalid
        if (this.addForm.invalid) {
	        console.log(this.addForm.value);
            return;
        }
    this.clientsService.createClient(this.addForm.value)
      .subscribe( data => {
        this.router.navigate(['pages/clients/get-client']);
      });
  }

}