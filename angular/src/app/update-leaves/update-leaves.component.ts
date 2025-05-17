import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AllmyservicesService } from '../services/allmyservices.service';
import { ActivatedRoute, ParamMap, Router } from '@angular/router';

@Component({
  selector: 'app-update-leaves',
  templateUrl: './update-leaves.component.html',
  styleUrls: ['./update-leaves.component.css']
})
export class UpdateLeavesComponent implements OnInit{
  constructor(private service:AllmyservicesService,private router:Router,private formbuilder:FormBuilder,private route:ActivatedRoute){}
  listusers:any;
  detailleaves:any;
  form:FormGroup
  leavesId:String
    ngOnInit(): void {
     this.form=  this.formbuilder.group({
      startDate:["",Validators.required],
      endDate:["",Validators.required],
      leaveType:["",Validators.required],
      numberOfDays:["",Validators.required],
      iduser:["",Validators.required],
     })
        this.route.paramMap.subscribe((params:ParamMap)=>{
              this.leavesId = params.get('id');
              if(this.leavesId){
                this.getdetailleaves(this.leavesId);
              }
            })
  this.allusers()
    }
    updateleaves(){
      console.log("startDate",this.form.value.startDate)
      console.log("endDate",this.form.value.endDate)
      console.log("leaveType",this.form.value.leaveType)
      console.log("numberOfDays",this.form.value.numberOfDays)
      console.log("iduser",this.form.value.iduser)
      let formdata= new FormData()
      formdata.append("startDate",this.form.value.startDate)
      formdata.append("endDate",this.form.value.endDate)
      formdata.append("leaveType",this.form.value.leaveType)
     // formdata.append("numberOfDays",this.form.value.numberOfDays)
      formdata.append("iduser",this.form.value.iduser)
      this.service.UpdateLeaves(this.leavesId,formdata).subscribe(
        (res)=>{console.log("leaves updated sucessfully",res);
        },
        (error)=>{console.log("error",error)}
      )
      this.router.navigateByUrl("/leaves")  }
      getdetailleaves(id:String){
        this.service.DetailLeaves(id).subscribe(
          (res)=>{console.log("detail des leaves:",res);
            this.detailleaves=res;
            this.form.patchValue({
              leaveType:this.detailleaves.leaveType,
            })
          },
          (error)=>{console.log("error",error)}
        )
      }
      allusers(){
        this.service.AllUsers().subscribe(
          (res)=>{console.log("la list de s utilisateurs:",res);
            this.listusers=res;
          },
          (error)=>{console.log("error",error)}
        )
      }
  }