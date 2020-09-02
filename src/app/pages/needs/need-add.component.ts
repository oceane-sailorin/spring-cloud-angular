import {Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import { NeedsService } from './needs.service';
import Needs from './Needs';
import {first} from "rxjs/operators";
import Users from '../users/Users';
import NeedsStatuses from './NeedsStatuses';
import Priorities from './Priorities';
import Keywords from './Keywords';
import Clients from '../clients/Clients';
import { PrioritiesService } from './priority.service';
import { NeedStatusService } from './needStatus.service';
import { UploadFilesComponent } from '../upload-files/upload-files.component';
import { NbDialogService } from '@nebular/theme';
import { UploadFileService } from '../upload-files/upload-files.service';
import { HttpEventType, HttpResponse } from '@angular/common/http';


@Component({
  selector: 'app-need-add',
  templateUrl: './need-add.component.html',
  styleUrls: ['./need-add.component.scss']
})
export class NeedAddComponent implements OnInit {

  clients : Clients[];
  needsStatuses : NeedsStatuses[];
  priorities : Priorities[];
  commercials : Users[];
  submitted = false;
  selectedPriority = "Moyenne";
  selectedStatus = "Ouvert";
  selectedClient = 1;
  selectedCommercial = 1;
  dropdownList: Keywords[];
  selectedItems = [];
  dropdownSettings:{};
  fileUpload: File;
  progress = 0;
  message = '';
  newNeed = Needs;
  rand: any;
  fileName: string;
  



  constructor(private formBuilder: FormBuilder,private router: Router, private needsService: NeedsService, private prioritiesService: PrioritiesService, private needStatusService: NeedStatusService,private dialogService: NbDialogService, private uploadService: UploadFileService ) { }

  addForm: FormGroup;
  

  ngOnInit() {

	this.needsService.getKeywords()
      .subscribe( data => {
        this.dropdownList = data;
      });

    /*this.selectedItems = [
      { id: 3, code: 'Pune' },
      { id: 4, code: 'Navsari' }
    ];*/
    this.dropdownSettings = {
      singleSelection: false,
      idField: 'id',
      textField: 'code',
      selectAllText: 'Select All',
      unSelectAllText: 'UnSelect All',
      itemsShowLimit: 12,
      allowSearchFilter: true
    };
	
	this.needsService.getClients()
      .subscribe( data => {
        this.clients = data;
      });

	this.needsService.getCommercials()
      .subscribe( data => {
        this.commercials = data;
      });

	this.needStatusService.getNeedsStatuses()
      .subscribe( data => {
        this.needsStatuses = data;
      });


	this.prioritiesService.getPriorities()
      .subscribe( data => {
        this.priorities = data;
      });


    this.addForm = this.formBuilder.group({
      id: [''],
      title: ['', [Validators.required, Validators.maxLength(255)]],
      status: [null, Validators.required],
      priority: [null, Validators.required],
      poste: ['', Validators.maxLength(250)],
      departement: ['', Validators.maxLength(250)],
      description: [''],
      client: [null, Validators.required],
	  commercial: [null, Validators.required],   	 			 
	  dateCreation: [''],
      dateNeed: [''], 
      dateEnd: [''],
      keywords: [null]
    });

 

  

  }

// convenience getter for easy access to form fields
get f() { return this.addForm.controls; }

  onSubmit() {
	this.submitted = true;
	console.log(this.addForm.value);
	// stop here if form is invalid
        if (this.addForm.invalid) {
				console.log("invalid");
            return;
        }

    this.needsService.createNeed(this.addForm.value)      	    
	   .subscribe( data => {	 
		console.log(data)	;	
		if(this.fileUpload != null && this.fileUpload !== undefined) {
			this.rand = this.getRandomString(30);	
		    this.uploadService.uploadNeed(this.fileUpload,this.rand+".pdf",data ).subscribe(
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

        this.router.navigate(['pages/needs/get-need']);

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