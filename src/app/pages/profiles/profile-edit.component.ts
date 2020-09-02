import { Component, OnInit , Inject} from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ProfilesService } from './profiles.service';
import Profiles from './Profiles';
import {first} from "rxjs/operators";

@Component({
  selector: 'app-profile-edit',
  templateUrl: './profile-edit.component.html',
  styleUrls: ['./profile-edit.component.css']
})
export class ProfileEditComponent implements OnInit {

  profile: Profiles;
  editForm: FormGroup;
  constructor(private formBuilder: FormBuilder,private router: Router, private profilesService: ProfilesService) { }

  ngOnInit() {
	if(!window.localStorage.getItem('token')) {
      this.router.navigate(['login']);
      return;
    }
    let profileId = window.localStorage.getItem("editProfileId");
    if(!profileId) {
      alert("Invalid action.")
      this.router.navigate(['pages/profiles/get-profile']);
      return;
    }
    this.editForm = this.formBuilder.group({
      id: [''],
      naming: ['', Validators.required]
    });
    this.profilesService.getProfileById(+profileId)
      .subscribe( data => {
        this.editForm.setValue(data);
      }, error => console.log(error));


  }

  onSubmit() {
    this.profilesService.editProfile(this.editForm.value)
      .pipe(first())
      .subscribe(
        data => {
          if(this.editForm.status === "VALID") {
            alert('Profile updated successfully.');
            this.router.navigate(['pages/profiles/get-profile']);
          }else {
            alert(this.editForm.getError);
          }
        },
        error => {
          alert(error);
        });
  }

}