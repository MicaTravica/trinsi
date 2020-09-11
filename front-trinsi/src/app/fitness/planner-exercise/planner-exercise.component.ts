import { Component, OnInit, Input, Output, EventEmitter, OnChanges, AfterViewInit } from '@angular/core';
import { UserPlanner } from 'src/app/models/user-planner/user-planner.model';
import { Exercise } from 'src/app/models/exercise/exercise.model';
import { PlannerService } from 'src/app/services/planner-service/planner.service';
import { MatDialog } from '@angular/material';
import { ViewExerciseComponent } from 'src/app/exercise/view-exercise/view-exercise.component';
import { PlannerTimeComponent } from '../planner-time/planner-time.component';
import { HeartBeatTracking } from 'src/app/models/heart-beat-tracking/heart-beat-tracking.model';
import { UserService } from 'src/app/services/user-service/user.service';
import { AlarmService } from 'src/app/services/alarm-service/alarm.service';
import { ToastrService } from 'ngx-toastr';
import { HealthService } from 'src/app/services/health-service/health.service';

@Component({
  selector: 'app-planner-exercise',
  templateUrl: './planner-exercise.component.html',
  styleUrls: ['./planner-exercise.component.scss']
})
export class PlannerExerciseComponent implements OnChanges {

  @Input() taken: boolean;
  @Output() refreshHealth = new EventEmitter();
  planner: UserPlanner = null;
  displayedColumns: string[] = ['name', 'type', 'repetitions', 'details'];
  exercises = [];
  sim = false;
  start: Date;

  constructor(
    private plannerService: PlannerService,
    private dialog: MatDialog,
    private userService: UserService,
    private alarmService: AlarmService,
    private toastr: ToastrService,
    private healthService: HealthService
  ) { }

  ngOnChanges() {
    if (this.taken) {
      this.getPlanner();
    } else {
      this.planner = null;
    }
  }

  getPlanner() {
    this.plannerService.get().subscribe(
      (data: UserPlanner) => {
        this.refreshHealth.emit();
        this.planner = data;
        this.exercises = this.planner.exercisesWarmUp.concat(this.planner.exercises, this.planner.exercisesWarmUp,
          this.planner.exercisesStretching);
      }
    );
  }

  warmUp(exercise: Exercise) {
    for (const e of this.planner.exercisesWarmUp) {
      if (e.id === exercise.id) {
        return true;
      }
    }
    return false;
  }

  addTime() {
    const dialogRef = this.dialog.open(PlannerTimeComponent, {
      width: '33%',
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.refreshHealth.emit();
      }
    });
  }

  details(exercise: Exercise) {
    const dialogRef = this.dialog.open(ViewExerciseComponent, {
      width: '70%',
      data: exercise
    });
  }

  async simulation() {
    this.sim = true;
    this.start = new Date();
    const wait = (ms) => new Promise(res => setTimeout(res, ms));
    const id = this.userService.getUserId();
    let num = this.planner.upperPulseLimit + 35;
    while (this.sim) {
      const hbt = new HeartBeatTracking(id, num, this.planner.lowerPulseLimit, this.planner.upperPulseLimit,
        this.planner.bloodPressureClassification, this.planner.nutritionLevel);
      this.alarmService.heartBeatTracking(hbt).subscribe(
        (data: string) => {
          if (data !== '') {
            this.toastr.warning(data);
          }
        }
      );
      num -= 1;
      await wait(2000);
    }
  }

  stopSimulation() {
    this.sim = false;
    const stop = new Date().getTime() - this.start.getTime();
    this.healthService.addTime(Math.round(stop / 60000)).subscribe(
      () => {
        this.toastr.success('Successfully add time!');
        this.refreshHealth.emit();
      }
    );
  }
}
