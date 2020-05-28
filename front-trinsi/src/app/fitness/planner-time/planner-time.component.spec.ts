import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PlannerTimeComponent } from './planner-time.component';

describe('PlannerTimeComponent', () => {
  let component: PlannerTimeComponent;
  let fixture: ComponentFixture<PlannerTimeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PlannerTimeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PlannerTimeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
