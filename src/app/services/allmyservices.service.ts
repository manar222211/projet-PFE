import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AllmyservicesService {

  
 constructor(private http:HttpClient) {}

 //admin
AllAdmin()
{return this.http.get(`${environment.baseUrl}/User/allbyrole?role=admin`)}

CreateAdmin(Data:any)
{return this.http.post(`${environment.baseUrluser}/User/createphoto`,Data)}

DetailAdmin(id:String)
{return this.http.get(`${environment.baseUrl}/User/details/${id}`)}

UpdateAdmin(id:String, Data:any)
{return this.http.put(`${environment.baseUrluser}/User/update/${id}`,Data)}

DeleteAdmin(id:String){
  return this.http.delete(`${environment.baseUrluser}/User/delete/${id}`)
}

//user
AllUsers()
{return this.http.get(`${environment.baseUrl}/User/all`)}

//employe
AllEmploye()
{return this.http.get(`${environment.baseUrl}/User/allbyrole?role=user`)}
Detailemploye(id:String)
{return this.http.get(`${environment.baseUrl}/User/allbyrole?role=user/${id}`)}

Deleteemploye(id:String)
{return this.http.get(`${environment.baseUrl}/User/allbyrole?role=user/${id}`)}


//leaves
AllLeaves()
{return this.http.get(`${environment.baseUrlleaves}/Leaves/all`)}

allLeavesbyuser(id:String){
  return this.http.get(`${environment.baseUrlleaves}/Leaves/allbyiduser/${id}`)
  
}

CreateLeaves(Data:any,iduser:String)

{
  return this.http.post(`${environment.baseUrlleaves}/Leaves/create/${iduser}`,Data)}

DetailLeaves(id:String)
{return this.http.get(`${environment.baseUrl}/Leaves/details/${id}`)}

UpdateLeaves(id:String, Data:any)
{return this.http.put(`${environment.baseUrlleaves}/Leaves/update/${id}`,Data)}

DeleteLeaves(id:String)
{console.log("id deleted on services is",id);
return this.http.delete(`${environment.baseUrlleaves}/Leaves/delete/${id}`)}


Login(Data:any)
{return this.http.post(`${environment.baseUrl}/User/signin`,Data)}

httpOptions:any;

logout(){
  this.httpOptions  = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
       'Authorization':`Bearer` +` `+ localStorage.getItem("token")
      })
    }
   //console.log("****************httpheadrs**",this.httpOptions);
   return this.http.get(`${environment.baseUrluser}/User/signout`,this.httpOptions)
  }

SendMail(Data:any)
{return this.http.post(`${environment.baseUrluser}/User/SendMail`,Data)}

Allbyrole()
{return this.http.get(`${environment.baseUrl}/User/allbyrole`)}

Updatepicture(id:String, Data:any)
{return this.http.put(`${environment.baseUrl}/User/updatepicture/${id}`,Data)}



}
