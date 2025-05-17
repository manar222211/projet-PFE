import { Component, OnInit } from '@angular/core';
import { AllmyservicesService } from '../services/allmyservices.service';
import { ActivatedRoute, ParamMap, Router } from '@angular/router';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-detail-leaves',
  templateUrl: './detail-leaves.component.html',
  styleUrls: ['./detail-leaves.component.css']
})
export class DetailLeavesComponent implements OnInit {

  detailleaves:any;
  iduserconnected:String=""
  roleuser:String=""

  
constructor(private service:AllmyservicesService,
  private route:ActivatedRoute,
  private router:Router
){}

getdetailleaves(id:String){
  this.service.DetailLeaves(id).subscribe(
    (res)=>{console.log("detail des leaves:",res);
      this.detailleaves=res;
    },
    (error)=>{console.log("error",error)}
  )
}

  ngOnInit(): void {

    this.route.paramMap.subscribe((params:ParamMap)=>{
      const leavesId = params.get('id');
      if(leavesId){

        this.getdetailleaves(leavesId);
        this.roleuser=localStorage.getItem("roleuserconnected")


      }
    })
   
     
  }
  deleteleaves(id:String){
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
  
  this.service.DeleteLeaves(id).subscribe(
    (res)=>{console.log("deleted");
      this.router.navigateByUrl("/leaves");
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
