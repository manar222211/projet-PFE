import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DetailLeavesComponent } from './detail-leaves.component';

describe('DetailLeavesComponent', () => {
  let component: DetailLeavesComponent;
  let fixture: ComponentFixture<DetailLeavesComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [DetailLeavesComponent]
    });
    fixture = TestBed.createComponent(DetailLeavesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
