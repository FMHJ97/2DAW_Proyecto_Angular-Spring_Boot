import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminUsuarioComponent } from './admin-usuario.component';

describe('AdminUsuarioComponent', () => {
  let component: AdminUsuarioComponent;
  let fixture: ComponentFixture<AdminUsuarioComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AdminUsuarioComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdminUsuarioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
