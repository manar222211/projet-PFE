import { Component, OnInit } from '@angular/core';
import { AllmyservicesService } from '../services/allmyservices.service';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-employer',
  templateUrl: './employer.component.html',
  styleUrls: ['./employer.component.css']
})
export class EmployerComponent  implements OnInit{
  listemployer:any

  iduserconnected:String=""
  roleuser:String=""

  constructor(private service :AllmyservicesService,private router:Router){}

  allemployer(){

    let formdata=new FormData()

    this.service.AllEmploye().subscribe(

      (res)=>{console.log("la list de employer:",res);
  
        this.listemployer=res;
      },
  
      (error)=>{console.log("error",error)}
    )
  
  }

  ngOnInit(): void {

    this.roleuser=localStorage.getItem("roleuserconnected")
    this.allemployer()
      
  }

  detailemploye(id : String){
    
    this.router.navigateByUrl("/detailemployer/"+id)
  }

  updateemploye(id:String){
    
    this.router.navigateByUrl("/updateemployer/"+id)
  }
deleteemploye(id:String){
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
    this.allemployer();
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
