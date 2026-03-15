
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule } from '@angular/router';

import { MatToolbarModule } from '@angular/material/toolbar';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';

import { AppComponent } from './app.component';
import { NavbarComponent } from './navbar/navbar.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { TrainersComponent } from './trainers/trainers.component';
import { MembershipComponent } from './membership/membership.component';
import { BookingComponent } from './booking/booking.component';
import { PaymentsComponent } from './payments/payments.component';

@NgModule({
declarations:[
AppComponent,
NavbarComponent,
LoginComponent,
RegisterComponent,
DashboardComponent,
TrainersComponent,
MembershipComponent,
BookingComponent,
PaymentsComponent
],
imports:[
BrowserModule,
BrowserAnimationsModule,
FormsModule,
HttpClientModule,
MatToolbarModule,
MatCardModule,
MatButtonModule,
RouterModule.forRoot([
{path:'',component:LoginComponent},
{path:'register',component:RegisterComponent},
{path:'dashboard',component:DashboardComponent},
{path:'trainers',component:TrainersComponent},
{path:'membership',component:MembershipComponent},
{path:'booking',component:BookingComponent},
{path:'payments',component:PaymentsComponent}
])
],
bootstrap:[AppComponent]
})
export class AppModule{}
