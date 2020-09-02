import { Component, OnInit , Inject} from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { NeedsService } from './needs.service';
import Needs from './Needs';
import {first} from "rxjs/operators";
import Users from '../users/Users';
import Priorities from './Priorities';
import NeedsStatuses from './NeedsStatuses';
import Clients from '../clients/Clients';
import Keywords from './Keywords';
import { PrioritiesService } from './priority.service';
import { NeedStatusService } from './needStatus.service';
import { formatDate } from '@angular/common';
import { UploadFilesComponent } from '../upload-files/upload-files.component';
import { NbDialogService } from '@nebular/theme';
import { UploadFileService } from '../upload-files/upload-files.service';
import { HttpEventType, HttpResponse } from '@angular/common/http';

@Component({
  selector: 'app-need-edit',
  templateUrl: './need-edit.component.html',
  styleUrls: ['./need-edit.component.scss']
})
export class NeedEditComponent implements OnInit {

  need: Needs;
  editForm: FormGroup;
  editable: boolean = false;
  clients : Clients[];
  needsStatuses : NeedsStatuses[];
  priorities : Priorities[];
  commercials : Users[];
  submitted = false;
  selectedClient = 0;
  selectedStatus = 0;
  selectedPriority = 0;
  selectedCommercial = 0;
  selectedDateNeed = null;
  dropdownList: Keywords[];
  selectedItems = [];
  dropdownSettings:{};
  fileUpload: File;
  progress = 0;
  message = '';
  newNeed = Needs;
  rand: any;
  currentId: number;
  currentUrl: any;
  newFile: string;

  constructor(private formBuilder: FormBuilder,private router: Router, private needsService: NeedsService, private prioritiesService: PrioritiesService, private needStatusService: NeedStatusService,private dialogService: NbDialogService, private uploadService: UploadFileService) { }

  ngOnInit() {
    let needId = window.localStorage.getItem("editNeedId");
    this.currentId = +needId;
    if(!needId) {
      alert("Invalid action.")
      this.router.navigate(['pages/needs/get-need']);
      return;
    }

	this.needsService.getKeywords()
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
   
    this.editForm = this.formBuilder.group({
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
      keywords: [null],
      filename: ['']
   
 	});
    this.needsService.getNeedById(+needId)
      .subscribe( data => {
        this.editForm.setValue(data);
        this.selectedClient = this.editForm.controls.client.value.id;
        this.selectedStatus = this.editForm.controls.status.value.id;
        this.selectedPriority = this.editForm.controls.priority.value.id;
        this.selectedCommercial = this.editForm.controls.commercial.value.id;
        //this.selectedDateNeed = this.editForm.controls.dateNeed.value;
        this.selectedItems = this.editForm.controls.keywords.value;    
        this.currentUrl = this.uploadService.getUrl(this.editForm.controls.filename.value);
	    //console.log(this.currentUrl);
       
         //console.log(this.currentUrl);
         //console.log(this.selectedItems);
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
    this.needsService.updateNeed(this.editForm.value)
      .pipe(first())
      .subscribe(
        data => {
          if(this.editForm.status === "VALID") {
            alert('Need updated successfully.'); 
			
			if(this.fileUpload != null && this.fileUpload !== undefined) {
				this.rand = this.getRandomString(30);	
			    this.uploadService.uploadNeed(this.fileUpload,this.rand+".pdf",this.currentId).subscribe(
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