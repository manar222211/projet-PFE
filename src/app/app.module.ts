import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AdminComponent } from './admin/admin.component';
import { EmployerComponent } from './employer/employer.component';
import { LeavesComponent } from './leaves/leaves.component';
import { HomeComponent } from './home/home.component';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { SidebarComponent } from './sidebar/sidebar.component';
import { AddAdminComponent } from './add-admin/add-admin.component';
import { UpdateAdminComponent } from './update-admin/update-admin.component';
import { DetailAdminComponent } from './detail-admin/detail-admin.component';
import { AddEmployerComponent } from './add-employer/add-employer.component';
import { UpdateEmployerComponent } from './update-employer/update-employer.component';
import { DetailEmployerComponent } from './detail-employer/detail-employer.component';
import { AddLeavesComponent } from './add-leaves/add-leaves.component';
import { DetailLeavesComponent } from './detail-leaves/detail-leaves.component';
import { UpdateLeavesComponent } from './update-leaves/update-leaves.component';
import { LoginComponent } from './login/login.component';
import { HttpClientModule } from '@angular/common/http';
import { UsersComponent } from './users/users.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { SendmailComponent } from './sendmail/sendmail.component';
import { ProfileComponent } from './profile/profile.component';
import { IntegrationAIComponent } from './integration-ai/integration-ai.component';
import { NgxPaginationModule } from 'ngx-pagination';
import { LeavesbyuserComponent } from './leavesbyuser/leavesbyuser.component';




@NgModule({
  declarations: [
    AppComponent,
    AdminComponent,
    EmployerComponent,
    LeavesComponent,
    HomeComponent,
    HeaderComponent,
    FooterComponent,
    SidebarComponent,
    AddAdminComponent,
    UpdateAdminComponent,
    DetailAdminComponent,
    AddEmployerComponent,
    UpdateEmployerComponent,
    DetailEmployerComponent,
    AddLeavesComponent,
    DetailLeavesComponent,
    UpdateLeavesComponent,
    LoginComponent,
    UsersComponent,
    SendmailComponent,
    ProfileComponent,
    IntegrationAIComponent,
    LeavesbyuserComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    NgxPaginationModule


    
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
