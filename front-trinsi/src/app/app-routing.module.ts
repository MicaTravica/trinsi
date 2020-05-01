import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './core/login/login.component';
import { LoginGuard } from './guards/login.service';
import { RegisterComponent } from './core/register/register.component';
import { ProfileComponent } from './core/profile/profile.component';
import { RoleGuard } from './guards/role.service';
import { ChangePasswordComponent } from './core/profile/change-password/change-password.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';


const routes: Routes = [
  { path: 'login', component: LoginComponent, canActivate: [LoginGuard] },
  { path: 'register', component: RegisterComponent, canActivate: [LoginGuard] },
  { path: 'profile', component: ProfileComponent,
   canActivate: [RoleGuard], data: {expectedRoles: 'ROLE_ADMIN|ROLE_REGULAR'}
  },
  { path: 'changePassword', component: ChangePasswordComponent,
     canActivate: [RoleGuard], data: {expectedRoles: 'ROLE_ADMIN|ROLE_REGULAR'}
  },
  { path: '', redirectTo: 'homepage', pathMatch: 'full' },
  { path: '**', component: PageNotFoundComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
