import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import {AddCandidateNeedComponent } from './add-candidate-need.component';

describe('AddCandidateNeedComponent', () => {
  let component: AddCandidateNeedComponent;
  let fixture: ComponentFixture<AddCandidateNeedComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddCandidateNeedComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddCandidateNeedComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});