import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AllmyservicesService } from '../services/allmyservices.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-admin',
  templateUrl: './add-admin.component.html',
  styleUrls: ['./add-admin.component.css']
})
export class AddAdminComponent implements OnInit{

constructor(private service:AllmyservicesService,private router:Router,private formbuilder:FormBuilder){}
listadmin:any;
form:FormGroup
fileToUpload: Array<File> = [];





alladmin(){

  

  this.service.AllAdmin().subscribe(
    (res)=>{console.log("la list de admin:",res);

      this.listadmin=res;
    },

    (error)=>{console.log("error",error)}

  )
  
}

handleFileInput(files: any)
  {
    this.fileToUpload = <Array<File>>files.target.files;
    console.log(this.fileToUpload);
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

      this.alladmin()
  }
  addadmin(){
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
     formdata.append("role","admin")
    formdata.append("adresse",this.form.value.adresse)
    formdata.append("file",this.fileToUpload[0])

    this.service.CreateAdmin(formdata).subscribe(
      (res)=>{console.log("admin added sucessfully",res);
      },
      (error)=>{console.log("error",error)}
    )
    this.router.navigateByUrl("/admin")  }
}

  
  
  






