import {Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import { UsersService } from './users.service';
import { ProfilesService } from '../profiles/profiles.service';
import Profile from '../profiles/Profiles';
import Status from './Status';

@Component({
  selector: 'app-user-add',
  templateUrl: './user-add.component.html',
  styleUrls: ['./user-add.component.scss']
})
export class UserAddComponent implements OnInit {

  profiles : Profile[];
  statuses : Status[];
  submitted = false;

  constructor(private formBuilder: FormBuilder,private router: Router, private usersService: UsersService, private profilesService: ProfilesService) { }

  addForm: FormGroup;

  ngOnInit() {
	
	this.profilesService.getProfiles()
      .subscribe( data => {
        this.profiles = data;
      });

	this.usersService.getStatuses()
      .subscribe( data => {
        this.statuses = data;
      });

    this.addForm = this.formBuilder.group({
     id: [''],
      login: ['', Validators.required],
      firstname: ['', Validators.required],
      lastname: ['', Validators.required],
      dateCreation: [''],
      dateActivation: [''], 
      dateLastConnection: [''], 
      dateArchive: [''],
      profile: [null, Validators.required],
      status: [null, Validators.required]
    });

 

  

  }

// convenience getter for easy access to form fields
get f() { return this.addForm.controls; }

  onSubmit() {
	this.submitted = true;
	
	// stop here if form is invalid
        if (this.addForm.invalid) {
	        console.log(this.addForm.value);
            return;
        }

    this.usersService.createUser(this.addForm.value)
      .subscribe( data => {
        this.router.navigate(['pages/users/get-user']);
      });
  }

   onReset() {
        // reset whole form back to initial state
        this.submitted = false;
        this.addForm.reset();
    }

}