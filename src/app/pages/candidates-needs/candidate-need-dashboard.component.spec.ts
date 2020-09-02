import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CandidateNeedDashboardComponent } from './candidate-need-dashboard.component';

describe('CandidateNeedDashboardComponent', () => {
  let component: CandidateNeedDashboardComponent;
  let fixture: ComponentFixture<CandidateNeedDashboardComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CandidateNeedDashboardComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CandidateNeedDashboardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});