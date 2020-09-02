import {Component, OnInit , Inject} from '@angular/core';
import { FormGroup, FormBuilder, Validators,FormArray, } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { UsersService } from './users.service';
import Users from './Users';
import {first} from "rxjs/operators";
import { ProfilesService } from '../profiles/profiles.service';
import Profile from '../profiles/Profiles';
import Status from './Status';

@Component({
  selector: 'app-user-edit',
  templateUrl: './user-edit.component.html',
  styleUrls: ['./user-edit.component.css']
})
export class UserEditComponent implements OnInit {

  user: Users;
  profiles : Profile[];
  statuses : Status[];
  editForm: FormGroup;
  selectedStatus = 0;
  selectedProfile = 0;
  editable: boolean = false;
  submitted = false;
  constructor(private formBuilder: FormBuilder,private router: Router, private usersService: UsersService, private profilesService: ProfilesService) { }

  ngOnInit() {
	if(!window.localStorage.getItem('token')) {
      this.router.navigate(['login']);
      return;
    }	

	this.profilesService.getProfiles()
      .subscribe( data => {
        this.profiles = data;
      });

	this.usersService.getStatuses()
      .subscribe( data => {
        this.statuses = data;
      });

    let userId = window.localStorage.getItem("editUserId");
    if(!userId) {
      alert("Invalid action.")
      this.router.navigate(['user-get']);
      return;
    }
    this.editForm = this.formBuilder.group({
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
    this.usersService.getUserById(+userId)
      .subscribe( data => {
        this.editForm.setValue(data);
        this.selectedStatus = this.editForm.controls.status.value.id;
        this.selectedProfile = this.editForm.controls.profile.value.id;

      }, error => console.log(error));
  }

 get f() { return this.editForm.controls; }


 onSubmit() {
	this.submitted = true;
	 // stop here if form is invalid
    if (this.editForm.invalid) {
	console.log(this.editForm.value);
        return;
    }
    this.usersService.updateUser(this.editForm.value)
      .pipe(first())
      .subscribe(
        data => {
          if(this.editForm.status === "VALID") {
            alert('User updated successfully.');
            this.router.navigate(['pages/users/get-user']);
          }else {
            alert(this.editForm.getError);
          }
        },
        error => {
          alert(error);
        });
  }

}