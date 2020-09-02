import {Component, OnInit , Inject} from '@angular/core';
import {Router} from "@angular/router";
import Profiles from './Profiles';
import { ProfilesService } from './profiles.service';

@Component({
  selector: 'app-profile-get',
  templateUrl: './profile-get.component.html',
  styleUrls: ['./profile-get.component.scss']
})
export class ProfileGetComponent implements OnInit {

  profiles: Profiles[];

  constructor(private router: Router, private profilesService: ProfilesService) { }

  ngOnInit() {
    if(!window.localStorage.getItem('token')) {
      this.router.navigate(['login']);
      return;
    }
    this.profilesService.getProfiles()
      .subscribe( data => {
        this.profiles = data;
      });
  }

  deleteProfile(profile: Profiles): void {
    this.profilesService.deleteProfile(profile.id)
      .subscribe( data => {
        this.profiles = this.profiles.filter(u => u !== profile);
      })
  };

  editProfile(profile: Profiles): void {
    window.localStorage.removeItem("editProfileId");
    window.localStorage.setItem("editProfileId", profile.id.toString());
    this.router.navigate(['pages/profiles/edit-profile']);
  };

  addProfile(): void {
    this.router.navigate(['pages/profiles/add-profile']);
  };
}