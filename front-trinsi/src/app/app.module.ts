import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { UserService } from './services/user-service/user.service';
import { AuthService } from './services/auth-service/auth.service';
import { LoginGuard } from './guards/login.service';
import { RoleGuard } from './guards/role.service';
import { ErrorDialogService } from './services/error-dialog-service/error-dialog.service';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { ErrorHandlingInterceptor } from './interceptors/error.handling.intercepotr';
import { Router } from '@angular/router';
import { MaterialModule } from './material.module';
import { HeaderComponent } from './core/header/header.component';
import { MenuComponent } from './core/menu/menu.component';
import { LoginComponent } from './core/login/login.component';
import { RegisterComponent } from './core/register/register.component';
import { ProfileComponent } from './core/profile/profile.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { ChangePasswordComponent } from './core/profile/change-password/change-password.component';
import { ToastrModule } from 'ngx-toastr';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    MenuComponent,
    LoginComponent,
    RegisterComponent,
    ProfileComponent,
    PageNotFoundComponent,
    ChangePasswordComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MaterialModule,
    HttpClientModule,
    ToastrModule.forRoot(),
    ReactiveFormsModule
  ],
  providers: [
    UserService,
    AuthService,
    LoginGuard,
    RoleGuard,
    ErrorDialogService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: ErrorHandlingInterceptor,
      multi: true,
      deps: [ErrorDialogService, Router]
    }
  ],
  exports: [
    ToastrModule
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
