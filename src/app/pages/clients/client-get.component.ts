import {Component, OnInit , Inject} from '@angular/core';
import {Router} from "@angular/router";
import Clients from './Clients';
import { ClientsService } from './clients.service';

@Component({
  selector: 'app-client-get',
  templateUrl: './client-get.component.html',
  styleUrls: ['./client-get.component.scss']
})
export class ClientGetComponent implements OnInit {

  clients: Clients[];

  constructor(private router: Router, private clientsService: ClientsService) { }

  ngOnInit() {
    if(!window.localStorage.getItem('token')) {
      this.router.navigate(['login']);
      return;
    }
    this.clientsService.getClients()
      .subscribe( data => {
        this.clients = data;
      });
  }

  deleteClient(client: Clients): void {
    this.clientsService.deleteClient(client.id)
      .subscribe( data => {
        this.clients = this.clients.filter(u => u !== client);
      })
  };

  editClient(client: Clients): void {
    window.localStorage.removeItem("editClientId");
    window.localStorage.setItem("editClientId", client.id.toString());
    this.router.navigate(['pages/clients/edit-client']);
  };

  addClient(): void {
    this.router.navigate(['pages/clients/add-client']);
  };
}