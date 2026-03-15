
import {Component} from '@angular/core';

@Component({
selector:'app-navbar',
template:`
<mat-toolbar color="primary">
Fitness Gym
<span style="flex:1 1 auto"></span>
<a mat-button routerLink="/dashboard">Dashboard</a>
<a mat-button routerLink="/trainers">Trainers</a>
<a mat-button routerLink="/membership">Membership</a>
<a mat-button routerLink="/booking">Booking</a>
<a mat-button routerLink="/payments">Payments</a>
</mat-toolbar>
`
})
export class NavbarComponent{}
