import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LeavesbyuserComponent } from './leavesbyuser.component';

describe('LeavesbyuserComponent', () => {
  let component: LeavesbyuserComponent;
  let fixture: ComponentFixture<LeavesbyuserComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [LeavesbyuserComponent]
    });
    fixture = TestBed.createComponent(LeavesbyuserComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
