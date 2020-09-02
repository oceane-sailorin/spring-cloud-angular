import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import {NeedEditComponent } from './need-edit.component';

describe('NeedEditComponent', () => {
  let component: NeedEditComponent;
  let fixture: ComponentFixture<NeedEditComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NeedEditComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NeedEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});