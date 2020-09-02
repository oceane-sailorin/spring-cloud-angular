import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NeedGetComponent } from './need-get.component';

describe('NeedGetComponent', () => {
  let component: NeedGetComponent;
  let fixture: ComponentFixture<NeedGetComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NeedGetComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NeedGetComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});