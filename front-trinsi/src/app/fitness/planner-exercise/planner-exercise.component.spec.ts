import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PlannerExerciseComponent } from './planner-exercise.component';

describe('PlannerExerciseComponent', () => {
  let component: PlannerExerciseComponent;
  let fixture: ComponentFixture<PlannerExerciseComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PlannerExerciseComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PlannerExerciseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
