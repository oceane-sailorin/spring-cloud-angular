import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CandidateGetComponent } from './candidate-get.component';

describe('CandidateGetComponent', () => {
  let component: CandidateGetComponent;
  let fixture: ComponentFixture<CandidateGetComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CandidateGetComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CandidateGetComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});