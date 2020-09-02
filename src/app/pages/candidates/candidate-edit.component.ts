import { Component, OnInit , Inject} from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { CandidatesService } from './candidates.service';
import Candidates from './Candidates';
import {first} from "rxjs/operators";
import User from '../users/Users';
import CandidateStatus from './CandidateStatus';
import Sources from './Sources';
import Users from '../users/Users';
import Countries from './Countries';
import Keywords from '../needs/Keywords';
import { UploadFilesComponent } from '../upload-files/upload-files.component';
import { NbDialogService } from '@nebular/theme';
import { UploadFileService } from '../upload-files/upload-files.service';
import { HttpEventType, HttpResponse } from '@angular/common/http';
import { IDropdownSettings } from 'ng-multiselect-dropdown';

@Component({
  selector: 'app-candidate-edit',
  templateUrl: './candidate-edit.component.html',
  styleUrls: ['./candidate-edit.component.css']
})
export class CandidateEditComponent implements OnInit {

  candidate: Candidates;
  editForm: FormGroup;
  editable: boolean = false;
  countries : Countries[];
  candidatesStatuses : CandidateStatus[];
  sources : Sources[];
  recruiters : Users[];
  sourcers : Users[];
  submitted = false;
  selectedGender = "MR";
  selectedCountry = 62;
  selectedSource = 0;
  selectedStatus = 0;
  selectedCandidateStatus = 0;
  selectedRecruiter = 0;
  selectedSourcer = 0;
  dropdownList: Keywords[];
  selectedItems = [];
  dropdownSettings:IDropdownSettings;
  fileUpload: File;
  progress = 0;
  message = '';
  newCandidate = Candidates;
  rand: any;
  currentId: number;
  currentUrl: any;
  newFile: string;

  constructor(private formBuilder: FormBuilder,private router: Router, private candidatesService: CandidatesService,private dialogService: NbDialogService, private uploadService: UploadFileService) { }

  ngOnInit() {
    let candidateId = window.localStorage.getItem("editCandidateId");
    this.currentId = +candidateId;
    if(!candidateId) {
      alert("Invalid action.")
      this.router.navigate(['candidate-get']);
      return;
    }

	this.candidatesService.getKeywords()
      .subscribe( data => {
        this.dropdownList = data;
      });

    this.dropdownSettings = {
      singleSelection: false,
      idField: 'id',
      textField: 'code',
      selectAllText: 'Select All',
      unSelectAllText: 'UnSelect All',
      itemsShowLimit: 12,
      allowSearchFilter: true
    };

	this.candidatesService.getCountries()
      .subscribe( data => {
        this.countries = data;
      });

	this.candidatesService.getSources()
      .subscribe( data => {
        this.sources = data;
      });

	this.candidatesService.getRecruiters()
      .subscribe( data => {
        this.recruiters = data;
      });

	this.candidatesService.getSourcers()
      .subscribe( data => {
        this.sourcers = data;
      });

	this.candidatesService.getCandidatesStatuses()
      .subscribe( data => {
        this.candidatesStatuses = data;
      });

    this.editForm = this.formBuilder.group({
      id: [''],
      identity: ['', Validators.required],
      gender: ['', Validators.required],
      firstname: ['', Validators.required],
      lastname: ['', Validators.required],
	  email: [''],
      phone: [''],
      address: [''],
      country: [null, Validators.required],
	  recruiter: [null, Validators.required],
      sourcer: [null, Validators.required],
      source: [null, Validators.required],
      candidateStatus: [null, Validators.required],
	  stack: [''],
	  profile: [''],
	  salary: [''], 				 			 
	  dateDisponibility: [''],
      dateSiiInterview: [''], 
      dateSourcing: [''], 
      dateProposition: [''],
	  dateSignature: [''],
	  dateLastUpdate: [''],
      keywords: [null],
      filename: ['']
   
 	});
    this.candidatesService.getCandidateById(+candidateId)
      .subscribe( data => {
        this.editForm.setValue(data);
        this.selectedGender = this.editForm.controls.gender.value;
        this.selectedCountry = this.editForm.controls.country.value.id;
        this.selectedSource = this.editForm.controls.source.value.id;
		this.selectedCandidateStatus = this.editForm.controls.candidateStatus.value.id;
		this.selectedRecruiter = this.editForm.controls.recruiter.value.id;
		this.selectedSourcer = this.editForm.controls.sourcer.value.id;
		this.selectedItems = this.editForm.controls.keywords.value;    
        this.currentUrl = this.uploadService.getUrl(this.editForm.controls.filename.value);
      console.log(this.selectedCountry);
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
    this.candidatesService.updateCandidate(this.editForm.value)
      .pipe(first())
      .subscribe(
        data => {
			if(this.editForm.status === "VALID") {
            alert('Candidate updated successfully.'); 
			
			if(this.fileUpload != null && this.fileUpload !== undefined) {
				this.rand = this.getRandomString(30);	
			    this.uploadService.uploadCandidate(this.fileUpload,this.rand+".pdf",this.currentId).subscribe(
			      event => {
			        if (event.type === HttpEventType.UploadProgress) {
			          this.progress = Math.round(100 * event.loaded / event.total);
			        } else if (event instanceof HttpResponse) {
			          this.message = event.body.message;
					  console.log(this.message);         
			        }
			      },
			      err => {
			        this.progress = 0;
			        this.message = 'Could not upload the file!';
					console.log(this.message);
			        this.fileUpload = undefined;
			      });
	         };
            this.router.navigate(['pages/candidates/get-candidate']);
          }else {
            alert(this.editForm.getError);
          }
        },
        error => {
          alert(error);
        });
  }

onItemSelect(item: any) {
    console.log(item);
  }
  onSelectAll(items: any) {
    console.log(items);
  }

 openUpload() {
    this.dialogService.open(UploadFilesComponent)
      .onClose.subscribe(file => {this.fileUpload = file;
      //console.log("upload="+this.fileUpload) ; 
      this.newFile = this.fileUpload.name;
      });
    
  }


  getRandomString(length: number) {
    var randomChars = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
    var result = '';
    for ( var i = 0; i < length; i++ ) {
        result += randomChars.charAt(Math.floor(Math.random() * randomChars.length));
    }
    return result;
 }


}