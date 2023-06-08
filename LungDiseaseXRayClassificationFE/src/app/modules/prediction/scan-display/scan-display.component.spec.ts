import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ScanDisplayComponent } from './scan-display.component';

describe('ScanDisplayComponent', () => {
  let component: ScanDisplayComponent;
  let fixture: ComponentFixture<ScanDisplayComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ScanDisplayComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ScanDisplayComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
