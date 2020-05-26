import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators, FormGroup, FormControl } from '@angular/forms';
import { Router } from '@angular/router';
import { UserService } from 'src/app/services/user-service/user.service';
import { AuthService } from 'src/app/services/auth-service/auth.service';
import { User } from 'src/app/models/user-model/user.model';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {

  name: FormControl = new FormControl('', [Validators.required]);
  surname: FormControl = new FormControl('', [Validators.required]);
  email: FormControl = new FormControl({value: '', disabled: true }, [Validators.required]);
  username: FormControl = new FormControl('', [Validators.required]);
  profileForm: FormGroup;

  userID: number;

  constructor(
        private formBuilder: FormBuilder,
        private router: Router,
        private userService: UserService,
        private authService: AuthService,
        private toastr: ToastrService
    ) {
      this.profileForm = this.formBuilder.group({
        name: this.name,
        surname: this.surname,
        email: this.email,
        username: this.username
      });
  }

  ngOnInit() {
    this.userService.me(this.authService.getToken()).subscribe( (userData: User) => {
      this.name.setValue(userData.name);
      this.surname.setValue(userData.surname);
      this.email.setValue(userData.email);
      this.username.setValue(userData.username);
      this.userID = userData.id;
    });
  }

  onSubmit() {
    const payLoad = new User(
      this.userID,
      this.name.value,
      this.surname.value,
      this.email.value,
      this.username.value
    );
    this.userService.updateMyData(payLoad).subscribe( (token: string) => {
        localStorage.setItem('token', token);
        this.userService.me(token).subscribe(user => {
          localStorage.setItem('user', JSON.stringify(user));
          this.toastr.success('Successfully changed profile data');
        });
      });
  }

}
