import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AllmyservicesService } from '../services/allmyservices.service';
import { ActivatedRoute, ParamMap, Router } from '@angular/router';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-update-employer',
  templateUrl: './update-employer.component.html',
  styleUrls: ['./update-employer.component.css']
})
export class UpdateEmployerComponent implements OnInit{

  constructor(private service:AllmyservicesService,private router:Router,private formbuilder:FormBuilder,private route:ActivatedRoute){}
  listadmin:any;
  form:FormGroup
  fileToUpload: Array<File> = [];
  datailemploye:any;
  adminId:String

  iduserconnected:String=""
  roleuserconnected:String=""

  
  
  
  
  
 
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
       
    
       }) 
  
        
        this .route.paramMap.subscribe((params: ParamMap)=>{
                this.adminId = params.get('id');
                if (this.adminId){
                  this.getemployedetail(this.adminId);
                }
              })
              
          
    }
    updateemploye(){
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
      formdata.append("file",this.fileToUpload[0])
  
      this.service.UpdateAdmin(this.adminId,formdata).subscribe(
        (res)=>{console.log("admin added sucessfully",res);
        },
        (error)=>{console.log("error",error)}
      )
      this.router.navigateByUrl("/employer")  


 this.roleuserconnected=localStorage.getItem("roleuserconnected")
               console.log("role",this.roleuserconnected);
           
               this.iduserconnected=localStorage.getItem("iduserconnected")
                   if (this.iduserconnected && this.roleuserconnected=='admin'){
           
           
                     
           
                   }
                  else{
                            console.log("Accès refusé ou rôle non autorisé. Redirection...");
                            Swal.fire({
                              icon: 'warning',
                              title: 'Accès refusé',
                              text: 'Vous êtes connecté en tant qu’utilisateur, l’accès à cette page est réservé aux administrateurs.',
                              confirmButtonText: 'Retour à l’accueil',
                              confirmButtonColor: '#3085d6',
                              backdrop: true,
                              allowOutsideClick: false,
                              allowEscapeKey: false
                            }).then((result) => {
                              if (result.isConfirmed) {
                                this.router.navigate(['/home']);
                              }
                            });
                          }
           
               console.log("iduserconcontedheee",this.iduserconnected);
        


    }

      
      
      
      
      
      
      
      
      
      
      getemployedetail(id:String){
        this.service.DetailAdmin(id).subscribe(
          (res:any)=>{
            console.log("les detail de l'Empoye", res);
            this.datailemploye=res;

            this.form.patchValue({
              username:this.datailemploye.username,
              email:this.datailemploye.email,
              firstname:this.datailemploye.firstname,
              lastname:this.datailemploye.lastname,
              phone:this.datailemploye.phone,
              adresse:this.datailemploye.adresse,
            })

          },
          (error: any)=>{
            console.log("Erreur",error);
          }
          );
        }

  }
  
    
    
    
  
  
  
  
  
  
  
