import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, ParamMap, Router } from '@angular/router';
import { AllmyservicesService } from '../services/allmyservices.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-detail-admin',
  templateUrl: './detail-admin.component.html',
  styleUrls: ['./detail-admin.component.css']
})
export class DetailAdminComponent  implements OnInit{

detailadmin:any;
iduserconnected:String=""
roleuserconnected:String=""


constructor(
  private route:ActivatedRoute,
  private service:AllmyservicesService,
  private router:Router
){}

getadmindetail(id:String){
  this.service.DetailAdmin(id).subscribe(
    (res:any)=>{
      console.log("les detail de l'admin", res);
      this.detailadmin=res;
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
      this.getadmindetail(adminId);
    }
  })





  this.roleuserconnected=localStorage.getItem("roleuserconnected")
      console.log("role",this.roleuserconnected);
  
      this.iduserconnected=localStorage.getItem("iduserconnected")
          if (this.iduserconnected && this.roleuserconnected=='admin'){
  
  
          
  
          }
         else{
                   console.log("Accès refusé ou rôle non autorisé. Redirection...");
                   Swal.fire({
                     icon: 'warning',
                     title: 'Accès refusé',
                     text: 'Vous êtes connecté en tant qu’utilisateur, l’accès à cette page est réservé aux administrateurs.',
                     confirmButtonText: 'Retour à l’accueil',
                     confirmButtonColor: '#3085d6',
                     backdrop: true,
                     allowOutsideClick: false,
                     allowEscapeKey: false
                   }).then((result) => {
                     if (result.isConfirmed) {
                       this.router.navigate(['/home']);
                     }
                   });
                 }
  
      console.log("iduserconcontedheee",this.iduserconnected);
      
  }

}

