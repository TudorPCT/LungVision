import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PredictionsTableComponent } from './predictions-table.component';

describe('PredictionsTableComponent', () => {
  let component: PredictionsTableComponent;
  let fixture: ComponentFixture<PredictionsTableComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PredictionsTableComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PredictionsTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
