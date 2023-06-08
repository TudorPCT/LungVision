import {inject} from '@angular/core';
import {CanActivateFn, Router} from "@angular/router";
import {AuthService} from "../../services/auth/auth.service";
import {MessageService} from "primeng/api";

export const authGuard: CanActivateFn = async () => {
  const authService = inject(AuthService);
  const router = inject(Router);
  const messageService = inject(MessageService);

  await authService.validateToken();

  if (authService.isAuthenticated()) {
    return true;
  }

  messageService.add({severity:'error', summary:'Error', detail:'You must be logged in to access this page.'});
  return router.parseUrl('');
};
