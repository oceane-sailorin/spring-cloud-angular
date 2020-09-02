import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ProfileGetComponent } from './profile-get.component';

describe('ProfileGetComponent', () => {
  let component: ProfileGetComponent;
  let fixture: ComponentFixture<ProfileGetComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ProfileGetComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProfileGetComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});