import {Component, OnInit , Inject,  Input} from '@angular/core';
import {Router} from "@angular/router";
import Needs from './Needs';
import { NeedsService } from './needs.service';
import {CdkDragDrop, moveItemInArray, transferArrayItem} from '@angular/cdk/drag-drop';


@Component({
  selector: 'app-need-get',
  templateUrl: './need-get.component.html',
  styleUrls: ['./need-get.component.scss']
})
export class NeedGetComponent implements OnInit {

  needs: Needs[];


  constructor(private router: Router, private needsService: NeedsService) { }

  ngOnInit() {
    if(!window.localStorage.getItem('token')) {
      this.router.navigate(['login']);
      return;
    }
    this.needsService.getNeeds()
      .subscribe( data => {
        this.needs = data;		
      });
  }

  deleteNeed(need: Needs): void {
	if (window.confirm('Are you sure you want to delete?')) {
	    this.needsService.deleteNeed(need.id)
	      .subscribe( data => {
	        this.needs = this.needs.filter(u => u !== need);
	      })
    }
  };

  editNeed(need: Needs): void {
    window.localStorage.removeItem("editNeedId");
    window.localStorage.setItem("editNeedId", need.id.toString());
    this.router.navigate(['pages/needs/edit-need']);
  };

  addNeed(): void {
    this.router.navigate(['pages/needs/add-need']);
  };

	

 onDeleteConfirm(event): void {
    if (window.confirm('Are you sure you want to delete?')) {
      event.confirm.resolve();
    } else {
      event.confirm.reject();
    }
  }


}


