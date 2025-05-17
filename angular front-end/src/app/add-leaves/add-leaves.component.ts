import { Component, OnInit } from '@angular/core';
import { AllmyservicesService } from '../services/allmyservices.service';
import { Router } from '@angular/router';
import { Form, FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-add-leaves',
  templateUrl: './add-leaves.component.html',
  styleUrls: ['./add-leaves.component.css']
})
export class AddLeavesComponent implements OnInit{

constructor(private service:AllmyservicesService,private router:Router,private formbuilder:FormBuilder){}
listusers:any;
form:FormGroup



  allusers(){
    this.service.AllUsers().subscribe(
  
      (res)=>{console.log("la list de s utilisateurs:",res);
  
        this.listusers=res;
      },
  
      (error)=>{console.log("error",error)}
  
    )

   

  }

  ngOnInit(): void {
   this.form=  this.formbuilder.group({
    startDate:["",Validators.required],
    endDate:["",Validators.required],
    leaveType:["",Validators.required],
    numberOfDays:["",Validators.required],
    iduser:["",Validators.required],

   }) 

      this.allusers()
  }
  addleaves(){
    console.log("startDate",this.form.value.startDate)
    console.log("endDate",this.form.value.endDate)
    console.log("leaveType",this.form.value.leaveType)
    console.log("numberOfDays",this.form.value.numberOfDays)
    console.log("iduser",this.form.value.iduser)
    let formdata= new FormData()
    formdata.append("startDate",this.form.value.startDate)
    formdata.append("endDate",this.form.value.endDate)
    formdata.append("leaveType",this.form.value.leaveType)
    
    this.service.CreateLeaves(formdata,this.form.value.iduser).subscribe(
      (res)=>{console.log("leaves added sucessfully",res);
      },
      (error)=>{console.log("error",error)}
    )
    this.router.navigateByUrl("/leaves")  }
}

  
  
  





