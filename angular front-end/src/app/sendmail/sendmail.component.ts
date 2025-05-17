import { Component, OnInit } from '@angular/core';
import { AllmyservicesService } from '../services/allmyservices.service';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-sendmail',
  templateUrl: './sendmail.component.html',
  styleUrls: ['./sendmail.component.css']
})
export class SendmailComponent implements OnInit{
constructor(private service:AllmyservicesService,private router:Router,private formbuilder:FormBuilder){}

form:FormGroup
resmail:any;

sendmail(){
  console.log("to",this.form.value.to)
  console.log("from",this.form.value.from)
  console.log("subject",this.form.value.subject)
  console.log("content",this.form.value.content)

  let data={
    "to":this.form.value.to,
    "from":this.form.value.from,
    "subject":this.form.value.subject,
    "content":this.form.value.content
}
this.service.SendMail(data).subscribe(
  (res)=>{console.log("send mail:",res);
    this.resmail=res;
  },
  (error)=>{console.log("error",error)}
)

  this.router.navigateByUrl("/home")


  
}

  ngOnInit(): void {
    this.form= this.formbuilder.group({
      to:["",Validators.required],
      from:["",Validators.required],
      subject:["",Validators.required],
      content:["",Validators.required],

      })
      
  }

}
