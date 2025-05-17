import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, ParamMap, Router } from '@angular/router';
import { AllmyservicesService } from '../services/allmyservices.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-detail-employer',
  templateUrl: './detail-employer.component.html',
  styleUrls: ['./detail-employer.component.css']
})
export class DetailEmployerComponent implements OnInit{


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



    this .route.paramMap.subscribe((params: ParamMap)=>{
        const adminId = params.get('id');
        if (adminId){
          this.getemployedetail(adminId);
        }
      })
      


      
  }
  

}
