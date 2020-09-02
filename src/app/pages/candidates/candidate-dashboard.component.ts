import {Component, OnInit , Inject,  Input} from '@angular/core';
import {Router} from "@angular/router";
import {Observable, zip} from "rxjs/index";
import Candidates from './Candidates';
import CandidateStatus from './CandidateStatus';
import { CandidatesService } from './candidates.service';
import { CandidateStatusService } from './candidatestatus.service';
import {CdkDragDrop, moveItemInArray, transferArrayItem} from '@angular/cdk/drag-drop';
import { first } from 'rxjs/operators';
import StatusCandidate from './StatusCandidate';
import StatusDateCandidate from './StatusDateCandidate';
import { DialogDatePickerComponent } from './dialog-date-picker/dialog-date-picker.component';
import { NbDialogService } from '@nebular/theme';


@Component({
  selector: 'app-candidate-dashboard',
  templateUrl: './candidate-dashboard.component.html',
  styleUrls: ['./candidate-dashboard.component.scss']
})
export class CandidateDashboardComponent implements OnInit {
    
  [x: string]: any;
  candidates: Candidates[];
  qualifies: Candidates[];
  prevus: Candidates[];
  candidatesStatuses: CandidateStatus[];
  trackys: CandStat[]=[] ;
  candidatesByStatuses: any;
  candidateStat: CandidateStatus;
  namingTemp: string;
  names: string[] = [];
  dateUpdate: Date;
  codeTemp: CandidateStatus;


  constructor(private router: Router, private candidatesService: CandidatesService, private candidateStatusService: CandidateStatusService,private dialogService: NbDialogService) {
	
	const  groupedResponse: Observable<[any, any]> = zip( this.candidateStatusService.getCandidatesByStatuses(), this.candidateStatusService.getCandidatesStatuses());
 }


  ngOnInit() {
    if(!window.localStorage.getItem('token')) {
      this.router.navigate(['login']);
      return;
    }
    this.candidatesService.getCandidates()
      .subscribe( data => {
        this.candidates = data;
		this.qualifies = this.candidates;
		this.prevus = this.candidates;		
      });

   /*this.candidateStatusService.getCandidatesStatuses()
      .subscribe( data => {
        this.candidatesStatuses = data;	
      });*/

   this.getArrayCandidate();

  }


getArrayCandidate(): void {
	     zip( this.candidateStatusService.getCandidatesStatuses(), this.candidateStatusService.getCandidatesByStatuses()).subscribe(values => {		 
	     this.candidatesByStatuses = values[1];
         this.candidatesStatuses = values[0];
       for (let [key, value] of Object.entries(this.candidatesByStatuses)) {
			var stat = {} as CandStat;						
            //stat.id = +key;
             stat.id = key.toString();
             //console.log(value);	
             for (let [key1, value1] of Object.entries(value)) {	          
			    stat.naming = key1;  	          	                   
				stat.candidates = value1;
			}
            this.trackys.push(stat);	
			//console.log(stat);	
		     
        }
	   });
}


  deleteCandidate(candidate: Candidates): void {
    this.candidatesService.deleteCandidate(candidate.id)
      .subscribe( data => {
        this.candidates = this.candidates.filter(u => u !== candidate);
      })
  };

  editCandidate(candidate: Candidates): void {
    window.localStorage.removeItem("editCandidateId");
    window.localStorage.setItem("editCandidateId", candidate.id.toString());
    this.router.navigate(['pages/candidates/edit-candidate']);
  };

  addCandidate(): void {
    this.router.navigate(['pages/candidates/add-candidate']);
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



 onTalkyDrop(event: CdkDragDrop<Candidates[]>) {
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
        var statusCandidate = {} as StatusCandidate;
        var statusDateCandidate = {} as StatusDateCandidate;	
        statusCandidate.id = +event.container.data[event.currentIndex].id;
        statusDateCandidate.id = +event.container.data[event.currentIndex].id;
        //statusCandidate.candidateStatus = +event.container.data[event.currentIndex].candidateStatus.id;	          
        //console.log(statusCandidate); 
        this.candidateStatusService.getCandidateStatusById(+event.container.id)
      			.subscribe( data => {  
	             this.codeTemp = data;
				 //console.log(this.codeTemp.code);
			     
				if(this.codeTemp.code=='RENDEZ_VOUS_PRIS' || this.codeTemp.code=='PROPALE_FAITE' || this.codeTemp.code=='PROPALE_ACCEPTEE') {
				   statusDateCandidate.candidateStatus = +event.container.id;
		           statusDateCandidate.dateUpdate = null;
		           this.dialogService.open(DialogDatePickerComponent)
		           .onClose.subscribe(dateUpdate => {this.dateUpdate = dateUpdate;
			           if(dateUpdate != null && dateUpdate != ""){
	                   statusDateCandidate.dateUpdate = dateUpdate;
                       //console.log(statusDateCandidate);
				       this.candidatesService.updateStatusDateCandidate(+event.container.data[event.currentIndex].id,statusDateCandidate)
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
				   statusCandidate.candidateStatus = +event.container.id;
			       this.candidatesService.updateStatusCandidate(+event.container.data[event.currentIndex].id,statusCandidate)
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

  onTrackDropy(event: CdkDragDrop<CandStat[]>) {
    moveItemInArray(event.container.data, event.previousIndex, event.currentIndex);
  }



}


export interface CandStat {
  id: string;
  naming: string;
  candidates: Candidates[];
}


