import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ScanSaveComponent } from './scan-save.component';

describe('ScanSaveComponent', () => {
  let component: ScanSaveComponent;
  let fixture: ComponentFixture<ScanSaveComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ScanSaveComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ScanSaveComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
