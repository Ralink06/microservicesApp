import {Component, OnInit} from '@angular/core';
import {NgbActiveModal} from "@ng-bootstrap/ng-bootstrap";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {UserService} from "../../user/user.service";
import {UserRegistration} from "../../user/model/user-registration";

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
      email: ['', Validators.compose([Validators.required, Validators.minLength(2), Validators.maxLength(255), Validators.email])],
      password: ['', Validators.compose([Validators.required, Validators.minLength(8), Validators.maxLength(255)])]
    })
  }

  register() {
    if(this.form.valid) {
      const registerData: UserRegistration = {
        firstName: this.form.value.firstName,
        lastName: this.form.value.lastName,
        email: this.form.value.email,
        password: this.form.value.password
      };
      this.userService.registerUser(registerData)
        .subscribe(value => console.log(value),
          error => this.handleBadRequest(error))
    }
  }

  handleBadRequest(error) {
    error.error.errors.forEach(error =>
      this.form.get(error.field).setErrors({'emailUsed': true}));
  }
}
