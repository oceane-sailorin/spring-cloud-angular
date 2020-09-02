import {Component, OnInit , TemplateRef, ViewChild} from '@angular/core';
import {Router} from "@angular/router";
import {Observable, zip} from "rxjs/index";
import CandidatesNeeds from './CandidatesNeeds';
import Needs from '../needs/Needs';
import NeedCandidateStatus from './NeedCandidateStatus';
import { CandidatesNeedsService } from './candidates-needs.service';
import { NeedsService } from '../needs/needs.service';
import { NeedCandidateStatusService } from './needcandidatestatus.service';
import {CdkDragDrop, moveItemInArray, transferArrayItem} from '@angular/cdk/drag-drop';
import { first } from 'rxjs/operators';
import StatusCandidateNeed from './StatusCandidateNeed';
import StatusDateCandidateNeed from './StatusDateCandidateNeed';
import { DialogDatePickerComponent } from '../candidates/dialog-date-picker/dialog-date-picker.component';
import { NbDialogService } from '@nebular/theme';
import { NbWindowService } from '@nebular/theme';
import { AddCandidateNeedComponent } from './add/add-candidate-need.component';

@Component({
  selector: 'app-candidate-dashboard',
  templateUrl: './candidate-need-dashboard.component.html',
  styleUrls: ['./candidate-need-dashboard.component.scss']
})
export class CandidateNeedDashboardComponent implements OnInit {
    
  [x: string]: any;
  candidatesNeeds: CandidatesNeeds[];
  qualifies: CandidatesNeeds[];
  prevus: CandidatesNeeds[];
  needsCandidatesStatuses: NeedCandidateStatus[]= [];
  trackys: NeedStat[]=[] ;
  needCandidatesByStatuses: any;
  needCandidateStat: NeedCandidateStatus;
  namingTemp: string;
  names: string[] = [];
  dateUpdate: Date;
  codeTemp: NeedCandidateStatus;
  needs: Needs[];


  constructor(private router: Router, private candidatesNeedsService: CandidatesNeedsService, private needCandidateStatusService: NeedCandidateStatusService,private dialogService: NbDialogService, private needService: NeedsService, private windowService: NbWindowService) {
	
 }


  ngOnInit() {
    if(!window.localStorage.getItem('token')) {
      this.router.navigate(['login']);
      return;
    }
    this.candidatesNeedsService.getCandidatesNeeds()
      .subscribe( data => {
        this.candidatesNeeds = data;
		this.qualifies = this.candidatesNeeds;
		this.prevus = this.candidatesNeeds;		
      });

   this.getArrayCandidateNeed();
      
  }


getArrayCandidateNeed(): void {
	     zip( this.needCandidateStatusService.getNeedsCandidatesStatuses(), this.needCandidateStatusService.getCandidatesNeedsByNeedByStatuses(), this.needService.getNeeds()).subscribe(values => {		 
	     this.candidatesNeedsByStatuses = values[1];
         this.needsCandidatesStatuses = values[0];
		 this.needs = values[2];
         //console.log(this.candidatesNeedsByStatuses);	
       for (let [key, value] of Object.entries(this.candidatesNeedsByStatuses)) {
			var stat = {} as NeedStat;							
            //stat.id = +key;
             stat.id = key.toString();
             stat.need = null;

             for (let [keyNeed, valueNeed] of Object.entries(this.needs)) {
              
	            if(valueNeed.id == +key){
		        	stat.need = valueNeed;
                }
		      }
            
             //console.log(value);	
             for (let [key1, value1] of Object.entries(value)) {	          
			    stat.title = key1; 
                stat.needCandidatesStat = [];
                 for (let [key2, value2] of Object.entries(value1)) {	
	                  var stat2 = {} as NeedCandidatesStat;		 	          	                   
				      stat2.id = key2;                           
                      for (let [key3, value3] of Object.entries(value2)) {	 
	                  	stat2.naming = key3;                      
                        stat2.candidatesNeeds = value3;
                        //console.log(stat2.candidatesNeeds);	                        
			            stat.needCandidatesStat.push(stat2);
					  }
			       
				 }
			
			}
            this.trackys.push(stat);	
			//console.log(stat);				
		     
        }
		console.log(this.trackys);	
	   });
}


  deleteCandidateNeed(candidateNeed: CandidatesNeeds): void {
    this.candidatesNeedsService.deleteCandidateNeed(candidateNeed.id)
      .subscribe( data => {
        this.candidatesNeeds = this.candidatesNeeds.filter(u => u !== candidateNeed);
      })
  };

  editCandidateNeed(candidateNeed: CandidatesNeeds): void {
    window.localStorage.removeItem("editCandidateNeedId");
    window.localStorage.setItem("editCandidateNeedId", candidateNeed.id.toString());
    this.router.navigate(['pages/candidates-needs/edit-candidate-need']);
  };

  addCandidateNeed(): void {
	this.windowService.open(AddCandidateNeedComponent, { title: `Add Candidate Need` });
  };

	

 onDeleteConfirm(event): void {
    if (window.confirm('Are you sure you want to delete?')) {
      event.confirm.resolve();
    } else {
      event.confirm.reject();
    }
  }




get trackyIds(): string[] {
    return this.trackys.map(tracky => tracky.id);
  }



 onTalkyDrop(event: CdkDragDrop<CandidatesNeeds[]>) {
    // In case the destination container is different from the previous container, we
    // need to transfer the given candidate to the target data array. This happens if
    // a candidate has been dropped on a different track.
    if (event.previousContainer === event.container) {
      moveItemInArray(event.container.data, event.previousIndex, event.currentIndex);
    } else {
      transferArrayItem(event.previousContainer.data,
        event.container.data,
        event.previousIndex,
        event.currentIndex);
        //console.log(event.container.id);
        //console.log(event.container.data[event.currentIndex]);
        var statusCandidateNeed = {} as StatusCandidateNeed;
        var statusDateCandidateNeed = {} as StatusDateCandidateNeed;	
        statusCandidateNeed.id = +event.container.data[event.currentIndex].id;
        statusDateCandidateNeed.id = +event.container.data[event.currentIndex].id;
        //statusCandidateNeed.candidateNeedStatus = +event.container.data[event.currentIndex].candidateNeedStatus.id;	          
        //console.log(statusCandidateNeed); 
        this.needCandidateStatusService.getNeedCandidateStatusById(+event.container.id)
      			.subscribe( data => {  
	             this.codeTemp = data;
				 //console.log(this.codeTemp.code);
			     
				if(this.codeTemp.code=='RENDEZ_VOUS_PRIS' || this.codeTemp.code=='PROPALE_FAITE' || this.codeTemp.code=='PROPALE_ACCEPTEE') {
				   statusDateCandidateNeed.needCandidateStatus = +event.container.id;
		           statusDateCandidateNeed.dateUpdate = null;
		           this.dialogService.open(DialogDatePickerComponent)
		           .onClose.subscribe(dateUpdate => {this.dateUpdate = dateUpdate;
			           if(dateUpdate != null && dateUpdate != ""){
	                   statusDateCandidateNeed.dateUpdate = dateUpdate;
                       //console.log(statusDateCandidate);
				       this.candidatesNeedsService.updateStatusDateCandidateNeed(+event.container.data[event.currentIndex].id,statusDateCandidateNeed)
						      .pipe(first())
						      .subscribe(
						        data => {	        
						            //alert(data);
						        },
						        error => {
						          alert(error);
						        });	
			            
	                    }else{		       
							transferArrayItem(event.container.data,
					        event.previousContainer.data,
					        +event.currentIndex,
					        +event.currentIndex);
		                }
					});	
						
				} else {
				   statusCandidateNeed.needCandidateStatus = +event.container.id;
			       this.candidatesNeedsService.updateStatusCandidateNeed(+event.container.data[event.currentIndex].id,statusCandidateNeed)
					      .pipe(first())
					      .subscribe(
					        data => {	        
					            //alert(data);
					        },
					        error => {
					          alert(error);
					        });
		       }
           }, error => console.log(error));
    }
  }

  onTrackDropy(event: CdkDragDrop<NeedStat[]>) {
    moveItemInArray(event.container.data, event.previousIndex, event.currentIndex);
  }



}


export interface NeedStat {
  id: string;
  title: string;
  need: Needs;
  needCandidatesStat: NeedCandidatesStat[];
}

export interface NeedCandidatesStat {
  id: string;
  naming: string;
  candidatesNeeds: CandidatesNeeds[];
}


