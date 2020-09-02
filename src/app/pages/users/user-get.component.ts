import {Component, OnInit , Inject} from '@angular/core';
import {Router} from "@angular/router";
import Users from './Users';
import { UsersService } from './users.service';


@Component({
  selector: 'app-user-get',
  templateUrl: './user-get.component.html',
  styleUrls: ['./user-get.component.scss']
})
export class UserGetComponent implements OnInit {

  users: Users[];

  constructor(private router: Router, private usersService: UsersService) { }

  ngOnInit() {
    if(!window.localStorage.getItem('token')) {
      this.router.navigate(['login']);
      return;
    }
    this.usersService.getUsers()
      .subscribe( data => {
        this.users = data;
      });
  }

  deleteUser(user: Users): void {
    this.usersService.deleteUser(user.id)
      .subscribe( data => {
        this.users = this.users.filter(u => u !== user);
      })
  };

  editUser(user: Users): void {
    window.localStorage.removeItem("editUserId");
    window.localStorage.setItem("editUserId", user.id.toString());
    this.router.navigate(['pages/users/edit-user']);
  };

  addUser(): void {
    this.router.navigate(['pages/users/add-user']);
  };
}