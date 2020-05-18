import { Component, OnInit } from '@angular/core';
import { HealthService } from 'src/app/services/health-service/health.service';
import { UserHealth } from 'src/app/models/user-health/user-health.model';
import { GENDER } from 'src/app/models/enums/gender.enum';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';

@Component({
  selector: 'app-health',
  templateUrl: './health.component.html',
  styleUrls: ['./health.component.scss']
})
export class HealthComponent implements OnInit {

  health = new UserHealth(null, null, null, null, null, null, null, null);
  genders = [ GENDER.MALE, GENDER.FEMALE ];

  constructor(
    private healthService: HealthService,
    private toastr: ToastrService,
    private router: Router
  ) { }

  ngOnInit() {
    this.healthService.get().subscribe(
      (data: UserHealth) => {
        this.health = data;
      }
    );
  }

  add() {
    this.healthService.add(this.health).subscribe(
      (data: number) => {
        this.toastr.success('Successful add!');
        this.router.navigate(['/planner']);
      }
    );
  }
}
