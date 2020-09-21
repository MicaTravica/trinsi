import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { RoleGuard } from './guards/role.service';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';

const routes: Routes = [
  {
    path: 'user',
    loadChildren: () => import('./core/core.module').then(m => m.CoreModule)
  },
  {
    path: 'exercises',
    loadChildren: () => import('./exercise/exercise.module').then(m => m.ExerciseModule)
  },
  {
    path: 'planner',
    loadChildren: () => import('./fitness/fitness.module').then(m => m.FitnessModule),
    canActivate: [RoleGuard], data: {expectedRoles: 'ROLE_REGULAR'}
  },
  { path: '', redirectTo: 'exercises', pathMatch: 'full' },
  { path: '**', component: PageNotFoundComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
