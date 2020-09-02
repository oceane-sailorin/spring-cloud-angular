import {Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import { CandidatesService } from './candidates.service';
import Candidates from './Candidates';
import {first} from "rxjs/operators";
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
  selector: 'app-candidate-add',
  templateUrl: './candidate-add.component.html',
  styleUrls: ['./candidate-add.component.scss']
})
export class CandidateAddComponent implements OnInit {

  countries : Countries[];
  candidatesStatuses : CandidateStatus[];
  sources : Sources[];
  recruiters : Users[];
  sourcers : Users[];
  submitted = false;
  selectedGender = "MR";
  selectedCountry = 62;
  selectedSource = 1;
  selectedStatus = 1;
  selectedCandidateStatus = 1;
  selectedRecruiter = 1;
  selectedSourcer = 1;
  dropdownList: Keywords[];
  selectedItems = [];
  fileUpload: File;
  progress = 0;
  message = '';
  newCandidate = Candidates;
  rand: any;
  fileName: string;
  dropdownSettings:IDropdownSettings;

  constructor(private formBuilder: FormBuilder,private router: Router, private candidatesService: CandidatesService,private dialogService: NbDialogService, private uploadService: UploadFileService ) { }

  addForm: FormGroup;

  ngOnInit() {
	
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


    this.addForm = this.formBuilder.group({
      id: [''],
      identity: ['', [Validators.required, Validators.maxLength(50)]],
      gender: ['', Validators.required],
      firstname: ['', [Validators.required, Validators.maxLength(50)]],
      lastname: ['', [Validators.required, Validators.maxLength(50)]],
	  email: ['', Validators.email],
      phone: ['', Validators.maxLength(20)],
      address: [''],
      country: [null, Validators.required],
	  recruiter: [null, Validators.required],
      sourcer: [null, Validators.required],
      source: [null, Validators.required],
      candidateStatus: [null, Validators.required],
	  stack: ['', Validators.maxLength(250)],
	  profile: ['', Validators.maxLength(250)],
	  salary: ['', Validators.maxLength(250)],			 			 
	  dateDisponibility: [''],
      dateSiiInterview: [''], 
      dateSourcing: [''], 
      dateProposition: [''],
	  dateSignature: [''],
	  dateLastUpdate: [''],
      keywords: [null]
    });

 

  

  }

// convenience getter for easy access to form fields
get f() { return this.addForm.controls; }

  onSubmit() {
	this.submitted = true;
	// stop here if form is invalid
        if (this.addForm.invalid) {
            return;
        }

    this.candidatesService.createCandidate(this.addForm.value)
      .subscribe( data => {
	        console.log(data);
            console.log(this.fileUpload);
            if(this.fileUpload != null && this.fileUpload !== undefined) {
			this.rand = this.getRandomString(30);	
		    this.uploadService.uploadCandidate(this.fileUpload,this.rand+".pdf",data ).subscribe(
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
         }	
        this.router.navigate(['pages/candidates/get-candidate']);

      });

  }

   onReset() {
        // reset whole form back to initial state
        this.submitted = false;
        this.addForm.reset();
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
      console.log("upload="+this.fileUpload.name) ; 
      this.fileName = this.fileUpload.name;
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