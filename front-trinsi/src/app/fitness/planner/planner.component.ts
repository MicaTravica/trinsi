import { Component, OnInit } from '@angular/core';
import { UserPlanner } from 'src/app/models/user-planner/user-planner.model';
import { PlannerService } from 'src/app/services/planner-service/planner.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-planner',
  templateUrl: './planner.component.html',
  styleUrls: ['./planner.component.scss']
})
export class PlannerComponent implements OnInit {

  planner = new UserPlanner(null, null, null, null, null, null, null);

  constructor(
    private plannerService: PlannerService,
    private router: Router
  ) { }

  ngOnInit() {
    this.plannerService.get().subscribe(
      (data: UserPlanner) => {
        this.planner = data;
      }
    );
  }

}
