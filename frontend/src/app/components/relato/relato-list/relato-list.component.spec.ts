import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RelatoListComponent } from './relato-list.component';

describe('RelatoListComponent', () => {
  let component: RelatoListComponent;
  let fixture: ComponentFixture<RelatoListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RelatoListComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RelatoListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
