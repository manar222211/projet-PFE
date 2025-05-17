import { Component, OnInit } from '@angular/core';
import { AllmyservicesService } from '../services/allmyservices.service';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';
import { FormBuilder, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-leaves',
  templateUrl: './leaves.component.html',
  styleUrls: ['./leaves.component.css']
})
export class LeavesComponent implements OnInit {
  
  listleaves:any 

form:FormGroup
c:number=5;
searchText:String=""


  constructor(private service:AllmyservicesService,private router:Router,private formbuilder:FormBuilder){}

allLeaves(){
  this.service.AllLeaves().subscribe(
    (res)=>{console.log("la list des leaves:",res);
      this.listleaves=res;
    },
    (error)=>{console.log("error",error)}
  )
}

   
ngOnInit(): void {this.allLeaves();  }

  detailleaves(id : String){
    
    this.router.navigateByUrl("/detailleaves/"+id)
  }

  updateleaves(id :String){
    this.router.navigateByUrl("/updateleaves/"+id)



   

  }
  search()
  {console.log("in sercah button",this.listleaves.filter(item =>typeof item.username === 'string' && item.username.toLowerCase().includes(this.searchText.toLowerCase()) ));  this.listleaves=this.listleaves.filter(item =>typeof item.username === 'string' && item.username.toLowerCase().includes(this.searchText.toLowerCase()) );  }


}
