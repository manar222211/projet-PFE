import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, ParamMap, Router } from '@angular/router';
import { AllmyservicesService } from '../services/allmyservices.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent 
implements OnInit{
  iduserconnected:String=""
  roleuserconnected:String=""

datailemploye:any;

constructor (private route:ActivatedRoute,
private service:AllmyservicesService,
private router:Router
){}
getemployedetail(id:String){
  this.service.DetailAdmin(id).subscribe(
    (res:any)=>{
      console.log("les detail de l'Empoye", res);
      this.datailemploye=res;
    },
    (error: any)=>{
      console.log("Erreur",error);
    }
    );
  }



  ngOnInit(): void {



   this.iduserconnected=localStorage.getItem("iduserconnected")
        if (this.iduserconnected){
          this.getemployedetail(this.iduserconnected);
        }
    
      

        this.roleuserconnected=localStorage.getItem("roleuserconnected")
  }


  updateadmin(id:String){
    
    this.router.navigateByUrl("/updateadmin/"+this.iduserconnected)
  }
}
