import { NbWindowRef } from '@nebular/theme';
import {Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import CandidatesNeeds from '../CandidatesNeeds';
import { CandidatesNeedsService } from '../candidates-needs.service';
import {first} from "rxjs/operators";
import NeedCandidateStatus from '../NeedCandidateStatus';
import { NeedCandidateStatusService } from '../needcandidatestatus.service';
import Needs from '../../needs/Needs';
import { NeedsService } from '../../needs/needs.service';
import Candidates from '../../candidates/Candidates';
import { CandidatesService } from '../../candidates/candidates.service';

@Component({
  selector: 'app-add-candidate-dashboard',
  templateUrl: './add-candidate-need.component.html',
  styleUrls: ['add-candidate-need.component.scss'],
})
export class AddCandidateNeedComponent implements OnInit {
	
  needs : Needs[];
  needCandidateStatuses : NeedCandidateStatus[];
  candidates : Candidates[];

  submitted = false;
  selectedStatus = 1;
  selectedNeed = 1;
  progress = 0;
  message = '';
  newCandidateNeed = CandidatesNeeds;

  constructor(public windowRef: NbWindowRef,private formBuilder: FormBuilder,private router: Router, private needsService: NeedsService,private candidatesService: CandidatesService, private candidatesNeedsService: CandidatesNeedsService, private needCandidateStatusService: NeedCandidateStatusService ) {}

addForm: FormGroup;
  

  ngOnInit() {

	

    this.addForm = this.formBuilder.group({
      id: [''],
      need: [null, Validators.required],
	  candidate: [null, Validators.required],
	  needCandidateStatus: [null, Validators.required],	 
	  dateCreation: [''],
      dateSent: [''], 
      interviewDate: [''],
	  dateFeedback: [''],
	  dateOk: [''],
	  dateKoClient: [''],
	  dateKoCandidate: ['']
    });

  

  }

get f() { return this.addForm.controls; }

  onSubmit() {
	this.submitted = true;
	console.log(this.addForm.value);
	// stop here if form is invalid
        if (this.addForm.invalid) {
				console.log("invalid");
            return;
        }

    this.candidatesNeedsService.createCandidateNeed(this.addForm.value)      	    
	   .subscribe( data => {	 
		console.log(data)	;		
        this.router.navigate(['pages/candidates-needs/get-candidate-need']);

      });

  }


  close() {
    this.windowRef.close();
  }
}