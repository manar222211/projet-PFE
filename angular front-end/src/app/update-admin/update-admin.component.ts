import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AllmyservicesService } from '../services/allmyservices.service';
import { ActivatedRoute, ParamMap, Router } from '@angular/router';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-update-admin',
  templateUrl: './update-admin.component.html',
  styleUrls: ['./update-admin.component.css']
})
export class UpdateAdminComponent implements OnInit{

constructor(private service:AllmyservicesService,private router:Router,private formbuilder:FormBuilder,private route:ActivatedRoute){}
listadmin:any;
form:FormGroup
fileToUpload: Array<File> = [];
detailadmin:any;
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
      file:[null, Validators.required],
  
     }) ;
      this .route.paramMap.subscribe((params: ParamMap)=>{
        this.adminId = params.get('id');
         if (this.adminId){
           this.getadmindetail(this.adminId);



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
       })
           
       



     
  }
  updateadmin(){
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

    this.service.UpdateAdmin(this.adminId,formdata).subscribe(
      (res)=>{console.log("admin added sucessfully",res);
      },
      (error)=>{console.log("error",error)}
    )
    this.router.navigateByUrl("/admin")  }


    getadmindetail(id:String){
      this.service.DetailAdmin(id).subscribe(
        (res:any)=>{
          console.log("les detail de l'admin", res);
          this.detailadmin=res;




          this.form.patchValue({
            username:this.detailadmin.username,
            email:this.detailadmin.email,
            firstname:this.detailadmin.firstname,
            lastname:this.detailadmin.lastname,
            phone:this.detailadmin.phone,
            adresse:this.detailadmin.adresse,
          })
        

        },
        (error: any)=>{
          console.log("Erreur",error);
        }
        );

        
    }
}

  
  
  






