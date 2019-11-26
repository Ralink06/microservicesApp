import {Component, OnInit} from '@angular/core';
import {NgbActiveModal} from "@ng-bootstrap/ng-bootstrap";
import {AbstractControl, AsyncValidatorFn, FormBuilder, FormGroup, Validators} from "@angular/forms";
import {UserService} from "../../user/user.service";
import {UserRegistration} from "../../user/model/user-registration";
import {Observable, of} from "rxjs";
import {catchError, map} from "rxjs/operators";

@Component({
  selector: 'app-registration-modal',
  templateUrl: './registration-modal.component.html',
  styleUrls: ['./registration-modal.component.scss']
})
export class RegistrationModalComponent implements OnInit {

  public form: FormGroup;

  constructor(public activeModal: NgbActiveModal,
              private fb: FormBuilder,
              private userService: UserService) {
  }

  ngOnInit() {
    this.form = this.fb.group({
      firstName: ['', Validators.compose([Validators.required, Validators.minLength(2), Validators.maxLength(60)])],
      lastName: ['', Validators.compose([Validators.required, Validators.minLength(2), Validators.maxLength(60)])],
      email: ['', [Validators.required, Validators.minLength(2), Validators.maxLength(255), Validators.email]],
      password: ['', Validators.compose([Validators.required, Validators.minLength(8), Validators.maxLength(255)])]
    })
  }

  register() {
    if (this.form.valid) {
      const registerData: UserRegistration = {
        firstName: this.form.value.firstName,
        lastName: this.form.value.lastName,
        email: this.form.value.email,
        password: this.form.value.password
      };
      this.userService.registerUser(registerData)
        .subscribe(value => console.log(value))
    }
  }

  userValidator(): AsyncValidatorFn {
    return (control: AbstractControl): Observable<{ [key: string]: any } | null> => {
      return this.userService.checkEmailNotTaken(control.value)
        .pipe(
          map(res => {
            return {'emailUsed': true}
          }),
          catchError(err => {
            return of();
          })
        )
    };
  }
}
