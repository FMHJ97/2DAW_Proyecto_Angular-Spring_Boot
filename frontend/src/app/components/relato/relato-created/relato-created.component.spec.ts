import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RelatoCreatedComponent } from './relato-created.component';

describe('RelatoCreatedComponent', () => {
  let component: RelatoCreatedComponent;
  let fixture: ComponentFixture<RelatoCreatedComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RelatoCreatedComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RelatoCreatedComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
