import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AllmyservicesService } from '../services/allmyservices.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent 
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
    alert(id);
    this.router.navigateByUrl("/updateadmin/"+this.iduserconnected)
  }
  logout()
  {
    Swal.fire({
      title: 'Are you sure you want to logout?',
      text: 'Your session will end.',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Logout',         // <- Only this button logs out
      cancelButtonText: 'Cancel',
      confirmButtonColor: '#3085D6',
      cancelButtonColor: '#d33',
      allowOutsideClick: false,
      allowEscapeKey: false
    }).then((result) => {
      if (result.isConfirmed) {            // <- User clicked "Logout"
        this.service.logout().subscribe(
          (res)=>{console.log("sucess to logout");
            localStorage.removeItem("iduserconnected");
            localStorage.removeItem("roleuserconnected")
            Swal.fire({
              title: 'Logged Out',
              text: 'You have been successfully logged out.',
              icon: 'success',
              timer: 1500,
              showConfirmButton: false,
            });
            this.router.navigate(['/'], {
              queryParams: { message: 'You have been logged out.' },
            });
          },
          (error)=>{console.log(error)}
        )
      } else {
        // Cancel button clicked — do nothing
        console.log("Logout cancelled.");
      }
    });



  }
}