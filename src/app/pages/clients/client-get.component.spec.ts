import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ClientGetComponent } from './client-get.component';

describe('ClientGetComponent', () => {
  let component: ClientGetComponent;
  let fixture: ComponentFixture<ClientGetComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ClientGetComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ClientGetComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});