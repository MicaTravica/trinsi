import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './core/login/login.component';
import { LoginGuard } from './guards/login.service';
import { RegisterComponent } from './core/register/register.component';
import { ProfileComponent } from './core/profile/profile.component';
import { RoleGuard } from './guards/role.service';
import { ChangePasswordComponent } from './core/profile/change-password/change-password.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { AddExerciseComponent } from './exercise/add-exercise/add-exercise.component';
import { ListExerciseComponent } from './exercise/list-exercise/list-exercise.component';
import { ViewExerciseComponent } from './exercise/view-exercise/view-exercise.component';
import { PlannerComponent } from './fitness/planner/planner.component';
import { HealthComponent } from './fitness/health/health.component';


const routes: Routes = [
  { path: 'login', component: LoginComponent, canActivate: [LoginGuard] },
  { path: 'register', component: RegisterComponent, canActivate: [LoginGuard] },
  { path: 'profile', component: ProfileComponent, canActivate: [RoleGuard], data: {expectedRoles: 'ROLE_ADMIN|ROLE_REGULAR'}},
  { path: 'change-password', component: ChangePasswordComponent, canActivate: [RoleGuard],
   data: {expectedRoles: 'ROLE_ADMIN|ROLE_REGULAR'}},
  { path: 'exercises', component: ListExerciseComponent, canActivate: [RoleGuard], data: {expectedRoles: 'ROLE_ADMIN|ROLE_REGULAR'}},
  { path: 'planner', component: PlannerComponent, canActivate: [RoleGuard], data: {expectedRoles: 'ROLE_REGULAR'}},
  { path: '', redirectTo: 'exercises', pathMatch: 'full' },
  { path: '**', component: PageNotFoundComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
