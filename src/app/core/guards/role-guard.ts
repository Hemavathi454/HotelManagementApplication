import { ActivatedRouteSnapshot, CanActivateFn, Router } from '@angular/router';
import { inject } from '@angular/core';
import { AuthService } from '../auth/AuthService';

export const roleGuard: CanActivateFn = (route: ActivatedRouteSnapshot) => {
  const authService = inject(AuthService);
  const router = inject(Router);

  const allowedRoles = route.data['roles'] as string[] | undefined;
  const userRole = authService.getUserRole();

  if (!authService.isLoggedIn()) {
    return router.createUrlTree(['/login']);
  }

  if (allowedRoles && userRole && allowedRoles.includes(userRole)) {
    return true;
  }

  return router.createUrlTree(['/login']);
};
