import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginGuard } from '../guards/login.service';
import { RoleGuard } from '../guards/role.service';
import { LoginComponent } from './login/login.component';
import { ChangePasswordComponent } from './profile/change-password/change-password.component';
import { ProfileComponent } from './profile/profile.component';
import { RegisterComponent } from './register/register.component';

const routes: Routes = [
  { path: 'login',
    component: LoginComponent,
    canActivate: [LoginGuard]
  },
  { path: 'register',
    component: RegisterComponent,
    canActivate: [LoginGuard]
  },
  { path: 'profile',
    component: ProfileComponent,
    canActivate: [RoleGuard],
    data: {expectedRoles: 'ROLE_ADMIN|ROLE_REGULAR'}
  },
  { path: 'change-password',
    component: ChangePasswordComponent,
    canActivate: [RoleGuard],
    data: {expectedRoles: 'ROLE_ADMIN|ROLE_REGULAR'}
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CoreRoutingModule { }
