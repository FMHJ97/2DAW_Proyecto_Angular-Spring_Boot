import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RelatoEditComponent } from './relato-edit.component';

describe('RelatoEditComponent', () => {
  let component: RelatoEditComponent;
  let fixture: ComponentFixture<RelatoEditComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RelatoEditComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RelatoEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
