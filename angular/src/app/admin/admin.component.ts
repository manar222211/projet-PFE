import { Component, OnInit } from '@angular/core';
import { AllmyservicesService } from '../services/allmyservices.service';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent  implements OnInit{

listadmin: any

  
constructor(private service:AllmyservicesService,private router : Router){}

alladmin(){

  let formdata = new FormData()


  this.service.AllAdmin().subscribe(
    (res)=>{console.log("la list de admin:",res);

      this.listadmin=res;
    },

    (error)=>{console.log("error",error)}

  )
  
}

 ngOnInit(): void {

    this.alladmin()
      
  }



  detailadmin(id : String){
    
    this.router.navigateByUrl("/detailadmin/"+id)
  }

  updateadmin(id:String){
    this.router.navigateByUrl("/updateadmin/"+id)
  }

  deleteadmin(id:String){
    Swal.fire({
      title: "Are you sure?",
      text: "You won't be able to revert this!",
      icon: "warning",
      showCancelButton: true,
      confirmButtonColor: "#3085d6",
      cancelButtonColor: "#d33",
      confirmButtonText: "Yes, delete it!"
    }).then((result) => {
      if (result.isConfirmed) {

this.service.DeleteAdmin(id).subscribe(
  (res)=>{console.log("deleted");
    this.alladmin();
  },
  (error)=>{console.log("erreur",error)}
)

     Swal.fire({
          title: "Deleted!",
          text: "Your file has been deleted.",
          icon: "success"
        });
      }
    });

    
    
  }

}
