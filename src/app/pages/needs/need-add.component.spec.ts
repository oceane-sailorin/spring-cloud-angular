import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import {NeedAddComponent } from './need-add.component';

describe('NeedAddComponent', () => {
  let component: NeedAddComponent;
  let fixture: ComponentFixture<NeedAddComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NeedAddComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NeedAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});