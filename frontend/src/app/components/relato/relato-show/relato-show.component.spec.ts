import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RelatoShowComponent } from './relato-show.component';

describe('RelatoShowComponent', () => {
  let component: RelatoShowComponent;
  let fixture: ComponentFixture<RelatoShowComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RelatoShowComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RelatoShowComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
