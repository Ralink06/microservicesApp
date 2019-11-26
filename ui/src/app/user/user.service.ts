import {Injectable} from '@angular/core';
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {UserRegistration} from "./model/user-registration";
import {delay} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) {
  }

  public registerUser(registerData: UserRegistration): Observable<UserRegistration> {
    return this.http.post<UserRegistration>('api/user/register', registerData);
  }

  public checkEmailNotTaken(email: string): Observable<any> {
    return this.http.get(`api/user/find/${email}`)
      .pipe(delay(3000));
  }
}
