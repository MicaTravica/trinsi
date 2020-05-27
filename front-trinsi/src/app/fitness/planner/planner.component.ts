import { Component, OnInit } from '@angular/core';
import { UserPlanner } from 'src/app/models/user-planner/user-planner.model';
import { PlannerService } from 'src/app/services/planner-service/planner.service';
import { MatDialog } from '@angular/material';
import { HealthComponent } from '../health/health.component';

@Component({
  selector: 'app-planner',
  templateUrl: './planner.component.html',
  styleUrls: ['./planner.component.scss']
})
export class PlannerComponent implements OnInit {

  planner = null;

  constructor(
    private plannerService: PlannerService,
    private dialog: MatDialog
  ) { }

  ngOnInit() {
    this.getPlanner();
  }

  getPlanner() {
    this.plannerService.get().subscribe(
      (data: UserPlanner) => {
        this.planner = data;
      }
    );
  }

  addHealth() {
    const dialogRef = this.dialog.open(HealthComponent, {
      width: '33%'
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log(result);
      if (result !== undefined) {
        this.getPlanner();
      }
    });
  }

  changeHealth() {
    const dialogRef = this.dialog.open(HealthComponent, {
      width: '33%',
      data: this.planner.id
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log(result);
    });
  }

}
