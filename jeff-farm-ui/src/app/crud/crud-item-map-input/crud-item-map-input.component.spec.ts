import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CrudItemMapInputComponent } from './crud-item-map-input.component';

describe('CrudItemMapInputComponent', () => {
  let component: CrudItemMapInputComponent;
  let fixture: ComponentFixture<CrudItemMapInputComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CrudItemMapInputComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CrudItemMapInputComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
