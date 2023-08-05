import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CursComponent } from './curs.component';

describe('CursComponent', () => {
  let component: CursComponent;
  let fixture: ComponentFixture<CursComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CursComponent]
    });
    fixture = TestBed.createComponent(CursComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
