import {Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import { ProfilesService } from './profiles.service';

@Component({
  selector: 'app-profile-add',
  templateUrl: './profile-add.component.html',
  styleUrls: ['./profile-add.component.scss']
})
export class ProfileAddComponent implements OnInit {

  constructor(private formBuilder: FormBuilder,private router: Router, private profilesService: ProfilesService) { }

  addForm: FormGroup;

  ngOnInit() {
	if(!window.localStorage.getItem('token')) {
      this.router.navigate(['login']);
      return;
    }
    this.addForm = this.formBuilder.group({
      naming: ['', Validators.required]
    });

  }

  onSubmit() {
    this.profilesService.createProfile(this.addForm.value)
      .subscribe( data => {
        this.router.navigate(['pages/profiles/get-profile']);
      });
  }

}