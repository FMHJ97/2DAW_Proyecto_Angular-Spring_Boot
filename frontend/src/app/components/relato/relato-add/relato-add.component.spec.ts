import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RelatoAddComponent } from './relato-add.component';

describe('RelatoAddComponent', () => {
  let component: RelatoAddComponent;
  let fixture: ComponentFixture<RelatoAddComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RelatoAddComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RelatoAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
