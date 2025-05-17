import { Component, OnInit } from '@angular/core';
import { AllmyservicesService } from '../services/allmyservices.service';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-add-employer',
  templateUrl: './add-employer.component.html',
  styleUrls: ['./add-employer.component.css']
})
export class AddEmployerComponent implements OnInit{
  listemployer:any
  form:FormGroup
  fileToUpload: Array<File> = [];


  
  constructor(private service :AllmyservicesService,private router:Router,private formbuilder:FormBuilder){}

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
     this.form=  this.formbuilder.group({
      firstname:["",Validators.required],
      lastname:["",Validators.required],
      username:["",Validators.required],
      email:["", [Validators.required, Validators.email]],
      password:["",Validators.required],
      phone:["",[Validators.required, Validators.pattern('^[0-9]{10}$')]],
      role:["",Validators.required],
      adresse:["",Validators.required],
      file:[null, Validators.required],
  
     }) 
  
        this.allemployer()
    }



    handleFileInput(files: any)
    {
      this.fileToUpload = <Array<File>>files.target.files;
      console.log(this.fileToUpload);
    }

    addemployer(){
      console.log("firstname",this.form.value.firstname)
      console.log("lastname",this.form.value.lastname)
  
      console.log("username",this.form.value.username)
      console.log("email",this.form.value.email)
      console.log("password",this.form.value.password)
      console.log("phone",this.form.value.phone)
      
      console.log("adresse",this.form.value.adresse)
      
  
  
      let formdata= new FormData()
      formdata.append("firstname",this.form.value.firstname)
      formdata.append("lastname",this.form.value.lastname)
      formdata.append("username",this.form.value.username)
      formdata.append("email",this.form.value.email)
      formdata.append("password",this.form.value.password)
      formdata.append("phone",this.form.value.phone)   
       formdata.append("role","user")
      formdata.append("adresse",this.form.value.adresse)
      formdata.append("file",this.fileToUpload[0]),


  
      this.service.CreateAdmin(formdata).subscribe(
        (res)=>{console.log("user added sucessfully",res);
        },
        (error)=>{console.log("error",error)}
      )
      this.router.navigateByUrl("/employer")  }
}
