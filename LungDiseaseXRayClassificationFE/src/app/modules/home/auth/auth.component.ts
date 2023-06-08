import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {AuthService} from "../../../services/auth/auth.service";
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {TranslateService} from "@ngx-translate/core";
@Component({
  selector: 'app-auth',
  templateUrl: './auth.component.html',
  styleUrls: ['./auth.component.css']
})
export class AuthComponent implements OnInit{

  @Input()
  public display: boolean = false;
  @Output() displayValueUpdated: EventEmitter<boolean> = new EventEmitter<boolean>();

  loginText?: string;
  signUpText?: string;
  submitted: boolean = false;

  authForm = new FormGroup({
    email: new FormControl('', [Validators.required, Validators.email]),
    password: new FormControl(
      '',
      [
        Validators.required,
        Validators.pattern("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$")
      ]),
    confirmPassword: new FormControl('', [Validators.required]),
  });

  isEmailInvalid: boolean = false;
  isPasswordInvalid: boolean = false;
  isConfirmPasswordInvalid: boolean = false;
  signUpTrigger: boolean = true;
  formHeight: string = '500px';

  constructor(private authService: AuthService, private translate: TranslateService) {}

  ngOnInit(): void {
    this.loginText = this.translate.instant("auth.login")
    this.signUpText = this.translate.instant("auth.signup")
  }

  onSubmit(){
    if (this.submitted || this.isEmailInvalid || this.isPasswordInvalid)
      return;

    this.submitted = true;

    if(this.signUpTrigger) {
      this.onLogin();
    }
    else
      this.onSignUp();

    this.submitted = false;
  }

  onLogin() {
    this.authService.login(this.authForm.value.email, this.authForm.value.password);
  }

  onSignUp() {
    if(this.isConfirmPasswordInvalid)
      return;

    this.authService.signUp(this.authForm.value.email, this.authForm.value.password);
  }

  getEmailError() {
    return this.authForm.controls.email.hasError('required') ?
      this.translate.instant("error.isRequired", {field: 'Email'}):
      this.authForm.controls.email.hasError('email') ?
        this.translate.instant("error.invalid", {field: 'Email'}) : '';
  }

  getPasswordError() {
    return this.authForm.controls.password.hasError('required') ?
      this.translate.instant("error.isRequired", {field: this.translate.instant("auth.pass")}) :
      (this.authForm.controls.password.hasError('pattern') && !this.signUpTrigger)
        ? this.translate.instant("error.passwordPattern") : '';
  }

  getConfirmPasswordError() {
    return this.authForm.controls.confirmPassword.hasError('required') ?
      this.translate.instant("error.isRequired", {field: this.translate.instant("auth.pass.confirm")}) :
      this.authForm.controls.confirmPassword.value !== this.authForm.controls.password.value ?
        this.translate.instant("error.passwordsNotMatch") : '';
  }

  onHide() {
    this.display = false;
    this.displayValueUpdated.emit(this.display);
  }

  onEmailFocusOut() {
    this.isEmailInvalid = this.authForm.controls.email.hasError('required') ||
      this.authForm.controls.email.hasError('email');
  }

  onPasswordFocusOut() {
    this.isPasswordInvalid = this.authForm.controls.password.hasError('required') ||
      (this.authForm.controls.password.hasError('pattern') && !this.signUpTrigger);
  }

  onConfirmPasswordFocusOut() {
    this.isConfirmPasswordInvalid = this.authForm.controls.confirmPassword.hasError('required') ||
      this.authForm.controls.confirmPassword.value !== this.authForm.controls.password.value;
  }

  changeForm() {
    this.isEmailInvalid = false;
    this.isPasswordInvalid = false;
    this.isConfirmPasswordInvalid = false;
    this.formHeight = this.signUpTrigger ? '500px' : '600px';
  }

}
