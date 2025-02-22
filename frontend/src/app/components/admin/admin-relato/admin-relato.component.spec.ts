import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminRelatoComponent } from './admin-relato.component';

describe('AdminRelatoComponent', () => {
  let component: AdminRelatoComponent;
  let fixture: ComponentFixture<AdminRelatoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AdminRelatoComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdminRelatoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
