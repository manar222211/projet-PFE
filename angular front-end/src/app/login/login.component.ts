import { Component, OnInit } from '@angular/core';
import { AllmyservicesService } from '../services/allmyservices.service';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit{
constructor(private service:AllmyservicesService,private router:Router,private formbuilder:FormBuilder){}

form:FormGroup
reslogin:any;


signin(){
  console.log("username",this.form.value.username)
console.log("password",this.form.value.password)
let formdata= new FormData()
formdata.append("username",this.form.value.username)
formdata.append("password",this.form.value.password)
this.service.Login(formdata).subscribe(
  (res)=>{console.log("succeed to login:",res);
    this.reslogin=res;
    //en cas de success de login on doit suavegarder iduser pour autre utilisation
    //de même nous allons sauvegarder token
    localStorage.setItem("iduserconnected",this.reslogin.id) //stocage id dans localstorage
    localStorage.setItem("token",this.reslogin.token)//stacage token
    localStorage.setItem("roleuserconnected",this.reslogin.role)//staockage role

    if(this.reslogin.token != null){
      this.router.navigateByUrl("/home")
    }

    else {
      this.router.navigateByUrl("/")
    }
  },
  (error)=>{console.log("error",error)}
)

 
}

ngOnInit(): void {
 this.form= this.formbuilder.group({
    username:["",Validators.required],
    password:["",Validators.required],
  })
    
}
}
