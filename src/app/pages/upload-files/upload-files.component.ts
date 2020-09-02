import { Component, OnInit } from '@angular/core';
import { UploadFileService } from './upload-files.service';
import { HttpEventType, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { NbDialogRef } from '@nebular/theme';

@Component({
  selector: 'app-upload-files',
  templateUrl: './upload-files.component.html',
  styleUrls: ['./upload-files.component.scss']
})
export class UploadFilesComponent implements OnInit {

  selectedFiles: FileList;
  currentFile: File;
  progress = 0;
  message = '';

  fileInfos: Observable<any>;

  constructor(protected ref: NbDialogRef<UploadFilesComponent>, private uploadService: UploadFileService) { }

  ngOnInit() {
    this.fileInfos = this.uploadService.getFiles();
  }

  selectFile(event) {
    this.selectedFiles = event.target.files;
  }

  upload() {
    this.progress = 0;

    this.currentFile = this.selectedFiles.item(0);
    //console.log(this.currentFile);
   

    this.selectedFiles = undefined;
    this.ref.close(this.currentFile);
  }

 cancel() {
    this.ref.close();
  }

  submit(file: any) {
	//console.log(file);
    this.ref.close(file);
  }
}