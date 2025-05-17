import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminComponent } from './admin/admin.component';
import { EmployerComponent } from './employer/employer.component';
import { LeavesComponent } from './leaves/leaves.component';
import { HomeComponent } from './home/home.component';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { SidebarComponent } from './sidebar/sidebar.component';
import { AddAdminComponent } from './add-admin/add-admin.component';
import { DetailAdminComponent } from './detail-admin/detail-admin.component';
import { UpdateAdminComponent } from './update-admin/update-admin.component';
import { AddEmployerComponent } from './add-employer/add-employer.component';
import { DetailEmployerComponent } from './detail-employer/detail-employer.component';
import { UpdateEmployerComponent } from './update-employer/update-employer.component';
import { AddLeavesComponent } from './add-leaves/add-leaves.component';
import { DetailLeavesComponent } from './detail-leaves/detail-leaves.component';
import { UpdateLeavesComponent } from './update-leaves/update-leaves.component';
import { UsersComponent } from './users/users.component';
import { LoginComponent } from './login/login.component';
import { SendmailComponent } from './sendmail/sendmail.component';
import { ProfileComponent } from './profile/profile.component';
import { IntegrationAIComponent } from './integration-ai/integration-ai.component';
import { LeavesbyuserComponent } from './leavesbyuser/leavesbyuser.component';

const routes: Routes = [

  
 {path:"admin" , component:AdminComponent},
{path:"addadmin",component:AddAdminComponent},
{path:"detailadmin/:id",component:DetailAdminComponent},
{path:"updateadmin/:id", component:UpdateAdminComponent},

 {path:"employer", component:EmployerComponent},
 {path:"addemployer",component:AddEmployerComponent},
 {path:"detailemployer/:id",component:DetailEmployerComponent},
 {path:"updateemployer/:id",component:UpdateEmployerComponent},

 {path:"leaves", component:LeavesComponent},
{path:"addleaves", component:AddLeavesComponent},
{path:"detailleaves/:id",component:DetailLeavesComponent},
{path:"updateleaves/:id",component:UpdateLeavesComponent},


 {path:"home",component:HomeComponent},

 {path:"users",component:UsersComponent},

 {path:"",component:LoginComponent},

 {path:"sendmail",component:SendmailComponent},
{path:"profile",component:ProfileComponent},
{path:"integrationai",component:IntegrationAIComponent},
 
{path:"leavesbyuser",component:LeavesbyuserComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
