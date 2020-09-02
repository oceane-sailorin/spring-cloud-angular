import {Component, OnInit , Inject,  Input} from '@angular/core';
import {Router} from "@angular/router";

import Candidates from './Candidates';
import CandidateStatus from './CandidateStatus';
import { CandidatesService } from './candidates.service';
import { CandidateStatusService } from './candidatestatus.service';
import { LocalDataSource } from 'ng2-smart-table';

import { SmartTableData } from '../../@core/data/smart-table';


@Component({
  selector: 'app-candidate-get',
  templateUrl: './candidate-get.component.html',
  styleUrls: ['./candidate-get.component.scss']
})
export class CandidateGetComponent implements OnInit {
    
  candidates: Candidates[];
  candidatesStatuses: CandidateStatus[];
  candidatesByStatuses: any;
  candidateStat: CandidateStatus;
  

settings = {
	hideSubHeader: true,  
	actions: {
	      columnTitle: 'Actions',
	      add: false,
	      edit: false,
          delete: false,
	      custom: [  
          { name: 'deleteCandidate', title: '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<i class="nb-trash" ></i>'},
	      { name: 'editCandidate', title: '<i class="nb-edit" ></i>'},
           ],
	      position: 'right'
	    },
	 columns: {
      id: {
        title: 'ID',
        type: 'number',
      },
      firstname: {
        title: 'FirstName',
        type: 'string',
      },
      lastname: {
        title: 'LastName',
        type: 'string',
      },
      phone: {
        title: 'Phone',
        type: 'string',
      },
      email: {
        title: 'E-mail',
        type: 'string',
      },
      candidateStatus: {
        title: 'Status',
        valuePrepareFunction: (value) => { return value.naming }
      },
    },
    mode: 'external',
  };


  constructor(private router: Router, private candidatesService: CandidatesService, private candidateStatusService: CandidateStatusService) {
	
 }


  ngOnInit() {
    if(!window.localStorage.getItem('token')) {
      this.router.navigate(['login']);
      return;
    }
    this.candidatesService.getCandidates()
      .subscribe( data => {
        this.candidates = data;
      });

   /*this.candidateStatusService.getCandidatesStatuses()
      .subscribe( data => {
        this.candidatesStatuses = data;	
      });*/


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

  onCustomAction(event) {
	console.log(event);
      switch ( event.action) {
        case 'editCandidate':
          this.editCand(event.data);
          break;
       case 'deleteCandidate':
          this.deleteCand(event.data);
      }
    }

  editCand(candidate) {
    window.localStorage.removeItem("editCandidateId");
    window.localStorage.setItem("editCandidateId", candidate.id.toString());
    this.router.navigate(['pages/candidates/edit-candidate']);
  };

 deleteCand(candidate) {
	console.log(candidate);
    this.candidatesService.deleteCandidate(candidate.id)
      .subscribe( data => {
        this.candidates = this.candidates.filter(u => u !== candidate);
      });
  };

}

