import { Component, OnInit } from '@angular/core';
import { AllmyservicesService } from '../services/allmyservices.service';
import Swal from 'sweetalert2';
import { Router } from '@angular/router';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit{
        iduserconnected:String=""
  roleuserconnected:String=""

listusers:any 
c:number=5;
searchText:String=""


constructor(private service:AllmyservicesService, private router:Router) {}


allusers(){
  this.service.AllUsers().subscribe(

    (res)=>{console.log("la list de s utilisateurs:",res);

      this.listusers=res;
    },

    (error)=>{console.log("error",error)}

  )
}


  ngOnInit(): void {


   
        

    
          

          this.allusers();

        }
        
    search()
    {console.log("in sercah button",this.listusers.filter(item =>typeof item.username === 'string' && item.username.toLowerCase().includes(this.searchText.toLowerCase()) ));  this.listusers=this.listusers.filter(item =>typeof item.username === 'string' && item.username.toLowerCase().includes(this.searchText.toLowerCase()) );  }

  }
  





  


