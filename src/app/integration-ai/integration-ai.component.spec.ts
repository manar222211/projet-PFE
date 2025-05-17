import { ComponentFixture, TestBed } from '@angular/core/testing';

import { IntegrationAIComponent } from './integration-ai.component';

describe('IntegrationAIComponent', () => {
  let component: IntegrationAIComponent;
  let fixture: ComponentFixture<IntegrationAIComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [IntegrationAIComponent]
    });
    fixture = TestBed.createComponent(IntegrationAIComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
