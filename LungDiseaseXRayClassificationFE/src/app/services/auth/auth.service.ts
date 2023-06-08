import {Injectable} from '@angular/core';
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {environment} from "../../../environments/environment";
import {Router} from "@angular/router";
import {catchError, tap} from "rxjs/operators";
import {FormControl, ɵValue} from "@angular/forms";
import {EMPTY, lastValueFrom} from "rxjs";
import {MessageService} from "primeng/api";
import {TranslateService} from "@ngx-translate/core";

@Injectable({
  providedIn: 'root'
})
export class AuthService{

  private authenticated : boolean = false;
  private register: boolean = false;

  constructor(private translate: TranslateService, private http: HttpClient, private router: Router, private messageService: MessageService) {
    this.validateToken().then(() => {});
  }

    login(email: ɵValue<FormControl<string | null>> | undefined, password: ɵValue<FormControl<string | null>> | undefined){

    let requestPath: string = environment.apiUrl + `/api/auth/login`;

    this.http.post(requestPath, {email: email, password: password}).pipe(
      tap((res: any) => {
        sessionStorage.setItem("token", res.token);
        this.authenticated = true;
        if (!this.register)
          this.messageService.add({severity:'success', summary: 'Success', detail: this.translate.instant('auth.loginSuccess')})
        this.register = false;
        this.router.navigateByUrl("").then(() => {});
      }),
      catchError((err: HttpErrorResponse) => {
        this.messageService.add({severity:'error', summary: 'Error', detail: err.error.message});
        return EMPTY;
      }),
    ).subscribe();
  }

  logout(){
    sessionStorage.removeItem("token");
    this.authenticated = false;
  }

  async validateToken() {
    try {
      const tokenValidation$ = this.http.get(environment.apiUrl + "/api/auth/validate").pipe(
        tap(() => {
          this.authenticated = true;
        }),
        catchError((err: any) => {
          this.logout();
          return EMPTY;
        }),
      );
      await lastValueFrom(tokenValidation$);
    } catch (e) {}
  }

  isAuthenticated(){
    return this.authenticated;
  }

  signUp(email: ɵValue<FormControl<string | null>> | undefined, password: ɵValue<FormControl<string | null>> | undefined) {
    let requestPath: string = environment.apiUrl + `/api/auth/register`;

    this.http.post<any>(requestPath, {email: email, password: password}).pipe(
      catchError((err: HttpErrorResponse) => {
        this.messageService.add({severity:'error', summary: 'Error', detail: err.error.message});
        return EMPTY;
      }),
      tap((res: any) => {
        this.register = true;
        this.login(email, password)
        this.messageService.add({severity:'success', summary: 'Success', detail: res.message})
      }),
    ).subscribe();
  }

}
