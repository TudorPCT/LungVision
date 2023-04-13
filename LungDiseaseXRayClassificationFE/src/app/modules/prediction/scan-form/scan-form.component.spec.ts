import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ScanFormComponent } from './scan-form.component';

describe('ScanFormComponent', () => {
  let component: ScanFormComponent;
  let fixture: ComponentFixture<ScanFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ScanFormComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ScanFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
