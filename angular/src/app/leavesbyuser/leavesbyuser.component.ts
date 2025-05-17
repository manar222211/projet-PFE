import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { AllmyservicesService } from '../services/allmyservices.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-leavesbyuser',
  templateUrl: './leavesbyuser.component.html',
  styleUrls: ['./leavesbyuser.component.css']
})
export class LeavesbyuserComponent implements OnInit {
 
  listleaves: any; // ← plus sûr que just "any"

 iduserconnected:String=""
  roleuserconnected:String=""
form:FormGroup
c:number=5;
searchText:String=""
iduser:any

  constructor(private service:AllmyservicesService,private router:Router,private formbuilder:FormBuilder){}

  allLeavesbyuser(id:string):void{
  this.service.allLeavesbyuser(this.iduserconnected).subscribe(
    (res)=>{console.log("la list des leaves:",res);
      this.listleaves=res;
    },
    (error)=>{console.log("error",error)}
  )
}

   
  ngOnInit(): void {

    this.iduserconnected = localStorage.getItem("iduser") || ''; // ← ou récupère via token
    
    this.allLeavesbyuser(this.iduser);

   
      
  }
  detailleaves(id : String){
    
    this.router.navigateByUrl("/detailleaves/"+id)
  }

  updateleaves(id :String){
    this.router.navigateByUrl("/updateleaves/"+id)



   

  }
  search(): void {
    if (!Array.isArray(this.listleaves)) {
      console.error("La liste des congés est vide ou invalide !");
      return;
    }
  
    if (!this.searchText.trim()) {
      // Si champ vide, recharge les congés
      this.allLeavesbyuser(this.iduser);
    } else {
      this.listleaves = this.listleaves.filter(item =>
        item.userDTO?.name?.toLowerCase().includes(this.searchText.toLowerCase())
      );
    }
  }
  

}

