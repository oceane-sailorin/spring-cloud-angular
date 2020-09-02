import { Component,ViewChild, ElementRef, ViewEncapsulation, AfterViewInit } from '@angular/core';
import {VERSION} from '@angular/material/core';
import {NavItem} from './nav-item';
import {NavService} from './nav.service';
import { AppConstants } from './app.constants';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  encapsulation: ViewEncapsulation.None
})
export class AppComponent  {
  title = 'UIBCC';
  version = VERSION;
  navItems: NavItem[] = [
    {
      displayName: 'Users',
      iconName: 'client.png',
      route: 'users'    
    }]

 constructor(private navService: NavService) {
 }


}
